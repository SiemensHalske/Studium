import java.util.Scanner;

public class SIMPLE_Aufgabe_3_IbanCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Geben Sie die Bankleitzahl ein:");
        String bankCode = scanner.nextLine();
        
        System.out.println("Geben Sie die Kontonummer ein:");
        String accountNumber = scanner.nextLine();
        
        // Stellen Sie sicher, dass die Kontonummer 10 Stellen lang ist
        accountNumber = ("0000000000" + accountNumber).substring(accountNumber.length());

        // Landeskennzahl für Deutschland und zwei Nullen für die Prüfsumme
        String countryCodeNumeric = "131400";

        // Kombinieren Sie Bankleitzahl, Kontonummer und Ländercode
        String bban = bankCode + accountNumber + countryCodeNumeric;

        // Ersetzen von Buchstaben durch Zahlen
        bban = bban.replace("A", "10")
                .replace("B", "11")
                .replace("C", "12")
                .replace("D", "13")
                .replace("E", "14")
                .replace("F", "15")
                .replace("G", "16")
                .replace("H", "17")
                .replace("I", "18")
                .replace("J", "19")
                .replace("K", "20")
                .replace("L", "21")
                .replace("M", "22")
                .replace("N", "23")
                .replace("O", "24")
                .replace("P", "25")
                .replace("Q", "26")
                .replace("R", "27")
                .replace("S", "28")
                .replace("T", "29")
                .replace("U", "30")
                .replace("V", "31")
                .replace("W", "32")
                .replace("X", "33")
                .replace("Y", "34")
                .replace("Z", "35");

        // Berechnen der Prüfsumme nach Modulo 97
        String tempBban = bban.substring(0, 9);
        String remainingBban = bban.substring(9);
        long tempValue = Long.parseLong(tempBban) % 97;
        
        tempBban = tempValue + remainingBban.substring(0, 7);
        remainingBban = remainingBban.substring(7);
        tempValue = Long.parseLong(tempBban) % 97;

        tempBban = tempValue + remainingBban;
        tempValue = Long.parseLong(tempBban) % 97;

        // Prüfsumme berechnen und führende Nullen hinzufügen
        int checksum = (int) (98 - tempValue);
        String checksumString = String.format("%02d", checksum);
        
        // Zusammenbau der IBAN
        String iban = "DE" + checksumString + bankCode + accountNumber;
        System.out.println("Die berechnete IBAN ist: " + iban);

        scanner.close();
    }
}
