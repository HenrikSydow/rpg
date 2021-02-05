package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
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
	
	private int hp = 10, atk = 1, def = 5;
	
	public Player(int x, int y, EntityHandler entityHandler, KeyHandler keyHandler) {
		super(x, y, entityHandler, ID.Player);
		this.keyHandler = keyHandler;
		velX = 1;
		velY = 1;
		facingRight = true;
		activeImage = idleAnimations[1];
	}

	@Override
	public void tick() {
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
		g.setColor(Color.red);
		g.drawRect(x+45, y+110, 60, 50);
		g.drawImage(activeImage.getGif(), x, y, 150, 150, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 150, 150);
	}
	
	@Override
	public Rectangle getTopViewBounds() {
		return new Rectangle(x+45, y+110, 60, 50);
	}
	
	// berechnet die neue Position und legt die aktive Animation fest:
	private void attack() {
		Enemy[] defendingEnemies = entityHandler.getInterceptingEnemies(new Rectangle(x, y, 100, 100));
		
		for(Enemy tempEnemy : defendingEnemies) {
			tempEnemy.defend(atk);
		}
		
		if(facingUp)
			activeImage = attackingAnimations[3];
		else if(facingDown)
			activeImage = attackingAnimations[2];
		else if(facingRight)
			activeImage = attackingAnimations[1];
		else
			activeImage = attackingAnimations[0];
		
		attacking = false;
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
	
	// bewegt den Spieler und lädt die passende Animation in die "activeImage"-Variable:
	private void walk() {
		if(facingRight) {
			activeImage = walkingAnimations[1];
			x+=velX;
		} else if(facingLeft) {
			activeImage = walkingAnimations[0];
			x-=velX;
		} else if(facingUp) {
			activeImage = walkingAnimations[3];
			y-=velY;
		} else if(facingDown) {
			activeImage = walkingAnimations[2];
			y+=velY;
		}
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
	
	
	// Getters & Setters:
	public ID getId() {
		return id;
	}
}
