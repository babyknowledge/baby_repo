package cn.hpapa.bkl.study.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 演示缓冲字符流
 * @author yi
 * 
 *
 */
public class BufferCharStream {

	public static void main(String args[]){
		BufferedReader reader = null;
		
		BufferedWriter writer = null;
		
		try {
			reader = new BufferedReader(new FileReader("d:/aa.txt"));
			writer = new BufferedWriter(new FileWriter("d:/vv.txt"));
			String s = "";
			while((s = reader.readLine()) != null) {
				writer.write(s);
				writer.write("\r\n");
				System.out.println(s);
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
