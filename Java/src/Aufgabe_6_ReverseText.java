import javax.swing.*;

public class Aufgabe_6_ReverseText {

    public static void main(String[] args) {
        // Use a dialog to get the input text
        String inputText = JOptionPane.showInputDialog("Enter text to reverse and capitalize:");
        
        // Process the text: remove spaces, reverse and convert to upper case
        String processedText = new StringBuilder(inputText.replaceAll("\\s", ""))
                .reverse().toString().toUpperCase();
        
        // Output the result
        JOptionPane.showMessageDialog(null, "Reversed Text: " + processedText);
    }
}