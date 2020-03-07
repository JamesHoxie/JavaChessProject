package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Board.Board;
import Pieces.Piece;
import Utils.ChessColor;

public class GameRunner{
	private static GameFrame chessGameFrame = new GameFrame();
	
	//sets up GameFrame/Board and pieces
	private static void setUpGameFrame() {
		chessGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessGameFrame.setSize(700, 700);
		chessGameFrame.setVisible(true);
	}
	
	private static void selectPiece(ChessPlayer nextPlayer) {
		Board board = chessGameFrame.gameBoard;
		Piece selectedPiece = null;
		
		//until the nextPlayer properly selects a piece, keep looping, exit once a piece matching the nextplayer color has been selected
		while(nextPlayer.getPlayerSelectedPiece() == null) {
			selectedPiece = board.getCurrentSelectedPiece();
			System.out.println(selectedPiece);
			if(selectedPiece != null && selectedPiece.getPieceColor() == nextPlayer.getPlayerColor()) {
				nextPlayer.setPlayerSelectedPiece(selectedPiece);
			}
				
			board.setCurrentSelectedPiece(null);
		}
		
		System.out.println(chessGameFrame.gameBoard.getCurrentSelectedPiece());
		System.out.println(nextPlayer.getPlayerSelectedPiece());
		System.exit(0);
	}
	
	private static void selectMove(ChessPlayer nextPlayer) {
		
	}
	
	private static void movePiece(ChessPlayer nextPlayer) {
		
	}
	
	public static void main(String[] args) {
		ChessPlayer whitePlayer = new ChessPlayer(ChessColor.WHITE);
		ChessPlayer blackPlayer = new ChessPlayer(ChessColor.BLACK);
		//white player always goes first
		ChessPlayer nextPlayer = whitePlayer;
		
		setUpGameFrame();
		
		//basic game loop, until one player is in checkmate keep taking turns
		while(!(whitePlayer.isInCheckMate()) && !(blackPlayer.isInCheckMate())) {
			nextPlayer.takeTurn();
			selectPiece(nextPlayer);
			selectMove(nextPlayer);
			movePiece(nextPlayer);
			nextPlayer.finishTurn();
			//set nextPlayer to whoever isn't taking their turn currently
			if(nextPlayer == whitePlayer) {
				nextPlayer = blackPlayer;
			}
			
			else {
				nextPlayer = whitePlayer;
			}
		}
		
	}
}