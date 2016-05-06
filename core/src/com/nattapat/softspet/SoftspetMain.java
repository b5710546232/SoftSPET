package com.nattapat.softspet;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.nattapat.softspet.screens.FoodScreen;
import com.nattapat.softspet.screens.MiniGameScreen;
import com.nattapat.softspet.screens.PlayScreen;
import com.nattapat.softspet.util.Assets;

public class SoftspetMain extends Game {
	private PlayScreen playScreen;
	private FoodScreen foodScreen;
	private MiniGameScreen miniGameScreen;
	@Override
	public void create () {
		Assets.instance.init(new AssetManager());
		Assets.instance.load();
		playScreen = new PlayScreen(this);
		foodScreen = new FoodScreen(this);
		miniGameScreen = new MiniGameScreen(this);
	this.setScreen(playScreen);
	}
	public PlayScreen getPlayScreen(){
		return playScreen;
	}
	public FoodScreen getFoodScreen(){
		return foodScreen;
	}
	public MiniGameScreen getMiniGameScreen(){
		return miniGameScreen;
	}

	@Override
	public void dispose() {
		super.dispose();
		Assets.instance.dispose();
	}
}
