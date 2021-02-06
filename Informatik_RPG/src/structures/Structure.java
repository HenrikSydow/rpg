package structures;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import entities.Entity;
import main.EntityHandler;
import main.ID;

public abstract class Structure extends Entity{

	protected Image structureImage;
	protected Dimension size;
	
	public Structure(int x, int y, Dimension size, Image image, EntityHandler entityHandler) {
		super(x, y, entityHandler, ID.Structure);
		this.structureImage = image;
		this.size = size;
	}
	
	public void render(Graphics g) {
		g.drawImage(structureImage, x, y, size.width, size.height, null);
	}

}
