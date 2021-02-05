package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.EntityHandler;
import main.ID;

public class HpBar extends Entity{

	private int hp = 0;
	
	private Color barColor = Color.red;
	
	public HpBar(int x, int y, EntityHandler entityHandler, ID id) {
		super(x, y, entityHandler, id);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		if(hp > 0) {
			g.setColor(Color.black);
			g.drawRect(x, y, hp+2, 10);
		}
		
		g.setColor(barColor);
		g.fillRect(x+1, y+1, hp, 8);
	}

	public void updateHpBar(int x, int y, int hp) {
		this.x = x;
		this.y = y;
		this.hp = hp;
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
