package com.ludumdare40.com.entities;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Bullet extends Entity {
    public Vector2f velocity;
    /** Age in ms*/
    public int age;

    public Bullet(float x, float y, Image img, Vector2f velocity) {
        super(x, y, img);
        this.velocity = velocity;
        age = 0;
    }

    public void update(GameContainer gc, int delta){
        age += delta;
        x += velocity.x;
        y += velocity.y;
    }
}
