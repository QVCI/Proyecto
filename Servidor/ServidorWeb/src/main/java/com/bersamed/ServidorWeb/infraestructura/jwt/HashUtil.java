package com.bersamed.ServidorWeb.infraestructura.jwt;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

public class HashUtil 
{
    public static String calcularHash(String clave, String sal) {
        final short longitudSal = 16;

        byte[] bytesclave = new byte[256];
        char[] charsclave = new char[clave.length() + longitudSal];
        char[] charsmd5sal;
        char[] sha1chars;
        char[] hashtotal;

        int longitudrealbytesclave = 0;

        try {
            if (sal == null || sal.isEmpty()) {
                byte[] bytesaleatorios = new byte[longitudSal];
                SecureRandom randgen = new SecureRandom();
                randgen.nextBytes(bytesaleatorios);

                MessageDigest md5 = MessageDigest.getInstance("MD5");
                charsmd5sal = byteArrayToCharArray(md5.digest(bytesaleatorios));

                Arrays.fill(bytesaleatorios, (byte) 0);
            } else {
                charsmd5sal = new char[longitudSal];
                for (int i = 0; i < longitudSal; ++i) {
                    charsmd5sal[i] = sal.charAt(i);
                }
            }

            System.arraycopy(charsmd5sal, 0, charsclave, 0, longitudSal);
            clave.getChars(0, clave.length(), charsclave, longitudSal);

            for (int i = 0; i < charsclave.length; ++i) {
                byte[] temp = ByteBuffer.allocate(2).putChar(charsclave[i]).array();
                for (byte b : temp) {
                    if (b != 0) {
                        bytesclave[longitudrealbytesclave++] = b;
                    }
                }
                charsclave[i] = '\0';
            }

            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            byte[] hashSHA1 = sha1.digest(Arrays.copyOf(bytesclave, longitudrealbytesclave));
            sha1chars = byteArrayToCharArray(hashSHA1);

            Arrays.fill(bytesclave, (byte) 0);

            hashtotal = new char[longitudSal + sha1chars.length];
            System.arraycopy(charsmd5sal, 0, hashtotal, 0, longitudSal);
            System.arraycopy(sha1chars, 0, hashtotal, longitudSal, sha1chars.length);

            Arrays.fill(charsmd5sal, '\0');
            Arrays.fill(sha1chars, '\0');

            return new String(hashtotal);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static char[] byteArrayToCharArray(byte[] bytes) {
        // Conversión byte[] → char[] (cada byte convertido a dos chars hexadecimales)
        char[] chars = new char[bytes.length * 2];
        final char[] hexArray = "0123456789ABCDEF".toCharArray();

        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            chars[j * 2] = hexArray[v >>> 4];
            chars[j * 2 + 1] = hexArray[v & 0x0F];
        }

        return chars;
    }

}
