package cn.hpapa.bkl.study.generatejava;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型
 * 
 * @author yi
 *
 * 泛型的有点
 * 1、类型安全
 * 2、向后兼容
 * 3、层次清晰
 * 4、性能较高。用GJ（泛型Java）编写的代码可以为Java编译器和虚拟机带来更多的类型信息，这些信息对Java程序做进一步优化提供条件。
 */
public class GenerateJavaClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Dog> dogs = new ArrayList<Dog>();
		Dog dog = new Dog();
		
		dogs.add(dog);
	}

}

class Dog {
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
	
}
