package entities;

public abstract class Enemy extends Entity{

	protected int hp, atk, def;
	
	public Enemy(int x, int y) {
		super(x, y);
	}

}
