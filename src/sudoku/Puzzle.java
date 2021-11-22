package sudoku;

public class Puzzle extends SudokuBoard{
	public Puzzle(Puzzle puzzle) {
		super(puzzle);
		
	}
	int rows = 9;
	int columns = 9;
	int boxWidth = 3;
	int boxHeight = 3;
	Integer[] validValues = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	public int getNumRows() {
		
		return this.rows;
	}
	public int getNumColumns() {
		
		return this.columns;
	}
	
}
