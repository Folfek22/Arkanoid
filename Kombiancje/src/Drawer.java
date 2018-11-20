import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Drawer extends JPanel {

	private static final long serialVersionUID = 2610090513874176146L;
	private Rocket rocket;
	private Animation animation;
	private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	private Ball ball;

	private BufferedImage image;

	public Drawer() {

		try {
			image = ImageIO.read(new File("tło.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int w = 0; w < 7; w++) {
			for (int k = 0; k < 21; k++) {
				rectangles.add(new Rectangle(100 + (50 * k), 50 + (20 * w)));
			}
		}
		rocket = new Rocket(1300 / 2 - 90, 670, 40);
		ball = new Ball(660, (int)rocket.getY()-25, 5, 5);
		setFocusable(true);
		setPreferredSize(new Dimension(1300, 700));
		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					animation.setMoveLeft(false);
				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					animation.setMoveRight(false);
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					animation.setMoveLeft(true);
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					animation.setMoveRight(true);
				}
				if(e.getKeyCode() == KeyEvent.VK_SPACE)
					animation.setStart(true);
			}
		});
		animation = new Animation(this);
		animation.start();
	}

	public void moveBall() {
		int score = ball.moveBall(getWidth(), getHeight(), rocket);
		if (score == Ball.LOSE_LIFE) {
			rocket.setLife(rocket.getLife() - 1);
			ball.x = 660;
			ball.y =(int)rocket.getY()-25;
			rocket.x = 610;
			animation.setStart(false);
			if (rocket.getLife() < 0) {
				JOptionPane.showMessageDialog(null, "Game Over", "The END", JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}

		}
		for(Rectangle r : rectangles){
			if(ball.intersects(r)){
				ball.setVy(ball.getVy() * -1);
			}
		}
		removeRectangle(ball);
		

	}

	private boolean removeRectangle(Ball ball) {
		rectangles.removeIf(e -> e.intersects(ball));

		return true;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		setBackGround(g);

		rocket.draw(g);
		ball.draw(g);
		g.setFont(new Font("ASS", Font.ITALIC, 20));
		g.drawString("LIFE=" + String.valueOf(rocket.getLife()), 1200, 20);
		g.setColor(Color.green);
		rectangles.forEach(r -> r.drawRectangle(g));
		g.setColor(Color.black);
		rectangles.forEach(r -> r.drawFrame(g));

	}

	public void moveRocketLeft() {
		if (rocket.getX() > 0) {
			rocket.setVx(-10);
			rocket.moveRocket(getWidth());

		}

	}

	public void moveRocektRight() {
		if (rocket.getX() <= getWidth() - rocket.getWidth()) {
			rocket.setVx(10);
			rocket.moveRocket(getWidth());
		}
	}

	private void setBackGround(Graphics g) {
		g.drawImage(VolatileImageResolver.fromBufferedImage(image), 0, 0, null);
	}


}
