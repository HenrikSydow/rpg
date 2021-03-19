package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;
import main.KeyHandler;


public class Pause {
	private Dimension size = new Dimension(Game.GAME_SIZE.width/8*2, Game.GAME_SIZE.height/4*3);
	private int xOffset = (Game.GAME_SIZE.width - size.width) / 2, yOffset = (Game.GAME_SIZE.height - size.height) / 2;
	
	private Game game;
	private KeyHandler keyHandler;
	
	public Pause(Game game, KeyHandler keyHandler) {
		this.game = game;
		this.keyHandler = keyHandler;
	}
	
	public void tick() {
		if(keyHandler.isEscape())
			game.setPaused(true);
		else
			game.setPaused(false);
	}
	
	public void render(Graphics g) {
		if(keyHandler.isEscape()) {
			g.translate(xOffset, yOffset);


			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.drawString("PAUSE",Game.WIDTH/2, Game.HEIGHT/2);
		}
	}
}
