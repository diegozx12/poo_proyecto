import java.util.ArrayList;

/**
 * Controlador centralizado en memoria del módulo de usuarios y clientes.
 * Se encarga de alojar colecciones, validar accesos y realizar la precarga.
 */
public class GestorUsuarios {
    private ArrayList<Usuario> listaUsuarios;

    public GestorUsuarios() {
        this.listaUsuarios = new ArrayList<>();
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * Se eliminaron los usuarios anteriores.
     * Ahora el sistema inicia completamente vacío en la memoria RAM.
     */
    public void precargarDatosEjemplo() {
        // Método vacío intencionalmente para no cargar usuarios antiguos.
    }

    /**
     * Busca y valida las credenciales de un usuario en el ArrayList.
     */
    public Usuario iniciarSesion(String idIngresado, String contrasenaIngresada) throws AutenticacionException {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId().equalsIgnoreCase(idIngresado)) {
                if (usuario.autenticar(contrasenaIngresada)) {
                    return usuario;
                } else {
                    throw new AutenticacionException("Error: Contraseña incorrecta para el usuario con ID: " + idIngresado);
                }
            }
        }
        throw new AutenticacionException("Error: No se encontró ningún usuario registrado con el ID: " + idIngresado);
    }

    /**
     * Añade un nuevo perfil de cliente a la estructura en memoria.
     */
    public void registrarNuevoCliente(Cliente nuevoCliente) throws AutenticacionException {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId().equalsIgnoreCase(nuevoCliente.getId())) {
                throw new AutenticacionException("Error: Ya existe un usuario registrado con el ID: " + nuevoCliente.getId());
            }
        }
        this.listaUsuarios.add(nuevoCliente);
    }
}