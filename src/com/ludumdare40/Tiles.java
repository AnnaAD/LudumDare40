package com.ludumdare40;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Tiles {
	private int x;
	private int y;



	private Image img;
	
	public Tiles(int x, int y, Image img) {
		this.x = x;
		this.y = y;
		this.img = img;
	}
	
	public void render(Graphics g, float x, float y) {
		g.drawImage(img, x, y);
	}
	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return img.getWidth();
	}
	
	public int getHeight() {
		return img.getHeight();
	}
}
