package cn.hpapa.bkl.study.tank.eventprocess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 事件处理机制
 * @author yi
 * Java事件处理是采用“委派事件模型”。所谓“委派事件模型”是指当事件发生时，产生事件的对象（即事件源），
 * 会把此“信息”传递给“事件的监听者”(一个类，可以是一个JFrame，Panel或者一个普通的类只要实现了ActionListener接口)的一种方式，
 * 而这里所说的“信息”实际上就是java.awt.event事件类库里某个类所创建的对象，我们暂时把它称作“事件的对象”。
 *                 事件（ActionEvent)
 * 事件源（按钮）------------------------->事件监听者（事件处理方法）
 * 一个类实现监听的步骤
 * a、事件监听接口：KeyListener/MouseListener/ActionListener/WindowListener
 * b、把接口的处理方法重写。
 * c、在事件源上注册监听。
 * d、事件传递是靠事件对象（ActionEvent、KeyEvent）
 * 
 */

public class EventProcess extends JFrame implements ActionListener{
	
	JPanel mp = null;
	
	JButton jb1, jb2 = null;
	
	public static void main(String args[]){
		EventProcess ep = new EventProcess();
	}
	
	public EventProcess() {
		mp = new JPanel();
		jb1 = new JButton("黑色");
		jb2 = new JButton("红色");
		
		this.add(jb1, BorderLayout.NORTH);
		mp.setBackground(Color.BLACK);
		this.add(mp);
		this.add(jb2, BorderLayout.SOUTH);
		
		// 注册监听
		jb1.addActionListener(this);
		OtherListener ol = new OtherListener();
		// jb1就是事件源，ol就是监听者
		jb1.addActionListener(ol);
		jb2.addActionListener(this);
		jb2.addActionListener(ol);
		// 指定action命令
		jb1.setActionCommand("black");
		jb2.setActionCommand("red");
		
		
		this.setSize(200, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// 对事件处理的方法
	public void actionPerformed(ActionEvent e) {
//		System.out.println("OK");
		
		// 判断是监听哪个按钮
		if("black".equalsIgnoreCase(e.getActionCommand())){
			mp.setBackground(Color.BLACK);
		} else if("red".equalsIgnoreCase(e.getActionCommand())){
			mp.setBackground(Color.RED);
		}
	}
}

class OtherListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		if("black".equalsIgnoreCase(e.getActionCommand())){
			System.out.println("black other listener");
//			mp.setBackground(Color.BLACK);
		} else if("red".equalsIgnoreCase(e.getActionCommand())){
			System.out.println("red other listener");
//			mp.setBackground(Color.RED);
		}
	}
	
}

class MyPanel extends JPanel {
	
	public void paint(Graphics g){
		super.paint(g);
	}
}