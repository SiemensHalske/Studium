public class Aufgabe_5_DiceRoller {

    public static void main(String[] args) {
        int[] dice = new int[6];
        int sum, product;
        long rollCount = 0;

        do {
            sum = 0;
            product = 1;
            
            // Roll six dice times
            for (int i = 0; i < 6; i++) {
                dice[i] = (int)(Math.random() * 6) + 1;  // Generate a random number between 0 and 6
                sum += dice[i];
                product *= dice[i];
            }

            rollCount++;
        } while (sum != 22 || product != 540);

        // Print the results
        System.out.println("It took " + rollCount + " rolls to get a sum of 22 and a product of 540.");
        System.out.print("The dice rolls were: ");
        for (int roll : dice) {
            System.out.print(roll + " ");
        }
    }
}