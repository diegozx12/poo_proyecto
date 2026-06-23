import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase encargada de la interfaz de usuario por consola para el
 * Módulo de Administración y Reportes de Cierre.
 */
public class MenuGestorReportes {
    private final GestorReportes gestorReportes;
    private final ArrayList<Pedido> historialPedidos;
    private final ArrayList<Producto> catalogoProductos;
    private final Scanner teclado;

    public MenuGestorReportes(GestorReportes gestorReportes, ArrayList<Pedido> historialPedidos, ArrayList<Producto> catalogoProductos) {
        this.gestorReportes = gestorReportes;
        this.historialPedidos = historialPedidos;
        this.catalogoProductos = catalogoProductos;
        this.teclado = new Scanner(System.in);
    }

    /**
     * Inicia el ciclo principal del menú del módulo.
     */
    public void iniciarMenu() {
        boolean salir = false;
        while (!salir) {
            mostrarOpciones();
            int opcion = leerOpcion();
            salir = procesarOpcion(opcion);
        }
    }

    private void mostrarOpciones() {
        System.out.println("\n--- MÓDULO DE ADMINISTRACIÓN Y REPORTES DE CIERRE ---");
        System.out.println("1. Generar Reporte de Operaciones Completo (Cierre)");
        System.out.println("2. Verificar Alertas de Inventario Crítico (Stock en Cero)");
        System.out.println("3. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
    }

    private int leerOpcion() {
        try {
            return Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                ejecutarGeneracionReporteCierre();
                break;
            case 2:
                ejecutarVerificacionAlertasStock();
                break;
            case 3:
                System.out.println("Retornando al menú principal...");
                return true; // Termina el ciclo interno devolviendo el control al Main sin cerrar el sistema
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
        return false;
    }

    /**
     * Método auxiliar para la Opción 1: Generación y despliegue del reporte integral de cierre.
     */
    private void ejecutarGeneracionReporteCierre() {
        try {
            // El menú delega la lógica de negocio al controlador responsable
            ReporteOperaciones reporte = gestorReportes.generarReporteCierre(historialPedidos, catalogoProductos);
            // Llama al método simple de presentación aprobado en el diseño
            reporte.mostrarReporteCompleto();
        } catch (StockCriticoException e) {
            // Captura centralizada del error arrojado por las reglas de negocio del módulo
            System.out.println("\n[ERROR DE CONTROL] " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para la Opción 2: Evaluación directa y filtrado de alertas de inventario.
     */
    private void ejecutarVerificacionAlertasStock() {
        System.out.println("\n--- COMPROBACIÓN DIRECTA DE ALERTAS DE STOCK ---");
        ArrayList<String> agotados = gestorReportes.verificarAlertasStock(catalogoProductos);

        if (agotados.isEmpty()) {
            System.out.println("Éxito: Todos los productos cuentan con existencias disponibles en el catálogo.");
        } else {
            System.out.println("Alerta: Se detectaron " + agotados.size() + " artículo(s) con inventario en cero:");
            for (String nombre : agotados) {
                System.out.println(" - " + nombre);
            }
        }
    }
}