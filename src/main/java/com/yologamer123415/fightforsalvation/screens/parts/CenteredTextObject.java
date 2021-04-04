package com.yologamer123415.fightforsalvation.screens.parts;

import nl.han.ica.oopg.objects.TextObject;
import processing.core.PConstants;
import processing.core.PGraphics;

public class CenteredTextObject extends TextObject {
	public CenteredTextObject(String text, int fontSize) {
		super(text, fontSize);
	}

	@Override
	public void draw(PGraphics g) {
		g.fill(0);
		g.textAlign(PConstants.CENTER);
		g.textSize(getFontSize());
		g.text(getText(), this.x, this.y);
	}
}
