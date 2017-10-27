package com.example.service;

import com.example.model.Game;


public interface ComputerService {
	
	static final String X = "X";
	static final String O = "O";
	static final String EMPTHY = "";
	static final int BOARD_LENGTH = 3;
	
	void playMove(Game game,int row,int column);
}
