import java.util.*; // Scanner
import static java.lang.System.out;

public class OperationerMedNaturligaHeltalGivnaSomTeckenstrangar {
    public static void main(String[] args) {
        out.println("OPERATIONER MED NATURLIGA HELTAL GIVNA SOM TECKENSTRANGAR\n");
        // mata in två naturliga heltal
        Scanner in = new Scanner(System.in);
        out.println("två naturliga heltal:");
        String tal1 = in .next();
        String tal2 = in .next();
        out.println();
        // addera heltalen och visa resultatet
        // visa(tal1, tal2, addera(tal1, tal2), '+');
        // subtrahera heltalen och visa resultatet
        visa(tal1, tal2, subtrahera(tal1, tal2), '-');
    }
    // addera tar emot två naturliga heltal givna som teckensträngar, och returnerar deras
    // summa som en teckensträng.
    public static String addera(String tal1, String tal2) {
        // koden ska skrivas här
    	int t1l = tal1.length();
    	int t2l = tal2.length();
    	int carry = 0;
    	
    	String result = "";
    	
    	int times = Math.max(t1l, t2l) + 2;
    	for(int i = 1; i < times; i++) {
    		int rtli1 = t1l - i; // Right to Left Index for tal 1
    		int rtli2 = t2l - i; // Right to Left Index for tal 2
    		int num1 = 0;
    		int num2 = 0;
    		
    		if (rtli1 > -1 && rtli1 < t1l) num1 = Character.getNumericValue(tal1.charAt(rtli1));
    		if (rtli2 > -1 && rtli2 < t2l) num2 = Character.getNumericValue(tal2.charAt(rtli2));

    		int num = (num1 + num2 + carry) % 10;
    		carry   = (num1 + num2 + carry) / 10;

    		//System.out.print(i + " " + rtli1 + " " + rtli2 + " | ");
    		//System.out.println(num1 + " + " + num2 + " \t-> Carry: " + carry + ", Number: " + num);

    		if (i == times - 1 && num == 0) continue;
    		result = num + result;
    	}
    	return result;
    }
    // subtrahera tar emot två naturliga heltal givna som teckensträngar, och returnerar
    // deras differens som en teckensträng.
    // Det första heltalet är inte mindre än det andra heltalet.
    public static String subtrahera(String tal1, String tal2) {
        // koden ska skrivas här
    	int l1 = tal1.length();
    	int l2 = tal2.length();
    	int maxLen = Math.max(l1, l2);
    	String result = "";
    	int carry = 0;
    	int mult = 1;
    	for(int i = 0; i < maxLen; i++) {
    		int num1 = 0;
    		int num2 = 0;
    		if (i < l1) num1 = Character.getNumericValue(tal1.charAt(l1 - 1 - i));
    		if (i < l2) num2 = Character.getNumericValue(tal2.charAt(l2 - 1 - i));
    		
    		int diff = 0;
    		
    		out.print(num1 * mult + " - " + num2 * mult + " + " + carry);
    		
    		if (num1 < num2) {
    			num1 += 10;
    			diff = (num1 - num2) + carry;
    			carry = -1;
    		}
    		else {
    			diff = (num1 - num2) + carry;
    			carry = 0;
    		}
    		
    		out.print("\t= " + diff + " carry " + carry);
    		
    		out.println();
    		
    		if (i == maxLen - 1 && diff == 0) break;
    		result = diff + result;
    		mult *= 10;
    	}
    	
    	
    	return result;
    }
    // visa visar två givna naturliga heltal, och resultatet av en aritmetisk operation
    // utförd i samband med hetalen
    public static void visa(String tal1, String tal2, String resultat, char operator) {
        // sätt en lämplig längd på heltalen och resultatet
        int len1 = tal1.length();
        int len2 = tal2.length();
        int len = resultat.length();
        int maxLen = Math.max(Math.max(len1, len2), len);
        tal1 = sattLen(tal1, maxLen - len1);
        tal2 = sattLen(tal2, maxLen - len2);
        resultat = sattLen(resultat, maxLen - len);
        // visa heltalen och resultatet
        out.println("  " + tal1);
        out.println("" + operator + " " + tal2);
        for (int i = 0; i < maxLen + 2; i++)
            out.print("-");
        out.println();
        out.println("  " + resultat + "\n");
    }
    // sattLen lägger till ett angivet antal mellanslag i början av en given sträng
    public static String sattLen(String s, int antal) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < antal; i++)
            sb.insert(0, " ");
        return sb.toString();
    }
}