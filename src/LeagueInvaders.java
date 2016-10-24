import java.awt.Dimension;

import javax.swing.JFrame;

public class LeagueInvaders {

	JFrame window;
	GamePanel panel;
	
	// final variables are constant, so they can't be changed
	static final int WIDTH = 500;
	static final int HEIGHT = 700;
	
	public static void main(String[] args) {
		LeagueInvaders game = new LeagueInvaders();
	}
	
	LeagueInvaders() {
		window = new JFrame();
		window.setSize(WIDTH, HEIGHT);
		panel = new GamePanel();
		setup();
	}
	
	void setup() {
		window.add(panel);
		window.addKeyListener(panel);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.startGame();
	}
	
}
