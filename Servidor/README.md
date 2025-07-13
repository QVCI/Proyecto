# Servidor de Órdenes y Notificaciones

## Descripción General

El servidor será el eje principal del sistema, al estar basado en una arquitectura cliente-servidor.
Se encargara de gestionar los datos y la comunicación en tiempo real entre ambos sistemas, además de generar las notificaciones a los dispositivos moviles mediante FCM.
---

## Funcionalidades Principales

- API REST para consultar y actualizar órdenes de trabajo.
- Conexión **Server-Sent Events (SSE)** para actualizaciones en tiempo real.
- Notificaciones push vía **FCM** para informar sobre nuevos trabajos.
- Soporte de reconexión automática para clientes Android y Web.
- Persistencia de datos históricos y activos.

---

## Flujo General

1. **Carga Inicial**
   - Los clientes envían un `GET` para obtener todas las órdenes activas y su historial.
   - Los datos permiten funcionamiento offline (Android usa SQLite).

2. **Actualizaciones en Tiempo Real**
   - Los clientes abren una conexión **SSE** (`/stream`) para recibir eventos:
     - Nuevas actualizaciones de técnicos.
     - Cambios de estado.
     - Finalización de órdenes.

3. **Envió de Actualizaciones**
   - Los técnicos reportan cambios mediante `POST` o `PUT`.
   - El servidor valida, actualiza y emite eventos SSE.
   - Para nuevas asignaciones, se envía una notificación **FCM**.

4. **Reconexión Automática**
   - Si se pierde la conexión, los clientes reintentan hasta confirmar la sincronización.
   - Los cambios pendientes se reenvían periódicamente.

---

## Endpoints 

| Método  | Endpoint                 | Descripción                                                  |
|---------|--------------------------|--------------------------------------------------------------|
| `GET`   | `/ordenes`               | Listar todas las órdenes activas.                            |
| `GET`   | `/ordenes/{id}`          | Detalles y actualizaciones de una orden específica.          |
| `GET`   | `/ordenes` + filtros     | Filtrar por cliente, estado, número de orden, etc.           |
| `GET`   | `/seguimiento/{codigo}`  | Consulta pública de estado para clientes externos.           |
| `GET`   | `/stream`                | Establecer conexión SSE para actualizaciones en tiempo real. |
| `PUT`   | `/ordenes/{id}`          | Actualizar estado o progreso de una orden.                   |
| `POST`  | `/notificaciones`        | Enviar notificación push vía FCM.                            |

---

## Seguridad

- **JWT** o sesiones para usuarios administrativos.
- Roles y permisos para proteger endpoints sensibles.
- `/seguimiento/{codigo}` es público para clientes externos.
> [!WARNING]  
> Uso de Hashes (hash de clave primaria + sal), para el código, así se evita IDOR.
- SSE restringido a usuarios autenticados.

---

## Notificaciones Push (FCM)

- El servidor usa **Firebase Cloud Messaging** para notificar:
  - Nuevas órdenes asignadas.
  - Cambios críticos que requieren despertar la app Android.
- Las notificaciones sincronizan datos automáticamente.

---

##  Persistencia de Datos

- Base de datos relacional o no relacional:
  - Órdenes activas y finalizadas.
  - Historial completo de actualizaciones.
  - Información de usuarios, técnicos y clientes.
- Logs de eventos SSE y envíos FCM.

