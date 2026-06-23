/**
 * Interfaz que define el contrato obligatorio para la aplicación de 
 * descuentos comerciales sobre un monto base.
 */
public interface Descontable {
    double calcularDescuento(double monto);
}