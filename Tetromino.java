package com.tetris.main;

import java.awt.Color;
import java.awt.Graphics;

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
			board.cycleCurrentShape();
			return;
		}
		
		
		//this creates boundarys to stop outside of board horizontally
		if(!(x + horizontalmove + cords[0].length >10) && !(x + horizontalmove <0)){
			x+= horizontalmove;
		}
		horizontalmove = 0;
		x+= horizontalmove;
		horizontalmove = 0;
		
		if(System.currentTimeMillis()-beginUpdate > delayUpdate) {
			if(!(y + 1 + cords.length > BOARD_HEIGHT)) {
				y++;
			}else {
				collision = true;
			}
			
			beginUpdate = System.currentTimeMillis();
		}
		}
		
		public void draw(Graphics g) {
			// Tetromino Drawing// Only draws if it is not null
			for(int row = 0; row<cords.length; row++) {
				for(int column = 0; column<cords[0].length; column++) {
					
					if(cords[row][column] != 0) {
					g.setColor(Color.red);
												// This will draw the tetromino and make it go down
					g.fillRect(column * SQUARE_SIZE + x *SQUARE_SIZE, row * SQUARE_SIZE + y *SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
					}
				}
			}
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
	
}
