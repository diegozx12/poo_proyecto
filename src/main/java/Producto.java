/**
 * Clase base abstracta unificada para todo el sistema.
 * Define la estructura común y el control de inventario para todos los productos.
 */
public abstract class Producto {
    private String id; // Este actúa como el idProducto de Diego
    private String nombre;
    private double precioBase;
    private int stock; // Añadido para dar soporte al módulo de administración y reportes

    public Producto(String id, String nombre, double precioBase, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.stock = stock;
    }

    /**
     * Método abstracto para calcular el costo del producto de forma polimórfica.
     */
    public abstract double obtenerPrecioFinal();

    // Encapsulamiento (Getters y Setters)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto otro = (Producto) obj;
        return id != null && id.equalsIgnoreCase(otro.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.toLowerCase().hashCode() : 0;
    }

    @Override
    public abstract String toString();
}