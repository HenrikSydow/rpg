package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
	
	// x und y Koordinate der Entity auf dem Canvas; x und y Geschwindigkeit
	protected int x, y;
	protected double velX = 0, velY = 0;
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// updated die position etc. :
	abstract public void tick();
	
	// zeichnet die Entity auf dem Canvas:
	abstract public void render(Graphics g);
	
	// gibt die größe der Entity zurück (collision-detection möglich --> Rectangle.intersects()?):
	abstract public Rectangle getBounds();
	
}
