import com.sun.xml.internal.messaging.saaj.packaging.mime.util.OutputUtil;

public class ChessboardTest {

	public static void main(String[] args) throws NoSuchFieldException, InterruptedException {
		// TODO Auto-generated method stub
		Chessboard cb = new Chessboard();

		Chessboard.Chesspiece[] pieces = new Chessboard.Chesspiece[] {
				cb.new Pawn  ('w'),	// Changing from white to black changes the walking direction
				cb.new Rook  ('b'),
				cb.new Queen ('w'),
				cb.new Bishop('w'),
				cb.new King  ('b'),
				cb.new Knight('w')
		};
		
		for (int i = 0; i < pieces.length; i++) {
			pieces[i].moveToRandom();
			pieces[i].markReachableFields();
			System.out.println(cb);
			Thread.sleep(1 * 1000);
			pieces[i].unmarkReachableFields();
			pieces[i].moveOut();
			
			/*
			 *  Alla pj�ser kan presenteras och anv�ndas i en och samma loop
			 *  p� grund av att de alla �rver fr�n en abstrakt klass, Chesspiece.
			 *  Den klassen kan inte instansieras av sig sj�lv, utan andra klasser
			 *  kan bara "extend", ut�ka denna klass och implementera abstrakta
			 *  metoder i Chesspiece. Detta g�r att man kan skapa olika klasser som
			 *  �rver fr�n en om samma huvudklass och kan hanteras eller behandlas
			 *  som den huvudklassen d� de alla har samma egenskaper som kommer fr�n
			 *  huvudklassen.
			 *  Eftersom vi skapade en vektor av sorten Chesspiece, kan vi stoppa in alla
			 *  klasser som �rver utav den klassen, Chesspiece.
			 */
		}
		System.out.println("Done!");
	}

}
