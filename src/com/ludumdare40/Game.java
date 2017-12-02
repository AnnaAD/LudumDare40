package com.ludumdare40;

import java.util.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.*;

public class Game extends BasicGameState {
	public static final int ID = 0;

	public Game() {
		super();
		System.out.println("Constructor");
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		setupNewGame(gc, sbg);
	}

	public void update(GameContainer gc, StateBasedGame arg1, int delta) throws SlickException {
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	
	}

	public void endGame(GameContainer gc, StateBasedGame sbg) {
		
	}

	public void setupNewGame(GameContainer gc, StateBasedGame sbg) {
		
	}

	@Override
	public int getID() {
		return Game.ID;
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg){
		setupNewGame(gc, sbg);
	}
}