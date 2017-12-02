package com.ludumdare40.ui;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Panel {
	private ArrayList<Button> buttons;
	private ArrayList<Text> texts;
	private float x;
	private float y;
	private float width;
	private float height;
	
	public Panel(float x, float y, float width, float height, ArrayList<Button> b, ArrayList<Text> t) {
		texts = t;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void render(GameContainer gc, Graphics g) {
		g.setColor(new Color(0x202020));
		g.fillRect(x,y,width,height);
		
		for(Button b: buttons) {
			b.render(gc,g);
		}
		
		for(Text t: texts) {
			t.render(gc,g);
		}
	}
}
