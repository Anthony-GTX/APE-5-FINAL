import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio10_VentasCafeteria {

    private static final String ARCHIVO = "ventas.txt";

    public static void registrarVenta(String producto, String precio) {
        // Valida que el precio sea un numero antes de escribirlo
        try {
            Double.parseDouble(precio);
        } catch (NumberFormatException e) {
            System.out.println("Precio inválido. Venta no registrada.");
            return;
        }
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            escritor.write(producto + "," + precio);
            escritor.newLine();
            System.out.println("Venta registrada.");
        } catch (IOException e) {
            System.out.println("Error de escritura: " + e.getMessage());
        }
    }

    public static void procesarVentas(double umbral) {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            System.out.println("El archivo ventas.txt no existe.");
            return;
        }
        double total = 0;
        double mayor = -1;
        String productoMayor = "";
        StringBuilder filtrados = new StringBuilder();

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length != 2) {
                    System.out.println("Formato inválido: " + linea);
                    continue;
                }
                try {
                    String producto = partes[0].trim();
                    // Convierte el precio de texto a numero decimal
                    double precio = Double.parseDouble(partes[1].trim());
                    System.out.println(producto + " - $" + precio);
                    total += precio;
                    if (precio > mayor) {
                        mayor = precio;
                        productoMayor = producto;
                    }
                    // Filtra los productos que superan el valor indicado
                    if (precio > umbral) {
                        filtrados.append("- ").append(producto).append(": $").append(precio).append("\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Precio inválido: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
            return;
        }

        System.out.println("Total de ventas: $" + total);
        if (!productoMayor.isEmpty()) {
            System.out.println("Producto de mayor precio: " + productoMayor + " ($" + mayor + ")");
        }
        System.out.println("Productos con precio superior a $" + umbral + ":");
        System.out.print(filtrados);
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1. Registrar venta");
            System.out.println("2. Procesar ventas");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(entrada.nextLine().trim());
            switch (opcion) {
                case 1:
                    System.out.print("Producto: ");
                    String producto = entrada.nextLine().trim();
                    System.out.print("Precio: ");
                    registrarVenta(producto, entrada.nextLine().trim());
                    break;
                case 2:
                    System.out.print("Valor mínimo para filtrar: ");
                    procesarVentas(Double.parseDouble(entrada.nextLine().trim()));
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
