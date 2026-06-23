import java.util.ArrayList;

/**
 * Gestor en memoria del catálogo que permite almacenar, listar y buscar 
 * de manera polimórfica las instancias de Electronico, Ropa y Comida.
 */
public class CatalogoServicio {
    private ArrayList<Producto> listaProductos;

    public CatalogoServicio() {
        this.listaProductos = new ArrayList<>();
    }

    /**
     * Agrega un nuevo artículo al catálogo en memoria verificando duplicados mediante equals().
     */
    public void registrarProducto(Producto producto) {
        if (producto != null && !listaProductos.contains(producto)) {
            listaProductos.add(producto);
        }
    }

    /**
     * Recorre la colección e imprime la presentación en consola de cada artículo
     * delegando la responsabilidad a sus respectivos toString() sobreescritos.
     */
    public void listarCatalogo() {
        for (Producto producto : listaProductos) {
            System.out.println(producto.toString());
        }
    }

    /**
     * Busca un producto por ID alfanumérico único.
     * Lanza una excepción personalizada de negocio si el artículo solicitado no existe.
     */
    public Producto buscarProductoPorId(String id) throws CatalogoException {
        for (Producto producto : listaProductos) {
            if (producto.getId().equalsIgnoreCase(id)) {
                return producto;
            }
        }
        throw new CatalogoException("Error de Catálogo: El producto con ID '" + id + "' no existe en el sistema.");
    }

    // Getter para comunicar las colecciones con otros módulos del sistema
    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }
}