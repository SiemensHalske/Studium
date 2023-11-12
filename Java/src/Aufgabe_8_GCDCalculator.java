import java.util.Scanner;

public class Aufgabe_8_GCDCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first number: ");
        int number1 = scanner.nextInt();
        System.out.print("Enter the second number: ");
        int number2 = scanner.nextInt();
        scanner.close();

        int gcd = calculateGCD(number1, number2);
        System.out.println("The GCD of " + number1 + " and " + number2 + " is " + gcd);
    }

    private static int calculateGCD(int number1, int number2) {
        while (number2 != 0) {
            int temp = number2;
            number2 = number1 % number2;
            number1 = temp;
        }
        return number1;
    }
}