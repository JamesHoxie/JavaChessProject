package Pieces;

import javax.swing.ImageIcon;

import Board.Tile;
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

	@Override
	public Coordinate[] generateMoves() {
		return null;
		// TODO Auto-generated method stub
		
	}
}
