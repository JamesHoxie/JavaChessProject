package Pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Board.Tile;
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
	public Coordinate[] generateMoves(Tile[][] tiles) {
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		
		//TODO: generate move list here
		
		return moves.toArray(new Coordinate[moves.size()]);
	}

}