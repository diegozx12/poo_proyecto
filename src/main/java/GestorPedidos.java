import java.util.ArrayList;

/**
 * Controlador central del ciclo de vida e historial de las órdenes del sistema.
 */
public class GestorPedidos {
    private ArrayList<Pedido> historialPedidos;

    public GestorPedidos() {
        this.historialPedidos = new ArrayList<>();
    }

    /**
     * Guarda un pedido en el historial y descuenta el stock de los productos de forma real.
     */
    public void registrarNuevoPedido(Pedido nuevoPedido) {
        if (nuevoPedido != null) {
            this.historialPedidos.add(nuevoPedido);

            // Deducción real del inventario al confirmar el pedido
            for (ItemCarrito item : nuevoPedido.getItemsComprados()) {
                Producto prod = item.getProductoSeleccionado();
                int nuevaCantidad = prod.getStock() - item.getCantidadComprada();
                prod.setStock(nuevaCantidad);
            }
            System.out.println("Pedido registrado exitosamente e inventario actualizado.");
        }
    }

    public Pedido buscarPedidoPorId(int idPedido) {
        for (Pedido pedido : this.historialPedidos) {
            if (pedido.getIdPedido() == idPedido) {
                return pedido;
            }
        }
        return null;
    }

    public void listarPedidosPorEstado(String estado) {
        boolean encontrado = false;
        System.out.println("\n--- LISTADO DE PEDIDOS (Estado: " + estado + ") ---");

        for (Pedido pedido : this.historialPedidos) {
            if (pedido.getEstadoActual().equalsIgnoreCase(estado)) {
                System.out.println("ID: " + pedido.getIdPedido() +
                        " | Cliente: " + pedido.getClienteComprador().getNombre() +
                        " | Total: $" + pedido.getTotalFinalCobrado());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron pedidos en este estado.");
        }
    }

    public ArrayList<Pedido> getHistorialPedidos() {
        return historialPedidos;
    }
}