import java.util.Scanner;

/**
 * Gestión del submenú de Checkout y Pedidos con protección de casillas en blanco.
 */
public class MenuGestionPedidos {
    private Scanner scanner;
    private GestorPedidos gestorPedidos;
    private CarritoCompras carritoActual;
    private int contadorIdPedido;

    public MenuGestionPedidos(Scanner scanner, GestorPedidos gestorPedidos, CarritoCompras carritoActual) {
        this.scanner = scanner;
        this.gestorPedidos = gestorPedidos;
        this.carritoActual = carritoActual;
        this.contadorIdPedido = 1;
    }

    public void iniciarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n--- MÓDULO DE GESTIÓN DE PEDIDOS ---");
            System.out.println("1. Confirmar compra (Checkout del carrito)");
            System.out.println("2. Buscar pedido por ID");
            System.out.println("3. Listar pedidos por estado");
            System.out.println("4. Actualizar estado de un pedido");
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
            case 1: confirmarCompraActual(); break;
            case 2: buscarPedido(); break;
            case 3: listarPedidos(); break;
            case 4: actualizarEstadoPedido(); break;
            case 5: System.out.println("Regresando al menú principal."); break;
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

    private void confirmarCompraActual() {
        if (carritoActual.getListaItems().isEmpty()) {
            System.out.println("No se puede confirmar la compra: El carrito está vacío.");
            return;
        }

        System.out.println("\n--- CHECKOUT DE COMPRA ---");
        String metodoPago = leerCampoObligatorio("Ingrese el método de pago (Ej. Efectivo, Tarjeta): ");

        Pedido nuevoPedido = new Pedido(
                contadorIdPedido,
                carritoActual.getClienteAsignado(),
                carritoActual.getListaItems(),
                metodoPago
        );

        gestorPedidos.registrarNuevoPedido(nuevoPedido);
        carritoActual.vaciarCarrito();
        contadorIdPedido++;

        System.out.println("\n¡Compra confirmada exitosamente!");
        nuevoPedido.mostrarTicketFacturacion();
    }

    private void buscarPedido() {
        try {
            int id = Integer.parseInt(leerCampoObligatorio("\nIngrese el ID del pedido a buscar: "));
            Pedido pedidoEncontrado = gestorPedidos.buscarPedidoPorId(id);

            if (pedidoEncontrado != null) {
                pedidoEncontrado.mostrarTicketFacturacion();
            } else {
                System.out.println("No se encontró ningún pedido con el ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un ID numérico.");
        }
    }

    private void listarPedidos() {
        String estado = leerCampoObligatorio("\nIngrese el estado a filtrar (Ej. Pendiente, En Fabricacion, Enviado): ");
        gestorPedidos.listarPedidosPorEstado(estado);
    }

    private void actualizarEstadoPedido() {
        try {
            int id = Integer.parseInt(leerCampoObligatorio("\nIngrese el ID del pedido a actualizar: "));
            Pedido pedido = gestorPedidos.buscarPedidoPorId(id);

            if (pedido != null) {
                System.out.println("El estado actual es: " + pedido.getEstadoActual());
                String nuevoEstado = leerCampoObligatorio("Ingrese el nuevo estado: ");

                pedido.actualizarEstado(nuevoEstado);
                System.out.println("Estado actualizado correctamente.");
            } else {
                System.out.println("Pedido no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un ID numérico.");
        }
    }
}