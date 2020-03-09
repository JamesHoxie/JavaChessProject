package Board;

import java.util.ArrayList;

//This class holds an arraylist of tiles that will never be larger than size 2,
//the tile at index 0 is the source tile of this move and the tile at index 1 is the
//destination tile of the this move
//this class does not check if a move is valid, it simply constructs a move based on source and destination
public class Move{
	private ArrayList<Tile> move;
	
	//return true is a source tile has been selected
	public boolean sourceSelected() {
		return move.size() >= 1;
	}
	
	//return true if a destination tile has been selected
	public boolean destinationSelected() {
		return move.size() == 2;
	}
	
	//return tile set as source for this move, null if no source set
	public Tile getSourceTile() {
		if(sourceSelected()) {
			return move.get(0);
		}
		
		return null;
	}
	
	//return tile set as destination for this move
	public Tile getDestinationTile() {
		if(destinationSelected()) {
			return move.get(1);
		}
		
		return null;
	}
	
	//set source tile for this move
	public void setSource(Tile source) {
		move.add(source);
	}
	
	//set destination tile for this move
	public void setDestination(Tile destination) {
		move.add(destination);
	}
	
	//clears previous move for next move
	public void clearMove() {
		move.clear();
	}
	
	//class constructor, initialize arraylist to hold source and destination tiles for move
	public Move() {
		move = new ArrayList<Tile>();
	}
}