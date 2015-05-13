package cn.hpapa.bkl.study.interfacetest;

/**
 * 类继类，接继接，类实接。
 * 接口：当一个类实现了接口，就要求该类把这个接口中的所有方法全部实现
 * @author yi
 * 1、接口不能被实例化
 * 2、接口中的所有方法都不能有方法体。
 * 3、一个类可以实现多个接口。
 * 4、接口中可以有变量【但变量不能用private和protected修饰】
 * 		接口中的变量默认都是static的，并且都是final的，不管你加不加static修饰
 * 		在Java中我们经常把常用的变量定义在接口中，作为全局变量。
 * 5、一个接口不能继承其他的类，但可以继承其他的接口。不能使用implements关键字
 * 
 * 接口体现了程序设计的多态和高内聚低耦合的设计思想。
 * 
 * 实现接口 vs 继承类
 * Java的继承是单继承，也就是一个类最多只能有一个父类，这种单继承的机制可以保证类的纯洁性，
 * 比C++中的多继承机制简洁。
 * 但是不可否认，对于子类功能扩展有一定影响。所以我们认为：
 * 1、实现接口可以看做是对继承的一种补充
 * 还有一点，继承是层级式的，不太灵活。这种结构修改某个类就会打破这种继承的平衡，
 * 而接口就是没有这样的麻烦，应为它只针对实现接口的类才起作用。所以：
 * 2、实现接口可在不打破继承关系的前提下，对某个类功能扩展。非常灵活。
 * 
 */
public class Interface {

	public static void main(String args[]){
		// 接口中的变量作为全局变量。
		System.out.println(Usb.a);
		Computer c = new Computer();
		Usb camera = new camera();
		Usb phone = new Phone();
		
		c.useUsb(camera);
		c.useUsb(phone);
	}
}

interface Usb extends Kkk {
	// 不加任何修饰，默认是  public final static int a = 1;
	int a = 1;
	
	public void start();
	
	public void stop();
}

class camera implements Usb {

	public void start() {
		System.out.println("我是照相机，我开始工作了！");
	}

	public void stop() {
		System.out.println("我是照相机，我停止工作了！");
	}

	public void cry() {
		// TODO Auto-generated method stub
		
	}
}

class Phone implements Usb {
	public void start() {
		System.out.println("我是手机，我开始工作了！");
	}

	public void stop() {
		System.out.println("我是手机，我停止工作了！");
	}

	public void cry() {
		// TODO Auto-generated method stub
		
	}
}

class Computer {
	
	public void useUsb(Usb usb){
		usb.start();
		usb.stop();
	}
}

interface Kkk {
	public void cry();
}

class Monkey {
	
	String name;
	
	public void jump(){
		System.out.println("猴子会跳！");
	}
}

interface Fish {
	public void swimming();
}

interface Bird{
	public void flying();
}

class Monkey1 extends Monkey implements Fish, Bird{

	public void flying() {
		System.out.println("猴子也会飞！");
	}

	public void swimming() {
		System.out.println("猴子也游泳！");
	}
}
