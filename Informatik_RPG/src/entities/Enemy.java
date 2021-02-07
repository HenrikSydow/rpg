package entities;

import java.awt.Color;
import java.awt.Graphics;

import main.EntityHandler;
import main.GifContainer;
import main.ID;

public abstract class Enemy extends Entity{

	protected int exp = 0, hp = 0, atk = 0, def = 0;
	
	protected GifContainer[] animations; // 0:idle, 1:death;
	protected GifContainer activeAnimation;
	
	protected HpBar hpBar;
	
	protected int width, height;
	
	public Enemy(int x, int y, int width, int height, EntityHandler entityHandler) {
		super(x, y, entityHandler, ID.Enemy);
		this.width = width;
		this.height = height;
		
		hpBar = new HpBar(x, y, entityHandler, ID.HpBar);
		entityHandler.addEntity(hpBar);
	}
	
	public void tick() {
		hpBar.updateHpBar(x, y, hp);
		
		// �berpr�fe ob Enemy tot ist
		if(hp <= 0) {
			die();
		}
	}
	
	public void render(Graphics g) {
		if(animations != null)
			g.drawImage(activeAnimation.getGif(), x, y, width, height, null);
		if(getBounds() != null) {
			g.setColor(Color.orange);
			g.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
		}
		if(getGroundBounds() != null) {
			g.setColor(Color.red);
			g.drawRect(getGroundBounds().x, getGroundBounds().y, getGroundBounds().width, getGroundBounds().height);
		}
	}

	public void defend(int atk) {
		hpBar.setVisible();
		this.hp-= atk-def;
		if(hp < 0)
			this.hp = 0;
	}
	
	protected void setAnimations(GifContainer[] animations) {
		this.animations = animations;
		activeAnimation = animations[0];
	}
	
	public void die() {
		// �ndere die animation und lasse sie EINMAL durchlaufen --> hierf�r wird die getLoopCount()-Methode
		// des GifContainers benutzt.
		activeAnimation = animations[1];
		if(!animations[1].isCounting())
			animations[1].startLoopCount();
		if(animations[1].getLoopCount() >= 1) {
			entityHandler.removeEntity(hpBar);
			entityHandler.removeEntity(this);
		}
	}
	
	
	//Getter & Setter:
	public int getExp() {
		return exp;
	}
	
}
