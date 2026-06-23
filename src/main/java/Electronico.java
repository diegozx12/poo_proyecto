/**
 * Subclase que representa artículos electrónicos.
 */
public class Electronico extends Producto {
    private String marca;
    private int garantiaMeses;

    public Electronico(String id, String nombre, double precioBase, int stock, String marca, int garantiaMeses) {
        super(id, nombre, precioBase, stock); // Pasa el stock al constructor padre
        this.marca = marca;
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public double obtenerPrecioFinal() {
        return getPrecioBase();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public String toString() {
        String estadoStock = (getStock() == 0) ? "AGOTADO" : String.valueOf(getStock());
        return String.format("ID: %s | %s (Electrónico) | Marca: %s | Garantía: %d meses | Stock: %s | Precio: $%.2f",
                getId(), getNombre(), marca, garantiaMeses, estadoStock, obtenerPrecioFinal());
    }
}