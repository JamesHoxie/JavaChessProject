package Pieces;

import javax.swing.ImageIcon;

import Utils.ChessColor;
import Utils.Coordinate;

public class Bishop extends Piece {
	public Bishop(Coordinate coordinate, ChessColor color) {
		this.pieceCoordinate = coordinate;
		this.pieceColor = color;
		
		if(color == ChessColor.BLACK) {
			this.pieceIcon = new ImageIcon(getClass().getResource("/resources/BlackBishop.png"));
		}
		
		else {
			this.pieceIcon = new ImageIcon(getClass().getResource("/resources/WhiteBishop.png"));
		}
		
	}

	//moves diagonally on tiles that match the color of the tile the piece is on, never straight
	@Override
	public void updateMoves() {
		// TODO Auto-generated method stub
		
	}
}