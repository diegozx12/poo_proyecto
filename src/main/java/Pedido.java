import java.util.ArrayList;

/**
 * Administra el carrito confirmado, procesa costos finales, envío y
 * ahora implementa Descontable para manejar las deducciones VIP.
 */
public class Pedido implements Descontable {
    private int idPedido;
    private Cliente clienteComprador;
    private ArrayList<ItemCarrito> itemsComprados;
    private double subtotalProductos;
    private double costoEnvio;
    private double descuentoAplicado;
    private double totalFinalCobrado;
    private String metodoPago;
    private String estadoActual;

    private static final double TARIFA_ENVIO_BASE = 5.00;

    public Pedido(int idPedido, Cliente clienteComprador, ArrayList<ItemCarrito> itemsComprados, String metodoPago) {
        this.idPedido = idPedido;
        this.clienteComprador = clienteComprador;
        this.itemsComprados = new ArrayList<>(itemsComprados);
        this.metodoPago = metodoPago;
        this.estadoActual = "Pendiente";

        this.subtotalProductos = 0.0;
        for (ItemCarrito item : this.itemsComprados) {
            this.subtotalProductos += item.getSubtotalItem();
        }

        this.procesarReglaEnvioGratis();

        // Se ejecuta el método de la interfaz pasando el subtotal
        this.descuentoAplicado = this.calcularDescuento(this.subtotalProductos);

        this.calcularTotalFinal();
    }

    /**
     * Implementación obligatoria del contrato de la interfaz Descontable.
     * Evalúa si el comprador es VIP para retornar el 10% de descuento.
     */
    @Override
    public double calcularDescuento(double monto) {
        if (this.clienteComprador != null && this.clienteComprador.isEsVip()) {
            return monto * 0.10; // Retorna el 10% del monto recibido
        }
        return 0.0;
    }

    public void procesarReglaEnvioGratis() {
        if (this.subtotalProductos > 50.0) {
            this.costoEnvio = 0.0;
        } else {
            this.costoEnvio = TARIFA_ENVIO_BASE;
        }
    }

    public void calcularTotalFinal() {
        this.totalFinalCobrado = this.subtotalProductos - this.descuentoAplicado + this.costoEnvio;
    }

    public void actualizarEstado(String nuevoEstado) {
        this.estadoActual = nuevoEstado;
    }

    public void mostrarTicketFacturacion() {
        System.out.println("\n====================================");
        System.out.println("          TICKET DE COMPRA          ");
        System.out.println("====================================");
        System.out.println("Pedido #: " + this.idPedido + " | Estado: " + this.estadoActual);

        if (this.clienteComprador != null) {
            System.out.println("Cliente: " + this.clienteComprador.getNombre());
        }

        System.out.println("Método de Pago: " + this.metodoPago);
        System.out.println("------------------------------------");

        for (ItemCarrito item : this.itemsComprados) {
            System.out.println("- " + item.getProductoSeleccionado().getNombre() +
                    " (x" + item.getCantidadComprada() + ") -> $" + item.getSubtotalItem());
        }

        System.out.println("------------------------------------");
        System.out.println("Subtotal Productos: $" + this.subtotalProductos);
        System.out.println("Descuento VIP 10%    : $" + this.descuentoAplicado);
        System.out.println("Costo de Envío    : $" + this.costoEnvio);
        System.out.println("TOTAL A COBRAR    : $" + this.totalFinalCobrado);
        System.out.println("====================================\n");
    }

    // Getters
    public int getIdPedido() { return idPedido; }
    public Cliente getClienteComprador() { return clienteComprador; }
    public ArrayList<ItemCarrito> getItemsComprados() { return itemsComprados; }
    public String getEstadoActual() { return estadoActual; }
    public double getTotalFinalCobrado() { return totalFinalCobrado; }
}