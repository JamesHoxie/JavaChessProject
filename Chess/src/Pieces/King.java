package Pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Board.Tile;
import Utils.ChessColor;
import Utils.Coordinate;

//TODO: Implement castling

/**
 * This class represents the King piece in a chess game.
 * @author James Hoxie
 *
 */
public class King extends Piece {
	/**
	 * Member variable to keep track of this King's position for the purpose of castling
	 */
	private boolean hasMoved = false;
	
	
	/**
	 * Member variable to keep track of whether this King is currently in check
	 */
	private boolean isInCheck = false;
	
	/**
	 * Class constructor, sets the coordinates and color for this King
	 * @param pieceCoordinate the current coordinates of this King
	 * @param pieceColor the ChessColor of this King
	 */
	public King(Coordinate pieceCoordinate, ChessColor pieceColor) {
		super(pieceCoordinate, pieceColor, 5);
	}

	
	/**
	 * sets this King to be in check
	 */
	public void setKingInCheck() {
		isInCheck = true;
	}
	
	/**
	 * sets this King to be out of check
	 */
	public void setKingOutOfCheck() {
		isInCheck = false;
	}
	
	
	/**
	 * Checks if this King is currently in check
	 * @return true if this King is in check, false otherwise
	 */
	public boolean isInCheck() {
		return isInCheck;
	}
	
	/**
	 * sets this King's status of hasMoved to true
	 */
	public void setKingHasMoved() {
		hasMoved = true;
	}
	
	/**
	 * sets this King's status of hasMoved to false
	 */
	public void setKingHasNotMoved() {
		hasMoved = false;
	}
	
	/**
	 * Checks if this King has been moved at least once this game
	 * @return true if this King has been moved at least once this game, false otherwise
	 */
	public boolean hasMoved() {
		return hasMoved;
	}
	
	//moves one space in any direction as long as that space would not put him in check
	@Override
	public Coordinate[] generateMoves(Tile[][] tiles, int turnNumber) {
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		
		int srcRow = getPieceCoordinate().getRow();
		int srcCol = getPieceCoordinate().getCol();
		int rowSize = tiles[0].length;
		int colSize = tiles.length;
		Coordinate nextPotentialMove;
		Tile destTile;
		
		//check all tiles in 8 directions by space, add spaces if empty or occupied by opposing piece color and
		//space would not put king in check
		
		//North			
		nextPotentialMove = new Coordinate(srcRow - 1, srcCol);
		if((Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
			destTile = tiles[srcRow - 1][srcCol];
			
			if((destTile.isEmpty() || destTile.getPiece().getPieceColor() != getPieceColor())) {
				moves.add(nextPotentialMove);
			}			
		}
		
		//North East			
		nextPotentialMove = new Coordinate(srcRow - 1, srcCol + 1);
		if((Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
			destTile = tiles[srcRow - 1][srcCol + 1];

			if((destTile.isEmpty() || destTile.getPiece().getPieceColor() != getPieceColor())) {
				moves.add(nextPotentialMove);
			}			
		}
		
		//East			
		nextPotentialMove = new Coordinate(srcRow, srcCol + 1);
		if((Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
			destTile = tiles[srcRow][srcCol + 1];

			if((destTile.isEmpty() ||destTile.getPiece().getPieceColor() != getPieceColor())) {
				moves.add(nextPotentialMove);
			}			
		}
		
		//South East			
		nextPotentialMove = new Coordinate(srcRow + 1, srcCol + 1);
		if((Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
			destTile = tiles[srcRow + 1][srcCol + 1];

			if((destTile.isEmpty() ||destTile.getPiece().getPieceColor() != getPieceColor())) {
				moves.add(nextPotentialMove);
			}			
		}
		
		//South		
		nextPotentialMove = new Coordinate(srcRow + 1, srcCol);
		if((Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
			destTile = tiles[srcRow + 1][srcCol];

			if((destTile.isEmpty() ||destTile.getPiece().getPieceColor() != getPieceColor())) {
				moves.add(nextPotentialMove);
			}			
		}
		
		//South West		
		nextPotentialMove = new Coordinate(srcRow + 1, srcCol - 1);
		if((Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
			destTile = tiles[srcRow + 1][srcCol - 1];

			if((destTile.isEmpty() ||destTile.getPiece().getPieceColor() != getPieceColor())) {
				moves.add(nextPotentialMove);
			}			
		}
		
		//West		
		nextPotentialMove = new Coordinate(srcRow, srcCol - 1);
		if((Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
			destTile = tiles[srcRow][srcCol - 1];

			if((destTile.isEmpty() ||destTile.getPiece().getPieceColor() != getPieceColor())) {
				moves.add(nextPotentialMove);
			}			
		}
		
		//North West	
		nextPotentialMove = new Coordinate(srcRow - 1, srcCol - 1);
		if((Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
			destTile = tiles[srcRow - 1][srcCol - 1];

			if((destTile.isEmpty() ||destTile.getPiece().getPieceColor() != getPieceColor())) {
				moves.add(nextPotentialMove);
			}			
		}	
		
		//check for castling moves
		if(!hasMoved && !isInCheck) {
			boolean allSpacesEmptyBetween = true;
			Rook rook;
			
			for(int i = 0; i < tiles[srcRow].length; i++) {
				if(tiles[srcRow][i].getPiece() instanceof Rook) {
					rook = (Rook) tiles[srcRow][i].getPiece();
					
					//check that all spaces in between rook and king are empty
					//king side castle
					if(!(rook.hasMoved()) && rook.getPieceColor() == getPieceColor()) {
						if(srcCol < rook.getPieceCoordinate().getCol()) {
							for(int j = srcCol + 1; j < rook.getPieceCoordinate().getCol(); j++) {
								if(!(tiles[srcRow][j].isEmpty())) {
									allSpacesEmptyBetween = false;
									break;
								}
							}
							
							if(allSpacesEmptyBetween) {
								moves.add(new Coordinate(srcRow, rook.getPieceCoordinate().getCol()));
							}
							
							allSpacesEmptyBetween = true;						
						}
						
						//srcCol > rook.getPieceCoordinate().getCol()
						//queen side castle
						else {
							for(int j = srcCol - 1; j > rook.getPieceCoordinate().getCol() + 1; j--) {
								if(!(tiles[srcRow][j].isEmpty())) {
									allSpacesEmptyBetween = false;
									break;
								}
							}
							
							if(allSpacesEmptyBetween) {
								moves.add(new Coordinate(srcRow, rook.getPieceCoordinate().getCol()));
							}
							
							allSpacesEmptyBetween = true;	
						}
					}
				}
			}
		}
		
		return moves.toArray(new Coordinate[moves.size()]);
	}

}