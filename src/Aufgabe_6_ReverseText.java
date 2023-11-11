import javax.swing.*;

public class Aufgabe_6_ReverseText {

    public static void main(String[] args) {
        // Use a dialog to get the input text
        String inputText = JOptionPane.showInputDialog("Enter text to reverse and capitalize:");

        // If you want to use Scanner instead, comment out the dialog above and uncomment the lines below:
        // Scanner scanner = new Scanner(System.in);
        // System.out.print("Enter text to reverse and capitalize: ");
        // String inputText = scanner.nextLine();
        
        // Process the text: remove spaces, reverse and convert to upper case
        String processedText = new StringBuilder(inputText.replaceAll("\\s", ""))
                .reverse().toString().toUpperCase();
        
        // Output the result
        JOptionPane.showMessageDialog(null, "Reversed Text: " + processedText);
        
        // If using Scanner, use the line below instead of the dialog to output the result:
        // System.out.println("Reversed Text: " + processedText);
    }
}