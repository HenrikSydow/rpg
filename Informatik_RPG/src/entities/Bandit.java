package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

import main.EntityHandler;
import main.GifContainer;

public class Bandit extends Enemy {
	
	// initialisiere alle animationen (siehe Enemy-class für Reihenfolge)
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private GifContainer[] animations = {
			new GifContainer(toolkit.createImage("res\\enemies\\banditStandDown.gif"), 104),
			new GifContainer(toolkit.createImage("res\\enemies\\banditStandDown.gif"), 160),
			new GifContainer(toolkit.createImage("res\\enemies\\banditWalkLeft.gif"), 160),
	};
		
	public Bandit(int x, int y, EntityHandler entityHandler) {
		super(x, y, 140, 140, entityHandler);
		this.setAnimations(animations);
		this.hp = 50;
		this.atk = 10;
		this.def = 3;
	}
	
	public void render(Graphics g) {
		super.render(g);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x+45, y+35, 50, 100);
	}

	@Override
	public Rectangle getGroundBounds() {
		return new Rectangle(x+45, y+110, 50, 25);
	}

}
