package entities;

import java.awt.Rectangle;
import java.awt.Toolkit;

import main.EntityHandler;
import main.GifContainer;
import main.ID;

public class Bandit extends Enemy {
	
	// initialisiere alle animationen (siehe Enemy-class für Reihenfolge)
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private String path = "res\\enemies\\bandit\\";
	private GifContainer[][] animations = {
		{
			new GifContainer(toolkit.createImage(path + "banditStandDown.gif"), 12),
			new GifContainer(toolkit.createImage(path + "banditStandUp.gif"), 12),
			new GifContainer(toolkit.createImage(path + "banditStandLeft.gif"), 12),
			new GifContainer(toolkit.createImage(path + "banditStandRight.gif"), 12)
		},
		{	new GifContainer(toolkit.createImage(path + "banditDeath.gif"), 180)},
		{
			new GifContainer(toolkit.createImage(path + "banditWalkDown.gif"), 96),
			new GifContainer(toolkit.createImage(path + "banditWalkUp.gif"), 96),
			new GifContainer(toolkit.createImage(path + "banditWalkLeft.gif"), 96),
			new GifContainer(toolkit.createImage(path + "banditWalkRight.gif"), 96)
		},
		{
			new GifContainer(toolkit.createImage(path + "banditSwordDown.gif"), 72),
			new GifContainer(toolkit.createImage(path + "banditSwordUp.gif"), 72),
			new GifContainer(toolkit.createImage(path + "banditSwordLeft.gif"), 72),
			new GifContainer(toolkit.createImage(path + "banditSwordRight.gif"), 72)
		}
	};
	
	//Hitboxen der Angriffe (unten, oben, links, rechts)
	private Rectangle[] atkHitBox = {
			new Rectangle(55, 95, 30, 50),
			new Rectangle(55, 45, 30, 50),
			new Rectangle(20, 70, 40, 30),
			new Rectangle(80, 70, 40, 30)
	};
		
	public Bandit(int x, int y, EntityHandler entityHandler) {
		super(x, y, 140, 140, entityHandler);
		this.setAnimations(animations);
		this.hp = 10;
		this.atk = 10;
		this.def = 2;
		this.exp = 50;
		this.velX = this.velY = 1;
		this.setAttackHitboxes(atkHitBox);
	}
	
	public void tick() {
		if(entityHandler.getEntityById(ID.Player)[0].getBounds().intersects(this.getCurrentAtkHitbox()))
			this.attack();
		else
			this.trackPlayer();
		super.tick();
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x+45, y+35, 50, 100);
	}

	@Override
	public Rectangle getGroundBounds() {
		return new Rectangle(x+45, y+110, 50, 25);
	}

}
