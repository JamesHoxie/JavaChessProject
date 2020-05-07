package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import Board.Board;
import Board.MenuBox;
import Board.PieceBox;
import Pieces.Pawn;
import Utils.Styler;

/**
 * 
 * Main GUI for the chess game.
 * @author James Hoxie
 *
 */
public class GameFrame extends JFrame {
	JLabel titleBox = new JLabel("This textbox could be used for a title", JLabel.CENTER);
	MenuBox menuBox = new MenuBox();
	PieceBox pieceBox = new PieceBox();
	TextArea actionText = new TextArea("", 5, 100);
	Board gameBoard = new Board(8, 8, actionText, pieceBox, menuBox);
	Styler styler = new Styler();

	/**
	 * Class Constructor
	 */
	public GameFrame() {
		super("Chess");
		final Color BACKGROUND_COLOR = new Color(90, 60, 60);
		final int FRAME_WIDTH = 1450;
		final int FRAME_HEIGHT = 950;
		
		//setters for GameFrame
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setMaximumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//setters for gameBoard
		gameBoard.setOpaque(true);
		gameBoard.setBackground(BACKGROUND_COLOR);
		
		//setters for titleBox
		titleBox.setOpaque(true);
		titleBox.setBackground(BACKGROUND_COLOR);
		titleBox.setHorizontalTextPosition(JLabel.CENTER);
		
		//setters for actionText
		actionText.setEditable(false);
		
		//setters for pieceBox
		pieceBox.setOpaque(true);
		pieceBox.setBackground(BACKGROUND_COLOR);
			
		//setters for menuBox
		menuBox.setOpaque(true);
		menuBox.setBackground(BACKGROUND_COLOR);		
		
		styler.changeJOP();
		
		//add components
		add(gameBoard, BorderLayout.CENTER);
		add(titleBox, BorderLayout.NORTH);
		add(pieceBox, BorderLayout.EAST);
		add(menuBox, BorderLayout.WEST);
		add(actionText, BorderLayout.SOUTH);		
	}
}