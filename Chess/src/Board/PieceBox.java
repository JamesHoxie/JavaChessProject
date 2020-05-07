package Board;

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
import javax.swing.JLabel;
import javax.swing.JPanel;

import Pieces.King;
import Pieces.Pawn;
import Pieces.Piece;
import Utils.ChessColor;

public class PieceBox extends JPanel {
	private JPanel capturedWhitePieces, capturedBlackPieces;
	private ArrayList<String> capturedWhitePiecesTypes = new ArrayList<String>();
	private ArrayList<String> capturedBlackPiecesTypes = new ArrayList<String>();
	
	public PieceBox() {
		final int PANEL_WIDTH = 300;
		final int PANEL_HEIGHT = 400;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		capturedWhitePieces = new JPanel();
		capturedBlackPieces = new JPanel();
		capturedWhitePieces.setBackground(Color.DARK_GRAY);
		capturedBlackPieces.setBackground(Color.lightGray);
		capturedWhitePieces.setOpaque(true);
		capturedBlackPieces.setOpaque(true);
		
		add(capturedWhitePieces);
		add(capturedBlackPieces);
	}
	
	public void clear() {
		capturedWhitePieces.removeAll();
		capturedBlackPieces.removeAll();
		capturedWhitePiecesTypes.clear();
		capturedBlackPiecesTypes.clear();
		capturedWhitePieces.repaint();
		capturedBlackPieces.repaint();
	}
	
	public void capturePiece(Piece piece) {		
		if(piece.getPieceColor() == ChessColor.BLACK) {
			capturedBlackPieces.add(new JLabel(piece.getPieceIcon()));
			capturedBlackPiecesTypes.add(piece.getPieceType());
		}
		
		else {
			capturedWhitePieces.add(new JLabel(piece.getPieceIcon()));
			capturedWhitePiecesTypes.add(piece.getPieceType());
		}
		
		revalidate();  
		validate(); 
	}
	
	public ArrayList<String> getCapturedBlackPiecesTypes() {
		return capturedBlackPiecesTypes;
	}
	
	public ArrayList<String> getCapturedWhitePiecesTypes() {
		return capturedWhitePiecesTypes;
	}
}