package Board;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuBox extends JPanel{
	private JButton saveGameButton = new JButton("Save");
	private JButton loadGameButton = new JButton("Load");
	private JButton resetGameButton = new JButton("Reset");
	private JButton exitGameButton = new JButton("Exit");
	
	public MenuBox() {
		setLayout(null);	
		setPreferredSize(new Dimension(100, 400));
		
		saveGameButton.setBounds(2, 80, 100, 50);
		loadGameButton.setBounds(2, 180, 100, 50);
		resetGameButton.setBounds(2, 280, 100, 50);
		exitGameButton.setBounds(2, 380, 100, 50);
		
//		addMouseListener(new MouseListener() {
//
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent arg0) {
//				showButtons();
//			}
//
//			@Override
//			public void mouseExited(MouseEvent arg0) {
//				hideButtons();
//			}
//
//			@Override
//			public void mousePressed(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		});
		
		//hideButtons();
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
	
//	private void showButtons() {
//		saveGameButton.setVisible(true);
//		loadGameButton.setVisible(true);
//		resetGameButton.setVisible(true);
//		exitGameButton.setVisible(true);
//	}
//	
//	private void hideButtons() {
//		saveGameButton.setVisible(false);
//		loadGameButton.setVisible(false);
//		resetGameButton.setVisible(false);
//		exitGameButton.setVisible(false);
//	}
}