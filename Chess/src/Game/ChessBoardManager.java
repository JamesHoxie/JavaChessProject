package Game;

import javax.swing.JFrame;

import Board.Board;
import Board.Tile;
import Pieces.Piece;

//this class manages events on the chess board and communicates to players when it is their turn
public class ChessBoardManager implements Runnable{
	Board board;
	
	public ChessBoardManager(Board board) {
		this.board = board;
	}

	public void setUpGameFrame(GameFrame chessGame) {
		chessGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessGame.setSize(700, 700);
		chessGame.setVisible(true);
	}
	
	public void switchPlayerTurn(ChessPlayer nextPlayer) {
		board.setCurrentPlayerColor(nextPlayer.getPlayerColor());
	}
	

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}