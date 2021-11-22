package sudoku;

import java.util.*;

public class SudokuGenerator {
	public static SudokuBoard test  = generateRandomSudoku();
	public static Integer[][] test2 = initialize();
	public static Integer[][] testBoardSolved = SudokuSolver.solveSudoku2(test2);
	// ==============================================================================================================================
//	public static void main(String[] args) {
//		
//		test2 = initialize();
//	}
	static Integer[][] initialize(){
		Integer[][] test3 = new Integer[9][9];
		for(int i = 0; i<9; i++) {
			for(int j = 0; j<9; j++) {
				test3[i][j] = test.board[i][j];
			}
		}
		return test3;
	}
	static SudokuBoard generateRandomSudoku() {
		Integer[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		SudokuBoard puzzle = new SudokuBoard(9, 9, 3, 3, a);

		SudokuBoard copy = new SudokuBoard(puzzle);

		Random randomGenerator = new Random();

		List<Integer> notUsedValidValues = new ArrayList<Integer>(Arrays.asList(copy.getValidValues()));
		for (int r = 0; r < copy.getNumRows(); r++) {
			int randomValue = randomGenerator.nextInt(notUsedValidValues.size());
			copy.makeMove(r, 0, notUsedValidValues.get(randomValue), true);
			notUsedValidValues.remove(randomValue);
		}

		backtrackSudokuSolver(0, 0, copy);

		int numberOfValuesToKeep = (int) (0.22222 * (copy.getNumRows() * copy.getNumRows()));

		for (int i = 0; i < numberOfValuesToKeep;) {
			int randomRow = randomGenerator.nextInt(puzzle.getNumRows());
			int randomColumn = randomGenerator.nextInt(puzzle.getNumColumns());

			if (puzzle.isSlotAvailable(randomRow, randomColumn)) {
				puzzle.makeMove(randomRow, randomColumn, copy.getValue(randomRow, randomColumn), false);
				i++;
			}
		}

		return puzzle;
	}

	private static boolean backtrackSudokuSolver(int r, int c, SudokuBoard puzzle) {
		
		if (!puzzle.inRange(r, c)) {
			return false;
		}

		
		if (puzzle.isSlotAvailable(r, c)) {

			
			for (int i = 0; i < puzzle.getValidValues().length; i++) {

				
				if (!puzzle.numInRow(r, puzzle.getValidValues()[i]) && !puzzle.numInCol(c, puzzle.getValidValues()[i])
						&& !puzzle.numInBox(r, c, puzzle.getValidValues()[i])) {

					
					puzzle.makeMove(r, c, puzzle.getValidValues()[i], true);

					
					if (puzzle.boardFull()) {
						return true;
					}

				
					if (r == puzzle.getNumRows() - 1) {
						if (backtrackSudokuSolver(0, c + 1, puzzle))
							return true;
					} else {
						if (backtrackSudokuSolver(r + 1, c, puzzle))
							return true;
					}
				}
			}
		}

	
		else {
		
			if (r == puzzle.getNumRows() - 1) {
				return backtrackSudokuSolver(0, c + 1, puzzle);
			} else {
				return backtrackSudokuSolver(r + 1, c, puzzle);
			}
		}

		puzzle.makeSlotEmpty(r, c);

	
		return false;
	}

	// ==============================================================================================================================
	static boolean isEqual(Integer[][] a, Integer[][] b) {
		System.out.println("=============A===============");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-------------B---------------");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(b[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("=============================");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (a[i][j] != b[i][j]) {
					return false;
				}
			}
		}

		return true;
	}
}
