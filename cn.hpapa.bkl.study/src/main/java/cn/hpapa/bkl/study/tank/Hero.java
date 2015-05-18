package cn.hpapa.bkl.study.tank;

public class Hero extends Tank {
	
	// 子弹是Hero的一个属性
	private Shot shot;

	public Hero(int x, int y) {
		super(x, y);
	}
	
	public void shotEnemy() {
		
		switch(this.getDirection()){
		case 0 :
			shot = new Shot(this.getX() + 10, this.getY(), 0);
			break;
		case 1 :
			shot = new Shot(this.getX() + 30, this.getY() + 10, 1);
			break;
		case 2 :
			shot = new Shot(this.getX() + 10, this.getY() + 30, 2);
			break;
		case 3 :
			shot = new Shot(this.getX(), this.getY() + 10, 3);
			break;
		}
		// 启动子弹线程
		Thread t = new Thread(shot);
		t.start();
		
	}

	public Shot getShot() {
		return shot;
	}

	public void setShot(Shot shot) {
		this.shot = shot;
	}
	
	// 坦克向上移动
	public void moveUp() {
		setY(getY() - getSpeed());
	}

	// 坦克向右移动
	public void moveRight() {
		setX(getX() + getSpeed());
	}

	// 坦克向左移动
	public void moveLeft() {
		setX(getX() - getSpeed());
	}

	// 坦克向下移动
	public void moveDown() {
		setY(getY() + getSpeed());
	}
}
