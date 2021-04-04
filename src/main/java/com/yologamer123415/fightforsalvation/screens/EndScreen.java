package com.yologamer123415.fightforsalvation.screens;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.screens.parts.Button;
import com.yologamer123415.fightforsalvation.screens.parts.CenteredTextObject;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.Sprite;

public class EndScreen extends Dashboard {
	private Button quit;

	public EndScreen(boolean playerDied) {
		super(0, 0, FightForSalvation.SCREEN_WIDTH, FightForSalvation.SCREEN_HEIGHT);
		this.setupScreen(playerDied);
	}

	private void setupScreen(boolean playerDied) {
		CenteredTextObject title = new CenteredTextObject("Fight for Salvation", FightForSalvation.FONT_SIZE * 3);
		this.addGameObject(title, FightForSalvation.SCREEN_WIDTH / 2, 100);

		CenteredTextObject by = new CenteredTextObject("Game over!", FightForSalvation.FONT_SIZE * 2);
		this.addGameObject(by, FightForSalvation.SCREEN_WIDTH / 2, 150);

		String diedText;
		if (playerDied) {
			diedText = "Je bent vermoord door de monsters. Probeer het opnieuw!";
		} else {
			diedText = "Gefeliciteerd. Goed gedaan, je hebt het spel gehaald!";
		}

		CenteredTextObject died = new CenteredTextObject(diedText, FightForSalvation.FONT_SIZE);
		this.addGameObject(died, FightForSalvation.SCREEN_WIDTH / 2, 200);

		this.quit = new Button(380, 266, 210, 57);

		this.setBackgroundImage( new Sprite("src/main/resources/background_end.jpg") );
	}

	@Override
	public void mousePressed(int x, int y, int button) {
		if (this.quit.isMouseAboveButton(x, y)) {
			FightForSalvation.getInstance().stop();
		}
	}
}
