import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        //Test Part1: primecheck
        System.out.println(MillerRabinTest.primecheck(BigInteger.ONE));
        System.out.println(MillerRabinTest.primecheck(BigInteger.TWO));
        System.out.println(MillerRabinTest.primecheck(BigInteger.ZERO));
        System.out.println(MillerRabinTest.primecheck(new BigInteger("32401")));
        System.out.println(MillerRabinTest.primecheck(new BigInteger("3244568")));

        //Test Part1: primegen
        System.out.println(PrimeNumberGenerator.primegen(24));
        System.out.println(PrimeNumberGenerator.primegen(8));
        System.out.println(MillerRabinTest.primecheck(PrimeNumberGenerator.primegen(1024)));


        // Test Part2:RSA Implementation
        // Test generate key pair
        KeyPair keyPair = RSA.keygen(BigInteger.valueOf(127), BigInteger.valueOf(131));
        System.out.println("Public Key: (" + keyPair.publicKey.getN() + "," + keyPair.publicKey.getE() + ")");
        System.out.println("Private Key: (" + keyPair.privateKey.getN() + "," + keyPair.privateKey.getD() + ")");

        // Test encrypt
        char c = 20;
        System.out.println(RSA.encrypt(keyPair, c));

        // Test decrypt

        System.out.println(RSA.decrypt(keyPair,BigInteger.valueOf(12046)));


    }
}