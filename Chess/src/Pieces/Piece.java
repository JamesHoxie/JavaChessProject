package Pieces;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

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
	private Coordinate pieceCoordinate = null;
	private ChessColor pieceColor;
	private Icon pieceIcon;
	private final String[] whitePieceIcons = {"/resources/WhitePawn.png", "/resources/WhiteRook.png",
										"/resources/WhiteKnight.png", "/resources/WhiteBishop.png",
										"/resources/WhiteQueen.png", "/resources/WhiteKing.png"};
	private final String[] blackPieceIcons = {"/resources/BlackPawn.png", "/resources/BlackRook.png", 
										"/resources/BlackKnight.png", "/resources/BlackBishop.png",
										"/resources/BlackQueen.png" ,"/resources/BlackKing.png"};
	
	/**
	 * Constructor for all pieces
	 * @param pieceCoordinate the coordinates of this piece on the board
	 * @param pieceColor the ChessColor of this piece
	 * @param pieceType an int from 0 to 5 representing if this piece will be a Pawn, Rook, Knight, Bishop, Queen, or King
	 */
	public Piece(Coordinate pieceCoordinate, ChessColor pieceColor, int pieceType) {
		this.pieceCoordinate = pieceCoordinate;
		this.pieceColor = pieceColor;
		
		if(pieceColor == ChessColor.BLACK) {
			setPieceIcon(new ImageIcon(getClass().getResource(blackPieceIcons[pieceType])));
		}

		else {
			setPieceIcon(new ImageIcon(getClass().getResource(whitePieceIcons[pieceType])));
		}
	}
	
	/**
	 * 
	 * @param tiles 2D array representing the board being played on
	 * @param turnNumber the current turn number of this chess game
	 * @return array containing all valid moves this piece can take given its position on the board and the rules of chess
	 */
	public abstract Coordinate[] generateMoves(Tile[][] tiles, int turnNumber);
	
	/**
	 * 
	 * @return the current coordinates of this piece
	 */
	public Coordinate getPieceCoordinate() {
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
	public void setPieceCoordinate(Coordinate coordinate) {
		this.pieceCoordinate.setRow(coordinate.getRow());
		this.pieceCoordinate.setCol(coordinate.getCol());
	}
	
	/**
	 * Returns the name of this piece as a string(Rook, Knight, Bishop, Queen, King)
	 */
	public String getPieceType() {
		//getClass() + "" = "class Pieces.<PieceName>"
		return (getClass() + "").split("\\.")[1];
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
			   p.getPieceCoordinate() == pieceCoordinate;
	}
	
	@Override
	public String toString() {
		return getPieceType() + "," + getPieceColor();
	}
}