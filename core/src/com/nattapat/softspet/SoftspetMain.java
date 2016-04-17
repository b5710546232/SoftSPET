package com.nattapat.softspet;

import com.badlogic.gdx.Game;
import com.nattapat.softspet.screens.GameScreen;
import com.nattapat.softspet.util.Assets;

public class SoftspetMain extends Game {

	@Override
	public void create () {
		Assets.load();
		GameScreen gameScreen = new GameScreen();
		this.setScreen(gameScreen);

	}

	@Override
	public void dispose() {
		super.dispose();
		Assets.dispose();
	}
}
