package com.jd.path;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;

public class Path extends Game {
	public static TweenManager manager = new TweenManager();

	@Override
	public void create () {
		Assets.load();
		Tween.registerAccessor(Entity.class, new EntityAccessor());
		setScreen(new GridRender(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose() {
		getScreen().dispose();
		super.dispose();
	}
}
