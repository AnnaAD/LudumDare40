package com.ludumdare40;

import com.ludumdare40.com.entities.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.ludumdare40.ui.Button;

import org.newdawn.slick.*;

public class Game extends BasicGameState {
	public static final int ID = 0;
	private static final int WORLDWIDTH = 10000;
	private static final int WORLDHEIGHT = 10000;
	private Camera camera;
	private World world;
	private Player player;
	private Image playerImg;
	private Button button;

	public Game() {
		super();
		System.out.println("Game initiated");
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		playerImg = new Image("res/playeridle.png");
		setupNewGame(gc, sbg);
	}

	public void update(GameContainer gc, StateBasedGame arg1, int delta) throws SlickException {
		player.update(gc, delta);
		world.update(gc, delta);
		camera.centerOnEntity(player);
		
		if(gc.getInput().isMousePressed(0)) {
			if(button.checkClicked(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
				System.out.println("click");
			}
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		camera.render(gc, g);
		g.drawString("Food:" + player.getFood(), 10, 5);
		g.drawString("Health:" + (int) player.getHealth(), 10, 20);
	}

	public void endGame(GameContainer gc, StateBasedGame sbg) {
		
	}

	public void setupNewGame(GameContainer gc, StateBasedGame sbg) {
		player = new Player(WORLDWIDTH / 2 - 100,WORLDHEIGHT/2,playerImg, 50);
		world = new World(WORLDWIDTH, WORLDHEIGHT, player);
		camera = new Camera(world);
		gc.getGraphics().setBackground(new Color(0x448E4B));
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