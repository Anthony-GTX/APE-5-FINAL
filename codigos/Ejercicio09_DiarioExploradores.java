import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio09_DiarioExploradores {

    private static final String ARCHIVO = "descubrimientos.txt";

    // Valida que el archivo exista y tenga informacion antes de procesarlo
    public static boolean archivoValido() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            System.out.println("El archivo descubrimientos.txt no existe.");
            return false;
        }
        if (archivo.length() == 0) {
            System.out.println("El archivo descubrimientos.txt está vacío.");
            return false;
        }
        return true;
    }

    // Agrega el descubrimiento al final del archivo
    public static void escribirDescubrimiento(String descubrimiento) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            escritor.write(descubrimiento);
            escritor.newLine();
            System.out.println("Descubrimiento registrado.");
        } catch (IOException e) {
            System.out.println("Error de escritura: " + e.getMessage());
        }
    }

    public static void leerDescubrimientos() {
        if (!archivoValido()) {
            return;
        }
        int contador = 0;
        try (BufferedReader lector = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                contador++;
                System.out.println(contador + ". " + linea);
            }
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
            return;
        }
        System.out.println("Total de descubrimientos: " + contador);
    }

    public static void buscarDescubrimiento(String nombre) {
        if (!archivoValido()) {
            return;
        }
        boolean encontrado = false;
        try (BufferedReader lector = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (linea.equalsIgnoreCase(nombre.trim())) {
                    encontrado = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
            return;
        }
        System.out.println(encontrado ? "Descubrimiento encontrado." : "Descubrimiento no encontrado.");
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1. Escribir descubrimiento");
            System.out.println("2. Leer y contar descubrimientos");
            System.out.println("3. Buscar descubrimiento");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(entrada.nextLine().trim());
            switch (opcion) {
                case 1:
                    System.out.print("Descubrimiento: ");
                    escribirDescubrimiento(entrada.nextLine());
                    break;
                case 2:
                    leerDescubrimientos();
                    break;
                case 3:
                    System.out.print("Nombre a buscar: ");
                    buscarDescubrimiento(entrada.nextLine());
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
