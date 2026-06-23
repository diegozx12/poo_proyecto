/**
 * Excepción personalizada para controlar percances operativos como la búsqueda 
 * de productos inexistentes dentro del catálogo.
 */
public class CatalogoException extends Exception {
    public CatalogoException(String mensaje) {
        super(mensaje);
    }
}