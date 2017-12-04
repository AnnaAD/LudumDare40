package com.ludumdare40;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BasicGameState {
	public static final int ID = 1;
	// time elapsed in milliseconds
	private int timeElapsed;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		timeElapsed = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		String text = "GAME OVER.";
		g.setColor(Color.black);
		text = "Press Enter to Play again, or Q to quit";
		g.drawString(text, gc.getWidth() / 2 - gc.getDefaultFont().getWidth(text) / 2, gc.getHeight() / 2 - gc.getDefaultFont().getHeight(text));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timeElapsed += delta;
		if (timeElapsed > 30000)
			sbg.enterState(0);
		if (gc.getInput().isKeyDown(Input.KEY_ENTER)) {
			sbg.enterState(0);
		}
		if (gc.getInput().isKeyDown(Input.KEY_Q)) {
			gc.exit();
			System.exit(-1);
		}
	}

	@Override
	public int getID() {
		return GameOver.ID;
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) {
		timeElapsed = 0;
	}
}