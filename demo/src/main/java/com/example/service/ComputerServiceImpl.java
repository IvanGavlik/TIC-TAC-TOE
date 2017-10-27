package com.example.service;

import org.springframework.stereotype.Service;

import com.example.helper.BoardState;
import com.example.model.Game;

@Service
public class ComputerServiceImpl implements ComputerService {
	
	// TODO SMISLIT NACIN KAKO PROVJERITI ZA POBJEDU, NEKA SE RADI JEDNOM
	@Override
	public void playMove(Game game,int row, int column) {
		
		BoardState boardState = new BoardState();
		boardState.populateBoard(game.getBoard());
		
		BoardState board = max(boardState);
		
		// treba gledati i trenutnog igraca
		// iz gamea kreirati stanje 
		// indeki is gamea da updejtas game
		// u stanje staviti dali imamo pobjdnika i s time isto updejatatai game
		
		// BoardState s = max(new BoardState());
		//System.out.println("MAX r,c: " + s.r + " " + s.c);
	}

	private static BoardState max(BoardState state) {
		if (chechVictory(state)) {
			return state;
		}

		BoardState maxState = new BoardState(-1000);
		for (int x = 0; x < BOARD_LENGTH; x++) {
			for (int y = 0; y < BOARD_LENGTH; y++) {

				if (state.getBoard()[x][y] == EMPTHY) {

					BoardState newState = new BoardState();
					newState.populateBoard(state.getBoard());		
					newState.setBoardValue(x, y, X);
					newState.setRowAndColumn(x, y);

					BoardState tempStanje = min(newState);
					if (tempStanje.getState() > maxState.getState()) {
						maxState = tempStanje;
					}

				}
			}
		}
		// System.out.println("\n" + Arrays.deepToString(maxStanje.getBoard()) + "	Max rez: " + maxStanje.s);
		return maxState;
	}

	private static BoardState min(BoardState state) {
		if (chechVictory(state)) {
			return state;
		}

		BoardState minState = new BoardState(-10000);
		for (int x = 0; x < BOARD_LENGTH; x++) {
			for (int y = 0; y < BOARD_LENGTH; y++) {

				if (state.getBoardValue(x, y) == EMPTHY) {

					BoardState newState = new BoardState();
					newState.populateBoard(state.getBoard());
					newState.setBoardValue(x, y, O);
					newState.setRowAndColumn(x, y);
					
					BoardState tempState = max(newState);
					if (minState.getState() < tempState.getState()) {
						minState = tempState;
					}

				}

			}

		}
		// System.out.println("\n" + Arrays.deepToString(minStanje.getBoard()) + "	Min rez: " + minStanje.s);
		return minState;
	}

	// TODO POBOLJSATI 
	private static boolean chechVictory(BoardState state) {

		int notEmpty = 0;
		for (int i = 0; i < BOARD_LENGTH; i++) {
			
			
			if (state.getBoardValue(i, 0) == X && state.getBoardValue(i, 1) == X && state.getBoardValue(i, 2) == X) {
				state.setState(10);
				return true;
			}

			if (state.getBoardValue(i, 0) == O && state.getBoardValue(i, 1) == O && state.getBoardValue(i, 2) == O) {
				state.setState(10);
				return true;
			}

			if(state.getBoardValue(0, i) == X && state.getBoardValue(1, i) == X && state.getBoardValue(2, i) == X) {
				state.setState(10);
				return true;
			}

			if (state.getBoardValue(0, i) == O && state.getBoardValue(1, i) == O && state.getBoardValue(2, i) == O) {
				state.setState(10);
				return true;
			}

			for (int j = 0; j < BOARD_LENGTH; j++) {
				if (state.getBoard()[i][j] == EMPTHY) {
					notEmpty += 1;
				}

			}
		}

		if (notEmpty == 9) {
			state.setState(0);
			return true;
		}

		
		if (state.getBoardValue(0, 0) == X && state.getBoardValue(1, 1) == X && state.getBoardValue(2, 2)  == X) {
			state.setState(10);
			return true;
		}

		if (state.getBoardValue(0, 0) == O && state.getBoardValue(1, 1) == O && state.getBoardValue(2, 2)  == O) {
			state.setState(10);
			return true;
		}

		if (state.getBoardValue(0, 2) == X && state.getBoardValue(1, 1) == X && state.getBoardValue(2, 0)  == X) {
			state.setState(10);
			return true;
		}

		if (state.getBoardValue(0, 2) == O && state.getBoardValue(1, 1) == O && state.getBoardValue(2, 0)  == O) {
			state.setState(10);
			return true;
		}

		return false;
	}

	
	
	
	/*
	
	
	private static class Stanje {
		int s;
		String[][] board = {{ EMPTHY, EMPTHY, EMPTHY },
							{ EMPTHY, EMPTHY, EMPTHY }, 
							{ EMPTHY, EMPTHY, EMPTHY }};
		int r;
		int c;

		Stanje() {
			this.s = 0;
		}

		Stanje(int v) {
			this.s = v;
		}

		void populateBoard(String[][] newBoard) {
			board[0][0] = newBoard[0][0];
			board[0][1] = newBoard[0][1];
			board[0][2] = newBoard[0][2];

			board[1][0] = newBoard[1][0];
			board[1][1] = newBoard[1][1];
			board[1][2] = newBoard[1][2];

			board[2][0] = newBoard[2][0];
			board[2][1] = newBoard[2][1];
			board[2][2] = newBoard[2][2];
		}
	}


	private static Stanje max(Stanje stanje) {
		if (chechVictory(stanje)) {
			return stanje;
		}

		Stanje maxStanje = new Stanje(-1000);
		for (int x = 0; x < BOARD_LENGTH; x++) {
			for (int y = 0; y < BOARD_LENGTH; y++) {

				if (stanje.board[x][y] == EMPTHY) {

					Stanje novoStanje = new Stanje();
					novoStanje.populateBoard(stanje.board);

					novoStanje.board[x][y] = X;
					novoStanje.r = x;
					novoStanje.c = y;

					Stanje tempStanje = min(novoStanje);
					if (tempStanje.s > maxStanje.s) {
						maxStanje = tempStanje;
					}

				}
			}
		}
		System.out.println("\n" + Arrays.deepToString(maxStanje.board) + "	Max rez: " + maxStanje.s);
		return maxStanje;
	}

	private static Stanje min(Stanje stanje) {
		if (chechVictory(stanje)) {
			return stanje;
		}

		Stanje minStanje = new Stanje(-10000);
		for (int x = 0; x < BOARD_LENGTH; x++) {
			for (int y = 0; y < BOARD_LENGTH; y++) {

				if (stanje.board[x][y] == EMPTHY) {

					Stanje novoStanje = new Stanje();
					novoStanje.populateBoard(stanje.board);

					novoStanje.board[x][y] = O;
					novoStanje.r = x;
					novoStanje.c = y;

					Stanje tempStanje = max(novoStanje);
					if (minStanje.s < tempStanje.s) {
						minStanje = tempStanje;
					}

				}

			}

		}
		System.out.println("\n" + Arrays.deepToString(minStanje.board) + "	Min rez: " + minStanje.s);
		return minStanje;
	}

	// TODO POBOLJSATI 
	private static boolean chechVictory(Stanje stanje) {

		int notEmpty = 0;
		for (int i = 0; i < BOARD_LENGTH; i++) {

			if (stanje.board[i][0] == X && stanje.board[i][1] == X && stanje.board[i][2] == X) {
				stanje.s = 10;
				return true;
			}

			if (stanje.board[i][0] == O && stanje.board[i][1] == O && stanje.board[i][2] == O) {
				stanje.s = 10;
				return true;
			}

			if (stanje.board[0][i] == X && stanje.board[1][i] == X && stanje.board[2][i] == X) {
				stanje.s = 10;
				return true;
			}

			if (stanje.board[0][i] == O && stanje.board[1][i] == O && stanje.board[2][i] == O) {
				stanje.s = 10;
				return true;
			}

			for (int j = 0; j < BOARD_LENGTH; j++) {
				if (stanje.board[i][j] == EMPTHY) {
					notEmpty += 1;
				}

			}
		}

		if (notEmpty == 9) {
			stanje.s = 0;
			return true;
		}

		if (stanje.board[0][0] == X && stanje.board[1][1] == X && stanje.board[2][2] == X) {
			stanje.s = 10;
			return true;
		}

		if (stanje.board[0][2] == X && stanje.board[1][1] == X && stanje.board[2][0] == X) {
			stanje.s = 10;
			return true;
		}

		if (stanje.board[0][0] == O && stanje.board[1][1] == O && stanje.board[2][2] == O) {
			stanje.s = 10;
			return true;
		}

		if (stanje.board[0][2] == O && stanje.board[1][1] == O && stanje.board[2][0] == O) {
			stanje.s = 10;
			return true;
		}

		return false;
	}

	
*/
}
