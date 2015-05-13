package cn.hpapa.bkl.study.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 数组
 * @author yi
 * 1、数组可以存放多个同一类型的数据。
 * 2、数组大小必须事先指定
 * 3、对象数组定义后，每个数组元素必须重新分配空间（new）
 * 4、数组名可以理解为指向数组首地址的指针。
 * 定义：
 * 数据类型 类型 数组名[] = new 类型[大小];
 * 初始化数组：数据类型 数组名[] = {元素值,元素值,...};
 */
public class ArrayClass {
	
	public float getMaxWight(Dog dogs[]){
		if(dogs == null || dogs.length == 0){
			return 0.0f;
		}
		float ret = 0.0f;
		for(int i = 0; i < dogs.length; i++){
			if(dogs[i].getWeight() > ret){
				ret = dogs[i].getWeight();
			}
		}
		return ret;
	}
	
	public float getWeightByName(Dog dogs[], String name){
		if(dogs == null || dogs.length == 0){
			return 0.0f;
		}
		for(int i = 0; i < dogs.length; i++){
			if(name.equalsIgnoreCase(dogs[i].getName())){
				return dogs[i].getWeight();
			}
		}
		return 0.0f;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// 定义一个可以存放6个float类型的数组
		float[] arr = new float[6];
		float[] arr1 = {1,3.4f,4,5,5};
		
		Dog dogs[] = { new Dog("花花", 4.5f), new Dog("白白", 5.5f),
				new Dog("黑黑", 3.5f), new Dog("红红", 6.5f) };
		
		
		ArrayClass as = new ArrayClass();
		System.out.println(as.getMaxWight(dogs));
		System.out.println(as.getWeightByName(dogs, "白白"));
		
		/*Dog dogs1[] = new Dog[4];
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		for(int i = 0; i < 4; i++){
			dogs1[i] = new Dog();
			System.out.println("请输入第" + (i + 1) + "狗的名字");
			String name = br.readLine();
			dogs1[i].setName(name);
			System.out.println("请输入第" + (i + 1) + "狗的体重");
			String weight = br.readLine();
			dogs1[i].setWeight(Float.parseFloat(weight));
		}*/
	}

}

class Dog {
	public Dog() {
		
	}
	public Dog(String name, float weight) {
		this.name = name;
		this.weight = weight;
	}
	private String name;
	
	private float weight;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
	
}
