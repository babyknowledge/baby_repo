package cn.hpapa.bkl.study.finalpkg;

/**
 * Final
 * @author yi
 *
 * 在某些情况下，程序员可能有以下需求：
 * 1、当不希望父类的某个方法被子类覆盖（override）时，可以使用final关键字修饰。
 * 2、当不希望某个变量被修改时，可以定义为final。如果一个变量是final的则必须被初始化，否则编译不通过。
 * 3、当不希望类被继承时，可以使用final修饰。
 * 
 * 注意事项：
 * 1、final修饰的变量又叫常量，一般用xx_xx_xx命名。
 * 2、final修饰的变量在定义时，必须被赋值，并且以后不能再赋值。
 * 
 * final-什么时候使用
 * 1、因为安全的考虑，某个类或某个方法不允许修改。
 * 2、类不会被其他类继承。
 * 3、某些变量值是固定不变的，比如圆周率。
 */
public class Final {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/*final */class FinalClass {
	final int a = 1;
	public final void sendMes(){
		System.out.println("发送消息！");
	}
}

class SubFinalClass extends FinalClass {
	
	public SubFinalClass(){
		// 不可以修改a的值
		//a++;
	}
	/* 因为父类该方法已经被定义为final
	public void sendMes(){
		System.out.println("发送消息！");
	}
	*/
}
