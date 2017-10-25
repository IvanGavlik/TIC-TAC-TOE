package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Game;
import com.example.model.GameLevel;
import com.example.model.GameStatus;
import com.example.model.Player;
import com.example.responseModel.GameId;
import com.example.responseModel.GameInfo;
import com.example.responseModel.GameInfoFinished;
import com.example.responseModel.GameInfoInProges;
import com.example.responseModel.GameStaistic;

@Service
public class GameServiceImpl implements GameService {

	private HashMap<Integer, Game> games = new HashMap<>();
	private static int gameCounter = 0;
	private static final String COMPUTER = "computer";
	private static final String LEVEL_EASY = "easy";
	private static final String LEVEL_HARD = "hard";
	private static final String ALL_PLAYERS = "all";

	@Autowired
	private ComputerService computerService;
	
	public GameServiceImpl(ComputerService computerService) {
		this.computerService = computerService;
	}

	@Override
	public GameId inicializeGame(String player1, String player2, String level) {
		Game game = new Game();

		if (player1.equals(COMPUTER)) {
			game.setPlayerX(new Player(COMPUTER));
			game.setPlayerO(new Player(player2));
		} else {
			game.setPlayerX(new Player(player1));
			game.setPlayerO(new Player(COMPUTER));
		}

		if (level.equals(LEVEL_EASY) || level.equals(LEVEL_HARD)) {
			game.setLevel(GameLevel.valueOf(level.toUpperCase()));
		}
		game.setLevel(GameLevel.EASY);

		gameCounter += 1;
		game.setGameId(gameCounter);

		game.setGameStatus(GameStatus.IN_PROGRES);
		games.put(game.getGameId(), game);

		return new GameId(game.getGameId());
	}

	@Override
	public GameInfo findGameStatus(int id) {

		Game currentGame = games.get(id);
		if (currentGame == null) {
			// TODO Auto-generated method stub
		}

		GameInfo gameStatusData;
		if (currentGame.getGameStatus().equals(GameStatus.IN_PROGRES)) {
			gameStatusData = new GameInfoInProges(currentGame);
		} else if (currentGame.getGameStatus().equals(GameStatus.FINISHED)) {
			gameStatusData = new GameInfoFinished(currentGame);
		} else {
			throw new RuntimeException("GameInfoImpl: gameStatus unknown");
		}

		return gameStatusData;
	}

	@Override
	public void playGame(int gameId, int row, int column) {

		Game currentGame = games.get(gameId);
		if (currentGame == null) {
			return;
		}

		if (isMoveValid(currentGame.getBoard(), row, column)) {
			computerService.playMove(currentGame);
		}

	}

	private boolean isMoveValid(String[][] board, int row, int column) {

		if (checkInputPositionCordinates(row) && checkInputPositionCordinates(column)) {

			if (board[row][column] == null) {
				return true;
			}

		}
		return false;
	}

	private boolean checkInputPositionCordinates(int position) {
		if (position < 0 && position >= 4) {
			return false;
		}
		return true;
	}

	@Override
	public GameStaistic findGameStaistic(String playerName) {

		List<Player> players = new ArrayList<Player>();

		if (playerName.equals(ALL_PLAYERS)) {

			for (Game game : games.values()) {

				if (!game.getPlayerX().equals(COMPUTER)) {
					players.add(game.getPlayerX());
				}
				if (!game.getPlayerO().equals(COMPUTER)) {
					players.add(game.getPlayerO());
				}
			}

		} else {

			for (Game game : games.values()) {

				if (!game.getPlayerX().equals(COMPUTER) && game.getPlayerX().equals(playerName)) {
					players.add(game.getPlayerX());
				}
				if (!game.getPlayerO().equals(COMPUTER) && game.getPlayerX().equals(playerName)) {
					players.add(game.getPlayerO());
				}
			}

		}

		return new GameStaistic(players);
	}

}
