package sudoku;

public class SudokuBoard {
	protected Integer[][] board;

	protected boolean[][] mutable;
	private final int ROWS;
	private final int COLUMNS;
	private final int BOXWIDTH;
	private final int BOXHEIGHT;
	private final Integer[] VALIDVALUES;

	public SudokuBoard(int rows, int columns, int boxWidth, int boxHeight, Integer[] validValues) {
		this.ROWS = rows;
		this.COLUMNS = columns;
		this.BOXWIDTH = boxWidth;
		this.BOXHEIGHT = boxHeight;
		this.VALIDVALUES = validValues;
		this.board = new Integer[ROWS][COLUMNS];
		this.mutable = new boolean[ROWS][COLUMNS];
		initializeBoard();
		initializeMutableSlots();
	}
	public SudokuBoard(SudokuBoard puzzle) {
		this.ROWS = puzzle.ROWS;
		this.COLUMNS = puzzle.COLUMNS;
		this.BOXWIDTH = puzzle.BOXWIDTH;
		this.BOXHEIGHT = puzzle.BOXHEIGHT;
		this.VALIDVALUES = puzzle.VALIDVALUES;
		this.board = new Integer[ROWS][COLUMNS];
		this.mutable = new boolean[ROWS][COLUMNS];
		initializeBoard();
		initializeMutableSlots();
	}
	
	public Integer getValue(int row,int col) {
		if(this.inRange(row,col)) {
			return this.board[row][col];
		}
		return 0;
	}
	
	
	public Integer[][] getBoard() {
		return board;
	}



	public void setBoard(Integer[][] board) {
		this.board = board;
	}



	public boolean[][] getMutable() {
		return mutable;
	}



	public void setMutable(boolean[][] mutable) {
		this.mutable = mutable;
	}



	public int getNumRows() {
		return ROWS;
	}

	public void makeMove(int row, int col, Integer value, boolean isMutable) {
		if (this.isValidValue(value) && this.isValidMove(row, col, value) && this.isSlotMutable(row, col)) {
			this.board[row][col] = value;
			this.mutable[row][col] = isMutable;
		}
	}

	public boolean isValidMove(int row, int col, Integer value) {
		if (this.inRange(row, col)) {
			if (!this.numInCol(col, value) && !this.numInRow(row, value) && !this.numInBox(row, col, value)) {
				return true;
			}
		}
		return false;
	}

	public boolean isSlotMutable(int row, int col) {
		return this.mutable[row][col];
	}

	private boolean isValidValue(Integer value) {
		for (Integer str : this.VALIDVALUES) {
			if (str.equals(value))
				return true;
		}
		return false;
	}

	public boolean numInCol(int col, Integer value) {
		if (col <= this.COLUMNS) {
			for (int row = 0; row < this.ROWS; row++) {
				if (this.board[row][col].equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean numInRow(int row, Integer value) {
		if (row <= this.ROWS) {
			for (int col = 0; col < this.COLUMNS; col++) {
				if (this.board[row][col].equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean numInBox(int row, int col, Integer value) {
		if (this.inRange(row, col)) {
			int boxRow = row / this.BOXHEIGHT;
			int boxCol = col / this.BOXWIDTH;

			int startingRow = (boxRow * this.BOXHEIGHT);
			int startingCol = (boxCol * this.BOXWIDTH);

			for (int r = startingRow; r <= (startingRow + this.BOXHEIGHT) - 1; r++) {
				for (int c = startingCol; c <= (startingCol + this.BOXWIDTH) - 1; c++) {
					if (this.board[r][c].equals(value)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean inRange(int row, int col) {
		return row <= this.ROWS && col <= this.COLUMNS && row >= 0 && col >= 0;
	}

	
	public int getNumColumns() {
		return COLUMNS;
	}

	
	public int getBOXWIDTH() {
		return BOXWIDTH;
	}

	
	public int getBOXHEIGHT() {
		return BOXHEIGHT;
	}

	
	public Integer[] getValidValues() {
		return VALIDVALUES;
	}

	private void initializeBoard() {
		for (int row = 0; row < this.ROWS; row++) {
			for (int col = 0; col < this.COLUMNS; col++) {
				this.board[row][col] = 0;
			}
		}
	}
	public void makeSlotEmpty(int row,int col) {
		this.board[row][col] = 0;
	}

	public boolean boardFull() {
		for (int r = 0; r < this.ROWS; r++) {
			for (int c = 0; c < this.COLUMNS; c++) {
				if (this.board[r][c] == 0)
					return false;
			}
		}
		return true;
	}

	public boolean isSlotAvailable(int row, int col) {
		return (this.inRange(row, col) && this.board[row][col] == 0 && this.isSlotMutable(row, col));
	}

	private void initializeMutableSlots() {
		for (int row = 0; row < this.ROWS; row++) {
			for (int col = 0; col < this.COLUMNS; col++) {
				this.mutable[row][col] = true;
			}
		}
	}
}
