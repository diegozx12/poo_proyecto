/**
 * Excepción personalizada para controlar los errores lógicos de autenticación
 * dentro del módulo, delegando su captura al bloque try/catch centralizado.
 */
public class AutenticacionException extends Exception {
    public AutenticacionException(String mensaje) {
        super(mensaje);
    }
}