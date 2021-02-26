package items;

import java.awt.Image;

import entities.Entity;
import main.EntityHandler;
import main.ID;

abstract class Item extends Entity{

	protected Image animation;
	
	public Item(int x, int y, Image animation, EntityHandler entityHandler, ID id) {
		super(x, y, entityHandler, id);
		this.animation = animation;
	}

}
