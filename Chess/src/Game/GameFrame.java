package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Board.Board;
import Board.PieceBox;
import Pieces.Pawn;

/**
 * 
 * Main GUI for the chess game.
 * @author James Hoxie
 *
 */
public class GameFrame extends JFrame {
	JLabel titleBox = new JLabel("This textbox could be used for a title", JLabel.CENTER);
	//TODO: make menuBox a new class with a menu and buttons (extend jpanel)
	JPanel menuBox = new JPanel();
	PieceBox pieceBox = new PieceBox();
	TextArea actionText = new TextArea("", 5, 100);
	Board gameBoard = new Board(8, 8, actionText, pieceBox);
	JButton saveGameButton = new JButton("Save");
	/**
	 * Class Constructor
	 */
	public GameFrame() {
		super("Chess");
		Color backgroundColor = new Color(90, 60, 60);
		
		//setters for GameFrame
		setBackground(backgroundColor);
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(1300, 1000));
		setMaximumSize(new Dimension(1300, 1000));
		this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//setters for gameBoard
		gameBoard.setOpaque(true);
		gameBoard.setBackground(backgroundColor);
		
		//setters for titleBox
		titleBox.setOpaque(true);
		titleBox.setBackground(backgroundColor);
		titleBox.setHorizontalTextPosition(JLabel.CENTER);
		
		//setters for actionText
		actionText.setEditable(false);
		
		//setters for pieceBox
		pieceBox.setPreferredSize(new Dimension(400, 400));
		pieceBox.setOpaque(true);
		pieceBox.setBackground(backgroundColor);
		
		
		//setters for menuBox
		menuBox.setOpaque(true);
		menuBox.setBackground(backgroundColor);
		menuBox.add(saveGameButton);
		menuBox.add(new JButton("blahblahblah"));
		
		
		//add components
		add(gameBoard, BorderLayout.CENTER);
		add(titleBox, BorderLayout.NORTH);
		add(pieceBox, BorderLayout.EAST);
		add(menuBox, BorderLayout.WEST);
		add(actionText, BorderLayout.SOUTH);
		

		
	}
}