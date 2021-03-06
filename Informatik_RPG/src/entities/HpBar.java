package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.EntityHandler;
import main.ID;

public class HpBar extends Entity{

	private int hp = 0;
	private int exp = 0;
	private int lvl = 0;
	
	private int positionOffset = 50-hp/2;
	private int lvlOffset = positionOffset-18;
	private int hpBarHeight = 10;
	
	private Font lvlFont = new Font("Arial", Font.BOLD, 11);
	
	private Color barColor = Color.red;
	
	private boolean visible = false;
	
	public HpBar(int x, int y, EntityHandler entityHandler, ID id) {
		super(x, y, entityHandler, id);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		if(visible) {
			//HP:
			if(hp > 0) {
				g.setColor(Color.white);
				g.fillRect(x+positionOffset, y, hp+2, hpBarHeight);
				g.setColor(Color.black);
				g.drawRect(x+positionOffset, y, hp+2, hpBarHeight);
			}
			g.setColor(barColor);
			g.fillRect(x+1+positionOffset, y+1, hp, hpBarHeight-2);
			
			//EXP:
			if(exp > 0) {
				g.setColor(Color.cyan);
				g.fillRect(x+positionOffset-3, y+hpBarHeight+1, exp, 2);
				g.setColor(Color.black);
				g.drawRect(x+positionOffset-3, y+hpBarHeight, exp, 4);
			}
			
			//LVL:
			if(lvl > 0) {
				g.setFont(lvlFont);
				g.setColor(Color.black);
				g.fillOval(x+lvlOffset, y-5, 20, 20);
				g.setColor(Color.white);
				g.drawString(String.valueOf(lvl), x+lvlOffset+3, y+8);
			}
		}
	}

	public void updateHpBar(int x, int y, int hp) {
		this.x = x;
		this.y = y;
		this.hp = hp;
	}
	
	public void updateHPBar(int x, int y, int hp, int exp, int lvl) {
		this.x = x;
		this.y = y;
		this.hp = hp;
		this.exp = exp;
		this.lvl = lvl;
	}
	
	public void setBarColorGreen() {
		barColor = Color.green;
	}
	
	public void setVisible() {
		visible = true;
	}
	
	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	public Rectangle getGroundBounds() {
		return new Rectangle(x+positionOffset, y+120, 0, 0);
	}

}
