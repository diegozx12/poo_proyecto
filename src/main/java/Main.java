import java.util.ArrayList;
import java.util.Scanner;

/**
 * Orquestador unificado global.
 * Respeta de forma estricta la sesión activa seleccionada por el usuario.
 */
public class Main {
    // Inicia con un Invitado de seguridad hasta que alguien inicie sesión realmente
    private static Usuario usuarioLogueado = new Cliente("0000000000", "Invitado Temporal", "123", "Sin Direccion", false);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // INSTANCIACIÓN ÚNICA DE GESTORES
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        CatalogoServicio catalogoServicio = new CatalogoServicio();
        GestorPedidos gestorPedidos = new GestorPedidos();
        GestorReportes gestorReportes = new GestorReportes();

        gestorUsuarios.precargarDatosEjemplo();
        CarritoCompras carritoCentral = new CarritoCompras((Cliente) usuarioLogueado);

        // PRECARGA DE PRODUCTOS
        ArrayList<Producto> catalogoSistema = catalogoServicio.getListaProductos();
        catalogoSistema.add(new Electronico("101", "Casa tecnologica para gato", 45.00, 10, "PetTech", 12));
        catalogoSistema.add(new Ropa("102", "Rascador de torre acolchado", 25.00, 5, "L", "Algodon"));
        catalogoSistema.add(new Comida("103", "Plato de alimento balanceado", 8.50, 20, "31/12/2026"));

        // MENÚS
        MenuGestorUsuarios menuUsuarios = new MenuGestorUsuarios(gestorUsuarios);
        MenuCatalogo menuCatalogo = new MenuCatalogo(catalogoServicio);
        MenuVentasFacturacion menuVentas = new MenuVentasFacturacion(scanner, carritoCentral, catalogoSistema);
        MenuGestionPedidos menuPedidos = new MenuGestionPedidos(scanner, gestorPedidos, carritoCentral);
        MenuGestorReportes menuReportes = new MenuGestorReportes(gestorReportes, gestorPedidos.getHistorialPedidos(), catalogoSistema);

        int opcionPrincipal = 0;
        do {
            System.out.println("\n==========================================================");
            System.out.println("      SISTEMA TIENDA VIRTUAL E-COMMERCE - PANEL GLOBAL ");
            System.out.println("============================================================");
            System.out.println(" SESIÓN ACTUAL: " + usuarioLogueado.getNombre() + " (ID: " + usuarioLogueado.getId() + ")");
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Módulo de Gestión de Usuarios y Clientes");
            System.out.println("2. Módulo de Catálogo e Inventario");
            System.out.println("3. Módulo de Ventas y Facturación (Carrito de Compras)");
            System.out.println("4. Módulo de Gestión de Pedidos (Checkout y Estados)");
            System.out.println("5. Módulo de Administración y Reportes de Cierre");
            System.out.println("6. Salir del Sistema");
            System.out.print("Seleccione una opción: ");

            try {
                opcionPrincipal = Integer.parseInt(scanner.nextLine());

                switch (opcionPrincipal) {
                    case 1:
                        // Al salir del módulo de usuarios, capturamos directamente el retorno del login
                        Usuario usuarioLogueadoEnModulo = menuUsuarios.iniciarMenu();

                        // Si el usuario efectivamente inició sesión (no es null), hacemos el cambio global
                        if (usuarioLogueadoEnModulo != null) {
                            usuarioLogueado = usuarioLogueadoEnModulo;

                            // Si es un cliente, vinculamos de inmediato su cuenta al carrito central
                            if (usuarioLogueado instanceof Cliente) {
                                carritoCentral.setClienteAsignado((Cliente) usuarioLogueado);
                            }
                        }
                        break;

                    case 2:
                        menuCatalogo.iniciarMenu();
                        break;

                    case 3:
                        // Ya no se sobreescribe la sesión aquí, se respeta el usuario activo
                        if (usuarioLogueado instanceof Cliente) {
                            menuVentas.iniciarMenu();
                        } else {
                            System.out.println("\n[BLOQUEO] Un Administrador no puede comprar. Inicie sesión como Cliente.");
                        }
                        break;

                    case 4:
                        // Se ingresa directamente respetando el cliente asignado en el caso 1
                        menuPedidos.iniciarMenu();
                        break;

                    case 5:
                        menuReportes.iniciarMenu();
                        break;

                    case 6:
                        System.out.println("\nCerrando el sistema... ¡Hasta pronto!");
                        break;

                    default:
                        System.out.println("\nOpción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nError: Ingrese un número válido.");
            }
        } while (opcionPrincipal != 6);

        scanner.close();
    }
}