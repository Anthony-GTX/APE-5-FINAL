import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio07_CompetenciaVideojuegos {

    public static void main(String[] args) {
        File archivo = new File("jugadores.txt");
        if (!archivo.exists()) {
            System.out.println("El archivo jugadores.txt no existe.");
            return;
        }

        List<String> nombres = new ArrayList<>();
        List<Double> puntajes = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");
                // Valida que la linea tenga nombre y puntuacion
                if (partes.length != 2) {
                    System.out.println("Formato inválido: " + linea);
                    continue;
                }
                try {
                    // Convierte la puntuacion de texto a numero
                    double puntaje = Double.parseDouble(partes[1].trim());
                    nombres.add(partes[0].trim());
                    puntajes.add(puntaje);
                } catch (NumberFormatException e) {
                    System.out.println("Puntuación inválida: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
            return;
        }

        if (puntajes.isEmpty()) {
            System.out.println("No hay registros válidos.");
            return;
        }

        double suma = 0;
        int indiceMayor = 0;
        for (int i = 0; i < puntajes.size(); i++) {
            suma += puntajes.get(i);
            if (puntajes.get(i) > puntajes.get(indiceMayor)) {
                indiceMayor = i;
            }
        }

        System.out.println("Promedio: " + suma / puntajes.size());
        System.out.println("Mayor puntaje: " + nombres.get(indiceMayor) + " (" + puntajes.get(indiceMayor) + ")");
        System.out.println("Jugadores con puntuación superior a 70:");
        for (int i = 0; i < puntajes.size(); i++) {
            if (puntajes.get(i) > 70) {
                System.out.println("- " + nombres.get(i) + ": " + puntajes.get(i));
            }
        }
    }
}
