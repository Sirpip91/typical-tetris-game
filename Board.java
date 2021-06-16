
//package
package com.tetris.main;

import java.awt.Color;
//imports
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JPanel;

//The Board aka the play area of the game where all the actions happen
public class Board extends JPanel {

	//declaring the variables
	public static final int BOARD_WIDTH = 10;
	public static final int BOARD_HEIGHT = 20;
	public static final int SQUARE_SIZE = 30;
	private Timer ticker;
	//a visual grid using the .Color
	private Color[][] board = new Color [BOARD_WIDTH][BOARD_HEIGHT];
	//tetromino shape
	private Color[][] tetromino = {
			{Color.red, Color.red, Color.red},
				{null, Color.red,null}
	};
	 
	
	
	
	//constructor
	public Board() {

		//Creating the Gameloop every half second event will happen.
		ticker = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Heloo");
			}
			
		});
		ticker.start();
	}
	
	//Drawing stuff to the screen using Graphics g
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//filling the entire window
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		// Tetromino Drawing// Only draws if it is not null
		for(int row = 0; row<tetromino.length; row++) {
			for(int column = 0; column<tetromino[0].length; column++) {
				
				if(tetromino[row][column] != null) {
				g.setColor(tetromino[row][column]);
				g.fillRect(column * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
				}
			}
		}
		
		
		//Setting up the matrix/grid for each tetrimino to be onttop off
		g.setColor(Color.white);
		//rows
		for(int row = 0; row<BOARD_HEIGHT; row++) {
			g.drawLine(0, SQUARE_SIZE * row, SQUARE_SIZE * BOARD_WIDTH, SQUARE_SIZE * row);
		}
		//columns
		for(int column = 0; column<BOARD_WIDTH + 1; column++) {
			g.drawLine(column * SQUARE_SIZE,0,column * SQUARE_SIZE,SQUARE_SIZE* BOARD_HEIGHT);
		}
	}
}
	

