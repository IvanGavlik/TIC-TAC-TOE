package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.responseModel.GameId;
import com.example.responseModel.GameInfo;
import com.example.responseModel.GameStaistic;
import com.example.service.GameService;


@RestController
public class GameRestService {

	@Autowired
	private GameService gameService;
	
	@RequestMapping("/g")
	public GameId greeting(@RequestParam(value = "first", defaultValue = "computer") String player1) {

		return gameService.inicializeGame(player1, null, null);
	}

	
	@RequestMapping("/game")
	public GameId greeting(@RequestParam(value = "first", defaultValue = "computer") String player1,
			@RequestParam(value = "first") String player2,
			@RequestParam(value = "level", defaultValue = "easy") String level) {

		return gameService.inicializeGame(player1, player2, level);
	}

	@RequestMapping("/game/status")
	public GameInfo greeting(@RequestParam(value = "gameId") int id) {

		return gameService.findGameStatus(id);

	}

	@RequestMapping("/game/play")
	public GameInfo gamePlay(@RequestParam(value = "gameId") int gameId, @RequestParam(value = "gameId") int row,
			@RequestParam(value = "gameId") int column) {

		gameService.playGame(gameId, row, column);
		 // ako je false imamo pobjednika ili je krivi potez 
		return gameService.findGameStatus(gameId);
	}

	@RequestMapping("/game/statistic")
	public GameStaistic gameStaistic(@RequestParam(value = "playerName", defaultValue = "all") String playerName) {

		return gameService.findGameStaistic(playerName);

	}

}
