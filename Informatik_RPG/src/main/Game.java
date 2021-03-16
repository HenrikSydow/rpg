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
//import structures.HouseAlt;
import structures.Tree;
import structures.Tree2;
import ui.Inventory;

public class Game extends Canvas implements Runnable{

	public final static Dimension GAME_SIZE = new Dimension(800, 600);
	
	private KeyHandler keyHandler = new KeyHandler();
	private EntityHandler entityHandler = new EntityHandler();
	
	private Thread gameloopThread;
	private boolean running = false, paused = false;
	
	private Player player = new Player(850, 1000, entityHandler, keyHandler);

	private Inventory inventory = new Inventory(player, this, keyHandler);
	
	public Game() {
		init();
		start();
	}
	
	// initialisiert dieses Game-Object
	private void init() {
		this.setSize(GAME_SIZE);
		new MainFrame("RPG 0.1", this);
		this.addKeyListener(keyHandler);
		
		entityHandler.addEntity(new Grass(0, 0, 2000, 2000, entityHandler));
		entityHandler.addEntity(player);
		
		entityHandler.addEntity(new House(600, 400, entityHandler));
		//entityHandler.addEntity(new HouseAlt(1200, 400, entityHandler));
		
		entityHandler.addEntity(new CombatDummy(700, 1150, entityHandler));

		entityHandler.addEntity(new Barrel(600, 1250, entityHandler));
		entityHandler.addEntity(new Barrel(650, 1250, entityHandler));
		entityHandler.addEntity(new Barrel(615, 1280, entityHandler));
		
		for( int i=0; i<10; i++)
		{
		entityHandler.addEntity(new Bandit(1300,1200+ i*50, entityHandler));
		}
		
		entityHandler.addEntity(new Tree2(550, 980, entityHandler));
		entityHandler.addEntity(new Tree2(470, 1050, entityHandler));
		entityHandler.addEntity(new Tree2(520, 1120, entityHandler));
		entityHandler.addEntity(new Tree2(540, 1190, entityHandler));

		entityHandler.addEntity(new Tree2(1050, 920, entityHandler));
		entityHandler.addEntity(new Bench(1050, 1080, entityHandler));
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
