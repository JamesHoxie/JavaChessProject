package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Board.Tile;
import Utils.ChessColor;


public class GameRunner{
	
	//return true when whitePlayer or blackPlayer is in checkmate
	public static boolean gameIsNotOver(ChessPlayer whitePlayer, ChessPlayer blackPlayer) {
		return !(whitePlayer.isInCheckMate() || blackPlayer.isInCheckMate());
	}
	
	
	public static void setUpGameFrame(GameFrame chessGame) {
		chessGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessGame.setSize(700, 700);
		chessGame.setVisible(true);
	}
	
	public static void main(String[] args) {
		GameFrame chessGame = new GameFrame();
		setUpGameFrame(chessGame);
		
		ChessPlayer whitePlayer = new ChessPlayer(ChessColor.WHITE);
		ChessPlayer blackPlayer = new ChessPlayer(ChessColor.BLACK);
		//white player always goes first
		ChessPlayer nextPlayer = whitePlayer;
		
		TurnManager turnManager = new TurnManager(chessGame.gameBoard);
		
		Thread t1 = new Thread(new Runnable() {
			@Override
            public void run() 
            { 
                try { 
                    turnManager.setWhitePlayerTurn(); 
                } 
                catch (InterruptedException e) { 
                    e.printStackTrace(); 
                } 
            } 
        }); 

		Thread t2 = new Thread(new Runnable() {
			@Override
            public void run() 
            { 
                try { 
                    turnManager.setBlackPlayerTurn(); 
                } 
                catch (InterruptedException e) { 
                    e.printStackTrace(); 
                } 
            } 
        }); 
		
		t1.start();
		t2.start();
		
//		//basic game loop, until one player is in checkmate keep taking turns
//		while(gameIsNotOver(whitePlayer, blackPlayer)) {
//			nextPlayer.takeTurn();
//			
//			//TODO: turn switch logic
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
//
//		}	
	}
}