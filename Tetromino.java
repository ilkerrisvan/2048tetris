import java.util.ArrayList;
import java.util.Random;  // import Random class
import java.awt.Color; // import Color class
import java.awt.Point; // import Point class

// A class representing the tetromino L
public class Tetromino {
	
	private static final char[] CurrentX = null;
	// Private data fields
	private Color color; // color of the tetromino L
	public boolean[][] shapeMatrix; // shape of the tetromino L
	public Point[][] coordinateMatrix; // coordinates of the tetromino L w.r.t the game grid
	public int gridWidth, gridHeight; // dimensions of the tetris game grid
	
	public tile[][] tileMatrix = new tile [4][4];
	//elements of tiles
	int randomvalue;
	Color textcolorofTile;
	Color backgroundColor;
	int valueofTile;
	Point pointerforTile;
	
	
	
	// Constructor
	Tetromino (int gridHeight, int gridWidth) {
		
		this.gridHeight = gridHeight;
		this.gridWidth = gridWidth;
		// color of the tetromino is determined randomly
		Random random = new Random();
		int red = random.nextInt(256), green = random.nextInt(256), blue = random.nextInt(256);
		color = new Color(red, green, blue);
		// shape of the tetromino in its initial orientation
		shapeGenerator();
		// initial coordinates just before the tetromino L enters the game grid from the upper side
		// at a random horizontal position
		int n_rows = 4, n_cols = 4;
		coordinateMatrix = new Point[n_rows][n_cols];
		int lowerLeftCornerX = random.nextInt(gridWidth - (n_cols - 1)), lowerLeftCornerY = gridHeight;
		coordinateMatrix[n_rows - 1][0] = new Point(lowerLeftCornerX, lowerLeftCornerY);
		for (int row = n_rows - 1; row >= 0; row--)
			for (int col = 0; col < n_cols; col++) {
				if (row == n_rows - 1 && col == 0)
					continue;
				else if (col == 0) { 
					int currentX = coordinateMatrix[row + 1][col].x;
					int currentY = coordinateMatrix[row + 1][col].y + 1;
					coordinateMatrix[row][col] = new Point(currentX, currentY);
					continue;
				}
				int currentX = coordinateMatrix[row][col - 1].x + 1;
				int currentY = coordinateMatrix[row][col - 1].y; 
				coordinateMatrix[row][col] = new Point(currentX, currentY);			
				
			}
	}
		
		
	// create a new random tetromino	
	public void shapeGenerator() {
		Random random= new Random();
		int random_number = random.nextInt(7);
		switch (random_number) {
        case 0:  boolean [][] shapeI = {{false, false, false, false},{true, true, true, true },{false, false, false, false},{false, false, false, false}};
        		 shapeMatrix= shapeI;
        		 break;
        case 1:  boolean [][] shapeL = {{false, false, false, false}, {false, true, true, true}, {false, true, false, false},{false, false, false, false}};
		 		 shapeMatrix= shapeL;		
        		 break;
        case 2:  boolean [][] shapeO = {{false, false, false, false}, {false, false, false, false}, {false, true, true, false},{false, true, true, false}};
		 		 shapeMatrix= shapeO;		
		 		 break;	 
        case 3:  boolean [][] shapeT = {{false, false, false, false}, {false, true, true, true}, {false, false, true, false},{false, false, false, false}};
		 		 shapeMatrix= shapeT;		
		 		 break;	  
        case 4:  boolean [][] shapeJ = {{false, false, false, false}, {false, true, true, true}, {false, false, false, true},{false, false, false, false}};
		 		 shapeMatrix= shapeJ;		
		 		 break;
        case 5:  boolean [][] shapeS = {{false, false, false, false}, {false, true, true, false}, {true, true, false, false},{false, false, false, false}};
  		 		 shapeMatrix= shapeS;		
  		 		 break; 		 
        case 6:  boolean [][] shapeZ = {{false, false, false, false}, {false, true, true, false}, {false, false, true, true},{false, false, false, false}};
        		 shapeMatrix= shapeZ;		
        		 break; 	 		 		 
		}
		 for (int i = 0; i < 4; i++) {
			 for (int j = 0; j < 4; j++) {
				  if(shapeMatrix[i][j] == true)
					  tileMatrix [i][j] =new tile(textcolorofTile,backgroundColor,valueofTile,pointerforTile); // create tile for each true  square
			}	
		}
	}
	//modifier
		public tile[][] to2DArray128() {
			tile tilenew[][]= new tile[12][8];
			int k = 0;
			for (int i = 0; i < tileMatrix.length; i++) {
				for (int j = 0; j < tileMatrix[0].length; j++) {
						tilenew[i][j] = tileMatrix[i][j];
					}
				
			}
		return tilenew;}

		
	//rotate of each tile
	public boolean rotate(Grid gameGrid) {
		tile [][] temp= new tile [4][4];
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++)
				temp[i][j] = tileMatrix[i][j];
		// copy back rotated 90 degrees
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++)
				tileMatrix[j][i] = temp[i][3-j];
		return false;
	}
	
	private void Random() {
		// TODO Auto-generated method stub
		
	}
	
	// Getter method for getting the color of tetromino L
	public Color getColor() {
		return color;
	}
	
	// Method for displaying tetromino L on the game grid
	public void display() { 
		for (int row = 0; row < coordinateMatrix.length; row++) {
			for (int col = 0; col < coordinateMatrix[0].length; col++) {
				Point point = coordinateMatrix[row][col];
		
				// considering newly entered tetromino L objects to the game grid that may have squares with point.y >= gridHeight
				if(point.y   < gridHeight   && tileMatrix[row][col] != null) {
				   tileMatrix[row][col].displayTile(tileMatrix,coordinateMatrix); //display tiles
				}	} }
		
	}

	
	
	
	
	
	// Method for moving tetromino L down by 1 in the game grid
	public boolean goDown(Grid gameGrid) {
		// Check whether tetromino L can go down or not
		boolean canGoDown = true;
		// determine the coordinates of the bottommost block for each column of tetromino L
		Point dummyPoint = new Point(-1, -1);
		Point[] bottommostBlock = {dummyPoint, dummyPoint, dummyPoint, dummyPoint};
		for (int col = 0; col < tileMatrix[0].length; col++) {
			for (int row = tileMatrix.length - 1; row >= 0; row--) {
				if (tileMatrix[row][col] != null) {
					bottommostBlock[col] = coordinateMatrix[row][col];
					if (bottommostBlock[col].y == 0) // tetromino L cannot go down if it is already at y = 0
						canGoDown = false;
					break; // break the inner for loop
				}
			}
			if (!canGoDown)
				break; // break the outer for loop
		}
		// check if the grid square below the bottommost block is occupied for each column of tetromino L
		if (canGoDown) {
			for (int i = 0; i < bottommostBlock.length; i++) {
				// skip each column of tetromino L that does not contain any blocks
				if (bottommostBlock[i].equals(dummyPoint))
					continue;
				// skip each column of tetromino L whose bottommost block is out of the game grid 
				// (newly entered tetromino L objects to the game grid) 
				if (bottommostBlock[i].y > gridHeight)
					continue;
				if (gameGrid.isOccupied(bottommostBlock[i].y - 1, bottommostBlock[i].x)) {
					canGoDown = false;
					break; // break the for loop
				}
			}
		}
		// move tetromino L down by 1 in the game grid if it can go down
		if (canGoDown) {
			for (int row = 0; row < coordinateMatrix.length; row++)
				for (int col = 0; col < coordinateMatrix[0].length; col++)
					coordinateMatrix[row][col].y--;
		}
		// return the result
		return canGoDown;
	}
	// Method for returning the occupied squares w.r.t. the game grid by a placed (stopped) tetromino L 
	public Point[] getOccupiedSquares() {
		Point[] occupiedSquares = new Point[1];
		Point[] occupiedSquares1 = new Point[1];
		Point[] occupiedSquares2 = new Point[1];
		Point[] occupiedSquares3 = new Point[1];
		int count = 0;
		 
		for (int row = 0; row < coordinateMatrix.length; row++)
			for (int col = 0; col < coordinateMatrix[0].length; col++)
				if (tileMatrix[row][col] != null) {
					occupiedSquares[count] = coordinateMatrix[row][col];
					occupiedSquares1[count] = coordinateMatrix[row][col];
					occupiedSquares2[count] = coordinateMatrix[row][col];
					occupiedSquares3[count] = coordinateMatrix[row][col];
				}
		
		Point x[] = { occupiedSquares[0], occupiedSquares1[0],occupiedSquares2[0],occupiedSquares3[0]};
				
					
		return x;
	}
	
	
	
	// Method for moving tetromino L left by 1 in the game grid
	public boolean goLeft(Grid gameGrid) {
		// Check whether tetromino L can go left or not
		boolean canGoLeft = true;
		// determine the coordinates of the leftmost block for each row of tetromino L
		Point dummyPoint = new Point(-1, -1);
		Point[] leftmostBlock = {dummyPoint, dummyPoint, dummyPoint, dummyPoint};
		for (int row = 0; row < tileMatrix.length; row++) {
			for (int col = 0; col < tileMatrix[0].length; col++) {
				if (tileMatrix[row][col] != null) {
					leftmostBlock[row] = coordinateMatrix[row][col];
					if (leftmostBlock[row].x == 0) // tetromino L cannot go left if it is already at x = 0
						canGoLeft = false;
					break; // break the inner for loop
				}
			}
			if (!canGoLeft)
				break; // break the outer for loop
		}
		// check if the grid square on the left of the leftmost block is occupied for each row of tetromino L
		if (canGoLeft) {
			for (int i = 0; i < leftmostBlock.length; i++) {
				// skip each row of tetromino L that does not contain any blocks
				if (leftmostBlock[i].equals(dummyPoint))
					continue;
				// skip each row of tetromino L whose leftmost block is out of the game grid 
				// (newly entered tetromino L objects to the game grid) 
				if (leftmostBlock[i].y >= gridHeight)
					continue;
				if (gameGrid.isOccupied(leftmostBlock[i].y, leftmostBlock[i].x - 1)) {
					canGoLeft = false;
					break; // break the for loop
				}
			}
		}
		// move tetromino L left by 1 in the game grid if it can go left
		if (canGoLeft) {
			for (int row = 0; row < coordinateMatrix.length; row++)
				for (int col = 0; col < coordinateMatrix[0].length; col++)
					coordinateMatrix[row][col].x--;
		}
		// return the result
		return canGoLeft;
	}
	// Method for moving tetromino L right by 1 in the game grid
	public boolean goRight(Grid gameGrid) {
		// Check whether tetromino L can go right or not
		boolean canGoRight = true;
		// determine the coordinates of the rightmost block for each row of tetromino L
		Point dummyPoint = new Point(-1, -1);
		Point[] rightmostBlock = {dummyPoint, dummyPoint, dummyPoint, dummyPoint};
		for (int row = 0; row < tileMatrix.length; row++) {
			for (int col = tileMatrix[0].length - 1; col >= 0; col--) {
				if (tileMatrix[row][col] != null) {
					rightmostBlock[row] = coordinateMatrix[row][col];
					if (rightmostBlock[row].x == gridWidth - 1) // tetromino L cannot go right if it is already at x = gridWidth - 1
						canGoRight = false;
					break; // break the inner for loop
				}
			}
			if (!canGoRight)
				break; // break the outer for loop
		}
		// check if the grid square on the right of the rightmost block is occupied for each row of tetromino L
		if (canGoRight) {
			for (int i = 0; i < rightmostBlock.length; i++) {
				// skip each row of tetromino L that does not contain any blocks
				if (rightmostBlock[i].equals(dummyPoint))
					continue;
				// skip each row of tetromino L whose rightmost block is out of the game grid 
				// (newly entered tetromino L objects to the game grid) 
				if (rightmostBlock[i].y >= gridHeight)
					continue;
				if (gameGrid.isOccupied(rightmostBlock[i].y, rightmostBlock[i].x + 1)) {
					canGoRight = false;
					break; // break the for loop
				}
			}
		}
		// move tetromino L right by 1 in the game grid if it can go right
		if (canGoRight) {
			for (int row = 0; row < coordinateMatrix.length; row++)
				for (int col = 0; col < coordinateMatrix[0].length; col++)
					coordinateMatrix[row][col].x++;
		}
		// return the result
		return canGoRight;
	}



	

}