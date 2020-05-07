package Pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Board.Tile;
import Utils.ChessColor;
import Utils.Coordinate;

/**
 * This class represents the Queen piece in a chess game.
 * @author James Hoxie
 *
 */
public class Queen extends Piece {
	/**
	 * Class constructor, sets the coordinates and color for this Queen
	 * @param pieceCoordinate the current coordinates of this Queen
	 * @param pieceColor the ChessColor of this Queen
	 */
	public Queen(Coordinate pieceCoordinate, ChessColor pieceColor) {
		super(pieceCoordinate, pieceColor, 4);
	}

	//moves straight or diagonal, combine moves that a rook and bishop could take from current position
	@Override
	public Coordinate[] generateMoves(Tile[][] tiles, int turnNumber) {
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
	
	public Queen copy() {
		return new Queen(getPieceCoordinate(), getPieceColor());	
	}
}