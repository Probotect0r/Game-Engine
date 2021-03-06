
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Game extends Canvas implements Runnable{

	private final int SCALE = 4;
	private final int WIDTH = 160;
	private final int HEIGHT = 120;
	private boolean running = false;	
	private int tickCount = 0;
	private final int maxFPS = 250;
	private int renderPointx = -80; 
	private int renderPointy = -60;
	
	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();		//This returns the colour of the pixels in a decimal format of the hex equivalent of the colour
	SpriteSheet spriteSheet;
	Level level;
	LocalPlayer player;
	Screen screen;
	InputHandler input;
	Client client;
	Server server;
	ArrayList <NetworkPlayer> networkPlayers = new ArrayList();
	
	boolean serverIsRunning = false;
	boolean clientIsRunning = false;

	
	public Game() {
		this.setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		this.setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));		
	}
	
	public void start(){
		running = true;
		new Thread(this).start();
	}
	
	public void stop(){
		running = false;
	}
	
	public int[] getPixels(){
		return pixels;
	}
	
	/**
	 * This is run at start-up once and initializes everything. 
	 */
	public void init(){	
		/*
		int choice = JOptionPane.showConfirmDialog(null, "Do you want to run the server?", "Server", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (choice == 0){	//if server is run
			String serverPort = JOptionPane.showInputDialog("What port would you like to run the server on?", "1337");
			server = new Server(Integer.parseInt(serverPort));
			client = new Client("127.0.0.1", Integer.parseInt(serverPort));
			clientIsRunning = false;
			server.startListening();
		}
		else if (choice == 1){	//if server is not run
			int choice2 = JOptionPane.showConfirmDialog(null, "Do you want to connect to a Server?", "Client", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (choice2 == 0){	// if client wants to connect to server
				String ipAddress = JOptionPane.showInputDialog("What is the ip of the server?", "127.0.0.1");
				String serverPort = JOptionPane.showInputDialog("What is the port of the server?", "1337");
				client = new Client(ipAddress, Integer.parseInt(serverPort));
				clientIsRunning = true;
				serverIsRunning = false;
			}		
		}
		*/
		spriteSheet = new SpriteSheet("/SpriteSheet.png");
		level = new Level ("/Level.png", spriteSheet);
		player = new LocalPlayer(spriteSheet, this, level, 32, 32);		
		screen = new Screen(this, spriteSheet, HEIGHT, WIDTH, level, player);
		input = new InputHandler(this, screen);	
		
	}
	
	
	
	/**
	 * converts the 2D array map of the pixels on the screen to the 1D array of the pixels on the screen. 
	 */
	public void convertTo1D(int [][] pixels2D){
		int index = 0;
		for (int row=0; row<HEIGHT; row++){
			for(int column=0; column<WIDTH; column++){
				pixels[index] = pixels2D[row][column];
				index++;
			}
		}
		
	}
	
	/**player.ypos and player.xPos are top left of the character sprite. Player bounds were calculated based on that and the Screen width and height. 
	 * The image is 160*120 larger than the screen (2 extra tiles on right and left and 1.5 extra tiles on top and bottom. 
	 * Screen bounds were calculated based on that.
	 */
	
	public void tick(){	
		//UP
		if ((input.up.isPressed() && player.yPos == 60) && (screen.yOffSet != 12) && !player.hasCollidedUp){ // if the character is still in the middle of the screen, and the screen hasnt reached the very top of map
			player.isMoving = true;
			player.dirMoving = 1;		
			screen.yOffSet++;									// then keep moving screen down
			player.globaly--;
		}
		else if (input.up.isPressed() && player.yPos != 12 && !player.hasCollidedUp){		//if screen has reached very top, then move player up and stop moving screen
			player.isMoving = true;
			player.dirMoving = 1;
			player.yPos--;
			player.globaly--;
		}
		
		//DOWN
		if ((input.down.isPressed() && player.yPos == 60)&&(screen.yOffSet != -12)&& !player.hasCollidedDown){	// if screen has not reached the very bottom of map, and player is still is in middle
			player.isMoving = true;
			player.dirMoving = 3;
			screen.yOffSet--;		// move screen up
			player.globaly++;
		}
		else if (input.down.isPressed() && player.yPos != 92&& !player.hasCollidedDown){	// Else move player down until he reaches the edge of the screen
			player.isMoving = true;
			player.dirMoving = 3;
			player.yPos++;
			player.globaly++;
		}		
		
		//RIGHT
		if ((input.right.isPressed() && player.xPos ==80) && (screen.xOffSet != -16) && !player.hasCollidedRight){	// if screen has not reached to the very right of map, and player is still in middle
			player.isMoving = true;
			player.dirMoving = 2;
			screen.xOffSet--;								// move screen to the left
			player.globalx++;
		}
		else if (input.right.isPressed() && player.xPos != 134  && !player.hasCollidedRight){ // else move player to the right
			player.isMoving = true;
			player.dirMoving = 2;			
			player.xPos++;
			player.globalx++;
		}
		
		//LEFT
		if ((input.left.isPressed() && player.xPos ==80) && (screen.xOffSet != 16) && !player.hasCollidedLeft){ // if screen has not reached the very left of map, and player is in middle
			player.isMoving = true;
			player.dirMoving = 4;
			screen.xOffSet++;									// move screen to the right
			player.globalx--;
		}
		else if (input.left.isPressed() && player.xPos != 16 && !player.hasCollidedLeft){	// else move player to the left
			player.isMoving = true;
			player.dirMoving = 4;
			player.xPos--;
			player.globalx--;
			
		}
		
		if (!input.up.isPressed() && !input.right.isPressed() && !input.down.isPressed() && !input.left.isPressed()){
			player.isMoving = false;
		}
		
		
		level.tick();
		player.tick();		
	}
	
	/**
	 * 
	 */
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if (bs == null){
			createBufferStrategy(3);
			return;
		}
		
		screen.render();
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, renderPointx, renderPointy, getWidth()+160, getHeight()+120, null);
		
		if(networkPlayers.size() != 0)
		for(int i=0; i<networkPlayers.size(); i++){
			
		}
		g.dispose();
		bs.show();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * Main thread of the game. Calls the tick() and render() methods every second. 
	 */
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		init();
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			boolean shouldRender = true;
			
			while(delta >= 1){
				ticks++;
				tick();
				delta -= 1;		
				shouldRender = true;
			}
			
			try{
				Thread.sleep(2);
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
			
			
			if (shouldRender){
				frames++;
				render();
			}
			
			if (System.currentTimeMillis() - lastTimer >= 1000){
				if (clientIsRunning){
					client.sendPosition();
				} 
				
				lastTimer += 1000;
				System.out.println(ticks + " ticks, " + frames + " frames.");
				frames = 0;
				ticks = 0;
			}
		}		
	}
	
	
	

}
