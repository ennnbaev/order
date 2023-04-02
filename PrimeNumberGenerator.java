import java.math.BigInteger;
import java.util.Random;

/**
 * This class has a method that generates a prime number from the number of bits.
 * And it is checked by an auxiliary class whether it is prime
 */
public class PrimeNumberGenerator {

    /**
     * Generates a BigInt prime using the random library or custom code below.
     * After that, we check if this number is prime, if not, we try again
     *
     * @param numBits a positive integer that represents the number of bits
     * @return prime number of this number of bits
     */
    public static BigInteger primegen(int numBits) {
        if (numBits == 0) {
            throw new IllegalArgumentException("Number of bits must be positive.");
        }
        Random random = new Random();
        BigInteger prime = new BigInteger(numBits, random);
        while (!MillerRabinTest.primecheck(prime)) {
            prime = new BigInteger(numBits, random);
        }
        return prime;
    }

    /**
     * Alternative code, instead of using the random library if it's not allowed
     *
     * @param numBits a positive integer that represents the number of bits
     * @return random BigInteger
     */
    private static BigInteger getRandomBigInteger(int numBits) {
        BigInteger number = BigInteger.ONE;
        // generate a number from bits
        number = number.modPow(BigInteger.TWO, BigInteger.valueOf(numBits)).subtract(BigInteger.ONE);
        // find the square root of a number
        number = number.sqrt();

        return number;
    }

}
