package cn.hpapa.bkl.study.io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class NotePad extends JFrame implements ActionListener {

	JTextArea jta = null;
	
	// 定义菜单条
	JMenuBar jmb = null;
	// 菜单
	JMenu jm = null;
	// 菜单项
	JMenuItem jmi1, jmi2 = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NotePad np = new NotePad();
	}
	
	public NotePad(){
		jta = new JTextArea();
		
		jmb = new JMenuBar();
		jm = new JMenu("文件");
		
		jmi1 = new JMenuItem("Open");
		
		// 添加打开菜单的监听
		jmi1.addActionListener(this);
		// 添加命令
		jmi1.setActionCommand("open");
		jmi2 = new JMenuItem("Save");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");
//		jmi1 = new JMenuItem("Open", new ImageIcon("E:\\kk\\pic\\100APPLE_IMG_0725.JPG"));
		// 设置助记符
		jm.setMnemonic('F');
		
		// 加入菜单条
		this.setJMenuBar(jmb);
		// 把菜单放到菜单条中
		jmb.add(jm);
		
		// 把菜单项放入菜单
		jm.add(jmi1);
		jm.add(jmi2);
		this.add(jta);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(400, 300);
		
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("open")){
			System.out.println("open");
			// 隆重推荐JFileChooser
			// 创建文件选择组件
			JFileChooser jfc1 = new JFileChooser();
			// 设置名字
			jfc1.setDialogTitle("请选择文件。。。。。。");
			// 打开时使用默认
			jfc1.showOpenDialog(null);
			// 显示
			jfc1.setVisible(true);
			// 得到用户选择的文件绝对路径
			String path = jfc1.getSelectedFile().getAbsolutePath();
			BufferedReader reader = null;
			FileReader fr = null;
			try {
				fr = new FileReader(path);
				reader = new BufferedReader(fr);
				String s = "";
				String all = "";
				// 从文件中读取信息并显示到JTextArea中
				while((s = reader.readLine()) != null){
					all += s + "\r\n";
				}
				jta.setText(all);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				try {
					fr.close();
					reader.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if(e.getActionCommand().equalsIgnoreCase("save")){
			// 创建文件选择组件
			JFileChooser jfc1 = new JFileChooser();
			// 设置名字
			jfc1.setDialogTitle("另存为。。。。。。");
			// 打开时使用默认
			jfc1.showSaveDialog(null);
			// 显示
			jfc1.setVisible(true);
			// 得到用户选择的文件绝对路径
			String path = jfc1.getSelectedFile().getAbsolutePath();
			BufferedWriter writer = null;
			FileWriter fw = null;
			try {
				fw = new FileWriter(path);
				writer = new BufferedWriter(fw);
				String s= "";
				writer.write(this.jta.getText());
				/*while(true){
					s = this.jta.getText();
					if(s.equals("\n")){
						break;
					}
				}*/
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				try {
					writer.close();
					fw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

}
