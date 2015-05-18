/**
 * 坦克游戏1.0版本
 * 1、画出坦克。
 * 2、我的坦克可以上下左右移动
 */
package cn.hpapa.bkl.study.tank.v3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cn.hpapa.bkl.study.tank.EnemyTank;
import cn.hpapa.bkl.study.tank.Hero;

public class MyTankGame3 extends JFrame {
	
	
	private static final long serialVersionUID = 5690270586844111483L;

	MyPanel mp = null;
	
	
	public MyTankGame3(){
		mp = new MyPanel();
		
		Thread t = new Thread(mp);
		t.start();
		
		this.add(mp);
		
		// 注册监听
		this.addKeyListener(mp);
		
		this.setSize(400, 300);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTankGame3 mtg = new MyTankGame3();
	}

}

// 为了实现重复刷新，所以这个Panel也是多线程的
class MyPanel extends JPanel implements KeyListener, Runnable {
	
	// 定义一个我的坦克
	private Hero hero = null;
	
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	
	int enSize = 3;
	
	public MyPanel() {
		hero = new Hero(100, 10);
		
		for(int i = 0; i < enSize; i++){
			EnemyTank et = new EnemyTank((i + 1) * 50, 0);
			et.setColor(Color.YELLOW);
			et.setDirection(2);
			ets.add(et);
		}
	}
	
	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
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
		case 1 :
			// 炮筒向右
			// 1、画出上面的矩形
			g.fillRect(x, y, 30, 5);
			// 2、画出右面矩形
			g.fillRect(x, y + 15, 30, 5);
			// 3、画出中间矩形
			g.fillRect(x + 5, y + 5, 20, 10);
			// 4、画出圆形
			g.fillOval(x + 10, y + 5, 10, 10);
			// 5画出线
			g.drawLine(x + 15, y + 10, x + 30, y + 10);
			break;
		case 2 :
			// 向下
			// 1、画出上面的矩形
			g.fillRect(x, y, 30, 5);
			// 2、画出右面矩形
			g.fillRect(x, y + 15, 30, 5);
			// 3、画出中间矩形
			g.fillRect(x + 5, y + 5, 20, 10);
			// 4、画出圆形
			g.fillOval(x + 10, y + 5, 10, 10);
			// 5画出线
			g.drawLine(x + 15, y + 10, x + 10, y + 30);
			break;
		case 3 :
			// 向左
			// 1、画出上面的矩形
			g.fillRect(x, y, 30, 5);
			// 2、画出右面矩形
			g.fillRect(x, y + 15, 30, 5);
			// 3、画出中间矩形
			g.fillRect(x + 5, y + 5, 20, 10);
			// 4、画出圆形
			g.fillOval(x + 10, y + 5, 10, 10);
			// 5画出线
			g.drawLine(x + 15, y + 10, x, y + 10);
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		// 设置画笔的颜色
		g.setColor(Color.YELLOW);
//		g.drawArc(20, 20, 100, 200, 50, -100);
		this.drawTank(50, 200, g, this.hero.getDirection(), 0);
		
		// 画子弹
		if(hero.getShot() != null && hero.getShot().isLive()) {
			g.drawRect(hero.getShot().getX(), hero.getShot().getY(), 1, 1);
		}
		
		//  画出敌人的坦克
		for(int i = 0; i < ets.size(); i++){
			this.drawTank(ets.get(i).getX(), ets.get(i).getY(), g, ets.get(i)
					.getDirection(), 1);
		}
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W){
//			hero.setX(hero.getX() + 1);
			// 设置我的坦克的方向
			this.hero.setDirection(0);
			this.hero.moveUp();
		} else if(e.getKeyCode() == KeyEvent.VK_D){
			this.hero.setDirection(1);
			this.hero.moveRight();
		} else if(e.getKeyCode() == KeyEvent.VK_S){
			this.hero.setDirection(2);
			this.hero.moveDown();
		} else if(e.getKeyCode() == KeyEvent.VK_A){
			this.hero.setDirection(3);
			this.hero.moveLeft();
		}
		if(e.getKeyCode() == KeyEvent.VK_J){
			hero.shotEnemy();
		}
		
		this.repaint();
		
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void run(){
		while(true){
			try{
				Thread.sleep(100);
			} catch(Exception e){
				e.printStackTrace();
			}
			
			repaint();
		}
	}
}