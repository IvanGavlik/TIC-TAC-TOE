package com.example.responseModel;

import com.example.model.Game;
import com.example.model.GameStatus;

public abstract class GameInfo {
	private int gameId;
	private GameStatus gameStatus;
	private String[][] board;

	public GameInfo(Game game) {
		this.gameId = game.getGameId();
		this.gameStatus = game.getGameStatus();
		this.board = game.getBoard();
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public String[][] getBoard() {
		return board;
	}

	public void setBoard(String[][] board) {
		this.board = board;
	}
}
