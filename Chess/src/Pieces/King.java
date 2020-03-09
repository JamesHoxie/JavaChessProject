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

	//moves one space in any direction as long as that space would not put him in check
	@Override
	public Coordinate[] generateMoves() {
		return null;
		// TODO Auto-generated method stub
		
	}

}