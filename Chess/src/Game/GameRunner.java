package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Board.Tile;
import Utils.ChessColor;


public class GameRunner{
	
	public static void main(String[] args) {
		GameFrame chessGame = new GameFrame();
		
		ChessPlayer whitePlayer = new ChessPlayer(ChessColor.WHITE);
		ChessPlayer blackPlayer = new ChessPlayer(ChessColor.BLACK);
		//white player always goes first
		ChessPlayer nextPlayer = whitePlayer;
		
		ChessBoardManager boardManager = new ChessBoardManager(chessGame.gameBoard);
		boardManager.setUpGameFrame(chessGame);
		
		//Thread controls execution of chess game
		GameFlowThread game = new GameFlowThread();
		game.addParticipants(whitePlayer, blackPlayer, boardManager);
		game.launch();
		
		//basic game loop, until one player is in checkmate keep taking turns
		while(!(game.isOver())) {
//			nextPlayer.takeTurn();
//			
//			//TODO: game logic
//			
//			nextPlayer.finishTurn();
//			//set nextPlayer to whoever isn't taking their turn currently
//			if(nextPlayer == whitePlayer) {
//				nextPlayer = blackPlayer;
//			}
//			
//			else {
//				nextPlayer = whitePlayer;
//			}
//			
//			//switch player taking turn
//			boardManager.switchPlayerTurn(nextPlayer);
		}
		
	}
}