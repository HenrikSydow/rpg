package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.EntityHandler;
import main.ID;

public abstract class Entity {
	
	// x und y Koordinate der Entity auf dem Canvas; x und y Geschwindigkeit
	protected int x, y;
	protected double velX = 0, velY = 0;
	
	protected ID id;
	protected EntityHandler entityHandler;
	
	public Entity(int x, int y, EntityHandler entityHandler, ID id) {
		this.x = x;
		this.y = y;
		this.entityHandler = entityHandler;
		this.id = id;
	}
	
	// updated die position etc. :
	abstract public void tick();
	
	// zeichnet die Entity auf dem Canvas:
	abstract public void render(Graphics g);
	
	// gibt die größe der Entity zurück (collision-detection möglich --> Rectangle.intersects()?):
	abstract public Rectangle getBounds();

	// gibt die Bounds der Entity aus der Vogelperspektive zurück
	abstract public Rectangle getGroundBounds();

	
	
	
	
	//Getters & Setters:
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
}
