package com.ludumdare40;

import org.newdawn.slick.Image;

public class StaticEntity extends Entity{
    public enum Type{TREE,ROCK}

    public StaticEntity(float x, float y, Type type, Image img) {
        super(x, y, img);
        switch (type) {
            case TREE:
                collider.setWidth(32);
                collider.setHeight(32);
                break;
            case ROCK:
                collider.setWidth(32);
                collider.setHeight(32);
                break;
        }
    }
    
}
