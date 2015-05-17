package cn.hpapa.bkl.study.tank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicsClass extends JFrame {

	JPanel jp = null;
	
	public GraphicsClass() {
//		jp = new MyPanel();
		
		this.add(jp);
		
		this.setSize(1000, 1000);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphicsClass gc = new GraphicsClass();
	}

}

/*class MyPanel extends JPanel {
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawOval(10, 10, 30, 30);
		g.drawLine(50, 50, 30, 10);
		g.drawRect(110, 110, 50, 50);
		
		g.draw3DRect(150, 150, 50, 50, true);
		
		g.fillRect(120, 120, 50, 50);
		
		g.fillOval(10, 10, 30, 30);
		
		g.setFont(new Font("华文彩云", Font.BOLD, 30));
		
		g.setColor(Color.red);
		
		g.drawString("Hello world", 60, 60);
		
		
		Image im = Toolkit.getDefaultToolkit().getImage(
				Panel.class.getResource("/images/20130107.jpg"));
		g.drawImage(im, 90, 90, 200, 150, this);
	}
}*/
