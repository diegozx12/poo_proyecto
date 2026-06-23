# Sistema de E-Commerce - Tienda Virtual  🛒

Este proyecto es una aplicación de consola desarrollada en Java bajo el paradigma de POO. Simula el flujo completo de una tienda virtual, automatizando desde el control de accesos y la gestión de inventario, hasta el procesamiento del carrito de compras, checkout transaccional y la auditoría analítica de cierre de caja.

Toda la persistencia de datos y el flujo operativo se manejan  utilizando colecciones dinámicas (`ArrayList`), garantizando la coherencia global entre módulos mediante un acoplamiento limpio guiado por un orquestador unificado.

---

## 🛠️ Arquitectura y Estructura de Módulos

El sistema está dividido en 5 módulos independientes interconectados de forma coherente a través de la clase principal (`Main`):

### 1. Módulo de Gestión de Usuarios y Clientes 👥
* **Responsabilidad:** Administra el registro, autenticación y tipificación de cuentas del sistema en memoria.
* **Componentes Clave:** `Usuario` (Clase abstracta), `Cliente` (Subclase con soporte VIP), `Administrador` (Subclase con privilegios), `ClienteInvitado` y `GestorUsuarios`.
* **Características:** Aplica validaciones estrictas de formato (como cédulas obligatorias de exactamente 10 dígitos numéricos) y maneja excepciones personalizadas (`AutenticacionException`) ante fallos de credenciales.

### 2. Módulo de Catálogo e Inventario 📦
* **Responsabilidad:** Aloja y cataloga de forma polimórfica los diferentes tipos de artículos comerciales.
* **Componentes Clave:** `Producto` (Clase base abstracta), `Electronico`, `Ropa`, `Comida` y `CatalogoServicio`.
* **Características:** Implementa un diseño polimórfico mediante el método abstracto `obtenerPrecioFinal()` y una búsqueda de artículos que dispara controles de negocio (`CatalogoException`) si el ID no existe.

### 3. Módulo de Ventas y Facturación (Carrito) 🛒
* **Responsabilidad:** Gestiona la acumulación temporal de artículos por parte de un cliente activo.
* **Componentes Clave:** `CarritoCompras` e `ItemCarrito`.
* **Características:** Congela el precio unitario del producto al instante de su adición para proteger la cotización y ejecuta bloqueos de seguridad automáticos si la cantidad solicitada supera el stock disponible en el catálogo.

### 4. Módulo de Gestión de Pedidos (Checkout) 📄
* **Responsabilidad:** Transforma el carrito confirmado en una orden de compra formal, procesando descuentos y deducciones logísticas.
* **Componentes Clave:** `Pedido` e Interfaz `Descontable`.
* **Características:** Implementa un contrato de interfaz para aplicar un 10% de descuento automático a Clientes VIP, evalúa reglas de negocio (como envío gratis en compras mayores a $50) y descuenta de forma real y definitiva el inventario físico al confirmar el checkout.

### 5. Módulo de Administración y Reportes de Cierre 📊
* **Responsabilidad:** Consolida la información analítica de la sesión comercial para auditoría.
* **Componentes Clave:** `GestorReportes`, `ReporteOperaciones` y `StockCriticoException`.
* **Características:** Recorre el historial en memoria para totalizar el dinero recaudado exclusivamente de ventas completadas (`Enviado`/`Entregado`) y dispara alertas críticas si detecta artículos con stock exactamente en cero.

---

## 🚀 Pilares de POO Aplicados

* **Abstracción:** Modelado de entidades del mundo real en clases puras bien delimitadas (`Pedido`, `Producto`, `Usuario`), separando la lógica de negocio de la lectura por teclado (`Menus`).
* **Encapsulamiento:** Protección de estados internos mediante atributos privados expuestos de forma controlada a través de métodos de acceso (Getters y Setters).
* **Herencia:** Reutilización estructurada de código a través de jerarquías claras: `Cliente` y `Administrador` extienden de `Usuario`; `Electronico`, `Ropa` y `Comida` extienden de `Producto`.
* **Polimorfismo:** Comportamientos dinámicos mediante la sobrescritura de métodos (`toString()`), la implementación de contratos (`Descontable`) y la ejecución en tiempo de ejecución del método abstracto `obtenerPrecioFinal()`.

---


