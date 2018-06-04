import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Seeds {
	
	
	final int SPRITE_WIDTH = 48; 
	final int SPRITE_HEIGHT = 48;
	
	protected Image closed;
	protected Image opened;
	//protected Image currentState ;
	
	public Seeds(double currentX, double currentY) {
		this.currentX = currentX;
		this.currentY = currentY;
		
		
		try {
			this.closed = ImageIO.read(new File("res/seeds-closed.png"));
		} catch (IOException e) {
		}
		
	}
	protected double currentX = 0;
	protected double currentY = 0;
	
	//PIXELS PER SECOND PER SECOND 
	
	public double getCurrentX() {
		return currentX;
	}

	public void setCurrentX(double currentX) {
		this.currentX = currentX;
	}

	public double getCurrentY() {
		return currentY;
	}

	public void setCurrentY(double currentY) {
		this.currentY = currentY;
	}

	

	public long getHeight() {
		return SPRITE_HEIGHT;
	}

	public long getWidth() {
		return SPRITE_WIDTH;
	}

	public Image getImage() {
		return closed;
	
	}
	
	public void open() {
		closed = opened;
	
	}
}
