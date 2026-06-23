import java.util.ArrayList;

/**
 * Controlador del módulo encargado de recorrer las listas en memoria
 * para procesar las métricas de cierre y evaluar las alertas de inventario.
 */
public class GestorReportes {
    public GestorReportes() {
        // Constructor vacío asignado por responsabilidad del diseño
    }

    /**
     * Recorre el historial de pedidos y el catálogo para generar un reporte consolidado.
     * Ubica la validación de negocio dentro de la clase responsable arrojando una excepción si corresponde.
     */
    public ReporteOperaciones generarReporteCierre(ArrayList<Pedido> historialPedidos, ArrayList<Producto> catalogoProductos) throws StockCriticoException {
        if (catalogoProductos == null) {
            throw new StockCriticoException("Error: No se puede generar el reporte debido a que el catálogo de productos no está inicializado.");
        }
        int totalOrdenes = historialPedidos != null ? historialPedidos.size() : 0;
        double totalRecaudado = 0.0;

        // Sumar el dinero acumulado únicamente de las ventas completadas
        if (historialPedidos != null) {
            for (Pedido pedido : historialPedidos) {
                // CORREGIDO: Se cambió getEstado() por getEstadoActual()
                if (pedido.getEstadoActual().equalsIgnoreCase("Enviado") || pedido.getEstadoActual().equalsIgnoreCase("Entregado")) {
                    // CORREGIDO: Se cambió getTotalFinal() por getTotalFinalCobrado()
                    totalRecaudado += pedido.getTotalFinalCobrado();
                }
            }
        }

        // Obtener la lista de alertas usando el método especializado interno
        ArrayList<String> nombresAgotados = verificarAlertasStock(catalogoProductos);
        return new ReporteOperaciones(totalOrdenes, totalRecaudado, nombresAgotados);
    }

    /**
     * Filtra y extrae de forma estricta los productos cuyo inventario sea exactamente igual a cero.
     */
    public ArrayList<String> verificarAlertasStock(ArrayList<Producto> catalogoProductos) {
        ArrayList<String> productosAgotados = new ArrayList<>();

        if (catalogoProductos != null) {
            for (Producto producto : catalogoProductos) {
                // Validación estricta de stock exactamente en cero
                if (producto.getStock() == 0) {
                    productosAgotados.add(producto.getNombre());
                }
            }
        }

        return productosAgotados;
    }
}