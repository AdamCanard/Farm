import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//improved game by 9001 - chris

public class Farmer {
	
		protected double currentX = 0;
		protected double currentY = 500;
		protected boolean holding;

		final int SPRITE_WIDTH = 55; // sprite.get_width()
		final int SPRITE_HEIGHT = 75; //sprite.get_height()

		protected Image lay;
		protected Image image_left;
		protected Image image_right;
		protected Image image_up;
		protected Image image_down;
		protected Image image_left_seeds;
		protected Image image_right_seeds;
		protected Image image_up_seeds;
		protected Image image_down_seeds;
		protected Image image_left_plant;
		protected Image image_right_plant;
		protected Image image_up_plant;
		protected Image image_down_plant;
		public Image currentState = lay;
		int i = 0;

		public Farmer(double currentX, double currentY, boolean holding) {
			this.currentX = currentX;
			this.currentY = currentY;
			this.holding = holding;
			
			try {
				this.image_left = ImageIO.read(new File("res/image-left.png"));
			} catch (IOException e) {
			}

			try {
				this.image_right = ImageIO.read(new File("res/image-right.png"));
			} catch (IOException e) {
			}
			
			try {
				this.image_down = ImageIO.read(new File("res/image-down.png"));
			} catch (IOException e) {
			}
			
			try {
				this.image_up = ImageIO.read(new File("res/image-up.png"));
			} catch (IOException e) {
			}
			
			try {
				this.image_left_seeds = ImageIO.read(new File("res/image-left-seeds.png"));
			} catch (IOException e) {
			}

			try {
				this.image_right_seeds = ImageIO.read(new File("res/image-right-seeds.png"));
			} catch (IOException e) {
			}
			
			try {
				this.image_down_seeds = ImageIO.read(new File("res/image-down-seeds.png"));
			} catch (IOException e) {
			}
			
			try {
				this.image_up_seeds = ImageIO.read(new File("res/image-up-seeds.png"));
			} catch (IOException e) {
			}
			
			try {
				this.image_left_plant = ImageIO.read(new File("res/image-left-plant.png"));
			} catch (IOException e) {
			}

			try {
				this.image_right_plant = ImageIO.read(new File("res/image-right-plant.png"));
			} catch (IOException e) {
			}
			
			try {
				this.image_down_plant = ImageIO.read(new File("res/image-down-plant.png"));
			} catch (IOException e) {
			}
			
			try {
				this.image_up_plant = ImageIO.read(new File("res/image-up-plant.png"));
			} catch (IOException e) {
			}
			
			try {
				this.lay = ImageIO.read(new File("res/lay.png"));
			} catch (IOException e) {
			}
			
		}

		
		
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
		public Image startPosition(){
			return lay;
		}
		

		public long getHeight() {
			return SPRITE_HEIGHT;
		}

		public long getWidth() {
			return SPRITE_WIDTH;
		}

		public Image getImage() {
			
			if (i == 0){
				i++;
				return lay;
				
			}else{
				return currentState;
			}
			
		
		}
		public void setImage(int moveIndex){
			//START 0
			//LEFT	1
 			//RIGHT 2
			//UP	3
			//DOWN  4
			if(this.holding == true){
				if(moveIndex == 0){
					return;
				}else if (moveIndex == 1) {
					 this.currentState = image_left_seeds;
				}else if (moveIndex == 2){
					this.currentState = image_right_seeds;
				}else if (moveIndex == 3){
					this.currentState = image_up_seeds;
				}else{
					this.currentState = image_down_seeds;
				}
			}else{
				if(moveIndex == 0){
					return;
				}else if (moveIndex == 1) {
					 this.currentState = image_left;
				}else if (moveIndex == 2){
					this.currentState = image_right;
				}else if (moveIndex == 3){
					this.currentState = image_up;
				}else{
					this.currentState = image_down;
				}
			}
		}
		
		  
}