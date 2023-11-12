public class Fahrzeug {

    private String marke;
    private String typ;
    private int tankkapazitaet;
    private int tankinhalt;
    private double verbrauch;

    public Fahrzeug(String marke, String typ, int tankkapazitaet, int tankinhalt, double verbrauch) {
        this.marke = marke;
        this.typ = typ;
        this.tankkapazitaet = tankkapazitaet;
        this.tankinhalt = tankinhalt;
        this.verbrauch = verbrauch;
    }

    public void tanken(int liter) {
        if (liter > this.tankkapazitaet) {
            System.out.println("Der Tank ist voll!");
        } else {
            this.tankinhalt += liter;
        }
    }

    public double fahren(double kilometer) {
        double verbrauchProKilometer = this.verbrauch / 100;
        double verbrateneLiter = kilometer * verbrauchProKilometer;
        if (verbrateneLiter > this.tankinhalt) {
            double moeglicheStrecke = this.tankinhalt / verbrauchProKilometer;
            System.out.println("Die maximale Strecke beträgt " + moeglicheStrecke + " Kilometer.");
            return moeglicheStrecke;
        } else {
            this.tankinhalt -= verbrateneLiter;
            return kilometer;
        }
    }

    public String toString() {
        return "Fahrzeug: " + this.marke + " " + this.typ + ", Tankinhalt: " + this.tankinhalt + " Liter";
    }
}