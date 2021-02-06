package entities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import main.EntityHandler;
import main.ID;

public class House extends Entity{

	private Image houseImg = Toolkit.getDefaultToolkit().createImage("res\\scenery\\house2.png");
	private Dimension size = new Dimension(500, 700);
	
	public House(int x, int y, EntityHandler entityHandler) {
		super(x, y, entityHandler, ID.Building);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(houseImg, x, y, size.width, size.height, null);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	public Rectangle getGroundBounds() {
		return new Rectangle(x+size.width/20, y+size.height/3, size.width-size.width/10, size.height-size.height/3);
	}

}
