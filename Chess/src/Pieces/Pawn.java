package Pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Board.Tile;
import Utils.ChessColor;
import Utils.Coordinate;

//TODO: Implement piece promotion and en passant

/**
 * This class represents the Pawn piece in a chess game.
 * @author James Hoxie
 *
 */
public class Pawn extends Piece {
	//member variable, set to true if this pawn has just used its double move, used later for other pawns to 
	//check if they are allowed to capture this piece in passing (en passant)
	private boolean enPassant = false;
	
	/**
	 * Class constructor, sets the coordinates and color for this Pawn
	 * @param coordinate the current coordinates of this Pawn
	 * @param color the ChessColor of this Pawn
	 */
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
	
	//for use in checking for check and checkmate, only generate diagonals this pawn can move to
	public Coordinate[] generateAttackSpaces(Tile[][] tiles) {
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		int upOrDown, dstCol, dstRow;
		int srcRow = pieceCoordinate.getRow();
		int srcCol = pieceCoordinate.getCol();
		int rowSize = tiles[0].length;
		int colSize = tiles.length;
		
		//set if this pawn will be able to move up the board or down the board, depending on color
		if(pieceColor == ChessColor.WHITE) {
			upOrDown = -1;
		}
		
		else {
			upOrDown = 1;
		}
		
		//space diagonal to left of pawn
		dstRow = srcRow + upOrDown*1;
		dstCol = srcCol + upOrDown*1;

		if(Coordinate.isValidCoordinate(new Coordinate(dstRow, dstCol), rowSize, colSize) &&
				!(tiles[dstRow][dstCol].isEmpty()) &&
				tiles[dstRow][dstCol].getPiece().getPieceColor() != pieceColor) {
			
			moves.add(new Coordinate(dstRow, dstCol));
		}

		//space diagonal to right of pawn
		dstRow = srcRow + upOrDown*1;
		dstCol = srcCol - upOrDown*1;

		if(Coordinate.isValidCoordinate(new Coordinate(dstRow, dstCol), rowSize, colSize) &&
				!(tiles[dstRow][dstCol].isEmpty()) &&
				tiles[dstRow][dstCol].getPiece().getPieceColor() != pieceColor) {
			
			moves.add(new Coordinate(dstRow, dstCol));
		}
		
		return moves.toArray(new Coordinate[moves.size()]);
	}

	//moves 1 space up or down depending on color, or moves diagonal to capture pieces
	//can move 2 spaces on its first move
	@Override
	public Coordinate[] generateMoves(Tile[][] tiles) {
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		int upOrDown, dstCol, dstRow, intCol, intRow;
		int srcRow = pieceCoordinate.getRow();
		int srcCol = pieceCoordinate.getCol();
		int rowSize = tiles[0].length;
		int colSize = tiles.length;
		Coordinate[] attackSpaces = generateAttackSpaces(tiles);
		
		//set if this pawn will be able to move up the board or down the board, depending on color
		if(pieceColor == ChessColor.WHITE) {
			upOrDown = -1;
		}
		
		else {
			upOrDown = 1;
		}
		
		//check if pawn can use double move
		if((srcRow == 1 && upOrDown == 1) || (srcRow == 6 && upOrDown == -1)) {
			//space two in front of pawn if its the first move
			dstRow = srcRow + upOrDown*2;
			intRow = srcRow + upOrDown*1;
			intCol = srcCol;
			dstCol = srcCol;
			
			if(tiles[dstRow][dstCol].isEmpty() && tiles[intRow][intCol].isEmpty()) {
				moves.add(new Coordinate(dstRow, dstCol));
			}
		}
		
		//space directly in front of pawn
		dstRow = srcRow + upOrDown*1;
		dstCol = srcCol;
		
		if(Coordinate.isValidCoordinate(new Coordinate(dstRow, dstCol), rowSize, colSize) &&
		   tiles[dstRow][dstCol].isEmpty()) {
			moves.add(new Coordinate(dstRow, dstCol));
		}
		
		//add attack spaces to moves
		for(Coordinate c: attackSpaces) {
			moves.add(c);
		}
		
		return moves.toArray(new Coordinate[moves.size()]);
	}
}
