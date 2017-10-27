package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.responseModel.GameId;
import com.example.responseModel.GameInfo;
import com.example.responseModel.GameStaistic;
import com.example.service.GameService;

//TODO LOGIANJE APILACIJE
//TODO BAZA 
//TODO VIEW
@RestController
public class GameRestService {

	@Autowired
	private GameService gameService;
	
	
	@RequestMapping("/game/new")
	public GameId greeting(
			@RequestParam(value = "first", defaultValue = GameService.COMPUTER) String player1,
			@RequestParam(value = "second", defaultValue = GameService.COMPUTER) String player2,
			@RequestParam(value = "level", defaultValue = GameService.LEVEL_EASY) String level) {

		return gameService.inicializeGame(player1, player2, level);
	}

	@RequestMapping("/game/status")
	public GameInfo greeting(@RequestParam(value = "gameId") int id) {

		return gameService.findGameStatus(id);

	}

	@RequestMapping("/game/play")
	public GameInfo gamePlay(
			@RequestParam(value = "gameId") int gameId,
			@RequestParam(value = "gameId") int row,
			@RequestParam(value = "gameId") int column) {

		gameService.playGame(gameId, row, column);
		 // ako je false imamo pobjednika ili je krivi potez 
		return gameService.findGameStatus(gameId);
	}

	@RequestMapping("/game/statistic")
	public GameStaistic gameStaistic(
			@RequestParam(value = "playerName", defaultValue = GameService.ALL_PLAYERS) String playerName) {

		return gameService.findGameStaistic(playerName);

	}

}
