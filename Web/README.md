## Funcionalidades Principales de WebApp

### Para Administrativos (usuarios autenticados)
- Visualizar **todas las órdenes activas** (no finalizadas) en una interfaz estilo foro/muro.
- Consultar detalles de cada orden: cliente, equipo, ubicación, estado actual, última actualización.
- Filtrar/buscar órdenes por:

  - Número de orden
- Ver el **historial completo de actualizaciones** realizadas por los técnicos.
- Recibir **actualizaciones en tiempo real** mediante **Server-Sent Events (SSE)**.

### Para Clientes Externos (sin autenticación)
- Ingresar el **número de seguimiento** para consultar el estado de su servicio.
- Ver estado actual, última actualización y datos básicos del equipo.

---

## 🔄 Flujo de Datos

### Carga Inicial
- `GET /ordenes`: Carga las órdenes activas (no finalizadas) junto con su última actualización.

### Conexión en Tiempo Real
- `GET /stream`: Establece conexión SSE con el servidor.
- Escucha y actualiza la vista automáticamente cuando se reportan:
  - Nuevas actualizaciones del técnico
  - Cambios de estado
  - Finalización de una orden

---

## 🔧 Endpoints Principales

### Lectura
- `GET /ordenes`: Obtener todas las órdenes activas.
- `GET /ordenes/{id}`: Detalles y todas las actualizaciones de una orden.
- `GET /ordenes?cliente=...&estado=...`: Filtros combinados.
- `GET /seguimiento/{codigo}`: Consulta pública para clientes por número de orden.

### SSE (Server-Sent Events)
- `GET /stream`: Escucha de eventos en tiempo real.

---

## 🔐 Seguridad

- **JWT o sesiones seguras** para usuarios administrativos.
- Los endpoints de administración están protegidos por **roles y permisos**.
- La consulta por número de rastreo no requiere autenticación.
- SSE solo disponible para usuarios autorizados.

---

## 💡 Interfaz Web

- Diseño tipo **muro de tarjetas**, cada tarjeta representa una orden activa.
- La tarjeta muestra:
  - Cliente
  - Equipo
  - Estado actual
  - Última nota del técnico
- Opciones de búsqueda y filtrado visibles para mejorar la navegación.
- Para los clientes: una interfaz simple donde solo introducen su número de rastreo para ver el estado de su servicio.

