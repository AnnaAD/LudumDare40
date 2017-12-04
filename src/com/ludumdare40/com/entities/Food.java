package com.ludumdare40.com.entities;

import com.ludumdare40.com.entities.Entity;
import org.newdawn.slick.Image;

public class Food extends Entity {
    private int foodValue;
    public enum Type {BERRY,MUSHROOM,MEAT};

    public Food(float x, float y, Type type, Image img) {
        super(x, y, img);
        switch(type) {
            case BERRY:
                foodValue = 1;
                break;
            case MUSHROOM:
                foodValue = 2;
                break;
            case MEAT:
            	foodValue = 3;
            	break;
        }
    }

    public int getFoodValue() {
        return foodValue;
    }
}
