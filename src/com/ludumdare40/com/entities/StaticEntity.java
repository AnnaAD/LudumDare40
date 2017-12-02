package com.ludumdare40.com.entities;

import com.ludumdare40.com.entities.Entity;
import org.newdawn.slick.Image;

public class StaticEntity extends Entity {
    public enum Type{TREE,ROCK}

    public StaticEntity(float x, float y, Type type, Image img) {
        super(x, y, img);
        switch (type) {
            case TREE:
                collider.setOffsetX(24);
                collider.setOffsetY(168);
                collider.setWidth(50);
                collider.setHeight(210-168);
                break;
            case ROCK:

                break;
        }
    }
    
}
