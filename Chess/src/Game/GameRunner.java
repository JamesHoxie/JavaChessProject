package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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
	public static void main(String[] args) {
		String[] options = {"Start New Game", "Exit"};
		GameFrame chessGame = new GameFrame();
		TitleFrame titleFrame = new TitleFrame(options, chessGame);
		chessGame.gameBoard.setExitScreen(titleFrame, chessGame);
		
		titleFrame.setVisible(true);	
		
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