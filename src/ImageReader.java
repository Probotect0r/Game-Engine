import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 
 */

/**
 * @author Sagar Desai
 *
 */
public class ImageReader {
	
	int width;
	int height;
	int[][] pixelData;

	/**
	 * 
	 */
	public ImageReader(String path) {
		BufferedImage image = null;
		URL url = getClass().getResource(path);
		//File imageFile = new File(url);
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			System.out.println("Image could not be read");
			e.printStackTrace();
		}		
		this.width = image.getWidth();		
		this.height = image.getHeight();
		int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);
	
		

		pixelData = new int[height][width];	
		
		int index = 0;
		for (int row=0; row<height; row++){
			for (int column=0; column<width; column++){
				pixelData[row][column] = pixels[index];
				index++;
			}
		}
		
	}
	
	public int[][] getPixelData(){
		return pixelData;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	

}
