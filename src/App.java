import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) {
        // Benutzer nach dem Radius fragen
        String input = JOptionPane.showInputDialog("Radius eingeben");

        double radius = Double.parseDouble(input);

        final double PI = 3.14159265;

        double umfang = 2 * PI * radius;

        double flaeche = PI * Math.pow(radius, 2);

        System.out.printf("Bei einem Radius von %.2f beträgt der Umfang %.2f. Die Fläche ist %.2f.%n", radius, umfang, flaeche);
    }

    class NestedApp {
        // add any nested class code here
    }
}
