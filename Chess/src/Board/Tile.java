package Board;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Pieces.Piece;
import Utils.ChessColor;
import Utils.Coordinate;

/**
 * This class represents each square on the chess board
 * @author James Hoxie
 *
 */
public class Tile extends JButton{
	private final Coordinate tileCoordinate;
	private Piece piece = null;
	private final ChessColor tileColor;
	
	/**
	 * Class constructor, assigns coordinates for tile and the ChessColor of the tile
	 * @param coordinate the coordinates of this tile on the board
	 * @param color the ChessColor of this tile
	 */
	public Tile(Coordinate coordinate, ChessColor color) {
		this.tileCoordinate = new Coordinate(coordinate.getRow(), coordinate.getCol());
		this.tileColor = color;
		
		if(this.tileColor == ChessColor.BLACK) {
			this.setBackground(new Color(40, 40, 40));
		}
		
		else {
			this.setBackground(new Color(245, 245, 245));
		}
	}
	
	/**
	 * 
	 * @return the coordinates of this tile on the board
	 */
	public Coordinate getCoordinate() {
		return this.tileCoordinate;
	}
	
	/**
	 * 
	 * @return the piece occupying this tile on the board or null if empty
	 */
	public Piece getPiece() {
		return this.piece;
	}
	
	/**
	 * 
	 * @return true if this tile has no piece occupying it
	 */
	public boolean isEmpty() {
		return this.piece == null;
	}
	
	/**
	 * Sets a piece on this tile on the chess board
	 * @param piece the piece to be placed on this tile
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	/**
	 * Displays the current piece occupying this tile on the GUI or nothing if this tile is empty
	 */
	public void displayPiece() {
		if(this.piece != null) {
			this.setIcon(this.piece.getPieceIcon());
		}
		
		else {
			this.setIcon(null);
		}
	}
}