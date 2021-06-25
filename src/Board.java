
//package
package com.tetris.main;

import java.awt.Color;
import java.awt.Font;
//imports
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

//The Board aka the play area of the game where all the actions happen
public class Board extends JPanel implements KeyListener {

	
	
	//declaring the variables
	
	public static int GAME_PLAY = 0;
	public static int GAME_PAUSE = 1;
	public static int GAME_OVER = 2;
	public static int score = 0;
	
	private int game = GAME_PLAY;
	public  boolean pause = false;
	private static int FPS = 60;
	private static int delay = 1000/FPS;
	public static final int BOARD_WIDTH = 10;
	public static final int BOARD_HEIGHT = 20;
	public static final int SQUARE_SIZE = 30;
	private Timer ticker;
	//a visual grid using the .Color
	private Color[][] board = new Color [BOARD_HEIGHT][BOARD_WIDTH];
	//tetromino Tetrominos
	// create Tetrominos
	private Tetromino[] shape = new Tetromino[7];
	
	private Color[] colors = {Color.decode("#ed1c24"), Color.decode("#ff7f27"), Color.decode("#fff200"), 
		        Color.decode("#22b14c"), Color.decode("#00a2e8"), Color.decode("#a349a4"), Color.decode("#3f48cc")};

	 Sounds sound = new Sounds();
	 private Tetromino currentShape;
	 Random tetris = new Random();

	
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

	    currentShape = shape[tetris.nextInt(shape.length)];

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
			if(game == GAME_PLAY) {
			currentShape.movement();
			currentShape.normalspeed();
			}
		}
	public void cycleCurrentShape() {
		//random tetris shape
			currentShape = shape[tetris.nextInt(shape.length)];
			currentShape.reset();
			GameOver();
	}
	
	private void GameOver() {
		int[][] cords = currentShape.getCords();
		for(int row = 0; row < cords.length; row++ ) {
			for(int col = 0; col < cords[0].length; col++) {
				if(cords[row][col] != 0) {
					if(board[row + currentShape.getY()][col + currentShape.getX()] !=null) {
						game = GAME_OVER;
					}
				}
			}
		}
	}
	
	//Drawing stuff to the screen using Graphics g
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//filling the entire window
		g.fillRect(0, 0, getWidth(), getHeight());
		currentShape.draw(g);
		
		for( int row = 0; row<BOARD_HEIGHT; row++) {
			for(int column = 0; column<BOARD_WIDTH;column++) {
				
				if(board[row][column] != null) {
					g.setColor(board[row][column]);
					g.fillRect(column * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
				}
			}
			//Score
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman", Font.BOLD, 30));
			
			g.drawString("Score: " + score , 330, 100);
			
			
			if(game == GAME_OVER) {
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman", Font.BOLD , 30));
			g.drawString("GAME OVER\n PRESS SPACE TO RESTART ", 330, 50);
			
			}
			
			if(game == GAME_PAUSE) {
				g.setColor(Color.white);
				g.setFont(new Font("TimesRoman", Font.BOLD , 30));
				g.drawString("GAME PAUSED", 330, 50);
			}
			
		}
		
		
		
		
		//Setting up the matrix/grid for each tetrimino to be onttop off
		g.setColor(Color.black);
		//rows
		for(int row = 0; row<BOARD_HEIGHT + 1; row++) {
			g.drawLine(0, SQUARE_SIZE * row, SQUARE_SIZE * BOARD_WIDTH, SQUARE_SIZE * row);
		}
		//columns
		for(int column = 0; column<BOARD_WIDTH + 1; column++) {
			g.drawLine(column * SQUARE_SIZE,0,column * SQUARE_SIZE,SQUARE_SIZE* BOARD_HEIGHT);
		}
	}

	//geting board
	public Color[][] getBoard(){
		return board;
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
		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
			currentShape.rotateTetromino();
		}
		
		//clearing the board after game over
		if(game == GAME_OVER) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				for(int row = 0; row < board.length; row++) {
					for(int col = 0; col < board[row].length;col++) {
						board[row][col] = null;
					}
				}
				cycleCurrentShape();
				game = GAME_PLAY;
			}
		
		}
		//Pausing the Game
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if(game == GAME_PLAY) {
					game = GAME_PAUSE;
					
				}else if(game == GAME_PAUSE) {
					game = GAME_PLAY;
				}
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

	

