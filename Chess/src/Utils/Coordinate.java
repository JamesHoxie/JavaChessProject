package Utils;
//coordinate class, represents row and column coordinates of tile on board
public class Coordinate{
	private int row, col;
	
	//class constructor, takes row and column coordinates as params
	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	//returns row coordinate of this coordinate pair
	public int getRow() {
		return this.row;
	}
	
	//returns col coordinate of this coordinate pair
	public int getCol() {
		return this.col;
	}
	
	//sets x coordinate of this coordinate pair
	public void setRow(int row) {
		this.row = row;
	}
	
	//sets y coordinate of this coordinate pair
	public void setCol(int col) {
		this.col = col;
	}
	
	//override equals method to compare coordinates properly, returns true if coordinates have same x and y values
	@Override 
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Coordinate)) {
			return false;
		}
		
		Coordinate c = (Coordinate) o;
		
		return c.getRow() == this.row && c.getCol() == this.col;
	}
}