package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class CombatDummy extends Enemy{

	private Image animation = Toolkit.getDefaultToolkit().createImage("res\\combat_dummy.gif");
	
	public CombatDummy(int x, int y) {
		super(x, y);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(animation, x, y, 140, 140, null);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
