import static java.lang.System.out;

public class MinstaHeltalet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		out.println("Minsta heltalet: " + min(new int[] { 5, 2, 8, 0, -12, 9999999, 453 }));
		out.println("Minsta heltalet: " + min2(new int[] { 5, 2, 8, 0, -12, 9999999, 453 }));
		out.println("Minsta heltalet: " + min(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
		out.println("Minsta heltalet: " + min2(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
	}

	public static int min(int[] element) throws IllegalArgumentException {
	    if (element.length == 0)
	        throw new IllegalArgumentException("tom samling");
	    // h�r ihop med sp�rutskriften 2:
	    // int antalVarv = 1;
	    int[] sekvens = element;
	    int antaletPar = sekvens.length / 2;
	    int antaletOparadeElement = sekvens.length % 2;
	    int antaletTankbaraElement = antaletPar + antaletOparadeElement;
	    int[] delsekvens = new int[antaletTankbaraElement];
	    int i = 0;
	    int j = 0;
    	// out.println(java.util.Arrays.toString(element));
	    while (sekvens.length > 1) {
	        // skilj ur en delsekvens med de t�nkbara elementen
	        i = 0;
	        j = 0;
	        while (j < antaletPar) {
	            delsekvens[j++] = (sekvens[i] < sekvens[i + 1]) ? sekvens[i] : sekvens[i + 1];
	            i += 2;
	        }
	        if (antaletOparadeElement == 1)
	            delsekvens[j] = sekvens[sekvens.length - 1];
	        // utg� nu ifr�n delsekvensen
	        sekvens = delsekvens;
	        antaletPar = antaletTankbaraElement / 2;
	        antaletOparadeElement = antaletTankbaraElement % 2;
	        antaletTankbaraElement = antaletPar + antaletOparadeElement;

	    	// out.println(java.util.Arrays.toString(delsekvens));
	        delsekvens = new int[antaletTankbaraElement]; // Den h�r raden saknades
	        
	        // sp�rutskrift 1 � f�r att f�lja sekvensen
	        // System.out.println (java.util.Arrays.toString (sekvens));
	        // sp�rutskrift 2 - f�r att avsluta loopen i f�rv�g
	        // (f�r att kunna se vad som h�nder i b�rjan)
	        // if (antalVarv++ == 10)
	        // System.exit (0);
	    }
	    // sekvens[0] �r det enda �terst�ende t�nkbara elementet
	    // - det �r det minsta elementet
	    return sekvens[0];
	}
	
	public static int min2(int[] element) {
		int min = element[0];
		for(int i = 1; i < element.length; i++) if (element[i] < min) min = element[i];
		return min;
	}
}