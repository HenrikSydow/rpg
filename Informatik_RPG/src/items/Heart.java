package items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

import entities.Player;
import main.EntityHandler;
import main.ID;

public class Heart extends Item {

	public Heart(int x, int y, EntityHandler entityHandler) {
		super(x, y, Toolkit.getDefaultToolkit().createImage("res\\items\\heartAnimation.gif"), entityHandler, ID.Item);
	}

	@Override
	public void tick() {
		Player player = (Player) entityHandler.getEntityById(ID.Player)[0];
		if(this.getGroundBounds().intersects(player.getBounds())) {
			player.defend(-20);
			entityHandler.removeEntity(this);
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(animation, x, y, 50, 50, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x+13, y+15, 20, 20);
	}

	@Override
	public Rectangle getGroundBounds() {
		return new Rectangle(x+13, y+15, 20, 20);
	}

}
