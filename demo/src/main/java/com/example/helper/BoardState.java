package com.example.helper;

import com.example.service.ComputerService;
import com.example.service.GameService;

public class BoardState {
	
	private String[][] board = {{ ComputerService.EMPTHY, ComputerService.EMPTHY, ComputerService.EMPTHY },
								{ ComputerService.EMPTHY, ComputerService.EMPTHY, ComputerService.EMPTHY }, 
								{ ComputerService.EMPTHY, ComputerService.EMPTHY, ComputerService.EMPTHY }};
	private int state;
	private int row;
	private int column;

	public BoardState() {
		state = 0;
	}

	public BoardState(int state) {
		this.state = state;
	}

	public void populateBoard(String[][] newBoard) {
		if(newBoard == null || newBoard.length == 0) {
			return;
		}
		
		for (int i = 0; i < ComputerService.BOARD_LENGTH; i++) {
			for (int j = 0; j < ComputerService.BOARD_LENGTH; j++) {
				board[i][j] = newBoard[i][j];
			}
		}
	}
	
	public void setRowAndColumn(int row,int column) {
		this.row = row;
		this.column = column; 
	}
	
	public void setBoardValue(int row, int column, String value) {
		board[row][column] = value;
	}
	
	public String getBoardValue(int row, int column) {
		return board[row][column];
	}
	// geter and seters
	public String[][] getBoard() {
		return board;
	}
	public void setBoard(String[][] board) {
		this.board = board;
	}

	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
}
