public class CampoGuerra {
    // Matriz que representa el campo de guerra
    private static String[][] campoGuerra = {
            {"CA", "CA", "CA", "CA", "CA", "CA", "CA", "CA", "E1"},
            {"CA", "E2", "CA", "CA", "CA", "CA", "CA", "CA", "A"},
            {"CA", "CA", "CA", "E1", "CA", "CA", "A", "E2", "CA"},
            {"CA", "A", "E1", "CA", "CA", "CA", "CA", "CA", "CA"},
            {"CA", "CA", "E2", "CA", "CA", "A", "E1", "CA", "CA"},
            {"CA", "CA", "CA", "A", "E1", "CA", "CA", "CA", "PC"}
    };

    // Variables para contar enemigos y aliados
    private static int enemigosTipo1 = 0;
    private static int enemigosTipo2 = 0;
    private static int aliados = 0;

    public static void main(String[] args) {
        // Iniciar el recorrido desde la posición (0, 7)
        if (caminar(0, 7)) {
            System.out.println("Soldado llegó al punto de control.");
        } else {
            System.out.println("No hay un camino válido hacia el punto de control.");
        }

        // Imprimir resultados
        System.out.println("Enemigos tipo 1 eliminados: " + enemigosTipo1);
        System.out.println("Enemigos tipo 2 eliminados: " + enemigosTipo2);
        System.out.println("Aliados encontrados: " + aliados);
    }

    // Función recursiva para que el soldado camine por el campo minado
    private static boolean caminar(int i, int j) {
        // Comprobamos que las coordenadas estén dentro de los límites de la matriz
        if (i < 0 || i >= campoGuerra.length || j < 0 || j >= campoGuerra[0].length) {
            return false;
        }

        // Si es el punto de control, retornamos true (camino válido)
        if (campoGuerra[i][j].equals("PC")) {
            imprimirCampo();  // Imprimir la matriz con el camino recorrido
            return true;
        }

        // Si el soldado encuentra un enemigo o aliado
        if (campoGuerra[i][j].equals("E1")) {
            enemigosTipo1++;
        } else if (campoGuerra[i][j].equals("E2")) {
            enemigosTipo2++;
        } else if (campoGuerra[i][j].equals("A")) {
            aliados++;
        }

        // Marcar el camino recorrido con "??"
        String temp = campoGuerra[i][j];  // Guardar el valor original
        campoGuerra[i][j] = "??";

        // Intentar moverse en las 4 direcciones (derecha, abajo, izquierda, arriba)
        if (caminar(i, j + 1) ||  // Derecha
                caminar(i + 1, j) ||  // Abajo
                caminar(i, j - 1) ||  // Izquierda
                caminar(i - 1, j)) {  // Arriba
            return true;
        }

        // Volver a la posición original si no encontró un camino
        campoGuerra[i][j] = temp;

        return false;
    }

    // Función para imprimir el campo con el camino marcado
    private static void imprimirCampo() {
        System.out.println("Camino trazado por el soldado:");
        for (int i = 0; i < campoGuerra.length; i++) {
            for (int j = 0; j < campoGuerra[i].length; j++) {
                System.out.print(campoGuerra[i][j] + " ");
            }
            System.out.println();
        }
    }
}
