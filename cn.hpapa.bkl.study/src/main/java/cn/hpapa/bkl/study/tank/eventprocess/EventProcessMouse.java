package cn.hpapa.bkl.study.tank.eventprocess;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class EventProcessMouse extends JFrame {
	
	MyPanel1 mp1 = null;
	
	public static void main(String args[]){
		EventProcessMouse epm = new EventProcessMouse();
	}
	
	public EventProcessMouse() {
		mp1 = new MyPanel1();
		
		this.add(mp1);
		
		this.addMouseListener(mp1);
		this.addMouseMotionListener(mp1);
		this.addWindowListener(mp1);
		
		this.setSize(400, 300);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
	}
}

class MyPanel1 extends JPanel implements MouseListener, MouseMotionListener,
		WindowListener {
	
	public void paint(Graphics g){
		super.paint(g);
	}

	/**
	 * 鼠标被点击
	 */
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX() + "y=" + e.getY());
	}

	/**
	 * 鼠标按下去但没松开
	 */
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println();
	}

	/**
	 * 鼠标松开
	 */
	public void mouseReleased(MouseEvent e) {
		
	}

	/**
	 * 鼠标移动到MyPanel1
	 */
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("鼠标来");
		
	}

	/**
	 * 鼠标离开MyPanel1
	 */
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("鼠标走");
	}

	/**
	 * 拖拽
	 */
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 移动
	 */
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("鼠标当前坐标：x=" + e.getX() + "y=" + e.getY());
	}

	/**
	 * 窗口打开了
	 */
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 窗口正在关闭
	 */
	public void windowClosing(WindowEvent e) {
		System.out.println("window Closing!");
	}

	/**
	 * 窗口关闭了
	 */
	public void windowClosed(WindowEvent e) {
		System.out.println("window Closed!");
		
	}

	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 窗口最小化了
	 */
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 窗口激活了（重新打开了）
	 */
	public void windowActivated(WindowEvent e) {
		System.out.println("window activated!");
	}

	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
