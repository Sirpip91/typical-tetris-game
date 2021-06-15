package com.tetris.main;


import javax.swing.JFrame;

public class GameWindow {
		
		//WINDOW DIMENSIONS
		public static final int WIDTH = 455, HEIGHT = 630;
		
		
		//
		//
		private Board board;
		private JFrame window;
		
		
		public GameWindow() {
			window = new JFrame("Tetris");
			window.setSize(WIDTH,HEIGHT);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setResizable(false);
			window.setVisible(true);
			window.setLocationRelativeTo(null);
			
			board = new Board();
			window.add(board);
			window.setVisible(true);
		}
		
		//main method
		
		public static void main(String[] args) {
			new GameWindow();
		}
	

}
