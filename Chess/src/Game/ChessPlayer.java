package Game;

import Utils.ChessColor;

/**
 * This class represents the black and white players in a chess game
 * @author James Hoxie
 *
 */
public class ChessPlayer{
	private int points = 0;
	private boolean inCheckMate = false;
	private boolean takingTurn = false;
	private ChessColor playerColor;
	
	/**
	 * Class constructor, sets the player color
	 * @param playerColor will be set to ChessColor.BLACK or ChessColor.WHITE
	 */
	public ChessPlayer(ChessColor playerColor) {
		this.playerColor = playerColor;
	}
	
	/**
	 * Sets this player to be taking their turn
	 */
	public void takeTurn() {
		this.takingTurn = true;
	}
	
	/**
	 * Sets this player to not be taking their turn
	 */
	public void finishTurn() {
		this.takingTurn = false;
	}
	
	/**
	 * 
	 * @return true if this player is currently taking their turn
	 */
	public boolean isTakingTurn() {
		return this.takingTurn;
	}
	
	/**
	 * 
	 * @return true if this player is currently in check mate
	 */
	public boolean isInCheckMate() {
		return this.inCheckMate;
	}
	
	/**
	 * 
	 * @return The ChessColor of this player; either ChessColor.BLACK or ChessColor.WHITE
	 */
	public ChessColor getPlayerColor() {
		return this.playerColor;
	}
}