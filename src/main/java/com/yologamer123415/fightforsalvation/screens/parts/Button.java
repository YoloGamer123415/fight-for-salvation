package com.yologamer123415.fightforsalvation.screens.parts;

public class Button {
	private final int x;
	private final int y;
	private final int width;
	private final int height;

	public Button(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Check if the mouse is above the done button.
	 *
	 * @param mouseX The x-position of the mouse.
	 * @param mouseY The y-position of the mouse.
	 * @return True if above, false if not.
	 */
	public boolean isMouseAboveButton(int mouseX, int mouseY) {
		return mouseX >= this.x &&
				mouseX <= this.x + this.width &&
				mouseY >= this.y &&
				mouseY <= this.y + this.height;
	}
}
