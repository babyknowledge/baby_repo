package cn.hpapa.bkl.study.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/**
 * 演示FileOutputStream的使用
 * @author yi
 *
 */
public class OutputSteam {

	public static void main(String[] args) {
		File f = new File("d:/aa.txt");
		// 字节输出流
		OutputStream os = null;
		
		try {
			os = new FileOutputStream(f);
			
			String s = "中国人";
			
			os.write(s.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
