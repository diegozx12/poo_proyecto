import java.util.Objects;

/**
 * Representa al usuario con privilegios operativos elevados de la tienda virtual.
 */
public class Administrador extends Usuario {
    private String numeroEmpleado;

    public Administrador(String id, String nombre, String contrasena, String numeroEmpleado) {
        super(id, nombre, contrasena);
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Administrador that = (Administrador) o;
        return Objects.equals(numeroEmpleado, that.numeroEmpleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroEmpleado);
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "id='" + getId() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", numeroEmpleado='" + numeroEmpleado + '\'' +
                '}';
    }
}