import java.net.InetAddress;

public class NetworkPlayer {

	final int UP = 1;
	final int RIGHT = 2;
	final int DOWN = 3; 
	final int LEFT = 4;
	
	private int xPos;
	private int yPos;
	private int animationState;
	private int dirMoving;
	InetAddress ipAddress; 
	
	
	public NetworkPlayer(SpriteSheet sheet, int xPos, int yPos, int animationState, int dirMoving, int playerHP, InetAddress ipAddress) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.animationState = animationState;
		this.dirMoving = dirMoving;
		this.ipAddress = ipAddress;
	}
	
	public void render(){
		
	}

}
