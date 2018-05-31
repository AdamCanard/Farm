
	import javax.imageio.ImageIO;
	import javax.swing.*;
	import java.awt.*;
	import java.util.ArrayList;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.io.File;
	import java.io.IOException;
	import java.awt.event.KeyAdapter;
	import java.awt.event.KeyEvent;

	final public class Simulator extends JFrame {
		

	    private JPanel panel = null;
	    private JButton btnPauseRun;
	    private boolean isPaused = false;
		
		final int FRAMES_PER_SECOND = 240;
		final int SCREEN_HEIGHT = 600;
		final int SCREEN_WIDTH = 600;
		final int BARRIER_WIDTH = 20;

		long current_time = 0;								//MILLISECONDS
		long next_refresh_time = 0;							//MILLISECONDS
		long last_refresh_time = 0;
		long minimum_delta_time = 1000 / FRAMES_PER_SECOND;	//MILLISECONDS
		long actual_delta_time = 0;							//MILLISECONDS
		
		private int current_key_pressed = 0;
		
		
	    
	    ArrayList<Rectangle> barriers = null;
	    ArrayList<Rectangle> backgrounds = null;
	    Farmer player = null;
	    Seeds seeds = null; 

	    
	    public Simulator()
	    {
	        super("Farming Simulator");

	        addKeyListener(new KeyAdapter() {
	        	@Override
	        	public void keyPressed(KeyEvent arg0) {
	        		this_keyPressed(arg0);
	        	}
	        	@Override
	        	public void keyReleased(KeyEvent arg0) {
	        		this_keyReleased(arg0);
	        	}
	        });
	        this.setFocusable(true);
	        
	        getContentPane().setBackground(Color.GREEN);
	        Container cp = getContentPane();
	        
	        panel = new DrawPanel();
	        getContentPane().add(panel, BorderLayout.CENTER);
	        getContentPane().setLayout(null);
	        
	      
	        panel.setLayout(null);
	        panel.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
	        setSize(SCREEN_WIDTH + 20, SCREEN_HEIGHT + 36);

		    

		    
		    
		    player = new Farmer(SCREEN_WIDTH / 2 , SCREEN_HEIGHT / 2, false);
		    
		    seeds = new Seeds (SCREEN_WIDTH / 2 , SCREEN_HEIGHT / 1.5);			    	    
	    	barriers = new ArrayList<Rectangle>();
	    	backgrounds = new ArrayList<Rectangle>();
	    	//left barrier
	    	barriers.add(new Rectangle(0,0,BARRIER_WIDTH,SCREEN_HEIGHT));
	    	//right barrier
	    	barriers.add(new Rectangle(SCREEN_WIDTH - BARRIER_WIDTH, 0, BARRIER_WIDTH, SCREEN_HEIGHT));
	    	//top barrier
	    	barriers.add(new Rectangle( 0, 0, SCREEN_WIDTH, BARRIER_WIDTH));
	    	//bottom barrier
	    	barriers.add(new Rectangle( 0, SCREEN_HEIGHT - BARRIER_WIDTH, SCREEN_WIDTH, BARRIER_WIDTH));
	    	//seeds barrier
	    	barriers.add(new Rectangle((SCREEN_WIDTH / 2) - 24, (int) ((SCREEN_HEIGHT / 1.5)), 48, 5));
	    	barriers.add(new Rectangle((SCREEN_WIDTH / 2) - 24, (int) ((SCREEN_HEIGHT / 1.5) - 45), 5, 48));
	    	barriers.add(new Rectangle((SCREEN_WIDTH / 2) - 24, (int) ((SCREEN_HEIGHT / 1.5) - 48), 48, 5));
	    	barriers.add(new Rectangle((SCREEN_WIDTH / 2) + 20, (int) ((SCREEN_HEIGHT / 1.5) - 48), 5, 53));
	    	//pathway
	    	backgrounds.add(new Rectangle(SCREEN_WIDTH/100, SCREEN_HEIGHT/2, SCREEN_WIDTH, 55));
	    }

		public static void main(String[] args)
	    {
			Simulator m = new Simulator();
	    	m.setVisible(true);
	    	
	        Thread loop = new Thread()
	        {
	           public void run()
	           {
	              m.gameLoop();
	           }
	        };
	        loop.start();
	    	
	    }
		
		class DrawPanel extends JPanel {
			
			 public void paintComponent(Graphics g)
		     {
				 
		        //BS way of clearing out the old rectangle to save CPU.
		        g.setColor(getBackground());
		        
		        g.setColor(Color.GREEN);
		        
		        
		       g.setColor(Color.GREEN);
		       
		       
		       
		       g.drawImage(player.getImage(), (int)player.getCurrentX(), (int)player.getCurrentY(), (int)player.getWidth(), (int)player.getHeight(), null);
		        for (Rectangle barrier : barriers) {
		        	g.setColor(Color.GREEN);
		        		g.fillRect((int)barrier.getX(),(int) barrier.getY(), (int)barrier.getWidth(), (int)barrier.getHeight());    
		        	}
		        
		        for (Rectangle background : backgrounds) {
		        	g.setColor(Color.ORANGE);
	        		g.fillRect((int)background.getX(),(int) background.getY(), (int)background.getWidth(), (int)background.getHeight());    
	        	} 	
		        
		        g.drawImage(seeds.getImage(), (int)(seeds.getCurrentX() - (seeds.getWidth()/2)),(int)(seeds.getCurrentY() - seeds.getHeight()), (int)seeds.getWidth(), (int)seeds.getHeight(), null);
		        
		        
		        g.drawImage(player.getImage(), (int)player.getCurrentX(), (int)player.getCurrentY(), (int)player.getWidth(), (int)player.getHeight(), null);
		       
		        
		        
		        
		       
		        
		        //g.setColor(Color.BLACK);
		     }
		}
		
		
	    
		public boolean collision(){
			double X = player.getCurrentX();
			double Y = player.getCurrentY();
			
			X = X + (player.SPRITE_WIDTH/2);
			Y = Y + (player.SPRITE_HEIGHT/2);
			int i = 0;
			for (Rectangle barrier : barriers){
				i++; 
				if (barrier.getMinX() <= X && X <= barrier.getMaxX()){ 
					
					if (barrier.getMinY() <= Y && Y <= barrier.getMaxY()){ 
						isSeeds(i);
						return true;
					}
				}		
				
			}
			return false;
				
			
		}
	    private void isSeeds(int i) {
	    	
			if (i > 4){
				player.holding = true;
			}
			
		}

		private void gameLoop() {
	    	
			while (true) { // main game loop

				//adapted from http://www.java-gaming.org/index.php?topic=24220.0
				last_refresh_time = System.currentTimeMillis();
			    next_refresh_time = current_time + minimum_delta_time;

			    while (current_time < next_refresh_time)
	            {
	               Thread.yield();

	               try {Thread.sleep(1);}
	               catch(Exception e) {} 
	            
	               current_time = System.currentTimeMillis();
	            }
			    
			    //read input
			    handleKeyboardInput();
			    
			    //UPDATE STATE
			    updateTime();
			    
			    
			    //REFRESH
			    this.repaint();

			}
	    }
	    
	    private void updateTime() {

	        current_time = System.currentTimeMillis();
	        actual_delta_time = (isPaused ? 0 : current_time - last_refresh_time);
//		    System.out.println(String.format("%d, %d, %d", last_refresh_time, current_time, actual_delta_time));
		    last_refresh_time = current_time;
	    }
	    
		
		protected void btnPauseRun_mouseClicked(MouseEvent arg0) {
			if (isPaused) {
				isPaused = false;
				this.btnPauseRun.setText("||");
			}
			else {
				isPaused = true;
				this.btnPauseRun.setText(">");
			}
		}
		
		protected void this_keyPressed(KeyEvent arg0) {
			//keep track of which key is currently pressed
			System.out.println("current key = " + Integer.toString(current_key_pressed));
			current_key_pressed = arg0.getKeyCode();
			if (current_key_pressed == 32) {
				btnPauseRun_mouseClicked(null);
			}
		}
		protected void this_keyReleased(KeyEvent arg0) {
			//keep track of which key is currently pressed
			current_key_pressed = 0;
			System.out.println("current key = " + Integer.toString(current_key_pressed));
		}

		private void handleKeyboardInput() {
			//important that we set the velocity only when updating, as key events are not guaranteed to occur synchronously
			
			
			//player.currentY = player.currentY - 15;
			
			
			int moveIndex = 0;
	     
			if (collision() == true){
				switch (current_key_pressed) {		
				case 37:	//LEFT   
					player.currentX = player.currentX + 5;
					
					break;
				case 39:	//RIGHT
					player.currentX = player.currentX - 5;
					
					break;
				case 38:	//UP
					player.currentY = player.currentY + 5;
					
					break;
				case 40:	//Down
					player.currentY = player.currentY - 5;
					
					break;
				default:
					
			
			}
			player.setImage(moveIndex);
				
				return;
			}else{
			switch (current_key_pressed) {		
				case 37:	//LEFT   
					if (player.getCurrentX() > BARRIER_WIDTH + 2) {
						player.setCurrentX(player.getCurrentX() - 2);
						moveIndex = 1;
					}
					
					break;
				case 39:	//RIGHT
					if (player.getCurrentX() <  (SCREEN_WIDTH - BARRIER_WIDTH - 10)) {
						player.setCurrentX(player.getCurrentX() + 2);
						moveIndex = 2;
					}
					
					break;
				case 38:	//UP
					if(player.getCurrentY() < SCREEN_HEIGHT - (BARRIER_WIDTH + 10)){
						player.setCurrentY(player.getCurrentY() - 2);
						moveIndex = 3;
					}
					
					break;
				case 40:	//Down
					if(player.getCurrentY() < SCREEN_HEIGHT){
						player.setCurrentY(player.getCurrentY() + 2);
						moveIndex = 4;
					}
					
					break;
				default:
					
			
			}
			player.setImage(moveIndex);		
			}
		}
		
	}

	



