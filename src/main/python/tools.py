import openpyxl
import pandas as pd


def migrate_data_plan_id():
    file_path = '/Users/andres/Downloads/20230303_Regularización_servicios_flujo_fuera_de_base.xlsx'

    wb = openpyxl.load_workbook(file_path)

    sheet = wb.active

    group: dict = {}

    for row in sheet.iter_rows(min_row=2, min_col=1, max_col=2):
        first: bool = True
        service_id: int = 0
        for cell in row:
            if first:
                service_id = cell.value
                first = False
            elif cell.value in group:
                group[cell.value].append(service_id)
            else:
                group[cell.value] = [service_id]

    group_sorted = {k: v for k, v in sorted(group.items())}

    for key in group_sorted:
        values = ','.join(map(str, group_sorted[key]))
        comments: str = f'-- Migration-backup planId {key}, total of services updated {len(group_sorted[key])} - HU-64175'
        count_query: str = f' SELECT tbm.id, (tbm.data->>\'idPlan\')::bigint AS "planId" FROM mss_booking.tuten_booking_macarena tbm WHERE tbm.context = \'macarena_booking\' AND tbm.domain = \'macarena\' AND tbm.id IN ({values});'
        print(comments)
        print(count_query)

    print()

    for key in group_sorted:
        values = ','.join(map(str, group_sorted[key]))
        comments: str = f'-- Migration planId {key}, total of services updated {len(group_sorted[key])} - HU-64175'
        update_query: str = f'UPDATE mss_booking.tuten_booking_macarena tbm SET data = jsonb_set(tbm.data, \'{{idPlan}}\', \'{key}\')  WHERE tbm.context = \'macarena_booking\' AND tbm.domain = \'macarena\' AND tbm.id IN ({values});'
        print(comments)
        print(update_query)


# migrate_data_plan_id()

def check_if_csv_file_is_valid(file_path: str):
    df = pd.read_csv(file_path, delimiter=';', encoding='utf-8', keep_default_na=False, na_values=[''])
    has_missing_values = False
    for index, row in df.iterrows():
        try:
            if row.isnull().values.any():
                print(f'===> {row.isnull().values}')
                print(f"Row {index + 1} contains missing values.")
                has_missing_values = True
        except UnicodeDecodeError:
            print(f"Exception - Row {index + 1} contains missing values.")
    if not has_missing_values:
        print("CSV is valid.")


check_if_csv_file_is_valid(
    "/Users/andres/Downloads/cambio_pre_pos_v2tmk#16-03-2023 09-00#jheansosa@sqdm.com#cambio_pre_pos_v2tmk.csv")
