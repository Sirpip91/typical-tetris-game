
//package
package com.tetris.main;

//imports
import javax.swing.JFrame;

//GameWindow class that includes the main
public class GameWindow {
		
		//WINDOW DIMENSIONS
		public static final int WIDTH = 455, HEIGHT = 630;
		
		
		//Declaring variables
		private Board board;
		private JFrame window;
		
		//this has all things inside the GameWindow including itself
		public GameWindow() {
			
			//Window settings from JFrame
			window = new JFrame("Tetris");
			window.setSize(WIDTH,HEIGHT);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setResizable(false);
			window.setVisible(true);
			window.setLocationRelativeTo(null);
			
			//Implementing the Board class with all of the info
			board = new Board();
			window.add(board);
			window.setVisible(true);
		}
		
		//main method
		public static void main(String[] args) {
			new GameWindow();
		}
	

}
