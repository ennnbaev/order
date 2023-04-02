
import java.math.BigInteger;

/**
 * This class implement three methods: keygen, encrypt and decrypt
 */
public class RSA {

    /**
     * This method generates keys using the Euler function
     *
     * @param p prime number that is at least 10 digits long
     * @param q prime number that is at least 10 digits long
     * @return KeyPair of two keys Public Key and Private Key
     */
    public static KeyPair keygen(BigInteger p, BigInteger q) {
        // Calculate n, p * q
        BigInteger n = p.multiply(q);
        // Compute Euler function of n, (p-1) * (q-1)
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        // Choose an integer e such that 1 < e < φ(n) and e is relatively prime to φ(n)
        BigInteger e = BigInteger.valueOf(3);
        while (phi.gcd(e).intValue() > 1) {
            e = e.add(BigInteger.valueOf(2));
        }
        // Calculate d such that d * e ≡ 1 (mod φ(n))
        BigInteger d = e.modInverse(phi);
        // Create and return an instance object of the KeyPair class
        return new KeyPair(new PublicKey(n, e), new PrivateKey(n, d));
    }

    // Encrypting a character with the public key
    public static BigInteger encrypt(KeyPair publicKey, char c) {
        BigInteger m = BigInteger.valueOf(c);
        BigInteger n = publicKey.publicKey.getN();
        BigInteger e = publicKey.publicKey.getE();

        return m.modPow(e, n);
    }

    //Decrypting a character with a private key
    public static BigInteger decrypt(KeyPair privateKey, BigInteger encryptedChar) {
        BigInteger n = privateKey.privateKey.getN();
        BigInteger d = privateKey.privateKey.getD();

        return encryptedChar.modPow(d, n);
    }
}


/**
 * Helper class that stores a pair of keys (public and private)
 */
class KeyPair {
    public final PublicKey publicKey;
    public final PrivateKey privateKey;

    public KeyPair(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }
}

/**
 * The class that stores the values of the public key
 */
class PublicKey {
    private final BigInteger n;
    private final BigInteger e;

    public PublicKey(BigInteger n, BigInteger e) {
        this.n = n;
        this.e = e;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getE() {
        return e;
    }
}

/**
 * Class that stores private key values
 */
class PrivateKey {
    private final BigInteger n;
    private final BigInteger d;

    public PrivateKey(BigInteger n, BigInteger d) {
        this.n = n;
        this.d = d;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getD() {
        return d;
    }
}
// Мне нужен код с комментариями на языке программирования джава, который будет реализовывать следующий функционал: внедрение RSA, генератор ключей. Пример входных данных 127 131 . Пример выходных данных Public Key: (16637,11)  Private Key: (16637,14891).Необходимо использовать тест Miller-Rabin. Также добавить encrypt функционал который возьмет открытый ключ (n,e) и один символ c для кодирования и вернет зашифрованный текст, соответствующий открытому тексту, m. Пример входных данных: 16637 11 20 . Пример выходных данных: 12046. Также добавить метод decrypt который возьмет закрытый ключ (n, d) и зашифрованный символ и вернет соответствующий открытый текст. Пример входных данных: 16637 14891 12046. Ожидаемый результат: 20