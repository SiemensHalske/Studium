import java.math.BigInteger;

public class Aufgabe_12_SieveOfEratosthenes {

    public static void main(String[] args) {
        final BigInteger MAX = new BigInteger("10000000000000");
        boolean[] isPrime = new boolean[MAX.intValue() + 1]; // Array of booleans from 0 to 1000
        for (int i = 2; i <= MAX.intValue(); i++) {
            isPrime[i] = true; // Initialize all indices to true
        }

        // Implement the Sieve of Eratosthenes
        for (int factor = 2; factor * factor <= MAX.intValue(); factor++) {
            if (isPrime[factor]) {
                for (int j = factor * factor; j <= MAX.intValue(); j += factor) {
                    isPrime[j] = false; // Set multiple of prime numbers to false
                }
            }
        }

        // Print prime numbers in a formatted way
        System.out.println("Prime numbers between 2 and " + MAX + ":");
        for (int i = 2, count = 0; i <= MAX.intValue(); i++) {
            if (isPrime[i]) {
                System.out.printf("%4d", i);
                count++;
                // Break line after every 10 numbers for readability
                if (count % 10 == 0) {
                    System.out.println();
                } else {
                    System.out.print(", ");             }
            }
        }
    }
}
