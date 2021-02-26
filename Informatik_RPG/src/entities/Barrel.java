package entities;

import java.awt.Rectangle;
import java.awt.Toolkit;

import items.Heart;
import main.EntityHandler;
import main.GifContainer;

public class Barrel extends Enemy{
	
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private GifContainer[][] animations = {
			{new GifContainer(toolkit.createImage("res\\enemies\\barrel.gif"), 1)},
			{new GifContainer(toolkit.createImage("res\\enemies\\barrelBreaking.gif"), 60)},
			{},
			{}
	};
	
	public Barrel(int x, int y, EntityHandler entityHandler) {
		super(x, y, 200, 200, entityHandler);
		this.setAnimations(animations);
		this.hp = 1;
	}
	
	public void die() {
		// ändere die animation und lasse sie EINMAL durchlaufen --> hierfür wird die getLoopCount()-Methode
		// des GifContainers benutzt.
		dead = true;
		velX = velY = 0;
		activeAnimation = deathAnimations[0];
		if(!deathAnimations[0].isCounting())
			deathAnimations[0].startLoopCount();
		if(deathAnimations[0].getLoopCount() >= 1) {
			entityHandler.removeEntity(hpBar);
			entityHandler.addEntity(new Heart(x+70, y+100, entityHandler));
			entityHandler.removeEntity(this);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x+80, y+90, 53, 50);
	}

	@Override
	public Rectangle getGroundBounds() {
		return new Rectangle(x+85, y+120, 42, 25);
	}

	@Override
	Rectangle viewDistance() {
		return null;
	}

}
