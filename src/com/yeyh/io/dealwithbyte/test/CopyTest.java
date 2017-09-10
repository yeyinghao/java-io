package com.yeyh.io.dealwithbyte.test;

import java.io.File;

import com.yeyh.io.dealwithbyte.Copy;
import com.yeyh.io.dealwithbyte.DeleteFoler;

/**
 * 
 * ClassName: CopyTest
 * 
 * @Description: 字节处理测试类
 * @author Yeyh
 * @date 2017年9月6日
 */
public class CopyTest {

	public static void main(String[] args) {

		// String oldPath = "F:/node-v6.11.2-win-x64/node.exe";
		// String newPath = "F:/node-v6.11.2-win-x64/node1.exe";
		//
		String oldPathFolder = "I:\\zookeeper-3.5.3";
		String newPathFolder = "I:\\zookeeper-3.5.31";

		// File s = new File("D:\\Java-development-tools\\android-sdk-windows");
		// File t = new File("I:\\Java-development-tools\\android-sdk-windows");

		// Copy.fileChannelCopy(s, t);
		// Copy.copyFile(oldPath, newPath);
		long start = System.currentTimeMillis();

		System.out.println("复制开始");
		//Copy.copyFolder(oldPathFolder, newPathFolder);
		DeleteFoler.delAllFile("I:\\zookeeper-3.5.31");
		System.out.println("复制结束");
		long end = System.currentTimeMillis();

		System.out.println("共耗时: " + (end - start));
	}
}
