package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.EntityHandler;
import main.ID;

public class HpBar extends Entity{

	private int hp = 0;
	private int exp = 0;
	
	private int positionOffset = 50-hp/2;
	private int hpBarHeight = 10;
	
	private Color barColor = Color.red;
	
	public HpBar(int x, int y, EntityHandler entityHandler, ID id) {
		super(x, y, entityHandler, id);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		//HP:
		if(hp > 0) {
			g.setColor(Color.black);
			g.drawRect(x+positionOffset, y, hp+2, hpBarHeight);
		}
		g.setColor(barColor);
		g.fillRect(x+1+positionOffset, y+1, hp, hpBarHeight-2);
		
		//EXP:
		if(exp > 0) {
			g.setColor(Color.cyan);
			g.fillRect(x+positionOffset, y+hpBarHeight+1, exp, 2);
		}
	}

	public void updateHpBar(int x, int y, int hp) {
		this.x = x;
		this.y = y;
		this.hp = hp;
	}
	
	public void updateHPBar(int x, int y, int hp, int exp) {
		this.x = x;
		this.y = y;
		this.hp = hp;
		this.exp = exp;
	}
	
	public void setBarColorGreen() {
		barColor = Color.green;
	}
	
	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	public Rectangle getTopViewBounds() {
		return null;
	}

	@Override
	public Rectangle getGroundBounds() {
		return null;
	}

}
