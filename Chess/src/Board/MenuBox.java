package Board;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Utils.Styler;

public class MenuBox extends JPanel{
	private JButton saveGameButton = new JButton("Save");
	private JButton loadGameButton = new JButton("Load");
	private JButton resetGameButton = new JButton("Reset");
	private JButton exitGameButton = new JButton("Exit");
	
	public MenuBox() {
		final int PANEL_WIDTH = 100;
		final int PANEL_HEIGHT = 400;
		Styler styler = new Styler();
		
		setLayout(null);	
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));		
		saveGameButton.setBounds(2, 80, 100, 50);
		loadGameButton.setBounds(2, 180, 100, 50);
		resetGameButton.setBounds(2, 280, 100, 50);
		exitGameButton.setBounds(2, 380, 100, 50);
		styler.styleButton(saveGameButton);
		styler.styleButton(loadGameButton);
		styler.styleButton(resetGameButton);
		styler.styleButton(exitGameButton);
		
		add(saveGameButton);
		add(loadGameButton);
		add(resetGameButton);
		add(exitGameButton);
	}
	
	public void addButtonFunctions(ActionListener saveHandler, ActionListener loadHandler, ActionListener resetHandler, ActionListener exitHandler) {
		saveGameButton.addActionListener(saveHandler);
		loadGameButton.addActionListener(loadHandler);
		resetGameButton.addActionListener(resetHandler);
		exitGameButton.addActionListener(exitHandler);	
	}
}