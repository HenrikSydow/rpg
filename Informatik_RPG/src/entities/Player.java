package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import main.EntityHandler;
import main.ID;
import main.KeyHandler;

public class Player extends Entity{
	
	private ID id = ID.Player;
	
	private KeyHandler keyHandler;
	
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	private Image walkLeftImg = toolkit.createImage("res\\player\\walkLeft.gif");
	private Image walkRightImg = toolkit.createImage("res\\player\\walkRight.gif");
	private Image walkDownImg = toolkit.createImage("res\\player\\walkDown.gif");
	private Image walkUpImg = toolkit.createImage("res\\player\\walkUp.gif");

	private Image standLeftImg = toolkit.createImage("res\\player\\standLeft.gif");
	private Image standRightImg = toolkit.createImage("res\\player\\standRight.gif");
	private Image standDownImg = toolkit.createImage("res\\player\\standDown.gif");
	private Image standUpImg = toolkit.createImage("res\\player\\standUp.gif");
	
	private Image swordSwingUpImg = toolkit.createImage("res\\player\\swordSwingUp.gif");
	private Image swordSwingLeftImg = toolkit.createImage("res\\player\\swordSwingLeft.gif");
	private Image swordSwingRightImg = toolkit.createImage("res\\player\\swordSwingRight.gif");
	private Image swordSwingDownImg = toolkit.createImage("res\\player\\swordSwingDown.gif");
	
	private Image activeImage = standRightImg;
	
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
				System.out.println("attacked / defending: " + tempEnemy);
				tempEnemy.defend(atk);
			}
		}
	}
	
	//berechnet die Bewegungen (x,y-Koordinaten) und legt die aktuelle Animation fest
	private void movement() {
		if(keyHandler.isW()) {
			y -= velY;
			activeImage = walkUpImg;
		} else if(keyHandler.isS()) {
			y += velY;
			activeImage = walkDownImg;
		} else if(keyHandler.isA()) {
			x -= velX;
			activeImage = walkLeftImg;
		} else if(keyHandler.isD()) {
			x += velX;
			activeImage = walkRightImg;
		} else {
			
			if(activeImage == walkUpImg)
				activeImage = standUpImg;
			else if(activeImage == walkLeftImg)
				activeImage = standLeftImg;
			else if(activeImage == walkDownImg)
				activeImage = standDownImg;
			else if(activeImage == walkRightImg)
				activeImage = standRightImg;
		}
	}
	
	
	// Getters & Setters:
	public ID getId() {
		return id;
	}
}
