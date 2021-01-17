package entities;

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
		
	private Image activeImage = idleAnimations[1].getGif();
	
	private int hp = 10, atk = 3, def = 5;
	
	public Player(int x, int y, EntityHandler entityHandler, KeyHandler keyHandler) {
		super(x, y, entityHandler, ID.Player);
		this.keyHandler = keyHandler;
		velX = 1;
		velY = 1;
	}

	// Entity Methoden:
	@Override
	public void tick() {
		movement();
		attack();
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(activeImage, x, y, 150, 150, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 150, 150);
	}
	
	// berechnet die neue Position und legt die aktive Animation fest:
	private void attack() {
		if(keyHandler.isEnter()) {
			
			Enemy[] defendingEnemies = entityHandler.getInterceptingEnemies(new Rectangle(x, y, 100, 100));
			
			for(Enemy tempEnemy : defendingEnemies) {
				tempEnemy.defend(atk);
			}
		}
	}
	
	//berechnet die Bewegungen (x,y-Koordinaten) und legt die aktuelle Animation fest
	private void movement() {
		if(keyHandler.isW()) {
			y -= velY;
			activeImage = walkingAnimations[3].getGif();
		} else if(keyHandler.isS()) {
			y += velY;
			activeImage = walkingAnimations[2].getGif();
		} else if(keyHandler.isA()) {
			x -= velX;
			activeImage = walkingAnimations[0].getGif();
		} else if(keyHandler.isD()) {
			x += velX;
			activeImage = walkingAnimations[1].getGif();
		} else {
			
			if(activeImage == walkingAnimations[3].getGif())
				activeImage = idleAnimations[3].getGif();
			else if(activeImage == walkingAnimations[0].getGif())
				activeImage = idleAnimations[0].getGif();
			else if(activeImage == walkingAnimations[2].getGif())
				activeImage = idleAnimations[2].getGif();
			else if(activeImage == walkingAnimations[1].getGif())
				activeImage = idleAnimations[1].getGif();
		}
	}
	
	
	// Getters & Setters:
	public ID getId() {
		return id;
	}
}
