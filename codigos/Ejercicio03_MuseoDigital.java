import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Ejercicio03_MuseoDigital {

    public static void main(String[] args) {
        String archivo = "obras.txt";
        // El mapa guarda cada categoria y cuantas obras tiene
        Map<String, Integer> categorias = new LinkedHashMap<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");
                // Cada registro debe tener nombre y categoria
                if (partes.length != 2) {
                    System.out.println("Registro con formato incorrecto: " + linea);
                    continue;
                }
                String obra = partes[0].trim();
                String categoria = partes[1].trim();
                System.out.println("Obra: " + obra + " | Categoría: " + categoria);
                categorias.put(categoria, categorias.getOrDefault(categoria, 0) + 1);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        System.out.println("Clasificación por categoría:");
        for (Map.Entry<String, Integer> entrada : categorias.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue());
        }
    }
}
