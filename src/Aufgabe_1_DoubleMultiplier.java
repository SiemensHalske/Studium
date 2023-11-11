import java.util.Scanner;


public class Aufgabe_1_DoubleMultiplier {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the first decimal number: ");
        double number1 = scanner.nextDouble();
        
        System.out.print("Enter the second decimal number: ");
        double number2 = scanner.nextDouble();
        
        scanner.close();
        
        double produkt = number1 * number2;
        
        // Print out the result formatted to two decimal places
        System.out.printf("The product is: %,.2f", produkt);
    }
}