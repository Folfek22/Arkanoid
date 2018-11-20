import java.awt.Color;
import java.awt.Graphics;



public class Rectangle extends java.awt.Rectangle {
	private static final long serialVersionUID = -1751777773741647184L;
	public Rectangle(int x, int y){
		this.x = x;
		this.y = y;
		this.width = 50;
		this.height = 20;
	}
	public double getX(){
		return x;
	}
	public void drawRectangle(Graphics g){
		g.fillRect(x, y, width, height);
		g.setColor(Color.green);
	}
	public void drawFrame(Graphics g){
		g.drawRect(x, y, width, height);
		g.setColor(Color.BLACK);
	}
}
