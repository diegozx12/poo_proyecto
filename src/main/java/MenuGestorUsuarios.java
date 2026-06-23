import java.util.Scanner;

/**
 * Clase encargada de la interfaz de usuario por consola para el
 * Módulo de Gestión de Usuarios y Clientes.
 * Mantiene de forma persistente y correcta la sesión del usuario activo.
 */
public class MenuGestorUsuarios {
    private final GestorUsuarios gestorUsuarios;
    private final Scanner teclado;
    private Usuario usuarioSesionActual = null;

    public MenuGestorUsuarios(GestorUsuarios gestorUsuarios) {
        this.gestorUsuarios = gestorUsuarios;
        this.teclado = new Scanner(System.in);
    }

    /**
     * Inicia el submenú de usuarios y mantiene la referencia del login exitoso.
     */
    public Usuario iniciarMenu() {
        boolean salir = false;
        while (!salir) {
            mostrarOpciones();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    ejecutarRegistroCliente();
                    break;
                case 2:
                    ejecutarInicioSesion();
                    break;
                case 3:
                    ejecutarListarUsuarios();
                    break;
                case 4:
                    ejecutarSimulacionInvitado();
                    break;
                case 5:
                    System.out.println("Retornando al menú principal...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        return this.usuarioSesionActual;
    }

    private void mostrarOpciones() {
        System.out.println("\n--- MÓDULO DE GESTIÓN DE USUARIOS Y CLIENTES ---");
        System.out.println("1. Registrar Nuevo Cliente (Crear Cuenta)");
        System.out.println("2. Iniciar Sesión (Clientes y Administradores)");
        System.out.println("3. Listar Usuarios en Memoria (Ver Base de Datos RAM)");
        System.out.println("4. Simular Compra de Cliente Invitado (Sin registro)");
        System.out.println("5. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
    }

    private int leerOpcion() {
        try {
            return Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String leerCedulaObligatoria(String mensaje) {
        String entrada;
        boolean valido = false;
        do {
            System.out.print(mensaje);
            entrada = teclado.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("[ERROR] El ID de cédula no puede quedar en blanco.");
            } else if (entrada.length() != 10) {
                System.out.println("[ERROR] La cédula debe tener exactamente 10 dígitos. Longitud actual: " + entrada.length());
            } else if (!entrada.matches("\\d+")) {
                System.out.println("[ERROR] La cédula solo debe contener números (0-9).");
            } else {
                valido = true;
            }
        } while (!valido);
        return entrada;
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

    private void ejecutarInicioSesion() {
        System.out.println("\n--- INICIO DE SESIÓN ---");
        String id = leerCedulaObligatoria("Ingrese Cédula de Usuario (10 dígitos): ");
        String contrasena = leerCampoObligatorio("Ingrese Contraseña: ");

        try {
            Usuario usuarioLogueado = gestorUsuarios.iniciarSesion(id, contrasena);
            System.out.println("¡Autenticación Exitosa!");
            System.out.println("Bienvenido: " + usuarioLogueado.getNombre());

            // Asignación directa en caliente a la variable de instancia
            this.usuarioSesionActual = usuarioLogueado;

            if (usuarioLogueado instanceof Cliente) {
                Cliente c = (Cliente) usuarioLogueado;
                System.out.println("Rol: Cliente " + (c.esElegibleParaDescuento() ? "VIP (Aplica 10% desc.)" : "Regular"));
                System.out.println("Dirección de envío: " + c.getDireccionEnvio());
            } else if (usuarioLogueado instanceof Administrador) {
                Administrador a = (Administrador) usuarioLogueado;
                System.out.println("Rol: Administrador");
                System.out.println("Número de Empleado: " + a.getNumeroEmpleado());
            }
        } catch (AutenticacionException e) {
            System.out.println(e.getMessage());
        }
    }

    private void ejecutarRegistroCliente() {
        System.out.println("\n--- REGISTRO DE NUEVO CLIENTE ---");
        String id = leerCedulaObligatoria("Ingrese Cédula para el ID único (10 dígitos): ");
        String nombre = leerCampoObligatorio("Ingrese Nombre Completo: ");
        String contrasena = leerCampoObligatorio("Ingrese Contraseña: ");
        String direccion = leerCampoObligatorio("Ingrese Dirección de Envío: ");
        String esVipInput = leerCampoObligatorio("¿Es cliente frecuente / VIP? (S/N): ");
        boolean esVip = esVipInput.equalsIgnoreCase("S");

        Cliente nuevoCliente = new Cliente(id, nombre, contrasena, direccion, esVip);
        try {
            gestorUsuarios.registrarNuevoCliente(nuevoCliente);
            System.out.println("¡Cliente registrado exitosamente en memoria! Ya puede iniciar sesión en la opción 2.");
        } catch (AutenticacionException e) {
            System.out.println(e.getMessage());
        }
    }

    private void ejecutarListarUsuarios() {
        System.out.println("\n--- USUARIOS REGISTRADOS ACTUALMENTE EN MEMORIA ---");
        if (gestorUsuarios.getListaUsuarios().isEmpty()) {
            System.out.println("No hay usuarios en la lista.");
        } else {
            for (Usuario u : gestorUsuarios.getListaUsuarios()) {
                System.out.println(u.toString());
            }
        }
    }

    private void ejecutarSimulacionInvitado() {
        System.out.println("\n--- DATOS DE COMPRA PARA CLIENTE INVITADO ---");
        String nombre = leerCampoObligatorio("Ingrese Nombre del Comprador: ");
        String direccion = leerCampoObligatorio("Ingrese Dirección de Envío: ");

        ClienteInvitado invitado = new ClienteInvitado(nombre, direccion);
        System.out.println("Objeto temporal creado con éxito para el flujo del Carrito/Pedido:");
        System.out.println(invitado.toString());
    }
}