package edu.algorithms.competitive.string;

public class KnuthMorrisPratt {

    private static final int SIZE = 1000002;
    private static final int F[] = new int[SIZE];

    /**
     * Algoritmo KMP (Knuth-Morris-Pratt ) - Tabla de fallos
     *
     * @param str
     */
    private void table_kmp(char str[]) {
        int pos = 2, ok = 0;
        F[0] = -1;
        while (pos < (int) str.length) {
            if (str[pos - 1] == str[ok]) {	// coincidencia hallada
                ++ok;
                F[pos] = ok;
                ++pos;
            } else {	// fallo en las coincidencias consecutivas
                if (ok > 0) {
                    ok = F[ok];
                } else {	// no hay coincidencias
                    F[pos] = 0;
                    ++pos;
                }
            }
        }
    }

    /**
     * The algorithm's time complexity is O(n + m) if n and m are the lengths of
     * the pattern and the text. This is better than the trivial O(nm) solution.
     *
     * Implementacion contruida para encontrar un substring dentro de un string.
     *
     *
     * @param str_origin Cadena origen.
     * @param target Cadena origen al contrario.
     * @return
     */
    public String find_kmp(String str_origin, String target) {
        char str[] = str_origin.toCharArray();
        char rev[] = target.toCharArray();
        table_kmp(str);

        int i = 0, j = 0, subSize = -1;
        while (i < (int) str.length) {
            while (j >= 0 && str[j] != rev[i]) {
                j = F[j];
            }
            ++i;
            ++j;

            if (j > subSize) {
                subSize = j;
            }
        }
        String subString = str_origin.substring(0, subSize);
        return new StringBuilder(subString).reverse().toString();
    }
}
