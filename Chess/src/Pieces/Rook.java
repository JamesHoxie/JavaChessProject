package Pieces;

import javax.swing.ImageIcon;

import Utils.ChessColor;
import Utils.Coordinate;

public class Rook extends Piece {
	public Rook(Coordinate coordinate, ChessColor color) {
		this.pieceCoordinate = coordinate;
		this.pieceColor = color;
		
		if(color == ChessColor.BLACK) {
			this.pieceIcon = new ImageIcon(getClass().getResource("/resources/BlackRook.png"));
		}
		
		else {
			this.pieceIcon = new ImageIcon(getClass().getResource("/resources/WhiteRook.png"));
		}
		
	}

	//moves straight, never diagonal
	@Override
	public void updateMoves() {
		// TODO Auto-generated method stub
		
	}
}