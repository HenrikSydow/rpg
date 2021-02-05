package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import entities.Enemy;
import entities.Entity;
import entities.Player;

public class EntityHandler {
	
	private LinkedList<Entity> entities = new LinkedList<Entity>();
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
		if(entity.getId() == ID.Enemy)
			((Player) (getEntityById(ID.Player)[0])).receiveExp(((Enemy) entity).getExp());
	}
	
	public void tick() {
		for(Entity entity : entities)
			entity.tick();
	}
	
	public void render(Graphics g) {
		for(Entity entity : entities)
			entity.render(g);
	}
	
	// Gibt einen Array zurück, der alle Entities beinhaltet, welche sich innerhalb des Rectangles "area" aufhalten.
	// (Dafür werden die hitboxen / bounds verwendet)
	public Enemy[] getInterceptingEnemies(Rectangle area) {
		Entity[] enemies = getEntityById(ID.Enemy);
		LinkedList<Entity> interceptingEnemies = new LinkedList<Entity>();
		for(Entity enemy : enemies)
			if(enemy.getBounds() != null && enemy.getBounds().intersects(area)) interceptingEnemies.add(enemy);
		return interceptingEnemies.toArray(new Enemy[interceptingEnemies.size()]);
	}
	
	public Entity[] getEntityById(ID id) {
		LinkedList<Entity> filteredEntities = new LinkedList<Entity>();
		for(Entity entity : entities)
			if(entity.getId() == id) filteredEntities.add(entity);
		return filteredEntities.toArray(new Entity[filteredEntities.size()]);
	}
	
	// Getters & Setters:

	public LinkedList<Entity> getEntities() {
		return entities;
	}
	
}
