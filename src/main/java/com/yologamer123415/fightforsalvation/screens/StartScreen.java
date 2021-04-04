package com.yologamer123415.fightforsalvation.screens;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.screens.parts.Button;
import com.yologamer123415.fightforsalvation.screens.parts.CenteredTextObject;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.Sprite;

public class StartScreen extends Dashboard {
	private Button start;
	private Button quit;

	public StartScreen() {
		super(0, 0, FightForSalvation.SCREEN_WIDTH, FightForSalvation.SCREEN_HEIGHT);
		this.setupScreen();
	}

	private void setupScreen() {
		CenteredTextObject title = new CenteredTextObject("Fight for Salvation", FightForSalvation.FONT_SIZE * 3);
		this.addGameObject(title, FightForSalvation.SCREEN_WIDTH / 2, 100);

		CenteredTextObject by = new CenteredTextObject("Door Joram en Stijn", FightForSalvation.FONT_SIZE * 2);
		this.addGameObject(by, FightForSalvation.SCREEN_WIDTH / 2, 150);

		this.start = new Button(214, 266, 210, 57);
		this.quit = new Button(556, 266, 211, 56);

		this.setBackgroundImage( new Sprite("src/main/resources/background_start.jpg") );
	}

	@Override
	public void mousePressed(int x, int y, int button) {
		if ( this.start.isMouseAboveButton(x, y) ) {
			FightForSalvation.getInstance().startGame();
		} else if ( this.quit.isMouseAboveButton(x, y) ) {
			System.exit(0);
		}
	}
}
