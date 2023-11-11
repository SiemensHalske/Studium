
import java.util.Scanner;

public class Aufgabe_2_DecimalFormatting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a decimal number: ");
        double number = scanner.nextDouble();
        scanner.close();

        System.out.println(number);

        System.out.printf("%10.3f%n", number);

        
        System.out.printf("%11.0f %11.2f %11.4e%n", number, number, number);

        // Output the number right-aligned at the 20th position with leading zeros
        System.out.printf("%020.3f%n", number);
    }
}
