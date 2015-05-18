package cn.hpapa.bkl.study.thread;

/**
 * 线程
 * @author yi
 * 
 * 进程-概念
 * 要解释线程，就必须明白什么是进程，就好像要搞清中国历史，就必须要了解春秋战国。
 * 
 * 什么是进程呢？
 * 进程是指运行中的应用程序，每个进程都有自己独立的地址空间（内存空间），比如用户点击桌面的IE浏览器，就启动了一个进程，
 * 操作系统就会为该进程分配独立的地址空间，当用户再次点击桌面的IE浏览器，又启动了一个进程，操作系统将为新的进程分配新的独立的地址空间。
 * 目前操作系统都支持多进程。
 * 
 * 线程-概念
 * 在明白进程后，我们就比较容易理解线程的概念了。
 * 
 * 什么是线程呢？
 * 是进程中的一个实体，是被系统独立调用和分派的基本单位，线程自己不拥有系统资源，只拥有一点在运行中必不可少的资源。
 * 但它可与同属一个进程的其它线程共享所拥有的全部资源，一个线程可以创建和撤销另一个线程，同一进程中的多个线程之间可以并发执行。线程由就绪、阻塞和运行三种基本状态。
 * 
 * 另一种说法
 * 什么是线程呢？
 * 1、线程是轻量级的进程。
 * 2、线程没有独立的地址空间（内存空间）。
 * 3、线程是由进程创建的（寄生在进程）。
 * 4、一个进程可以拥有多个线程-->这就是我们常说的多线程编程。线程增加对应的进程的地址空间也随之变大.
 * 5、线程由几种状态：
 * a、新建状态(new)
 * b、就绪状态(Runnable)
 * c、运行状态(Running)
 * d、阻塞状态(Blocked)
 * e、死亡状态(Dead)
 * 
 * new --> Runnable(操作系统判断资源是否允许运行) --> 
 * running(如果内存不够、文件正在被读取) --> 阻塞(操作系统判断内存是否够用了，文件是否被其他释放了)
 * --> Runnable --> running(执行完成) --> dead
 * 
 * 线程-如何使用
 * 在java中任何一个类都可以作为一个线程类
 * 在java中一个类要当做线程来使用就有两种方法。
 * 1、继承Thread类，并重写run函数。
 * 2、实现Runnable接口，并重写run函数。(如果一个类已经继承了某个父类，这时希望也能实现多线程的操作，就可以实现Runnable接口)
 * 3、尽量使用实现Runnable接口的方式实现多线程。
 *
 */
public class ThreadClass {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ThreadTest tt = new ThreadTest();
		// 启动线程,导致run函数的运行
//		tt.start();
		
		// 
//		RunnableTest rt = new RunnableTest();
//		Thread t = new Thread(rt);
//		t.start();
		
		Bird b = new Bird(10);
		Thread t1 = new Thread(b);
		t1.start();
		
		Pig p = new Pig();
		Thread t2 = new Thread(p);
		t2.start();
		
	}

}

class ThreadTest extends Thread {
	
	int times = 0;
	
	public void run(){
		while(true){
			// 休眠一秒
			try {
				// sleep就会让该线程进入到Blocked状态。
				// 并释放自己占用的CPU、内存等资源
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("ThreadTest hello world");
			times++;
			if(times == 10){
				// 调出while循环
				break;
			}
		}
	}
}

class RunnableTest implements Runnable {

	int times = 0;
	
	public void run() {
		while(true){
			// 休眠一秒
			try {
				// sleep就会让该线程进入到Blocked状态。
				// 并释放自己占用的CPU、内存等资源
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("我是一个线程，正在输出第" + times + "个Hello world");
			times++;
			if(times == 10){
				// 调出while循环
				break;
			}
		}
	}
}

class Bird implements Runnable {
	int n = 0;
	
	int res = 0;
	
	int times = 0;
	
	public Bird( int n){
		this.n = n;
	}
	
	public void run(){
		while(true){
			try{
				Thread.sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			res += (++times);
			System.out.println("当前结果是：" + res);
			if(times == n){
				System.out.println("res = " + res);
				break;
			}
		}
	}
}

class Pig implements Runnable {
	
	int times = 0;
	
	public void run(){
		while(true){
			// 休眠一秒
			try {
				// sleep就会让该线程进入到Blocked状态。
				// 并释放自己占用的CPU、内存等资源
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("我是一个线程，正在输出第" + times + "个Hello world");
			times++;
			if(times == 10){
				// 调出while循环
				break;
			}
		}
	}
}


