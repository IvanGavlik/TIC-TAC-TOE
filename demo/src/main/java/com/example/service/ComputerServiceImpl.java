package com.example.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.example.model.Game;
import com.example.model.Player;

@Service
public class ComputerServiceImpl implements ComputerService {

	private class Move {

		public Move() {

		}

		public Move(int moveQuality, String value) {
			this.moveQuality = moveQuality;
			this.value = value;
		}

		int row;
		int column;
		int moveQuality;
		String value;
	}

	Player playerX;
	Player playerO;

	
	
	@Override
	public void playMove(Game game) {

		playerX = game.getPlayerX();
		playerO = game.getPlayerO();

		int bestMove =	minMax(game.board2, game.getCurrentPlayer());
		System.out.println("best move: " + bestMove);
		System.out.println("best move index: " + bestMoveGAME.row + " VAL=  " + bestMoveGAME.value);	
	
	}

	Move bestMoveGAME;
	
	private int minMax(String[] borard, Player currentPlayer) {
		System.out.println("	calculate: " + Arrays.deepToString(borard));
		
		int gameStatus = chechVictory(borard);
		if(gameStatus != -1) {
			return gameStatus;
		}
		
		int bestMove = 0;
		for (int x = 0; x < borard.length; x++) {

			if (borard[x] == "") {
				
				Move newMove = new Move();
				newMove.row = x;
				newMove.value = (currentPlayer.equals(playerX)) ? "X" : "O";
				borard[x] = newMove.value;

				if (currentPlayer.equals(playerX)) {
					int bestScorre = -1000000;
					int score = minMax(borard, playerO);
					
					if (score > bestScorre) {
						bestMoveGAME = newMove;
						bestMove = x;
						score = bestScorre;
					}

				} else {

					int bestScorre = 1000000;
					int score = minMax(borard, playerX);

					if (score < bestScorre) {
						bestMoveGAME = newMove;
						bestMove = x;
						bestScorre = score;
					}
				}
				borard[x]= "";

			}
		}
		
		return bestMove;
	}


	private int chechVictory(String[] borard) {
		
		if (borard[0] == "X" && borard[1] == "X") {
			return 10;
		}

		if (borard[2] == "X" && borard[1] == "X") {
			return 10;
		}

		if (borard[0] == "O" && borard[1] == "O") {
			return 10;
		}

		if (borard[2] == "O" && borard[1] == "O") {
			return 10;
		}

		if (borard[2] != "" && borard[1] != "" && borard[0] != "") {
			return 0; // DRAW
		} else {
			return -1; // COUNTINE
		}
		
	}
	
	
	
	// old
	
	
	/*
	private Move minMax(String[][] borard, Player currentPlayer) {
		System.out.println("	calculate: " + Arrays.deepToString(borard));

		int winer = chechVictory(borard);
		switch (winer) {
		case 0:
			return new Move(0, "hm"); // draw
		case 10:
			new Move(10, "X"); // comp
		case -10:
			return new Move(-10, "O"); // user
		}

		List<Move> moves = new ArrayList<>();
		for (int x = 0; x < borard.length; x++) {
			for (int y = 0; y < borard.length; y++) {

				if (borard[x][y] == "") {

					Move newMove = new Move();
					newMove.row = x;
					newMove.column = y;
					newMove.value = (currentPlayer.equals(playerX)) ? "X" : "O";

					if (currentPlayer.equals(playerX)) {
						newMove.moveQuality = minMax(borard, playerO).moveQuality;
					} else {
						newMove.moveQuality = minMax(borard, playerX).moveQuality;
					}

					moves.add(newMove);
					// borard[x][y] = "";
				}

			}
		}

		Move bestMove = null;
		int bestScorre = -1000000;
		if (currentPlayer.getName().equals("computer")) { // get +10

			for (Move move : moves) {
				if (move.moveQuality > bestScorre) {
					bestMove = move;
					bestScorre = move.moveQuality;
				}
			}

		} else { // get -10
			bestScorre = 1000000;

			for (Move move : moves) {
				if (move.moveQuality < bestScorre) {
					bestMove = move;
					bestScorre = move.moveQuality;
				}
			}

		}

		return bestMove;
	}

	// 0, 1, 2
	private int chechVictory(String[][] borard) {
		int numX = 0;
		int numO = 0;
		int numberOfInuts = 0;

		for (int x = 0; x < borard.length; x++) {
			for (int y = 0; y < borard.length; y++) {

				if (borard[x][y].equals("X")) {
					numX += 1;
					numberOfInuts++;
				}

				if (borard[x][y].equals("O")) {
					numO += 1;
					numberOfInuts = 0;
				}

			}

			if (numX == 3) {
				return 10;
			}
			if (numO == 3) {
				return -10;
			}
		}

		if (numberOfInuts == 9 || numberOfInuts == 6) {
			return 0; // draw
		}

		for (int x = 0; x < borard.length; x++) {
			for (int y = 0; y < borard.length; y++) {

				if (borard[y][x].equals("X")) {
					numX += 1;
				}

				if (borard[y][x].equals("O")) {
					numO += 1;
				}

			}

			if (numX == 3) {
				return 10;
			}
			if (numO == 3) {
				return -10;
			}
		}

		if (borard[0][0] == "X" && borard[1][1] == "X" && borard[2][2] == "X") {
			return 10;
		}

		if (borard[0][0] == "O" && borard[1][1] == "O" && borard[2][2] == "O") {
			return -10;
		}

		if (borard[0][2] == "X" && borard[1][1] == "X" && borard[2][0] == "X") {
			return 10;
		}

		if (borard[0][2] == "O" && borard[1][1] == "O" && borard[2][0] == "O") {
			return -10;
		}

		return -1; // in progres

	}
	*/

}
