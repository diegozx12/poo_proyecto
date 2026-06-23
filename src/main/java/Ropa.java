/**
 * Subclase que representa prendas de vestir.
 */
public class Ropa extends Producto {
    private String talla;
    private String material;

    public Ropa(String id, String nombre, double precioBase, int stock, String talla, String material) {
        super(id, nombre, precioBase, stock); // Pasa el stock al constructor padre
        this.talla = talla;
        this.material = material;
    }

    @Override
    public double obtenerPrecioFinal() {
        return getPrecioBase();
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        String estadoStock = (getStock() == 0) ? "AGOTADO" : String.valueOf(getStock());
        return String.format("ID: %s | %s (Ropa) | Talla: %s | Material: %s | Stock: %s | Precio: $%.2f",
                getId(), getNombre(), talla, material, estadoStock, obtenerPrecioFinal());
    }
}