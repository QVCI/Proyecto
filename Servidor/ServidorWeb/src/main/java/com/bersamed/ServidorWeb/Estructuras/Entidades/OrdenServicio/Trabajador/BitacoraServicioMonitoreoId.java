package com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador;

import java.io.Serializable;
import java.util.Objects;

public class BitacoraServicioMonitoreoId implements Serializable {
    private String bcodigo;
    private short bidversion;
    private String dpartida;
    private short idmensaje;

    public BitacoraServicioMonitoreoId() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BitacoraServicioMonitoreoId)) return false;
        BitacoraServicioMonitoreoId that = (BitacoraServicioMonitoreoId) o;
        return bidversion == that.bidversion &&
               idmensaje == that.idmensaje &&
               Objects.equals(bcodigo, that.bcodigo) &&
               Objects.equals(dpartida, that.dpartida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bcodigo, bidversion, dpartida, idmensaje);
    }
}
