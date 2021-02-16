package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import entities.Enemy;
import entities.Entity;
import entities.Player;

public class EntityHandler {
	
	private LinkedList<Entity> entities = new LinkedList<Entity>();
	private LinkedList<Entity> toRemove = new LinkedList<Entity>();
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		toRemove.add(entity);
		if(entity.getId() == ID.Enemy)
			((Player) (getEntityById(ID.Player)[0])).receiveExp(((Enemy) entity).getExp());
	}
	
	public void tick() {
		//sortiere die entities-liste nach renderreihenfolge:
		sortEntities();
		//ticke alle entities
		for(Entity entity : entities)
			entity.tick();
		//entferne DANACH alle entities aus toRemove-list, um concurrent modification-error zu vermeiden
		for(Entity entity : toRemove)
			entities.remove(entity);
		toRemove.clear();
	}
	
	public void render(Graphics g) {
		for(Entity entity : entities)
			entity.render(g);
	}
	
	// Gibt einen Array zurück, der alle Enemies beinhaltet, welche sich innerhalb des Rectangles "area" aufhalten.
	// (Dafür werden die hitboxen / bounds verwendet)
	public Enemy[] getInterceptingEnemies(Rectangle area) {
		Entity[] enemies = getEntityById(ID.Enemy);
		LinkedList<Entity> interceptingEnemies = new LinkedList<Entity>();
		for(Entity enemy : enemies)
			if(enemy.getBounds() != null && enemy.getBounds().intersects(area)) interceptingEnemies.add(enemy);
		return interceptingEnemies.toArray(new Enemy[interceptingEnemies.size()]);
	}
	
	//Gibt alle Entities mit einer bestimmten ID zurück
	public Entity[] getEntityById(ID id) {
		LinkedList<Entity> filteredEntities = new LinkedList<Entity>();
		for(Entity entity : entities)
			if(entity.getId() == id) filteredEntities.add(entity);
		return filteredEntities.toArray(new Entity[filteredEntities.size()]);
	}
	
	
	//Sortiert die liste aller Entities mit Hilfe der GroundBounds, damit sie in der richtigen Reihenfolge gerendert werden
	private void sortEntities() {
		Collections.sort(entities, new Comparator<Entity>() {
			@Override
			public int compare(Entity o1, Entity o2) {
				if(o1.getGroundBounds() != null && o2.getGroundBounds() != null) {
					Rectangle o1Bounds = o1.getGroundBounds();
					Rectangle o2Bounds = o2.getGroundBounds();
					return Integer.valueOf(o1Bounds.y+o1Bounds.height).compareTo(Integer.valueOf(o2Bounds.y+o2Bounds.height));
				} else {
					return 0;
				}
			}
		});
	}
	
	// Getters & Setters:

	public LinkedList<Entity> getEntities() {
		return entities;
	}
	
}
