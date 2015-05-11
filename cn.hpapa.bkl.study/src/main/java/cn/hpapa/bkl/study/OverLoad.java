package cn.hpapa.bkl.study;

/**
 * 重载，方法名相同，入参不同。
 * 相同的入参返回值不同，不可重载。
 * @author yi
 *
 */
public class OverLoad {

	public void overLoad(){
		System.out.println("");
	}
	
	public void overLoad(int n){
		System.out.println(n);
	}
	
	/* 与public void overLoad(int n)不可重载
	public int overLoad(int n){
		return n;
	}
	*/
}
