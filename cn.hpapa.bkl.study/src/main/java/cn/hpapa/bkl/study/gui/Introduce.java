package cn.hpapa.bkl.study.gui;

import javax.swing.JButton;
/**
 * javax包，x表示扩展的意思
 */
import javax.swing.JFrame;

/**
 * GUI 介绍
 * @author yi
 *
 * Sun公司开发出JDK之后，由于在控制台操作使用户感觉极不方便。
 * 所以开发了一套工具集叫AWT（Abstract Window Toolkit）叫抽象窗口工具集。
 * 它是一堆类库，可以供开发人员调用。
 * 
 * 但是AWT有一个Icd的问题（本地化问题，即开发出来的图形界面在不同平台（Window和Linux）上运行效果有很大差别）。
 * 于是开发了一个叫swing的框架。可以保证在Window和Linux中展现界面一致。
 * 
 * 但是swing非常耗费内存，为了解决内存消耗的问题，于是IBM开发了SWT。
 * 
 * 后续IBM为了方便开发SWT程序，在SWT基础上又创建了一个更易用，功能强大的图形包JFace。
 * 
 * 布局管理器
 * 1、概念
 * 组件在容器（比如JFrame）中的位置和大小是由布局管理器来决定的。
 * 所有的容器都会使用一个布局管理器，通过它来自动进行组件的布局管理。
 * 
 * 2、种类
 * java共提供五种布局管理器：
 * 1、流式布局管理器
 * 边界布局管理器 FlowLayout：将容器简单的划分为东南西北中5个区域，中间区域最大。
 * 网格布局管理器 BorderLayout
 * 卡片布局管理器 GridLayout
 * 网格包布局管理器 GridBagLayout
 * 其中前三种是最常见的。
 */
public class Introduce extends JFrame {
	
	// 把需要的swing组件定义到这里，在构造函数中创建
	JButton jb = null;
	
	JButton jb1 = null;
	
	public Introduce(){
		
		jb = new JButton("我是按钮");
		
		jb1 = new JButton("北");
		
		// 给窗体设置标题
		this.setTitle("hello, world");

		// 设置大小，按像素【1像素】
		this.setSize(200, 200);

		// 添加JButton组件
		this.add(jb);
		this.add(jb1);

		// 设置当关闭窗口时，保证jvm也退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 设置初始位置
		this.setLocation(500, 500);

		// 显示
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// JFrame是一个顶层容器类（可以添加其它swing组件的类）
		
		Introduce introduce = new Introduce();
	}

}

/*class AWTTest extends java.awt.Window {

	AWTTest() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
*/