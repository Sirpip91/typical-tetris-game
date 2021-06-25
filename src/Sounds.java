package com.tetris.main;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.sampled.*;



public class Sounds {

	
	
	public static void music()throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		String backgroundmusic;
		
		backgroundmusic = ".//src//assets//Tetris 99 - Main Theme.wav";
	
	
			File file = new File(backgroundmusic);
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
					Clip clip = AudioSystem.getClip();
					clip.open(audioStream);
					FloatControl gainControl = 
						    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
						gainControl.setValue(-15.0f); // Reduce volume by 15 decibels.
			
					clip.start();
					clip.loop(clip.LOOP_CONTINUOUSLY);
					
				
	}
	
	
	public static void audio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
			String tetrissound;
			
			tetrissound = ".//src//assets//NES Tetris Sound Effect - Tetris Clear.wav";
		
		
				File file = new File(tetrissound);
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
						Clip clip = AudioSystem.getClip();
						clip.open(audioStream);
	
						clip.start();
	}
}
