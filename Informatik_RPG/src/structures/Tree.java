package structures;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import main.EntityHandler;
import structures.Structure;

public class Tree extends Structure{
	
	public Tree(int x, int y, EntityHandler entityHandler) {
		super(x, y, new Dimension(140, 170), Toolkit.getDefaultToolkit().createImage("res\\scenery\\tree.png"), entityHandler);
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
		return new Rectangle(x+20, y+(int)(size.height*0.6), size.width-40, (int)(size.height*0.4));
	}

}
