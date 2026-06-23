import java.util.Objects;

/**
 * Representa de forma específica a los usuarios compradores del sistema,
 * soportando datos de envío y la condición de Cliente Frecuente/VIP.
 */
public class Cliente extends Usuario {
    private String direccionEnvio;
    private boolean esVip;

    public Cliente(String id, String nombre, String contrasena, String direccionEnvio, boolean esVip) {
        super(id, nombre, contrasena);
        this.direccionEnvio = direccionEnvio;
        this.esVip = esVip;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public boolean isEsVip() {
        return esVip;
    }

    public void setEsVip(boolean esVip) {
        this.esVip = esVip;
    }

    /**
     * Retorna si el cliente es elegible para el 10% de descuento automático.
     */
    public boolean esElegibleParaDescuento() {
        return this.esVip;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return esVip == cliente.esVip && Objects.equals(direccionEnvio, cliente.direccionEnvio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), direccionEnvio, esVip);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + getId() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", direccionEnvio='" + direccionEnvio + '\'' +
                ", esVip=" + esVip +
                '}';
    }
}