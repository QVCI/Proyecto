package com.bersamed.ServidorWeb.infraestructura.config;

public class Configuraciones {
    private String certificadoAutogenerado = "s3cr3t0-l0c4l-JWT-PR0V1S10NAL_2025!";
    //Rate Limiting
    /*
     * cantidadPeticionesSimultaneo: Establece la cantidad de peticiones de autenticación que puede tener el servidor
     * cantidadTokens: Establece cuantos tokens se recargaran en el lapso de tiempoRecargaTokens
     * tiempodeRecargaTokens: Establece el tiempo que tarda en recargar los tokens por ip
    */
        private int cantidadPeticionesSimultaneo = 5;
        private int cantidadTokens = 5;
        private int tiempodeRecargaTokens = 1;

    public String getCertificadoAutogenerado() {
        return certificadoAutogenerado;
    }

    public int getCantidadPeticionesSimultaneo()
    {
        return cantidadPeticionesSimultaneo;
    }

    public int getCantidadTokens()
    {
        return cantidadTokens;
    }
    
    public int getTiempoRecargaTokens()
    {
        return tiempodeRecargaTokens;
    }


}
