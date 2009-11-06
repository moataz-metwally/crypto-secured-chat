/*
 * RSA Class
 * Desc: RSA class implements the RSA asymmetric encryption
 */
package clientchat;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.SecureRandom;

public class RSA {

    private final static BigInteger one = new BigInteger("1");
    private final static SecureRandom random = new SecureRandom();

    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger modulus;

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger getModulus() {
        return modulus;
    }

    private int PublicKeyLength, PrivateKeyLength;

    BigInteger p, q;
    BigInteger phi;

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }

    public RSA(int PublicKeyLength, int PrivateKeyLength) {
        this.PublicKeyLength = PublicKeyLength;
        this.PrivateKeyLength = PrivateKeyLength;

        p = BigInteger.probablePrime(PrivateKeyLength / 2, random);
        q = BigInteger.probablePrime(PrivateKeyLength / 2, random);

        phi = (p.subtract(one)).multiply(q.subtract(one));

        modulus = p.multiply(q);

        publicKey = generatePublicKey();
        privateKey = publicKey.modInverse(phi);
    }

    private BigInteger generatBigNumber(int N) {

        return BigInteger.probablePrime(N, random);
    }

    private BigInteger generatePublicKey() {

        BigInteger publicKey= null;
        do {

            publicKey = generatBigNumber(PublicKeyLength);

        } while (!publicKey.gcd(phi).equals(one));

        return publicKey;
    }

    private BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    public BigInteger encryptString(String s) {
        BigInteger message = new BigInteger(s.getBytes());
        return message.modPow(publicKey, modulus);
    }

    private BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(privateKey, modulus);
    }

    public String decryptToString(BigInteger encrypted) {
        return new String(encrypted.modPow(privateKey, modulus).toByteArray());
    }

    public String toString() {
        String s = "";
        s += "public  = " + publicKey + "\n";
        s += "private = " + privateKey + "\n";
        s += "modulus = " + modulus;
        return s;
    }

}
