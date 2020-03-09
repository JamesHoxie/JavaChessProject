package Pieces;

import javax.swing.ImageIcon;

import Utils.ChessColor;
import Utils.Coordinate;

public class Knight extends Piece {
	public Knight(Coordinate coordinate, ChessColor color) {
		this.pieceCoordinate = coordinate;
		this.pieceColor = color;
		
		if(color == ChessColor.BLACK) {
			this.pieceIcon = new ImageIcon(getClass().getResource("/resources/BlackKnight.png"));
		}
		
		else {
			this.pieceIcon = new ImageIcon(getClass().getResource("/resources/WhiteKnight.png"));
		}
		
	}

	//moves in L shape
	@Override
	public Coordinate[] generateMoves() {
		return null;
		// TODO Auto-generated method stub
		
	}
}