package cn.hpapa.bkl.study.exception;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Father f = new Father();
		f.test1();
	}

}

class Father {
	private Son son = null;
	
	public Father() {
		son = new Son();
	}
	public void test1() {
		
		// FIXME 调用者处理异常时如何关闭文件。
		System.out.println("1");
		try{
			son.test2();
		} catch (FileNotFoundException e){
			System.out.println("父亲在处理异常");
			e.printStackTrace();
		} finally {
			System.out.println("执行finally");
			FileReader fr = son.getFr();
			if(fr != null){
				try{
					fr.close();
				} catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
}

class Son {
	private FileReader fr = null;
	
	public void test2() throws FileNotFoundException {
		fr = new FileReader("d:/aa.txt");
	}

	public FileReader getFr() {
		return fr;
	}

	public void setFr(FileReader fr) {
		this.fr = fr;
	}
	
}
