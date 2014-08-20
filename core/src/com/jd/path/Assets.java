package com.jd.path;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Assets {
	//Textures, Sprites
	public static Texture blackSquare;
	public static Texture redSquare;
	public static Texture sSquare;
	public static Texture follower;
	public static Sprite followSprite;
	
	//Maps
	public static TiledMap map;
	public static TiledMap map2;
	
	public static void load() {
		blackSquare = new Texture(Gdx.files.internal("blacksquare.png"));
		redSquare = new Texture(Gdx.files.internal("redsquare.png"));
		sSquare = new Texture(Gdx.files.internal("startsquare.png"));
		follower = new Texture(Gdx.files.internal("ShotAMod.png"));
		followSprite = new Sprite(follower);
		map = new TmxMapLoader().load("level1.tmx");
		map2 = new TmxMapLoader().load("level2.tmx");
	}
}
