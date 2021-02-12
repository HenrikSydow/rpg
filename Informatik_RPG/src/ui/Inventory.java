package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import entities.Player;
import main.Game;
import main.KeyHandler;

public class Inventory {

	private Dimension size = new Dimension(Game.GAME_SIZE.width/4*3, Game.GAME_SIZE.height/4*3);
	private int xOffset = (Game.GAME_SIZE.width - size.width) / 2, yOffset = (Game.GAME_SIZE.height - size.height) / 2;
	
	private Image playerImg = Toolkit.getDefaultToolkit().createImage("res\\player\\standDown.gif");
	
	private Player player;
	private Game game;
	private KeyHandler keyHandler;
	
	public Inventory(Player player, Game game, KeyHandler keyHandler) {
		this.player = player;
		this.game = game;
		this.keyHandler = keyHandler;
	}
	
	public void tick() {
		if(keyHandler.isI())
			game.setPaused(true);
		else
			game.setPaused(false);
	}
	
	public void render(Graphics g) {
		if(keyHandler.isI()) {
			// zentrieren des Inventorys:
			g.translate(xOffset, yOffset);
			
			g.setColor(new Color(242, 202, 128));
			g.fillRect(0, 0, size.width, size.height);
			
			g.setColor(Color.black);
			g.drawRect(0, 0, size.width, size.height);
			
			g.drawImage(playerImg, 0, 0, 250, 250, null);
			
			g.setColor(Color.black);
			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.drawString("PLAYER", 300, 75);
			g.setFont(new Font("Arial", Font.PLAIN, 40));
			g.drawString("Health: ", 300, 150);
			g.drawString("Attack: ", 300, 200);
			g.drawString("Defense: ", 300, 250);
			
			// g.translate "resetten"
			g.translate(-xOffset, -yOffset);
		}
	}
	
}
