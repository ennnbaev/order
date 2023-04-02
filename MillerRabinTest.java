import java.math.BigInteger;
import java.util.Random;

/**
 * This class has a public method that checks if a number is prime. We used the Miller-Rabin test for this.
 * There is also a variable that shows how many iterations of the check will be done. The class works with a BigInteger
 * to test for large values.
 */
public class MillerRabinTest {

    //This variable means how many times we repeat the Miller-Rabin test
    private static final int ITERATIONS = 100;

    /**
     * Check if a number is prime using Miller-Rabin test
     *
     * @param number input value which can be prime or not
     * @return true if a number is prime or false if a number isn't
     */
    public static boolean primecheck(BigInteger number) {
        //Check if input number less 1 or equals
        if (number.compareTo(BigInteger.ONE) <= 0) {
            return false;
        }
        //Check if input number less than 3 or equals
        if (number.compareTo(BigInteger.valueOf(3)) <= 0) {
            return true;
        }

        // split n - 1 into two factors: d * 2^r
        int k = 0;
        BigInteger d = number.subtract(BigInteger.ONE);
        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            k++;
            d = d.divide(BigInteger.TWO);
        }

        // repeat the Miller-Rabin test ITERATIONS times
        for (int i = 0; i < ITERATIONS; i++) {
            BigInteger a = getRandomBigInteger(BigInteger.TWO, number.subtract(BigInteger.ONE));
            BigInteger x = a.modPow(d, number);
            if (x.equals(BigInteger.ONE) || x.equals(number.subtract(BigInteger.ONE))) {
                continue;
            }
            boolean isProbablePrime = false;
            for (int j = 0; j < k; j++) {
                x = x.modPow(BigInteger.TWO, number);
                if (x.equals(BigInteger.ONE)) {
                    return false;
                }
                if (x.equals(number.subtract(BigInteger.ONE))) {
                    isProbablePrime = true;
                    break;
                }
            }

            if (!isProbablePrime) {
                return false;
            }
        }

        return true;
    }


    /**
     * Get random value in necessary range
     *
     * @param min value for start range
     * @param max value for end range
     * @return random BigInteger from necessary range
     */
    private static BigInteger getRandomBigInteger(BigInteger min, BigInteger max) {
        Random random = new Random();
        BigInteger res;
        do {
            res = new BigInteger(max.bitLength(), random);
        } while (res.compareTo(min) < 0 || res.compareTo(max) > 0);
        return res;
    }
}