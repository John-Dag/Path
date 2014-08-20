package com.jd.path;

import com.badlogic.gdx.utils.Array;

public class Entity {
	private Array<Square> path;
	float x, y;
	float stateTime;
	float movementSpeed;
	int type, startX, startY, endX, endY;
	int currentSquare;
	boolean isActive;
	public float originalX, originalY, nextX, nextY, t;
	int index;
	
	public void setPath(Array<Square> path) {
		this.path = path;
	}
	
	public Array<Square> getPath() {
		return path;
	}
	
	public Entity() {
		
	}
	
	public Entity(int type, int startX, int startY, int endX, int endY, float movementSpeed, boolean isActive) {
		this.x = startX;
		this.y = startY;
		this.startX = endX;
		this.startY = endY;
		this.endX = startX;
		this.endY = startY;
		this.type = type;
		this.path = new Array<Square>();
		this.stateTime = 0.0f;
		this.isActive = isActive;
		this.movementSpeed = movementSpeed;
		this.currentSquare = 0;
		this.originalX = 0;
		this.originalY = 0;
		this.nextX = 0;
		this.nextY = 0;
		this.t = 0;
		this.index = 0;
	}
	
	public void update(float deltaTime, int x, int y) {
		this.x = x;
		this.y = y;
		stateTime += deltaTime;
	}
}
