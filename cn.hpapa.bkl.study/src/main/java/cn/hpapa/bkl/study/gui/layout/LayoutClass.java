package cn.hpapa.bkl.study.gui.layout;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 布局管理器
 * 
 * 边界布局注意事项
 * 1、不是五个部分都必须添加
 * 2、中部组件会自动的调节大小,去占据少添加的那个部分。
 * 3、JFrame，JDalog默认布局管理器就是BorderLayout
 * @author yi
 * 
 * swing开发流程
 * 
 * 1、继承Frame
 * 2、定义组件
 * 3、创建组件
 * 4、添加组件
 * 5、对顶层容器进行设置
 * 6、显示窗体
 */
public class LayoutClass extends JFrame {
	
	// 定义组件
	JButton jb1,jb2,jb3,jb4,jb5 = null;
	
	public LayoutClass() {
		jb1 = new JButton("中");
		jb2 = new JButton("北");
		jb3 = new JButton("东");
		jb4 = new JButton("南");
		jb5 = new JButton("西");
		
		// 添加各个组件
		this.add(jb1, BorderLayout.CENTER);
		this.add(jb2, BorderLayout.NORTH);
		this.add(jb3, BorderLayout.EAST);
		this.add(jb4, BorderLayout.SOUTH);
		this.add(jb5, BorderLayout.WEST);
		
		// 设置窗体
		this.setTitle("边界布局演示");
		this.setSize(300, 200);
		this.setLocation(200, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 显示窗体
		this.setVisible(true);
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LayoutClass lc = new LayoutClass();
	}

}
