package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

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
	
	private Image activeImage = standRightImg;
	
	public Player(int x, int y, KeyHandler keyHandler) {
		super(x, y, ID.Player);
		this.keyHandler = keyHandler;
		velX = 1;
		velY = 1;
	}

	// Entity Methoden:
	@Override
	public void tick() {
		movement();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(activeImage, x, y, 150, 150, null);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}
	
	// berechnet die neue Position und legt die aktive Animation fest:
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
			
			String lastPressed = keyHandler.getLastPressed();
			
			if(lastPressed != null) {
				switch(lastPressed) {
					case "w":
						activeImage = standUpImg;
						break;
					case "a":
						activeImage = standLeftImg;
						break;
					case "s":
						activeImage = standDownImg;
						break;
					case "d":
						activeImage = standRightImg;
						break;
				}
			}
		}
	}
	
	
	// Getters & Setters:
	public ID getId() {
		return id;
	}
}
