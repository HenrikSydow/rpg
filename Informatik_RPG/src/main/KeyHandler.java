package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	boolean w = false, a = false, s = false, d = false;
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
				System.out.println("pressed w");
				w = true;
				break;
			case KeyEvent.VK_A:
				System.out.println("pressed a");
				a = true;
				break;
			case KeyEvent.VK_S:
				System.out.println("pressed s");
				s = true;
				break;
			case KeyEvent.VK_D:
				System.out.println("pressed d");
				d = true;
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
	
}
