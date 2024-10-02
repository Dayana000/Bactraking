public class Viajero {

    // Número de ciudades (considerando que la numeración empieza en 1)
    private static final int NUM_CIUDADES = 4;

    // Matriz de distancias entre ciudades (indexada desde 1)
    private static int[][] distancias = {
            { 0, 10, 15, 20 },
            { 10, 0, 35, 25 },
            { 15, 35, 0, 30 },
            { 20, 25, 30, 0 }
    };

    // Para almacenar la ruta óptima y su distancia mínima
    private static int distanciaMinima = Integer.MAX_VALUE;
    private static int[] mejorRuta;

    public static void main(String[] args) {
        boolean[] visitado = new boolean[NUM_CIUDADES];
        int[] rutaActual = new int[NUM_CIUDADES + 1]; // Incluye la vuelta a la ciudad de origen

        // Marca la primera ciudad como visitada (ciudad 0 en el array, que corresponde a ciudad 1)
        visitado[0] = true;
        rutaActual[0] = 0;  // Empieza en la ciudad 0 (que representa la ciudad 1)

        // Llama al método de backtracking
        tspBacktracking(0, 1, 0, rutaActual, visitado);

        // Imprime la distancia mínima y la mejor ruta
        System.out.println("Distancia mínima: " + distanciaMinima);
        System.out.print("Mejor ruta: ");
        for (int i = 0; i < mejorRuta.length; i++) {
            System.out.print((mejorRuta[i] + 1) + " "); // Ajusta el índice para mostrar las ciudades del 1 al 4
        }
    }

    /**
     * Método recursivo que resuelve el problema del viajante usando backtracking.
     * @param ciudadActual La ciudad en la que se encuentra el viajero.
     * @param contador La cantidad de ciudades visitadas.
     * @param distanciaActual La distancia acumulada de la ruta actual.
     * @param rutaActual La ruta parcial que el viajero ha recorrido.
     * @param visitado Array que indica qué ciudades han sido visitadas.
     */
    public static void tspBacktracking(int ciudadActual, int contador, int distanciaActual, int[] rutaActual, boolean[] visitado) {
        if (contador == NUM_CIUDADES) {
            // Añade la distancia de regreso a la ciudad 0
            distanciaActual += distancias[ciudadActual][0];
            rutaActual[contador] = 0;

            // Actualiza la ruta y la distancia mínima si es mejor que la actual
            if (distanciaActual < distanciaMinima) {
                distanciaMinima = distanciaActual;
                mejorRuta = rutaActual.clone();
            }
            return;
        }

        // Recorre todas las ciudades posibles
        for (int ciudadSiguiente = 0; ciudadSiguiente < NUM_CIUDADES; ciudadSiguiente++) {
            // Si la ciudad no ha sido visitada
            if (!visitado[ciudadSiguiente]) {
                // Marca la ciudad como visitada
                visitado[ciudadSiguiente] = true;
                rutaActual[contador] = ciudadSiguiente;

                // Llama recursivamente para visitar la siguiente ciudad
                tspBacktracking(ciudadSiguiente, contador + 1,
                        distanciaActual + distancias[ciudadActual][ciudadSiguiente],
                        rutaActual, visitado);

                // Desmarca la ciudad para explorar otras posibilidades
                visitado[ciudadSiguiente] = false;
            }
        }
    }
}
