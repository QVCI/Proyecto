package com.bersamed.ServidorWeb.Estructuras.Entidades.OrdenServicio.Trabajador;

import java.io.Serializable;

public class BitacoraServicioDetalleId implements Serializable {
    private String bcodigo;
    private short bidversion;
    private String partida;
    private short idversion;
    public BitacoraServicioDetalleId() {
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bcodigo == null) ? 0 : bcodigo.hashCode());
        result = prime * result + bidversion;
        result = prime * result + ((partida == null) ? 0 : partida.hashCode());
        result = prime * result + idversion;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BitacoraServicioDetalleId other = (BitacoraServicioDetalleId) obj;
        if (bcodigo == null) {
            if (other.bcodigo != null)
                return false;
        } else if (!bcodigo.equals(other.bcodigo))
            return false;
        if (bidversion != other.bidversion)
            return false;
        if (partida == null) {
            if (other.partida != null)
                return false;
        } else if (!partida.equals(other.partida))
            return false;
        if (idversion != other.idversion)
            return false;
        return true;
    }
    
    


}
