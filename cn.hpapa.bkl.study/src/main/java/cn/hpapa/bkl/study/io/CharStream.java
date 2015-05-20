package cn.hpapa.bkl.study.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * 演示文件字符流的案例
 * @author yi
 *
 */
public class CharStream {

	public static void main(String[] args) {
		// 文件取字符流对象（输入流）
		Reader reader = null;
		// 写到文件的字符流对象（输出流）
		Writer writer = null;
		
		try {
			reader = new FileReader("d:/aa.txt");
			
			writer = new FileWriter("d:/ss.txt");
			
			char[] c = new char[1024];
			
			int n = 0;
			
			while(true){
				n = reader.read(c);
				writer.write(c, 0, n);
				if(n == -1){
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
				reader.close();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
