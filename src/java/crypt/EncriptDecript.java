/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

/**
 *
 * @author Ilia Consuegra, Alain Lozano
 */
public class EncriptDecript {

    // Fíjate que el String es de exactamente 16 bytes

    /**
     * Cifra un texto con RSA, modo ECB y padding PKCS5Padding (simétrica) y lo
     * retorna
     *
     * @param clave La clave del usuario
     * @param mensaje El mensaje a cifrar
     * @return Mensaje cifrado
     */
    
    
    public static String encrypt(String password) {
        String mensajeCifradoHex = null;
        X509EncodedKeySpec keySpec = null;
        KeyFactory keyFactory = null;
        try {
            String path = EncriptDecript.class.getResource("Publica.dat").getPath();
           
            byte[] key = fileReader(path);

            if (path != null) {
                generarClaves();
            }
            keyFactory = KeyFactory.getInstance("RSA");
            // Obtenemos el keySpec
            keySpec = new X509EncodedKeySpec(key);
            // Obtenemos una instancia de SecretKeyFactory con el algoritmo "PBKDF2WithHmacSHA1"
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            byte[] mensajeCifrado = cifrarTexto(publicKey, password);
            mensajeCifradoHex = hexadecimal(mensajeCifrado);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(EncriptDecript.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncriptDecript.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensajeCifradoHex;
    }
    
    
    public static String decrypt(String mensajeCifradoHex){
        String mensajeHasheado = null;
        try {
            
            String path = EncriptDecript.class.getResource("Privada.dat").getPath();
            byte[] key = fileReader(path);
            
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
            KeyFactory factoria = KeyFactory.getInstance("RSA");
            PrivateKey privada = factoria.generatePrivate(keySpec);
            
            String mensajeDescifrado = descifrarTexto(privada, mensajeCifradoHex);
            mensajeHasheado = hashearTexto(mensajeDescifrado);
            
            System.out.println(mensajeHasheado);
            
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(EncriptDecript.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncriptDecript.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensajeHasheado;
    }
    
    
    public static byte[] cifrarTexto(PublicKey clave, String mensaje) {
       
        Cipher cipher = null;
        String mensajeCifrado = null;
        byte[] encodedMessage = null;
        try {
            // Obtenemos una instancide de Cipher con el algoritmos que vamos a usar "RSA/ECB/PKCS1Padding"
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            // Iniciamos el Cipher en ENCRYPT_MODE y le pasamos la clave privada
            cipher.init(ENCRYPT_MODE, clave);
            // Le decimos que cifre (método doFinal())
            encodedMessage = cipher.doFinal(mensaje.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedMessage;
    }

    /**
     * Descifra un texto con RSA, modo ECB y padding PKCS5Padding (simétrica) y
     * lo retorna
     *
     * @param clave La clave del usuario
     */
    private static String descifrarTexto(PrivateKey privateKey, String mensaje) {
        String mensajeDescifrado = null;
        Cipher cipher = null;
 
        try {
            // Obtenemos una instancide de Cipher con el algoritmos que vamos a usar "RSA/ECB/PKCS1Padding"
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            
            cipher.init(DECRYPT_MODE, privateKey);
            
            byte[] decodedMessage = cipher.doFinal(hexStringToByteArray(mensaje));

            mensajeDescifrado = new String(decodedMessage);
            System.out.println("DECODED MESSAGE STRING" + decodedMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mensajeDescifrado;
    }
    
    
    public static String hashearTexto(String texto) {
        MessageDigest messageDigest;
        String encriptacion = "SHA";
        String mensaje = null;
        String base64 = null;
        try {
            // Obtén una instancia de MessageDigest que usa SHA
            messageDigest = MessageDigest.getInstance(encriptacion);
            // Convierte el texto en un array de bytes
            byte[] textArray = texto.getBytes("UTF-8");
            // Actualiza el MessageDigest con el array de bytes             
            messageDigest.update(textArray);
            // Calcula el resumen (función digest)
            byte[] digest = messageDigest.digest();
            base64 = Base64.getEncoder().encodeToString(digest);
            mensaje = hexadecimal(base64.getBytes());
           
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(EncriptDecript.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }

   
    private static byte[] fileReader(String path) {
        byte[] ret = null;

        File file = new File(path);

        try {
            ret = Files.readAllBytes(file.toPath());

        } catch (IOException ex) {
            Logger.getLogger(EncriptDecript.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }



    // Convierte Array de Bytes en hexadecimal
    static String hexadecimal(byte[] resumen) {
        String HEX = "";
        for (int i = 0; i < resumen.length; i++) {
            String h = Integer.toHexString(resumen[i] & 0xFF);
            if (h.length() == 1) {
                HEX += "0";
            }
            HEX += h;
        }
        return HEX.toUpperCase();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] mensajeByte = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            mensajeByte[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return mensajeByte;
    }

    public static void generarClaves() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.genKeyPair();

            byte[] privateKey = keyPair.getPrivate().getEncoded();
            fileWriter("Privada.dat", privateKey);
            byte[] publicKey = keyPair.getPublic().getEncoded();
            fileWriter("Publica.dat", publicKey);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncriptDecript.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Escribe un fichero
     *
     * @param path Path del fichero
     * @param text Texto a escibir
     */
    public static void fileWriter(String path, byte[] text) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
