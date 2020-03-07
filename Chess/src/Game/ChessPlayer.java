package Game;

import Pieces.Piece;
import Utils.ChessColor;

//class representing each player in chess
public class ChessPlayer{
	private int points = 0;
	private boolean inCheckMate = false;
	private boolean takingTurn = false;
	private ChessColor playerColor;
	private Piece playerSelectedPiece;
	
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
	
	//increases this player's points by the amount of a piece captured
	public void increasePoints(int points) {
		this.points += points;
	}
	
	//returns the chesscolor of this player (BLACK or WHITE)
	public ChessColor getPlayerColor() {
		return this.playerColor;
	}
	
	//returns the current piece being selected by this player
	public Piece getPlayerSelectedPiece() {
		return this.playerSelectedPiece;
	}
	
	public void setPlayerSelectedPiece(Piece piece) {
		this.playerSelectedPiece = piece;
	}
	
	//returns whether this player is currently in checkmate
	public boolean isInCheckMate() {
		return this.inCheckMate;
	}
	
	//class constructor, sets player color
	public ChessPlayer(ChessColor playerColor) {
		this.playerColor = playerColor;
	}
}