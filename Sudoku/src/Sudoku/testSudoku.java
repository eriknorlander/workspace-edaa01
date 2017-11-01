package Sudoku;

public class testSudoku {

	public static void main(String[] args) {
		Sudoku s = new Sudoku();
		s.put(0,1,5);
		s.put(1,0,7);
		s.put(0,3,4);
		s.put(0,0,9);
		s.put(8, 8, 1);
		s.put(5, 6, 3);
		s.put(5, 7, 4);
		
		s.display();
		System.out.println(s.solve());
		s.display();
	}

}
