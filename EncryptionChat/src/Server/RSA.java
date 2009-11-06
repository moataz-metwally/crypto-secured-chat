/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Server;




import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.SecureRandom;


public class RSA {
   private final static BigInteger one      = new BigInteger("1");
   private final static SecureRandom random = new SecureRandom();


   private BigInteger publicKey;
   private BigInteger modulus;

 

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger getModulus() {
        return modulus;
    }

    public RSA(BigInteger publicKey, BigInteger modulus) {
        this.publicKey = publicKey;
        this.modulus = modulus;
    }


   BigInteger encrypt(BigInteger message) {
      return message.modPow(publicKey, modulus);
   }


    BigInteger encryptString(String m) {
        BigInteger message = new BigInteger(m.getBytes());
      return message.modPow(publicKey, modulus);
   }



}