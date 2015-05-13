package cn.hpapa.bkl.study.polymorphism;

/**
 * 前期绑定：（编译时能确定的类型即前期绑定）在程序运行之前进行绑定，由编译器和连接程序实现，又叫静态绑定。比如static方法和final方法。主义，这里也包括private方法，因为它时隐式final的。
 * 后期绑定：（只有在运行时才能确定的类型为后期绑定）在运行时根据对象的类型进行绑定，由方法调用机制实现，因此又叫动态绑定，或者运行时绑定。除了前期绑定外的所有方法都符合后期绑定。
 * @author yi
 *
 */
public class CarShop {

	public void sellCar(Car car){
		System.out.println("汽车名称=" + car.getCarName() + "\n"
				+ "汽车价格=" + car.getCarPrice());
	}
	public static void main(String[] args) {
		CarShop cs = new CarShop();
		// 后期绑定
		cs.sellCar(new Bmw());
		cs.sellCar(new CherryQQ());
	}

}

interface Car {
	
	public String getCarName();
	
	public int getCarPrice();
}

class Bmw implements Car {

	public String getCarName() {
		return "BMW";
	}

	public int getCarPrice() {
		return 300000;
	}
}

class CherryQQ implements Car {
	public String getCarName() {
		return "Cherry QQ";
	}

	public int getCarPrice() {
		return 10000;
	} 
}

