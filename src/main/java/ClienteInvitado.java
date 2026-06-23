import java.util.Objects;

/**
 * Gestiona las compras transaccionales de usuarios no registrados.
 * No extiende de Usuario al no poseer credenciales ni requerir autenticación.
 */
public class ClienteInvitado {
    private String nombre;
    private String direccionEnvio;

    public ClienteInvitado(String nombre, String direccionEnvio) {
        this.nombre = nombre;
        this.direccionEnvio = direccionEnvio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteInvitado that = (ClienteInvitado) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(direccionEnvio, that.direccionEnvio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, direccionEnvio);
    }

    @Override
    public String toString() {
        return "ClienteInvitado{" +
                "nombre='" + nombre + '\'' +
                ", direccionEnvio='" + direccionEnvio + '\'' +
                '}';
    }
}