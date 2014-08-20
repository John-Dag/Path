package com.jd.path;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.equations.Quad;

import com.badlogic.gdx.utils.Array;

public class World {
	private Pathfinder pathfind;
	private Array<Entity> entities;
	public float timer;
	
	public Array<Entity> getEntities() {
		return entities;
	}
	
	public Pathfinder getPathfind() {
		return pathfind;
	}
	
	public Array<Square> getPaths(Entity entity) {
		System.out.println(entity.getPath().size);
		return getPathfind().pathfind(entity.startX, entity.startY, entity.endX, entity.endY);
	}
	
	public World() {
		entities = new Array<Entity>();
		pathfind = new Pathfinder(Assets.map2);
		timer = 0;
	}
	
	public void populate() {
		Entity entity = new Entity(1, 1, 1, 18, 18, 0.2f, true);
		entity.setPath(getPaths(entity));
		entities.add(entity);
	}
	
	public void updateEntities(float deltaTime) {
		if (entities.size > 0) {
			for (int i = 0; i < entities.size; i++) {
				Entity entity = entities.get(i);
				
				if (entity.isActive) {
					moveEntity(entity);
				}
			}
		}
	}
	
	public void moveEntity(Entity entity) {
		if (entity.getPath().size > 0) {
			if (entity.index == 0) {
				entity.nextX = entity.getPath().get(0).xCoord;
				entity.nextY = entity.getPath().get(0).yCoord;
				Tween.to(entity, EntityAccessor.POSITION_XY, 0.1f)
				.target(entity.nextX, entity.nextY)
				.ease(Quad.INOUT)
				.start(Path.manager);
			}
			else if (entity.x == entity.nextX && entity.y == entity.nextY) {
				entity.nextX = entity.getPath().get(0).xCoord;
				entity.nextY = entity.getPath().get(0).yCoord;
				entity.getPath().removeIndex(0);
				
				Tween.to(entity, EntityAccessor.POSITION_XY, 0.1f)
				.target(entity.nextX, entity.nextY)
				.ease(TweenEquations.easeNone)
				.start(Path.manager);
			}
			
			entity.index++;
		}
	}
	
	public void update(float deltaTime) {
		timer += deltaTime;
		
		updateEntities(timer);
	}
}
