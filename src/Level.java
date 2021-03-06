
public class Level extends ImageReader {
	
	private final int GRASSTILE = Colours.getColour(255,81,132,19);
	private final int STONETILE = Colours.getColour(255,129,129,129);
	private final int WATERTILE = Colours.getColour(255,6,105,178);
	private final int SANDTILE  = Colours.getColour(255,255,196,125);
	private final int LAVATILE = Colours.getColour(255, 150, 0, 20);
	Tile [][] tilesFromLvl;
	long animationTimer = 0;
	
	public Level(String path, SpriteSheet sheet) {
		super(path);
		tilesFromLvl = new Tile[height][width];
		int index=0;
		for (int row=0; row<height; row++){
			for(int column=0; column<width; column++){
				if (pixelData[row][column] == GRASSTILE){
					tilesFromLvl [row][column] = new Tile(1, sheet, column, row, false, false, false);					
				}
				else if (pixelData[row][column] == STONETILE){
					tilesFromLvl [row][column] = new Tile(2, sheet, column, row, true, false, false);
				}
				else if (pixelData[row][column] == WATERTILE){
					tilesFromLvl [row][column] = new AnimatedTile(3, sheet, column, row, false, true, false);
				}
				else if (pixelData[row][column] == SANDTILE){
					tilesFromLvl [row][column] = new Tile(4, sheet, column, row, false, false, false);
				}
				else if (pixelData[row][column] == LAVATILE){
					tilesFromLvl [row][column] = new DamageTile(5, sheet, column, row, false, false,true);
				}
			}
		}
		
		
		
	}
	
	public Tile[][] getAllTileData(){
		return tilesFromLvl;
	}
	
	public void tick(){
		if (System.currentTimeMillis() - animationTimer >= 1000){
			animationTimer = System.currentTimeMillis();
			for (int row=0; row<15; row++){
				for (int column=0; column<20; column++){
					if (tilesFromLvl[row][column] instanceof AnimatedTile){
						if (((AnimatedTile)tilesFromLvl[row][column]).animationState == 1){
							((AnimatedTile)tilesFromLvl[row][column]).setState(2);
						}
						else if (((AnimatedTile)tilesFromLvl[row][column]).animationState == 2){
							((AnimatedTile)tilesFromLvl[row][column]).setState(3);
						}
						else if (((AnimatedTile)tilesFromLvl[row][column]).animationState == 3){
							((AnimatedTile)tilesFromLvl[row][column]).setState(4);
						}
						else if (((AnimatedTile)tilesFromLvl[row][column]).animationState == 4){
							((AnimatedTile)tilesFromLvl[row][column]).setState(1);
						}
						
						
						
						
					}
				}
			}
		}
	}

}
