import java.util.Random;
import java.util.Scanner;

public class Aufgabe_11_StickGame {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int sticks = random.nextInt(11) + 21; // Random number of sticks between 21 and 31
        int sticksTaken;

        System.out.println("Welcome to the Stick Game!");
        System.out.println("There are " + sticks + " sticks on the table.");

        while (sticks > 0) {
            // Human turn
            do {
                System.out.print("Take 1-3 sticks: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
                sticksTaken = scanner.nextInt();
                if (sticksTaken < 1 || sticksTaken > 3) {
                    System.out.println("Invalid input. Please enter a number between 1 and 3.");
                }
            } while (sticksTaken < 1 || sticksTaken > 3);

            sticks -= sticksTaken;
            if (sticks <= 0) {
                System.out.println("Human loses!");
                break;
            }
            System.out.println("Sticks left on the table: " + sticks);

            // Computer turn
            int toTake = (sticks - 1) % 4;
            if (toTake == 0) {
                toTake = 1; // If we're not in a winning position, take only one stick
            }
            sticks -= toTake;
            System.out.println("Computer takes " + toTake + " stick(s).");
            if (sticks <= 0) {
                System.out.println("Computer loses!");
                break;
            }
            System.out.println("Sticks left on the table: " + sticks);
        }

        scanner.close();
    }
}