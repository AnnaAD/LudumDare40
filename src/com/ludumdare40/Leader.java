package com.ludumdare40;

import org.newdawn.slick	.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Leader extends StateBasedGame {

	// Game state identifiers
	public static final int GAME = 0;
	public static final int GAMEOVER = 1;
	public static final int PAUSE = 2;

	// Application Properties
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 750;
	public static final int FPS = 60;
	public static final double VERSION = 1.0;

	// Class Constructor
	public Leader(String appName) {
		super(appName);
	}

	// Initialize your game states (calls init method of each gamestate, and
	// set's the state ID)
	public void initStatesList(GameContainer gc) throws SlickException {
		// The first state added will be the one that is loaded first, when the
		// application is launched
		this.addState(new Game());
		this.addState(new GameOver());
		this.addState(new PauseMenu());
	}

	// Main Method
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Leader("Leader"));
			app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), false);
			app.setTargetFrameRate(FPS);
			app.setShowFPS(false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
