package Pieces;

import javax.swing.ImageIcon;

import Utils.ChessColor;
import Utils.Coordinate;

public class Pawn extends Piece {
	private boolean isFirstMove = true;
	
	public Pawn(Coordinate coordinate, ChessColor color) {
		this.pieceCoordinate = coordinate;
		this.pieceColor = color;
		
		if(color == ChessColor.BLACK) {
			this.pieceIcon = new ImageIcon(getClass().getResource("/resources/BlackPawn.png"));
		}
		
		else {
			this.pieceIcon = new ImageIcon(getClass().getResource("/resources/WhitePawn.png"));
		}
		
	}

	//can move two spaces forward on first move, otherwise can move one space straight in front when not blocked
	//moves diagonal forward to capture pieces
	@Override
	public void updateMoves() {
		
	}
}
