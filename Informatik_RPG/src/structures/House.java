package structures;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import main.EntityHandler;

public class House extends Structure{
	
	public House(int x, int y, EntityHandler entityHandler) {
		super(x, y, new Dimension(500, 700), Toolkit.getDefaultToolkit().createImage("res\\scenery\\house2.png"), entityHandler);
	}

	@Override
	public void tick() {
		
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
