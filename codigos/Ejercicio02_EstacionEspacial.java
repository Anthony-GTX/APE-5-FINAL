import java.io.FileReader;
import java.io.IOException;

public class Ejercicio02_EstacionEspacial {

    public static void main(String[] args) {
        String archivo = "mensaje.txt";
        int total = 0;

        try (FileReader lector = new FileReader(archivo)) {
            int caracter;
            // read() devuelve el codigo del caracter o -1 al final del archivo
            while ((caracter = lector.read()) != -1) {
                System.out.print((char) caracter);
                total++;
            }
            System.out.println();
            System.out.println("Total de caracteres: " + total);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
