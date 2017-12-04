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
        Person.setCampfire(campfire);
        generateCreatures();

    }

    public void update(GameContainer gc, int delta, ArrayList<Entity> entities) {
        handleBullets(gc, delta, entities);
        removeDeadCreatures();
        updateCreatures(gc,delta);
        if (Math.random() * campMembers.size() / delta < .0005) {
            createMonster();
        }
        if (Math.random() / delta < .0001) {
            createPerson();
        }

        if (gc.getInput().isMousePressed(0)) {
            boolean shoot = true;
            for (Person p : campMembers) {
                if (p.checkToFeed(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
                    if (player.getFood() > 0) {
                        player.incrementFood(-1);
                        p.feed();
                    }
                    shoot = false;
                }
            }
            if (shoot) {
                shootBullet(gc);
            }
        }
    }

    private void shootBullet(GameContainer gc) {
        float xComponent = gc.getInput().getMouseX() - gc.getWidth() / 2;
        float yComponent = gc.getInput().getMouseY() - gc.getHeight() / 2;
        Vector2f velocity = new Vector2f(xComponent, yComponent);
        bullets.add(new Bullet(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, ImageRes.bulletImg, velocity));
    }

    private void handleBullets(GameContainer gc, int delta, ArrayList<Entity> staticentities) {
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet b = bullets.get(i);
            b.update(gc, delta);
            if (Math.abs(b.getX() - player.getX()) > gc.getWidth() * 2 || Math.abs(b.getX() - player.getX()) > gc.getWidth() * 2) {
                bullets.remove(b);
            } else {
                for (Entity s : staticentities)
                    if (b.getCollider().collidesWith(s.getCollider()))
                            bullets.remove(b);
                for(Creature s: monsters) {
                    if (b.getCollider().collidesWith(s.getCollider())){
                            s.hurt(Bullet.DAMAGE);
                            bullets.remove(b);
                    }
                }
            }
        }
    }

    public void createPerson() {
        Person campmember = new Person((float) Math.random() * WIDTH, (float) Math.random() * HEIGHT, ImageRes.getRandomPerson(), (float) Math.random() * 30f + 60f, nameGenerator.next());
        campMembers.add(campmember);
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    private void createMonster() {
        Monster m = new Monster((float) Math.random() * WIDTH, (float) Math.random() * HEIGHT, ImageRes.monsterImg, 10);
        monsters.add(m);
        m.setTarget(player);
        Person.monsters.add(m);
    }

    public void generateCreatures() {
        for (int i = 0; i < 5; i++) {
            createPerson();
        }
        for (int i = 0; i < 5; i++)
            createMonster();
    }

    private void removeDeadCreatures() {
        for (int i = campMembers.size() - 1; i >= 0; i--)
            if (  campMembers.get(i).isDead())
                    campMembers.remove(i);
        for (int i = monsters.size() - 1; i >= 0; i--)
            if (  monsters.get(i).isDead() ) {
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
}
