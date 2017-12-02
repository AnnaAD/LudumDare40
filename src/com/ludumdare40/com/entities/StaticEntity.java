package com.ludumdare40.com.entities;

import com.ludumdare40.com.entities.Entity;
import org.newdawn.slick.Image;

public class StaticEntity extends Entity {
    public enum Type{TREE,ROCK,CAMPFIRE}

    public StaticEntity(float x, float y, Type type, Image img) {
        super(x, y, img);
        switch (type) {
            case TREE:
                collider.setOffsetX(24);
                collider.setOffsetY(169);
                collider.setWidth(50);
                collider.setHeight(img.getHeight()-169);
                break;
            case ROCK:
                 collider.setOffsetY((int) (img.getHeight()/1.5));
                 collider.setHeight(img.getHeight() - (int) (img.getHeight()/1.5));
                break;
            case CAMPFIRE:


                break;
        }
    }
    
}
