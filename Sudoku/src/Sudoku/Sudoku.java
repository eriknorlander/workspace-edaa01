package Sudoku;

public class Sudoku {
private Integer[][] sudoku;
	
	public Sudoku() {
		sudoku = new Integer[9][9];
		
		for (int i = 0; i<9; i++) {
			for (int j=0; j<9;j++) {
				sudoku[i][j] = 0;
			}
		}
	}
	/** Ett test före testet.
	 * */
	public void display() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<9;i++) {
			for (int j = 0; j<9;j++) {
				sb.append(sudoku[i][j] + " ");
			}
			sb.append(System.lineSeparator());
		}
		System.out.print(sb);
	}
	/** Sätter in ett värde i en koordinat. 
	 * @param x-värde
	 * @param y-värde
	 * @param värde 
	 * */
	public void put(int x,int y, int value) {
		if (x>=0 && x <9 && y>=0 && y<9 && value>= 0 && value <10) {
			sudoku[x][y] = value;
		}
	}
	/** Returnerar ett värde i en koordinat.
	 * @param x-värde
	 * @param y-värde
	 * @return värde 
	 * */
	public int get(int x, int y) {
		if (x>=0 && x <9 && y>=0 && y<9) {
			return sudoku[x][y];
		} else {
			return 0;
		}
	}
	
	/**Returnerar true om regionen INTE innehåller testValue.
	 * @param x-värde
	 * @param y-värde
	 * @param testValue
	 * @return true om regionen inte innehåller värdet, false annars*/
	private boolean checkRegion(int x, int y, int testValue) {
		int xtemp = (x/3)*3;
		int ytemp = (y/3)*3;
	
		for (int i = 0; i <3; i++) {
			for (int j = 0; j <3; j++) {
				if (!(i+xtemp == x && j+ytemp == y)) {	 
					if (get(i+xtemp,j+ytemp)==testValue ) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**Returnerar true om raden eller kolumnen INTE innehåller testValue.
	 * @param x-värde
	 * @param y-värde
	 * @param testValue
	 * @return true om raden inte innehåller värdet, false annars*/
	private boolean checkRowsAndCols(int x,int y, int testValue) {
		for (int i = 0; i < 9; i++) {
			if (i != y) {
				if (get(x,i) == testValue) {
					return false;
				}
			}
			if (i != x) {
				if (get(i,y) == testValue) {
					return false;
				}
			}
		}
		return true;
	}
	private boolean check(int x, int y, int testValue) {
		if (checkRegion(x,y,testValue) && checkRowsAndCols(x,y,testValue)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/** Löser sudokun enligt reglerna för spelet.
	 * @return True om det gick att lösa annars false. 
	 * */
	public boolean solve() {
		return solve(0,0);
	}
	/** Vår rekursiva solve-metod.*/
	private boolean solve(int x, int y) {
		
		if (x > 8) {
			return true;
		}
		
		if (get(x,y) == 0) { 
			for(int testValue = 1; testValue<10;testValue++) { 
				
				if (check(x,y,testValue)) {
					put(x,y, testValue);
					if (y==8) {
						if (x==8) {
							return true;
						}
						if (solve(x+1,0)) {
							return true;
						}
					} else {
						if (solve(x,y+1)) {
							return true;
						}
					}
				}	
			} 
			put(x,y,0);
			return false; 
		} 
		else if (get(x,y)!= 0) { 
			int testValue = get(x,y); 
			if (check(x,y,testValue)) { 
				if (y==8) {
					return solve(x+1,0);
				} else {
					return solve(x,y+1);
				}
			}
		}
		return false;
	}
	/** Rensar sudokun. */
	public void clear() {
		for (int i = 0; i<9; i++) {
			for (int j=0; j<9;j++) {
				sudoku[i][j] = 0;  
			}
		}
	}
	/** Byter ut sudokun.
	 * @param input[][] som är en ny uppsättning */
	public void setBoard(int input[][]) {
		clear();
		for (int row = 0; row < input[0].length; row++) {
			for (int col = 0; col < input.length; col++) {
				sudoku[row][col] = input[row][col];
			}
		}
	}
	
}
