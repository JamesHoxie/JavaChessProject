package Pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Board.Tile;
import Utils.ChessColor;
import Utils.Coordinate;

/**
 * This class represents the King piece in a chess game.
 * @author James Hoxie
 *
 */
public class King extends Piece {
	/**
	 * Class constructor, sets the coordinates and color for this King
	 * @param coordinate the current coordinates of this King
	 * @param color the ChessColor of this King
	 */
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
	
	public boolean inCheck(Tile[][] tiles, Coordinate currPosition) {
		return false;
	}
	
	public boolean inCheckMate(Tile[][] tiles, Coordinate currPosition) {
		return false;
	}

	//moves one space in any direction as long as that space would not put him in check
	@Override
	public Coordinate[] generateMoves(Tile[][] tiles) {
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		
		int srcRow = pieceCoordinate.getRow();
		int srcCol = pieceCoordinate.getCol();
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
			
			if((destTile.isEmpty() || destTile.getPiece().getPieceColor() != pieceColor)) {
				moves.add(nextPotentialMove);
			}			
		}
		
		//North East			
		nextPotentialMove = new Coordinate(srcRow - 1, srcCol + 1);
		if((Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
			destTile = tiles[srcRow - 1][srcCol + 1];

			if((destTile.isEmpty() || destTile.getPiece().getPieceColor() != pieceColor)) {
				moves.add(nextPotentialMove);
			}			
		}
		
		//East			
		nextPotentialMove = new Coordinate(srcRow, srcCol + 1);
		if((Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
			destTile = tiles[srcRow][srcCol + 1];

			if((destTile.isEmpty() ||destTile.getPiece().getPieceColor() != pieceColor)) {
				moves.add(nextPotentialMove);
			}			
		}
		
		//South East			
		nextPotentialMove = new Coordinate(srcRow + 1, srcCol + 1);
		if((Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
			destTile = tiles[srcRow + 1][srcCol + 1];

			if((destTile.isEmpty() ||destTile.getPiece().getPieceColor() != pieceColor)) {
				moves.add(nextPotentialMove);
			}			
		}
		
		//South		
		nextPotentialMove = new Coordinate(srcRow + 1, srcCol);
		if((Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
			destTile = tiles[srcRow + 1][srcCol];

			if((destTile.isEmpty() ||destTile.getPiece().getPieceColor() != pieceColor)) {
				moves.add(nextPotentialMove);
			}			
		}
		
		//South West		
		nextPotentialMove = new Coordinate(srcRow + 1, srcCol - 1);
		if((Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
			destTile = tiles[srcRow + 1][srcCol - 1];

			if((destTile.isEmpty() ||destTile.getPiece().getPieceColor() != pieceColor)) {
				moves.add(nextPotentialMove);
			}			
		}
		
		//West		
		nextPotentialMove = new Coordinate(srcRow, srcCol - 1);
		if((Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
			destTile = tiles[srcRow][srcCol - 1];

			if((destTile.isEmpty() ||destTile.getPiece().getPieceColor() != pieceColor)) {
				moves.add(nextPotentialMove);
			}			
		}
		
		//North West	
		nextPotentialMove = new Coordinate(srcRow - 1, srcCol - 1);
		if((Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
			destTile = tiles[srcRow - 1][srcCol - 1];

			if((destTile.isEmpty() ||destTile.getPiece().getPieceColor() != pieceColor)) {
				moves.add(nextPotentialMove);
			}			
		}	
		
		return moves.toArray(new Coordinate[moves.size()]);
	}

}