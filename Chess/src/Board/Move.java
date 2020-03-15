package Board;

import java.util.ArrayList;

/**
 * 
 * This class represents a move in chess, from one space to another. It holds an arraylist of tiles that will never be larger than size 2, 
 * the tile at index 0 is the source tile of this move and the tile at index 1 is the destination tile of this
 * move. This class does not check if a move is valid, it only constructs a move based on source and destination selections
 * @author James Hoxie
 *
 */
public class Move{
	private ArrayList<Tile> move = new ArrayList<Tile>();
	
	public Move() {}

	/**
	 * 
	 * @return true if a source tile has been selected for this move
	 */
	public boolean sourceSelected() {
		return move.size() >= 1;
	}
	
	/**
	 * 
	 * @return true if a destination tile has been selected
	 */
	public boolean destinationSelected() {
		return move.size() == 2;
	}
	
	/**
	 * 
	 * @return the tile set as the source for this move or null if no source has been set
	 */
	public Tile getSourceTile() {
		if(sourceSelected()) {
			return move.get(0);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @return the tile set as the destination for this move or null if no destination has been set
	 */
	public Tile getDestinationTile() {
		if(destinationSelected()) {
			return move.get(1);
		}
		
		return null;
	}
	
	/**
	 * Sets the source tile for this move
	 * @param source the tile to be set as the source for this move
	 */
	public void setSource(Tile source) {
		move.add(source);
	}

	/**
	 * Sets the destination tile for this move
	 * @param destination the tile to be set as the destination for this move
	 */
	public void setDestination(Tile destination) {
		move.add(destination);
	}
	
	/**
	 * Clears the previously generated move to allow a new move to be constructed
	 */
	public void clearMove() {
		move.clear();
	}
}