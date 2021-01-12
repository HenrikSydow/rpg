package main;

import javax.swing.JFrame;

public class MainFrame extends JFrame{

	public MainFrame(String title, Game game) {
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(game);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}
