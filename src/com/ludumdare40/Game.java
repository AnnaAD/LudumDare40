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
	private static final int WORLDWIDTH = 4000;
	private static final int WORLDHEIGHT = 4000;
	private Camera camera;
	private World world;
	private CreatureManager creatureManager;
	private Player player;
	private Image playerImg;
	private Button button;

	public Game() {
		super();
		System.out.println("Game initiated");
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		ImageRes.init();
		setupNewGame(gc, sbg);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		player.update(gc, delta);
		world.update(gc, delta);
		creatureManager.update(gc, delta, world.getEntities());
		camera.centerOnEntity(player);
		
		if(creatureManager.isGameOver()) {
			endGame(gc,sbg);
		}
		
		if(gc.getInput().isMousePressed(0)) {
			if(button.checkClicked(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
				System.out.println("click");
			}
		}
		if(gc.getInput().isKeyPressed(Input.KEY_P) || gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			sbg.enterState(2);
		}
		
		 if (gc.getInput().isKeyPressed(Input.KEY_F2)) {
	            try {
	               gc.setFullscreen(!gc.isFullscreen());
	            } catch (SlickException e) {
	               System.out.println(e);
	            }
	      }
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		camera.render(gc, g, player);
		g.drawString("Food:" + player.getFood(), 10, 5);
		g.drawString("Health:" + (int) player.getHealth(), 10, 20);
	}

	public void endGame(GameContainer gc, StateBasedGame sbg) {
		sbg.enterState(1);
	}

	public void setupNewGame(GameContainer gc, StateBasedGame sbg) {
		player = new Player(WORLDWIDTH / 2 - 100,WORLDHEIGHT/2,ImageRes.personSpriteSheet, 50);
		world = new World(WORLDWIDTH, WORLDHEIGHT, player);
		creatureManager = new CreatureManager(WORLDWIDTH, WORLDHEIGHT, player, world.campfire);
		camera = new Camera(world, creatureManager);
		creatureManager.setCamera(camera);
		System.out.println("After");
		gc.getGraphics().setBackground(new Color(0xffffff));
	}

	@Override
	public int getID() {
		return Game.ID;
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg){
		if(creatureManager == null || creatureManager.isGameOver()) {
			setupNewGame(gc, sbg);
		}
	}
}