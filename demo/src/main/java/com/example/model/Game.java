package com.example.model;

public class Game { 
	
	private int gameId;
	private String[][] board = { { "", "", "" }, { "", "", "" }, { "", "", "" } };
	private Player firstPlayer;
	private Player secondPlayer;
	private GameStatus gameStatus;
	private GameLevel level;
	private GameWiner winer;
	private Player currentPlayer;

	
	// geter and seters 
	public Player getFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}
	public void setSecondPlayer(Player secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public GameWiner getWiner() {
		return winer;
	}
	public void setWiner(GameWiner winer) {
		this.winer = winer;
	}

	public GameLevel getLevel() {
		return level;
	}
	public void setLevel(GameLevel level) {
		this.level = level;
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

	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

}
