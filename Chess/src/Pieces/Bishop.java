package Pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Board.Tile;
import Utils.ChessColor;
import Utils.Coordinate;

/**
 * 
 * This class represents the Bishop piece in a chess game
 * @author James Hoxie
 *
 */
public class Bishop extends Piece {
	/**
	 * Class constructor, sets the coordinates and color for this Bishop
	 * @param pieceCoordinate the current coordinates of this Bishop
	 * @param pieceColor the ChessColor of this Bishop
	 */
	public Bishop(Coordinate pieceCoordinate, ChessColor pieceColor) {
		super(pieceCoordinate, pieceColor, 3);
	}

	/**
	 * Static class function used to allow the Queen piece to generate moves a Bishop piece could take
	 * @param tiles 2D array of tiles for the chess board being played on
	 * @param piece The piece being moved; either a Bishop or a Queen
	 * @return
	 */
	public static Coordinate[] generateBishopMoves(Tile[][] tiles, Piece piece) {
		ArrayList<Coordinate> validMoves = new ArrayList<Coordinate>();

		int srcRow = piece.getPieceCoordinate().getRow();
		int srcCol = piece.getPieceCoordinate().getCol();
		ChessColor pieceColor = piece.getPieceColor();
		int rowSize = tiles[0].length;
		int colSize = tiles.length;
		Coordinate nextPotentialMove;

		//check all tiles in 4 diagonal directions, add spaces if empty, stop when you get to the edge of the board
		//or hit an occupied space, add the occupied space if the piece occupying it doesnt match this piece's color

		//North West
		for(int r = srcRow - 1, c = srcCol - 1; r >= 0 && c >= 0; r--, c--) {
			nextPotentialMove = new Coordinate(r, c);

			if(!(Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
				break;
			}

			if(tiles[r][c].isEmpty()) {
				validMoves.add(nextPotentialMove);
			}

			else {
				if(tiles[r][c].getPiece().getPieceColor() != pieceColor) {
					validMoves.add(nextPotentialMove);
				}

				break;
			}	
		}

		//North East
		for(int r = srcRow - 1, c = srcCol + 1; r >= 0 && c < colSize; r--, c++) {	
			nextPotentialMove = new Coordinate(r, c);

			if(!(Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
				break;
			}

			if(tiles[r][c].isEmpty()) {
				validMoves.add(nextPotentialMove);
			}

			else {
				if(tiles[r][c].getPiece().getPieceColor() != pieceColor) {
					validMoves.add(nextPotentialMove);
				}

				break;
			}	
		}

		//South West
		for(int r = srcRow + 1, c = srcCol - 1; r < rowSize && c >= 0; r++, c--) {	
			nextPotentialMove = new Coordinate(r, c);

			if(!(Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
				break;
			}

			if(tiles[r][c].isEmpty()) {
				validMoves.add(nextPotentialMove);
			}

			else {
				if(tiles[r][c].getPiece().getPieceColor() != pieceColor) {
					validMoves.add(nextPotentialMove);
				}

				break;
			}	
		}

		//South East
		for(int r = srcRow + 1, c = srcCol + 1; r < rowSize && c < colSize; r++, c++) {		
			nextPotentialMove = new Coordinate(r, c);

			if(!(Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
				break;
			}
			if(tiles[r][c].isEmpty()) {
				validMoves.add(nextPotentialMove);
			}

			else {
				if(tiles[r][c].getPiece().getPieceColor() != pieceColor) {
					validMoves.add(nextPotentialMove);
				}

				break;
			}	
		}

		return validMoves.toArray(new Coordinate[validMoves.size()]);
	}

	//moves diagonal in any direction
	@Override
	public Coordinate[] generateMoves(Tile[][] tiles, int turnNumber) {
		return generateBishopMoves(tiles, this);
	}
	
	public Bishop copy() {
		return new Bishop(getPieceCoordinate(), getPieceColor());
		
	}
}