package structures;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import main.EntityHandler;

public class Bench extends Structure{

	public Bench(int x, int y, EntityHandler entityHandler) {
		super(x, y, new Dimension(110, 80), Toolkit.getDefaultToolkit().createImage("res\\scenery\\bench.png"), entityHandler);
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public Rectangle getGroundBounds() {
		return new Rectangle(x, y+40, 110, 30);
	}

}
