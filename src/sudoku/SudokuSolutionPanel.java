package sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SudokuSolutionPanel extends JPanel {
//	Integer[][] board = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 }, { 6, 0, 0, 1, 9, 5, 0, 0, 0 }, { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
//			{ 8, 0, 0, 0, 6, 0, 0, 0, 3 }, { 4, 0, 0, 8, 0, 3, 0, 0, 1 }, { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
//			{ 0, 6, 0, 0, 0, 0, 2, 8, 0 }, { 0, 0, 0, 4, 1, 9, 0, 0, 5 }, { 0, 0, 0, 0, 8, 0, 0, 7, 9 } };



	private static final long serialVersionUID = 1L;

	SudokuSolutionPanel() {
		this.setBackground(Color.black);
		this.setBounds(200, 100, 600, 600); // setLayout

		ArrayList<JPanel> grid = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			grid.add(new JPanel());
			grid.get(i).setLayout(new GridLayout(3, 3, 2, 2));
			grid.get(i).setBackground(Color.GRAY);

			for (Integer j = 0; j < 9; j++) {
				JLabel lblField = new JLabel(SudokuGenerator.testBoardSolved[i][j].toString());
				lblField.setHorizontalAlignment(JLabel.CENTER);
				lblField.setFont(new Font("Arial Bold", Font.BOLD, 20));
				grid.get(i).add(lblField);
			}
			this.add(grid.get(i));
		}
		this.setLayout(new GridLayout(3, 3, 5, 5));
	}
}
