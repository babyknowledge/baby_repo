package cn.hpapa.bkl.study.reflection;

import java.lang.reflect.Method;

public class ReflectionClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gen<Integer> gen1 = new Gen<Integer>(11);
		
		gen1.showTypeName();
		
		Gen<String> gen2 = new Gen<String>("aaa");
		
		gen2.showTypeName();
		
		Gen<Bird> gen3 = new Gen<Bird>(new Bird());
		
		gen3.showTypeName();
		
	}

}

class Gen<T> {
	private T o;
	
	public Gen(T a){
		o = a;
	}
	
	public void showTypeName() {
		System.out.println("类型是：" + o.getClass().getName());
		// 通过反射机制，我们可以得到T这个类型的很多信息。
		Method m[] = o.getClass().getDeclaredMethods();
		for(int i = 0; i < m.length; i++){
			System.out.println(m[i].getName());
//			System.out.println(m[i].get);
		}
	}
}

class Bird {
	public void test1(){
		System.out.println("aa");
	}
	
	public void count(int a, int b){
		System.out.println(a + b);
	}
}
