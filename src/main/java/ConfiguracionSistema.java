import java.util.ArrayList;

/**
 * Clase utilitaria encargada de la precarga obligatoria de datos simulados
 * en las colecciones compartidas de memoria RAM al arrancar el programa.
 */
public class ConfiguracionSistema {

    /**
     * Instancia directamente en código los objetos de prueba iniciales.
     * Al no usar packages, ya no requiere importar la clase Producto.
     */
    public static void precargarDatosIniciales(ArrayList<Cuenta> listaUsuarios, ArrayList<Producto> listaProductos) {
        // Precarga de cuenta de Administrador de ejemplo (PIN/Contraseña sugerida: 1234)
        listaUsuarios.add(new Cuenta("admin", "1234", "Administrador"));

        // Precarga de cuentas de Clientes de ejemplo
        listaUsuarios.add(new Cuenta("cliente@correo.com", "abc123", "Cliente"));
        listaUsuarios.add(new Cuenta("vip@correo.com", "vip123", "Cliente"));
    }
}