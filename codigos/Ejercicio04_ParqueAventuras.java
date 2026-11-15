import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio04_ParqueAventuras {

    public static void main(String[] args) {
        String archivo = "resultados.txt";
        int total = 0;
        int maximo = Integer.MIN_VALUE;
        String ganador = "";

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length != 2) {
                    System.out.println("Registro con formato incorrecto: " + linea);
                    continue;
                }
                try {
                    String nombre = partes[0].trim();
                    // Convierte el puntaje de texto a numero entero
                    int puntaje = Integer.parseInt(partes[1].trim());
                    System.out.println("Participante: " + nombre + " | Puntaje: " + puntaje);
                    total += puntaje;
                    // Guarda el puntaje mas alto encontrado hasta el momento
                    if (puntaje > maximo) {
                        maximo = puntaje;
                        ganador = nombre;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Puntaje inválido: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        System.out.println("Puntaje total: " + total);
        if (!ganador.isEmpty()) {
            System.out.println("Puntaje más alto: " + maximo + " (" + ganador + ")");
        }
    }
}
