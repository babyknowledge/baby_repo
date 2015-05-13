package cn.hpapa.bkl.study.override;

/**
 * 重写（覆盖）
 * 子类与父类的入参、返回值必须完全一致。
 * 子类不可以缩小父类的修饰符,但可以扩大父类的修饰符
 * @author yi
 *
 */
public class Override {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Animal c = new Cat();
		c.cry();
		
	}

}

class Animal{
	int age;
	
	String name;
	
	protected void cry(){
		System.out.println("不知道怎么叫");
	}
}

class Cat extends Animal {
	public void cry() {
		System.out.println("喵喵");
	}
}

class Dog extends Animal {
	public void cry() {
		System.out.println("汪汪");
	}
}
