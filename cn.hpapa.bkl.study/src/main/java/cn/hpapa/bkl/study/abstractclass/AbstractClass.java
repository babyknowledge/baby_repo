package cn.hpapa.bkl.study.abstractclass;

/**
 * 当父类方法具有不确定性时，使用抽象方法。
 * @author yi
 *
 * 1、抽象类不能被实例化；
 * 2、抽象类不一定要包含抽象（abstract）方法。也就是说，抽象类可以没有抽象方法。
 * 3、一旦包含了抽象方法，那么这个类必须被声明为abstract。
 * 4、抽象方法不能有方法体.
 */
public class AbstractClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 抽象类被实例化时，必须用下面的方法实现其中的抽象方法。
		Animal an = new Animal() {
			
			@Override
			public void cry() {
				// TODO Auto-generated method stub
				
			}
		};
	}

}

abstract class Animal {
	
	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public abstract void cry();
	
	public void eat(){
		
	}
}

// 当一个类继承的父类是抽象类的话，需要实现其中的所有抽象方法
class Cat extends Animal {

	// 实现父类的抽象方法
	@Override
	public void cry() {
		System.out.println("喵喵叫");
	}
	
}

class Dog extends Animal {

	// 实现父类的抽象方法
	@Override
	public void cry() {
		System.out.println("汪汪叫");
	}
	
}