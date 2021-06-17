
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
	public static final int BOARD_HEIGHT = 21;
	public static final int SQUARE_SIZE = 30;
	private Timer ticker;
	//a visual grid using the .Color
	private Color[][] board = new Color [BOARD_WIDTH][BOARD_HEIGHT];
	//tetromino shape
	private Color[][] tetromino = {
			{Color.red, Color.red, Color.red},
				{null, Color.red,null}
	};
	//updating the position of tetromino x=row y=column
	private int x = 4 , y= 0 ;
	
	//Delay the update time
	private int regularspeed = 600;
	private int fastspeed = 50;
	private int delayUpdate = regularspeed;
	private long beginUpdate;
	private int horizontalmove = 0;
	private boolean collision = false;
	
	//constructor
	public Board() {

		//Creating the Gameloop every half second event will happen.
		ticker = new Timer(delay, new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				if(collision){
					return;
				}
				
				//horizontal movement updates change x for each key pressed
				//this creates boundarys to stop outside of board horizontally
				if(!(x + horizontalmove + tetromino[0].length >10) && !(x + horizontalmove <0)){
					x+= horizontalmove;
				}
				horizontalmove = 0;
				x+= horizontalmove;
				horizontalmove = 0;
				
				if(System.currentTimeMillis()-beginUpdate > delayUpdate) {
					if(!(y + 1 + tetromino.length >= BOARD_HEIGHT)) {
						y++;
					}else {
						collision = true;
					}
					y++;
					beginUpdate = System.currentTimeMillis();
				}
				
				// Everthing in the paintComponent will be updated/repainted
				repaint();
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
											// This will draw the tetromino and make it go down
				g.fillRect(column * SQUARE_SIZE + x *SQUARE_SIZE, row * SQUARE_SIZE + y *SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
				}
			}
		}
		
		
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
			delayUpdate = fastspeed;
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			horizontalmove = 1;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			horizontalmove = -1;
		}
		
	}
	//back to regular speed after release down arrow key
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			delayUpdate = regularspeed;
		}		
	}
	
	//Getting key movement
	



}
	

