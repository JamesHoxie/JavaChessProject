package Board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
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
	
	public PieceBox() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		capturedWhitePieces = new JPanel();
		capturedBlackPieces = new JPanel();
		capturedWhitePieces.setBackground(Color.RED);
		capturedBlackPieces.setBackground(Color.BLUE);
		capturedWhitePieces.setOpaque(true);
		capturedBlackPieces.setOpaque(true);
		capturedWhitePieces.setPreferredSize(new Dimension(400, 400));
		capturedBlackPieces.setPreferredSize(new Dimension(400, 400));
		
//		JButton cap = new JButton();
//		cap.setPreferredSize(new Dimension(200, 200));
//		
//		cap.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				System.out.println("got here");
//				capturePiece(new King(null, ChessColor.BLACK));
//			}
//			
//		});
//		
//		add(cap);
		add(capturedWhitePieces);
		add(capturedBlackPieces);
		
	}
	
	public void capturePiece(Piece piece) {
		if(piece.getPieceColor() == ChessColor.BLACK) {
			capturedBlackPieces.add(new JLabel(piece.getPieceIcon()));
		}
		
		else {
			capturedWhitePieces.add(new JLabel(piece.getPieceIcon()));
		}
		
		revalidate();  
		validate(); 
	}
}