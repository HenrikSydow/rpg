package main;

import java.awt.Graphics;
import java.util.LinkedList;

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
	
	
	
	// Getters & Setters:

	public LinkedList<Entity> getEntities() {
		return entities;
	}
	
}
