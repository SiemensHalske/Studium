public class SkriptA30
{
    private double verbrauch; // Liter pro 100 km
    private double tankinhalt; // aktueller Tankinhalt in Litern
    private double tankvolumen; // maximales Tankvolumen

    SkriptA30(double verbrauch, double tankvolumen) {
        this.verbrauch = verbrauch;
        this.tankvolumen = tankvolumen;
        this.tankinhalt = 0; // Tank ist anfangs leer
    }

    public void tanken(double liter) {
        if (tankinhalt + liter > tankvolumen) {
            System.out.println("Der Tank des Fahrzeugs ist voll!");
            tankinhalt = tankvolumen;
        } else {
            tankinhalt += liter;
        }
        System.out.printf("Tankinhalt: %.2f Liter im Tank.%n", tankinhalt);
    }

    public void fahren(double strecke) {
        double moeglicheStrecke = (tankinhalt / verbrauch) * 100;
        if (strecke > moeglicheStrecke) {
            System.out.printf("Das Fahrzeug kann nur noch %.2f km mit dem aktuellen Tankinhalt fahren.%n",
                    moeglicheStrecke);
            tankinhalt = 0;
        } else {
            tankinhalt -= (strecke * verbrauch) / 100;
            System.out.printf("Nach der Fahrt sind noch %.2f Liter im Tank.%n", tankinhalt);
        }
    }
}



