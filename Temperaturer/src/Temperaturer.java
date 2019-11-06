import java.util.*; // Scanner, Locale
class Temperaturer {
    public static void main(String[] args) {
        System.out.println("TEMPERATURER\n");

        // inmatningsverktyg
        Scanner in = new Scanner(System.in); in.useLocale(Locale.US);
        // mata in uppgifter om antalet veckor och antalet mätningar
        System.out.print("antalet veckor: ");
        int antalVeckor = in.nextInt();
        System.out.print("antalet mätningar per vecka: ");
        int antalMatningarPerVecka = in.nextInt();
        // plats att lagra temperaturer
        double[][] t = new double[antalVeckor + 1][antalMatningarPerVecka + 1];
        // mata in temperaturerna
        for (int vecka = 1; vecka <= antalVeckor; vecka++) {
            System.out.println("temperaturer - vecka " + vecka + ":");
            for (int matning = 1; matning <= antalMatningarPerVecka; matning++)
                t[vecka][matning] = in.nextDouble();
        }
        System.out.println();
        // visa temperaturerna
        System.out.println("temperaturerna:");
        for (int vecka = 1; vecka <= antalVeckor; vecka++) {
            for (int matning = 1; matning <= antalMatningarPerVecka; matning++)
                System.out.print(t[vecka][matning] + " ");
            System.out.println();
        }
        System.out.println();
        // den minsta, den största och medeltemperaturen – veckovis
        double[] minT = new double[antalVeckor + 1];
        double[] maxT = new double[antalVeckor + 1];
        double[] sumT = new double[antalVeckor + 1];
        double[] medelT = new double[antalVeckor + 1];
        // koden ska skrivas här
        // visa den minsta, den största och medeltemperaturen för varje vecka
        
        for(int vecka = 1; vecka <= antalVeckor; vecka++) {
        	// Beräkna värden för alla vektorer över
        	minT[vecka] = Integer.MAX_VALUE;
            maxT[vecka] = Integer.MIN_VALUE;
            sumT[vecka] = 0;
            medelT[vecka] = 0;
        	for (int matning = 1; matning <= antalMatningarPerVecka; matning++) {
            	// Plocka ut värdet av varje inmatning
        		double v = t[vecka][matning];
            	
            	// Jämför och beräkna min, max, sum och medel.
        		if (v < minT[vecka]) minT[vecka] = v;
        		if (v > maxT[vecka]) maxT[vecka] = v;
        		sumT[vecka] += v;
            }
        	// Beräkna medel
        	medelT[vecka] = sumT[vecka] / (t[vecka].length - 1);
        	
        	// Visa värden för alla vektorer för den här veckan
        	System.out.println("--- Vecka " + vecka + " ---");
            System.out.println("Min: " + minT[vecka]);
            System.out.println("Max: " + maxT[vecka]);
            System.out.println("Summa: " + sumT[vecka]);
            System.out.println("Medel: " + medelT[vecka]);
        }
        // koden ska skrivas här
        // den minsta, den största och medeltemperaturen - hela mätperioden
        double minTemp = Integer.MAX_VALUE; // minT[1];
        double maxTemp = Integer.MIN_VALUE; // maxT[1];
        double sumTemp = 0; 				//sumT[1];
        double medelTemp = 0;
        // koden ska skrivas här
        // visa den minsta, den största och medeltemperaturen i hela mätperioden
        // koden ska skrivas här
        for(int vecka = 1; vecka <= antalVeckor; vecka++) {
        	// Jämför all mätdata
        	if (minT[vecka] < minTemp) minTemp = minT[vecka];
        	if (maxT[vecka] > maxTemp) maxTemp = maxT[vecka];
        	sumTemp += sumT[vecka];
        }
        medelTemp = sumTemp / ( antalVeckor * antalMatningarPerVecka);
        
        // Printa ut all data
        System.out.println("--- Totalt ---");
        System.out.println("Min: " + minTemp);
        System.out.println("Max: " + maxTemp);
        System.out.println("Summa: " + sumTemp);
        System.out.println("Medel: " + medelTemp);
    }
}