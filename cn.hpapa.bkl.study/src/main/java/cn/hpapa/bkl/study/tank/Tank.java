package cn.hpapa.bkl.study.tank;

import java.awt.Color;

public class Tank {

	// 坦克在panel中的横坐标
	private int x = 0;
	// 坦克在panel中的纵坐标
	private int y = 0;

	/**
	 * 坦克方向 0：上 1：下 2：← 3：右
	 */
	private int direction = 0;

	// 坦克的速度
	private int speed = 5;

	private Color color = Color.BLUE;

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
