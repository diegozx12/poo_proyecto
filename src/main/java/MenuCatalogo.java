import java.util.Scanner;

/**
 * Interfaz de consola para el Módulo de Catálogo con validación estricta de vacíos.
 */
public class MenuCatalogo {
    private CatalogoServicio catalogoServicio;
    private Scanner teclado;

    public MenuCatalogo(CatalogoServicio catalogoServicio) {
        this.catalogoServicio = catalogoServicio;
        this.teclado = new Scanner(System.in);
    }

    public void iniciarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n--- MÓDULO DE CATÁLOGO E INVENTARIO ---");
            System.out.println("1. Registrar Producto Electrónico");
            System.out.println("2. Registrar Prenda de Ropa");
            System.out.println("3. Registrar Producto Alimenticio (Comida)");
            System.out.println("4. Listar Catálogo Completo");
            System.out.println("5. Buscar Producto por ID");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine());
                switch (opcion) {
                    case 1: ejecutarRegistroElectronico(); break;
                    case 2: ejecutarRegistroRopa(); break;
                    case 3: ejecutarRegistroComida(); break;
                    case 4: ejecutarListarCatalogo(); break;
                    case 5: ejecutarBuscarProducto(); break;
                    case 6: System.out.println("Retornando..."); break;
                    default: System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número entero válido.");
                opcion = 0;
            }
        } while (opcion != 6);
    }

    private String leerCampoObligatorio(String mensaje) {
        String entrada;
        do {
            System.out.print(mensaje);
            entrada = teclado.nextLine();
            if (entrada.trim().isEmpty()) {
                System.out.println("[ERROR] Este campo no puede quedar en blanco. Intente de nuevo.");
            }
        } while (entrada.trim().isEmpty());
        return entrada;
    }

    private void ejecutarRegistroElectronico() {
        System.out.println("\n--- REGISTRAR ELECTRÓNICO ---");
        String id = leerCampoObligatorio("Ingrese ID único: ");
        String nombre = leerCampoObligatorio("Ingrese Nombre: ");

        try {
            double precio = Double.parseDouble(leerCampoObligatorio("Ingrese Precio Base: $"));
            int stock = Integer.parseInt(leerCampoObligatorio("Ingrese Stock Inicial: "));
            String marca = leerCampoObligatorio("Ingrese Marca: ");
            int garantia = Integer.parseInt(leerCampoObligatorio("Ingrese Meses de Garantía: "));

            Electronico nuevo = new Electronico(id, nombre, precio, stock, marca, garantia);
            catalogoServicio.registrarProducto(nuevo);
            System.out.println("¡Registrado con éxito!");
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Tipo de dato numérico inválido. Registro cancelado.");
        }
    }

    private void ejecutarRegistroRopa() {
        System.out.println("\n--- REGISTRAR PRENDA DE ROPA ---");
        String id = leerCampoObligatorio("Ingrese ID único: ");
        String nombre = leerCampoObligatorio("Ingrese Nombre: ");

        try {
            double precio = Double.parseDouble(leerCampoObligatorio("Ingrese Precio Base: $"));
            int stock = Integer.parseInt(leerCampoObligatorio("Ingrese Stock Inicial: "));
            String talla = leerCampoObligatorio("Ingrese Talla: ");
            String material = leerCampoObligatorio("Ingrese Material: ");

            Ropa nueva = new Ropa(id, nombre, precio, stock, talla, material);
            catalogoServicio.registrarProducto(nueva);
            System.out.println("¡Registrada con éxito!");
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Tipo de dato numérico inválido. Registro cancelado.");
        }
    }

    private void ejecutarRegistroComida() {
        System.out.println("\n--- REGISTRAR COMIDA ---");
        String id = leerCampoObligatorio("Ingrese ID único: ");
        String nombre = leerCampoObligatorio("Ingrese Nombre: ");

        try {
            double precio = Double.parseDouble(leerCampoObligatorio("Ingrese Precio Base: $"));
            int stock = Integer.parseInt(leerCampoObligatorio("Ingrese Stock Inicial: "));
            String fecha = leerCampoObligatorio("Ingrese Fecha de Caducidad: ");

            Comida nueva = new Comida(id, nombre, precio, stock, fecha);
            catalogoServicio.registrarProducto(nueva);
            System.out.println("¡Registrada con éxito!");
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Tipo de dato numérico inválido. Registro cancelado.");
        }
    }

    private void ejecutarListarCatalogo() {
        System.out.println("\n--- INVENTARIO ACTUAL DEL CATÁLOGO ---");
        if (catalogoServicio.getListaProductos().isEmpty()) {
            System.out.println("El catálogo está vacío.");
            return;
        }
        catalogoServicio.listarCatalogo();
    }

    private void ejecutarBuscarProducto() {
        String id = leerCampoObligatorio("\nIngrese el ID del producto: ");
        try {
            Producto encontrado = catalogoServicio.buscarProductoPorId(id);
            System.out.println("\n" + encontrado.toString());
        } catch (Exception e) {
            System.out.println("\n[ERROR] " + e.getMessage());
        }
    }
}