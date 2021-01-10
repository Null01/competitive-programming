package edu.algorithms.competitive.string;

public class StringMatchingAdHoc {

    /**
     * Halla la cantidad de veces que se repite una cadena @Ej: ababab = 3
     *
     * @param str cadena objetivo
     * @return cantidad de veces que se repite la cadena (debe ser periodica)
     */
    public int findPeriodOfRepeat(String str) {
        int i, index = 1;
        char array[] = str.toCharArray();
        for (i = 1; i < array.length; i++) {
            while (array[i] != array[i % index]) {
                index++;
            }
        }
        return (i % index == 0) ? (i / index) : 1;
    }

    public int findMatch(String subStr, String str) {
        int i, j;
        int plen = subStr.length(), tlen = str.length();
        for (i = 0; i <= (tlen - plen); i++) {
            j = 0;
            while ((j < plen) && (str.charAt(i + j) == subStr.charAt(j))) {
                j = j + 1;
            }
            if (j == plen) {
                return (i);
            }
        }
        return (-1);
    }
}
