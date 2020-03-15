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
	 * @param coordinate the current coordinates of this Knight
	 * @param color the ChessColor of this Knight
	 */
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
	public Coordinate[] generateMoves(Tile[][] tiles) {
		ArrayList<Coordinate> potentialMoves = new ArrayList<Coordinate>();
		ArrayList<Coordinate> validMoves = new ArrayList<Coordinate>();
		
		int srcRow = pieceCoordinate.getRow();
		int srcCol = pieceCoordinate.getCol();
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
			  (tiles[move.getRow()][move.getCol()].isEmpty() || tiles[move.getRow()][move.getCol()].getPiece().getPieceColor() != pieceColor)) {
					validMoves.add(move);
			}
		}
		
		return validMoves.toArray(new Coordinate[validMoves.size()]);
	}
}