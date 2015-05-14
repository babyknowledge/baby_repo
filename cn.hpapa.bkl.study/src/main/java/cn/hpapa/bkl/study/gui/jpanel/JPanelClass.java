package cn.hpapa.bkl.study.gui.jpanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * JPanel
 * @author yi
 * 
 * 注意事项
 * 1、JP媕娿链式JComponent的子类。
 * 2、属于容器类组件，可以加入别的组件。
 * 3、默认布局管理是流式布局（FlowLayout）
 *
 */
public class JPanelClass extends JFrame {

	// 定义组件
	JPanel jp1,jp2 = null;
	
	JButton jb1,jb2,jb3,jb4,jb5,jb6 = null;
	
	public JPanelClass(){
		// 创建组件
		// JP俺饿了布局默认是FlowLayout。
		jp1 = new JPanel();
		jp2 = new JPanel();
		
		jb1 = new JButton("西瓜");
		jb2 = new JButton("苹果");
		jb3 = new JButton("荔枝");
		jb4 = new JButton("橘子");
		jb5 = new JButton("香蕉");
		jb6 = new JButton("草莓");
		
		// 设置布局管理器
		
		// 添加组件
		jp1.add(jb1);
		jp1.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		jp2.add(jb5);
//		jp2.add(jb6);
		
		this.add(jp1, BorderLayout.NORTH);
		
		this.add(jb6, BorderLayout.CENTER);
		
		this.add(jp2, BorderLayout.SOUTH);
		
		this.setSize(300,150);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JPanelClass jpc  = new JPanelClass();
	}

}
