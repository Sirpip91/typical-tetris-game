
//package
package com.tetris.main;

import java.awt.Component;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
//imports
import javax.swing.JFrame;

//GameWindow class that includes the main
public class GameWindow {
		
		//WINDOW DIMENSIONS
		public static final int WIDTH = 600, HEIGHT = 645;
		
		
		//Declaring variables
		private Board board;
		private JFrame window;
		private Sounds sounds;
		
		
		
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
			window.addKeyListener(board);
			
		
		}
		
		
		
		//main method
		public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
			Sounds sound = new Sounds();
			sound.music();
			new GameWindow();
			
			
		}
	

}
