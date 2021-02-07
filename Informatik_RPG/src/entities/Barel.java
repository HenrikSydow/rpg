package entities;

import java.awt.Rectangle;
import java.awt.Toolkit;

import main.EntityHandler;
import main.GifContainer;

public class Barel extends Enemy{
	
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private GifContainer[] animations = {
			new GifContainer(toolkit.createImage("res\\enemies\\barel.gif"), 1),
			new GifContainer(toolkit.createImage("res\\enemies\\barelBreaking.gif"), 60)
	};
	
	public Barel(int x, int y, EntityHandler entityHandler) {
		super(x, y, 200, 200, entityHandler);
		this.setAnimations(animations);
		this.hp = 1;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x+80, y+90, 53, 50);
	}

	@Override
	public Rectangle getGroundBounds() {
		return new Rectangle(x+85, y+120, 42, 25);
	}

}
