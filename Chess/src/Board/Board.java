package Board;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;
import Utils.ChessColor;
import Utils.Coordinate;

//board class contains 2d array of tiles that contain pieces used for play
public class Board extends JPanel{
	private int xSize, ySize;
	private Tile[][] tiles;

	void setUpPieces() {
		//Set up white pieces
		tiles[7][0].setPiece(new Rook(new Coordinate(7, 0), ChessColor.WHITE));
		tiles[7][1].setPiece(new Knight(new Coordinate(7, 1), ChessColor.WHITE));
		tiles[7][2].setPiece(new Bishop(new Coordinate(7, 2), ChessColor.WHITE));
		tiles[7][3].setPiece(new Queen(new Coordinate(7, 3), ChessColor.WHITE));
		tiles[7][4].setPiece(new King(new Coordinate(7, 4), ChessColor.WHITE));
		tiles[7][5].setPiece(new Bishop(new Coordinate(7, 5), ChessColor.WHITE));
		tiles[7][6].setPiece(new Knight(new Coordinate(7, 6), ChessColor.WHITE));
		tiles[7][7].setPiece(new Rook(new Coordinate(7, 7), ChessColor.WHITE));
		
		for(int i = 0; i < tiles[7].length; i++) {
			tiles[7][i].displayPiece();
		}
		
		for(int i = 0; i < tiles[6].length; i++) {
			tiles[6][i].setPiece(new Pawn(new Coordinate(6, i), ChessColor.WHITE));
			tiles[6][i].displayPiece();
		}
		
		//Set up black pieces
		tiles[0][0].setPiece(new Rook(new Coordinate(0, 0), ChessColor.BLACK));
		tiles[0][1].setPiece(new Knight(new Coordinate(0, 1), ChessColor.BLACK));
		tiles[0][2].setPiece(new Bishop(new Coordinate(0, 2), ChessColor.BLACK));
		tiles[0][3].setPiece(new Queen(new Coordinate(0, 3), ChessColor.BLACK));
		tiles[0][4].setPiece(new King(new Coordinate(0, 4), ChessColor.BLACK));
		tiles[0][5].setPiece(new Bishop(new Coordinate(0, 5), ChessColor.BLACK));
		tiles[0][6].setPiece(new Knight(new Coordinate(0, 6), ChessColor.BLACK));
		tiles[0][7].setPiece(new Rook(new Coordinate(0, 7), ChessColor.BLACK));
		
		for(int i = 0; i < tiles[0].length; i++) {
			tiles[0][i].displayPiece();
		}
		
		for(int i = 0; i < tiles[1].length; i++) {
			tiles[1][i].setPiece(new Pawn(new Coordinate(6, i), ChessColor.BLACK));
			tiles[1][i].displayPiece();
		}
	}
	
	void setUpTiles(){
		//loop fills tiles array and fills board with JButtons to act as spaces, alternate between black and white tiles
		boolean setBlack;
		for(int i = 0; i < xSize; i++) {
			//even rows start with white, odd rows start with black
			if(i % 2 == 0) {
				setBlack = false;
			}

			else {
				setBlack = true;
			}

			for(int j = 0; j < ySize; j++) {
				if(setBlack) {
					tiles[i][j] = new Tile(new Coordinate(i, j), ChessColor.BLACK);
					setBlack = false;
				}

				else {
					tiles[i][j] = new Tile(new Coordinate(i, j), ChessColor.WHITE);
					setBlack = true;
				}

				add(tiles[i][j]);
				tiles[i][j].displayPiece();
			}

			setBlack = true;
		}
	}
	
	//class constructor, takes x and y size and assigns, also fills tiles array with empty tiles and sets up pieces on the board
	public Board(int xSize, int ySize) {
		setLayout(new GridLayout(8, 8));
		this.xSize = xSize;
		this.ySize = ySize;
		tiles = new Tile[xSize][ySize];
		setUpTiles();
		setUpPieces();
	}
	
	//returns tile of this board at given coordinate, coordinate must be valid
	public Tile getTile(Coordinate coordinate) {
		return tiles[coordinate.getRow()][coordinate.getCol()];
	}
	
	//returns true if specified coordinate is within the bounds of the board
	public boolean isValidCoordinate(Coordinate coordinate) {
		return coordinate.getRow() < 0 || coordinate.getRow() > xSize || coordinate.getCol() < 0 || coordinate.getCol() > ySize;
	}
}