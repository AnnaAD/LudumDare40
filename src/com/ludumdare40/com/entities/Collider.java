package com.ludumdare40.com.entities;

public class Collider {
	private float width;
	private float height;
	private Entity parent;
	private float offsetX;
	private float offsetY;
	
	public Collider(Entity parent, float width, float height) {
		this.parent = parent;
		this.width = width;
		this.height = height;
	}
	
	public Collider(Entity parent, float offsetX, float offsetY, float width, float height) {
		this.parent = parent;
		this.width = width;
		this.height = height;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	
	public boolean collidesWith(Collider c) {
		Collider rect1 = this;
		Collider rect2 = c;
		return rect1.getX() < rect2.getX() + rect2.getWidth() &&
				   rect1.getX() + rect1.getWidth() > rect2.getX() &&
				   rect1.getY() < rect2.getY() + rect2.getHeight() &&
				   rect1.getHeight() + rect1.getY() > rect2.getY();
	}
	
	public boolean willCollideWith(Collider c, float deltaX, float deltaY) {
		Collider rect1 = this;
		Collider rect2 = c;
		return rect1.getX()+deltaX < rect2.getX() + rect2.getWidth() &&
				   rect1.getX()+deltaX + rect1.getWidth() > rect2.getX() &&
				   rect1.getY()+deltaY < rect2.getY() + rect2.getHeight() &&
				   rect1.getHeight() + rect1.getY() + deltaY > rect2.getY();
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

	public void setOffsetX(float offsetX) {
		this.offsetX = offsetX;
	}

	public void setOffsetY(float offsetY) {
		this.offsetY = offsetY;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getX() {
		return parent.getX() + offsetX;
	}

	public float getY() {
		return parent.getY() + offsetY;
	}


}
