public class UE_30_Fahrzeug extends SkriptA30 {
    public UE_30_Fahrzeug() {
        super();
    }

    public class Fahrzeug {
        private double verbrauch;
        private int tankvolumen;
        private double tankinhalt;

        public Fahrzeug() {
            setVerbrauch(9.6); // Set the Verbrauch for Porsche 911
            setTankvolumen(80); // Set the Tankvolumen for Porsche 911
            setTankinhalt(0); // Set the initial Tankinhalt to 0
        }

        public void setVerbrauch(double verbrauch) {
            this.verbrauch = verbrauch;
        }

        public double getVerbrauch() {
            return verbrauch;
        }

        public void setTankvolumen(int tankvolumen) {
            this.tankvolumen = tankvolumen;
        }

        public int getTankvolumen() {
            return tankvolumen;
        }

        public void setTankinhalt(double tankinhalt) {
            this.tankinhalt = tankinhalt;
        }

        public double getTankinhalt() {
            return tankinhalt;
        }

        public void tanken(double liter) {
            if (tankinhalt + liter > tankvolumen) {
                System.out.println("Tanken nicht möglich, Tank ist voll");
            } else {
                tankinhalt += liter;
                System.out.println("Tankinhalt: " + tankinhalt + " Liter");
            }
        }

        public void fahren(int km) {
            double verbrauchProKm = verbrauch / 100;
            double verbrauch = km * verbrauchProKm;
            if (tankinhalt - verbrauch < 0) {
                System.out.println("Tanken nicht möglich, Tank ist leer");
            } else {
                tankinhalt -= verbrauch;
                System.out.println("Tankinhalt: " + tankinhalt + " Liter");
            }
        }

        public static void main(String[] args) {
            UE_30_Fahrzeug outer = new UE_30_Fahrzeug();
            Fahrzeug porsche911 = outer.new Fahrzeug();

            porsche911.tanken(40); // Tankt 40 Liter
            porsche911.fahren(300); // Fahrt 300 km
            porsche911.fahren(300); // Fahrt nochmals 300 km
            porsche911.fahren(200); // Fahrt 200 km
            porsche911.tanken(20); // Tankt 20 Liter
        }
    }
}
