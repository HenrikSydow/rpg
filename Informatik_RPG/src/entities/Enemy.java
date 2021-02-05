package entities;

import java.awt.Font;
import java.awt.Graphics;

import main.EntityHandler;
import main.GifContainer;
import main.ID;

public abstract class Enemy extends Entity{

	protected int hp = 0, atk = 0, def = 0;
	
	protected GifContainer[] animations; // 0:idle, 1:death;
	protected GifContainer activeAnimation;
	
	protected HpBar hpBar;
	
	public Enemy(int x, int y, EntityHandler entityHandler, GifContainer[] animations) {
		super(x, y, entityHandler, ID.Enemy);
		
		this.animations = animations;
		activeAnimation = animations[0];
		
		hpBar = new HpBar(x, y, entityHandler, ID.HpBar);
		entityHandler.addEntity(hpBar);
	}
	
	public void tick() {
		// überprüfe ob Enemy tot ist
		if(hp <= 0) {
			die();
		}
		
		hpBar.updateHpBar(x, y, hp);
	}
	
	public void render(Graphics g) {
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawImage(activeAnimation.getGif(), x, y, 140, 140, null);
	}

	public void defend(int atk) {
		this.hp-= atk-def;
		if(hp < 0)
			this.hp = 0;
	}
	
	public void die() {
		// ändere die animation und lasse sie EINMAL durchlaufen --> hierfür wird die getLoopCount()-Methode
		// des GifContainers benutzt.
		activeAnimation = animations[1];
		if(!animations[1].isCounting())
			animations[1].startLoopCount();
		if(animations[1].getLoopCount() >= 1) {
			entityHandler.removeEntity(hpBar);
			entityHandler.removeEntity(this);
		}
	}
	
}
