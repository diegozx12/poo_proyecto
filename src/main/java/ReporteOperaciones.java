import java.util.ArrayList;
import java.util.Objects;

/**
 * Entidad encargada de consolidar, calcular y estructurar la información
 * analítica de la sesión comercial para ofrecer una visión clara del estado del negocio.
 */
public class ReporteOperaciones {
    private int cantidadTotalOrdenes;
    private double dineroTotalRecaudado;
    private ArrayList<String> productosSinStock;

    public ReporteOperaciones(int cantidadTotalOrdenes, double dineroTotalRecaudado, ArrayList<String> productosSinStock) {
        this.cantidadTotalOrdenes = cantidadTotalOrdenes;
        this.dineroTotalRecaudado = dineroTotalRecaudado;
        this.productosSinStock = productosSinStock;
    }

    public int getCantidadTotalOrdenes() {
        return cantidadTotalOrdenes;
    }

    public void setCantidadTotalOrdenes(int cantidadTotalOrdenes) {
        this.cantidadTotalOrdenes = cantidadTotalOrdenes;
    }

    public double getDineroTotalRecaudado() {
        return dineroTotalRecaudado;
    }

    public void setDineroTotalRecaudado(double dineroTotalRecaudado) {
        this.dineroTotalRecaudado = dineroTotalRecaudado;
    }

    public ArrayList<String> getProductosSinStock() {
        return productosSinStock;
    }

    public void setProductosSinStock(ArrayList<String> productosSinStock) {
        this.productosSinStock = productosSinStock;
    }

    /**
     * Método simple de presentación encargado de imprimir de forma formateada
     * en la consola el desglose de métricas recolectadas de la sesión.
     */
    public void mostrarReporteCompleto() {
        System.out.println("\n==================================================");
        System.out.println("        REPORTE DE OPERACIONES DE CIERRE          ");
        System.out.println("==================================================");
        System.out.println("Cantidad total de órdenes procesadas: " + cantidadTotalOrdenes);
        System.out.printf("Dinero total recaudado (Ventas Completadas): $%.2f\n", dineroTotalRecaudado);
        System.out.println("--------------------------------------------------");
        System.out.println("ALERTA DE INVENTARIO CRÍTICO:");
        if (productosSinStock.isEmpty()) {
            System.out.println("-> No se registran productos con stock en cero.");
        } else {
            System.out.println("-> ¡ATENCIÓN! Los siguientes productos están AGOTADOS:");
            for (String nombreProducto : productosSinStock) {
                System.out.println("   [X] " + nombreProducto);
            }
        }
        System.out.println("==================================================");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReporteOperaciones reporte = (ReporteOperaciones) o;
        return cantidadTotalOrdenes == reporte.cantidadTotalOrdenes &&
                Double.compare(reporte.dineroTotalRecaudado, dineroTotalRecaudado) == 0 &&
                Objects.equals(productosSinStock, reporte.productosSinStock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cantidadTotalOrdenes, dineroTotalRecaudado, productosSinStock);
    }

    @Override
    public String toString() {
        return "ReporteOperaciones{" +
                "cantidadTotalOrdenes=" + cantidadTotalOrdenes +
                ", dineroTotalRecaudado=" + dineroTotalRecaudado +
                ", productosSinStockCount=" + productosSinStock.size() +
                '}';
    }
}