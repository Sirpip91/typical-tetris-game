
//package
package com.tetris.main;

import java.awt.Color;
//imports
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.Timer;
import javax.swing.JPanel;

public class Board extends JPanel {

	
	
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.white);
		g.drawRect(10, 10, 415, 525);
	}
}
