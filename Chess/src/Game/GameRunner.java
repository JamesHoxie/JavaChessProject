package Game;

import javax.swing.JFrame;

public class GameRunner{
	public static void main(String[] args) {
		GameFrame chessGame = new GameFrame();
		
		chessGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessGame.setSize(700, 700);
		chessGame.setVisible(true);
	}
}