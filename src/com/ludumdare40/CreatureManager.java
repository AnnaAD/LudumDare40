package com.ludumdare40;

import com.ludumdare40.com.entities.*;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CreatureManager {
    public static float WIDTH;
    public static float HEIGHT;
    private Player player;
    private ArrayList<Bullet> bullets;
    private ArrayList<Person> campMembers;
    private ArrayList<Monster> monsters;
    private NameGenerator nameGenerator;
    private Person selectedPerson;
    private StaticEntity campfire;
    private Camera mainCamera; //sorry...
	private String campfireDir = null;

    public ArrayList<Creature> getCreatures() {
        ArrayList<Creature> creatures = new ArrayList<Creature>(monsters);
        creatures.addAll(campMembers);
        return creatures;
    }

    public boolean isGameOver() {
         return player.getHealth() <= 0 || campMembers.size() <= 0;
    }

    public CreatureManager(float width, float height, Player player, StaticEntity campfire) {
        this.WIDTH = width;
        this.HEIGHT = height;
        nameGenerator = new NameGenerator();
        campMembers = new ArrayList<Person>();
        monsters = new ArrayList<Monster>();
        bullets = new ArrayList<>();
        this.player = player;
        this.campfire = campfire;
        Person.setCampfire(campfire);
        generateCreatures();
    }

    public void update(GameContainer gc, int delta, ArrayList<Entity> entities) {
        handleBullets(gc, delta, entities);
        removeDeadCreatures(entities);
        updateCreatures(gc,delta);
        //System.out.println(campMembers.size());
        int campFactor = campMembers.size()-3;
        if(campFactor < 1) {
        	campFactor = 1;
        }
        if (Math.random() / (campFactor) / delta < .00002) {
        	System.out.println(campMembers.size() + " added monster");
            createMonster();
        }
        
        if (Math.random() / delta < .00008) {
        	System.out.println("Added Person");
            createPerson();
        }

        if (gc.getInput().isMousePressed(0)) {
        	selectedPerson = null;
            boolean shoot = true;
            for (Person p : campMembers) {
            	p.setSelect(false);
                if (p.checkToFeed(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
                    if (player.getFood() > 0) {
                        player.incrementFood(-1);
                        p.feed();
                    }
                    p.setSelect(true);
                    shoot = false;
                }
            }
            if (shoot) {
                shootBullet(gc);
            }
        }
        
        if (gc.getInput().isMousePressed(1)) {
            for (Person p : campMembers) {
                if(p.checkClicked(gc.getInput().getMouseX() + mainCamera.getX(),gc.getInput().getMouseY() + mainCamera.getY())) {
                	p.setSelect(true);
                	selectedPerson = p;
                } else {
                	p.setSelect(false);
                }
            }
        }
        
        if(gc.getInput().isKeyPressed(gc.getInput().KEY_E)) {
        	 if (player.getFood() > 0) {
                 player.incrementFood(-1);
                 player.heal(2);
             }
        }
                
        if(gc.getInput().getMouseX() > mainCamera.getWidth()/2) {
        	player.setDirGun("right");
        } else {
        	player.setDirGun("left");
        }
        
        if(campfire.getX() - player.getX() > 500) {
        	campfireDir = "East";
        } else if (campfire.getX() - player.getX() < -500) {
        	campfireDir = "West";
        } else if (campfire.getY() - player.getY() > 500) {
        	campfireDir = "South";
        } else if (campfire.getY() - player.getY() <-500) {
        	campfireDir = "North";
        } else {
        	campfireDir = null;
        }
    }

    private void shootBullet(GameContainer gc) {
        /*float xComponent = gc.getInput().getMouseX() - gc.getWidth() / 2;
        float yComponent = gc.getInput().getMouseY() - (gc.getHeight() / 2);*/
    	float xComponent = gc.getInput().getMouseX() - (player.getX() + player.getWidth() / 2) + mainCamera.getX();
    	float yComponent = gc.getInput().getMouseY() - (player.getY() + player.getHeight() / 2 + 10) + mainCamera.getY();
        Vector2f velocity = new Vector2f(xComponent, yComponent);
        bullets.add(new Bullet(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2 + 10, ImageRes.bulletImg, velocity));
    }

    private void handleBullets(GameContainer gc, int delta, ArrayList<Entity> staticentities) {
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet b = bullets.get(i);
            b.update(gc, delta);
            if (Math.abs(b.getX() - player.getX()) > gc.getWidth() * 2 || Math.abs(b.getX() - player.getX()) > gc.getWidth() * 2) {
                bullets.remove(b);
            } else {
                for (Entity s : staticentities)
                    if (b.getCollider().collidesWith(s.getCollider()) && !(s instanceof Food))
                            bullets.remove(b);
                for(Creature s: monsters) {
                    if (b.getCollider().collidesWith(s.getCollider())){
                            s.hurt(Bullet.DAMAGE);
                            bullets.remove(b);
                    }
                }
                
                for(Creature s: campMembers) {
                    if (b.getCollider().collidesWith(s.getCollider())){
                            s.hurt(Bullet.DAMAGE);
                            bullets.remove(b);
                    }
                }
            }
        }
    }
    
    public void setCamera(Camera camera) {
    	mainCamera = camera;
    }
    public void createPerson() {
        Person campmember = new Person((float) Math.random() * WIDTH, (float) Math.random() * HEIGHT, ImageRes.getRandomPerson(), (float) Math.random() * 30f + 60f, nameGenerator.next());
        campMembers.add(campmember);
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    private void createMonster() {
        Monster m = new Monster((float) Math.random() * WIDTH, (float) Math.random() * HEIGHT, ImageRes.monsterImg, Monster.STARTING_HEALTH);
        monsters.add(m);
        m.setTarget(player);
        Person.monsters.add(m);
    }

    public void generateCreatures() {
        for (int i = 0; i < 6; i++) {
            createPerson();
        }
        for (int i = 0; i < 4; i++)
            createMonster();
    }

    private void removeDeadCreatures(ArrayList<Entity> entities) {
        for (int i = campMembers.size() - 1; i >= 0; i--)
            if (campMembers.get(i).isDead()) {
                entities.add(new Food(campMembers.get(i).getX(),campMembers.get(i).getY(),Food.Type.MEAT,ImageRes.meatImg));
                campMembers.remove(i);
            }
        for (int i = monsters.size() - 1; i >= 0; i--)
            if (monsters.get(i).isDead() ) {
            	if(Math.random() < 0.33) {
            		entities.add(new Food(monsters.get(i).getX(),monsters.get(i).getY(),Food.Type.MEAT,ImageRes.meatImg));
            	}
                Person.monsters.remove(monsters.get(i));
                monsters.remove(i);
            }
    }

    private void updateCreatures(GameContainer gc, int delta) {
        for (Monster m : monsters) {
            m.update(gc, delta);
            Creature closestTarget = player;
            for (Person p : campMembers) {
                if (m.distanceTo(p) < m.distanceTo(closestTarget)) {
                    closestTarget = p;
                }
            }
            m.setTarget(closestTarget);
        }

        for (Person p : campMembers) {
            p.update(gc,delta);
        }
    }

    
	public String getCampfireDir() {
		return campfireDir;
	}

	public Person getSelectedPerson() {
		return selectedPerson;
	}
    
    
}
