package com.example.service;

import com.example.model.Game;


public interface ComputerService {
	
	public final static String X = "X";
	public final static String O = "O";
	public final static String EMPTHY = "";
	public final static String COMP = "AI";
	
	void playMove(Game game);
}
