package com.example.service;

import com.example.responseModel.GameId;
import com.example.responseModel.GameInfo;
import com.example.responseModel.GameStaistic;

public interface GameService {

	GameId inicializeGame(String player1, String player2, String gameLevel);

	GameInfo findGameStatus(int id);

	void playGame(int gameId, int row, int column);

	GameStaistic findGameStaistic(String playerName);
}
