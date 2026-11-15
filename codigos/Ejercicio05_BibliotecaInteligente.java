import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio05_BibliotecaInteligente {

    private static final String ARCHIVO = "libros.txt";

    // FileWriter con true agrega al final sin borrar los registros anteriores
    public static void registrarLibro(String titulo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            escritor.write(titulo);
            escritor.newLine();
            System.out.println("Libro registrado.");
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }

    public static void listarLibros() {
        File archivo = new File(ARCHIVO);
        // Valida que el archivo exista antes de leerlo
        if (!archivo.exists()) {
            System.out.println("El archivo libros.txt no existe.");
            return;
        }
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println("- " + linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
    }

    public static void buscarLibro(String buscado) {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            System.out.println("El archivo libros.txt no existe.");
            return;
        }
        boolean encontrado = false;
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                // equalsIgnoreCase ignora mayusculas y minusculas
                if (linea.equalsIgnoreCase(buscado.trim())) {
                    encontrado = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
            return;
        }
        System.out.println(encontrado ? "El libro está registrado." : "El libro no se encuentra.");
    }

    public static int contarLibros() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return 0;
        }
        int contador = 0;
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            while (lector.readLine() != null) {
                contador++;
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
        return contador;
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1. Registrar libro");
            System.out.println("2. Listar libros");
            System.out.println("3. Buscar libro");
            System.out.println("4. Contar libros");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(entrada.nextLine().trim());
            switch (opcion) {
                case 1:
                    System.out.print("Título: ");
                    registrarLibro(entrada.nextLine());
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    System.out.print("Título a buscar: ");
                    buscarLibro(entrada.nextLine());
                    break;
                case 4:
                    System.out.println("Libros registrados: " + contarLibros());
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
