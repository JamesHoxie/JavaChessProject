package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Board.Tile;
import Utils.ChessColor;

/**
 * Runner file to launch chess game and exit when game ends
 * @author James Hoxie
 *
 */
public class GameRunner{
	
	/**
	 * Static helper function to check when the chess game has ended.
	 * @param whitePlayer white player in this chess game
	 * @param blackPlayer black player in this chess game
	 * @return true if either player is in checkmate, indicating the game is over
	 */
	public static boolean gameIsNotOver(ChessPlayer whitePlayer, ChessPlayer blackPlayer) {
		return !(whitePlayer.isInCheckMate() || blackPlayer.isInCheckMate());
	}
	
	/**
	 * Static helper function to make changes to the GameFrame before starting the game.
	 * @param chessGame GameFrame containing the chess game
	 */
	public static void setUpGameFrame(GameFrame chessGame) {
		chessGame.setVisible(true);
	}
	
	public static void main(String[] args) {
		GameFrame chessGame = new GameFrame();
		
		String[] options = {"Start New Game", "Load Game", "Exit"};
		int selection = JOptionPane.showOptionDialog(chessGame, "Welcome to chess!", "Java Chess", 0, 0, null, options, null);
		
		switch(selection) {
			case 0:
				setUpGameFrame(chessGame);
				break;
			case 1:
				JOptionPane.showMessageDialog(chessGame, "Not implemented!");
				break;
			case 2:
				System.exit(0);
		}
		
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
	
	}
}