package cn.hpapa.bkl.study;
/**
 * 
 * @author yi
 *
 * 类变量是该类的所有对象共享的变量，任何一个该类的对象去访问它时，取到的都是相同的值。
 * 同样，任何一个该类的对象去修改它时，修改的也是同一个变量。
 * 
 * 静态块的代码不需要创建对象才执行。类定义时已经执行。
 * 
 * Java中的规则，类变量（静态变量）原则上通过类方法（静态方法）去访问。
 * 
 * 类方法（静态方法）中不能访问非静态变量。普通方法既可以访问类变量（静态变量），也可以访问普通变量。
 */
public class StaticTest {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1 = new Person(23, "zhangsan");
		System.out.println(Person.getTotal());
		Person p2 = new Person(24, "lisi");
		p1.showInfo();
		p2.showInfo();
		System.out.println(Person.getTotal());
	}

}

class Person{
	// 可以不赋初始值
	static int age;
	
	String name;
	
	private static int total = 1;
	
	static{
		// 该静态块只被执行一次
		total++;
	}
	
	public Person(int age, String name){
		this.age = age;
		
		this.name = name;
		
		total++;
	}
	
	public void  showInfo(){
		System.out.println("age=" + this.age + "name=" + this.name);
	}
	
	public static int getTotal(){
		return total;
	}
}
