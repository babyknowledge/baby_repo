package cn.hpapa.bkl.study.tank.v1;
/**
 * 坦克游戏1.0版本
 * 1、画出坦克。
 * 
 *//*
package cn.hpapa.bkl.study.tank;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyTankGame1 extends JFrame {
	
	
	private static final long serialVersionUID = 5690270586844111483L;

	MyPanel mp = null;
	
	public MyTankGame1(){
		mp = new MyPanel();
		
		this.add(mp);
		
		this.setSize(400, 300);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTankGame1 mtg = new MyTankGame1();
	}

}

class Tank {
	// 坦克在panel中的横坐标
	private int x = 0;
	// 坦克在panel中的纵坐标
	private int y = 0;
	
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
}

class Hero extends Tank {
	public Hero(int x, int y){
		super(x,y);
	}
}

class MyPanel extends JPanel {
	
	// 定义一个我的坦克
	private Hero hero = null;
	
	public MyPanel() {
		hero = new Hero(100, 10);
	}
	
	// 画出坦克的函数
	public void drawTank(int x, int y, Graphics g, int direct, int type){
		
		switch (type) {
		// 我的坦克
		case 0:
			g.setColor(Color.CYAN);
			break;
		// 敌人的坦克
		case 1:
			g.setColor(Color.YELLOW);
			break;
		}
		
		// 判断方向
		switch (direct) {
		// 向上
		case 0:
			// 画出我的坦克（到时再封装成一个坦克）
			// 1、画出左面的矩形
			g.fillRect(x, y, 5, 30);
			// 2、画出右面矩形
			g.fillRect(x + 15, y, 5, 30);
			// 3、画出中间矩形
			g.fillRect(x + 5, y + 5, 10, 20);
			// 4、画出圆形
			g.fillOval(x + 5, y + 10, 10, 10);
			// 5画出线
			g.drawLine(x + 10, y + 15, x + 10, y + 15);
			break;
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		// 设置画笔的颜色
		g.setColor(Color.CYAN);
//		g.drawArc(20, 20, 100, 200, 50, -100);
		this.drawTank(hero.getX(), hero.getY(), g, 0, 0);
		
	}
}*/