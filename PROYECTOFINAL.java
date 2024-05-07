package Proyecto;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Definiendo el método de notificación
interface Notificador {
    void notificar(String producto);
}

class Notifica implements Notificador {
    public void notificar(String producto) {
        System.out.println("¡¡Alerta!! El nivel de stock del producto " + producto + " ha caído por debajo de 5 unidades. Tiene que realizar compra con los proveedores");
    }
}

public class PROYECTOFINAL {

    // Mapa para almacenar los productos y su stock
    private static Map<String, Integer> inventario = new HashMap<>();
    private static Notificador notificador;

    // Método para inicializar el inventario
    private static void inicializarInventario() {

        inventario.put("Leche", 50);
        inventario.put("Lejia", 30);
        inventario.put("Detergente", 50);
        inventario.put("Atun", 40);
        inventario.put("Fideos", 20);
        inventario.put("Ajinomen", 10);
        inventario.put("Poet", 15);
        inventario.put("Jabon", 25);
        inventario.put("Escoba", 17);
        inventario.put("Recogedor", 6);
        inventario.put("Cereal", 12);
        inventario.put("Cafe", 10);
        inventario.put("yogurt", 30);
    }

    // Método para mostrar el inventario actual
    private static void mostrarInventario() {
        System.out.println("Inventario Actual:");
        System.out.println("------------------");
        for (Map.Entry<String, Integer> entry : inventario.entrySet()) {
            System.out.println(entry.getKey() + " - Stock: " + entry.getValue());
        }
        System.out.println("------------------");
    }

    // Método para realizar una compra
    private static void comprarProducto(String producto, int cantidad) {
        if (inventario.containsKey(producto)) {
            int stockActual = inventario.get(producto);
            if (stockActual >= cantidad) {
                inventario.put(producto, stockActual - cantidad);
                System.out.println("Compra realizada con éxito. Stock actualizado.");

                // Verificador del stock
                if (stockActual - cantidad < 5) {
                    notificador.notificar(producto);
                }
            } else {
                System.out.println("No hay suficiente stock disponible para realizar la compra.");
            }
        } else {
            System.out.println("El producto especificado no existe en el inventario.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inicializarInventario();
        // Inicializar el notificador
        notificador = new Notifica();

        // Home de opciones
        int opcion;
        do {
            System.out.println("Bienvenido al Sistema de Gestión de Inventario");
            System.out.println("1. Mostrar Inventario");
            System.out.println("2. Comprar Producto");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mostrarInventario();
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.print("Ingrese el nombre del producto: ");
                    String producto = scanner.nextLine();
                    System.out.print("Ingrese la cantidad a comprar: ");
                    int cantidad = scanner.nextInt();
                    comprarProducto(producto, cantidad);
                    break;
                case 3:
                    System.out.println("Gracias por utilizar el Sistema de Gestión de Inventario. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 3);

        scanner.close();
    }
}
