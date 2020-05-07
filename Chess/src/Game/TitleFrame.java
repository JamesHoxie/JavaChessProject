package Game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Utils.Styler;

public class TitleFrame extends JFrame{
	GameFrame chessGame;
	JLabel titleLabel, titleImage;
	
	public TitleFrame(String[] options, GameFrame chessGame){
		super("Java Chess");
		this.chessGame = chessGame;
		final Color BACKGROUND_COLOR = new Color(40, 175, 40);
		final int FRAME_WIDTH = 900;
		final int FRAME_HEIGHT = 600;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);
		getContentPane().setBackground(BACKGROUND_COLOR);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		titleLabel = new JLabel("WELCOME TO JAVA CHESS!");
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleImage = new JLabel(new ImageIcon(getClass().getResource("/resources/chess_pieces_set.png")));
		titleImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(titleLabel);
		add(titleImage);
		addButtons(options);
	}

	private void addButtons(String[] options) {
		Styler styler = new Styler();
		JButton nextButton;
		
		for(String option : options) {
			nextButton = new JButton(option);
			
			switch(option) {
				case "Start New Game":
					nextButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							chessGame.pack();
							chessGame.setLocationRelativeTo(null);
							chessGame.setVisible(true);
							setVisible(false);
						}			
					});
					break;
				case "Exit":
					nextButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}			
					});
					break;
					
			}
			
			nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			styler.styleButton(nextButton);
			add(nextButton);
		}	
	}
}