# Proyecto WEB — Panel de Gestión

## Características Principales
- El proyecto es capaz de recibir **actualizaciones en tiempo real** sobre el estado de cada trabajo.
- El equipo administrador puede **crear, actualizar, modificar o eliminar** asignaciones y trabajos.
- El equipo puede **registrar nuevos empleados**.
- El equipo puede **asignar trabajos a distintos equipos**, así como **mover miembros entre equipos**.
- Se pueden **filtrar trabajos** por estado, equipo asignado y empresa.
- Usa **SSE** para escuchar actualizaciones de progreso en tiempo real.

---

## Flujo de trabajo

### Conexión y Reconexión

1. **HTTP REQUEST GET**  
   - Al abrir el panel web, se envía un `GET` al servidor para obtener **todos los datos actuales**: trabajos, asignaciones, empleados y equipos.

2. **Conexión SSE**  
   - El panel mantiene una conexión **SSE** para recibir **actualizaciones en tiempo real** (cambios de estado, avances de progreso).

---

### Operaciones principales

1. **HTTP REQUEST POST**
   - Crear un **nuevo trabajo**: el administrador captura los datos → `POST /trabajos`.
   - Registrar un **nuevo empleado**: el administrador captura los datos → `POST /empleados`.

2. **HTTP REQUEST PATCH**
   - Modificar un trabajo: `PATCH /trabajos/{id}`.
   - Modificar asignaciones de empleados o moverlos de equipo: `PATCH /empleados/{id}/asignacion`.

3. **HTTP REQUEST DELETE**
   - Eliminar un trabajo existente: `DELETE /trabajos/{id}`.
   - Eliminar un empleado: `DELETE /empleados/{id}`.

---

### Filtros y búsqueda
- Se pueden aplicar **filtros por estado, equipo o empresa** mediante query params en `GET`.
  - Ejemplo: `/trabajos?estado=pendiente&equipo=5`

---

## Seguridad
- Todos los endpoints deben protegerse con **roles y permisos**.
- Autenticación con tokens seguros (JWT o sesión).
- SSE restringido a usuarios autorizados.

---

## Tiempo real
- Cualquier **cambio** (POST, PATCH, DELETE) debe notificar por **SSE** a **todos los paneles web conectados**, garantizando que la vista siempre está sincronizada.


