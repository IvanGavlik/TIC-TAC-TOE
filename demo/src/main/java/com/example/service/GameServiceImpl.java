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

	@Autowired
	private ComputerService computerService;
	
	public GameServiceImpl(ComputerService computerService) {
		this.computerService = computerService;
	}

	@Override
	public GameId inicializeGame(String player1, String player2, String level) {
		Game game = new Game();
		
		if(player1.equals(COMPUTER) && player2.equals(COMPUTER)) {
			throw new RuntimeException(); //TODO BAD REQUEST
		}
		
		if (player1.equals(COMPUTER)) {
			game.setFirstPlayer(new Player(COMPUTER));
			game.setSecondPlayer(new Player(player2));
		} else {
			game.setFirstPlayer(new Player(player1));
			game.setSecondPlayer(new Player(COMPUTER));
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
			throw new RuntimeException(); //TODO BAD REQUEST
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
			throw new RuntimeException(); //TODO BAD REQUEST
		}

		if (isMoveValid(currentGame.getBoard(), row, column)) {
			computerService.playMove(currentGame,row,column);
		}

	}

	private boolean isMoveValid(String[][] board, int row, int column) {

		if (checkInputPositionCordinates(row) && checkInputPositionCordinates(column)) {

			if (board[row][column] == ComputerService.EMPTHY) {
				return true;
			}

		}
		return false;
	}

	private boolean checkInputPositionCordinates(int position) {
		if (position >= 0 && position <= 3) {
			return true;
		}
		return false;
	}

	@Override
	public GameStaistic findGameStaistic(String playerName) {

		List<Player> players = new ArrayList<Player>();

		if (playerName.equals(ALL_PLAYERS)) {

			for (Game game : games.values()) {

				if (!game.getFirstPlayer().equals(COMPUTER)) {
					players.add(game.getFirstPlayer());
				}
				if (!game.getSecondPlayer().equals(COMPUTER)) {
					players.add(game.getSecondPlayer());
				}
			}

		} else {

			for (Game game : games.values()) {

				if (!game.getFirstPlayer().equals(COMPUTER) && game.getFirstPlayer().equals(playerName)) {
					players.add(game.getFirstPlayer());
				}
				if (!game.getFirstPlayer().equals(COMPUTER) && game.getFirstPlayer().equals(playerName)) {
					players.add(game.getFirstPlayer());
				}
			}

		}

		return new GameStaistic(players);
	}

}
