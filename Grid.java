import java.awt.Color; // import Color class
import java.awt.Point; // import Point class
import java.util.Random;

// A class representing the tetris game grid
public class Grid {
	// Private data fields
	private Color emptySquare; // color used for empty squares 
//	private Color[][] colorMatrix; // a matrix storing colors of all squares
	private tile[][] tileMatrix = new tile [12][8];
	Color textcolorofTile;
	Color backgroundColor;
	int valueofTile;
	Point pointerforTile;
	
	// Constructor
	Grid (int n_rows, int n_cols) {
		// assigning color used for empty squares
		emptySquare = StdDraw.LIGHT_GRAY;
		// creating colorMatrix with given dimensions
		tileMatrix = new tile[n_rows][n_cols];
		// initializing colorMatrix with color emptySquare for all its elements 
		// using initMatrix method defined below
		initMatrix();

	}

	

	// Method used for initializing colorMatrix 
	public void initMatrix() {
		for (int row = 0; row < tileMatrix.length; row++)
			for (int col = 0; col < tileMatrix[0].length; col++) {
				tileMatrix[row][col]=new tile (textcolorofTile,backgroundColor,valueofTile,pointerforTile);
				tileMatrix[row][col].backgroundColor = emptySquare;	
			}
	}
	// Method used for checking whether the square with given indices is inside the grid or not
	public boolean isInside(int row, int col) {
		if (row < 0 || row >= tileMatrix.length)
			return false;
		if (col < 0 || col >= tileMatrix[0].length)
			return false;
		return true;
	}

	// Method used for checking whether the square with given indices is occupied or empty
	public boolean isOccupied(int row, int col) {
		return tileMatrix[row][col].backgroundColor != emptySquare;
	}


	// Method for updating the game grid with a placed (stopped) tiles
	public void updateGrid(tile [][] a213,Point [][] pointer) {
		for (int i = 0; i < tileMatrix.length; i++) {
			for (int j = 0; j < tileMatrix[0].length; j++) {
				if(a213[i][j] != null) 
			     a213[i][j].position = pointer[i][j];
				if(tileMatrix[i][j]!= null && a213[i][j] != null) {
				 tileMatrix[i][j].postion = a213[i][j].position; //get position
				 tileMatrix[i][j].value = a213[i][j].value; // get tile's value
				 tileMatrix[tileMatrix[i][j].postion.y][tileMatrix[i][j].postion.x].backgroundColor=a213[i][j].backgroundColor;  // each specific position has each tile's background color
				 
				 //System.out.println( tileMatrix[i][j].value); // show each tile's value for check.
				}
	}
}
	}
	
	
	// Method used for displaying the grid
	public void display() {
		
		// drawing squares
		for (int row = 0; row < tileMatrix.length; row++)
			for (int col = 0; col < tileMatrix[0].length; col++) {

				StdDraw.setPenColor(tileMatrix[row][col].backgroundColor);
				StdDraw.filledSquare(col, row, 0.5);
			}
		// drawing the grid
		StdDraw.setPenColor(StdDraw.BOOK_BLUE);
		for (double x = -0.5; x < tileMatrix[0].length; x++) // vertical lines
			StdDraw.line(x, -0.5, x, tileMatrix.length - 0.5);
		for (double y = -0.5; y < tileMatrix.length; y++) // horizontal lines
			StdDraw.line(-0.5, y, tileMatrix[0].length - 0.5, y);
	
		
		//draw tiles // not work effective(because there is not real tile) , for show values of tiles.
		for (int row = 0; row < tileMatrix.length; row++) {
			for (int col = 0; col < tileMatrix[0].length; col++) {
				if(tileMatrix[row][col] != null && tileMatrix[row][col].postion !=null) {
				Color text = tileMatrix[row][col]. textColor();
				StdDraw.setPenColor(text);
				String textofTile=String.valueOf(tileMatrix[row][col].value);  
				StdDraw.text(tileMatrix[row][col].postion.x, tileMatrix[row][col].postion.y, textofTile);}
			}} 
		
	}

	
	// lose checker if last rows once tile is not empty you lose.
	public boolean isFull() {
		for(int i=0; i<8; i++) // numbers of cols
			if(tileMatrix[11][i].backgroundColor !=emptySquare)  // 11 is the last row (num of rows =12) 
				return true;
		return false;
	}
	// delete full rows
	public void fullRows() { 
		for(int i=0; i<8; i++) // numbers of cols
			if(isRowFull(i)) 
				removeRow(i); // remove each full row
	
	}	
	// control each row  is it full or not
	public boolean isRowFull(int row) {
		for(int j=0; j<8; j++)
			if(tileMatrix[row][j].backgroundColor == emptySquare)
				return false;
		return true;
		}

	// delete full row
	public void removeRow(int row) {
		for(int j=0; j<8; j++)
			tileMatrix[row][j].backgroundColor = emptySquare; // if full row's elements backgroungColor will be emptySquare color (gray) it will be empty on grid. 
		for(int i=row; i<11; i++) { //row num is 11 because of get elements of row above full row if we use 12 it will cause of exception 
			for(int j=0; j<8; j++) { // col num
				tileMatrix[i][j] = tileMatrix[i+1][j];  // update timeMatrix for get elements of row above full row
				
			}
		}
	}



}
	
	

	

