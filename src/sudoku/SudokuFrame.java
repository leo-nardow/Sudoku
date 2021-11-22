package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SudokuFrame extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	private SudokuPanel sudokuPanel;
	private SudokuSolutionPanel sudokuSolutionPanel;
	JButton btnCheck, btnSolution, btnNewGame;

	SudokuFrame() {
		this.setTitle("Sudoku Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setSize(1000, 800);

		sudokuPanel = new SudokuPanel();

		btnSolution = new JButton("Solution");
		btnSolution.addActionListener(this);
		btnSolution.setBounds(850, 100, 100, 50);

		btnCheck = new JButton("Check");
		btnCheck.addActionListener(this);
		btnCheck.setBounds(850, 200, 100, 50);

		this.add(sudokuPanel);
		this.add(btnCheck);
		this.add(btnSolution);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCheck) {

			System.out.println(SudokuGenerator.isEqual(SudokuGenerator.test.board, SudokuGenerator.testBoardSolved));

			if (SudokuPanel.checkSudoku()) {
				System.out.println("Correct!");

			} else {
				System.out.println("Incorrect");
			}

		} else if (e.getSource() == btnSolution) {
			this.remove(sudokuPanel);
			this.remove(btnCheck);

			SudokuSolver.printSudoku(sudokuPanel.test.board);

			sudokuSolutionPanel = new SudokuSolutionPanel();
			btnNewGame = new JButton("New Game");
			btnNewGame.addActionListener(this);
			btnNewGame.setBounds(850, 200, 100, 50);

			this.add(btnNewGame);
			this.add(sudokuSolutionPanel);
			this.setVisible(true);
			this.update(getGraphics());

			System.out.println("Solution:");
			SudokuSolver.printSudoku(SudokuSolver.board);
		}
	}

}
