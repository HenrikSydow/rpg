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
	private Image faceLeftImg = toolkit.createImage("res\\leftSlash.gif");
	private Image faceRightImg = toolkit.createImage("res\\rightSlash.gif");
	private Image activeImage = faceRightImg;
	
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
	
	// Weitere Methoden:
	private void movement() {
		if(keyHandler.isW())
			y -= velY;
		if(keyHandler.isS())
			y += velY;
		if(keyHandler.isA()) {
			x -= velX;
			activeImage = faceLeftImg;
		}
		if(keyHandler.isD()) {
			x += velX;
			activeImage = faceRightImg;
		}
	}
	
	
	// Getters & Setters:
	public ID getId() {
		return id;
	}
}
