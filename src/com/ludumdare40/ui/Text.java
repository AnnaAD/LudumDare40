package com.ludumdare40.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Text {
	private float x;
	private float y;
	private String text;
	
	public Text(float x, float y, String text) {
		this.x = x;
		this.y = y;
		this.text = text;
	}
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.drawString(text, x, y);
	}
	public void setText(String text) {
		this.text = text;
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
	public void setY(float y) {
		this.y = y;
	}
}
