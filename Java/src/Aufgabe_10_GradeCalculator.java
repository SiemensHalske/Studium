import java.util.Scanner;

public class Aufgabe_10_GradeCalculator {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the score: ");
        int score = scanner.nextInt();
        scanner.close();

        // Validate the score
        if (score < 0 || score > 70) {
            System.out.println("Error: Score must be between 0 and 70.");
            return;
        }

        // Calculate the grade
        String grade;
        if (score <= 30) {
            grade = "mangelhaft";
        } else if (score <= 40) {
            grade = "ausreichend";
        } else if (score <= 50) {
            grade = "befriedigend";
        } else if (score <= 60) {
            grade = "gut";
        } else {
            grade = "sehr gut";
        }

        // a case-statement could also be used here, but naaaah.....
        // why not train a neural network for that task? :D

        System.out.println("Score: " + score + " - Grade: " + grade);
    }
}