package com.jd.path;

import com.badlogic.gdx.math.Rectangle;

public class Square implements Comparable<Object> {
	public final int SQUARE_WIDTH = 32;
	public final int SQUARE_HEIGHT = 32;
	public int xCoord = 0;
	public int yCoord = 0;
	public int type = 0;
	public boolean active;
	public boolean activePath;
	public boolean starter;
	public boolean inOpen;
	Square parent = null;
	Rectangle bounds;
	int f, g, h;
	
	public Square() {
		activePath = false;
		starter = false;
	}
	
	public Square(boolean active, int type, int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.active = active;
		this.type = type;
		this.g = 0;
		this.f = 0;
		this.h = 0;
		this.bounds = new Rectangle(xCoord * SQUARE_WIDTH, yCoord * SQUARE_HEIGHT, SQUARE_WIDTH, SQUARE_HEIGHT);
		activePath = false;
		starter = false;
		inOpen = false;
	}
	
	@Override
	public int compareTo(Object other) {
		Square o = (Square) other;
		return(f - o.f);
	}
}
