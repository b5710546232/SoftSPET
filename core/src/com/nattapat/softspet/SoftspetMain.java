package com.nattapat.softspet;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.nattapat.softspet.screens.GameScreen;
import com.nattapat.softspet.util.Assets;

public class SoftspetMain extends Game {

	@Override
	public void create () {
		Assets.instance.init(new AssetManager());
		Assets.instance.load();
		GameScreen gameScreen = new GameScreen();
		this.setScreen(gameScreen);
	}

	@Override
	public void dispose() {
		super.dispose();
		Assets.instance.dispose();
	}
}
