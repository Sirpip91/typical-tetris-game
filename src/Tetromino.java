package com.tetris.main;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Tetromino {

		//updating the position of tetromino x=row y=column
		private int x = 4 , y= 0 ;
		
		//Delay the update time
		private int regularspeed = 600;
		private int fastspeed = 50;
		private int delayUpdate = regularspeed;
		private long beginUpdate;
		private int horizontalmove = 0;
		private boolean collision = false;
		public static final int BOARD_WIDTH = 10;
		public static final int BOARD_HEIGHT = 20;
		public static final int SQUARE_SIZE = 30;
		
		
		private int [][] cords;
		private Board board;
		private Color color;
		
		
		
		
		
		public Tetromino(int[][] cords, Board board, Color color) {
					this.cords = cords;
					this.board = board;
					this.color = color;
			}
		
		public void setX(int x) {
			this.x = x;
		}
		public void setY(int y) {
			this.y = y;
		}
		public void reset() {
			this.x = 4;
			this.y = 0;
			collision = false;
		}
		
		public void movement(){
		if(collision){
			
			// after collision tetromino stays there through color the board
			for(int row  = 0; row < cords.length; row++) {
				for(int col = 0; col<cords[0].length; col++) {
					if(cords[row][col] !=0 ) {
						board.getBoard()[y + row][x + col] = color;
						
					}
				}	
			}
			checkLine();
			board.cycleCurrentShape();
			
			return;
			
		}
		
		
		//this creates boundarys to stop outside of board horizontally
		boolean moveX = true;
		if(!(x + horizontalmove + cords[0].length >10) && !(x + horizontalmove <0)){
			for(int row = 0; row< cords.length; row++)
				for(int col = 0; col< cords[row].length; col++) {
					if(cords[row][col]!=0) {
						if(board.getBoard()[y + row] [x + horizontalmove + col] != null) {
							moveX = false;
						}
					}
					
					}if(moveX) {
						x+= horizontalmove;
				}
		}
		
		horizontalmove = 0;
		
		
		if(System.currentTimeMillis()-beginUpdate > delayUpdate) {
			//VerticalMovment
			if(!(y + 1 + cords.length > BOARD_HEIGHT)) {
				for(int row = 0; row<cords.length; row++)
					for(int col = 0; col < cords[row].length; col++)
						if(cords[row][col] != 0) {
							if(board.getBoard()[y + 1 + row][x + horizontalmove + col] != null) {
								collision = true;
							}	
					}
					if(!collision) {
						y++;
					}
			}else {
				collision = true;
			}
			
			beginUpdate = System.currentTimeMillis();
		}
		}
		
		//TETRIS!!!
		//checks if each col/square is filled if the entire row is filled it will clear and move tetrominos down.
		private void checkLine() {
			int bottomLine = board.getBoard().length - 1;
			for(int topLine = board.getBoard().length -1; topLine > 0; topLine--) {
				int count  = 0;
				for(int col = 0; col<board.getBoard()[0].length; col++) {
					if(board.getBoard()[topLine][col] != null) {
						count++;
						if(count == 10) {
							//increase points
							Board.score += 100;
							//This will play tetris sound
							try {
								Sounds.audio();
							} catch (UnsupportedAudioFileException e1) {
								
								e1.printStackTrace();
							} catch (IOException e1) {
				
								e1.printStackTrace();
							} catch (LineUnavailableException e1) {
								
								e1.printStackTrace();
							}
						}
					}
					board.getBoard()[bottomLine][col] = board.getBoard()[topLine][col];
					}
					if(count<board.getBoard()[0].length) {
						bottomLine--;
						
				}
			}
		}
		
		//rotation
		public void rotateTetromino() {
			int[][] rotatedTetromino = flipMatrix(cords);
			reverseRows(rotatedTetromino);
			
			
			//Checking if too close to wall
			if((x + rotatedTetromino[0].length >Board.BOARD_WIDTH) || (y + rotatedTetromino.length > Board.BOARD_HEIGHT ))
				return;
			cords = rotatedTetromino;
			
			//Checking if it will rotate into another shape.
			//It will check all shapes !=0, 
			
			for(int row = 0; row < rotatedTetromino.length; row++) {
				for(int col = 0; col < rotatedTetromino[row].length; col++) {
					if(rotatedTetromino[row][col] != 0) {
						if(board.getBoard() [y+row] [x+col] != null) {
							return;
						}
					}
				}
			}
		}
		
		
		//flipping the matrix of shape
		private int[][] flipMatrix(int [][] matrix) {
			int[][] temp = new int [matrix[0].length][matrix.length];
			for(int row = 0; row < matrix.length; row++) {
				for (int col = 0; col<matrix[0].length; col++) {
					temp[col][row] = matrix[row][col];
				}
			}
			return temp;
		}
		//using the arrays index to flip and swap.
		private void reverseRows(int[][] matrix){
			int middle = matrix.length / 2;
			for(int row = 0; row<middle; row ++) {
				int[] temp = matrix[row];
				matrix[row] = matrix[matrix.length -row - 1];
				matrix[matrix.length - row - 1] = temp;
			}
		}
		
		public void draw(Graphics g) {
			// Tetromino Drawing// Only draws if it is not null
			for(int row = 0; row<cords.length; row++) {
				for(int column = 0; column<cords[0].length; column++) {
					
					if(cords[row][column] != 0) {
					g.setColor(color);
												// This will draw the tetromino and make it go down
					g.fillRect(column * SQUARE_SIZE + x *SQUARE_SIZE, row * SQUARE_SIZE + y *SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
					}
				}
			}
		}
		
		public int [][] getCords(){
			return cords;
		}
		
		
		//Key movement
		public void fastspeed() {
			delayUpdate = fastspeed;
		}
		public void normalspeed() {
			delayUpdate = regularspeed;
		}
		public void rightmovement() {
			horizontalmove = 1;
		}
		public void leftmovement() {
			horizontalmove = -1;
		}

		public int getX() {
			return x;
			
		}

		public int getY() {
			return y;
		}
	
}
