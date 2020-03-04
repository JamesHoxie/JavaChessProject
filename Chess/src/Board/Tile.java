package Board;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Pieces.Piece;
import Utils.ChessColor;
import Utils.Coordinate;

//tile class, represents each square on the chess board
public class Tile extends JButton{
	private Coordinate tileCoordinate;
	private Piece piece = null;
	private ChessColor color = null;
	
	//class constructor, assigns coordinates for tile and color of tile
	public Tile(Coordinate coordinate, ChessColor color) {
		this.tileCoordinate = new Coordinate(coordinate.getRow(), coordinate.getCol());
		this.color = color;
		
		if(this.color == ChessColor.BLACK) {
			this.setBackground(new Color(40, 40, 40));
		}
		
		else {
			this.setBackground(new Color(245, 245, 245));
		}
	}
	
	//returns chess piece occupying this tile or null if empty
	public Piece getPiece() {
		return this.piece;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	//displays current piece on this tile or nothing if empty
	public void displayPiece() {
		if(this.piece != null) {
			this.setIcon(this.piece.getPieceIcon());
		}
		
		else {
			this.setIcon(null);
		}
	}
	
	private class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}