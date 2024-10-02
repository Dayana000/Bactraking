public class Laberinto {

private int contadorSalidas = 0;  // Variable para contar las salidas

    public char[][] laberinto={

            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', 'S', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', '#', '#', ' ', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', ' ', ' ', ' ', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', ' ', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', '#'},
            {'#', 'e', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
    };


    
    /* --------------------- PRUEBA DEL ALGORITMO --------------------- */
    public static void main(String[] args) {
        Laberinto m = new Laberinto(); 												// construimos un objeto de la clase Laberinto por defecto
        m.laberinto[1][1] = 'X'; 													// introducimos en este caso, la salida (X) en las coordenadas (1,1)
        m.resuelve(8, 1); 															// ahora, introducimos la entrada (S) en las coordenadas (8,1) y llamamos al algoritmo
        System.out.println(m.imprimirLaberinto()); 								    // imprimimos el laberinto ya resuelto (si tiene solución)
        System.out.println("Número de salidas encontradas: " + m.getContadorSalidas()); // Imprimimos cuántas salidas se encontraron
    }

    /* ----------------- IMPLEMENTACIÓN DEL ALGORITMO ----------------- */
    public void resuelve(int x, int y){ 				// permite introducir unas coordenadas (x, y)
        if (paso(x, y)) { 								// intentará resolver el laberinto en estas coordenadas
            laberinto[x][y] = 'S'; 						// introduce en las coordenadas (x, y) la entrada
        }
    }

    private boolean paso(int x, int y)
    {

        if (laberinto[x][y]=='X'){ // si hemos llegado a X quiere decir que hemos encontrado solución
            contadorSalidas++; // Incrementamos el contador de salidas
            return true; // luego, el algoritmo termina
        }

        if (laberinto[x][y]=='#'||laberinto[x][y]=='*' ||laberinto[x][y]=='f') { // si llegamos a una pared o al mismo punto,
            return false; // entonces el laberinto no puede resolverse y termina.
        }

        // Marcamos la posición como visitada (si es la primera, en las coordenadas x e y)
        laberinto[x][y]='*';

        boolean result;

        result=paso(x-1, y); // Intentamos ir hacia ARRIBA. Primera llamada recursiva
        if (result) return true; // Si el resultado es el final, entonces el algoritmo termina

        result=paso(x+1, y); // Intentamos ir hacia ABAJO. Segunda llamada recursiva
        if (result) return true;

        result=paso(x, y-1); // Intentamos ir hacia la IZQUIERDA. Tercera llamada recursiva
        if (result) return true;

        result=paso(x, y+1); // Intentamos ir hacia la DERECHA. Cuarta llamada recursiva
        if (result)return true;

        laberinto[x][y]='f'; // Volvemos atrás si la solución no se encuentra aquí
        return false;
    }

    private String imprimirLaberinto() { // imprimiremos nuestra solución.
        String salida = "";
        for (int x=0; x<laberinto.length; x++) {
            for (int y=0; y<laberinto[x].length; y++) {
                salida += laberinto[x][y] + " ";
            }
            salida += "\n";
        }
        return salida;
    }

    // Método para obtener el número de salidas encontradas
    public int getContadorSalidas() {
        return contadorSalidas;
    }
}
