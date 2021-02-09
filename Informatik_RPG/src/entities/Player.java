package entities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

import main.EntityHandler;
import main.GifContainer;
import main.ID;
import main.KeyHandler;

public class Player extends Entity{
	
	private ID id = ID.Player;
	
	private KeyHandler keyHandler;
	
	private static Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	private static int walkingMs = 96, standingMs = 12, attackMs = 72;
	
	private static GifContainer[] idleAnimations = {
		new GifContainer(toolkit.createImage("res\\player\\standLeft.gif"), standingMs),
		new GifContainer(toolkit.createImage("res\\player\\standRight.gif"), standingMs),
		new GifContainer(toolkit.createImage("res\\player\\standDown.gif"), standingMs),
		new GifContainer(toolkit.createImage("res\\player\\standUp.gif"), standingMs)
	};
	
	private static GifContainer[] walkingAnimations = {
		new GifContainer(toolkit.createImage("res\\player\\walkLeft.gif"), walkingMs),
		new GifContainer(toolkit.createImage("res\\player\\walkRight.gif"), walkingMs),
		new GifContainer(toolkit.createImage("res\\player\\walkDown.gif"), walkingMs),
		new GifContainer(toolkit.createImage("res\\player\\walkUp.gif"), walkingMs)
	};
	
	private static GifContainer[] attackingAnimations = {
		new GifContainer(toolkit.createImage("res\\player\\swordSwingLeft.gif"), attackMs),
		new GifContainer(toolkit.createImage("res\\player\\swordSwingRight.gif"), attackMs),
		new GifContainer(toolkit.createImage("res\\player\\swordSwingDown.gif"), attackMs),
		new GifContainer(toolkit.createImage("res\\player\\swordSwingUp.gif"), attackMs)
	};
		
	private GifContainer activeImage;
	
	private boolean facingUp = false, facingDown = false, facingLeft = false, facingRight = false;
	private boolean walking = false;
	private boolean attacking = false;
	
	private HpBar hpBar;
	private int lvl = 1, exp = 0, hp = 25, atk = 5, def = 5;
	private int expForLvlUp = 100;
	private int walkingSpeed = 1, runningSpeed = 2;
	
	//----------------------------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------------------------
	
	public Player(int x, int y, EntityHandler entityHandler, KeyHandler keyHandler) {
		super(x, y, entityHandler, ID.Player);
		this.keyHandler = keyHandler;
		
		velX = velY = 1;
		
		facingRight = true;
		activeImage = idleAnimations[1];
		
		hpBar = new HpBar(x, y, entityHandler, ID.HpBar);
		hpBar.setBarColorGreen();
		hpBar.setVisible();
		entityHandler.addEntity(hpBar);
	}

	@Override
	public void tick() {
		lvlUp();
		hpBar.updateHPBar(x, y, hp, exp, lvl);
		prepareMovements();
		
		if(attacking)
			attack();
		else if(walking)
			walk();
		else
			standStill();
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(activeImage.getGif(), x, y, 150, 150, null);
		
		/*
		g.setColor(Color.white);
		g.drawString("X: " + x + " Y:" + y, x, y);
		g.setColor(Color.orange);
		g.drawRect(x+50, y+70, 50, 75);
		g.setColor(Color.red);
		g.drawRect(x+45, y+120, 60, 30);*/
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x+50, y+70, 50, 75);
	}
	
	@Override
	public Rectangle getGroundBounds() {
		return new Rectangle(x+45, y+120, 60, 30);
	}
	
	private void lvlUp() {
		if(exp >= expForLvlUp) {
			lvl++;
			hp+=3;
			atk+=2;
			def+=2;
			exp-=expForLvlUp;
			expForLvlUp+=2;
			lvlUp();
		}
	}
	
	// berechnet die neue Position und legt die aktive Animation fest:
	private void attack() {
		Enemy[] defendingEnemies;
		Dimension attackSize = new Dimension(30, 80);
		
		if(facingUp) {
			defendingEnemies = entityHandler.getInterceptingEnemies(new Rectangle(x+60, y+30, attackSize.width, attackSize.height));
			activeImage = attackingAnimations[3];
		} else if(facingDown) {
			defendingEnemies = entityHandler.getInterceptingEnemies(new Rectangle(x+60, y+attackSize.height, attackSize.width, attackSize.height));
			activeImage = attackingAnimations[2];
		} else if(facingRight) {
			defendingEnemies = entityHandler.getInterceptingEnemies(new Rectangle(x+70, y+70, attackSize.height, attackSize.width));
			activeImage = attackingAnimations[1];
		} else {
			defendingEnemies = entityHandler.getInterceptingEnemies(new Rectangle(x, y+70, attackSize.height, attackSize.width));
			activeImage = attackingAnimations[0];
		}
		
		//Lässt den Spieler einmal pro gif-Animations-loop angreifen --> Geschwindigkeit an gif-dauer gekoppelt
		if((!activeImage.isCounting()) || (activeImage.isCounting() && activeImage.getLoopCount() >= 1)) {
			for(Enemy tempEnemy : defendingEnemies) {
				tempEnemy.defend(atk);
				activeImage.startLoopCount();
			}
		}
		
		attacking = false;
	}
	
	public void defend(int atk) {
		this.hp-= atk-def;
		if(hp < 0)
			this.hp = 0;
	}
	
	//legt die Richting fest, in welche der Spieler schaut und bestimmt welche Aktionen ausgeführt werden sollen:
	private void prepareMovements() {
			
		if(keyHandler.isEnter()) {
			attacking = true;
		} else if(keyHandler.isS()) {
			faceDown();
			walking = true;
		} else if(keyHandler.isA()) {
			faceLeft();
			walking = true;
		} else if(keyHandler.isD()) {
			faceRight();
			walking = true;
		} else if(keyHandler.isW()) {
			faceUp();
			walking = true;
		} else {
			walking = false;
		}
	}
	
	// Der Spieler bleibt stehen und schaut in die Richtung, welche von facingUp etc. angegeben ist (siehe faceLeft() & faceRight(), etc.)
	private void standStill() {
		if(facingUp)
			activeImage = idleAnimations[3];
		else if(facingLeft)
			activeImage = idleAnimations[0];
		else if(facingDown)
			activeImage = idleAnimations[2];
		else if(facingRight)
			activeImage = idleAnimations[1];
	}
	
	// bewegt den Spieler, legt die Geschwindigkeit fest und lädt die passende Animation in die "activeImage"-Variable:
	private void walk() {
		if(keyHandler.isShift())
			velX = velY = runningSpeed;
		else
			velX = velY = walkingSpeed;
		
		if(facingRight) {
			if(!incomingCollision(velX,0)) {
				activeImage = walkingAnimations[1];
				x+=velX;
			}
		} else if(facingLeft) {
			if(!incomingCollision(-velX,0)) {
				activeImage = walkingAnimations[0];
				x-=velX;
			}
		} else if(facingUp) {
			if(!incomingCollision(0,-velY)) {
				activeImage = walkingAnimations[3];
				y-=velY;
			}
		} else if(facingDown) {
			if(!incomingCollision(0, velY)) {
				activeImage = walkingAnimations[2];
				y+=velY;
			}
		}
	}
	
	//gibt true zurück, wenn der Spieler bei nächster bewegung mit einer entity (groundbounds.width/height > 0) kollidieren würde
	private boolean incomingCollision(double xVel, double yVel) {
		Rectangle newGroundBounds = new Rectangle((int)(this.getGroundBounds().x+xVel), (int)(this.getGroundBounds().y+yVel), this.getGroundBounds().width, this.getGroundBounds().height);
		for(Entity entity : entityHandler.getEntities()) {
			if(entity.getGroundBounds() != null && entity.id != ID.Player && entity.getGroundBounds().intersects(newGroundBounds)) 
				return true;
		}
		return false;
	}
	
	// mit faceLeft etc. lässt sich die Richtung festlegen, in welche der Spieler schauen soll
	private void faceLeft() {
		facingLeft = true;
		facingRight = false;
		facingUp = false;
		facingDown = false;
	}
	
	private void faceRight() {
		facingLeft = false;
		facingRight = true;
		facingUp = false;
		facingDown = false;
	}
	
	private void faceUp() {
		facingLeft = false;
		facingRight = false;
		facingUp = true;
		facingDown = false;
	}
	
	private void faceDown() {
		facingLeft = false;
		facingRight = false;
		facingUp = false;
		facingDown = true;
	}
	
	//leveling:
	public void receiveExp(int exp) {
		this.exp += exp;
	}
	
	// Getters & Setters:
	public ID getId() {
		return id;
	}
}
