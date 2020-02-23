package Game.Entities.Dynamics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Game.GameStates.FightState;
import Game.GameStates.InWorldState;
import Game.GameStates.State;
import Game.World.Walls;
import Game.World.InWorldAreas.InWorldWalls;
import Main.GameSetUp;
import Main.Handler;
import Resources.Animation;
import Resources.Images;
import java.awt.font.*;


public class Merchant extends BaseDynamicEntity {
	private Animation animDown, animUp, animLeft, animRight;
	private int animWalkingSpeed = 150;
	private boolean press1 =false;
    private Rectangle Merchant;
    int width, height;
    public String type;
    public String Area;
    public static boolean Done = false; 

    public Merchant(Handler handler, int xPosition, int yPosition, String state,String area,String name,BufferedImage[] animFrames ){
    	super(handler, xPosition, yPosition, null);
    	
    	
        width = 40;
        height = 40;
        speed = 1;//original speed 15
        type="Entity";
        this.setXOffset(xPosition);
        this.setYOffset(yPosition);
		nextArea = new Rectangle();
		Area = area;

        Merchant = new Rectangle();
    }

    @Override
    public void tick() {

   	if (!GameSetUp.LOADING) {
   		
    		super.tick();

		}

    }

	public void render(Graphics g) {
		super.render(g);
		Graphics2D g2 = (Graphics2D) g;
				
		 if(handler.getArea().equals(this.Area)) {
	            if (!Player.checkInWorld) {
	                Merchant = new Rectangle((int) (handler.getXDisplacement() + getXOffset()),
	                        (int) (handler.getYDisplacement() + getYOffset()), 85, 85);

	            } else {
	                Merchant = new Rectangle((int) (handler.getXInWorldDisplacement() + getXOffset()),
	                        (int) (handler.getYInWorldDisplacement() + getYOffset()), 80, 80);

	            }

	            g2.setColor(Color.black); 

	            g.drawImage(Images.zard,Merchant.x-20,Merchant.y-270,150,150, null);
	            }
	            
		
	            if (Merchant.intersects(handler.getEntityManager().getPlayer().getCollision())) {
	            	g.setColor(Color.white);
            		g.fillRect(Merchant.x+150,Merchant.y-250, 95, 40);
            		g.setColor(Color.black);
            		Font newFont = new Font("Dialog",Font.CENTER_BASELINE, 25);
            		g.setFont((newFont));
                	g.drawString("Press E",Merchant.x+150,Merchant.y-220);
	            	
            		
                	
	            	if(handler.getKeyManager().questbutt) {
                		g.setColor(Color.white);
	            		g.fillRect(Merchant.x+150,Merchant.y-250, 700, 40);
	            		g.setColor(Color.black);
	            		Font newFont2 = new Font("Dialog",Font.CENTER_BASELINE, 25);
	            		g.setFont((newFont2));
	            		Player.quest1 = true;
	            		press1 =true;
	            	}
	            		if(press1 == false) {
	                	g.drawString("Defeat one enemy in this town and come back for reward",Merchant.x+150,Merchant.y-220);
	            		}
	                	
	                	else if(handler.getEntityManager().death == false) {
	                		
	                		g.drawString("Defeat one enemy in this town and come back for reward",Merchant.x+150,Merchant.y-220);
	                	}
	                	else if(handler.getEntityManager().death == true
	                			&& Done==false) {
	                		Done = true;
	                	}
	                	else if( Done == true) {
	                		g.fillRect(Merchant.x+150,Merchant.y-250, 700, 40);
		            		g.drawString("Fuck Off",Merchant.x+150,Merchant.y-220);
		            		handler.getEntityManager().getPlayer().setSkill("Freeze"); 
	                	}
	                	
	            	
	            		
	                	
	                
	            } 
	}

	        
    @Override
    public Rectangle getCollision() {
        return Merchant;
    }

}

