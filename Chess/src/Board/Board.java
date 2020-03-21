package Board;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
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
	private Move currentMove;
	private ChessColor currentPlayerColor;
	private ChessColor playerInCheckMate = null;
	private ChessColor playerInCheck = null;
	private King blackKing, whiteKing;
	private ArrayList<Piece> whitePieces = new ArrayList<Piece>();
	private ArrayList<Piece> blackPieces = new ArrayList<Piece>();

	//function sets up pieces on board for start of game
	void setUpPieces() {
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
	
	//function fills tiles array and fills board with JButtons to act as spaces, alternate between black and white tiles
	void setUpTiles(){
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

				tiles[i][j].addActionListener(new TileHandler());
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
		this.tiles = new Tile[xSize][ySize];
		this.currentMove = new Move();
		this.currentPlayerColor = ChessColor.WHITE;
		setUpTiles();
		setUpPieces();
	}
	
	/**
	 * This function is used to promote a pawn when it reaches the other side of the board
	 * @param pawnToBePromoted The pawn to be promoted into a piece of promotionType
	 * @param promotionType The type of chess piece the pawn will be promoted to (Rook, Bishop, Knight, or Queen)
	 */
	public void promotePawn(Piece pawnToBePromoted, int promotionType) {
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
	public void checkForPawnPromotion() {
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
				selection = JOptionPane.showOptionDialog(null, "Select piece to promote to...", "Pawn Promotion", 0, JOptionPane.PLAIN_MESSAGE, null, options, 0);
				pawnToPromote = (Pawn) p;
				break;
			}
		}
		
		if(selection != -1) {
			promotePawn(pawnToPromote, selection);
		}
	}
	
	//returns color of player in checkmate on this board, or null if neither player is in checkmate
	public ChessColor getPlayerInCheckMate() {
		return playerInCheckMate;
	}
	
	/**
	 * Checks if the player matching playerColor is currently in check, updates playerInCheck member variable
	 * of board accordingly, null if player is not in check
	 * @param playerColor color of the player being checked for check status
	 */
	public void checkForCheck(ChessColor playerColor) {
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
				moves = piece.generateMoves(tiles);
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
			System.out.println(currentPlayerColor + "player is in check");
		}
		
		else {
			playerInCheck = null;
			System.out.println(currentPlayerColor + " player is not in check");
		}
		
	}
		
	/**
	 * Checks if the player matching playerColor is currently in checkmate, updates playerInCheckMate member variable
	 * of board accordingly, null if player is not in checkmate, necessary assumption for this function being called:
	 * player matching playerColor is already in check
	 * @param playerColor color of the player being checked for checkmate status
	 */
	public void checkForCheckMate(ChessColor playerColor) {
		Coordinate[] moves;
		Move nextPossibleMove = new Move();
		ArrayList<Piece> friendlyPieces;
		Tile sourceTile, destinationTile;
		Piece[] originalPlacements;
		
		if(playerColor == ChessColor.WHITE) {
			friendlyPieces = whitePieces;
		}
		
		else {
			friendlyPieces = blackPieces;
		}
		
		for(Piece piece : friendlyPieces) {
			
			System.out.println(piece);
			
			moves = piece.generateMoves(tiles);

			for(Coordinate c: moves) {
				sourceTile = getTile(piece.getPieceCoordinate());
				destinationTile = getTile(c);
				nextPossibleMove.setSource(sourceTile);
				nextPossibleMove.setDestination(destinationTile);
				
				if(isValidMove(nextPossibleMove)) {
					originalPlacements = executeMove(nextPossibleMove);
					System.out.println(nextPossibleMove.getSourceTile().getPiece());
					System.out.println(nextPossibleMove.getDestinationTile().getCoordinate().getRow());
					System.out.println(nextPossibleMove.getDestinationTile().getCoordinate().getCol());
					checkForCheck(playerColor);
					
					if(playerInCheck != playerColor) {
						System.out.println("No player is in checkmate");
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
		
		System.out.println(playerColor + " player is in checkmate");
		playerInCheckMate = playerColor;
	}
	
	//switches color of current player taking their turn
	public void switchCurrentPlayerColor() {
		if(this.currentPlayerColor == ChessColor.BLACK) {
			this.currentPlayerColor = ChessColor.WHITE;
		}
		
		else {
			this.currentPlayerColor = ChessColor.BLACK;
		}
	}
	
	//returns the color of the player currently taking their turn
	public ChessColor getCurrentPlayerColor() {
		return this.currentPlayerColor;
	}

	//returns tile of this board at given coordinate, coordinate must be valid
	public Tile getTile(Coordinate coordinate) {
		return tiles[coordinate.getRow()][coordinate.getCol()];
	}
	
	//checks if current move is valid
	public boolean isValidMove(Move move) {
		Tile sourceTile = move.getSourceTile();
		Tile destinationTile = move.getDestinationTile();
		Piece piece = sourceTile.getPiece();		
		Coordinate[] validMoves = piece.generateMoves(tiles);
		
		//check all generated moves, if coordinates of any match coordinates of destination tile then
		//move is valid
		for(Coordinate c: validMoves) {
			if(c.equals(destinationTile.getCoordinate())) {
				return true;
			}
		}
		
		return false;
	}
	
	//used to undo the last move taken in the event of a move putting a player into check or keeping them in check
	public void undoMove(Piece[] originalPlacements, Move move) {
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
	
	//moves piece from source tile to destination for the current move, does not adjust icons
	public Piece[] executeMove(Move move) {
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
		}
		
		return orignalPlacements;
	}
	
	//sets icons on board for move
	public void commitMove() {
		Tile sourceTile = currentMove.getSourceTile();
		Tile destinationTile = currentMove.getDestinationTile();

		sourceTile.displayPiece();
		destinationTile.displayPiece();
	}
	
	/**
	 * tries to perform this move. first checks for a player in check, if a player is in check then check if that check
	 * is also checkmate. if it is checkmate, end the game. otherwise check if the move to be performed is valid.
	 * if it is, try to execute the move, otherwise clear the move and try again.
	 * after executing the move, check if this move put the player in check, if it did then undo the move and clear the
	 * move to try again. if it did not, then commit the move by updating the pieces icons on the board and switch turns
	 */
	public void tryMove() {
		if(playerInCheck != null) {
			checkForCheckMate(playerInCheck);
		}
		
		if(isValidMove(currentMove)) {
			Piece piece = currentMove.getSourceTile().getPiece();
			System.out.println("row: " + currentMove.getSourceTile().getPiece().getPieceCoordinate().getRow());
			System.out.println("col: " + currentMove.getSourceTile().getPiece().getPieceCoordinate().getCol());
			Piece[] originalPlacements;
			originalPlacements = executeMove(currentMove);
			checkForCheck(currentPlayerColor);
			
			//if this move put this player in check, undo and try another move
			if(playerInCheck == currentPlayerColor) {
				undoMove(originalPlacements, currentMove);
			}
			
			//player was not put in check by move, commit move
			else {
				commitMove();
				
				//check if a pawn can be promoted
				checkForPawnPromotion();
				
				switchCurrentPlayerColor();
				//check if other player was put into check from last players move
				checkForCheck(currentPlayerColor);
				
				if(playerInCheck != null) {
					JOptionPane.showMessageDialog(null, playerInCheck + " player is in check");
				}
				
				if(playerInCheck == currentPlayerColor) {
					//check if other player was put into checkmate from last players move
					checkForCheckMate(currentPlayerColor);
					
					if(playerInCheckMate == currentPlayerColor) {
						JOptionPane.showMessageDialog(null, playerInCheckMate + " player is in check mate!");
						//TODO: game is over, adjust shut down procedures
						System.exit(0);
					}
					
				}
				
			}
			
			System.out.println("drow: " + piece.getPieceCoordinate().getRow());
			System.out.println("dcol: " + piece.getPieceCoordinate().getCol());
		}

		
		
		currentMove.clearMove();
	}
	
	//Handler class for button clicks to tiles on gameboard
	private class TileHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			for(Piece p : blackPieces) {
				System.out.println("Black: " + p);
			}
			
			for(Piece p : whitePieces) {
				System.out.println("White: " + p);
			}
			
			Tile clickedTile = (Tile) e.getSource();
			Piece clickedPiece = clickedTile.getPiece();
			
			if(!(currentMove.sourceSelected())) {
				//if clicked tile has a piece and 
				//if clicked piece matches color of player taking their turn
				if(clickedPiece != null && clickedPiece.getPieceColor() == currentPlayerColor) {
					currentMove.setSource(clickedTile);
					System.out.println("SOURCE SET!");
				}
			}
			
			else if(!(currentMove.destinationSelected())) {
				//if clicked tile is empty or
				//if clicked tile does not have a piece of the same color as the player taking their turn
				if(clickedPiece == null || clickedPiece.getPieceColor() != currentPlayerColor) {
					currentMove.setDestination(clickedTile);
					System.out.println("DESTINATION SET!");
					tryMove();
				}
			}
		}
		
	}
}