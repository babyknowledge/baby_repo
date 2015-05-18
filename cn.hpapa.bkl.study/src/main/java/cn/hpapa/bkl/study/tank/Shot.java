package cn.hpapa.bkl.study.tank;


public class Shot implements Runnable {
	
	private int x;
	
	private int y;
	
	private int direction;
	
	private int speed = 1;
	
	private boolean isLive = true;
	
	public Shot(int x, int y, int direction){
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public void run() {
		while(true){
			try{
				Thread.sleep(50);
			} catch(Exception e){
				e.printStackTrace();
			}
			switch(this.direction){
			case 0: y -= speed; break;
			case 1: x += speed; break;
			case 2: y += speed; break;
			case 3: x -= speed; break;
			}
			System.out.println("x=" + x + "y=" + y);
			// 子弹何时死亡
			// 判断该子弹是否碰到边缘
			if(x <= 0 || x >= 400 || y <= 0 || y >= 300){
				this.isLive = false;
				break;
			}
		}
	}

}
