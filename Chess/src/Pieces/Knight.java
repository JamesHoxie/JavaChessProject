package Pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Board.Tile;
import Utils.ChessColor;
import Utils.Coordinate;

/**
 * This class represents the Knight piece in a chess game.
 * @author James Hoxie
 *
 */
public class Knight extends Piece {
	/**
	 * Class constructor, sets the coordinates and color for this Knight
	 * @param pieceCoordinate the current coordinates of this Knight
	 * @param pieceColor the ChessColor of this Knight
	 */
	public Knight(Coordinate pieceCoordinate, ChessColor pieceColor) {
		super(pieceCoordinate, pieceColor, 2);		
	}

	//moves in L shape
	@Override
	public Coordinate[] generateMoves(Tile[][] tiles, int turnNumber) {
		ArrayList<Coordinate> potentialMoves = new ArrayList<Coordinate>();
		ArrayList<Coordinate> validMoves = new ArrayList<Coordinate>();
		
		int srcRow = getPieceCoordinate().getRow();
		int srcCol = getPieceCoordinate().getCol();
		int rowSize = tiles[0].length;
		int colSize = tiles.length;
		
		potentialMoves.add(new Coordinate(srcRow - 1, srcCol - 2));
		potentialMoves.add(new Coordinate(srcRow - 2, srcCol - 1));
		potentialMoves.add(new Coordinate(srcRow - 2, srcCol + 1));
		potentialMoves.add(new Coordinate(srcRow - 1, srcCol + 2));
		potentialMoves.add(new Coordinate(srcRow + 1, srcCol - 2));
		potentialMoves.add(new Coordinate(srcRow + 2, srcCol - 1));
		potentialMoves.add(new Coordinate(srcRow + 2, srcCol + 1));
		potentialMoves.add(new Coordinate(srcRow + 1, srcCol + 2));
		
		for(Coordinate move: potentialMoves) {
			if(Coordinate.isValidCoordinate(move, rowSize, colSize) &&
			  (tiles[move.getRow()][move.getCol()].isEmpty() || tiles[move.getRow()][move.getCol()].getPiece().getPieceColor() != getPieceColor())) {
					validMoves.add(move);
			}
		}
		
		return validMoves.toArray(new Coordinate[validMoves.size()]);
	}
	
	public Knight copy() {
		return new Knight(getPieceCoordinate(),getPieceColor());
	}
}