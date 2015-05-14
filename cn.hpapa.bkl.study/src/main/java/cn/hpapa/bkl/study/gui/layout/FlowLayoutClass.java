package cn.hpapa.bkl.study.gui.layout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 流式布局
 * 
 * @author yi
 * 注意事项：
 * 1、不限制它所管理的组件大小，允许他们有最佳大小。
 * 2、当容器大小缩放时，组件的位置可能发生变化，但组件的大小不变。
 * 3、默认组件时居中对齐，可以通过FlowLayout(int align)函数来制定对齐方式
 */
public class FlowLayoutClass extends JFrame {

	JButton jb1, jb2, jb3, jb4, jb5, jb6 = null;
	
	public FlowLayoutClass() {
		jb1 = new JButton("1");
		jb2 = new JButton("2");
		jb3 = new JButton("3");
		jb4 = new JButton("4");
		jb5 = new JButton("5");
		jb6 = new JButton("6");
		
		this.add(jb1);
		this.add(jb2);
		this.add(jb3);
		this.add(jb4);
		this.add(jb5);
		this.add(jb6);
		
		// 设置布局管理器
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		// 禁止用户改变窗体大小
		this.setResizable(false);
		
		// 设置窗体
		this.setTitle("边界布局演示");
		this.setSize(200, 200);
		this.setLocation(200, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 显示窗体
		this.setVisible(true);
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FlowLayoutClass flc = new FlowLayoutClass();
	}

}
