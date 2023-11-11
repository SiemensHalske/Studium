import java.util.Random;

public class Aufgabe_9_DiceSimulation {

    public static void main(String[] args) {
        int[] counts = new int[6]; // Array to hold the count of each die face
        int totalRolls = 100_000;
        Random random = new Random();

        // Simulate the dice rolls
        for (int i = 0; i < totalRolls; i++) {
            int roll = random.nextInt(6) + 1; // Roll the dice (1-6)
            counts[roll - 1]++; // Increment the count for the rolled number
        }

        // Print the results
        System.out.println("Simulation of " + totalRolls + " dice rolls:");
        for (int i = 0; i < counts.length; i++) {
            double percentage = (counts[i] / (double) totalRolls) * 100;
            System.out.printf("Number %d: %d times (%.2f%%)%n", i + 1, counts[i], percentage);
        }
    }
}