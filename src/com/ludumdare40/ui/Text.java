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
	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.black);
		g.drawString(text, x + g.getFont().getWidth(text)/2, y+g.getFont().getHeight(text)/2);
	}
}
