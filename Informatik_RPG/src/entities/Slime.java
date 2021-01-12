package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

import main.ImageLoader;

public class Slime extends Enemy{

	private BufferedImage sprite = ImageLoader.loadImgScaled("res\\slime_single.png", 3);
	
	private int velX = 2, velY = 1;
	
	public Slime(int x, int y) {
		super(x, y);
	}

	@Override
	public void tick() {
		movement();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(sprite, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

	//--------------------------------------------
	
	private void movement() {
		double tempNum = ThreadLocalRandom.current().nextDouble();
		if(tempNum < 0.25)
			x += velX;
		else if(tempNum < 0.5)
			x -= velX;
		else if(tempNum < 0.75)
			y -= velY;
		else if(tempNum < 1)
			y += velY;
	}
}
