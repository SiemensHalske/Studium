public class Aufgabe_15_PalindromeChecker {

    public static void main(String[] args) {
        String testString = "A man, a plan, a canal, Panama"; // Example string
        System.out.println("Is the string a palindrome? " + isPalindrome(testString));
    }

    // Method to check if a string is a palindrome
    public static boolean isPalindrome(String str) {
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase(); // Remove non-alphanumeric characters and convert to lower case
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false; // Characters do not match
            }
            left++;
            right--;
        }
        return true; // String is a palindrome
    }
}