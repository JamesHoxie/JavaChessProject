package Pieces;

import javax.swing.ImageIcon;

import Utils.ChessColor;
import Utils.Coordinate;

public class Queen extends Piece {
	public Queen(Coordinate coordinate, ChessColor color) {
		this.pieceCoordinate = coordinate;
		this.pieceColor = color;
		
		if(color == ChessColor.BLACK) {
			this.pieceIcon = new ImageIcon(getClass().getResource("/resources/BlackQueen.png"));
		}
		
		else {
			this.pieceIcon = new ImageIcon(getClass().getResource("/resources/WhiteQueen.png"));
		}
		
	}

	//moves straight or diagonal
	@Override
	public void updateMoves() {
		// TODO Auto-generated method stub
		
	}
}