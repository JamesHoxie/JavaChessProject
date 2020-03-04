package Pieces;
import java.util.ArrayList;

import javax.swing.Icon;

import Board.Board;
import Utils.ChessColor;
import Utils.Coordinate;

public abstract class Piece {
	protected Coordinate pieceCoordinate = null;
	protected ArrayList<Coordinate> moves = null;
	protected ChessColor pieceColor = null;
	protected Icon pieceIcon = null;
	
	//returns the current coordinates of this piece
	public Coordinate getCoordinate() {
		return this.pieceCoordinate;
	}
	
	//checks if this piece can move to the coordinate parameter 
	//specified by checking if that coordinate is in the valid move list for this piece
	public boolean canMoveTo(Coordinate coordinate) {
		return this.moves.contains(coordinate);
	}
	
	//updates this piece's valid move list based on current coordinates and where the piece can legally move
	public abstract void updateMoves();
	
	//sets the icon for this piece that will be displayed on the tile it is on
	public void setPieceIcon(Icon pieceIcon) {
		this.pieceIcon = pieceIcon;
	}
	
	//returns the icon for this piece
	public Icon getPieceIcon() {
		return this.pieceIcon;
	}

}