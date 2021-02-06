package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import entities.CombatDummy;
import entities.Grass;
import entities.House;
import entities.Player;

public class Game extends Canvas implements Runnable{

	public final static Dimension GAME_SIZE = new Dimension(800, 600);
	
	private KeyHandler keyHandler = new KeyHandler();
	private EntityHandler entityHandler = new EntityHandler();
	
	private Thread gameloopThread;
	private boolean running = false;
	
	private Image background = Toolkit.getDefaultToolkit().createImage("res\\grassGround.png");
	
	private Player player = new Player(500, 500, entityHandler, keyHandler);
	
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
		entityHandler.addEntity(new CombatDummy(600, 200, entityHandler));
		entityHandler.addEntity(player);
		entityHandler.addEntity(new CombatDummy(800, 200, entityHandler));
		entityHandler.addEntity(new CombatDummy(700, 400, entityHandler));
	}
	
	// Gameloop:
	public void run() {
		this.requestFocus();
		int clock = 7;
		
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
		entityHandler.tick();
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
		g.translate(-(player.getX() - GAME_SIZE.width/2 + 50), -(player.getY() - GAME_SIZE.height/2 + 100));
		
		/*
		g.drawImage(background, 0, 0, this);
		g.drawImage(background, 0, background.getHeight(this), this);
		g.drawImage(background, background.getWidth(this), 0, this);
		g.drawImage(background, background.getWidth(this), background.getHeight(this), this);
		*/
		
		g.drawString("use \"wasd\" to move, use \"enter\" to attack", 550, 100);
		
		entityHandler.render(g);
		//-------------------------------------------------------------------------------
		
		bs.show();
		g.dispose();
	}
	
	// Entry-point / Anfang des Programms:
	public static void main(String[] args) {
		new Game();
	}

}
