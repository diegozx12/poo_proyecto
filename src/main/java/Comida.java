/**
 * Subclase que representa productos alimenticios.
 */
public class Comida extends Producto {
    private String fechaCaducidad;

    public Comida(String id, String nombre, double precioBase, int stock, String fechaCaducidad) {
        super(id, nombre, precioBase, stock); // Pasa el stock al constructor padre
        this.fechaCaducidad = fechaCaducidad;
    }

    @Override
    public double obtenerPrecioFinal() {
        return getPrecioBase();
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    @Override
    public String toString() {
        String estadoStock = (getStock() == 0) ? "AGOTADO" : String.valueOf(getStock());
        return String.format("ID: %s | %s (Comida) | Vence: %s | Stock: %s | Precio: $%.2f",
                getId(), getNombre(), fechaCaducidad, estadoStock, obtenerPrecioFinal());
    }
}