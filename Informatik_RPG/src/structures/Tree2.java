package structures;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

import main.EntityHandler;

public class Tree2 extends Structure {

	public Tree2(int x, int y, EntityHandler entityHandler) {
		super(x, y, new Dimension(160, 190), Toolkit.getDefaultToolkit().createImage("res\\scenery\\treeTwo.png"), entityHandler);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public Rectangle getGroundBounds() {
		return new Rectangle(x+30, y+110, 100, 70);
	}
}
