/**
 * Representa la entidad base que almacena las credenciales lógicas de acceso
 * y el rol del usuario autenticado en la consola.
 */
public class Cuenta {
    private String id;
    private String contrasena;
    private String rol;

    public Cuenta(String id, String contrasena, String rol) {
        this.id = id;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    /**
     * Verifica la coincidencia exacta de los datos ingresados frente a los atributos de la instancia.
     */
    public boolean validarCredenciales(String id, String contrasena) {
        return this.id != null && this.id.equals(id) && this.contrasena != null && this.contrasena.equals(contrasena);
    }

    // Encapsulamiento (Getters y Setters)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Sobrescrito obligatoriamente para la comparación lógica de cuentas por su ID dentro de colecciones.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cuenta otra = (Cuenta) obj;
        return id != null && id.equalsIgnoreCase(otra.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.toLowerCase().hashCode() : 0;
    }

    /**
     * Sobrescrito para la visualización formateada de la cuenta ocultando la contraseña.
     */
    @Override
    public String toString() {
        return "ID Cuenta: " + id + " | Rol Asignado: " + rol;
    }
}