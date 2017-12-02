package com.ludumdare40;

public class Collider {
	private float width;
	private float height;
	private Entity parent;
	
	public Collider(Entity parent, float width, float height) {
		this.parent = parent;
		this.width = width;
		this.height = height;
	}
	
	public boolean checkCol(Collider c) {
		Collider rect1 = this;
		Collider rect2 = c;
		return rect1.getX() < rect2.getX() + rect2.getWidth() &&
				   rect1.getX() + rect1.getWidth() > rect2.getX() &&
				   rect1.getY() < rect2.getY() + rect2.getHeight() &&
				   rect1.getHeight() + rect1.getY() > rect2.getY();
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getX() {
		return parent.getX();
	}

	public float getY() {
		return parent.getY();
	}


}
