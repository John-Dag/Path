package com.jd.path;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;

public class Pathfinder {
	Array<Square> closedSet;	//Starting node
	Array<Square> openSet;	//Empty set
	Array<Square> result;
	Square[][] grid;
	TiledMap map;
	
	public Pathfinder(TiledMap map) {
		this.closedSet = new Array<Square>();
		this.openSet = new Array<Square>();
		this.result = new Array<Square>();
		this.grid = new Square[20][20];
		this.map = map;
		setTiles();
	}
	
	public void setTiles() {
		for (int i = 0; i < map.getLayers().getCount(); i++) {
			TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(i);
			
			for (int a = 0; a < layer.getHeight(); a++) {
				for (int b = 0; b < layer.getWidth(); b++) {
					if ((layer.getCell(a, b) != null) && (layer.getCell(a, b).getTile().getProperties().containsKey("blocked"))) {
						grid[a][b] = new Square(false, 1, a, b);
					}
					else
						grid[a][b] = new Square(true, 1, a, b);
				}
			}
		}
	}

	public Array<Square> pathfind(int startX, int startY, int endX, int endY) {
		int nextSquareX, nextSquareY;
		openSet.add(grid[startX][startY]);
		int tentative = 0;
		
		while (openSet.size > 0 && grid[endX][endY].active) {
			Square current = openSet.first();
			
			current.active = false;
			current.inOpen = false;
			closedSet.add(current);
			openSet.removeIndex(0);
			//System.out.println("Current X: " + current.xCoord + " Current Y: " + current.yCoord);
			
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					
					nextSquareX = i + current.xCoord;
					nextSquareY = j + current.yCoord;
					Square next = grid[nextSquareX][nextSquareY];
					tentative = current.g + heuristic(current.xCoord, current.yCoord, next.xCoord, next.yCoord);
					if ((valid(next.xCoord, next.yCoord)) &&
					    (next.active)) {
						//Add valid squares to open list if they aren't already in it
						if (!next.inOpen) {
							//Set next squares parent to the current square
							next.parent = current;
							next.inOpen = true;
							openSet.add(next);
							
							//Calculate heuristic for each of the eight squares
							next.h = heuristic(next.xCoord, next.yCoord, endX, endY);
							if (Math.abs(i) == Math.abs(j))
								next.g = next.parent.g + 14;
							else
								next.g = next.parent.g + 10;
							next.f = next.g + next.h;
						
							openSet.sort();
							
							//System.out.println("Square: " + i + "Heur: " + next.f);
							//System.out.println("Tentative: " + tentative);
						}
						
						else {
							if (tentative < next.g) {
								System.out.println("ELSE");
								next.parent = current;
								
								next.g = tentative;
								next.f = next.g + heuristic(next.xCoord, next.yCoord, endX, endY);
								
								openSet.sort();
								System.out.println("Square: " + i + "Heur: " + next.f);
							}
						}
					}
				}
			}
		}

		Square temp = grid[endX][endY];
		while (temp.parent != null) {
			//temp.parent.activePath = true;
			System.out.println("X Coord: " + temp.parent.xCoord + " Y Coord: " + temp.parent.yCoord);
			result.add(temp.parent);
			temp = temp.parent;
		}
		
		return result;
	}
	
	public boolean valid(int x, int y) {
		if ((x <= 0) || (y <= 0) || ((x >= 19) || (y >= 19)))
			return false;
		else
			return true;
	}
	
	public int heuristic(int startX, int startY, int endX, int endY) {
		int d = 10;
		int x = Math.abs(startX - endX);
		int y = Math.abs(startY - endY);
		
		return d * (x + y);
	}
}
