import java.util.Random;

public class Chessboard {
	// Kan skapas som en egen class, men det skapas ett annat context ifall
	// Field ligger i Chessboard eftersom då vet man att det är ett fält på
	// Ett chackbräde
    public static class Field {
        private char row;
        private byte column;
        private Chesspiece piece = null;
        private boolean marked = false;
        
        public Field(char row, byte column) {
        	this.row = row;
        	this.column = column;
        }
        
        public void put(Chesspiece piece) {
        	this.piece = piece;
        }
        public Chesspiece take() {
        	Chesspiece p = this.piece;
        	this.piece = null;
        	return p;
        }
        public void mark() { this.marked = true; }
        public void unmark() { this.marked = false; }
        
        public String toString() {
            String s = marked ? "xx" : "--";
            return (piece == null) ? s : piece.toString();
        }
    }
    
    public static final int NUMBER_OF_ROWS = 8;
    public static final int NUMBER_OF_COLUMNS = 8;
    public static final int FIRST_ROW = 'a';
    public static final int FIRST_COLUMN = 1;
    
    private Field[][] fields;
    
    public Chessboard() {
        fields = new Field[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
        char row = 0;
        byte column = 0;
        for (int r = 0; r < NUMBER_OF_ROWS; r++) {
            row = (char)(FIRST_ROW + r);
            column = FIRST_COLUMN;
            for (int c = 0; c < NUMBER_OF_COLUMNS; c++) {
                fields[r][c] = new Field(row, column);
                column++;
            }
        }
    }
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	
    	String columns = "  ";
    	for (int i = 1; i <= NUMBER_OF_COLUMNS; i++) {
    		columns += i + "  ";
    	}
    	sb.append(columns + "\n");
    	
    	String row = "";
    	for(int i = 1; i <= NUMBER_OF_ROWS; i++) {
    		row += fields[i-1][0].row + " ";
    		
    		for (int j = 1; j <= NUMBER_OF_COLUMNS; j++) {
        		row += fields[i-1][j-1].toString() + " ";
        	}
        	sb.append(row + "\n");
        	row = "";
    	}
    	
    	return sb.toString();
    }
    public boolean isValidField(char row, byte column) {
    	try {
    		return fields[row][column] != null;
    	}
    	catch(ArrayIndexOutOfBoundsException e) {
    		return false;
    	}
    }

    public char toFieldR(char row) { return (char)(row - FIRST_ROW); }
    public char toFieldR(int row) { return (char)(row - FIRST_ROW); }
    public byte toFieldC(byte column) { return (byte)(column - FIRST_COLUMN); }
    public byte toFieldC(int column) { return (byte)(column - FIRST_COLUMN); }
    
    public boolean isValidField(char row, int column) { return isValidField(row, (byte)column); }
    
    // Chackpjäserna måste skapas som en inre class till Chessboard eftersom de är beroende
    // Av data och metoder som är privata i Chessboard.
    public abstract class Chesspiece {
        protected char color;
        // w - white, b - black
        private char name;
        // K - King, Q - Queen, R - Rook, B - Bishop, N - Knight,
        // P – Pawn
        protected char row = 0;
        protected byte column = -1;
        protected Chesspiece(char color, char name) {
        	this.color = color;
        	this.name = name;
        }
        public String toString() {
            return "" + color + name;
        }
        public boolean isOnBoard() {
            return Chessboard.this.isValidField(toFieldR(row), toFieldC(column));
        }
        public void moveTo(char row, byte column) throws NoSuchFieldException {
            if (!Chessboard.this.isValidField(toFieldR(row), toFieldC(column)))
                throw new NoSuchFieldException("bad field: " + row + column);
            this.row = row;
            this.column = column;
            int r = toFieldR(row);
            int c = toFieldC(column);
            Chessboard.this.fields[r][c].put(this); // Take first?
        }
        public void moveTo(char row, int column) throws NoSuchFieldException { moveTo(row, (byte)column); }
        public void moveOut() { Chessboard.this.fields[row - FIRST_ROW][column - FIRST_COLUMN].take(); this.row = 0; this.column = -1; }
        public void moveToRandom() throws NoSuchFieldException {
        	Random rnd = new Random();
        	moveTo((char)(rnd.nextInt(NUMBER_OF_ROWS) + FIRST_ROW), rnd.nextInt(NUMBER_OF_COLUMNS - 1) + 1);
        }
        
        // Skulle jag skrivit det här programmet, så skulle
        // jag bara ha en metod för att markera eller avmarkera
        // fält på brädan.
        // Ex: markReachableFields(boolean status)
        public abstract void markReachableFields();
        public abstract void unmarkReachableFields();
    }
    
    // Bonde
    public class Pawn extends Chesspiece {
        public Pawn(char color) {
            super(color, 'P');
        }
        public void markReachableFields() {
        	int dir = color == 'w' ? 1 : -1;
            if (Chessboard.this.isValidField(toFieldR(row + dir), toFieldC(column))) {
                Chessboard.this.fields[toFieldR(row + dir)][toFieldC(column)].mark();
            }
        }
        public void unmarkReachableFields() {
        	int dir = color == 'w' ? 1 : -1;
        	if (Chessboard.this.isValidField(toFieldR(row + dir), toFieldC(column))) {
                Chessboard.this.fields[toFieldR(row + dir)][toFieldC(column)].unmark();
            }
        }
    }
    
    // Torn
    public class Rook extends Chesspiece {
    	public Rook(char color) {
            super(color, 'R');
        }
        public void markReachableFields() {
            for(char r = 0; r < NUMBER_OF_COLUMNS; r++) {
            	if (Chessboard.this.isValidField(r, toFieldC(column))) {
                    Chessboard.this.fields[r][toFieldC(column)].mark();
                }
            }
            for(byte c = 1; c <= NUMBER_OF_ROWS; c++) {
            	if (Chessboard.this.isValidField(toFieldR(row), toFieldC(c))) {
                    Chessboard.this.fields[toFieldR(row)][toFieldC(c)].mark();
                }
            }
        }
        public void unmarkReachableFields() {
            for(char r = 0; r < NUMBER_OF_COLUMNS; r++) {
            	if (Chessboard.this.isValidField(r, toFieldC(column))) {
                    Chessboard.this.fields[r][toFieldC(column)].unmark();
                }
            }
            for(byte c = 1; c <= NUMBER_OF_ROWS; c++) {
            	if (Chessboard.this.isValidField(toFieldR(row), toFieldC(c))) {
                    Chessboard.this.fields[toFieldR(row)][toFieldC(c)].unmark();
                }
            }
        }
    }
    
    // Häst
    public class Knight extends Chesspiece {
    	public Knight(char color) {
            super(color, 'N');
        }
        public void markReachableFields() {
        	int[][] slots = new int [8][2];
        	slots[0] = new int[] { -2, 1 };
        	slots[1] = new int[] { -2, -1 };
        	slots[2] = new int[] { 2, 1 };
        	slots[3] = new int[] { 2, -1 };
        	slots[4] = new int[] { 1, 2 };
        	slots[5] = new int[] { 1, -2 };
        	slots[6] = new int[] { -1, 2 };
        	slots[7] = new int[] { -1, -2 };
        	
        	for (int i = 0; i < slots.length; i++) {
            	if (Chessboard.this.isValidField(toFieldR(row + slots[i][0]), toFieldC(column + slots[i][1]))) {
                    Chessboard.this.fields[toFieldR(row + slots[i][0])][toFieldC(column + slots[i][1])].mark();
                }
        	}
        }
        public void unmarkReachableFields() {
        	int[][] slots = new int [8][2];
        	slots[0] = new int[] { -2, 1 };
        	slots[1] = new int[] { -2, -1 };
        	slots[2] = new int[] { 2, 1 };
        	slots[3] = new int[] { 2, -1 };
        	slots[4] = new int[] { 1, 2 };
        	slots[5] = new int[] { 1, -2 };
        	slots[6] = new int[] { -1, 2 };
        	slots[7] = new int[] { -1, -2 };
        	
        	for (int i = 0; i < slots.length; i++) {
            	if (Chessboard.this.isValidField(toFieldR(row + slots[i][0]), toFieldC(column + slots[i][1]))) {
                    Chessboard.this.fields[toFieldR(row + slots[i][0])][toFieldC(column + slots[i][1])].unmark();
                }
        	}
        }
    }
    
    // Löpare
    public class Bishop extends Chesspiece {
    	public Bishop(char color) {
            super(color, 'B');
        }
        public void markReachableFields() {
            for(int a = 0; a < 4;a++) {
            	double angle = a * Math.PI / 2 + Math.PI / 4;
            	int dist = (int)( Math.sqrt(NUMBER_OF_COLUMNS * NUMBER_OF_COLUMNS + NUMBER_OF_ROWS * NUMBER_OF_ROWS) );
            	for(int d = 1; d < dist; d++) {
            		int r = (int)(Math.cos(angle) * d) + row;
            		int c = (int)(Math.sin(angle) * d) + column;
            		if (Chessboard.this.isValidField(toFieldR(r), toFieldC(c))) {
                        Chessboard.this.fields[toFieldR(r)][toFieldC(c)].mark();
                    }
            	}
            }
        }
        public void unmarkReachableFields() {
        	for(int a = 0; a < 4;a++) {
            	double angle = a * Math.PI / 2 + Math.PI / 4;
            	int dist = (int)( Math.sqrt(NUMBER_OF_COLUMNS * NUMBER_OF_COLUMNS + NUMBER_OF_ROWS * NUMBER_OF_ROWS) );
            	for(int d = 1; d < dist; d++) {
            		int r = (int)(Math.cos(angle) * d) + row;
            		int c = (int)(Math.sin(angle) * d) + column;
            		if (Chessboard.this.isValidField(toFieldR(r), toFieldC(c))) {
                        Chessboard.this.fields[toFieldR(r)][toFieldC(c)].unmark();
                    }
            	}
            }
        }
    }
    
    // Dam
    public class Queen extends Chesspiece {
    	public Queen(char color) {
            super(color, 'Q');
        }
        public void markReachableFields() {
        	// Bishop moves
        	for(int a = 0; a < 4;a++) {
            	double angle = a * Math.PI / 2 + Math.PI / 4;
            	int dist = (int)( Math.sqrt(NUMBER_OF_COLUMNS * NUMBER_OF_COLUMNS + NUMBER_OF_ROWS * NUMBER_OF_ROWS) );
            	for(int d = 1; d < dist; d++) {
            		int r = (int)(Math.cos(angle) * d) + row;
            		int c = (int)(Math.sin(angle) * d) + column;
            		if (Chessboard.this.isValidField(toFieldR(r), toFieldC(c))) {
                        Chessboard.this.fields[toFieldR(r)][toFieldC(c)].mark();
                    }
            	}
            }
        	// Rook moves
        	for(char r = 0; r < NUMBER_OF_COLUMNS; r++) {
            	if (Chessboard.this.isValidField(r, toFieldC(column))) {
                    Chessboard.this.fields[r][toFieldC(column)].mark();
                }
            }
            for(byte c = 1; c <= NUMBER_OF_ROWS; c++) {
            	if (Chessboard.this.isValidField(toFieldR(row), toFieldC(c))) {
                    Chessboard.this.fields[toFieldR(row)][toFieldC(c)].mark();
                }
            }
        }
        public void unmarkReachableFields() {
        	// Bishop moves
        	for(int a = 0; a < 4;a++) {
            	double angle = a * Math.PI / 2 + Math.PI / 4;
            	int dist = (int)( Math.sqrt(NUMBER_OF_COLUMNS * NUMBER_OF_COLUMNS + NUMBER_OF_ROWS * NUMBER_OF_ROWS) );
            	for(int d = 1; d < dist; d++) {
            		int r = (int)(Math.cos(angle) * d) + row;
            		int c = (int)(Math.sin(angle) * d) + column;
            		if (Chessboard.this.isValidField(toFieldR(r), toFieldC(c))) {
                        Chessboard.this.fields[toFieldR(r)][toFieldC(c)].unmark();
                    }
            	}
            }
        	// Rook moves
        	for(char r = 0; r < NUMBER_OF_COLUMNS; r++) {
            	if (Chessboard.this.isValidField(r, toFieldC(column))) {
                    Chessboard.this.fields[r][toFieldC(column)].unmark();
                }
            }
            for(byte c = 1; c <= NUMBER_OF_ROWS; c++) {
            	if (Chessboard.this.isValidField(toFieldR(row), toFieldC(c))) {
                    Chessboard.this.fields[toFieldR(row)][toFieldC(c)].unmark();
                }
            }
        }
    }
    
    // Kung
    public class King extends Chesspiece {
    	public King(char color) {
            super(color, 'K');
        }
        public void markReachableFields() {
        	int[][] slots = new int [4][2];
        	slots[0] = new int[] { 0, 1 };
        	slots[1] = new int[] { 0, -1 };
        	slots[2] = new int[] { 1, 0 };
        	slots[3] = new int[] { -1, 0 };
        	
        	for (int i = 0; i < slots.length; i++) {
            	if (Chessboard.this.isValidField(toFieldR(row + slots[i][0]), toFieldC(column + slots[i][1]))) {
                    Chessboard.this.fields[toFieldR(row + slots[i][0])][toFieldC(column + slots[i][1])].mark();
                }
        	}
        }
        public void unmarkReachableFields() {
        	int[][] slots = new int [4][2];
        	slots[0] = new int[] { 0, 1 };
        	slots[1] = new int[] { 0, -1 };
        	slots[2] = new int[] { 1, 0 };
        	slots[3] = new int[] { -1, 0 };
        	
        	for (int i = 0; i < slots.length; i++) {
            	if (Chessboard.this.isValidField(toFieldR(row + slots[i][0]), toFieldC(column + slots[i][1]))) {
                    Chessboard.this.fields[toFieldR(row + slots[i][0])][toFieldC(column + slots[i][1])].unmark();
                }
        	}
        }
    }
}