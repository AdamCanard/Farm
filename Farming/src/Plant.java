import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Plant {

	protected Image stage1;
	protected Image stage2;
	protected Image stage3;
	protected Image stage4;
	protected Image grown;
	protected Image currentStage;
	protected double currentX;
	protected double currentY;
	protected int stage; 
	protected boolean isPlanted;
	protected boolean harvestable;
	final int SPRITE_WIDTH = 50; 
	final int SPRITE_HEIGHT = 50;
	double setTime;
	
	public Plant(int currentX, int currentY, int stage, boolean harvestable) {
		this.currentX = currentX;
		this.currentY = currentY;
		this.stage = stage;
		this.isPlanted = false;
		this.harvestable = harvestable;
		this.setTime = System.currentTimeMillis() / 1000;
		try {
			this.stage1 = ImageIO.read(new File("res/ground-seed-stage1.png"));
		} catch (IOException e) {
		}

		try {
			this.stage2 = ImageIO.read(new File("res/ground-seed-stage2.png"));
		} catch (IOException e) {
		}
		
		try {
			this.stage3 = ImageIO.read(new File("res/ground-seed-stage3.png"));
		} catch (IOException e) {
		}

		try {
			this.stage4 = ImageIO.read(new File("res/ground-seed-stage4.png"));
		} catch (IOException e) {
		}
		
		try {
			this.grown = ImageIO.read(new File("res/fully-grown-plant.png"));
		} catch (IOException e) {
		}
	}


	public Image getImage() {
		stageUp(stage);
		
		if(currentStage == grown){
			return currentStage;
		}else if(stage == 0){
			currentStage = stage1;
		}else if(stage == 1){
			currentStage = stage2;
		}else if(stage == 2){
			currentStage = stage3;
		}else if(stage == 3){
			currentStage = stage4;
		}
		
		
		return currentStage;
	}

	public void stageUp(int currentStage){

		if (currentStage == 0){
			new java.util.Timer().schedule( 
				new java.util.TimerTask() {
					@Override
					public void run() {
			           stage = 1;
			           
			           
			        }
				 }, 
				 10000 
			);			
		}else if (currentStage == 1){
			new java.util.Timer().schedule( 
					new java.util.TimerTask() {
						@Override
						public void run() {
				           stage = 2;
				           
				           
				        }
					 }, 
					 10000 
				);			
		}else{
			new java.util.Timer().schedule( 
					new java.util.TimerTask() {
						@Override
						public void run() {
				           stage = 3;
				           
				           growFully();
				           
				        }
					 }, 
					 10000 
				);
			
			
		}
			
		
	}
	

	private void growFully() {
		currentStage = grown;
		harvestable = true;
	}


	public double getCurrentX() {
		return currentX;
	}

	public double getCurrentY() {
		return currentX;
	}

	public int getWidth() {		
		return SPRITE_WIDTH;
	}

	public int getHeight() {
		return SPRITE_HEIGHT;
	}


	

}
