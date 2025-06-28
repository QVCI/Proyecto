# Proyecto Android

## Características Principales
- El proyecto debe ser capaz de **recibir notificaciones** en cuanto se prenda el celular.
- Debe poder funcionar de forma **local**, mientras logra recuperar la conexión a internet.
- Usa **SSE** para actualizaciones de progreso y **FCM** solo para avisar de **nuevos trabajos**.

---

## Flujo de trabajo

### Conexión y Reconexión

1. **HTTP REQUEST GET**
   - Al iniciar el dispositivo o abrir la app, se envía un `GET` al servidor para obtener **todos los datos existentes** (trabajos asignados).

2. **Guardar en SQLite**
   - Los datos recibidos se almacenan localmente para permitir **acceso constante**, incluso **sin internet**.

3. **Conexión SSE**
   - La app abre una conexión **SSE** para escuchar **actualizaciones en tiempo real** mientras esté abierta.
---

### Desconexiones

- En caso de desconexión:
  - Se repite el proceso de **Conexión y Reconexión** para mantener los datos lo más actualizados posible.

---

###  Envío de datos

1. Los cambios de **estado de progreso** se guardan primero en **SQLite**.
2. Se envía un HTTP REQUEST de tipo `UPDATE` al servidor.
   - **Conexión Fallida:**  
     - La app vuelve a intentar enviar los datos de forma periódica hasta que se confirme la entrega.
   - **Conexión Exitosa:**  
     - Se espera la respuesta exitosa del servidor y se actualiza el estado local.

---

### 🔔 Notificaciones FCM

- Cuando el servidor asigna **un nuevo trabajo**, envía una **notificación push** usando **Firebase Cloud Messaging (FCM)**.
- La notificación **despierta la app**, sincroniza datos con el servidor y muestra la alerta: **"Nuevo trabajo asignado"**.
- Esto asegura que, aunque la app esté **cerrada**, el trabajador reciba nuevas asignaciones al instante.

---



