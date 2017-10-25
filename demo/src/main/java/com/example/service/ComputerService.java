package com.example.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.example.model.Game;


public interface ComputerService {
	
	void playMove(Game game);
}
