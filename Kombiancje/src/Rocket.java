import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Rocket extends Rectangle {

	private static final long serialVersionUID = -3349129308417120460L;
	private int vx;
	private int life = 3;
	private BufferedImage image;

	public Rocket(int x, int y, int vx) {
		try {
			image = ImageIO.read(new File("rocket.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public double getX() {
		return x;
	}

	public double getWidth() {
		return width;
	}

	public void moveRocket(int maxWidth) {
		x += vx;
		if (x <= 0 || x + width + 2 >= maxWidth) {
			vx *= -1;
		}

	}

	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
	}

}
