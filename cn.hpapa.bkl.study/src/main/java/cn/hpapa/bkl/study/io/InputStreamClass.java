package cn.hpapa.bkl.study.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 演示FileInputStream类的使用
 * @author yi
 *
 */
public class InputStreamClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 得到一个文件对象 f->d:/aa.txt
		File f = new File("d:/aa.txt");
		
		// 因为File没有文件读写的能力，所以需要使用InputStream
		InputStream fis = null;
		try {
			fis = new FileInputStream(f);
			
			// 定义一个字节数组, 相当于一个缓存,不能一次全部读入内存
			byte bytes[] = new byte[1024];
			// 得到实际读取到的字节数
			int n = 0;
			// 循环读取
			while((n = fis.read(bytes)) != -1){
				// 把字节转成String
				String s = new String(bytes, 0, n);
				System.out.println(s);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e ){
			e.printStackTrace();
		}
		finally {
			try {
				// 关闭文件流必须放这里
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
