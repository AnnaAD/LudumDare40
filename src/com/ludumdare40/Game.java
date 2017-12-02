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
	private static final int WORLDWIDTH = 10000;
	private static final int WORLDHEIGHT = 10000;
	private Camera camera;
	private World world;
	private Player player;

	public Game() {
		super();
		System.out.println("Game initiated");
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		setupNewGame(gc, sbg);
	}

	public void update(GameContainer gc, StateBasedGame arg1, int delta) throws SlickException {
		player.update(delta);
		world.update(delta);
		camera.centerOnEntity(player);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		camera.render(gc, g);
	}

	public void endGame(GameContainer gc, StateBasedGame sbg) {
		
	}

	public void setupNewGame(GameContainer gc, StateBasedGame sbg) {
		world = new World(WORLDWIDTH, WORLDHEIGHT);
		camera = new Camera();
		player = new Player(10,10,img);
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