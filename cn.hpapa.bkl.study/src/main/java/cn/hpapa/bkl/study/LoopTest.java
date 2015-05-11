package cn.hpapa.bkl.study;

public class LoopTest {

	public static void main(String[] args) {
		/**
		 * 1 int i = 0;
		 * 2 i < 10;
		 * 3 i++;
		 * 4 System.out.println("你好，我是刘德华");
		 * 执行顺序1-->2-->4-->3
		 *             ^       |
		 *             |_______|
		 */
		for(int i = 0; i < 10; i++){
			System.out.println("你好，我是刘德华");
		}
		
		/**
		 * 1 int j = 0;
		 * 2 j < 10;
		 * 3 j++;
		 * 4 System.out.println("你好，我是刘德华");
		 * 执行顺序1-->2-->3-->4
		 *             ^       |
		 *             |_______|
		 */
		int j = 0;
		while(j < 10){
			System.out.println("你好，我是刘德华");
			j++;
		}
		
		/**
		 * 1 int z = 0;
		 * 2 z < 10;
		 * 3 z++;
		 * 4 System.out.println("你好，我是刘德华");
		 * 执行顺序1-->2-->3-->4
		 *             ^       |
		 *             |_______|
		 */
		int z = 0;
		do{
			System.out.println("你好，我是刘德华");
			z++;
		}while(z < 10);
		
		int lay = 4;
		for(int i = 1; i <= lay; i++){
			for(int k = 1; k <= lay - i; k++){
				System.out.print(" ");
			}
			for(int a = 1; a <= 2 * i - 1; a++){
				if(i == 1||i == lay)
				System.out.print("*");
			}
			System.out.println("");
		}
	}

}
