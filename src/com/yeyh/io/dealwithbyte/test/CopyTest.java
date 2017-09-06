package com.yeyh.io.dealwithbyte.test;

import com.yeyh.io.dealwithbyte.Copy;

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

		String oldPath = "F:/node-v6.11.2-win-x64/node.exe";
		String newPath = "F:/node-v6.11.2-win-x64/node1.exe";

		String oldPathFolder = "F:/logs";
		String newPathFolder = "F:/logs1";

		Copy.copyFile(oldPath, newPath);
		Copy.copyFolder(oldPathFolder, newPathFolder);
	}
}
