package com.tetris.main;

import java.awt.Canvas;

public class Game extends Canvas implements Runnable{
	
	public static final int WIDTH = 445, HEIGHT = 629;
	
	
	public Game() {
		new Window(WIDTH,HEIGHT, "Typical Tetris", this);
	}
	
	public synchronized void start() {
		
	}
	public void run() {
		
	}
	
	//Main Method
	public static void main(String arg[]) {
		new Game();
	}
}
