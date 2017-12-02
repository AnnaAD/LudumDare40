package com.ludumdare40;

import org.newdawn.slick.Image;

public class Food extends Entity {
    private int foodValue;
    protected enum Type {BERRY};

    public Food(int x, int y, Image img, Type type) {
        super(x, y, img);
        switch(type) {
            case BERRY:
                foodValue = 1;
                break;
        }
    }

    public int getFoodValue() {
        return foodValue;
    }
}
