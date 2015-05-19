package cn.hpapa.bkl.study.thread;
/**
 * 演示线程注意事项
 * @author yi
 * 
 * 注意事项：
 * 1、不管通过哪种方式创建的线程，它们的一个对象只能启动一次。否则会有异常抛出。
 * 2、java处理线程同步的方法非常简单，只需要在需要同步的代码段用synchronized(Object){你要同步的代码}即可，
 * 就好像某位通知在上厕所前先把门关上（上锁），完事后再出来（解锁），那么别的同志就可再使用厕所了。
 * 
 * 对同步机制的解释
 * 如果有多个线程因等待同一个对象的标志位而处于阻塞状态时，当该对象的标志位恢复到1状态时，
 * 只会有一个线程能够进入同步代码执行，其它的线程让然处于阻塞状态。
 * 特殊说明：
 * 1、我们上面说的对象的标志位用术语讲就是对象锁。
 * 2、synchronized(Object) // object可以是任意类型对象
 */
public class ThreadNote {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicketWindow tw1 = new TicketWindow();
//		TicketWindow tw2 = new TicketWindow();
//		TicketWindow tw3 = new TicketWindow();
		
		Thread t1 = new Thread(tw1);
		Thread t2 = new Thread(tw1);
		Thread t3 = new Thread(tw1);
		
		t1.start();
		t2.start();
		t3.start();
	}

}

// 售票窗口类
class TicketWindow implements Runnable{
	// 一共2000张票
	private int nums = 2000;
	
	private Dog myDog = new Dog();
	public void run() {
		while(true){
			// 出票的速度是一秒出一张
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 以下代码要保证其原子性,this是对象锁
			// 对象锁是任意一个对象，每个对象锁都有0、1两个状态
			// 开始是1，进入代码块执行，并将1变成0。表示被占用。
			// 直到执行完整个sync语句总的代码块后，该对象的标志位又回到1状态。
			// 如果是0，进入等待池中排在第一位
			// 如果线程执行完，对象锁不置为1，则出现死锁状态，所有后续线程都堆在等待池
			// 对象锁可以是任意对象，比如this、某个类的实例
			synchronized (myDog) {// 或者synchronized(this)
				// 判断是否还有票
				if (nums > 0) {
					// Thread.currentThread().getName() 当前线程的名字
					System.out.println(Thread.currentThread().getName()
							+ " 正在售出第 " + nums + " 张票");
					nums--;
				} else {
					// 售票结束
					break;
				}
			}
		}
	}
}

class Dog {
	
}
