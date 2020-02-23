
package Game.Entities.Dynamics;

import Game.Entities.BaseEntity;
import Game.GameStates.InWorldState;
import Game.World.Walls;
import Game.World.InWorldAreas.InWorldWalls;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BaseDynamicEntity extends BaseEntity {

	public Animation PEnemyIdle;
	public BufferedImage[] frames;
	private Rectangle detector;

	private Random rand;
	private boolean chasingPlayer;
	private int count;
	private int directionMov;
	double chaseSpeed = 1.5;
	boolean canMove = true;
	public String Area;//None for MapState


	public String foundState;

	int speed = 2;
	protected boolean isMoving;


	//Where the player will stand
	protected Rectangle nextArea;
	public String facing = "Down";

	public BaseDynamicEntity(Handler handler, int xPosition, int yPosition,BufferedImage[] animFrames) {
		super(handler, xPosition, yPosition);
		nextArea = new Rectangle();
		PEnemyIdle = new Animation(120, animFrames);
		frames = animFrames;

	}

	// if it moves and stuff then what methods should it have? 


	// OTHER FUNCTIONALITIES THAT A DYNAMIC ENTITY SHOULD HAVE?


	public void tick(){
		super.tick();
		if(handler.getArea().equals(this.Area)) {
			UpdateNextMove();
			checkCollision();


			if (canMove) {
				count++;
				if (count >= 100 + rand.nextInt(350)) {

					directionMov = rand.nextInt(5); // 0 (idle), 1(up), 2(down), 3(left), 4(right)

					count = 0;
				}

				PlayerDetector();

				if (!chasingPlayer) {
					Move();
				} else {
					Chase();
				}
			}
			canMove = true;
		}

	}

	private void checkCollision() {
		if(foundState.equals("MapState")){
			for(Walls w:handler.getWorldManager().getWalls()){
				if(w.intersects(nextArea)) {
					canMove = false;
					switch (directionMov) {
					case 0://idle
					break;
					case 1://down
						this.setYOffset(this.getYOffset() - speed);
						break;
					case 2://up
						this.setYOffset(this.getYOffset() + speed);
						break;

					case 3://left
						this.setXOffset(this.getXOffset() + speed);
						break;

					case 4://right
						this.setXOffset(this.getXOffset() - speed);
						break;
					}
				}
			}
		}else if(foundState.equals("InWorldState")){
			for(InWorldWalls w:InWorldState.currentArea.getWalls()){
				if(w.intersects(nextArea)) {
					canMove = false;
					switch (directionMov) {
					case 1://down
					this.setYOffset(this.getYOffset() - speed);
					break;
					case 2://up
						this.setYOffset(this.getYOffset() + speed);
						break;

					case 3://left
						this.setXOffset(this.getXOffset() + speed);
						break;

					case 4://right
						this.setXOffset(this.getXOffset() - speed);
						break;
					}
				}
			}
		}
	}

	private void UpdateNextMove() {
		if(foundState.equals("MapState")) {
			switch (facing) {
			case "Up":
				nextArea = new Rectangle((int) getXOffset() + handler.getXDisplacement(), (int) getYOffset() + handler.getYDisplacement() - 10, getCollision().width, getCollision().height / 2);

				break;
			case "Down":
				nextArea = new Rectangle((int) getXOffset() + handler.getXDisplacement(), (int) getYOffset() + handler.getYDisplacement() + getCollision().height, getCollision().width, 10);

				break;
			case "Left":
				nextArea = new Rectangle((int) getXOffset() + handler.getXDisplacement() - 10, (int) getYOffset() + handler.getYDisplacement(), 10, getCollision().height);

				break;
			case "Right":
				nextArea = new Rectangle((int) getXOffset() + handler.getXDisplacement() + getCollision().width, (int) getYOffset() + handler.getYDisplacement(), 10, getCollision().height);

				break;
			}
		}else if(foundState.equals("InWorldState")){
			switch (facing) {
			case "Up":
				nextArea = new Rectangle((int) getXOffset() + handler.getXInWorldDisplacement(), (int) getYOffset() + handler.getYInWorldDisplacement() - 10, getCollision().width, getCollision().height / 2);

				break;
			case "Down":
				nextArea = new Rectangle((int) getXOffset() + handler.getXInWorldDisplacement(), (int) getYOffset() + handler.getYInWorldDisplacement() + getCollision().height, getCollision().width, 10);

				break;
			case "Left":
				nextArea = new Rectangle((int) getXOffset() + handler.getXInWorldDisplacement() - 10, (int) getYOffset() + handler.getYInWorldDisplacement(), 10, getCollision().height);

				break;
			case "Right":
				nextArea = new Rectangle((int) getXOffset() + handler.getXInWorldDisplacement() + getCollision().width, (int) getYOffset() + handler.getYInWorldDisplacement(), 10, getCollision().height);

				break;
			}
		}
	}

	protected void Chase() {
		if (this.handler.getEntityManager().getPlayer().getXOffset()+(handler.getEntityManager().getPlayer().getCollision().width/2) > this.getXOffset() && canMove) {
			facing = "Right";
			this.setXOffset(this.getXOffset() + chaseSpeed);
		}
		if (this.handler.getEntityManager().getPlayer().getXOffset()+(handler.getEntityManager().getPlayer().getCollision().width/2) < this.getXOffset() && canMove) {
			facing = "Left";
			this.setXOffset(this.getXOffset() - chaseSpeed);
		}

		if (this.handler.getEntityManager().getPlayer().getYOffset()+(handler.getEntityManager().getPlayer().getCollision().height) < this.getYOffset() && canMove) {
			facing = "Up";
			this.setYOffset(this.getYOffset() - chaseSpeed);
		}

		if (this.handler.getEntityManager().getPlayer().getYOffset()+(handler.getEntityManager().getPlayer().getCollision().height) > this.getYOffset() && canMove) {
			facing = "Down";
			this.setYOffset(this.getYOffset() + chaseSpeed);
		}

	}

	private void Move() {

		switch (directionMov) {
		case 0:
			break;
		case 1:
			facing = "Down";
			this.setYOffset(this.getYOffset() + speed);
			break;

		case 2:
			facing = "Up";
			this.setYOffset(this.getYOffset() - speed);
			break;

		case 3:
			facing = "Left";
			this.setXOffset(this.getXOffset() - speed);
			break;

		case 4:
			facing = "Right";
			this.setXOffset(this.getXOffset() + speed);
			break;
		}
	}




	public BufferedImage getIdle(){
		if(!PEnemyIdle.getCurrentFrame().equals(null)) {
			return PEnemyIdle.getCurrentFrame();
		}else{
			return Images.PEnemyIdle[0];
		}
	}

	private void PlayerDetector() {

		detector = this.getCollision();

		detector.setRect(detector.getX() - detector.getWidth() * 4.5, detector.getY() - detector.getHeight() * 4.5,
				detector.getWidth() * 20, detector.getHeight() * 15);
	}

	public BufferedImage getCurrentAnimationFrame( Animation animDown, Animation animUp, Animation animLeft, Animation animRight, BufferedImage[] front,BufferedImage[] back,BufferedImage[] left,BufferedImage[] right) {
		BufferedImage frame = null;
		if(isMoving) {
			switch (facing) {
			case "Down":
				frame =  animDown.getCurrentFrame();
				break;
			case "Up":
				frame =  animUp.getCurrentFrame();
				break;
			case "Right":
				frame = animRight.getCurrentFrame();
				break;
			case "Left":
				frame = animLeft.getCurrentFrame();
				break;
			}
		}
		else {
			switch (facing) {
			case "Down":
				frame =  front[0];
				break;
			case "Up":
				frame =  back[0];
				break;
			case "Right":
				frame = right[0];
				break;
			case "Left":
				frame = left[0];
				break;
			}
		}
		return frame;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}

