package cn.hpapa.bkl.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author yi
 * 条件表达式数据类型，应和case后的常量类型一致.
 * 只允许byte short int char enum类型
 */
public class SwitchTest {

	public static void main(String args[]) throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int a;
		a = Integer.parseInt(br.readLine());
		switch(a){
		case 1 : System.out.println("");break;
		default : break;
		}
	}
}
