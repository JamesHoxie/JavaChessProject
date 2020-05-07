package Utils;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class Styler {
	final Font BUTTON_FONT = new Font("Helvetica", Font.PLAIN, 18);
	final Color BUTTON_BACKGROUND = new Color(153, 76, 0);
	final Color BUTTON_FOREGROUND = new Color(225, 225, 225);
	final Font LABEL_FONT = new Font("Helvetica", Font.PLAIN, 18);
	
	public void styleButton(JButton button) {
		button.setFont(BUTTON_FONT);
		button.setBackground(BUTTON_BACKGROUND);
		button.setForeground(BUTTON_FOREGROUND);
	}
	
	public void styleFrame() {
		
	}
	
	public void styleWindow() {
		
	}
	
	public void changeJOP(){
		UIManager.put("Label.font", new FontUIResource
				(new Font("Helvetica", Font.PLAIN, 28)));
		UIManager.put("OptionPane.messageForeground",
				BUTTON_FOREGROUND);
		UIManager.put("Panel.background", new Color(90, 60, 60));
		UIManager.put("OptionPane.background", new Color(90, 60, 60));
		UIManager.put("Button.background", BUTTON_BACKGROUND);
		UIManager.put("Button.foreground", BUTTON_FOREGROUND);
		UIManager.put("Button.font", new FontUIResource
				(new Font("Helvetica", Font.PLAIN, 18)));
	}

}

