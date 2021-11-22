package sudoku;

import java.util.Scanner;

public class SudokuSolver {
	private static final int BOARD_BOUNDARY = 9;
	public static Integer[][] board = SudokuGenerator.test.board;
	
//	public static Integer[][] board = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
//										{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
//										{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//										{ 3, 8, 4, 0, 0, 0, 0, 0, 0 }, 
//										{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
//										{ 0, 0, 0, 0, 0, 0, 0, 0, 6 },
//										{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
//										{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
//										{ 0, 0, 0, 0, 0, 0, 0, 0, 2 } };
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Before:");
		printSudoku(board);
		if (solveSudoku(board)) {
			System.out.println("After:");
			printSudoku(board);

		} else {
			System.out.println("Not Solvable");
		}
		solveSudoku(board);

		scanner.close();
	}

	static void printSudoku(Integer[][] board) {
		for (int i = 0; i < BOARD_BOUNDARY; i++) {
			if (i % 3 == 0 && i != 0) {
				System.out.println("-------------------");
			}
			for (int j = 0; j < BOARD_BOUNDARY; j++) {
				if (j % 3 == 0 && j != 0) {
					System.out.print("|");
				}
				System.out.print(board[i][j] + " ");
			}
			System.out.println("");

		}
		System.out.println();
	}

	private static boolean isInRow(Integer[][] board, int number, int row) {

		for (int i = 0; i < BOARD_BOUNDARY; i++) {
			if (board[row][i] == number) {
				return true;
			}
		}

		return false;
	}

	private static boolean isInColumn(Integer[][] board, int number, int column) {
		for (int i = 0; i < BOARD_BOUNDARY; i++) {
			if (board[i][column] == number) {
				return true;
			}
		}

		return false;
	}

	private static boolean isInGrid(Integer[][] board, int number, int row, int column) {
		int firstRow = row - row % 3;
		int firstColumn = column - column % 3;

		for (int i = firstRow; i < firstRow + 3; i++) {
			for (int j = firstColumn; j < firstColumn + 3; j++) {
				if (board[i][j] == number) {
					return true;
				}
			}
		}

		return false;
	}

	private static boolean isValid(Integer[][] board, int number, int row, int column) {
		return !isInRow(board, number, row) && !isInColumn(board, number, column)
				&& !isInGrid(board, number, row, column);
	}

	static boolean solveSudoku(Integer[][] board) {

		for (int row = 0; row < BOARD_BOUNDARY; row++) {

			for (int column = 0; column < BOARD_BOUNDARY; column++) {

				if (board[row][column] == 0) {
					for (int number = 1; number <= BOARD_BOUNDARY; number++) {

						if (isValid(board, number, row, column)) {
							board[row][column] = number;

							if (solveSudoku(board)) {
								
								return true;
							} else {
								board[row][column] = 0;
							}
						}

					}
					return false;
				}

			}

		}
		return true;
	}
	
	static Integer[][] solveSudoku2(Integer[][] board) {

		for (int row = 0; row < BOARD_BOUNDARY; row++) {

			for (int column = 0; column < BOARD_BOUNDARY; column++) {

				if (board[row][column] == 0) {
					for (int number = 1; number <= BOARD_BOUNDARY; number++) {

						if (isValid(board, number, row, column)) {
							board[row][column] = number;

							if (solveSudoku(board)) {
								
								return board;
							} else {
								board[row][column] = 0;
							}
						}

					}
					return board;
				}

			}

		}
		return board;
	}

}
