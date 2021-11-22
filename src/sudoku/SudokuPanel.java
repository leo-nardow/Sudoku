package sudoku;

import java.awt.*;
import java.util.*;

import javax.swing.*;

public class SudokuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	SudokuBoard test  =  SudokuGenerator.generateRandomSudoku();
	
//	Integer[][] board = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 }, { 6, 0, 0, 1, 9, 5, 0, 0, 0 }, { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
//						  { 8, 0, 0, 0, 6, 0, 0, 0, 3 }, { 4, 0, 0, 8, 0, 3, 0, 0, 1 }, { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
//						  { 0, 6, 0, 0, 0, 0, 2, 8, 0 }, { 0, 0, 0, 4, 1, 9, 0, 0, 5 }, { 0, 0, 0, 0, 8, 0, 0, 7, 9 } };
	public static ArrayList<JPanel> grid;

	SudokuPanel() {

		this.setBounds(200, 100, 600, 600);

		grid = new ArrayList<>();
		
		for (int i = 0; i < 9; i++) {
			grid.add(new JPanel());
			grid.get(i).setLayout(new GridLayout(1, 9));
			grid.get(i).setBackground(Color.GRAY);

			for (Integer j = 0; j < 9; j++) {
				if (test.board[i][j] == 0) {
					JTextField txtField = new JTextField();
					txtField.setFont(new Font("Arial Bold", Font.BOLD, 20));
					txtField.setHorizontalAlignment(JTextField.CENTER);
					grid.get(i).add(txtField);
				} else {
					JLabel lblField = new JLabel(test.board[i][j].toString());
					lblField.setHorizontalAlignment(JLabel.CENTER);
					lblField.setFont(new Font("Arial Bold", Font.BOLD, 20));
					grid.get(i).add(lblField);
				}

			}
			this.add(grid.get(i));
		}
		this.setLayout(new GridLayout(9, 3));
	}

	public static boolean checkSudoku() {
		Integer[][] test = new Integer[9][9];
		Integer[][] test2 = new Integer[9][9];
		for (int i = 0; i < 9; i++) {

			JPanel a = grid.get(i);

			for (Integer j = 0; j < 9; j++) {
				if (a.getComponent(j).getClass() == JLabel.class) {
					JLabel c = (JLabel) a.getComponent(j);
					test[i][j] = Integer.parseInt(c.getText());

				} else if (a.getComponent(j).getClass() == JTextField.class) {
					JTextField b = (JTextField) a.getComponent(j);
					if(b.getText().equals("")) {
						test[i][j] = 0;
					}else {
						test[i][j] = Integer.parseInt(b.getText());
					}	
				}
			}
		}
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(test[i][j] + " ");
			}
			System.out.println();
		}
		
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				test2[i][j] = test[i][j];
			}
			
		}
		
		test2 = SudokuSolver.solveSudoku2(test2);
		return SudokuGenerator.isEqual(test, test2);
	}
}
