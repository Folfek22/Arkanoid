public class Animation extends Thread {
	private Drawer drawer;
	private boolean moveLeft;
	private boolean moveRight;
	private boolean start;

	public Animation(Drawer drawer) {
		this.drawer = drawer;
	}

	public void run() {
		while(true){
			try {
				if(moveLeft){
					drawer.moveRocketLeft();
				}
				if(moveRight){
					drawer.moveRocektRight();
				}
				if(start){
					drawer.moveBall();
				}
				
				Thread.sleep(20);
			} catch (Exception e) {
				
			}
			drawer.repaint();
		}
	}

	public boolean isMoveLeft() {
		return moveLeft;
	}

	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}

	public boolean isMoveRight() {
		return moveRight;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}
	
	
}
