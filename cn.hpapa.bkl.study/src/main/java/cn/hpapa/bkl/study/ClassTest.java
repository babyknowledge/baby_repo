package cn.hpapa.bkl.study;


public class ClassTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cat c = new Cat();
	}

}

class Cat{
	private int age;
	
	private String name;
	
	private String color;
	
	private Master myMaster;

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Master getMyMaster() {
		return myMaster;
	}

	public void setMyMaster(Master myMaster) {
		this.myMaster = myMaster;
	}
}
class Master{
	private int age;
	
	private String name;
	
	private String address;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 与cal(int n)是重载关系
	 * 如果参数相同，返回值不同，则不能重载
	 */
	public void cal(){
		int result = 0;
		for(int i = 0; i < 100; i++){
			result+=i;
		}
		System.out.println("result=" + result);
	}
	
	/**
	 * 与cal是重载关系
	 * 如果参数相同，返回值不同，则不能重载
	 * @param n
	 */
	public void cal(int n){
		int result = 0;
		for(int i = 0; i < n; i++){
			result+=i;
		}
		System.out.println("result=" + result);
	}
	
}
