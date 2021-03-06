package Board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Game.GameFrame;
import Game.TitleFrame;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;
import Utils.ChessColor;
import Utils.Coordinate;

/**
 * The board class contains a 2d array of tiles that contain the pieces used for play, as well as 
 * carrying out all the game logic
 * @author James Hoxie
 *
 */
public class Board extends JPanel{
	private int xSize, ySize;
	private int turnNumber = 1;
	private Tile[][] tiles;
	private Move currentMove;
	private ChessColor currentPlayerColor;
	private ChessColor playerInCheckMate;
	private ChessColor playerInCheck;
	private King blackKing, whiteKing;
	private ArrayList<Piece> whitePieces = new ArrayList<Piece>();
	private ArrayList<Piece> blackPieces = new ArrayList<Piece>();
	private Piece capturedPiece;
	private TextArea actionText;
	private PieceBox pieceBox;
	private MenuBox menuBox;
	private TitleFrame titleFrame;
	private GameFrame gameFrame;
	private File f;
	private AudioInputStream ais;
	private Clip clip;
	private File f2;
	private AudioInputStream ais2;
	private Clip clip2;

	/**
	 * 
	 * @param xSize number of tiles going horizontally
	 * @param ySize number of tiles going vertically
	 * @param actionText the text box that displays at the bottom of the frame
	 * @param pieceBox the set of panels on the right of the frame that displays pieces that have been captured in play
	 * @param menuBox the set of buttons for saving, loading, resetting, and exiting from the game
	 */
	public Board(int xSize, int ySize, TextArea actionText, PieceBox pieceBox, MenuBox menuBox) {
		setLayout(new GridLayout(8, 8));
		this.xSize = xSize;
		this.ySize = ySize;
		this.tiles = new Tile[xSize][ySize];
		this.currentMove = new Move();
		this.currentPlayerColor = ChessColor.WHITE;
		this.pieceBox = pieceBox;
		this.menuBox = menuBox;
		menuBox.addButtonFunctions(new saveHandler(), new loadHandler(), new resetHandler(), new exitHandler());
		setUpTiles();
		setUpPieces();
		this.actionText = actionText;
		actionText.append("Turn " + turnNumber + "\n");
		actionText.append(currentPlayerColor + " player's turn..." + "\n");
		
		try {
			f = new File("src/resources/tile_click.wav");
			f2 = new File("src/resources/button_click.wav");
			ais = AudioSystem.getAudioInputStream(f);
			ais2 = AudioSystem.getAudioInputStream(f2);
			clip = AudioSystem.getClip();
			clip2 = AudioSystem.getClip();
			clip.open(ais);
			clip2.open(ais2);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * sets up the pieces on the board for a new chess game
	 */
	private void setUpPieces() {
		//Set up white pieces
		whiteKing = new King(new Coordinate(7, 4), ChessColor.WHITE);
		Piece whiteKnight1, whiteKnight2, whiteBishop1, whiteBishop2, whiteRook1, whiteRook2, 
		whiteQueen, whitePawn; 

		whiteKnight1 = new Knight(new Coordinate(7, 1), ChessColor.WHITE);
		whiteKnight2 = new Knight(new Coordinate(7, 6), ChessColor.WHITE);
		whiteBishop1 = new Bishop(new Coordinate(7, 2), ChessColor.WHITE);
		whiteBishop2 = new Bishop(new Coordinate(7, 5), ChessColor.WHITE);
		whiteRook1 = new Rook(new Coordinate(7, 0), ChessColor.WHITE);
		whiteRook2 = new Rook(new Coordinate(7, 7), ChessColor.WHITE);
		whiteQueen = new Queen(new Coordinate(7, 3), ChessColor.WHITE);

		whitePieces.add(whiteKing);
		whitePieces.add(whiteKnight1);
		whitePieces.add(whiteKnight2);
		whitePieces.add(whiteBishop1);
		whitePieces.add(whiteBishop2);
		whitePieces.add(whiteRook1);
		whitePieces.add(whiteRook2);
		whitePieces.add(whiteQueen);

		tiles[7][0].setPiece(whiteRook1);
		tiles[7][1].setPiece(whiteKnight1);
		tiles[7][2].setPiece(whiteBishop1);
		tiles[7][3].setPiece(whiteQueen);
		tiles[7][4].setPiece(whiteKing);
		tiles[7][5].setPiece(whiteBishop2);
		tiles[7][6].setPiece(whiteKnight2);
		tiles[7][7].setPiece(whiteRook2);

		for(int i = 0; i < tiles[7].length; i++) {
			tiles[7][i].displayPiece();
		}


		for(int i = 0; i < tiles[6].length; i++) {
			whitePawn = new Pawn(new Coordinate(6, i), ChessColor.WHITE);
			whitePieces.add(whitePawn);
			tiles[6][i].setPiece(whitePawn);
			tiles[6][i].displayPiece();
		}

		//Set up black pieces
		blackKing = new King(new Coordinate(0, 4), ChessColor.BLACK);
		Piece blackKnight1, blackKnight2, blackBishop1, blackBishop2, blackRook1, blackRook2, 
		blackQueen, blackPawn;

		blackKnight1 = new Knight(new Coordinate(0, 1), ChessColor.BLACK);
		blackKnight2 = new Knight(new Coordinate(0, 6), ChessColor.BLACK);
		blackBishop1 = new Bishop(new Coordinate(0, 2), ChessColor.BLACK);
		blackBishop2 = new Bishop(new Coordinate(0, 5), ChessColor.BLACK);
		blackRook1 = new Rook(new Coordinate(0, 0), ChessColor.BLACK);
		blackRook2 = new Rook(new Coordinate(0, 7), ChessColor.BLACK);
		blackQueen = new Queen(new Coordinate(0, 3), ChessColor.BLACK);

		blackPieces.add(blackKing);
		blackPieces.add(blackKnight1);
		blackPieces.add(blackKnight2);
		blackPieces.add(blackBishop1);
		blackPieces.add(blackBishop2);
		blackPieces.add(blackRook1);
		blackPieces.add(blackRook2);
		blackPieces.add(blackQueen);

		tiles[0][0].setPiece(blackRook1);
		tiles[0][1].setPiece(blackKnight1);
		tiles[0][2].setPiece(blackBishop1);
		tiles[0][3].setPiece(blackQueen);
		tiles[0][4].setPiece(blackKing);
		tiles[0][5].setPiece(blackBishop2);
		tiles[0][6].setPiece(blackKnight2);
		tiles[0][7].setPiece(blackRook2);

		for(int i = 0; i < tiles[0].length; i++) {
			tiles[0][i].displayPiece();
		}

		for(int i = 0; i < tiles[1].length; i++) {
			blackPawn = new Pawn(new Coordinate(1, i), ChessColor.BLACK);
			blackPieces.add(blackPawn);
			tiles[1][i].setPiece(blackPawn);
			tiles[1][i].displayPiece();
		}
	}

	/**
	 * fills the tiles array and fills the board with those Tile objects to act as spaces, alternate between black and white tiles  
	 */
	private void setUpTiles(){
		boolean setBlack;
		for(int i = 0; i < xSize; i++) {
			//even rows start with white, odd rows start with black
			setBlack = (i % 2 == 0) ? false : true;

			for(int j = 0; j < ySize; j++) {
				if(setBlack) {
					tiles[i][j] = new Tile(new Coordinate(i, j), ChessColor.BLACK);
					setBlack = false;
				}

				else {
					tiles[i][j] = new Tile(new Coordinate(i, j), ChessColor.WHITE);
					setBlack = true;
				}

				tiles[i][j].addActionListener(new TileHandler());
				add(tiles[i][j]);
				tiles[i][j].displayPiece();
			}

			setBlack = true;
		}
	}

	/**
	 * sets the check status for the king matching kingColor
	 * @param kingColor the color of the king being set
	 * @param checkStatus true if this king is in check, false otherwise
	 */
	private void setKingCheckStatus(ChessColor kingColor, boolean checkStatus) {
		if(kingColor == ChessColor.BLACK) {
			if(checkStatus == true) {
				blackKing.setKingInCheck();
			}

			else {
				blackKing.setKingOutOfCheck();
			}
		}

		else {
			if(checkStatus == true) {
				whiteKing.setKingInCheck();
			}

			else {
				whiteKing.setKingOutOfCheck();
			}
		}
	}

	/**
	 * This function is used to promote a pawn when it reaches the other side of the board
	 * @param pawnToBePromoted The pawn to be promoted into a piece of promotionType
	 * @param promotionType The type of chess piece the pawn will be promoted to (Rook, Bishop, Knight, or Queen)
	 */
	private void promotePawn(Piece pawnToBePromoted, int promotionType) {
		ArrayList<Piece> pieces;
		Piece promotedPawn = null;

		if(currentPlayerColor == ChessColor.BLACK) {
			pieces = blackPieces;
		}

		else {
			pieces = whitePieces;
		}

		pieces.remove(pawnToBePromoted);

		switch(promotionType) {
		case 0:
			promotedPawn = new Queen(pawnToBePromoted.getPieceCoordinate(), currentPlayerColor);
			break;
		case 1:
			promotedPawn = new Rook(pawnToBePromoted.getPieceCoordinate(), currentPlayerColor);
			break;
		case 2:
			promotedPawn = new Bishop(pawnToBePromoted.getPieceCoordinate(), currentPlayerColor);
			break;
		case 3:
			promotedPawn = new Knight(pawnToBePromoted.getPieceCoordinate(), currentPlayerColor);
			break;
		}

		pieces.add(promotedPawn);
		getTile(promotedPawn.getPieceCoordinate()).setPiece(promotedPawn);
		getTile(promotedPawn.getPieceCoordinate()).displayPiece();
	}

	/**
	 * This function is used to check if any pawn on the board can be promoted
	 */
	private void checkForPawnPromotion() {
		ArrayList<Piece> pieces;
		Pawn pawnToPromote = null;
		int selection = -1;

		if(currentPlayerColor == ChessColor.WHITE) {
			pieces = whitePieces;
		}

		else {
			pieces = blackPieces;
		}

		for(Piece p: pieces) {
			if(p instanceof Pawn && (p.getPieceCoordinate().getRow() == 0 || p.getPieceCoordinate().getRow() == 7)) {
				String[] options = {"Queen", "Rook", "Bishop", "Knight"};
				selection = JOptionPane.showOptionDialog(getParent(), "Select piece to promote to...", "Pawn Promotion", 0, JOptionPane.PLAIN_MESSAGE, null, options, 0);
				pawnToPromote = (Pawn) p;
				break;
			}
		}

		if(selection != -1) {
			promotePawn(pawnToPromote, selection);
		}
	}

	/**
	 * 
	 * @return the color of the player in checkmate on this board, or null if neither player is in checkmate
	 */
	public ChessColor getPlayerInCheckMate() {
		return playerInCheckMate;
	}

	/**
	 * Checks if the player matching playerColor is currently in check, updates playerInCheck member variable
	 * of board accordingly, null if player is not in check
	 * @param playerColor color of the player being checked for check status
	 */
	private void checkForCheck(ChessColor playerColor) {
		ArrayList<Coordinate> threatenedCoordinates = new ArrayList<Coordinate>();
		Coordinate[] moves;
		ArrayList<Piece> opponentPieces;
		King kingBeingChecked;

		if(playerColor == ChessColor.WHITE) {
			opponentPieces = blackPieces;
			kingBeingChecked = whiteKing;
		}

		else {
			opponentPieces = whitePieces;
			kingBeingChecked = blackKing;
		}

		//generate all coordinates opponent can threaten
		for(Piece piece : opponentPieces) {
			if(piece instanceof Pawn) {
				moves = ((Pawn) piece).generateAttackSpaces(tiles);
			}

			else {
				moves = piece.generateMoves(tiles, turnNumber);
			}

			for(Coordinate c: moves) {
				//if this coordinate has not already been added to threatenedCoordinate, add it
				if(!threatenedCoordinates.contains(c)) {
					threatenedCoordinates.add(c);
				}
			}
		}

		if(threatenedCoordinates.contains(kingBeingChecked.getPieceCoordinate())) {
			playerInCheck = playerColor;
			//System.out.println(currentPlayerColor + "player is in check");
		}

		else {
			playerInCheck = null;
			//System.out.println(currentPlayerColor + " player is not in check");
		}

	}

	/**
	 * Checks if the player matching playerColor is currently in checkmate, updates playerInCheckMate member variable
	 * of board accordingly, null if player is not in checkmate, necessary assumption for this function being called:
	 * player matching playerColor is already in check
	 * @param playerColor color of the player being checked for checkmate status
	 */
	private void checkForCheckMate(ChessColor playerColor) {
		Coordinate[] moves;
		Move nextPossibleMove = new Move();
		ArrayList<Piece> friendlyPieces = new ArrayList<Piece>();
		Tile sourceTile, destinationTile;
		Piece[] originalPlacements;

		if(playerColor == ChessColor.WHITE) {
			for(Piece piece : whitePieces) {			
				if(piece instanceof Bishop) {
					friendlyPieces.add(((Bishop) piece).copy());
				}
				
				else if(piece instanceof King) {
					friendlyPieces.add(((King) piece).copy());
				}
				
				else if(piece instanceof Knight) {
					friendlyPieces.add(((Knight) piece).copy());
				}
				
				else if(piece instanceof Pawn) {
					friendlyPieces.add(((Pawn) piece).copy());
				}
				
				else if(piece instanceof Queen) {
					friendlyPieces.add(((Queen) piece).copy());
				}
				
				else if(piece instanceof Rook) {
					friendlyPieces.add(((Rook) piece).copy());
				}
			}
		}

		else {
			for(Piece piece : blackPieces) {
				if(piece instanceof Bishop) {
					friendlyPieces.add(((Bishop) piece).copy());
				}
				
				else if(piece instanceof King) {
					friendlyPieces.add(((King) piece).copy());
				}
				
				else if(piece instanceof Knight) {
					friendlyPieces.add(((Knight) piece).copy());
				}
				
				else if(piece instanceof Pawn) {
					friendlyPieces.add(((Pawn) piece).copy());
				}
				
				else if(piece instanceof Queen) {
					friendlyPieces.add(((Queen) piece).copy());
				}
				
				else if(piece instanceof Rook) {
					friendlyPieces.add(((Rook) piece).copy());
				}
			}
		}

		for(Piece piece : friendlyPieces) {
			moves = piece.generateMoves(tiles, turnNumber);

			for(Coordinate c : moves) {
				sourceTile = getTile(piece.getPieceCoordinate());
				destinationTile = getTile(c);
				nextPossibleMove.setSource(sourceTile);
				nextPossibleMove.setDestination(destinationTile);

				if(isValidMove(nextPossibleMove) && !(isCastlingMove(nextPossibleMove))) {
					originalPlacements = executeMove(nextPossibleMove);
					checkForCheck(playerColor);

					if(playerInCheck != playerColor) {
						playerInCheckMate = null;
						//this line resets playerInCheck marker that gets altered in process of checking for checkmate
						playerInCheck = playerColor;
						undoMove(originalPlacements, nextPossibleMove);
						nextPossibleMove.clearMove();
						return;
					}

					undoMove(originalPlacements, nextPossibleMove);
					nextPossibleMove.clearMove();
				}
			}
		}

		playerInCheckMate = playerColor;
	}

	/**
	 * switches color of current player taking their turn
	 */
	private void switchCurrentPlayerColor() {
		if(this.currentPlayerColor == ChessColor.BLACK) {
			this.currentPlayerColor = ChessColor.WHITE;
		}

		else {
			this.currentPlayerColor = ChessColor.BLACK;
		}
	}

	/**
	 * 
	 * @return the color of the player currently taking their turn
	 */
	public ChessColor getCurrentPlayerColor() {
		return this.currentPlayerColor;
	}

	/**
	 * 
	 * @param coordinate the coordinate for the tile being retrieved
	 * @return the tile of this board at given coordinate, coordinate must be valid
	 */
	private Tile getTile(Coordinate coordinate) {
		return tiles[coordinate.getRow()][coordinate.getCol()];
	}

	/**
	 * executes a castling move in this chess game
	 * @param move the castling move to be executed
	 * @return the original placements of the pieces being moved in this castling move as an array
	 */
	private Piece[] executeCastlingMove(Move move) {
		King king = (King) move.getSourceTile().getPiece();
		Rook rook = (Rook) move.getDestinationTile().getPiece();
		int row = king.getPieceCoordinate().getRow();
		int kingCol = king.getPieceCoordinate().getCol();
		int kingCol2, kingCol3;
		int rookCol = rook.getPieceCoordinate().getCol();
		Piece[] originalPlacements, originalPlacements2, originalPlacements3 = {king, rook};
		Move intermediateMove = new Move(), intermediateMove2 = new Move();

		//queen side castle
		if(rookCol < kingCol) {
			kingCol2 = kingCol - 1;
			kingCol3 = kingCol - 2;		
		}

		//king side castle
		else {
			kingCol2 = kingCol + 1;
			kingCol3 = kingCol + 2;
		}

		intermediateMove.setSource(move.getSourceTile());
		intermediateMove.setDestination(tiles[row][kingCol2]);
		originalPlacements = executeStandardMove(intermediateMove);

		checkForCheck(currentPlayerColor);
		//if this move put this player in check, undo and stop castling
		if(playerInCheck == currentPlayerColor) {
			undoMove(originalPlacements, intermediateMove);
			return null;
		}

		intermediateMove2.setSource(intermediateMove.getDestinationTile());
		intermediateMove2.setDestination(tiles[row][kingCol3]);
		originalPlacements2 = executeStandardMove(intermediateMove2);

		checkForCheck(currentPlayerColor);
		//if this move put this player in check, undo and stop castling
		if(playerInCheck == currentPlayerColor) {
			undoMove(originalPlacements2, intermediateMove2);
			undoMove(originalPlacements, intermediateMove);
			return null;
		}		

		king.setPieceCoordinate(tiles[row][kingCol3].getCoordinate());
		move.getSourceTile().setPiece(null);
		rook.setPieceCoordinate(tiles[row][kingCol2].getCoordinate());
		move.getDestinationTile().setPiece(null);

		getTile(rook.getPieceCoordinate()).setPiece(rook);

		//set movement status for king and rook
		rook.setRookHasMoved();
		king.setKingHasMoved();

		return originalPlacements3;
	}

	/**
	 * this function undoes a castling move
	 * @param originalPlacements the original placements of the pieces before the castling move
	 * @param move the castling move that is being undone
	 */
	private void undoCastlingMove(Piece[] originalPlacements, Move move) {
		King king = (King) originalPlacements[0];
		Rook rook = (Rook) originalPlacements[1];
		rook.setRookHasNotMoved();
		king.setKingHasNotMoved();

		getTile(king.getPieceCoordinate()).setPiece(null);
		getTile(rook.getPieceCoordinate()).setPiece(null);

		king.setPieceCoordinate(move.getSourceTile().getCoordinate());
		rook.setPieceCoordinate(move.getDestinationTile().getCoordinate());	
	}

	/**
	 * Checks if the current move is a castling move
	 * @param move the current move
	 * @return true if the move is a castling move, false otherwise
	 */
	private boolean isCastlingMove(Move move) {
		King king;
		Rook rook;

		if(move.getSourceTile().getPiece() instanceof King && 
				move.getDestinationTile().getPiece() instanceof Rook) {

			king = (King) move.getSourceTile().getPiece();
			rook = (Rook) move.getDestinationTile().getPiece();

			return (king.getPieceColor() == currentPlayerColor) &&
					(rook.getPieceColor() == currentPlayerColor) &&
					(!(king.hasMoved()) && !(rook.hasMoved()));
		}

		return false;		
	}

	/**
	 * executes an enpassant move in this chess game
	 * @param move the enpassant move to be executed
	 * @param enemyPawn the pawn to be captured via enpassant
	 * @return the original placements of the pieces involved in this enpassant move
	 */
	private Piece[] executeEnPassantMove(Move move, Pawn enemyPawn) {
		Piece[] originalPlacements = executeStandardMove(move);
		originalPlacements[1] = enemyPawn;

		getTile(enemyPawn.getPieceCoordinate()).setPiece(null);

		if(enemyPawn.getPieceColor() == ChessColor.BLACK) {
			blackPieces.remove(enemyPawn);
		}

		else {
			whitePieces.remove(enemyPawn);
		}

		capturedPiece = enemyPawn;

		return originalPlacements;
	}

	/**
	 * undoes an enpassant move in this chess game
	 * @param originalPlacements the original placements of the pieces involved in this enpassant move
	 * @param move the enpassant move being undone
	 */
	private void undoEnPassantMove(Piece[] originalPlacements, Move move) {
		Tile sourceTile = move.getSourceTile();
		Tile destinationTile = move.getDestinationTile();
		Piece sourcePiece = originalPlacements[0];
		Piece enemyPawn = originalPlacements[1];

		sourcePiece.setPieceCoordinate(sourceTile.getCoordinate());
		sourceTile.setPiece(sourcePiece);
		destinationTile.setPiece(null);
		getTile(enemyPawn.getPieceCoordinate()).setPiece(enemyPawn);

		if(enemyPawn != null) {
			if(enemyPawn.getPieceColor() == ChessColor.BLACK) {
				blackPieces.add(enemyPawn);
			}

			else {
				whitePieces.add(enemyPawn);
			}
		}
	}

	/**
	 * determines if this move is an enpassant move
	 * @param move the move being checked 
	 * @return the pawn to be captured enpassant if this move is an enpassant move or null otherwise
	 */
	private Pawn isEnPassantMove(Move move) {
		Tile sourceTile = move.getSourceTile();
		Tile destTile = move.getDestinationTile();
		Piece piece = sourceTile.getPiece();
		int enemyCol1 = -1, enemyCol2 = -1;
		Coordinate enemyCoordinate1 = null, enemyCoordinate2 = null;
		Pawn movingPawn, enemyPawn;
		
		if(!(destTile.isEmpty()) || piece == null || !(piece instanceof Pawn)) {
			return null;
		}
		
		movingPawn = (Pawn) piece;

		enemyCol1 = piece.getPieceCoordinate().getCol() - 1;
		enemyCol2 = piece.getPieceCoordinate().getCol() + 1;
		enemyCoordinate1 = new Coordinate(piece.getPieceCoordinate().getRow(), enemyCol1);
		enemyCoordinate2 = new Coordinate(piece.getPieceCoordinate().getRow(), enemyCol2);

		Piece enemyPiece1 = null;
		Piece enemyPiece2 = null;

		if(Coordinate.isValidCoordinate(enemyCoordinate1, xSize, ySize)) {
			enemyPiece1 = getTile(enemyCoordinate1).getPiece();
		}

		if(Coordinate.isValidCoordinate(enemyCoordinate2, xSize, ySize)) {
			enemyPiece2 = getTile(enemyCoordinate2).getPiece();
		}
		
		if(enemyPiece1 instanceof Pawn) {
			enemyPawn = (Pawn) enemyPiece1;
			
			if(enemyPawn.canBeCapturedEnPassant(turnNumber)) {
				if(movingPawn.getPieceColor() == ChessColor.BLACK &&
						enemyPawn.getPieceColor() == ChessColor.WHITE) {
					
					if(destTile.getCoordinate().equals(new Coordinate(
							enemyPawn.getPieceCoordinate().getRow() + 1, enemyPawn.getPieceCoordinate().getCol()))) {
						return enemyPawn;
					}
						
				}
				
				else if(movingPawn.getPieceColor() == ChessColor.WHITE && 
					    	enemyPawn.getPieceColor() == ChessColor.BLACK){
					
					if(destTile.getCoordinate().equals(new Coordinate(
							enemyPawn.getPieceCoordinate().getRow() - 1, enemyPawn.getPieceCoordinate().getCol()))) {
						return enemyPawn;
					}
					
				}
			}
		}
		
		if(enemyPiece2 instanceof Pawn) {
			enemyPawn = (Pawn) enemyPiece2;
			
			if(enemyPawn.canBeCapturedEnPassant(turnNumber)) {
				if(movingPawn.getPieceColor() == ChessColor.BLACK &&
						enemyPawn.getPieceColor() == ChessColor.WHITE) {
					
					if(destTile.getCoordinate().equals(new Coordinate(
							enemyPawn.getPieceCoordinate().getRow() + 1, enemyPawn.getPieceCoordinate().getCol()))) {
						return enemyPawn;
					}
						
				}
				
				else if(movingPawn.getPieceColor() == ChessColor.WHITE && 
					    	enemyPawn.getPieceColor() == ChessColor.BLACK){
					
					if(destTile.getCoordinate().equals(new Coordinate(
							enemyPawn.getPieceCoordinate().getRow() - 1, enemyPawn.getPieceCoordinate().getCol()))) {
						return enemyPawn;
					}
					
				}
			}
		}

		return null;
	}

	/**
	 * checks if the current move being performed is a valid chess move
	 * @param move the move being evaluated
	 * @return true if the move is valid, false otherwise
	 */
	private boolean isValidMove(Move move) {
		Tile sourceTile = move.getSourceTile();
		Tile destinationTile = move.getDestinationTile();
		Piece piece = sourceTile.getPiece();
		Coordinate[] validMoves = piece.generateMoves(tiles, turnNumber);

		//check all generated moves, if coordinates of any match coordinates of destination tile then
		//move is valid
		for(Coordinate c: validMoves) {
			if(sourceTile == null || destinationTile == null) {
				return false;
			}
			
			if(c.equals(destinationTile.getCoordinate())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * undoes any move that is not an enpassant or castling move
	 * @param originalPlacements the original placements of the pieces involved in this move
	 * @param move the move being undone
	 */
	private void undoStandardMove(Piece[] originalPlacements, Move move) {
		Tile sourceTile = move.getSourceTile();
		Tile destinationTile = move.getDestinationTile();
		Piece sourcePiece = originalPlacements[0];
		Piece destPiece = originalPlacements[1];

		sourcePiece.setPieceCoordinate(sourceTile.getCoordinate());
		sourceTile.setPiece(sourcePiece);
		destinationTile.setPiece(destPiece);

		if(destPiece != null) {
			if(destPiece.getPieceColor() == ChessColor.BLACK) {
				blackPieces.add(destPiece);
			}

			else {
				whitePieces.add(destPiece);
			}
		}
	}

	/**
	 * used to undo the last move taken in the event of a move putting a player into check or keeping them in check
	 * @param originalPlacements the original placements of the pieces involded in the move being undone
	 * @param move the move being undone
	 */
	private void undoMove(Piece[] originalPlacements, Move move) {
		if(isEnPassantMove(move) != null) {
			undoEnPassantMove(originalPlacements, move);
		}

		else if(isCastlingMove(move)) {
			undoCastlingMove(originalPlacements, move);
		}

		else {
			undoStandardMove(originalPlacements, move);
		}
		
		capturedPiece = null;
	}

	/**
	 * executes any move that is not an enpassant or castling move
	 * @param move the move to be executed
	 * @return the original placements of the pieces involved in the move
	 */
	private Piece[] executeStandardMove(Move move) {
		Tile sourceTile = move.getSourceTile();
		Tile destinationTile = move.getDestinationTile();
		Piece sourcePiece = sourceTile.getPiece();
		Piece destPiece = destinationTile.getPiece();
		Piece[] orignalPlacements = {sourcePiece, destPiece};

		sourcePiece.setPieceCoordinate(destinationTile.getCoordinate());
		sourceTile.setPiece(null);
		destinationTile.setPiece(sourcePiece);

		if(destPiece != null) {
			if(destPiece.getPieceColor() == ChessColor.BLACK) {
				blackPieces.remove(destPiece);
			}

			else {
				whitePieces.remove(destPiece);
			}

			capturedPiece = destPiece;
		}

		return orignalPlacements;
	}

	/**
	 * determines the type of move to execute (enpassant, castling, or a standard move) 
	 * and moves the pieces involved in the move, but does not display updated icons for this move
	 * @param move the move being executed
	 * @return the original placements of the pieces involved in this move
	 */
	private Piece[] executeMove(Move move) {
		Pawn enemyPawn = isEnPassantMove(move);

		if(enemyPawn != null) {
			return executeEnPassantMove(move, enemyPawn);
		}

		else if(isCastlingMove(move)) {
			return executeCastlingMove(move);
		}

		//move is not a special move
		else {
			return executeStandardMove(move);
		}
	}

	/**
	 * sets the icons on the board to display changes due to the current move, only performed once a move has been finalized (executed and not in need of being undone)
	 */
	private void displayMove() {
		for(int r = 0; r < tiles[0].length; r++) {
			for(int c = 0; c < tiles.length; c++) {
				tiles[r][c].displayPiece();
			}
		}

		if(capturedPiece != null) {
			pieceBox.capturePiece(capturedPiece);
			capturedPiece = null;
		}
	}

	/**
	 * tries to perform the current move. checks for check, checkmate, and stalemate conditions after successful execution and display of current move
	 */
	private void tryMove() {
		if(isValidMove(currentMove)) {
			Piece piece = currentMove.getSourceTile().getPiece();
			int row = piece.getPieceCoordinate().getRow();
			Piece[] originalPlacements;
			originalPlacements = executeMove(currentMove);

			//placements will be null if castling attempt failed, try another move
			if(originalPlacements == null) {
				currentMove.clearMove();
				return;
			}

			checkForCheck(currentPlayerColor);

			//if this move put this player in check, undo and try another move
			if(playerInCheck == currentPlayerColor) {
				undoMove(originalPlacements, currentMove);
			}

			//player was not put in check by move, display the move
			else {
				displayMove();

				//set enpassant status if this piece is a pawn
				if(piece instanceof Pawn) {
					((Pawn) piece).setEnPassant(row, turnNumber);
				}

				else if(piece instanceof Rook) {
					((Rook) piece).setRookHasMoved();
				}

				else if(piece instanceof King) {
					((King) piece).setKingHasMoved();
				}

				//check if a pawn can be promoted
				checkForPawnPromotion();

				switchCurrentPlayerColor();
				//check if other player was put into check from last players move
				checkForCheck(currentPlayerColor);

				if(playerInCheck != null) {
					actionText.append(playerInCheck + " player is in check\n");
					setKingCheckStatus(playerInCheck, true);
					
					if(playerInCheck == currentPlayerColor) {
						//check if other player was put into checkmate from last players move
						checkForCheckMate(currentPlayerColor);

						if(playerInCheckMate == currentPlayerColor) {
							actionText.append("GAME SET! \n" + playerInCheckMate + " player is in checkmate!");
							JOptionPane.showMessageDialog(getParent(), "GAME SET! \n" + playerInCheckMate + " player is in checkmate!");
							
							//JOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue)
							int choice = JOptionPane.showOptionDialog(getParent(), "Play again?", "Replay", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						
							if(choice == 0) {
								clearPieces();
								clearBoard();
								setUpPieces();
								turnNumber--;
							}
							
							else {
								gameFrame.dispose();
								titleFrame.setVisible(true);
							}
						}

					}
				}

				else {
					setKingCheckStatus(playerInCheck, false);
					
					//check if other player was put into stalemate from last players move
					if(checkForStaleMate(currentPlayerColor)) {
						//match ended in stalemate
						actionText.append("GAME SET! THIS GAME HAS ENDED IN STALEMATE!");
						JOptionPane.showMessageDialog(getParent(), "GAME SET! THIS GAME HAS ENDED IN STALEMATE!");
						
						//JOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue)
						int choice = JOptionPane.showOptionDialog(getParent(), "Play again?", "Replay", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
					
						if(choice == 0) {
							clearPieces();
							clearBoard();
							setUpPieces();
							turnNumber--;
						}
						
						else {
							gameFrame.dispose();
							titleFrame.setVisible(true);
						}
					}
				}

				turnNumber++;
				actionText.append("Turn " + turnNumber + "\n");
				actionText.append(currentPlayerColor + " player's turn..." + "\n");
			}

		}

		currentMove.clearMove();
	}
	
	private boolean checkForStaleMate(ChessColor playerColor) {	
		Coordinate[] moves;
		Move nextPossibleMove = new Move();
		ArrayList<Piece> friendlyPieces = new ArrayList<Piece>();
		Tile sourceTile, destinationTile;
		Piece[] originalPlacements;

		if(playerColor == ChessColor.WHITE) {
			for(Piece piece : whitePieces) {			
				if(piece instanceof Bishop) {
					friendlyPieces.add(((Bishop) piece).copy());
				}
				
				else if(piece instanceof King) {
					friendlyPieces.add(((King) piece).copy());
				}
				
				else if(piece instanceof Knight) {
					friendlyPieces.add(((Knight) piece).copy());
				}
				
				else if(piece instanceof Pawn) {
					friendlyPieces.add(((Pawn) piece).copy());
				}
				
				else if(piece instanceof Queen) {
					friendlyPieces.add(((Queen) piece).copy());
				}
				
				else if(piece instanceof Rook) {
					friendlyPieces.add(((Rook) piece).copy());
				}
			}
		}

		else {
			for(Piece piece : blackPieces) {
				if(piece instanceof Bishop) {
					friendlyPieces.add(((Bishop) piece).copy());
				}
				
				else if(piece instanceof King) {
					friendlyPieces.add(((King) piece).copy());
				}
				
				else if(piece instanceof Knight) {
					friendlyPieces.add(((Knight) piece).copy());
				}
				
				else if(piece instanceof Pawn) {
					friendlyPieces.add(((Pawn) piece).copy());
				}
				
				else if(piece instanceof Queen) {
					friendlyPieces.add(((Queen) piece).copy());
				}
				
				else if(piece instanceof Rook) {
					friendlyPieces.add(((Rook) piece).copy());
				}
			}
		}

		for(Piece piece : friendlyPieces) {

			moves = piece.generateMoves(tiles, turnNumber);

			for(Coordinate c : moves) {
				sourceTile = getTile(piece.getPieceCoordinate());
				destinationTile = getTile(c);
				nextPossibleMove.setSource(sourceTile);
				nextPossibleMove.setDestination(destinationTile);

				if(isValidMove(nextPossibleMove) && !(isCastlingMove(nextPossibleMove))) {
					originalPlacements = executeMove(nextPossibleMove);
					checkForCheck(playerColor);

					if(playerInCheck != playerColor) {
						//this line resets playerInCheck marker that gets altered in process of checking for stalemate
						playerInCheck = playerColor;
						undoMove(originalPlacements, nextPossibleMove);
						nextPossibleMove.clearMove();
						return false;
					}

					undoMove(originalPlacements, nextPossibleMove);
					nextPossibleMove.clearMove();
				}
			}
		}
		
		return true;
	}

	private void clearPieces() {
		whitePieces.clear();
		blackPieces.clear();
		whiteKing = null;
		blackKing = null;
		turnNumber = 1;
		currentMove.clearMove();
		currentPlayerColor = ChessColor.WHITE;
		playerInCheckMate = null;
		playerInCheck = null;
		capturedPiece = null;
		actionText.append("Game Reset...\n");
		actionText.append("Turn 1\nWHITE player's turn...\n");
		pieceBox.clear();
	}
	
	private void clearBoard() {
		for(int r = 0; r < tiles.length; r++) {
			for(int c = 0; c < tiles[r].length; c++) {
				tiles[r][c].setPiece(null);
				tiles[r][c].displayPiece();
			}
		}
	}
	
	private void playClip() {
		try {
			clip.start();
			clip.setFramePosition(0);
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	private void playClip2() {
		try {
			clip2.start();
			clip2.setFramePosition(0);
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public void setExitScreen(TitleFrame titleFrame, GameFrame gameFrame) {
		this.titleFrame = titleFrame;
		this.gameFrame = gameFrame;
	}

	/**
	 * handler for tile clicks, creates moves to be executed
	 * @author James Hoxie
	 *
	 */
	private class TileHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			playClip();
			
			Tile clickedTile = (Tile) e.getSource();
			Piece clickedPiece = clickedTile.getPiece();		

			if(!(currentMove.sourceSelected())) {
				//if clicked tile has a piece and 
				//if clicked piece matches color of player taking their turn
				if(clickedPiece != null && clickedPiece.getPieceColor() == currentPlayerColor) {
					currentMove.setSource(clickedTile);
				}
			}

			else if(!(currentMove.destinationSelected())) {
				currentMove.setDestination(clickedTile);
				tryMove();
			}
		}

	}

	/**
	 * handler for save game functionality, writes game state to a txt file stored in user's documents 
	 * @author James Hoxie
	 *
	 */
	private class saveHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {	
			playClip2();
			
			String path = System.getProperty("user.home") + File.separator + "Documents";
			path += File.separator + "Java_Chess_Save";
			File saveLocation = new File(path);
			File saveFile = new File(path + "/SaveFile.txt");
			boolean newSave = true;

			if (!(saveLocation.exists())) {
				if(saveLocation.mkdirs()) {
					System.out.println(saveLocation + " was created");
				}

				else {
					System.out.println(saveLocation + " was not created");
				}
			}

			
			
			try {
				saveLocation.setWritable(true);
				newSave = saveFile.createNewFile();
				//if save file already exists, prompt user before overwrite
				if(!newSave) {
					//JOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue)
					int choice = JOptionPane.showOptionDialog(getParent(), "Are you sure you want to save this game?\nPrevious saved file will be overwritten", "Save Game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
					
					if(choice != 0) {
						return;
					}
				}			
				
				FileWriter fw = new FileWriter(saveFile);

				fw.write(Integer.toString(turnNumber) + "\n");

				if(playerInCheck != null) {
					fw.write(playerInCheck+"\n");
				}

				else {
					fw.write("null\n");
				}
				
				fw.write(currentPlayerColor+"\n");

				for(int r = 0; r < tiles.length; r++) {
					for(int c = 0; c < tiles[0].length; c++) {
						if(tiles[r][c].isEmpty()) {
							fw.write("null\n");
						}

						else {
							fw.write(tiles[r][c].getPiece().toString() + "\n");
						}

					}
				}
				
				fw.write("Captured-Black-Pieces\n");
				
				//Save piecebox
				for(String pieceType : pieceBox.getCapturedBlackPiecesTypes()) {
					fw.write(pieceType + "\n");
				}
				
				fw.write("Captured-White-Pieces\n");
				
				for(String pieceType : pieceBox.getCapturedWhitePiecesTypes()) {
					fw.write(pieceType + "\n");
				}
				
				fw.close();
				
				if(newSave) {
					actionText.append("new save file created\n");
				}
				
				actionText.append("game saved!\n");
			} catch (IOException e) {
				//e.printStackTrace();
				JOptionPane.showMessageDialog(getParent(), "Error saving file");
			}

		}

	}

	/**
	 * handler for load game functionality, reads from txt file stored in user's documents to set game state
	 * @author James Hoxie
	 *
	 */
	private class loadHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			playClip2();
			
			String path = System.getProperty("user.home") + File.separator + "Documents";
			path += File.separator + "Java_Chess_Save";
			File saveLocation = new File(path);
			File saveFile = new File(path + "/SaveFile.txt");
			String[] pieceInfo;
			String pieceType;
			ChessColor pieceColor;
			Piece nextPiece;
			int turnNum = -1;
			
			if(!(saveLocation.exists())) {
				JOptionPane.showMessageDialog(getParent(), "No save location detected");
			}
			
			else if(!(saveFile.exists())) {
				JOptionPane.showMessageDialog(getParent(), "No save file detected");
			}
			
			else {
				
				//JOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue)
				int choice = JOptionPane.showOptionDialog(getParent(), "Are you sure you want to load a saved game?\nAll unsaved changes will be lost.", "Load Game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
				
				if(choice != 0) {
					return;
				}
				
				clearPieces();
				clearBoard();
				
				try {
					Scanner sc = new Scanner(saveFile);
					int lineNum = 1, row = 0, col = 0;
					String nextLine;
					
					while(sc.hasNext()) {
						nextLine = sc.nextLine();
						
						if(lineNum == 1) {
							turnNumber = Integer.parseInt(nextLine);
							turnNum = turnNumber;
						}
						
						else if(lineNum == 2) {
							if(nextLine.equals("BLACK")) {
								playerInCheck = ChessColor.BLACK;
							}
							
							else if(nextLine.equals("WHITE")) {
								playerInCheck = ChessColor.WHITE;
							}
							
							else {
								playerInCheck = null;
							}
						}
						
						else if(lineNum == 3) {
							if(nextLine.equals("BLACK")) {
								currentPlayerColor = ChessColor.BLACK;
							}
							
							else {
								currentPlayerColor = ChessColor.WHITE;
							}
						}
						
						else if(nextLine.equals("null")) {
							nextPiece = null;
						}
						
						else if(nextLine.equals("Captured-Black-Pieces")) {
							
							while(sc.hasNext()) {
								nextLine = sc.nextLine();
								
								if(nextLine.equals("Pawn")) {
									pieceBox.capturePiece(new Pawn(null, ChessColor.BLACK));
								}
								
								else if(nextLine.equals("Knight")) {
									pieceBox.capturePiece(new Knight(null, ChessColor.BLACK));
								}
								
								else if(nextLine.equals("Bishop")) {
									pieceBox.capturePiece(new Bishop(null, ChessColor.BLACK));
								}
								
								else if(nextLine.equals("Rook")) {
									pieceBox.capturePiece(new Rook(null, ChessColor.BLACK));
								}
								
								else if(nextLine.equals("Queen")) {
									pieceBox.capturePiece(new Queen(null, ChessColor.BLACK));
								}
								
								//nextLine.equals("Captured-White-Pieces")
								else {
									lineNum++;
									break;
								}
								
								lineNum++;
							}
							
							while(sc.hasNext()) {
								nextLine = sc.nextLine();
								
								if(nextLine.equals("Pawn")) {
									pieceBox.capturePiece(new Pawn(null, ChessColor.WHITE));
								}
								
								else if(nextLine.equals("Knight")) {
									pieceBox.capturePiece(new Knight(null, ChessColor.WHITE));
								}
								
								else if(nextLine.equals("Bishop")) {
									pieceBox.capturePiece(new Bishop(null, ChessColor.WHITE));
								}
								
								else if(nextLine.equals("Rook")) {
									pieceBox.capturePiece(new Rook(null, ChessColor.WHITE));
								}
								
								else if(nextLine.equals("Queen")) {
									pieceBox.capturePiece(new Queen(null, ChessColor.WHITE));
								}
								
								//nextLine.equals("Captured-White-Pieces")
								else {
									break;
								}
								
								lineNum++;
							}
												
						}
						
						else {			
							pieceInfo = nextLine.split(",");
							pieceType = pieceInfo[0];
							
							if(pieceInfo[1].equals("WHITE")) {
								pieceColor = ChessColor.WHITE;
							}
							
							else {
								pieceColor = ChessColor.BLACK;
							}
							
							switch(pieceType) {
								case "Pawn":
									nextPiece = new Pawn(new Coordinate(row, col), pieceColor);
									int lastRow = Integer.parseInt(pieceInfo[4]);
								
									((Pawn) nextPiece).setLastRow(lastRow);
									((Pawn) nextPiece).setTurnUsedDoubleMove(Integer.parseInt(pieceInfo[3]));
									((Pawn) nextPiece).setEnPassant(lastRow, turnNum);								
									break;
								case "Knight":
									nextPiece = new Knight(new Coordinate(row, col), pieceColor);
									break;
								case "Bishop":
									nextPiece = new Bishop(new Coordinate(row, col), pieceColor);
									break;
								case "Rook":
									nextPiece = new Rook(new Coordinate(row, col), pieceColor);
									
									if(pieceInfo[2].equals("true")) {
										((Rook) nextPiece).setRookHasMoved();
									}
					
									break;
								case "Queen":
									nextPiece = new Queen(new Coordinate(row, col), pieceColor);
									break;
								case "King":
									nextPiece = new King(new Coordinate(row, col), pieceColor);
									
									if(pieceInfo[2].equals("true")) {
										((King) nextPiece).setKingHasMoved();
									}
									
									if(pieceInfo[3].equals("true")) {
										((King) nextPiece).setKingInCheck();
									}
									
									break;	
								default:
									nextPiece = null;
							}
							
							
							if(nextPiece != null) {
								if(nextPiece.getPieceColor() == ChessColor.BLACK) {
									blackPieces.add(nextPiece);
									
									if(nextPiece instanceof King) {
										blackKing = (King) nextPiece;
									}
								}
								
								else {
									whitePieces.add(nextPiece);
									
									if(nextPiece instanceof King) {
										whiteKing = (King) nextPiece;
									}
								}
							}
							
							tiles[row][col].setPiece(nextPiece);
							tiles[row][col].displayPiece();
							
						}
						
						
						System.out.println(nextLine);
						lineNum++;
						
						if(lineNum > 4) {
							if(col == 7) {
								col = 0;
								row++;
							}
							
							else{
								col++;
							}
						}		
					}
					
					sc.close();
					
					System.out.println("game loaded");
					actionText.append("game loaded\n");
					actionText.append("Turn " + turnNumber + "\n");
					actionText.append(currentPlayerColor + " player's turn..." + "\n");
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			
			
		}

	}

	/**
	 * handler for reset game functionality, resets the game state to a new game
	 * @author James Hoxie
	 *
	 */
	private class resetHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			playClip2();
			
			int choice = JOptionPane.showOptionDialog(getParent(), "Are you sure you want to reset the game?", "Reset Game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

			if(choice == 0) {
				clearPieces();
				clearBoard();
				setUpPieces();
			}			
		}
	}

	/**
	 * handler for exiting the game and going back to the title
	 * @author James Hoxie
	 *
	 */
	private class exitHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {	
			playClip2();
			
			int choice = JOptionPane.showOptionDialog(getParent(), "Are you sure you want to quit?", "End Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

			if(choice == 0) {
				clearPieces();
				clearBoard();
				setUpPieces();
				gameFrame.dispose();
				titleFrame.setVisible(true);
			}
		}
	}
}


