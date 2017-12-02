package com.ludumdare40;

import org.newdawn.slick.Image;

public class StaticEntity extends Entity{
    public enum Type{TREE,ROCK}
    private final String treeImgLocation = "res/Tree.png";
    private final String rockImgLocation = "res/Rock.png";

    public StaticEntity(float x, float y, Type type, Image img) {
        super(x, y, img);
        switch (type) {
            case TREE:
                try {
                    img = new Image(treeImgLocation);
                } catch (Exception e) {
                    System.out.println("Could not load the tree Image");
                }
                collider.setWidth(32);
                collider.setHeight(32);
                break;
            case ROCK:
                try {
                    img = new Image(rockImgLocation);
                } catch (Exception e) {
                    System.out.println("Could not load the Rock Image");
                }
                collider.setWidth(32);
                collider.setHeight(32);
                break;
        }
    }
    
}
