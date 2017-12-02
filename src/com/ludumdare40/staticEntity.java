package com.ludumdare40;

import org.newdawn.slick.Image;

public class staticEntity extends Entity{
    public enum Type{TREE,ROCK}
    private final String treeImgLocation = "res/Tree.png";
    private final String rockImgLocation = "res/Rock.png";

    public staticEntity(float x, float y, Type type){
        super(x, y);
        switch(type){
            case TREE:
                img = loadImage(treeImgLocation);
                collider.setWidth(32);
                collider.setHeight(32);
                break;
            case ROCK:
                img = loadImage(rockImgLocation);
                collider.setWidth(32);
                collider.setHeight(32);
                break;
        }
    }

    
}
