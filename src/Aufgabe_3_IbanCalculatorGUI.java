
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class Aufgabe_3_IbanCalculatorGUI extends JFrame {
    private JTextField txtCountryCode;
    private JTextField txtBankCode;
    private JTextField txtAccountNumber;
    private JButton btnCalculate;
    private JLabel lblIban;

    public Aufgabe_3_IbanCalculatorGUI() {
        // Frame settings
        setTitle("IBAN Calculator");
        setSize(300, 200);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel with a grid layout
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

        // Add components to panel
        panel.add(new JLabel("Country Code:"));
        txtCountryCode = new JTextField();
        panel.add(txtCountryCode);

        panel.add(new JLabel("Bank Code:"));
        txtBankCode = new JTextField();
        panel.add(txtBankCode);

        panel.add(new JLabel("Account Number:"));
        txtAccountNumber = new JTextField();
        panel.add(txtAccountNumber);

        btnCalculate = new JButton("Calculate IBAN");
        panel.add(btnCalculate);

        // Add panel to frame
        add(panel, BorderLayout.CENTER);

        // Label for displaying the result
        lblIban = new JLabel(" ", SwingConstants.CENTER);
        add(lblIban, BorderLayout.SOUTH);

        // Action for the calculate button
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String country = txtCountryCode.getText().toUpperCase().trim();
                String bankCode = txtBankCode.getText().trim();
                String accountNumber = txtAccountNumber.getText().trim();

                String iban = calculateIban(country, bankCode, accountNumber);
                lblIban.setText("Calculated IBAN: " + iban);
            }
        });
    }

    private static String calculateIban(String country, String bankCode, String accountNumber) {
        String countryCodeNumeric = convertCountryCodeToNumeric(country);
        String intermediateNumber = bankCode + accountNumber + countryCodeNumeric + "00";
        BigInteger mod97 = new BigInteger(intermediateNumber).mod(BigInteger.valueOf(97));
        String checkDigits = String.format("%02d", 98 - mod97.intValue());
        return country + checkDigits + bankCode + accountNumber;
    }

    private static String convertCountryCodeToNumeric(String country) {
        char[] countryChars = country.toCharArray();
        StringBuilder numericCountryCode = new StringBuilder();

        for (char letter : countryChars) {
            int numericValue = Character.getNumericValue(letter);
            numericCountryCode.append(numericValue);
        }

        return numericCountryCode.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new IbanCalculatorGUI().setVisible(true);
            }
        });
    }
}
