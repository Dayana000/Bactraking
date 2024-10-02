public class SopaLetras {

    // Tamaños de las posibles direcciones (arriba, abajo, izquierda, derecha, diagonales)
    static int[] filas = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] columnas = {-1, 0, 1, -1, 1, -1, 0, 1};

    // Método principal para buscar la palabra
    public static boolean buscarPalabra(char[][] sopa, String palabra, int fila, int columna, int index) {
        // Caso base: si encontramos toda la palabra
        if (index == palabra.length()) {
            return true;
        }

        // Si estamos fuera de los límites de la matriz o el carácter no coincide
        if (fila < 0 || fila >= sopa.length || columna < 0 || columna >= sopa[0].length || sopa[fila][columna] != palabra.charAt(index)) {
            return false;
        }

        // Guardar el carácter actual y marcar la posición como visitada (usamos un carácter temporal)
        char temp = sopa[fila][columna];
        sopa[fila][columna] = '#';  // Marcamos la celda como usada

        // Probar todas las direcciones posibles
        for (int i = 0; i < 8; i++) {
            int nuevaFila = fila + filas[i];
            int nuevaColumna = columna + columnas[i];
            if (buscarPalabra(sopa, palabra, nuevaFila, nuevaColumna, index + 1)) {
                return true;
            }
        }

        // Deshacer el cambio (backtracking)
        sopa[fila][columna] = temp;

        return false;
    }

    // Método para buscar una palabra en toda la matriz
    public static boolean buscarEnSopa(char[][] sopa, String palabra) {
        for (int fila = 0; fila < sopa.length; fila++) {
            for (int columna = 0; columna < sopa[0].length; columna++) {
                if (buscarPalabra(sopa, palabra, fila, columna, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Ejemplo de sopa de letras
        char[][] sopa = {
                {'S', 'O', 'P', 'A', 'L'},
                {'O', 'C', 'A', 'T', 'X'},
                {'P', 'A', 'L', 'A', 'B'},
                {'A', 'X', 'O', 'R', 'D'},
                {'S', 'T', 'E', 'X', 'Y'}
        };

        // Palabras a buscar
        String palabra1 = "SOPA";
        String palabra2 = "PALABRA";

        // Buscar ambas palabras
        boolean encontrada1 = buscarEnSopa(sopa, palabra1);
        boolean encontrada2 = buscarEnSopa(sopa, palabra2);

        // Mostrar los resultados
        System.out.println("¿La palabra \"" + palabra1 + "\" fue encontrada? " + encontrada1);
        System.out.println("¿La palabra \"" + palabra2 + "\" fue encontrada? " + encontrada2);
    }
}
