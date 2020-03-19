package Game;

import Board.Board;
import Utils.ChessColor;

/**
 * This class manages switching turns between black and white players in a chess game
 * @author James Hoxie
 *
 */

public class TurnManager{
	Board board;
	
	/**
	 * Class constructor
	 * @param board the chess board for this TurnManager to manage
	 */
	public TurnManager(Board board) {
		this.board = board;
	}
	
	/**
	 * Waits on black player to finish their turn before allowing white player to take a turn,
	 * TODO: Wait for white player to resolve check status before allowing turn to switch
	 * @throws InterruptedException
	 */
	public void setWhitePlayerTurn() throws InterruptedException{
		while(true) {
			synchronized(this) {
				while(board.getCurrentPlayerColor() == ChessColor.BLACK) {
					wait();
				}
				
				notify();
			}
		}
	}
	
	/**
	 * 
	 * Waits on white player to finish their turn before allowing black player to take a turn,
	 * TODO: Wait for black player to resolve check status before allowing turn to switch
	 * @throws InterruptedException
	 */
	public void setBlackPlayerTurn() throws InterruptedException{
		while(true) {
			synchronized(this) {
				while(board.getCurrentPlayerColor() == ChessColor.WHITE) {
					wait();
				}
				
				notify();
			}
		}
	}
	
}