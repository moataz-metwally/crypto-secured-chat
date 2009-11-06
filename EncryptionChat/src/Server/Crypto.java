/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author moataz
 */
import java.io.IOException;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Crypto {

    Cipher ecipher;
    Cipher dcipher;

    String sharedKey;
    Bencoder encoder = new Bencoder();
    Bdecoder decoder = new Bdecoder();

    /**
     * Input a string that will be md5 hashed to create the key.
     *
     * @return void, cipher initialized
     */

    public Crypto() {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            byte x[] = kgen.generateKey().getEncoded();

            sharedKey = encoder.encodeBuffer(x);

            keyadd(x);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Crypto(String key) throws IOException {

        keyadd(decoder.decodeBuffer(key));
    }

    public void keyadd(byte[] key) {

        SecretKeySpec skey = new SecretKeySpec(key, "AES");
        this.setupCrypto(skey);
    }

    public String getSharedKey() {
        return sharedKey;
    }

    private void setupCrypto(SecretKey key) {
        // Create an 8-byte initialization vector
        byte[] iv = new byte[]{
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f
        };

        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
        try {
            ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // CBC requires an initialization vector
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// Buffer used to transport the bytes from one stream to another
    byte[] buf = new byte[1024];

    /**
     * Input is a string to encrypt.
     *
     * @return a Hex string of the byte array
     */
    public String encrypt(String plaintext) {
        try {
            byte[] ciphertext = ecipher.doFinal(plaintext.getBytes("UTF-8"));
            return encoder.encodeBuffer(ciphertext);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String decrypt(String hexCipherText) {
        try {
            String plaintext = new String(dcipher.doFinal(decoder.decodeBuffer(hexCipherText)), "UTF-8");
            return plaintext;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
