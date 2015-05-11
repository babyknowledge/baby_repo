package cn.hpapa.bkl.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Hello world! 控制台输入
 */
public class SystemIn {
	public static void main(String[] args) {
		Cat c = new Cat();
		System.out.println(c.getAge());
		try {
			// System.in标准输入
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			// 请输入第一个数
			System.out.println("请输入第一个数...");
			String a1 = br.readLine();
			// 请输入第二个数
			System.out.println("请输入第二个数...");
			String a2 = br.readLine();

			System.out.println(a1 + "\n" + a2);
//			SystemIn si = new SystemIn();
//			si.xjj(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void xjj(int n){
		for(int i = 1; i <= n; i++){
			for(int j = i; j <= n; j++){
				System.out.print(i + "*" + j + "=" + i*j);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}


