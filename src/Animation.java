public class Animation extends Thread {
	private Drawer drawer;
	private boolean moveLeft;
	private boolean moveRight;
	private boolean startBall;
	private boolean stop = false;

	
	public Animation(Drawer drawer) {
		this.drawer = drawer;
	}

	public void run() {
		while(true){
			try {
				if(!stop){
				if(moveLeft){
					drawer.moveRocketLeft();
				}
				if(moveRight){
					drawer.moveRocektRight();
				}
				if(startBall){
					drawer.moveBall();
				}
				
				}
				Thread.sleep(15);
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

	public boolean isStartBall() {
		return startBall;
	}

	public void setStartBall(boolean startBall) {
		this.startBall = startBall;
	}
	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	
	
}
