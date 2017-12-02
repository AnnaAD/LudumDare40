package com.ludumdare40.com.entities;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Bullet extends Entity {
    //The speed of the bullets
    private final float SPEED = .02f;
    public Vector2f velocity;
    /** Age in ms*/
    public int age;

    /**
     * @param velocity The unscaled velocity used to represent the direction
     */
    public Bullet(float x, float y, Image img, Vector2f velocity) {
        super(x, y, img);
        this.velocity = (Vector2f)(velocity.scale(SPEED/velocity.length()));
        age = 0;
    }

    public void update(GameContainer gc, int delta){
        age += delta;
        x += velocity.x * delta;
        y += velocity.y * delta;
    }

    public void render(GameContainer gc, Graphics g) {
        img.draw(x,y);
    }
}
