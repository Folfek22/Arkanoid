import javax.swing.JFrame;


public class Frame extends JFrame {

	private static final long serialVersionUID = 4983727800375272097L;
	public Frame(){
		setTitle("Arkanoid");
		setFocusable(false);
		add(new Drawer());
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		new Frame();
	}
	
}
