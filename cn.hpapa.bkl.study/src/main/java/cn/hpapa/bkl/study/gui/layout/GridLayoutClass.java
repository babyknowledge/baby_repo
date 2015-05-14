package cn.hpapa.bkl.study.gui.layout;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 网格布局
 * @author yi
 * 
 * 网格布局，听其名而知其意，它将容器分割成多行多列，组件被填充到每隔网格中，
 * 添加到容器中的组件受限放置在左上角的网格中，任何从左到右放置其它的组件，
 * 当占满该行的所有网格后，接着继续在下一行从左到右放置组件。
 * 
 * 注意事项：
 * 1、组件的相对位置不随容器的缩放而变化，但大小会变化。
 * 2、所有组件的大小相同
 * 3、可以通过GridLayout(int ,int ,int, int)构造函数创建
 *
 */
public class GridLayoutClass extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int size = 9;
	JButton jbs[] = new JButton[size];
	
	public GridLayoutClass() {
		for(int i = 0; i < size; i++){
			jbs[i] = new JButton(String.valueOf(i));
		}
		this.setLayout(new GridLayout(3, 3, 30, 30));
		
		for(int i = 0; i < size; i++){
			this.add(jbs[i]);
		}
		
		this.setSize(300, 300);
		
		this.setLocation(300, 300);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
	
	// 设置网格布局
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GridLayoutClass glc = new GridLayoutClass();
	}
}
	
	
	
	



