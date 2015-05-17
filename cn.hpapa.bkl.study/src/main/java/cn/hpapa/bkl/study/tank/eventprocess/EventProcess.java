package cn.hpapa.bkl.study.tank.eventprocess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
 * 深入理解java事件处理机制
 * 1、事件源：
 * 事件源是一个产生（或触发）事件的对象，比如前面的JButtonde一个对象jb1.
 * 当这个事件源对象的某些状态以某种方式发生变化时，就会产生某种类型的事件（一个事件源可能会生成多个不同类型的事件）
 * 。如果某个组件（对象）希望得到事件源产生的事件，就需要在这个事件源上注册。
 * 2、事件
 * 事件就是承载事件源状态改变时的信息对象。或者说，事件是事件源向事件监听器传输事件源状态信息的载体。
 * 在用户与GUI组件进行交互时就会生成事件，比如当鼠标在面板中移动时，就会生成一个鼠标移动事件的随想，而这个对象保存着当前鼠标在面板中位置信息。
 * java.awt.event包和javax.swing.event包中定义了各种事件类型。常见的事件类：
 * 3、事件监听器接口
 * 事件源产生了一个事件，可以通过传送给事件监听者处理，那么怎样才能编写一个事件监听者呢？
 * 事件监听者实际上就是一个类，该类实现了某个事件监听器接口。比如MyPanel就是一个类。它实现了ActionListener接口。它就可以作为一个事件监听者，
 * 对接受到的事件进行处理。
 * 事件监听器接口有多种，不同的事件监听器接口可以监听不同的事件，一个类可以实现一个事件监听接口，也可以实现多个监听接口。
 * 
 * 事件处理编程步骤
 * 1、先编写事件处理类（事件监听者）
 * 2、根据需求给事件处理类实现监听器接口
 * 3、在事件处理类中重写（实现）其事件处理的函数
 * 4、在事件源类中指定该事件的监听其（响应者）是谁，即注册监听。
 * 
 * 注意事项
 * 1、java采用委托机制处理事件
 * 2、java中的事件是分类的，比如对窗体事件、鼠标事件、按键事件、操作事件（按按钮）
 * 3、java中一个类要监听某个事件，则必须实现相应的事件监听接口。
 * 4、事件监听接口有多重，程序员应当针对不同的情况，实现不同的监听接口，比如监听鼠标事件就应当实现MouseListener；
 * 要监听减半事件，就应当实现KeyListener。。
 * 5、在实现监听接口的类（事件监听类/者）中，需要重写处理函数，比如实现了ActionListener接口，就应当重写actionPerformed(ActionEvent e)，
 * 6、在事件源中需要注册事件监听类。否则事件监听类接收不到事件源发生的事件。
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
		jb2.addKeyListener(new OtherListener1());
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

class OtherListener1 implements KeyListener {

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("-----" + e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
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