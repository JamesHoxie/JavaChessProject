package Pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Board.Tile;
import Utils.ChessColor;
import Utils.Coordinate;

/**
 * This class represents the Rook piece in a chess game.
 * @author James Hoxie
 *
 */
public class Rook extends Piece {
	/**
	 * Member variable to keep track of this Rook's position for the purpose of castling
	 */
	private boolean hasMoved = false;
	
	/**
	 * Class constructor, sets the coordinates and color for this Rook
	 * @param pieceCoordinate the current coordinates of this Rook
	 * @param pieceColor the ChessColor of this Rook
	 */
	public Rook(Coordinate pieceCoordinate, ChessColor pieceColor) {
		super(pieceCoordinate, pieceColor, 1);
	}
	
	/**
	 * sets this Rook's status of hasMoved to true
	 */
	public void setRookHasMoved() {
		hasMoved = true;
	}
	
	/**
	 * sets this Rook's status of hasMoved to false
	 */
	public void setRookHasNotMoved() {
		hasMoved = false;
	}
	
	/**
	 * Checks if this Rook has been moved at least once this game
	 * @return true if this Rook has been moved at least once this game, false otherwise
	 */
	public boolean hasMoved() {
		return hasMoved;
	}
	
	//movement calculations done statically to allow queen piece to use
	public static Coordinate[] generateRookMoves(Tile[][] tiles, Piece piece) {
		ArrayList<Coordinate> validMoves = new ArrayList<Coordinate>();
		
		int srcRow = piece.getPieceCoordinate().getRow();
		int srcCol = piece.getPieceCoordinate().getCol();
		ChessColor pieceColor = piece.getPieceColor();
		int rowSize = tiles[0].length;
		int colSize = tiles.length;
		Coordinate nextPotentialMove;
		
		//check all tiles in 4 directions, add spaces if empty, stop when you get to the edge of the board
		//or hit an occupied space, add the occupied space if the piece occupying it doesnt match this piece's color
		
		//North
		for(int r = srcRow - 1; r < rowSize; r--) {	
			nextPotentialMove = new Coordinate(r, srcCol);
			
			if(!(Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
				break;
			}
			
			if(tiles[r][srcCol].isEmpty()) {
				validMoves.add(nextPotentialMove);
			}
			
			else {
				if(tiles[r][srcCol].getPiece().getPieceColor() != pieceColor) {
					validMoves.add(nextPotentialMove);
				}
				
				break;
			}	
		}
		
		//South
		for(int r = srcRow + 1; r >= 0; r++) {	
			nextPotentialMove = new Coordinate(r, srcCol);
			
			if(!(Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
				break;
			}
			
			if(tiles[r][srcCol].isEmpty()) {
				validMoves.add(nextPotentialMove);
			}
			
			else {
				if(tiles[r][srcCol].getPiece().getPieceColor() != pieceColor) {
					validMoves.add(nextPotentialMove);
				}
				
				break;
			}	
		}
		
		//East
		for(int c = srcCol + 1; c < colSize; c++) {	
			nextPotentialMove = new Coordinate(srcRow, c);
			
			if(!(Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
				break;
			}
			
			if(tiles[srcRow][c].isEmpty()) {
				validMoves.add(nextPotentialMove);
			}
			
			else {
				if(tiles[srcRow][c].getPiece().getPieceColor() != pieceColor) {
					validMoves.add(nextPotentialMove);
				}
				
				break;
			}	
		}
		
		//West
		for(int c = srcCol - 1; c >= 0; c--) {		
			nextPotentialMove = new Coordinate(srcRow, c);
			
			if(!(Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
				break;
			}
			if(tiles[srcRow][c].isEmpty()) {
				validMoves.add(nextPotentialMove);
			}
			
			else {
				if(tiles[srcRow][c].getPiece().getPieceColor() != pieceColor) {
					validMoves.add(nextPotentialMove);
				}
				
				break;
			}	
		}
		
		return validMoves.toArray(new Coordinate[validMoves.size()]);
	}

	//moves straight, never diagonal
	@Override
	public Coordinate[] generateMoves(Tile[][] tiles, int turnNumber) {
		return generateRookMoves(tiles, this);
	}
	
	//add state to string for rook
	@Override
	public String toString() {
		return super.toString() + "," + hasMoved;
	}
}