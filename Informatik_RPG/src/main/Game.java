package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;

import entities.Bandit;
import entities.Barrel;
import entities.CombatDummy;
import entities.Grass;
import entities.Player;
import structures.Bench;
import structures.House;
import structures.Tree;
import structures.Tree2;
import ui.Inventory;

public class Game extends Canvas implements Runnable{

	public final static Dimension GAME_SIZE = new Dimension(800, 600);
	
	private KeyHandler keyHandler = new KeyHandler();
	private EntityHandler entityHandler = new EntityHandler();
	
	private Thread gameloopThread;
	private boolean running = false, paused = false;
	
	private Player player = new Player(450, 630, entityHandler, keyHandler);

	private Inventory inventory = new Inventory(player, this, keyHandler);
	
	public Game() {
		init();
		start();
	}
	
	// initialisiert dieses Game-Object
	private void init() {
		this.setSize(GAME_SIZE);
		new MainFrame("RPG", this);
		this.addKeyListener(keyHandler);
		
		entityHandler.addEntity(new Grass(0, 0, 2000, 2000, entityHandler));
		entityHandler.addEntity(new House(0,0, entityHandler));
		entityHandler.addEntity(player);
		
		entityHandler.addEntity(new Tree(470, 470, entityHandler));
		entityHandler.addEntity(new Tree(550, 530, entityHandler));
		entityHandler.addEntity(new Tree(600, 580, entityHandler));
		entityHandler.addEntity(new Tree(700, 600, entityHandler));
		entityHandler.addEntity(new Tree(770, 680, entityHandler));
		
		entityHandler.addEntity(new CombatDummy(615, 700, entityHandler));
		
		entityHandler.addEntity(new Barrel(0, 600, entityHandler));
		entityHandler.addEntity(new Barrel(40, 640, entityHandler));
		entityHandler.addEntity(new Barrel(40, 900, entityHandler));
		
		entityHandler.addEntity(new Bandit(1000, 1000, entityHandler));
		
		entityHandler.addEntity(new Tree2(400, 850, entityHandler));
		entityHandler.addEntity(new Bench(320, 660, entityHandler));
		
		entityHandler.spawnEnemies(Bandit.class, 5, new Rectangle(500, 900, 400, 400));
	}
	
	// Gameloop:
	public void run() {
		this.requestFocus();
		int clock = 5;
		
		while(running) {
			tick();
			render();
			
			try {
				Thread.sleep(clock);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	// startet den Gameloop
	private void start() {
		gameloopThread = new Thread(this);
		running = true;
		gameloopThread.start();
	}
	
	// stoppt den Gameloop
	private void stop() {
		running = false;
		try {
			gameloopThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// Updated das Spiel / alle Elemente
	private void tick() {
		if(!paused) entityHandler.tick();
		inventory.tick();
	}
	
	// Zeichnet alle Elemente auf den Canvas
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		//-- hier render methoden einfügen -----------------------------------------------
		g.setColor(Color.white);
		g.fillRect(0, 0, GAME_SIZE.width, GAME_SIZE.height);

		//Camera movement:
		g.translate(-(player.getX() - GAME_SIZE.width/2 + player.getBounds().width+25), -(player.getY() - GAME_SIZE.height/2 + player.getBounds().height));
		
		entityHandler.render(g);
		
		//Camera movement (g.translate) relativieren, damit alle folgenden overlays (ui: inventory etc) mittig gerendert werden:
		g.translate((player.getX() - GAME_SIZE.width/2 + player.getBounds().width+25), (player.getY() - GAME_SIZE.height/2 + player.getBounds().height));
		
		//wenn pausiert, spiel "ausgrauen"
		if(paused) {
			g.setColor(new Color(0,0,0,100));
			g.fillRect(0, 0, GAME_SIZE.width, GAME_SIZE.height);
		}

		inventory.render(g);
		//-------------------------------------------------------------------------------
		
		bs.show();
		g.dispose();
	}
	
	public void setPaused(boolean paused){
		this.paused = paused;
	}
	
	// Entry-point / Anfang des Programms:
	public static void main(String[] args) {
		new Game();
	}

}
