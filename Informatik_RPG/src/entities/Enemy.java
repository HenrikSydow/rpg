package entities;

import main.ID;

public abstract class Enemy extends Entity{

	protected int hp, atk, def;
	
	public Enemy(int x, int y) {
		super(x, y, ID.Enemy);
	}

}
