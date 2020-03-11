package Pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Board.Tile;
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

	//moves straight or diagonal, combine moves that a rook and bishop could take from current position
	@Override
	public Coordinate[] generateMoves(Tile[][] tiles) {
		Coordinate[] rookMoves = Rook.generateRookMoves(tiles, this);
		Coordinate[] bishopMoves = Bishop.generateBishopMoves(tiles, this);
		Coordinate[] queenMoves = new Coordinate[rookMoves.length + bishopMoves.length];
		int queenIndex = 0;
		
		for(int index = 0; index < rookMoves.length; index++) {
			queenMoves[queenIndex] = rookMoves[index];
			queenIndex++;
		}
		
		for(int index = 0; index < bishopMoves.length; index++) {
			queenMoves[queenIndex] = bishopMoves[index];
			queenIndex++;
		}
		
		return queenMoves; 
	}
}