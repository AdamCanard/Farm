import java.awt.Image;

public class Plant {

	protected Image stage1;
	protected Image stage2;
	protected Image stage3;
	protected Image stage4;
	protected Image currentstage;
	protected double currentX;
	protected double currentY;
	protected int stage;
	protected boolean isPlanted;
	
	
	public Plant(int currentX, int currentY, int stage) {
		this.currentX = currentX;
		this.currentY = currentY;
		this.stage = stage;
		this.isPlanted = false;
	}


	public Image getImage() {
		if(stage == 0){
			
		}else if(stage == 1){
			
		}else if(stage == 2){
			
		}else{
			
		}
		
		return null;
	}


	public int getCurrentX() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getCurrentY() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}


	

}
