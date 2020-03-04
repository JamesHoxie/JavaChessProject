package Pieces;

import javax.swing.ImageIcon;

import Utils.ChessColor;
import Utils.Coordinate;

public class King extends Piece {
	public King(Coordinate coordinate, ChessColor color) {
		this.pieceCoordinate = coordinate;
		this.pieceColor = color;
		
		if(color == ChessColor.BLACK) {
			this.pieceIcon = new ImageIcon(getClass().getResource("/resources/BlackKing.png"));
		}
		
		else {
			this.pieceIcon = new ImageIcon(getClass().getResource("/resources/WhiteKing.png"));
		}
		
	}

	//moves one space in any direction from current space, cannot ever move into a position that places this piece in check
	@Override
	public void updateMoves() {
		// TODO Auto-generated method stub
		
	}
}