package Pieces;
import java.util.ArrayList;

import javax.swing.Icon;

import Board.Board;
import Board.Tile;
import Utils.ChessColor;
import Utils.Coordinate;

public abstract class Piece {
	protected Coordinate pieceCoordinate = null;
	protected ChessColor pieceColor = null;
	protected Icon pieceIcon = null;
	
//	//checks if this piece can move to the coordinate parameter 
//	//specified by checking if that coordinate is in the valid move list for this piece
//	public boolean canMoveTo(Coordinate coordinate) {
//		return this.moves.contains(coordinate);
//	}
//	
//	//checks if this piece can move to given tile
//	public boolean canMoveTo(Tile tile) {
//		return this.moves.contains(tile.getCoordinate());
//	}

	//generate move list for this piece based on piece type and current tile on the board
	public abstract Coordinate[] generateMoves(Tile[][] tiles);
	
	//returns the current coordinates of this piece
	public Coordinate getCoordinate() {
		return this.pieceCoordinate;
	}
	
	//returns the icon for this piece
	public Icon getPieceIcon() {
		return this.pieceIcon;
	}

	//returns the chess color for this piece (WHITE or BLACK)
	public ChessColor getPieceColor() {
		return this.pieceColor;
	}
	
	//sets the icon for this piece that will be displayed on the tile it is on
	public void setPieceIcon(Icon pieceIcon) {
		this.pieceIcon = pieceIcon;
	}
	
	//sets the coordinates of this piece to coordinate
	public void setCoordinate(Coordinate coordinate) {
		this.pieceCoordinate.setRow(coordinate.getRow());
		this.pieceCoordinate.setCol(coordinate.getCol());
	}
}