package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.ImageLoader;
import main.KeyHandler;

public class Player extends Entity{
	
	private KeyHandler keyHandler;
	private BufferedImage sprite = ImageLoader.loadImgScaled("res\\character_right.png", 0.3);
	
	public Player(int x, int y, KeyHandler keyHandler) {
		super(x, y);
		this.keyHandler = keyHandler;
		velX = 1;
		velY = 1;
	}

	@Override
	public void tick() {
		if(keyHandler.isW())
			y -= velY;
		if(keyHandler.isS())
			y += velY;
		if(keyHandler.isA())
			x -= velX;
		if(keyHandler.isD())
			x += velX;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(sprite, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
