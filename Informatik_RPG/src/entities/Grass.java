package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import main.EntityHandler;
import main.ID;

public class Grass extends Entity{

	private int tileSize = 50;
	private Image grassTile = Toolkit.getDefaultToolkit().createImage("res\\scenery\\tile_grass.png");
	private int width, height;
	
	public Grass(int x, int y, int width, int height, EntityHandler entityHandler) {
		super(x, y, entityHandler, ID.Ground);
		this.width = width/tileSize;
		this.height = height/tileSize;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				g.drawImage(grassTile, x*tileSize, y*tileSize, tileSize, tileSize, null);
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	public Rectangle getGroundBounds() {
		return new Rectangle(0,0,0,0);
	}

}
