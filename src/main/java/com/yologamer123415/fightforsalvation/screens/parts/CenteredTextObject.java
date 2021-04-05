package com.yologamer123415.fightforsalvation.screens.parts;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PConstants;
import processing.core.PGraphics;

public class CenteredTextObject extends GameObject {
	private final String text;
	private final int fontSize;
	private int RGB = 0;

	public CenteredTextObject(String text, int fontSize) {
		this.text = text;
		this.fontSize = fontSize;
	}

	public void setRGB(int RGB) {
		this.RGB = RGB;
	}

	@Override
	public void draw(PGraphics g) {
		g.fill(RGB);
		g.textAlign(PConstants.CENTER);
		g.textSize(this.fontSize);
		g.text(this.text, this.x, this.y);
	}

	@Override
	public void update() {}
}
