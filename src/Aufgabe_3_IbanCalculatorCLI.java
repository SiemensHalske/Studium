import java.util.Scanner;

public class Aufgabe_3_IbanCalculatorCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the country code: ");
        String country = scanner.nextLine().trim().toUpperCase();
        
        System.out.print("Enter the bank code: ");
        String bankCode = scanner.nextLine().trim();
        
        System.out.print("Enter the account number: ");
        String accountNumber = scanner.nextLine().trim();
        
        // Adding leading zeros if account number is less than 10 digits
        accountNumber = String.format("%010d", Integer.parseInt(accountNumber));


        String countryCodeNumeric = convertCountryCodeToNumeric(country);
        String ibanWithoutChecksum = bankCode + accountNumber + countryCodeNumeric + "00";
        int checksum = 98 - mod97(ibanWithoutChecksum);  // Calculating the checksum
        
        String iban = country + String.format("%02d", checksum) + bankCode + accountNumber; // Preparing the complete IBAN
        
        System.out.println("Calculated IBAN: " + iban);
        scanner.close();
    }

    
    private static String convertCountryCodeToNumeric(String country) {
        /**
        * Converts a given country code to its corresponding numeric representation.
        * 
        * @param country the country code to convert
        * @return the numeric representation of the country code
        */
        StringBuilder numericCountryCode = new StringBuilder();
        for (char letter : country.toCharArray()) {
            int numericValue = Character.getNumericValue(letter);
            numericCountryCode.append(numericValue - 10);
        }
        return numericCountryCode.toString();
    }

    
    private static int mod97(String number) {
        /**
        * Calculates the remainder of a given number when divided by 97.
        * 
        * @param number the number to calculate the remainder for
        * @return the remainder of the number when divided by 97
        */ 
        int remainder = 0;
        for (int i = 0; i < number.length(); i++) {
            remainder = (remainder * 10 + Character.getNumericValue(number.charAt(i))) % 97;
        }
        return remainder;
    }

}