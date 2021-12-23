/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private static byte[] salt = "esta es la salt!".getBytes();

    /**
     * Cifra un texto con RSA, modo ECB y padding PKCS5Padding (simétrica) y lo
     * retorna
     *
     * @param clave La clave del usuario
     * @param mensaje El mensaje a cifrar
     * @return Mensaje cifrado
     */
    public static String cifrarTexto(RSAPublicKey clave, String mensaje) {
        /*byte[] ret;
        File file = new File(path);
        X509EncodedKeySpec keySpec = null;
        KeyFactory keyFactory = null;*/
        Cipher cipher = null;
        String mensajeCifrado = null;
        try {
            // Obtenemos una instancide de Cipher con el algoritmos que vamos a usar "RSA/ECB/PKCS1Padding"
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            // Iniciamos el Cipher en ENCRYPT_MODE y le pasamos la clave privada
            cipher.init(ENCRYPT_MODE, clave);
            // Le decimos que cifre (método doFinal())
            byte[] encodedMessage = cipher.doFinal(mensaje.getBytes());

            // Obtenemos el vector CBC del Cipher (método getIV())
            //byte[] iv = cipher.getIV();
            // Guardamos el mensaje codificado: IV (16 bytes) + Mensaje
            //byte[] combined = concatArrays(iv, encodedMessage);
            // Escribimos el fichero cifrado 
            fileWriter("C:\\Users\\2dam.HZ375754\\Desktop\\encriptacion\\Publica.dat", encodedMessage);

            // Retornamos el texto cifrado
            mensajeCifrado = new String(encodedMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mensajeCifrado;
    }

    /**
     * Descifra un texto con RSA, modo ECB y padding PKCS5Padding (simétrica) y
     * lo retorna
     *
     * @param clave La clave del usuario
     */
    private static String descifrarTexto(RSAPrivateKey clave) {
        String mensajeDescifrado = null;
        Cipher cipher = null;

        // Fichero leído
        byte[] fileContent = fileReader("C:\\Users\\2dam.HZ375754\\Desktop\\encriptacion\\Privada.dat"); // Path del fichero EjemploRSA.dat

        try {
            // Obtenemos una instancide de Cipher con el algoritmos que vamos a usar "RSA/ECB/PKCS1Padding"
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            // Leemos el fichero codificado 
            //IvParameterSpec ivParam = new IvParameterSpec(Arrays.copyOfRange(fileContent, 0, 16));
            // Iniciamos el Cipher en ENCRYPT_MODE y le pasamos la clave privada y el ivParam
            cipher.init(DECRYPT_MODE, clave);
            // Le decimos que descifre
            byte[] decodedMessage = cipher.doFinal(fileContent);

            // Texto descifrado
            mensajeDescifrado = new String(decodedMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mensajeDescifrado;
    }

    /**
     * Retorna una concatenaci�n de ambos arrays
     *
     * @param array1
     * @param array2
     * @return Concatenaci�n de ambos arrays
     */
    /*
    private static byte[] concatArrays(byte[] array1, byte[] array2) {
        byte[] ret = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, ret, 0, array1.length);
        System.arraycopy(array2, 0, ret, array1.length, array2.length);
        return ret;
    }*/
    private static PublicKey fileReaderPublic(String path, File file) throws InvalidKeySpecException {
        byte[] ret;
        //File file = new File("C:\\Users\\2dam.HZ375754\\Desktop\\encriptacion\\Publica.dat");
        X509EncodedKeySpec keySpec = null;
        KeyFactory keyFactory = null;

        try {
            ret = Files.readAllBytes(file.toPath());
            // Obtenemos el keySpec
            keySpec = new X509EncodedKeySpec(ret);
            // Obtenemos una instancia de SecretKeyFactory con el algoritmo "PBKDF2WithHmacSHA1"
            keyFactory = KeyFactory.getInstance("RSA");

        } catch (IOException ex) {
            Logger.getLogger(EncriptDecript.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncriptDecript.class.getName()).log(Level.SEVERE, null, ex);
        }

        return keyFactory.generatePublic(keySpec);
    }

    private static PrivateKey fileReaderPrivate(String path, File file) throws InvalidKeySpecException {
        byte[] ret;
        //File file = new File("C:\\Users\\2dam.HZ375754\\Desktop\\encriptacion\\Privada.dat");
        PKCS8EncodedKeySpec keySpec = null;
        KeyFactory keyFactory = null;

        try {
            ret = Files.readAllBytes(file.toPath());
            keySpec = new PKCS8EncodedKeySpec(ret);
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (IOException ex) {
            Logger.getLogger(EncriptDecript.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncriptDecript.class.getName()).log(Level.SEVERE, null, ex);
        }
        return keyFactory.generatePrivate(keySpec);

    }

    public static String encryptDecrypt(String password) {
        String mensajeHasheado = null;
        try {

            File publica = new File("C:\\Users\\2dam.HZ375754\\Desktop\\encriptacion\\Publica.dat");
            File privada = new File("C:\\Users\\2dam.HZ375754\\Desktop\\encriptacion\\Privada.dat");
            if (!publica.exists() || !privada.exists()) {
                generarClaves();
            }

            RSAPublicKey publicKey = (RSAPublicKey) fileReaderPublic("C:\\Users\\2dam.HZ375754\\Desktop\\encriptacion\\Publica.dat", publica);
            RSAPrivateKey privateKey = (RSAPrivateKey) fileReaderPrivate("C:\\Users\\2dam.HZ375754\\Desktop\\encriptacion\\Privada.dat", privada);
            String mensajeCifrado = cifrarTexto(publicKey, password);
            byte[] mensajeCifradoArray = mensajeCifrado.getBytes();
            String mensajeCifradoHex = hexadecimal(mensajeCifradoArray);
            //String mensajeCifrado = ejemploRSA.cifrarTexto("C:\\Users\\2dam.HZ375754\\Desktop\\encriptacion\\Publica.dat", "Mensaje super secreto");
            System.out.println("Cifrado! -> " + mensajeCifradoHex);
            System.out.println("-----------");
            byte[] mensajeByte = hexStringToByteArray(mensajeCifradoHex);
            String mensaje = descifrarTexto(privateKey);
            System.out.println("Descifrado! -> " + mensaje);
            System.out.println("-----------");
            mensajeHasheado = hashearTexto(mensajeCifrado);

        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(EncriptDecript.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensajeHasheado;
    }

    public static String hashearTexto(String texto) {
        MessageDigest messageDigest;
        String encriptacion = "SHA";
        String mensaje = null;
        try {
            // Obtén una instancia de MessageDigest que usa SHA
            messageDigest = MessageDigest.getInstance(encriptacion);
            // Convierte el texto en un array de bytes
            byte[] textArray = texto.getBytes();
            // Actualiza el MessageDigest con el array de bytes             
            messageDigest.update(textArray);
            // Calcula el resumen (función digest)
            byte[] digest = messageDigest.digest();

            System.out.println("Mensaje original: " + texto);
            System.out.println("Número de Bytes: " + messageDigest.getDigestLength());
            System.out.println("Algoritmo usado: " + messageDigest.getAlgorithm());
            System.out.println("Resumen del Mensaje: " + digest);
            mensaje = hexadecimal(digest);
            System.out.println("Mensaje en Hexadecimal: " + mensaje);
            System.out.println("Proveedor: " + messageDigest.getProvider());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return mensaje;
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
            fileWriter("C:\\Users\\2dam.HZ375754\\Desktop\\encriptacion\\Privada.dat", privateKey);
            byte[] publicKey = keyPair.getPublic().getEncoded();
            fileWriter("C:\\Users\\2dam.HZ375754\\Desktop\\encriptacion\\Publica.dat", publicKey);

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

    /**
     * Retorna el contenido de un fichero
     *
     * @param path Path del fichero
     * @return El texto del fichero
     */
    public static byte[] fileReader(String path) {
        byte ret[] = null;
        File file = new File(path);
        try {
            ret = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

}
