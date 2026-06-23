/**
 * Representa una línea individual dentro del carrito de compras.
 * Congela el precio del producto al momento de su adición.
 */
public class ItemCarrito {
    private Producto productoSeleccionado;
    private int cantidadComprada;
    private double precioUnitarioCongelado;
    private double subtotalItem;

    /**
     * Constructor que asigna el producto, la cantidad y congela el precio al instante.
     */
    public ItemCarrito(Producto productoSeleccionado, int cantidadComprada) {
        this.productoSeleccionado = productoSeleccionado;
        this.cantidadComprada = cantidadComprada;

        // Congelamiento del precio unitario al momento de agregar al carrito
        if (this.productoSeleccionado != null) {
            this.precioUnitarioCongelado = this.productoSeleccionado.getPrecioBase();
        } else {
            this.precioUnitarioCongelado = 0.0;
        }

        // Se calcula el subtotal de esta línea inmediatamente
        this.calcularSubtotalItem();
    }

    /**
     * Calcula el subtotal multiplicando la cantidad por el precio congelado.
     */
    public void calcularSubtotalItem() {
        this.subtotalItem = this.cantidadComprada * this.precioUnitarioCongelado;
    }

    // Getters
    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public int getCantidadComprada() {
        return cantidadComprada;
    }

    public double getPrecioUnitarioCongelado() {
        return precioUnitarioCongelado;
    }

    public double getSubtotalItem() {
        return subtotalItem;
    }
}