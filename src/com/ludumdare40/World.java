package com.ludumdare40;

import java.util.ArrayList;

import com.ludumdare40.com.entities.*;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class World {
	private ArrayList<Entity> entities;
	/** WIDTH and height of map*/
	public static float WIDTH;
	public static float HEIGHT;
	private Player player;
	private StaticEntity campfire;
	private ArrayList<Bullet> bullets;
	private ArrayList<Person> campMembers;
	private boolean endGame;

	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public World(float width, float height, Player player) {
		this.WIDTH = width;
		this.HEIGHT = height;
		entities = new ArrayList<Entity>();
		bullets = new ArrayList<> ();
		campMembers = new ArrayList<>();
		this.player = player;
		generateTerrain();
		generateCreatures();
		endGame = false;
	}

	public boolean isEndGame() {
		return endGame;
	}

	public void update(GameContainer gc, int delta) {
		//System.out.println("Player x:"+player.getX() + "Player y:"+player.getY());
		handlePlayerMovementAndCollisions(gc, delta);
		handleBullets(gc, delta);
		removeDeadCreatures();
		if(Math.random() * campMembers.size() / delta < .0005) {
			createMonster();
		}
		if(Math.random() / delta < .0001) {
			createPerson();
		}
		if(Math.random()/delta < .0001) {
			float x = (float) (Math.random()) * WIDTH;

			float y = (float) (Math.random()) * HEIGHT;


			entities.add(new Food(x, y, Food.Type.BERRY, ImageRes.berryImg));
			
		}

		if(player.getHealth() <= 0 || campMembers.size() < 1) {
			endGame = true;
		}
	}
	
	public Player getPlayer() {
		return player;
	}

	private void generateTerrain() {
		for(int i = 0; i < (int)(Math.random() * 40 + 100); i++) {
			float x = (float)(Math.random()) * WIDTH;
			float y = (float)(Math.random()) * HEIGHT;
			entities.add(new StaticEntity(x, y, StaticEntity.Type.TREE, ImageRes.treeImg));
			
		}
		for(int i = 0; i < (int)(Math.random() * 40 + 300); i++) {
			float x = (float)(Math.random()) * WIDTH;
			float y = (float)(Math.random()) * HEIGHT;
			entities.add(new Food(x, y, Food.Type.BERRY, ImageRes.berryImg));
			
		}
		
		for(int i = 0; i < (int)(Math.random() * 40 + 100); i++) {
			float x = (float)(Math.random()) * WIDTH;
			float y = (float)(Math.random()) * HEIGHT;
			entities.add(new StaticEntity(x, y, StaticEntity.Type.ROCK, ImageRes.rockImg));
			
		}

		this.campfire = new StaticEntity(WIDTH /2, HEIGHT /2, StaticEntity.Type.CAMPFIRE,ImageRes.campfireImg);
		entities.add(campfire);
		
	}

	private void handlePlayerMovementAndCollisions(GameContainer gc, int delta) {
		float intendedDeltaX = 0;
		float intendedDeltaY = 0;

		if(gc.getInput().isKeyDown(Input.KEY_A)) {
			intendedDeltaX = -0.1f;
		} else if(gc.getInput().isKeyDown(Input.KEY_D)) {
			intendedDeltaX = 0.1f;
		}

		if(gc.getInput().isKeyDown(Input.KEY_W)) {
			intendedDeltaY = -0.1f;
		} else if(gc.getInput().isKeyDown(Input.KEY_S)){
			intendedDeltaY = 0.1f;
		}
		intendedDeltaY = intendedDeltaY*delta;
		intendedDeltaX = intendedDeltaX*delta;

		for(int i = entities.size() - 1; i>=0; i--) {
			Entity e = entities.get(i);
			e.update(gc, delta);
			if(e instanceof Monster){
				if(campMembers.size() == 0 || Math.abs(e.distanceTo(player)) < 500) {
					((Monster)e).setTarget(player);
				} else {
					((Monster)e).setTarget(campMembers.get(0));
				}
			}

			if(player.getCollider().willCollideWith(e.getCollider(), 0, intendedDeltaY)) {
				if (e instanceof Food){
					entities.remove(e);
					player.incrementFood(((Food) e).getFoodValue());
				} else {
					intendedDeltaY = 0;
				}
			}
			if(player.getCollider().willCollideWith(e.getCollider(), intendedDeltaX, 0)) {
				if (e instanceof Food){
					entities.remove(e);
					player.incrementFood(((Food) e).getFoodValue());
				} else {
					intendedDeltaX = 0;
				}
			}
		}
		player.moveX(intendedDeltaX);
		player.moveY(intendedDeltaY);
		
		if(gc.getInput().isMousePressed(0)) {
			boolean shoot = true;
			for(Person p: campMembers) {
				if(p.checkToFeed(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
					if(player.getFood() > 0) {
						player.incrementFood(-1);
						p.feed();
					}
					shoot = false;
				}
			}
			if(shoot) {
				shootBullet(gc);
			}
		}
	}

	private void shootBullet(GameContainer gc) {
		float xComponent = gc.getInput().getMouseX() - gc.getWidth()/2;
		float yComponent = gc.getInput().getMouseY() - gc.getHeight()/2;
		Vector2f velocity = new Vector2f(xComponent, yComponent);

		bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY() + player.getHeight()/2, ImageRes.bulletImg,velocity));
		
	}
	
	private void handleBullets(GameContainer gc, int delta) {
		for(int i = bullets.size() - 1; i>=0; i--){
			Bullet b = bullets.get(i);
			b.update(gc, delta);
			if(Math.abs(b.getX() - player.getX()) > gc.getWidth() * 2 || Math.abs(b.getX() - player.getX()) > gc.getWidth() * 2){
				bullets.remove(b);
			} else {
				for(Entity e : entities) {

					if (b.getCollider().collidesWith(e.getCollider())) {
						if (e instanceof StaticEntity)
							bullets.remove(b);
						else if (e instanceof Creature) {

							((Creature) (e)).hurt(Bullet.DAMAGE);
							bullets.remove(b);
						}
					}
				}

			}
		}
	}

	public ArrayList<Bullet> getBullets(){
		return bullets;
	}

	private void removeDeadCreatures(){
		for(int i = entities.size() - 1; i>=0; i--) {
			if(entities.get(i) instanceof Creature && ((Creature) entities.get(i)).isDead()) {
				if (entities.get(i) instanceof Person) {
					campMembers.remove(entities.get(i));
				}
				if (entities.get(i) instanceof Monster) {
					Person.monsters.remove(entities.get(i));
				}
				entities.remove(i);
			}
		}
	}

	public void generateCreatures() {
		for (int i = 0; i < 5; i++) {
			createPerson();
		}
		for(int i = 0;i < 2; i++)
			createMonster();
	}

	private void createMonster(){
		Monster m = null;
		m = new Monster((float)Math.random()*WIDTH, (float)Math.random() * HEIGHT, ImageRes.monsterImg, 10);
		m.setTarget(player);
		entities.add(m);
		Person.monsters.add(m);
	}

	public void createPerson() {
		Person.setCampfire(campfire);
			/*float x = (float) Math.random() * 100f + campfire.getWidth() + 10f ;
			float y = (float) Math.random() * 100f + campfire.getHeight() + 10f;
			if(Math.random() < .5 ) { x *= -1 ;}
			if(Math.random() < .5 ) { y *= -1 ;}*/
			try {
				Person campmember = new Person((float)Math.random() * WIDTH, (float)Math.random() * HEIGHT, new Image("res/playeridle.png"), (float)Math.random() *30f + 60f);
				entities.add(campmember);
				campMembers.add(campmember);
				//System.out.println("Camp member x: " + campmember.getX() +"Camp member y: " + campmember.getY());
			} catch(Exception exception) {
				System.out.println("Could not load people image" + exception);
			}

	}
}
