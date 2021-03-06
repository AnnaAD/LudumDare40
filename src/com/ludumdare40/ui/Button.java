package com.ludumdare40.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Button {
	private float width;
	private float height;
	private float x;
	private float y;
	private String text;
	
	public Button(float x, float y, float width, float height, String text) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.text = text;
	}
	
	public boolean checkClicked(int x, int y) {
		return (x > this.x) && (x <  this.x+ width) && (y > this.y) && (y < this.y + height);
	}
	
	public void render(GameContainer gc, Graphics g) {
		g.setColor(new Color(0x606060));
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawString(text, x + (width- gc.getDefaultFont().getWidth(text))/2, y + (height- gc.getDefaultFont().getHeight(text))/2);
	}
	
	public void render(GameContainer gc,Graphics g, float x, float y) {
		setX(x);
		setY(y);
		g.setColor(new Color(0x606060));
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawString(text, x + (width- gc.getDefaultFont().getWidth(text))/2, y + (height- gc.getDefaultFont().getHeight(text))/2);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public void setY(float y) {
		this.y = y;

	}

}
