package Pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Board.Tile;
import Utils.ChessColor;
import Utils.Coordinate;


/**
 * This class represents the Pawn piece in a chess game.
 * @author James Hoxie
 *
 */
public class Pawn extends Piece {
	//Member variable, set to true if this pawn can be captured en passant
	private boolean enPassant = false;
	//Member variable, stores the turn that this pawn used its double move
	private int turnUsedDoubleMove = -1;
	//Member variable, set to 1 or -1 to determine if this pawn moves up or down the board
	private int upOrDown;
	//Member variable, stores the last row this pawn was on before this move
	private int lastRow = -1;
	
	/**
	 * Class constructor, sets the coordinates and color for this Pawn
	 * @param pieceCoordinate the current coordinates of this Pawn
	 * @param pieceColor the ChessColor of this Pawn
	 */
	public Pawn(Coordinate pieceCoordinate, ChessColor pieceColor) {
		super(pieceCoordinate, pieceColor, 0);
		
		if(pieceColor == ChessColor.WHITE) {
			upOrDown = -1;
		}

		else {
			upOrDown = 1;
		}
	}
	
	public int getUpOrDown() {
		return upOrDown;
	}
	
	//for use in checking for check and checkmate, only generate diagonals this pawn can move to
	public Coordinate[] generateAttackSpaces(Tile[][] tiles) {
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		int upOrDown, dstCol, dstRow;
		int srcRow = getPieceCoordinate().getRow();
		int srcCol = getPieceCoordinate().getCol();
		int rowSize = tiles[0].length;
		int colSize = tiles.length;
		
		//set if this pawn will be able to move up the board or down the board, depending on color
		if(getPieceColor() == ChessColor.WHITE) {
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
				tiles[dstRow][dstCol].getPiece().getPieceColor() != getPieceColor()) {
			
			moves.add(new Coordinate(dstRow, dstCol));
		}

		//space diagonal to right of pawn
		dstRow = srcRow + upOrDown*1;
		dstCol = srcCol - upOrDown*1;

		if(Coordinate.isValidCoordinate(new Coordinate(dstRow, dstCol), rowSize, colSize) &&
				!(tiles[dstRow][dstCol].isEmpty()) &&
				tiles[dstRow][dstCol].getPiece().getPieceColor() != getPieceColor()) {
			
			moves.add(new Coordinate(dstRow, dstCol));
		}
		
		return moves.toArray(new Coordinate[moves.size()]);
	}

	//moves 1 space up or down depending on color, or moves diagonal to capture pieces
	//can move 2 spaces on its first move
	@Override
	public Coordinate[] generateMoves(Tile[][] tiles, int turnNumber) {
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		int dstCol, dstRow, intCol, intRow;
		int srcRow = getPieceCoordinate().getRow();
		int srcCol = getPieceCoordinate().getCol();
		int rowSize = tiles[0].length;
		int colSize = tiles.length;
		Coordinate[] attackSpaces = generateAttackSpaces(tiles);
		
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
		
		//check for enpassant captures
		dstRow = srcRow + upOrDown*1;
		dstCol = srcCol + 1;

		if(Coordinate.isValidCoordinate(new Coordinate(dstRow, dstCol), rowSize, colSize) &&
				Coordinate.isValidCoordinate(new Coordinate(srcRow, dstCol), rowSize, colSize) && 
				tiles[dstRow][dstCol].isEmpty() &&
				tiles[srcRow][dstCol].getPiece() instanceof Pawn &&
				tiles[srcRow][dstCol].getPiece().getPieceColor() != getPieceColor() &&
				((Pawn) tiles[srcRow][dstCol].getPiece()).canBeCapturedEnPassant(turnNumber)) {
			moves.add(new Coordinate(dstRow, dstCol));
		}
		
		dstCol = srcCol - 1;
		
		if(Coordinate.isValidCoordinate(new Coordinate(dstRow, dstCol), rowSize, colSize) &&
				Coordinate.isValidCoordinate(new Coordinate(srcRow, dstCol), rowSize, colSize) &&
				tiles[dstRow][dstCol].isEmpty() &&
				tiles[srcRow][dstCol].getPiece() instanceof Pawn &&
				tiles[srcRow][dstCol].getPiece().getPieceColor() != getPieceColor() &&
				((Pawn) tiles[srcRow][dstCol].getPiece()).canBeCapturedEnPassant(turnNumber)) {
			moves.add(new Coordinate(dstRow, dstCol));
		}	
		
		return moves.toArray(new Coordinate[moves.size()]);
	}
	
	/**
	 * Used to determine if this pawn can be captured with an enpassant move
	 * @param turnNumber the current turn this move is being attempted on
	 * @return true if this pawn can be captured enpassant this turn
	 */
	public boolean canBeCapturedEnPassant(int turnNumber) {
		return enPassant && (turnNumber - turnUsedDoubleMove == 1);
	}
	
	/**
	 * Returns whether this pawn has just used its double move
	 * @return true if the last move this pawn made was a double move
	 */
	public boolean usedDoubleMove() {
		return lastRow != -1 && Math.abs(lastRow - getPieceCoordinate().getRow()) == 2;
	}
	
	/**
	 * Sets the enPassant boolean for this pawn based on its last position
	 * @param row the last row this pawn was on before its current move
	 * @param turnNumber the current turn number
	 */
	public void setEnPassant(int row, int turnNumber) {
		lastRow = row;
		
		if(usedDoubleMove()) {
			turnUsedDoubleMove = turnNumber;
			enPassant = true;
		}
		
		else {
			enPassant = false;
		}		
	}
	
	/**
	 * Sets the turn a double move was used for this pawn, for use in loading a saved game state
	 * @param turn the turn that this pawn used a double move in the saved game state
	 */
	public void setTurnUsedDoubleMove(int turn) {
		turnUsedDoubleMove = turn;
	}
	
	/**
	 * Sets the last row that this pawn was on, for use in loading a saved game state
	 * @param row the last row that this pawn was on in the saved game state
	 */
	public void setLastRow(int row) {
		lastRow = row;
	}
	
	//add state to string for pawn
	@Override
	public String toString() {
		return super.toString() + "," + enPassant + "," + turnUsedDoubleMove + "," +lastRow;
	}
}
