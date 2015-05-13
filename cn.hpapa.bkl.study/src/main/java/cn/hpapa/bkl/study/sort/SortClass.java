package cn.hpapa.bkl.study.sort;

/**
 * 排序
 * @author yi
 * 排序是讲一群数据，依指定的顺序进行排列的过程
 * 排序的分类
 * 1、内部排序：
 * 指定将需要处理的所有数据都加载到内部存储器中进行排序
 * 包括交换式排序、选择是排序和插入式排序法
 * 2、外部排序：
 * 数据量过大，无法全部加载到内存中，需要借助外部存储进行排序
 * 包括合并排序法和直接合并排序法。
 * 
 * 排序是数据处理中一种很重要的运算，同时也是很常用的运算，一般数据处理工作25%的时间都在进行排序。
 * 简单地说，排序就是把一组记录（元素）按照某个域的值的递增（即由小到大）或递减（即由大到小）的次序重新排列的过程。
 * 
 */
public class SortClass {
	
	public static void main(String args[]){
		int arr[] = {2,3,5,0,-1,9};
		
		BubbleSort bs = new BubbleSort();
		bs.sort(arr);
		for(int i = 0; i < arr.length; i++){
			System.out.println(arr[i]);
		}
	}

}

// 冒泡排序法
class BubbleSort {
	//排序方法
	public void sort(int arr[]) {
		// 排序
		// 外层循环,它决定一共走几趟
		int temp = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			// 内层循环，开始逐一比较。
			// 如果我们发现前一个数比后一个数大。则交换
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					// 换位。利用中间变量换位。
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}
}

class SelectionSort{
	
}
