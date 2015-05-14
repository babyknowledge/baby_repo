package cn.hpapa.bkl.study.collection;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

/**
 * 集合类
 * 
 * @author yi
 *
 * Java中常用的集合类
 * 1、List结构的集合类：ArrayList/LinkedList/Vector/Stack
 * ArrayList和LinkedList都有序，AL每次add时最加到最后一个，LL默认也是追加到最后一个，但可以调用addFirst方法在前面追加。
 * ArrayList和LinkedList都是线程不安全的，
 * ArrayList是用数组实现，查找效率高，修改和删除效率低
 * LinedList是用链表实现，查找时需要根据指针一个一个的查找，直到找到，效率低，删除只需要操作指针即可，效率高
 * Vector也是用数组实现的，是线程安全因此效率低。Vector如果超容量自动增加一倍，ArrayList超容量自动增加50%。因此Vector浪费空间。
 * 2、Map结构的集合类：HashMap/HashTable
 * 区别：都是Java的集合类
 * 		1、历史原因：HashTable是基于陈旧的Dictionary类的，HashMap是Java1.2引进的Map接口的一个实现
 * 		2、同步性：HashTable是同步的。这个类中的一些方法保证了HashTable中的对象是线程安全的。
 * 			而HashMap则是异步的，因此HashMap中的对象并不是线程安全的，因为同步的要求会影响执行的效率，所以如果你不需要线程安全的集合那么使用HashMap是一个很好的选择，这样可以避免由于同步带来的不必要的性能开销，从而提高效率
 * 		3、值：HashMap可以让你将空值作为一个表的条目的Key或value。但HashTable是不能放入空值的（null）。
 * 3、Set结构的集合类：HashSet/TreeSet
 * 4、Queue结构的集合类：Queue接口
 * 
 * 如果要求线程安全选择Vector/Hashtable
 * 如果不要求线程安全，使用ArrayList/LinkedList/HashMap
 * 如果要求键值对，使用HashMap/Hashtable
 * 如果数据量很大，又要求线程安全考虑Vector
 */
public class CollectionClass {

	public static void main(String args[]){
//		System.exit(0);
		Vector v = new Vector();
		v.add("111");
		v.add("222");
		
		for(int i = 0; i < v.size(); i++){
			System.out.println("v=" + v.get(i));
		}
		
		//默认在最前一个追加
		Stack s = new Stack();
		s.add("111");
		s.add("222");
		for(int i = 0; i < s.size(); i++){
			System.out.println("s=" + s.get(i));
		}
		
		LinkedList ll = new LinkedList();
		ll.addFirst("111");
		ll.addFirst("222");
		
		for(int i = 0; i < ll.size(); i++){
			System.out.println("ll=" + ll.get(i));
		}
		
		HashMap hm = new HashMap();
		hm.put(null, null);
		
		Hashtable ht = new Hashtable();
//		ht.put(null, null);
		
//		System.out.println(ht.get(null));
		System.out.println(hm.get(null));
	}
}
