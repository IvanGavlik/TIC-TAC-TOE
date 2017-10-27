package com.example.model;

public class Game {

	private int gameId;
	private String[][] board = { { "", "", "" }, { "", "", "" }, { "", "", "" } };
	public String[] board2 = { "", "", "" };
	private Player playerX;
	private Player playerO;
	private GameStatus gameStatus;
	private GameLevel level;
	private GameWiner winer;
	private Player currentPlayer;

	public void initBoard(String[][] board) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				this.board[i][j] = board[i][j];

			}
		}
	}

	public boolean isCurrentPlayerX() {
		return currentPlayer.equals(playerX);
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

	public Player getPlayerX() {
		return playerX;
	}

	public void setPlayerX(Player playerX) {
		this.playerX = playerX;
	}

	public Player getPlayerO() {
		return playerO;
	}

	public void setPlayerO(Player playerO) {
		this.playerO = playerO;
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
