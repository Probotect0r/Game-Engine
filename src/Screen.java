
public class Screen {

	int xOffSet = 0;
	int yOffSet = 0;
	Game game;
	SpriteSheet spriteSheet;
	Level level; 
	int height;
	int width;
	int [][] pixels2D;
	Tile[][] allTileData = new Tile[15][20];
	LocalPlayer player; 
	
	public Screen(Game game, SpriteSheet sheet, int height, int width, Level level, LocalPlayer player ) {
		
		this.game = game; 
		this.spriteSheet = sheet;
		this.height = height;
		this.width = width;
		this.level = level;
		this.player = player;
		pixels2D = new int[height][width];
		allTileData = level.getAllTileData();
		
			
	}
	
	public void render(){			
		game.convertTo1D(pixels2D);		
		drawTiles();
		drawPlayer();
		
		
		
	}
	
	public void drawTiles(){
		allTileData = level.getAllTileData();
		int xTilePixel = 0;
		int yTilePixel = 0;
		int[][] tempTileData = new int[8][8];	
		
		for(int i=0; i<15; i++){			 // loops through each tile from the allTileData array
			for(int j=0; j<20; j++){	
				xTilePixel =  allTileData[i][j].getXPos() * 8 + xOffSet;	//for each tile, get the  x and y Pixel position and adds the appropriate pixel offset		
				yTilePixel = allTileData[i][j].getYPos() * 8 + yOffSet;	
				tempTileData = allTileData[i][j].getTilePixelData();		// gets the tile pixel data for each tile
				
				int index=0;
				for (int row = yTilePixel; row<yTilePixel+8; row++){		// goes to the x and y position (with offset) in the pixels2D array
					int index2=0;											// and proceeds to set the next 8*8 pixels to the same as the tile pixel data
					for (int column=xTilePixel; column< xTilePixel+8; column++){		
						if((row < height && column < width) && (row >= 0 && column >= 0)){ //if the y poisition is on the screen, and x position is on the screen
							pixels2D[row][column] = tempTileData[index][index2]; //Then proceeds to set the pixel at that position to the appropriate one from the tilePixelData							
						}
						index2++;
					}	
					index++;					
				}			
					
			}
		}
		
		game.convertTo1D(pixels2D);	
	}
	
	public void drawPlayer(){
		player.render();
	
		int xPlayerPixel = player.xPos;
		int yPlayerPixel = player.yPos;
		int [][] playerPixelData = new int[16][10];
		playerPixelData = player.pixelData;		
		
		int index=0;
		for (int row=yPlayerPixel; row<(yPlayerPixel+16); row++){
			int index2 = 0;
			for(int column=xPlayerPixel; column<(xPlayerPixel+10); column++){
				if((yPlayerPixel < height && xPlayerPixel < width) && (yPlayerPixel >= 0 && xPlayerPixel >= 0)){
					if (playerPixelData[index][index2] == Colours.getColour(255, 255, 255, 255)) playerPixelData[index][index2] = pixels2D[row][column];
					pixels2D[row][column] = playerPixelData[index][index2];

				}
				index2++;
			}
			index++;
		}
		

		
		
		
		 
		game.convertTo1D(pixels2D);
		
	}

}
