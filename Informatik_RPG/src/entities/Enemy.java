package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.EntityHandler;
import main.GifContainer;
import main.ID;

public abstract class Enemy extends Entity {

	protected int exp = 0, hp = 0, atk = 0, def = 0;
	
	protected GifContainer[] idleAnimations;
	protected GifContainer[] deathAnimations;
	protected GifContainer[] walkingAnimations;
	protected GifContainer[] attackAnimations;
	
	protected GifContainer activeAnimation;
	
	protected HpBar hpBar;
	
	private Rectangle[] attackHitboxes;
	
	private boolean facingDown = false, facingUp = false, facingLeft = false, facingRight = false;
	
	private boolean animationsSet = false, moving = false;
	protected boolean dead = false;
	
	private int width, height;
	
	public Enemy(int x, int y, int width, int height, EntityHandler entityHandler) {
		super(x, y, entityHandler, ID.Enemy);
		this.width = width;
		this.height = height;
		
		hpBar = new HpBar(x, y, entityHandler, ID.HpBar);
		entityHandler.addEntity(hpBar);
	}
	
	public void tick() {
		hpBar.updateHpBar(x, y, hp);
		moving = false;
		
		// �berpr�fe ob Enemy tot ist
		if(hp <= 0) {
			die();
		} else if (!moving) {
			stayStill();
		}
	}
	
	public void render(Graphics g) {
		if(animationsSet)
			g.drawImage(activeAnimation.getGif(), x, y, width, height, null);
	}
	
	//gibt an wie weit der Enemy schauen kann...
	abstract Rectangle viewDistance();
	
	protected void attack() {
		if(!dead) {
			Player player = (Player) entityHandler.getEntityById(ID.Player)[0];
			if(facingDown) activeAnimation = attackAnimations[0];
			else if (facingUp) activeAnimation = attackAnimations[1];
			else if (facingLeft) activeAnimation = attackAnimations[2];
			else if (facingRight) activeAnimation = attackAnimations[3];
			
			if((!activeAnimation.isCounting()) || (activeAnimation.isCounting() && activeAnimation.getLoopCount() >= 1)) {
				if(hp>0)player.defend(atk);
				activeAnimation.startLoopCount();
			}
		}
	}

	public void defend(int atk) {
		hpBar.setVisible();
		if(def<=0) this.hp-= atk;
		else this.hp-= atk-def;
		if(hp < 0)
			this.hp = 0;
	}
	
	public void die() {
		// �ndere die animation und lasse sie EINMAL durchlaufen --> hierf�r wird die getLoopCount()-Methode
		// des GifContainers benutzt.
		dead = true;
		velX = velY = 0;
		activeAnimation = deathAnimations[0];
		if(!deathAnimations[0].isCounting())
			deathAnimations[0].startLoopCount();
		if(deathAnimations[0].getLoopCount() >= 1) {
			entityHandler.removeEntity(hpBar);
			entityHandler.removeEntity(this);
		}
	}
	
	//Legt fest in welche Richtung sich der Enemy bewegen soll:
	protected void walkDown() {
		faceDown();
		if(!incomingCollision(velX, velY)) {
			activeAnimation = walkingAnimations[0];
			y+=velY;
			moving = true;
		}
	}
	protected void walkUp() {
		faceUp();
		if(!incomingCollision(velX, -velY)) {
			activeAnimation = walkingAnimations[1];
			y-=velY;
			moving = true;
		}
	}
	protected void walkLeft() {
		faceLeft();
		if(!incomingCollision(velX, velY)) {
			activeAnimation = walkingAnimations[2];
			x-=velX;
			moving = true;
		}
	}
	protected void walkRight() {
		faceRight();
		if(!incomingCollision(-velX, velY)) {
			activeAnimation = walkingAnimations[3];
			x+=velX;
			moving = true;
		}
	}
	
	protected void stayStill() {
		if(facingDown) activeAnimation = idleAnimations[0];
		else if (facingUp) activeAnimation = idleAnimations[1];
		else if (facingLeft) activeAnimation = idleAnimations[2];
		else if (facingRight) activeAnimation = idleAnimations[3];
	}
	
	//l�sst den Enemy in die Richtung des Spielers laufen.
	protected void trackPlayer() {
		if(!dead) {
			Player player = (Player) entityHandler.getEntityById(ID.Player)[0];
			if(this.viewDistance() != null && player.getGroundBounds().intersects(this.viewDistance())) {
				int distanceX = this.x - player.getX();
				int distanceY = this.y - player.getY();
				if(Math.abs(distanceX) > Math.abs(distanceY)) {
					if(distanceX < 0) walkRight();
					else if(distanceX > 0) walkLeft();
				} else {
					if(distanceY < 0) walkDown();
					else if(distanceY > 0) walkUp();
				}
			}
		}
	}
	
	private boolean incomingCollision(double xVel, double yVel) {
		Rectangle newGroundBounds = new Rectangle((int)(this.getGroundBounds().x+xVel), (int)(this.getGroundBounds().y+yVel), this.getGroundBounds().width, this.getGroundBounds().height);
		for(Entity entity : entityHandler.getEntities()) {
			if(entity.getGroundBounds() != null && entity != this && entity.getGroundBounds().intersects(newGroundBounds)) 
				return true;
		}
		return false;
	}
	
	//legt die blickrichtung fest:
	protected void faceDown() {
		facingDown = true;
		facingUp = false;
		facingLeft = false;
		facingRight = false;
	}
	protected void faceUp() {
		facingDown = false;
		facingUp = true;
		facingLeft = false;
		facingRight = false;
	}
	protected void faceLeft() {
		facingDown = false;
		facingUp = false;
		facingLeft = true;
		facingRight = false;
	}
	protected void faceRight() {
		facingDown = false;
		facingUp = false;
		facingLeft = false;
		facingRight = true;
	}
	
	//Getter & Setter:
	public int getExp() {
		return exp;
	}
	
	public void setAttackHitboxes(Rectangle[] hitboxes) {
		this.attackHitboxes = new Rectangle[hitboxes.length];
		for(int i = 0; i < hitboxes.length; i++)
			attackHitboxes[i] = hitboxes[i];
	}
	
	protected void setAnimations(GifContainer[][] animations) {
		this.idleAnimations = animations[0];
		this.deathAnimations = animations[1];
		this.walkingAnimations = animations[2];
		this.attackAnimations = animations[3];
		activeAnimation = idleAnimations[0];
		facingDown = true;
		animationsSet = true;
	}
	
	// gibt die aktuelle, von der blickrichtung abh�ngige, AtkHitbox:
	protected Rectangle getCurrentAtkHitbox() {
		Rectangle tempBox;
		if(facingDown) tempBox = attackHitboxes[0];
		else if (facingUp) tempBox = attackHitboxes[1];
		else if (facingLeft) tempBox = attackHitboxes[2];
		else tempBox = attackHitboxes[3];
		return new Rectangle(x+tempBox.x, y+tempBox.y, tempBox.width, tempBox.height);
	}
}
