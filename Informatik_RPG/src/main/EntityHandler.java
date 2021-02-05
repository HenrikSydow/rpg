package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import entities.Enemy;
import entities.Entity;

public class EntityHandler {
	
	private LinkedList<Entity> entities = new LinkedList<Entity>();
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
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
		LinkedList<Entity> tempEntityList = new LinkedList<Entity>();
		for(Entity tempEntity : entities) {
			if(tempEntity.getBounds().intersects(area) && tempEntity.getId() != ID.Player && tempEntity.getId() == ID.Enemy)
				tempEntityList.add(tempEntity);
		}
		Enemy[] output = new Enemy[tempEntityList.size()];
		for(int i=0; i<tempEntityList.size(); i++)
			output[i] = (Enemy) tempEntityList.get(i);
		return output;
	}
	
	// Getters & Setters:

	public LinkedList<Entity> getEntities() {
		return entities;
	}
	
}
