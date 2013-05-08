package nl.avans.min04sob.scrabble.core;

import java.awt.GridBagConstraints;

public class CoreConstraint extends GridBagConstraints {

	public CoreConstraint(int width, int height, int xPos, int yPos){
		gridwidth = width;
		gridheight = height;
		gridx = xPos;
		gridy = yPos;
		fill = GridBagConstraints.BOTH;
	}
	
	public CoreConstraint(int width, int height, int xPos, int yPos, int fillConstraint){
		gridwidth = width;
		gridheight = height;
		gridx = xPos;
		gridy = yPos;
		fill = fillConstraint;
	}
}
