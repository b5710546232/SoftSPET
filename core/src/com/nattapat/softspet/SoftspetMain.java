package com.nattapat.softspet;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.nattapat.softspet.screens.PlayScreen;
import com.nattapat.softspet.screens.FoodScreen;
import com.nattapat.softspet.util.Assets;

public class SoftspetMain extends Game {
	private PlayScreen gameScreen;
	private Screen testScreen;
	@Override
	public void create () {
		Assets.instance.init(new AssetManager());
		Assets.instance.load();
		gameScreen = new PlayScreen(this);
		testScreen = new FoodScreen(this);
	this.setScreen(testScreen);
	}
	public PlayScreen getGameScreen(){
		return gameScreen;
	}
	public Screen getTestScreen(){
		return  testScreen;
	}

	@Override
	public void dispose() {
		super.dispose();
		Assets.instance.dispose();
	}
}
