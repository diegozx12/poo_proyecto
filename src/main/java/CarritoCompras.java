import java.util.ArrayList;

/**
 * Encargado de la acumulación temporal de artículos por parte del cliente.
 */
public class CarritoCompras {
    private Cliente clienteAsignado;
    private ArrayList<ItemCarrito> listaItems;
    private double subtotalTemporal;

    public CarritoCompras(Cliente clienteAsignado) {
        this.clienteAsignado = clienteAsignado;
        this.listaItems = new ArrayList<>();
        this.subtotalTemporal = 0.0;
    }

    /**
     * Valida estrictamente que la cantidad solicitada no supere el inventario disponible.
     */
    public boolean validarStockDisponible(Producto producto, int cantidadSolicitada) {
        if (producto != null && cantidadSolicitada <= producto.getStock()) {
            return true;
        }
        return false;
    }

    /**
     * Intenta agregar un producto al carrito aplicando la validación de stock.
     */
    public void agregarProducto(Producto producto, int cantidad) {
        if (validarStockDisponible(producto, cantidad)) {
            ItemCarrito nuevoItem = new ItemCarrito(producto, cantidad);
            this.listaItems.add(nuevoItem);

            // Se actualiza el subtotal general del carrito
            this.calcularSubtotalTemporal();
            System.out.println("Éxito: Producto agregado al carrito.");
        } else {
            System.out.println("Bloqueo de seguridad: No hay stock suficiente para la cantidad solicitada.");
        }
    }

    /**
     * Recorre la lista de ítems para sumar el subtotal acumulado.
     */
    public void calcularSubtotalTemporal() {
        this.subtotalTemporal = 0.0;
        for (ItemCarrito item : this.listaItems) {
            this.subtotalTemporal += item.getSubtotalItem();
        }
    }

    /**
     * Limpia la lista de ítems temporales y encera los valores.
     */
    public void vaciarCarrito() {
        this.listaItems.clear();
        this.subtotalTemporal = 0.0;
    }

    // Getters y Setters
    public Cliente getClienteAsignado() {
        return clienteAsignado;
    }

    public void setClienteAsignado(Cliente clienteAsignado) {
        this.clienteAsignado = clienteAsignado;
    }

    public ArrayList<ItemCarrito> getListaItems() {
        return listaItems;
    }

    public double getSubtotalTemporal() {
        return subtotalTemporal;
    }
}