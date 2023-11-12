import java.util.Random;

public class Aufgabe_13_DistanceMatrix {
    public static void main(String[] args) {
        final int CITY_COUNT = 20;
        int[] x = new int[CITY_COUNT];
        int[] y = new int[CITY_COUNT];
        double[][] distanceMatrix = new double[CITY_COUNT][CITY_COUNT];
        Random random = new Random();

        // Generate random coordinates for the cities
        for (int i = 0; i < CITY_COUNT; i++) {
            x[i] = random.nextInt(50) + 1;
            y[i] = random.nextInt(50) + 1;
        }

        // Calculate the distance matrix using Pythagorean theorem
        for (int i = 0; i < CITY_COUNT; i++) {
            for (int j = 0; j < CITY_COUNT; j++) {
                distanceMatrix[i][j] = Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
            }
        }

        // Print the coordinates and distance matrix
        System.out.println("Coordinates of the cities:");
        for (int i = 0; i < CITY_COUNT; i++) {
            System.out.printf("City %d: (%d,%d)%n", i, x[i], y[i]);
        }
        
        System.out.println();
        System.out.println("Distance Matrix:");
        System.out.print("        ");
        for (int i = 0; i < CITY_COUNT; i++) {
            System.out.printf("%8d", i);
        }
        System.out.println();
        for (int i = 0; i < CITY_COUNT; i++) {
            System.out.printf("%8d", i);
            for (int j = 0; j < CITY_COUNT; j++) {
                System.out.printf("%8.3f", distanceMatrix[i][j]);
            }
            System.out.println();
        }
    }
}