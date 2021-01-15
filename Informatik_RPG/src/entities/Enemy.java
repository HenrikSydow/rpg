package entities;

import main.EntityHandler;
import main.ID;

public abstract class Enemy extends Entity{

	protected int hp = 0, atk = 0, def = 0;
	
	public Enemy(int x, int y, EntityHandler entityHandler) {
		super(x, y, entityHandler, ID.Enemy);
	}
	
	public void tick() {
		if(hp <= 0)
			entityHandler.removeEntity(this);
	}

	public void defend(int atk) {
		this.hp-= atk-def;
		System.out.println("Defending: HP="+hp);
	}
	
}
