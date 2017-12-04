package com.ludumdare40.ui;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Panel {
	private float width;
	private float height;
	private float x;
	private float y;
	private String text;
	private ArrayList<Button> buttons;
	private ArrayList<Text> texts;
	
	
	public Panel(float x, float y, float width, float height, ArrayList<Button> buttons, ArrayList<Text> texts) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.buttons = buttons;
		this.texts = texts;
	}
	
	public void render(GameContainer gc, Graphics g) {
		g.setColor(new Color(0x202020));
		g.fillRect(x, y, width, height);
		for(Text t:texts) {
			t.render(g);
		}
		
		for(Button b : buttons) {
			b.render(gc, g);
		}
	}
	
	public void render(GameContainer gc, Graphics g, float x, float y) {
		g.setColor(new Color(0x202020));
		g.fillRect(x, y, width, height);
		for(Text t:texts) {
			t.render(g);
		}
		
		for(Button b : buttons) {
			b.render(gc, g);
		}
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
	
	public ArrayList<Button> getButtons() {
		return buttons;
	}
}
