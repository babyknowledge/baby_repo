package cn.hpapa.bkl.study.gui.jpanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * 登录框
 * @author yi
 * 
 * 网格布局
 *
 */
public class LoginClass extends JFrame {
	
	JTextField jtf = null;
	
	JPasswordField jpwf = null;
	
	JLabel admin = null;
	
	JLabel password = null;
	
	JPanel jp1,jp2, jp3 = null;
	
	JButton login = null;
	
	JButton cancel = null;
	
	ButtonGroup jbg = null;
	
	JRadioButton jrb1 = null;
	
	JRadioButton jrb2 = null;
	
	JCheckBox jcb1,jcb2 = null;
	
	public LoginClass() {
		jtf = new JTextField(10);
		jpwf = new JPasswordField(10);
		
		admin = new JLabel("管理员");
		
		password = new JLabel("密码");
		
		login = new JButton("登录");
		
		cancel = new JButton("取消");
		
		jrb1 = new JRadioButton("nan");
		jrb2 = new JRadioButton("nv");
		
		jcb1 = new JCheckBox("football");
		jcb2 = new JCheckBox("basketball");
		
		jbg = new ButtonGroup();
		
		jbg.add(jrb1);
		jbg.add(jrb2);
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		jp1.add(admin, BorderLayout.CENTER);
		jp1.add(jtf, BorderLayout.CENTER);
		jp1.add(jcb1, BorderLayout.CENTER);
		jp1.add(jcb2, BorderLayout.CENTER);
		
		jp2.add(password, BorderLayout.CENTER);
		jp2.add(jpwf, BorderLayout.CENTER);
		jp2.add(jbg, BorderLayout.CENTER);
		
		jp3.add(login, BorderLayout.CENTER);
		jp3.add(cancel, BorderLayout.CENTER);
		
		
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		this.setLayout(new GridLayout(3, 1, 10, 10));
		
		this.setSize(500, 400);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginClass lc = new LoginClass();
	}

}
