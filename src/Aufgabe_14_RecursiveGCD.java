import java.util.Scanner;

public class Aufgabe_14_RecursiveGCD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first number: ");
        int number1 = scanner.nextInt();
        System.out.print("Enter the second number: ");
        int number2 = scanner.nextInt();
        scanner.close();

        int gcd = gcd(number1, number2);
        System.out.println("The GCD of " + number1 + " and " + number2 + " is " + gcd);
    }

    // Recursive method to calculate GCD
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}