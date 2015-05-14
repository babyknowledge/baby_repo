package cn.hpapa.bkl.study.binary;

/**
 * 二进制
 * 
 * @author yi
 * 对于有符号的数而言
 * 1、二进制的最高位是符号位：0表示正数，1表示负数
 * 2、正数的原码、反码、补码都一样。
 * 3、负数的反码=它的原码符号位不变，其它位取反。
 * 4、负数的补码=它的反码+1
 * 5、0的反码，补码都是1
 * 6、Java没有无符号数，换言之，Java中的数都是有符号的。
 * 7、在计算机运算的时候，都是以补码的方式来运算的。
 * 8、负数要想参加运算应该先换算成其补码，即 原码-->反码-->补码-->运算后如果是负数-->反码-->原码
 * 		
 * Java中有三个移位运算符
 * <<算数左移 >>算数右移
 * 算数右移：低位溢出，符号位不变，并用符号位补溢出的高位
 * 算数左移：符号位不变，低位补0.
 * 逻辑右移：低位溢出，高位补0.
 */
public class BinaryClass {

	public static void main(String args[]){
//		System.out.println(~1);
//		System.out.println(1>>2);
//		System.out.println(~2);
		
		System.out.println(2 << 4);
	}
}
