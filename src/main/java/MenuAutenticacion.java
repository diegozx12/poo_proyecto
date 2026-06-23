import java.util.Scanner;
import java.util.ArrayList;

/**
 * Gestiona la interfaz de consola, lecturas de teclado y llamadas 
 * a los servicios lógicos del módulo de Autenticación.
 */
public class MenuAutenticacion {
    private AutenticacionServicio autenticacionServicio;
    private ArrayList<Producto> listaProductosAux;
    private Scanner teclado;

    public MenuAutenticacion(AutenticacionServicio autenticacionServicio, ArrayList<Producto> listaProductosAux) {
        this.autenticacionServicio = autenticacionServicio;
        this.listaProductosAux = listaProductosAux;
        this.teclado = new Scanner(System.in);
    }

    /**
     * Ciclo principal que despliega el menú en consola.
     */
    public void iniciarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n--- MÓDULO DE AUTENTICACIÓN Y ACCESO ---");
            System.out.println("1. Precargar Datos Iniciales (Configuración)");
            System.out.println("2. Registrar una Nueva Cuenta (Cualquier correo/clave)");
            System.out.println("3. Iniciar Sesión");
            System.out.println("4. Ver Cuentas Registradas en Memoria");
            System.out.println("5. Cerrar Sesión Activa");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {
                    case 1:
                        ejecutarPrecargaDatos();
                        break;
                    case 2:
                        ejecutarRegistroCuenta();
                        break;
                    case 3:
                        ejecutarInicioSesion();
                        break;
                    case 4:
                        ejecutarVerCuentas();
                        break;
                    case 5:
                        ejecutarCerrarSesion();
                        break;
                    case 6:
                        System.out.println("Retornando el control al menú principal de la tienda...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número entero válido.");
                opcion = 0;
            }
        } while (opcion != 6); // Cambiado a 6 para acoplar la nueva opción
    }

    /**
     * Opción 1: Llama a la clase utilitaria para sembrar datos en las listas de la RAM.
     */
    private void ejecutarPrecargaDatos() {
        ConfiguracionSistema.precargarDatosIniciales(
                autenticacionServicio.getCuentasRegistradas(),
                listaProductosAux
        );
        System.out.println("¡Datos de prueba cargados con éxito en memoria RAM!");
    }

    /**
     * NUEVA Opción 2: Permite registrar de forma dinámica CUALQUIER usuario y clave por teclado.
     */
    private void ejecutarRegistroCuenta() {
        System.out.println("\n--- REGISTRO DE NUEVA CUENTA ---");
        System.out.print("Ingrese el nuevo Correo / ID: ");
        String nuevoId = teclado.nextLine();
        System.out.print("Ingrese la nueva Contraseña: ");
        String nuevaClave = teclado.nextLine();
        System.out.print("Ingrese el Rol (Administrador / Cliente): ");
        String nuevoRol = teclado.nextLine();

        // Creamos el objeto Cuenta con los datos que digitaste
        Cuenta nuevaCuenta = new Cuenta(nuevoId, nuevaClave, nuevoRol);

        // Lo guardamos en el ArrayList del servicio en memoria
        autenticacionServicio.registrarCuenta(nuevaCuenta);

        System.out.println("¡[ÉXITO] Cuenta '" + nuevoId + "' registrada exitosamente en memoria RAM!");
    }

    /**
     * Opción 3: Solicita credenciales y procesa el inicio de sesión capturando fallos de negocio.
     */
    private void ejecutarInicioSesion() {
        System.out.print("Ingrese ID / Correo: ");
        String id = teclado.nextLine();
        System.out.print("Ingrese Contraseña: ");
        String contrasena = teclado.nextLine();

        try {
            Cuenta cuentaLogueada = autenticacionServicio.iniciarSesion(id, contrasena);
            System.out.println("\n[ÉXITO] Sesión iniciada correctamente.");
            System.out.println(cuentaLogueada.toString());
        } catch (AutenticacionException e) {
            System.out.println("\n[ERROR] " + e.getMessage());
        }
    }

    /**
     * Opción 4: Imprime el listado actual de usuarios registrados en el sistema.
     */
    private void ejecutarVerCuentas() {
        ArrayList<Cuenta> cuentas = autenticacionServicio.getCuentasRegistradas();
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas cargadas en memoria. Registre una cuenta (Opción 2) o ejecute la precarga (Opción 1).");
            return;
        }
        System.out.println("\n--- Cuentas en el Sistema ---");
        for (Cuenta c : cuentas) {
            System.out.println(c.toString());
        }

        Cuenta activa = autenticacionServicio.getCuentaActiva();
        System.out.println("-----------------------------");
        System.out.println("Sesión activa actual: " + (activa != null ? activa.getId() : "Ninguna"));
    }

    /**
     * Opción 5: Remueve el estado de logueado en el servicio.
     */
    private void ejecutarCerrarSesion() {
        if (autenticacionServicio.getCuentaActiva() == null) {
            System.out.println("No hay ninguna sesión activa para cerrar.");
        } else {
            autenticacionServicio.cerrarSesion();
            System.out.println("Sesión finalizada. El estado actual de sesión ahora es nulo.");
        }
    }
}