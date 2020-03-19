package Pieces;
import java.util.ArrayList;

import javax.swing.Icon;

import Board.Board;
import Board.Tile;
import Utils.ChessColor;
import Utils.Coordinate;

/**
 * This class represents the abstract object of a piece in a game of chess (Pawn, Knight, Bishop, Rook, King, Queen)
 * @author James Hoxie
 *
 */
public abstract class Piece {
	protected Coordinate pieceCoordinate = null;
	protected ChessColor pieceColor = null;
	protected Icon pieceIcon = null;
	
	/**
	 * 
	 * @param tiles 2D array representing the board being played on
	 * @return array containing all valid moves this piece can take given its position on the board and the rules of chess
	 */
	public abstract Coordinate[] generateMoves(Tile[][] tiles);
	
	/**
	 * 
	 * @return the current coordinates of this piece
	 */
	public Coordinate getCoordinate() {
		return this.pieceCoordinate;
	}
	
	/**
	 * 
	 * @return the piece icon for this piece
	 */
	public Icon getPieceIcon() {
		return this.pieceIcon;
	}

	/**
	 * 
	 * @return the ChessColor for this piece; either ChessColor.BLACK or ChessColor.WHITE
	 */
	public ChessColor getPieceColor() {
		return this.pieceColor;
	}
	
	/**
	 * Sets the icon to be displayed for this piece on the GUI
	 * @param pieceIcon the icon to be displayed for this piece on the GUI
	 */
	public void setPieceIcon(Icon pieceIcon) {
		this.pieceIcon = pieceIcon;
	}
	
	/**
	 * Sets the current coordinates for this piece on the board
	 * @param coordinate the updated coordinates to be set as the current coordinates for this piece
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.pieceCoordinate.setRow(coordinate.getRow());
		this.pieceCoordinate.setCol(coordinate.getCol());
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Piece)) {
			return false;
		}
		
		Piece p = (Piece) o;
		
		return p.getClass() == this.getClass() &&
			   p.getPieceColor() == pieceColor &&
			   p.getCoordinate() == pieceCoordinate;
	}
}