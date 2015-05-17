package cn.hpapa.bkl.study.tank.eventprocess;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 加深对事件处理机制的理解
 * @author yi
 * 
 * 1、通过上下左右键控制一个小球的移动
 *
 */
public class EventProcessMoveObj extends JFrame {
	
	Panel p = null;
	public static void main(String args[]){
		EventProcessMoveObj epmo = new EventProcessMoveObj();
	}
	
	public EventProcessMoveObj() {
		p = new Panel();
		
		this.add(p);
		
		// 实现监听
		this.addKeyListener(p);
		
		this.setSize(400, 300);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}

class Panel extends JPanel implements KeyListener {
	
	private int x;
	
	private int y;
	
	
	public void paint(Graphics g){
		super.paint(g);
		
		g.fillOval(x, y, 20, 20);
	}

	/**
	 * 键的一个值被输出
	 */
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 按下键
	 */
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("被按下。。。" + e.getKeyChar());
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			if(y <= 300)
				y++;
		} else if(e.getKeyCode() == KeyEvent.VK_UP){
			if(y != 0)
				y--;
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			if(x <= 400)
				x++;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(x != 0)
				x--;
		} else {
			
		}
		
		// 调用repaint函数来重绘。
		this.repaint();
	}

	/**
	 * 松开键
	 */
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
