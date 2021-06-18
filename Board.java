
//package
package com.tetris.main;

import java.awt.Color;
//imports
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.JPanel;

//The Board aka the play area of the game where all the actions happen
public class Board extends JPanel implements KeyListener {

	
	
	//declaring the variables
	private static int FPS = 60;
	private static int delay = 1000/FPS;
	public static final int BOARD_WIDTH = 10;
	public static final int BOARD_HEIGHT = 20;
	public static final int SQUARE_SIZE = 30;
	private Timer ticker;
	//a visual grid using the .Color
	private Color[][] board = new Color [BOARD_WIDTH][BOARD_HEIGHT];
	//tetromino Tetrominos
	// create Tetrominos
	private Tetromino[] shape = new Tetromino[7];
	
	 private Color[] colors = {Color.decode("#ed1c24"), Color.decode("#ff7f27"), Color.decode("#fff200"), 
		        Color.decode("#22b14c"), Color.decode("#00a2e8"), Color.decode("#a349a4"), Color.decode("#3f48cc")};
	
	 private Tetromino currentShape;

	
	//constructor
	public Board() {
		
		shape[0] = new Tetromino(new int[][]{
	        {1, 1, 1, 1} 			// I shape;
	    }, this, colors[0]);

	    shape[1] = new Tetromino(new int[][]{
	        {1, 1, 1},
	        {0, 1, 0}, 				// T shape;
	    }, this, colors[1]);

	    shape[2] = new Tetromino(new int[][]{
	        {1, 1, 1},
	        {1, 0, 0}, 				// L shape;
	    }, this, colors[2]);

	    shape[3] = new Tetromino(new int[][]{
	        {1, 1, 1},
	        {0, 0, 1}, 				// J shape;
	    }, this, colors[3]);

	    shape[4] = new Tetromino(new int[][]{
	        {0, 1, 1},
	        {1, 1, 0}, 				// S shape;
	    }, this, colors[4]);

	    shape[5] = new Tetromino(new int[][]{
	        {1, 1, 0},
	        {0, 1, 1}, 				// Z shape;
	    }, this, colors[5]);

	    shape[6] = new Tetromino(new int[][]{
	        {1, 1},
	        {1, 1}, 				// O shape;
	    }, this, colors[6]);

	    currentShape = shape[0];

		//Creating the Gameloop every half second event will happen.
		ticker = new Timer(delay, new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				movement();
				repaint();
			}
			
		});
		ticker.start();
	}
	
	
	private void movement() {
			currentShape.movement();
		}
		
	
	
	//Drawing stuff to the screen using Graphics g
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//filling the entire window
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		currentShape.draw(g);
		
		
		//Setting up the matrix/grid for each tetrimino to be onttop off
		g.setColor(Color.white);
		//rows
		for(int row = 0; row<BOARD_HEIGHT + 1; row++) {
			g.drawLine(0, SQUARE_SIZE * row, SQUARE_SIZE * BOARD_WIDTH, SQUARE_SIZE * row);
		}
		//columns
		for(int column = 0; column<BOARD_WIDTH + 1; column++) {
			g.drawLine(column * SQUARE_SIZE,0,column * SQUARE_SIZE,SQUARE_SIZE* BOARD_HEIGHT);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	// Down Arrow Key to speed up //RIGHT to move right // left to move left
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			currentShape.fastspeed();
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			currentShape.rightmovement();
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			currentShape.leftmovement();
		}
		
	}
	//back to regular speed after release down arrow key
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			currentShape.normalspeed();
		}		
	}



}
	

