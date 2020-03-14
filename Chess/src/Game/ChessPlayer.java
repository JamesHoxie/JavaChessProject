package Game;

import Utils.ChessColor;

//class representing each player in chess
public class ChessPlayer{
	private int points = 0;
	private boolean inCheckMate = false;
	private boolean takingTurn = false;
	private ChessColor playerColor;
	
	//class constructor, sets player color
	public ChessPlayer(ChessColor playerColor) {
		this.playerColor = playerColor;
	}
	
	//sets this player to be taking their turn
	public void takeTurn() {
		this.takingTurn = true;
	}
	
	//sets this player to not be taking their turn
	public void finishTurn() {
		this.takingTurn = false;
	}
	
	//returns whether it is currently this player's turn
	public boolean isTakingTurn() {
		return this.takingTurn;
	}
	
	//returns whether this player is currently in checkmate
	public boolean isInCheckMate() {
		return this.inCheckMate;
	}
	
	//returns the color of this chess player (BLACK or WHITE)
	public ChessColor getPlayerColor() {
		return this.playerColor;
	}
}