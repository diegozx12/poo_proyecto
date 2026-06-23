/**
 * Excepción personalizada para notificar de manera estructurada eventos
 * críticos de stock e inventario dentro del flujo del panel de administración.
 */
public class StockCriticoException extends Exception {
    public StockCriticoException(String mensaje) {
        super(mensaje);
    }
}