package entities;

import java.awt.Rectangle;
import java.awt.Toolkit;

import main.EntityHandler;
import main.GifContainer;

public class CombatDummy extends Enemy{
	
	// initialisiere alle animationen (siehe Enemy-class für Reihenfolge)
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private GifContainer[] animations = {
			new GifContainer(toolkit.createImage("res\\combat_dummy.gif"), 104),
			new GifContainer(toolkit.createImage("res\\combat_dummy_death.gif"), 160)
	};
	
	public CombatDummy(int x, int y, EntityHandler entityHandler) {
		super(x, y, entityHandler);
		super.setAnimations(animations);
		this.hp = 100;
		this.exp = 50;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 140, 140);
	}
	
	@Override
	public Rectangle getGroundBounds() {
		return new Rectangle(x+50, y+100, 40, 40);
	}

}
