
import java.util.ArrayList;

/**
 * Centraliza la validación de credenciales y controla la referencia
 * del usuario que opera activamente la consola.
 */
public class AutenticacionServicio {
    private ArrayList<Cuenta> cuentasRegistradas;
    private Cuenta cuentaActiva;

    public AutenticacionServicio() {
        this.cuentasRegistradas = new ArrayList<>();
        this.cuentaActiva = null;
    }

    /**
     * Busca en la colección, aplica la validación de credenciales y gestiona la sesión activa.
     * Incorpora la validación de negocio lanzando la excepción correspondiente en caso de fallo.
     */
    public Cuenta iniciarSesion(String id, String contrasena) throws AutenticacionException {
        for (Cuenta cuenta : cuentasRegistradas) {
            if (cuenta.validarCredenciales(id, contrasena)) {
                this.cuentaActiva = cuenta;
                return cuenta;
            }
        }
        throw new AutenticacionException("Error de Autenticación: Credenciales inválidas o cuenta inexistente.");
    }

    /**
     * Remueve la referencia de cuentaActiva devolviéndola a un estado nulo para cerrar la sesión.
     */
    public void cerrarSesion() {
        this.cuentaActiva = null;
    }

    // Métodos de administración para el ArrayList de cuentas en memoria
    public void registrarCuenta(Cuenta cuenta) {
        if (cuenta != null && !cuentasRegistradas.contains(cuenta)) {
            cuentasRegistradas.add(cuenta);
        }
    }

    public ArrayList<Cuenta> getCuentasRegistradas() {
        return cuentasRegistradas;
    }

    public Cuenta getCuentaActiva() {
        return cuentaActiva;
    }
}