package Game;

public class GameFlowThread{
	ChessPlayer whitePlayer, blackPlayer;
	ChessBoardManager boardManager;
	
	public GameFlowThread(){
		
	}
	
	//add participants to this chess game to keep track of state of game
	public void addParticipants(ChessPlayer whitePlayer, ChessPlayer blackPlayer, ChessBoardManager boardManager) {
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
		this.boardManager = boardManager;
	}
	
	//return true when whitePlayer or blackPlayer is in checkmate
	public boolean isOver() {
		return whitePlayer.isInCheckMate() || blackPlayer.isInCheckMate();
	}

	public void launch() {
		
	}
}