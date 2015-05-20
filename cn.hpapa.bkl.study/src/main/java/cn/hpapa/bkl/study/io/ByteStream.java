package cn.hpapa.bkl.study.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 演示字节流
 * @author yi
 * 先把图片读入到内存--->写入某文件
 * 因为是二进制文件，因此只能用字节流
 */
public class ByteStream {

	public static void main(String args[]){
		//思路：
		File f = new File("E:\\kk\\pic\\100APPLE_IMG_0725.JPG");
		
		// 输入流，将文件读入内存。
		InputStream is = null;
		
		// 输出流
		OutputStream os = null;
		try {
			is = new FileInputStream(f);
			
			os = new FileOutputStream("d:/kk.jpg");
			
			byte buf[] = new byte[1024];
			int n = 0;// 记录实际读取到的字节数
			// 循环读取
			while(true) {
				// 从输入流中读取1024个字节，放入到buf中。
				n = is.read(buf);
				System.out.println("-----------------+" + n);
				// 输出到指定文件
				os.write(buf);
				if(n == -1) {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
