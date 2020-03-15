package Utils;
/**
 * 
 * This class represents a pair of row and column coordinates for a tile on the chess board.
 * @author James Hoxie
 * 
 * 
 */
public class Coordinate{
	private int row, col;
	
	/**
	 * Class constructor
	 * @param row The row coordinate of this pair
	 * @param col The column coordinate of this pair
	 */
	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Static class function, checks whether the given coordinate is valid for the given row and column sizes
	 * @param coordinate The coordinate to be validated
	 * @param rowSize The amount of rows for this coordinate space
	 * @param colSize The amount of columns for this coordinate space
	 * @return true if this coordinate is within the bounds of rowSize and colSize
	 */
	public static boolean isValidCoordinate(Coordinate coordinate, int rowSize, int colSize) {
		return coordinate.getRow() >= 0 && 
			   coordinate.getRow() < rowSize &&
			   coordinate.getCol() >= 0 &&
			   coordinate.getCol() < colSize;
	}
	
	/**
	 * 
	 * @return the row coordinate of this coordinate pair
	 */
	public int getRow() {
		return this.row;
	}
	
	/**
	 * 
	 * @return the column coordinate of this coordinate pair
	 */
	public int getCol() {
		return this.col;
	}
	

	/**
	 * 
	 * @param row value to be set for the row coordinate for this pair
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * 
	 * @param col value to be set for the column coordinate for this pair
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
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