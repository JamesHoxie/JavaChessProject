package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.synth.SynthLookAndFeel;

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
		TurnManager turnManager = new TurnManager(chessGame.gameBoard);
		
		chessGame.gameBoard.setExitScreen(titleFrame, chessGame);
		
		titleFrame.pack();
		titleFrame.setLocationRelativeTo(null);
		titleFrame.setVisible(true);		
		
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