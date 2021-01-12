package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.ImageLoader;
import main.KeyHandler;

public class Player extends Entity{
	
	private KeyHandler keyHandler;
	
	private BufferedImage spriteRight = ImageLoader.loadImgScaled("res\\character_right.png", 0.3);
	private BufferedImage spriteLeft = ImageLoader.loadImgScaled("res\\character_left.png", 0.3);
	private BufferedImage activeSprite = spriteRight;
	
	public Player(int x, int y, KeyHandler keyHandler) {
		super(x, y);
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
		g.drawImage(activeSprite, x, y, null);
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
			activeSprite = spriteLeft;
		}
		if(keyHandler.isD()) {
			x += velX;
			activeSprite = spriteRight;
		}
	}
	
}
