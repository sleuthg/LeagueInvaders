import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	Timer timer;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	Font titleFont;
	Rocketship rocket = new Rocketship(250,500,50,50);
	ObjectManager manager;
	
	public static BufferedImage alienImg;
	public static BufferedImage rocketImg;
	public static BufferedImage bulletImg;
	
	GamePanel() {
		timer = new Timer(1000/60, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		manager = new ObjectManager();
		manager.addObject(rocket);
		
		try {
			alienImg = ImageIO.read(this.getClass().getResourceAsStream("alien.png"));
			rocketImg = ImageIO.read(this.getClass().getResourceAsStream("rocket.png"));
			bulletImg = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	void updateMenuState() {
		
	}
	
	void updateGameState() {
		manager.update();
		manager.manageEnemies();
		manager.checkCollision();
		if (!rocket.isAlive) {
			currentState = END_STATE;
			manager.reset();
			rocket = new Rocketship(150,500,50,50);
			manager.addObject(rocket);
		}
	}
	
	void updateEndState() {
		
	}
	
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("League Invaders", 60, 300);
		g.drawString("Press 'Enter' to Play", 60, 350);
	}
	
	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		manager.draw(g);
	}
	
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 100, 300);
		g.drawString("Score: " + manager.getScore(), 100, 350);
	}
	
	void startGame() {
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else {
			updateEndState();
		}
	}
	
	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else {
			drawEndState(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("key Typed");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == MENU_STATE) {
				currentState = GAME_STATE;
				manager.setScore(0);
			} else if(currentState == GAME_STATE) {
				currentState = END_STATE;
			} else {
				currentState = MENU_STATE;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			rocket.movingLeft = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rocket.movingRight = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			rocket.movingUp = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			rocket.movingDown = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			manager.addObject(new Projectile(rocket.x+20,rocket.y,10,10));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			rocket.movingLeft = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rocket.movingRight = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			rocket.movingUp = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			rocket.movingDown = false;
		}
	}
}
