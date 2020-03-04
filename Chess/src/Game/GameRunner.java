package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Utils.ChessColor;

//TODO: Add action listener/class to game frame to handle game logic

public class GameRunner{
	//sets up GameFrame/Board and pieces
	public static void setUpGameFrame(GameFrame chessGame) {
		chessGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessGame.setSize(700, 700);
		chessGame.setVisible(true);
	}
	
	public static void main(String[] args) {
		GameFrame chessGame = new GameFrame();
		ChessPlayer whitePlayer = new ChessPlayer(ChessColor.WHITE);
		ChessPlayer blackPlayer = new ChessPlayer(ChessColor.BLACK);
		//white player always goes first
		ChessPlayer nextPlayer = whitePlayer;
		
		setUpGameFrame(chessGame);
		
		//basic game loop, until one player is in checkmate keep taking turns
		while(!(whitePlayer.isInCheckMate()) && !(blackPlayer.isInCheckMate())) {
			nextPlayer.takeTurn();
			
			//TODO: game logic
			
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