package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import entities.Player;
import entities.Slime;

public class Game extends Canvas implements Runnable{

	public final static Dimension GAME_SIZE = new Dimension(800, 600);
	
	private KeyHandler keyHandler = new KeyHandler();
	private EntityHandler entityHandler = new EntityHandler();
	
	private Thread gameloopThread;
	private boolean running = false;
	
	public Game() {
		init();
		start();
	}
	
	// initialisiert dieses Game-Object
	private void init() {
		this.setSize(GAME_SIZE);
		new MainFrame("RPG", this);
		this.addKeyListener(keyHandler);
		
		entityHandler.addEntity(new Player(10, 10, keyHandler));
		entityHandler.addEntity(new Slime(400, 300));
	}
	
	// Gameloop:
	public void run() {
		this.requestFocus();
		int clock = 3;
		
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
		
		g.setColor(Color.black);
		g.drawString("use wasd to move", 550, 100);

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
