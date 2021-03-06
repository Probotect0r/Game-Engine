
public class LocalPlayer {
	public int xPos = 4*8; 
	public int yPos = 4*8; 
	int [][] pixelData = new int[16][10];
	public boolean hasCollidedUp = false;
	public boolean hasCollidedRight = false;
	public boolean hasCollidedDown = false;
	boolean hasCollidedLeft = false;
	final int UP = 1;
	final int RIGHT = 2;
	final int DOWN = 3; 
	final int LEFT = 4;
	int dirMoving = RIGHT;
	SpriteSheet sheet; 
	Game game; 
	boolean isMoving = false;
	long animationTimer = 0;
	int animationState = 1;
	Double  playerHP = 100.0;
	int globalx;
	int globaly;
	Level level;
	
	
	public LocalPlayer(SpriteSheet sheet, Game game, Level level, int xPos, int yPos) {		
		this.sheet = sheet;
		this.level = level;
		this.game = game;
		this.xPos = xPos;
		this.yPos = yPos;
		globalx = xPos;
		globaly = yPos;
	}
	
	public void render(){		
		if (animationState == 1){
			if (dirMoving == DOWN){
				pixelData = sheet.getPlayerData(16, 0);
			}
			else if (dirMoving == RIGHT){
				pixelData = sheet.getPlayerData(16, 32);
			}
			else if (dirMoving == LEFT){
				pixelData = sheet.getPlayerData(16, 80);
			}
			else if (dirMoving == UP){
				pixelData = sheet.getPlayerData(16, 96);
			}
		}
		else if (animationState == 2){
			if (dirMoving == DOWN){
				pixelData = sheet.getPlayerData(16, 16);
			}
			else if (dirMoving == RIGHT){
				pixelData = sheet.getPlayerData(16, 48);
			}
			else if (dirMoving == LEFT){
				pixelData = sheet.getPlayerData(16, 64);
			}
			else if (dirMoving == UP){
				pixelData = sheet.getPlayerData(16, 112);
			}
		}
		
		if(playerHP <= 0){
			pixelData[0][9] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][8] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][7] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][6] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][5] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][4] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][3] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][2] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][1] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][0] = Colours.getColour(255, 255, 255, 255);
		}	
		else if (playerHP <= 10){
			pixelData[0][9] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][8] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][7] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][6] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][5] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][4] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][3] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][2] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][1] = Colours.getColour(255, 255, 255, 255);			
		}
		else if (playerHP <= 20){
			pixelData[0][9] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][8] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][7] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][6] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][5] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][4] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][3] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][2] = Colours.getColour(255, 255, 255, 255);			
		}
		else if (playerHP <= 30){
			pixelData[0][9] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][8] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][7] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][6] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][5] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][4] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][3] = Colours.getColour(255, 255, 255, 255);			
		}
		else if (playerHP <= 40){
			pixelData[0][9] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][8] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][7] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][6] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][5] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][4] = Colours.getColour(255, 255, 255, 255);			
		}
		else if (playerHP <= 50){
			pixelData[0][9] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][8] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][7] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][6] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][5] = Colours.getColour(255, 255, 255, 255);			
		}
		else if (playerHP <= 60){
			pixelData[0][9] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][8] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][7] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][6] = Colours.getColour(255, 255, 255, 255);			
		}
		else if (playerHP <= 70){
			pixelData[0][9] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][8] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][7] = Colours.getColour(255, 255, 255, 255);			
		}	
		else if (playerHP <= 80){
			pixelData[0][9] = Colours.getColour(255, 255, 255, 255);
			pixelData[0][8] = Colours.getColour(255, 255, 255, 255);			
		}
		else if (playerHP <= 90){
			pixelData[0][9] = Colours.getColour(255, 255, 255, 255);			
		}
		
		
		
	}
	
	public void tick(){
		if (isMoving){
			if (System.currentTimeMillis() - animationTimer >= 250){
				animationTimer = System.currentTimeMillis();
				if (animationState == 1) animationState = 2;
				else if (animationState == 2) animationState = 1;				
			}
		}
		//collision testing
		//right
		if ((level.tilesFromLvl[(globaly/8)%15][((globalx+10)/8)%20].isSolid) || (level.tilesFromLvl[((globaly+8)/8)%15][((globalx+10)/8)%20].isSolid) || (level.tilesFromLvl[((globaly+15)/8)%15][((globalx+10)/8)%20].isSolid)){
			hasCollidedRight = true;
		}
		else hasCollidedRight = false;
		//left
		if (level.tilesFromLvl[(globaly/8)%15][((globalx-1)/8)%20].isSolid || level.tilesFromLvl[((globaly+8)/8)%15][((globalx-1)/8)%20].isSolid || level.tilesFromLvl[((globaly+15)/8)%15][((globalx-1)/8)%20].isSolid){
			hasCollidedLeft = true;
		}
		else hasCollidedLeft = false;
		//up
		if (level.tilesFromLvl[((globaly-1)/8)%15][((globalx)/8)%20].isSolid || level.tilesFromLvl[((globaly-1)/8)%15][((globalx+5)/8)%20].isSolid || level.tilesFromLvl[((globaly-1)/8)%15][((globalx+9)/8)%20].isSolid){
			hasCollidedUp = true;
		}
		else hasCollidedUp = false;
		//down
		if(level.tilesFromLvl[((globaly+16)/8)%15][((globalx)/8)%20].isSolid || level.tilesFromLvl[((globaly+16)/8)%15][((globalx+5)/8)%20].isSolid || level.tilesFromLvl[((globaly+16)/8)%15][((globalx+9)/8)%20].isSolid){
			hasCollidedDown = true;
		}
		else hasCollidedDown = false;
				
		//damage testing
		
		if ((level.tilesFromLvl[(globaly/8)%15][((globalx)/8)%20].isDamage)){
			playerHP -= 0.5;
		}
		
	}

}
