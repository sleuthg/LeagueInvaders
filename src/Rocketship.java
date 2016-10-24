import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {
	
	int speed = 5;
	
	boolean movingLeft = false;
	boolean movingRight = false;
	boolean movingDown = false;
	boolean movingUp = false;
	
	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	void update() {
		super.update();
		if (movingLeft) {
			x-=speed;
		}
		if (movingRight) {
			x+=speed;
		}
		if (movingUp) {
			y-=speed;
		}
		if (movingDown) {
			y+=speed;
		}
		if (x<0) {x=0;}
		if (y<0) {y=0;}
		if (x+width>LeagueInvaders.WIDTH) {
			x = LeagueInvaders.WIDTH - width;
		}
		if (y+height>LeagueInvaders.HEIGHT) {
			y = LeagueInvaders.HEIGHT - height;
		}
	}
	
	void draw(Graphics g) {
		g.drawImage(GamePanel.rocketImg, x, y, width, height, null);
	}
	
}
