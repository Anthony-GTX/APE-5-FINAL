import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio08_ControlTemperatura {

    private static final String ARCHIVO = "temperaturas.txt";

    public static void guardarTemperatura(String valor) {
        // Solo se guardan valores con formato numerico valido
        try {
            Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            System.out.println("Dato no numérico. No se guardó.");
            return;
        }
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            escritor.write(valor);
            escritor.newLine();
            System.out.println("Temperatura guardada.");
        } catch (IOException e) {
            System.out.println("Error de escritura: " + e.getMessage());
        }
    }

    public static List<Double> leerTemperaturas() {
        List<Double> temperaturas = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                try {
                    // Convierte el texto leido a valor numerico
                    temperaturas.add(Double.parseDouble(linea.trim()));
                } catch (NumberFormatException e) {
                    System.out.println("Dato no numérico omitido: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }
        return temperaturas;
    }

    public static void mostrarEstadisticas() {
        List<Double> temperaturas = leerTemperaturas();
        if (temperaturas.isEmpty()) {
            System.out.println("No hay temperaturas válidas.");
            return;
        }
        double suma = 0;
        double maxima = temperaturas.get(0);
        int superiores = 0;
        for (double t : temperaturas) {
            suma += t;
            if (t > maxima) {
                maxima = t;
            }
            if (t > 30) {
                superiores++;
            }
        }
        System.out.println("Promedio: " + suma / temperaturas.size());
        System.out.println("Temperatura más alta: " + maxima);
        System.out.println("Registros superiores a 30 grados: " + superiores);
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1. Guardar temperatura");
            System.out.println("2. Calcular estadísticas");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(entrada.nextLine().trim());
            switch (opcion) {
                case 1:
                    System.out.print("Temperatura: ");
                    guardarTemperatura(entrada.nextLine().trim());
                    break;
                case 2:
                    mostrarEstadisticas();
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
