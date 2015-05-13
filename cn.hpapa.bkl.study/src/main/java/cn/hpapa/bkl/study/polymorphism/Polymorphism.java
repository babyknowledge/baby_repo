package cn.hpapa.bkl.study.polymorphism;


/**
 * 多态
 * 所谓多态，就是指一个引用（类型）在不同情况下的多种状态。
 * 也可以理解为：通过父类的指针调用不同子类实现的方法。
 * @author yi
 * 
 * 注意事项：
 * 1、Java允许父类的引用变量去引用子类的实例（对象）
 * Animal an = new Cate();这种转换是自动完成的。
 * 2、
 * 
 * 多态就是一种类型表现出来的多种状态。
 *
 */
public class Polymorphism {

	public static void main(String[] args) {
		/*Animal a1 = new Cat();
		a1.cry();
		// 同一个引用
		a1 = new Dog();
		a1.cry();*/
		/*
		 * 
		 * 如果动物和食物再有增加，不会影响次部分代码
		 */
		Master m = new Master();
		m.feed(new Dog(), new Bone());
		
	}

}

class Animal{
	private int age;
	
	private String name;
	
	protected void cry(){
		System.out.println("不知道怎么叫");
	}
	
	public void eat() {
		System.out.println("不知道吃什么");
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class Cat extends Animal {
	public void cry() {
		System.out.println("喵喵");
	}
	
	public void eat(){
		System.out.println("鱼类");
	}
}

class Dog extends Animal {
	public void cry() {
		System.out.println("汪汪");
	}
	
	public void eat(){
		System.out.println("骨头");
	}
}

class Food {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void showName() {
		System.out.println("不知道吃什么");
	}
}

class Fish extends Food {
	public void showName() {
		System.out.println("猫爱吃鱼类");
	}
}

class Bone extends Food {
	public void showName() {
		System.out.println("狗爱吃骨头");
	}
}

class Master {
	// 给动物喂食物，使用多态方法就很方便。
	public void feed(Animal an, Food f){
		an.eat();
		f.showName();
	}
}
