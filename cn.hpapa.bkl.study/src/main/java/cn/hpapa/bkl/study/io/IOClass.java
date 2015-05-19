package cn.hpapa.bkl.study.io;

import java.io.File;
import java.io.IOException;

/**
 * 文件
 * @author yi
 * 
 * 文件大体分类：文本文件和二进制文件
 * 
 * 概念：文件时数据源（保存数据的地方）的一种，比如大家经常使用的word文档，txt文件都是文件。
 * 文件最重要的左右就是保存数据，它既能保存一张图片，也能保存文字
 * 文件在程序中是以流的形式来操作的。
 *           
 * 内存到文件：输出流
 * 文件到内存：输入流
 * 如何判断是输入流还是输出流：以内存为参造物，如果数据是向内存流动则是输入流，反之是输出流。
 * 流：数据在数据源（文件）和程序（内存）之间经历的路径。
 * 输入流：数据从数据源（文件）到程序（内存）的路径。
 * 输出流：数据从程序（内存）到数据源（文件）的路径。
 * 
 * Java流分为两种，
 * 1、字节流：可以用于读写二进制文件及任何文件类型。
 * 2、字符流：可以用于文本文件。
 * 
 *          字节流              字符流
 * 输入      InputStream    Reader
 * 输出      OutputStream   Writer
 * 
 * File类基本操作
 * 
 * 操作系统
 */
public class IOClass {

	public static void main(String args[]) throws IOException{
		// 创建一个文件
		File f = new File("d:/aa.txt");
		
		// 得到文件的路径
		System.out.println("文件路径：" + f.getAbsolutePath());
		
		// 得到文件的大小，字节数
		System.out.println("大小" + f.length());
		
		// 文件是否可读
		System.out.println(f.canRead());
		// 文件是否可写
		System.out.println(f.canWrite());
		
		// 创建文件和文件夹
		File fNew = new File("d:/hsp.txt");
		if(!fNew.exists()){
			// 可以创建
			fNew.createNewFile();
		}
		if(!fNew.isDirectory()){
			System.out.println("文件夹不存在");
		}
		
		// 创建文件夹 
		/*File fDirectory = new File("d:/ff");
		if(!f.isDirectory()){
			fDirectory.mkdir();
		}*/
		
		// 列出一个文件夹下的所有文件
		// 文件夹是一种特殊的文件
		File fl = new File("E:\\Dell\\bakup\\personal");
		if(fl.isDirectory()){
			File listFile[] = fl.listFiles();
			for(int i = 0; i < listFile.length; i++){
				System.out.println(listFile[i].getAbsolutePath());
			}
		}
	}
}
