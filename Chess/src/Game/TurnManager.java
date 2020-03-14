package Game;

import Board.Board;
import Utils.ChessColor;

public class TurnManager{
	Board board;
	
	public TurnManager(Board board) {
		this.board = board;
	}
	
	public void setWhitePlayerTurn() throws InterruptedException{
		while(true) {
			synchronized(this) {
				
				while(board.inCheck(ChessColor.WHITE)) {
					wait();
				}
				
				while(board.getCurrentPlayerColor() == ChessColor.BLACK) {
					wait();
				}
				
				notify();
			}
		}
	}
	
	public void setBlackPlayerTurn() throws InterruptedException{
		while(true) {
			synchronized(this) {
				while(board.inCheck(ChessColor.BLACK)) {
					wait();
				}
				
				while(board.getCurrentPlayerColor() == ChessColor.WHITE) {
					wait();
				}
				
				notify();
			}
		}
	}
	
}