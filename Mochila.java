public class Mochila  {

    // Definir los valores y pesos de los objetos
    private static int[] valores = {60, 100, 120};
    private static int[] pesos = {10, 20, 30};
    private static int capacidad = 50;

    // Variable para rastrear el valor máximo encontrado
    private static int valorMaximo = 0;

    public static void main(String[] args) {
        // Llamar a la función de backtracking con los parámetros iniciales
        mochilaBacktracking(0, 0, 0);
        System.out.println("El valor máximo que se puede obtener es: " + valorMaximo);
    }

    // Función recursiva que implementa el backtracking
    public static void mochilaBacktracking(int indice, int pesoActual, int valorActual) {
        // Caso base: Si hemos considerado todos los objetos o excedemos la capacidad
        if (indice == valores.length) {
            // Actualizar el valor máximo si encontramos una mejor solución
            if (valorActual > valorMaximo) {
                valorMaximo = valorActual;
            }
            return;
        }

        // Caso 1: No incluir el objeto actual en la mochila
        mochilaBacktracking(indice + 1, pesoActual, valorActual);

        // Caso 2: Incluir el objeto actual en la mochila si no excede la capacidad
        if (pesoActual + pesos[indice] <= capacidad) {
            mochilaBacktracking(indice + 1, pesoActual + pesos[indice], valorActual + valores[indice]);
        }
    }
}
