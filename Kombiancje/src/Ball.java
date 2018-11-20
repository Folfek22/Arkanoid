import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends Rectangle {

	private static final long serialVersionUID = -8283932358086209502L;
	private int vx;
	private int vy;
	public static final int LOSE_LIFE = 1;
	public static final int NORMAL_REFLECTION = 2;

	public Ball(int x, int y, int vx, int vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.width = 25;
		this.height = 25;

	}

	public int moveBall(int maxWidth, int maxHeight, Rocket... rockets) {
		x += vx;
		y += vy;
		for (Rocket r : rockets)
			if (r.intersects(this)) {
				vy *= -1;
				break;
			}
		if (y <= 0 || y + height >= maxHeight) {
			vy *= -1;
			if (y + height >= 700) { // dlaczegi przy getHight lub max hght
										// warunek wykonuje sie 4 lub 5 razy.???
				return LOSE_LIFE;
			}

		}
		if (x <= 0 || x + width >= maxWidth) {
			vx *= -1;
		}

		return NORMAL_REFLECTION;
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, width, height);
	}

}
