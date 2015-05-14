package cn.hpapa.bkl.study.exception;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;

/**
 * 异常
 * @author yi
 *
 * Java中用2种方法处理异常
 * 1、在发生异常的地方直接处理。
 * 2、将异常抛给调用者，让调用者处理。
 * 
 * 异常分类
 * 1、检查性异常（编译异常）：java.lang.Exception。编译时即可探测出异常
 * 2、运行期异常：java.lang.RuntimeException。如：数组的下标越界异常
 * 3、错误：java.lang.Error
 * 
 * 无论哪种异常，顶层都是java.lang.Throwable
 * 
 * 检查性异常：程序正确，但因为外在环境条件不满足引发。
 * 例如：用户错误即I/O问题---程序视图打开一个并不存在的远程Socket端口，或者是打开不存在的文件时。
 * 这不是程序本身的逻辑错误。而很可能是远程机器名字错误（用户拼写错误）。
 * 对商用软件系统，程序开发者必须考虑并处理这个问题。Java编译器强制要求处理这类异常如果不捕获这类异常，程序将不能被编译。
 * 
 * 运行期异常：这以为这程序存在bug，如数据越界，0被正处，入参不满足规范。。。这类异常需要更改程序来避免，Java编译器强制要求处理这类异常
 * 
 * 错误：一般很少见，也很难通过程序解决。它可能源于程序的bug，但一般更可能源于环境问题，如内存耗尽，错误在程序中无需处理，而由运行环境处理。
 * 例如：程序服务器无法启动，是由于某杀毒软件不允许程序监听某端口造成。
 */
public class ExceptionClass {

	public static void main(String[] args) {
		/**
		 * 检查性异常
		 */
		// 检查性异常1、打开文件
		FileReader fr = null;
		Socket socket = null;
		try{
			fr = new FileReader("d:\\aa.txt");
			// 在抛出异常的地方就终止执行了代码
			// 然后进入到catch语句
			// 如果有多个catch语句，则进入匹配异常的那个catch代码块
			socket = new Socket("192.168.12.12",4567);
		} catch(FileNotFoundException e) {
			// 把异常的信息输出，利于排出bug
			e.printStackTrace();
			// 可以做相应处理
		} catch(IOException e){
			e.printStackTrace();
			// 可以做相应处理
		} finally{
			// 这个语句块不管有没有异常，都会执行
			// 一般说，把需要关闭的资源。【文件，数据库连接，开辟的内存】
			System.out.println("进入finally");
			try{
				if(fr != null)
					fr.close();// 如果文件不关闭，修改的内容则无法保存
				if(socket != null)
					socket.close();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
		
		// 检查性异常2、连接一个192.168.12.12 ip的端口4567
		/*try{
			Socket socket = new Socket("192.168.12.12",4567);
		} catch(IOException e){
			e.printStackTrace();
		}*/
		
		System.out.println("OK");
		
		/**
		 * 运行期异常
		 */
		// 运行期异常1、0被除
		// int a = 4 / 0;
		
		// 运行期异常2、数组越界
		// int arr[] = {1,2,3};
		// System.out.println(arr[5]);
		
		/**
		 * try...catch...
		 * 
		 * 在抛出异常的地方就终止执行了代码
		 * 然后进入到catch语句
		 * 如果有多个catch语句，则进入匹配异常的那个catch代码块
		 */
		/**
		 * finally
		 * 如果把finally块放在try。。。catch。。。语句后，
		 * finally块一般都会得到执行，它相当于一个万能的保险，即使前面的try块发生异常，
		 * 而又没有对应的catch块，finally块得到马上执行。
		 * 
		 * 以下情形，finally块不会被执行
		 * 1、finally块中发生异常
		 * 2、程序所在线程死亡
		 * 3、在前面的代码中用了System.exit();
		 * 4、关闭CPU。
		 */
	}

}
