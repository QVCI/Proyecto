package com.bersamed.ServidorWeb.infraestructura.jwt;

import java.security.MessageDigest;

public class Sha1Util 
{
    public static String hashSha1(String texto) 
    {
        try 
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] resultado = md.digest(texto.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : resultado) 
            {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } 
        catch (Exception e) 
        {
            throw new RuntimeException("Error al realizar el hash", e);
        }
    }
}