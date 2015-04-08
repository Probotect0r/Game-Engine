
public class SpriteSheet extends ImageReader {

	public SpriteSheet(String path){
		super(path);
	}
	//xTilePos and yTilePos is the position of the tile on the Sprite Sheet. from 0-31 in x direction and 0-31 in y.
	public int[][] getTileData(int xTilePos, int yTilePos, int width, int height){
		int xOnSprite = xTilePos * 8;
		int yOnSprite = yTilePos * 8;
		int[][] tileData = new int[height][width];
		
		for (int row=0; row<height; row++){
			for (int column=0; column<width; column++){
				tileData[row][column] = pixelData[yOnSprite][xOnSprite++];
			}
			xOnSprite= xTilePos *8; // to reset it to the beggining column of that tile
			yOnSprite++;
		}
		
		return tileData;		
		
	}
	
	public int[][] getPlayerData(int rowOnSprite, int columnOnSprite){
		int[][] playerData = new int[16][10];
		int index = columnOnSprite;
		for (int row=0; row<16; row++){
			index = columnOnSprite;
			for(int column=0; column<10; column++){
				playerData[row][column] = pixelData[rowOnSprite][index++];
			}
			rowOnSprite++;
		}
		return playerData;
	}

}
