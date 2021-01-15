package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import main.EntityHandler;

public class CombatDummy extends Enemy{

	private Image animation = Toolkit.getDefaultToolkit().createImage("res\\combat_dummy.gif");
	
	public CombatDummy(int x, int y, EntityHandler entityHandler) {
		super(x, y, entityHandler);
		this.hp = 100;
	}

	@Override
	public void tick() {
		super.tick();// <-- Enemy überprüft in der eigenen tick()-method, ob die hp <= 0 sind
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(animation, x, y, 140, 140, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 140, 140);
	}

}
