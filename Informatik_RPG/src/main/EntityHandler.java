package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import entities.Bandit;
import entities.CombatDummy;
import entities.Enemy;
import entities.Entity;
import entities.Player;

public class EntityHandler {
	
	private LinkedList<Entity> entities = new LinkedList<Entity>();
	private LinkedList<Entity> toRemove = new LinkedList<Entity>();
	private LinkedList<Entity> toAdd = new LinkedList<Entity>();
	
	public void addEntity(Entity entity) {
		toAdd.add(entity);
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
		for(Entity entity : toAdd)
			entities.add(entity);
		toRemove.clear();
		toAdd.clear();
	}
	
	public void render(Graphics g) {
		for(Entity entity : entities)
			entity.render(g);
	}
	
	// Gibt einen Array zur�ck, der alle Enemies beinhaltet, welche sich innerhalb des Rectangles "area" aufhalten.
	// (Daf�r werden die hitboxen / bounds verwendet)
	public Enemy[] getInterceptingEnemies(Rectangle area) {
		Entity[] enemies = getEntityById(ID.Enemy);
		LinkedList<Entity> interceptingEnemies = new LinkedList<Entity>();
		for(Entity enemy : enemies)
			if(enemy.getBounds() != null && enemy.getBounds().intersects(area)) interceptingEnemies.add(enemy);
		return interceptingEnemies.toArray(new Enemy[interceptingEnemies.size()]);
	}
	
	//Gibt alle Entities mit einer bestimmten ID zur�ck
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
	
	public void spawnEnemies(Class enemyClass, int amount, Rectangle area) {
		LinkedList<Enemy> enemiesToSpawn = new LinkedList<Enemy>();
		for(int i = 0; i < amount; i++) {
			// groundbounds des Enemys m�ssen noch abgezogen werden, damit er nicht au�erhalb des
			// Rectangles spawnt
			int x = ThreadLocalRandom.current().nextInt(area.x, area.x+area.width);
			int y = ThreadLocalRandom.current().nextInt(area.y, area.y+area.height);
			if(enemyClass.equals(Bandit.class)) entities.add(new Bandit(x, y, this));
			else if(enemyClass.equals(CombatDummy.class)) entities.add(new CombatDummy(x, y, this));
		}
		for(Enemy enemy : enemiesToSpawn) {
			this.entities.add(enemy);
		}
	}
	
	// Getters & Setters:

	public LinkedList<Entity> getEntities() {
		return entities;
	}
	
}
