package com.tetris.main;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;


public class Sounds {
	
	
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
