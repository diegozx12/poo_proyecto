import java.util.ArrayList;
import java.util.Scanner;

/**
 * Gestión del submenú del Carrito con protección frente a casillas en blanco.
 */
public class MenuVentasFacturacion {
    private Scanner scanner;
    private CarritoCompras carrito;
    private ArrayList<Producto> catalogo;

    public MenuVentasFacturacion(Scanner scanner, CarritoCompras carrito, ArrayList<Producto> catalogo) {
        this.scanner = scanner;
        this.carrito = carrito;
        this.catalogo = catalogo;
    }

    public void iniciarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n--- MÓDULO DE VENTAS Y FACTURACIÓN ---");
            System.out.println("1. Mostrar catálogo de productos");
            System.out.println("2. Agregar producto al carrito");
            System.out.println("3. Ver resumen del carrito temporal");
            System.out.println("4. Vaciar carrito");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                procesarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        } while (opcion != 5);
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1: mostrarCatalogo(); break;
            case 2: agregarProductoAlCarrito(); break;
            case 3: verResumenCarrito(); break;
            case 4: vaciarCarritoActual(); break;
            case 5: System.out.println("Regresando al menú principal..."); break;
            default: System.out.println("Opción no válida.");
        }
    }

    private String leerCampoObligatorio(String mensaje) {
        String entrada;
        do {
            System.out.print(mensaje);
            entrada = scanner.nextLine();
            if (entrada.trim().isEmpty()) {
                System.out.println("[ERROR] Este campo no puede quedar en blanco. Intente de nuevo.");
            }
        } while (entrada.trim().isEmpty());
        return entrada;
    }

    private void mostrarCatalogo() {
        System.out.println("\n--- CATÁLOGO DE PRODUCTOS DISPONIBLES ---");
        if (catalogo.isEmpty()) {
            System.out.println("El catálogo está vacío.");
            return;
        }
        for (Producto prod : catalogo) {
            System.out.println(prod.toString());
        }
    }

    private void agregarProductoAlCarrito() {
        String idProducto = leerCampoObligatorio("\nIngrese el ID alfanumérico del producto: ");
        Producto productoSeleccionado = null;

        for (Producto prod : catalogo) {
            if (prod.getId().equalsIgnoreCase(idProducto)) {
                productoSeleccionado = prod;
                break;
            }
        }

        if (productoSeleccionado == null) {
            System.out.println("Producto no encontrado en el catálogo.");
            return;
        }

        try {
            int cantidad = Integer.parseInt(leerCampoObligatorio("Ingrese la cantidad a comprar: "));
            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor a cero.");
                return;
            }
            carrito.agregarProducto(productoSeleccionado, cantidad);
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un valor numérico entero.");
        }
    }

    private void verResumenCarrito() {
        System.out.println("\n--- RESUMEN DEL CARRITO ---");
        if (carrito.getListaItems().isEmpty()) {
            System.out.println("El carrito está vacío.");
            return;
        }

        System.out.println("Cliente: " + carrito.getClienteAsignado().getNombre());
        for (ItemCarrito item : carrito.getListaItems()) {
            System.out.println("- " + item.getProductoSeleccionado().getNombre() +
                    " | Cantidad: " + item.getCantidadComprada() +
                    " | Precio Unitario: $" + item.getPrecioUnitarioCongelado() +
                    " | Subtotal: $" + item.getSubtotalItem());
        }
        System.out.println("---------------------------");
        System.out.println("Total Temporal: $" + carrito.getSubtotalTemporal());
    }

    private void vaciarCarritoActual() {
        carrito.vaciarCarrito();
        System.out.println("El carrito ha sido vaciado correctamente.");
    }
}