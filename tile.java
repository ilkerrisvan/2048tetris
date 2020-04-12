import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class tile {
	public Point[][] coordinateMatrix;
	Color textcolor;
	Color backgroundColor;
	public int value; 
	public Point position;
	public Point postion;
	
	 tile(Color textcolor,Color backgroundColor,int value,Point position) {
		 this.value=getValue2048();  // get random value for each tile
		 this.textcolor=textColor(); // get textColor of each tile
		 this.backgroundColor=getBackground();  // get background of each tile
		 this.position = position;   // position info
	 }
	 
/*		//not used
 *          public String value() {
			Random rand = new Random();
			int tempValue = rand.nextInt(10) == 0 ? 4 : 2;
			String value = String.valueOf(tempValue);
			return value;	
		} */
	 
		 //assign value of new tile
		public static int getValue2048() {
			int array[]= {2,4,8};
		    int randomvalue = new Random().nextInt(array.length);
		    return array[randomvalue];
		}

		//assign text color of each tile 
	    public Color textColor()
	    {
	    	if(value == 8 || value == 16 )
	    		return Color.WHITE;
	        return Color.BLACK;
	    }

	    // assign tile of background color with total value of tile
		  public Color getBackground()
		    {	
			  
	       switch (value )
		        {
		        case 2:    return new Color(0xede0c8); 
		        case 4:    return new Color(0xf2b179);
		        case 8:    return new Color(0xf59563);
		        
		        case 16:   return new Color(0xf67c5f);
		        case 32:   return new Color(0xf65e3b);
		        case 64:   return new Color(0xedcf72);
		        case 128:  return new Color(0xedcc61);
		        case 256:  return new Color(0xedc850);
		        case 512:  return new Color(0xedc53f);
		        case 1024: return new Color(0xedc22e); // same colors with 2048 
		        case 2048: return new Color(0xedc22e);
		        }

		        return new Color( 0xcdc1b4 );
		    }
		  
		  
		  //tile merge ,not used,  guess
		  public void merge(tile [][] tileMatrix) {
			  for (int i = 0; i < tileMatrix.length; i++) {
				for (int j = 0; j < tileMatrix.length; j++) {
					 if(tileMatrix[i][j].value == tileMatrix[i-1][j].value && tileMatrix[i][j] != null && tileMatrix[i-1][j] != null) {
						 tileMatrix[i][j-1].value=tileMatrix[i][j].value*2;
						 tileMatrix[i][j].value=0;
					 }
					 if(tileMatrix[i][j].value == tileMatrix[i][j-1].value && tileMatrix[i][j] != null && tileMatrix[i][j-1] != null) {
						 tileMatrix[i][j-1].value=tileMatrix[i][j-1].value*2;
						 tileMatrix[i][j].value=0;
					 }
					 if(tileMatrix[i][j].value == tileMatrix[i-1][j-1].value && tileMatrix[i][j] != null && tileMatrix[i-1][j-1] != null) {
						 tileMatrix[i-1][j-1].value=tileMatrix[i-1][j-1].value*2;
						 tileMatrix[i][j].value=0;
					 }}
			}
		  
		  }
	
		  	//display tiles
		  public void displayTile(tile [][] tileMatrix,Point [][]coordinate) {
			  for (int row = 0; row < tileMatrix.length; row++) {
				for (int col = 0; col < tileMatrix.length; col++) {
							Point point = coordinate[row][col];
							if(point.y < 12 && tileMatrix[row][col] != null) {
								Color color = tileMatrix[row][col].backgroundColor;	
								Color text = tileMatrix[row][col].textcolor;
								StdDraw.setPenColor(color);
								StdDraw.filledSquare(point.x, point.y ,0.5);
								StdDraw.setPenColor(text);
								String textofTile=String.valueOf(tileMatrix[row][col].value);  
								StdDraw.text(point.x, point.y, textofTile);
					}
			
				
				}
			}
			  
		  }
	/*	  // not used
		  public void displayTileforGrid(tile [][] tileMatrix,tile tileForDisplay) {
			  for (int row = 0; row < tileMatrix.length; row++) {
					for (int col = 0; col < tileMatrix[0].length; col++) {
						if(tileMatrix[row][col] != null ) {
					  	Color text = tileMatrix[row][col].textcolor;
					  	if(tileMatrix[row][col].position !=null) {
					  	StdDraw.filledSquare(tileMatrix[row][col].position.x, tileMatrix[row][col].position.y,0.5);
					  	StdDraw.setPenColor(text);
						String textofTile=String.valueOf(tileMatrix[row][col].value);  
						StdDraw.text(tileMatrix[row][col].position.x, tileMatrix[row][col].position.y, textofTile);}}
					  
		  }}}*/
		  
		  
		  
}