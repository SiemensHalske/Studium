
public class Aufgabe_7_MultiplicationTable {

    public static void main(String[] args) {
        printStandardTable();
        System.out.println("\n--- EXTENDED TABLE ---\n");
        printFormattedTable();
    }

    private static void printStandardTable() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.printf("%4d", i * j);
            }
            System.out.println();
        }
    }

    private static void printFormattedTable() {
        // Print the top header for the extended table
        System.out.print("     ");
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println("\n    ------------------------------------------");

        // Print each row of the extended table
        for (int i = 1; i <= 10; i++) {
            
            // Print the row header
            System.out.printf("%2d |", i);

            // Print the row's multiplication values
            for (int j = 1; j <= 10; j++) {
                System.out.printf("%4d", i * j);
            }
            System.out.println();
        }
    }
}
