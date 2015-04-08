
public class Tile {
	private final int GRASS = 1;
	private final int STONE = 2;
	private final int WATER = 3;
	private final int SAND = 4;
	private final int LAVA = 5;
	 
	int xPos; 
	int yPos; 
	int id;
	boolean isSolid;
	boolean isAnimated;
	boolean isDamage;
	SpriteSheet sheet; 
	int[][] tilePixelData;

	public Tile(int id, SpriteSheet sheet, int xPos, int yPos, boolean isSolid, boolean isAnimated, boolean isDamage) {
		this.id = id;
		this.xPos = xPos;
		this.yPos = yPos; 
		this.isSolid = isSolid;
		this.sheet = sheet;
		this.isDamage = isDamage;
		this.isAnimated = isAnimated;
		if (id == GRASS) tilePixelData = sheet.getTileData(0, 0, 8, 8);
		
		else if (id == STONE) tilePixelData = sheet.getTileData(2,0, 8, 8);
		
		else if (id == WATER) tilePixelData = sheet.getTileData(4,0, 8, 8);
		
		else if (id == SAND) tilePixelData = sheet.getTileData(8,0, 8, 8);
		
		else if (id == LAVA) tilePixelData = sheet.getTileData(14,0,8,8);
		
		
		
		

	}
	
	public int[][] getTilePixelData(){
		
		return tilePixelData;
	}
	
	public int getID(){
		return id;
	}
	
	public int getXPos(){
		return xPos;
	}
	
	public int getYPos(){
		return yPos;
	}

}
