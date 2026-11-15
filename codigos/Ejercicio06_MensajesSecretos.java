import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio06_MensajesSecretos {

    private static final String ARCHIVO = "secreto.txt";

    // Modo append: el nuevo mensaje no sobrescribe a los anteriores
    public static void escribirMensaje(String mensaje) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            escritor.write(mensaje);
            escritor.newLine();
            System.out.println("Mensaje guardado.");
        } catch (IOException e) {
            System.out.println("Error de escritura: " + e.getMessage());
        }
    }

    // Muestra los mensajes y devuelve la cantidad de lineas leidas
    public static int mostrarMensajes() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            System.out.println("El archivo secreto.txt no existe.");
            return 0;
        }
        int lineas = 0;
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas++;
                System.out.println(lineas + ". " + linea);
            }
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }
        return lineas;
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1. Escribir mensaje");
            System.out.println("2. Leer mensajes y contar líneas");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(entrada.nextLine().trim());
            switch (opcion) {
                case 1:
                    System.out.print("Mensaje: ");
                    escribirMensaje(entrada.nextLine());
                    break;
                case 2:
                    int total = mostrarMensajes();
                    System.out.println("Líneas registradas: " + total);
                    break;
                case 0:
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
        entrada.close();
    }
}
