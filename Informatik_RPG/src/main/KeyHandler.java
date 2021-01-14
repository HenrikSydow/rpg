package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	private boolean w = false, a = false, s = false, d = false, space = false;
	private String lastPressed;
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
				w = true;
				lastPressed = "w";
				break;
			case KeyEvent.VK_A:
				a = true;
				lastPressed = "a";
				break;
			case KeyEvent.VK_S:
				s = true;
				lastPressed = "s";
				break;
			case KeyEvent.VK_D:
				d = true;
				lastPressed = "d";
				break;
			case KeyEvent.VK_SPACE:
				space = true;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
				w = false;
				break;
			case KeyEvent.VK_A:
				a = false;
				break;
			case KeyEvent.VK_S:
				s = false;
				break;
			case KeyEvent.VK_D:
				d = false;
				break;
			case KeyEvent.VK_SPACE:
				space = false;
				break;
		}
	}
	
	
	
	// Getters & Setters:
	
	public boolean isW() {
		return w;
	}

	public boolean isA() {
		return a;
	}

	public boolean isS() {
		return s;
	}

	public boolean isD() {
		return d;
	}
	
	public boolean isSpace() {
		return space;
	}
	
	public String getLastPressed() {
		return lastPressed;
	}
	
}
