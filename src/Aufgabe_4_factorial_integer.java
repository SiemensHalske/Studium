import java.util.Scanner;
import java.math.BigInteger;

public class Aufgabe_4_factorial_integer {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter an integer value: ");
            BigInteger n = scanner.nextBigInteger();
            BigInteger factorial = BigInteger.ONE;
            for (BigInteger i = BigInteger.ONE; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
                factorial = factorial.multiply(i);
            }
            System.out.println("The factorial of " + n + " is " + factorial);
        }
    }
}