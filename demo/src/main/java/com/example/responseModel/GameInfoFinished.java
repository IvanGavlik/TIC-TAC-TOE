package com.example.responseModel;

import com.example.model.Game;

public class GameInfoFinished extends GameInfo {

	private String winer;

	public GameInfoFinished(Game game) {
		super(game);
		this.winer = game.getWiner().toString();
	}

	public String getWiner() {
		return winer;
	}

	public void setWiner(String winer) {
		this.winer = winer;
	}
}
