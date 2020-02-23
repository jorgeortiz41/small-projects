package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
public class MarioBro extends Player {

	private boolean hit = false;
	public boolean grabbed =false;

	public MarioBro(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.mariobroSmallWalkRight[0]
				,new Animation(175,Images.mariobroSmallWalkLeft)
				, new Animation(175,Images.mariobroSmallWalkRight)
				, new Animation(150,Images.mariobroBigWalkLeft)
				, new Animation(150,Images.mariobroBigWalkRight)
				, new Animation(115,Images.mariobroBigRunLeft)
				, new Animation(115,Images.mariobroBigRunRight));
		if(isBig){
			this.y-=8;
			this.height+=8;
			setDimension(new Dimension(width, this.height));
		}
	}

	@Override
	public void tick(){
	    if(!grabbed) {
            super.tick();
            if (!this.hit) {
                if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE) && !handler.getKeyManager().up && !handler.getKeyManager().down) {
                    this.jump();
                }

                if (handler.getKeyManager().right && !handler.getKeyManager().up && !handler.getKeyManager().down) {
                    if (handler.getKeyManager().runbutt) {
                        velX = 6;
                        running = true;
                    } else {
                        velX = 3;
                        running = false;
                    }
                    if (facing.equals("Left")) {
                        changeDirrection = true;
                    }
                    facing = "Right";
                    moving = true;
                } else if (handler.getKeyManager().left && !handler.getKeyManager().up && !handler.getKeyManager().down) {
                    if (handler.getKeyManager().runbutt) {
                        velX = -6;
                        running = true;
                    } else {
                        velX = -3;
                        running = false;
                    }
                    if (facing.equals("Right")) {
                        changeDirrection = true;
                    }
                    facing = "Left";
                    moving = true;
                } else {
                    velX = 0;
                    moving = false;
                }
                if (jumping && velY <= 0) {
                    jumping = false;
                    falling = true;
                } else if (jumping) {
                    velY = velY - gravityAcc;
                    y = (int) (y - velY);
                }

                if (falling) {
                    y = (int) (y + velY);
                    velY = velY + gravityAcc;
                }
                x += velX;
            } else {
                this.setX(this.getX() - 30);
                this.setY(this.getY() - 30);
            }
        }
	}

	public void drawMarioBro(Graphics2D g2) {
		if(!grabbed) {
			if (!isBig) {
				if (handler.getKeyManager().up) {
					if (facing.equals("Left")) {
						g2.drawImage(Images.mariobroSmallJumpLeft[2], x, y, width, height, null);
					} else {
						g2.drawImage(Images.mariobroSmallJumpRight[2], x, y, width, height, null);
					}
				} else if (handler.getKeyManager().down) {
					if (facing.equals("Left")) {
						g2.drawImage(Images.mariobroSmallJumpLeft[3], x, y, width, height, null);
					} else {
						g2.drawImage(Images.mariobroSmallJumpRight[3], x, y, width, height, null);
					}
				} else if (!jumping && !falling) {
					if (facing.equals("Left") && moving) {
						g2.drawImage(playerSmallLeftAnimation.getCurrentFrame(), x, y, width, height, null);
					} else if (facing.equals("Right") && moving) {
						g2.drawImage(playerSmallRightAnimation.getCurrentFrame(), x, y, width, height, null);
					}
					if (facing.equals("Left") && !moving) {
						g2.drawImage(Images.mariobroSmallWalkLeft[0], x, y, width, height, null);
					} else if (facing.equals("Right") && !moving) {
						g2.drawImage(Images.mariobroSmallWalkRight[0], x, y, width, height, null);
					}
				} else {
					if (jumping) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.mariobroSmallJumpLeft[0], x, y, width, height, null);
						} else {
							g2.drawImage(Images.mariobroSmallJumpRight[0], x, y, width, height, null);
						}

					} else {
						if (facing.equals("Left")) {
							g2.drawImage(Images.mariobroSmallJumpLeft[1], x, y, width, height, null);
						} else {
							g2.drawImage(Images.mariobroSmallJumpRight[1], x, y, width, height, null);
						}
					}
				}
			} else {
				if (!changeDirrection) {
					if (handler.getKeyManager().up) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.mariobroBigJumpLeft[4], x, y, width, height, null);
						} else {
							g2.drawImage(Images.mariobroBigJumpRight[4], x, y, width, height, null);
						}
					} else if (handler.getKeyManager().down) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.mariobroBigJumpLeft[3], x, y, width, height, null);
						} else {
							g2.drawImage(Images.mariobroBigJumpRight[3], x, y, width, height, null);
						}
					} else if (!jumping && !falling) {
						if (facing.equals("Left") && moving && running) {
							g2.drawImage(playerBigLeftRunAnimation.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Left") && moving && !running) {
							g2.drawImage(playerBigLeftWalkAnimation.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Left") && !moving) {
							g2.drawImage(Images.mariobroBigWalkLeft[0], x, y, width, height, null);
						} else if (facing.equals("Right") && moving && running) {
							g2.drawImage(playerBigRightRunAnimation.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Right") && moving && !running) {
							g2.drawImage(playerBigRightWalkAnimation.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Right") && !moving) {
							g2.drawImage(Images.mariobroBigWalkRight[0], x, y, width, height, null);
						}
					} else {
						if (jumping) {
							if (facing.equals("Left")) {
								g2.drawImage(Images.mariobroBigJumpLeft[0], x, y, width, height, null);
							} else {
								g2.drawImage(Images.mariobroBigJumpRight[0], x, y, width, height, null);
							}

						} else {
							if (facing.equals("Left")) {
								g2.drawImage(Images.mariobroBigJumpLeft[1], x, y, width, height, null);
							} else {
								g2.drawImage(Images.mariobroBigJumpRight[1], x, y, width, height, null);
							}
						}
					}
				} else {
					if (!running) {
						changeDirrection = false;
						changeDirectionCounter = 0;
						drawMarioBro(g2);
					}
					if (facing.equals("Right")) {
						g2.drawImage(Images.mariobroBigJumpRight[4], x, y, width, height, null);
					} else {
						g2.drawImage(Images.mariobroBigJumpLeft[4], x, y, width, height, null);
					}
				}
			}
		}
	}
	public boolean getHit() {
		return this.hit;
	}
	public void setHit(Boolean hit) {
		this.hit = hit;
	}
}
