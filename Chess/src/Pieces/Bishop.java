package Pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Board.Tile;
import Utils.ChessColor;
import Utils.Coordinate;

public class Bishop extends Piece {
	public Bishop(Coordinate coordinate, ChessColor color) {
		this.pieceCoordinate = coordinate;
		this.pieceColor = color;

		if(color == ChessColor.BLACK) {
			this.pieceIcon = new ImageIcon(getClass().getResource("/resources/BlackBishop.png"));
		}

		else {
			this.pieceIcon = new ImageIcon(getClass().getResource("/resources/WhiteBishop.png"));
		}

	}

	//move calculation done statically to allow queen to use
	public static Coordinate[] generateBishopMoves(Tile[][] tiles, Piece piece) {
		ArrayList<Coordinate> validMoves = new ArrayList<Coordinate>();

		int srcRow = piece.getCoordinate().getRow();
		int srcCol = piece.getCoordinate().getCol();
		ChessColor pieceColor = piece.getPieceColor();
		int rowSize = tiles[0].length;
		int colSize = tiles.length;
		Coordinate nextPotentialMove;

		//check all tiles in 4 diagonal directions, add spaces if empty, stop when you get to the edge of the board
		//or hit an occupied space, add the occupied space if the piece occupying it doesnt match this piece's color

		//North West
		for(int r = srcRow - 1, c = srcCol - 1; r >= 0 && c >= 0; r--, c--) {
			System.out.println(r);
			System.out.println(c);
			nextPotentialMove = new Coordinate(r, c);

			if(!(Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
				System.out.println("1");
				break;
			}

			if(tiles[r][c].isEmpty()) {
				System.out.println("2");
				validMoves.add(nextPotentialMove);
			}

			else {
				System.out.println("3");
				if(tiles[r][c].getPiece().getPieceColor() != pieceColor) {
					System.out.println("4");
					validMoves.add(nextPotentialMove);
				}

				break;
			}	
		}

		//North East
		for(int r = srcRow - 1, c = srcCol + 1; r >= 0 && c < colSize; r--, c++) {	
			nextPotentialMove = new Coordinate(r, c);

			if(!(Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
				break;
			}

			if(tiles[r][c].isEmpty()) {
				validMoves.add(nextPotentialMove);
			}

			else {
				if(tiles[r][c].getPiece().getPieceColor() != pieceColor) {
					validMoves.add(nextPotentialMove);
				}

				break;
			}	
		}

		//South West
		for(int r = srcRow + 1, c = srcCol - 1; r < rowSize && c >= 0; r++, c--) {	
			nextPotentialMove = new Coordinate(r, c);

			if(!(Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
				break;
			}

			if(tiles[r][c].isEmpty()) {
				validMoves.add(nextPotentialMove);
			}

			else {
				if(tiles[r][c].getPiece().getPieceColor() != pieceColor) {
					validMoves.add(nextPotentialMove);
				}

				break;
			}	
		}

		//South East
		for(int r = srcRow + 1, c = srcCol + 1; r < rowSize && c < colSize; r++, c++) {		
			nextPotentialMove = new Coordinate(r, c);

			if(!(Coordinate.isValidCoordinate(nextPotentialMove, rowSize, colSize))) {
				break;
			}
			if(tiles[r][c].isEmpty()) {
				validMoves.add(nextPotentialMove);
			}

			else {
				if(tiles[r][c].getPiece().getPieceColor() != pieceColor) {
					validMoves.add(nextPotentialMove);
				}

				break;
			}	
		}

		for(Coordinate c: validMoves) {
			System.out.println("Row: " + c.getRow() + ", Col: " + c.getCol());
		}

		return validMoves.toArray(new Coordinate[validMoves.size()]);
	}

	//moves diagonal in any direction
	@Override
	public Coordinate[] generateMoves(Tile[][] tiles) {
		return generateBishopMoves(tiles, this);
	}
}