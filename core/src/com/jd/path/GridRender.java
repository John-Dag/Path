package com.jd.path;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

public class GridRender implements Screen {
	float unitScale = 1f;
	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera camera;
	SpriteBatch batch;
	World world;
	Pathfinder pathfinder;
	Vector3 touchPoint;
	Boolean touchSet;
	
	public GridRender(Path game) {
		renderer = new OrthogonalTiledMapRenderer(Assets.map2, unitScale);
		camera = new OrthographicCamera(640, 640);
		camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		world = new World();
		world.populate();
		touchPoint = new Vector3();
		touchSet = false;
	}
	
	public void renderEntities() {
		world.update(Gdx.graphics.getDeltaTime());
		for (int i = 0; i < world.getEntities().size; i++) {
			Entity entity = world.getEntities().get(i);
		
			if (entity.type == 1)
				batch.draw(Assets.follower, entity.x * 32, entity.y * 32);
		}
	}

	@Override
	public void render(float delta) {
		Path.manager.update(delta);
		Gdx.graphics.getGL20().glClearColor( 1, 0, 0, 1 );
		renderer.render();
		camera.update();
		renderer.setView(camera);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.enableBlending();
		renderEntities();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}

/*
public class GridRender {
	OrthographicCamera camera;
	SpriteBatch batch;
	public static Square[][] grid = new Square[100][100];
	Pathfinder pathfind;
	Vector3 touchPoint;
	Square startSquare;
	Entity follower;
	ArrayList<Entity> entities;
	int startX = 2, startY = 2, endX, endY;
	
	public GridRender() {
		camera = new OrthographicCamera(1600, 1600);
		camera.position.set(800, 800, 0);
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		generateGrid();
		startSquare = new Square(false, 5, startX, startY);
		startSquare.starter = true;
		grid[startSquare.xCoord][startSquare.yCoord] = startSquare;
		touchPoint = new Vector3();
		follower = new Entity(startX, startY);
		entities = new ArrayList<Entity>();
		entities.add(follower);
		pathfind = new Pathfinder(grid);
	}
	
	public void generateGrid() {	
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				int x = (int) (Math.random() * 5 + 1);
				Square temp;
				if (x == 1)
					temp = new Square(false, x, i, j);
				else
					temp = new Square(true, x, i, j);
				grid[i][j] = temp;
			}
		}
	}
	
	public static boolean pointInRectangle (Rectangle r, float x, float y) {
	    return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}
	
	public void render() {
		Gdx.graphics.getGL20().glClearColor( 1, 0, 0, 1 );
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.enableBlending();
		Entity entity = entities.get(0);
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				Square temp = grid[i][j];
				
				if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
					touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
					camera.unproject(touchPoint);
					
					if (temp.active && pointInRectangle(temp.bounds, touchPoint.x, touchPoint.y)) {
						temp.type = 1;
						temp.active = false;
					}
				}
					
				if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
					touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
					camera.unproject(touchPoint);
					if (temp.active && pointInRectangle(temp.bounds, touchPoint.x, touchPoint.y)) {
						endX = temp.xCoord;
						endY = temp.yCoord;
						System.out.println("End X: " + endX + " End Y; " + endY);
						pathfind.pathfind(startX, startY, endX, endY);
					}
				}

				if (temp.type == 1)
					batch.draw(Assets.blackSquare, temp.xCoord * 16, temp.yCoord * 16);
				else if (temp.starter)
					batch.draw(Assets.sSquare, temp.xCoord * 16, temp.yCoord * 16);
				else if (temp.activePath)
					batch.draw(Assets.sSquare, temp.xCoord * 16, temp.yCoord * 16);

				else
					batch.draw(Assets.redSquare, temp.xCoord * 16, temp.yCoord * 16);
				batch.draw(Assets.followSprite, entity.x * 16, entity.y * 16);
			}
		}
		batch.end();
	}
}
*/
