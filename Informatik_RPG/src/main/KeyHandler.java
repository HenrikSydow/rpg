package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	private boolean w = false, a = false, s = false, d = false, enter = false, space = false, shift = false, i = false;
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
				w = true;
				break;
			case KeyEvent.VK_A:
				a = true;
				break;
			case KeyEvent.VK_S:
				s = true;
				break;
			case KeyEvent.VK_D:
				d = true;
				break;
			case KeyEvent.VK_ENTER:
				enter = true;
				break;
			case KeyEvent.VK_SPACE:
				space = true;
				break;
			case KeyEvent.VK_SHIFT:
				shift = true;
				break;
			case KeyEvent.VK_I:
				if(i) i = false;
				else i = true;
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
			case KeyEvent.VK_ENTER:
				enter = false;
				break;
			case KeyEvent.VK_SPACE:
				space = false;
				break;
			case KeyEvent.VK_SHIFT:
				shift = false;
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
	
	public boolean isEnter() {
		return enter;
	}
	
	public boolean isSpace() {
		return space;
	}
	
	public boolean isShift() {
		return shift;
	}
	
	public boolean isI() {
		return i;
	}
}
