package com.yeyh.io.dealwithbyte;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * 
 * ClassName: Copy
 * 
 * @Description: 复制
 * @author Yeyh
 * @date 2017年9月6日
 */
public class Copy {

	/**
	 * 
	 * @Description: 复制单个文件
	 * @param oldPath
	 *            原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            复制后路径 如：f:/fqf.txt
	 * @author Yeyh
	 * @date 2017年9月6日
	 */
	public static void copyFile(String oldPath, String newPath) {
		InputStream inputStream = null; // 输入流 读
		OutputStream outputStream = null;// 输出流 写
		try {
			int bytesum = 0;// 记录字节数
			File oldfile = new File(oldPath);// 取出要复制的文件
			if (oldfile.exists()) { // 文件存在时
				inputStream = new FileInputStream(oldPath); // 将文件读到内存
				outputStream = new FileOutputStream(newPath);// 用来写新文件的输出流
				byte[] buffer = new byte[1024];// 缓冲数组
				int length = 0;
				while ((length = inputStream.read(buffer)) != -1) {// inputStream.read()一个一个字节的读,此处使用缓冲加快处理文件速度
					bytesum += length; // 字节数 文件大小
					outputStream.write(buffer, 0, length);// 声明byteread来记录实际长度,避免文件不是缓冲字节数组长度的倍数
				}
				System.out.println("该文件共" + bytesum + "B, " + bytesum / 1024 + "KB, " + bytesum / (1024 * 1024) + "MB");
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				System.out.println("关闭失败");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 */
	public static void copyFolder(String oldPath, String newPath) {
		InputStream inputStream = null; // 输入流 读
		OutputStream outputStream = null;// 输出流 写
		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();// 根据路径拿到目录下的所有文件
			File temp = null;// 处理具体的某个文件
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {// File.separator代表路径分隔符
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {// 如果是文件
					inputStream = new FileInputStream(temp);
					outputStream = new FileOutputStream(newPath + File.separator + (temp.getName()).toString());
					byte[] buffer = new byte[1024 * 5];// 缓冲数组
					int length = 0;
					while ((length = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, length);
					}
					outputStream.flush();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + File.separator + file[i], newPath + File.separator + file[i]);// 递归使用
				}
			}
		} catch (Exception e) {
		} finally {
			try {
				outputStream.close();
				inputStream.close();
			} catch (Exception e2) {
			}
		}
	}

	/**
	 * 高效复制文件夹
	 * 
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyFolderByChannel(String oldPath, String newPath) {
		FileInputStream inputStream = null; // 输入流 读
		FileOutputStream outputStream = null;// 输出流 写
		FileChannel in = null;
		FileChannel out = null;
		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();// 根据路径拿到目录下的所有文件
			File temp = null;// 处理具体的某个文件
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {// File.separator代表路径分隔符
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {// 如果是文件
					inputStream = new FileInputStream(temp);
					outputStream = new FileOutputStream(newPath + File.separator + (temp.getName()).toString());
					in = inputStream.getChannel();
					out = outputStream.getChannel();
					in.transferTo(0, in.size(), out);
					// byte[] buffer = new byte[1024 * 5];// 缓冲数组
					// int length = 0;
					// while ((length = inputStream.read(buffer)) != -1) {
					// outputStream.write(buffer, 0, length);
					// }
					outputStream.flush();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolderByChannel(oldPath + File.separator + file[i], newPath + File.separator + file[i]);// 递归使用
				}
			}
		} catch (Exception e) {
		} finally {
			try {
				outputStream.close();
				inputStream.close();
				in.close();
				out.close();
			} catch (Exception e2) {
			}
		}
	}

	/**
	 * 
	 * @Description: TODO
	 * @param s
	 *            源文件
	 * @param t
	 *            复制到的新文件
	 * @author Yeyh
	 * @date 2017年9月6日
	 */
	public static void fileChannelCopy(File s, File t) {
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fi = new FileInputStream(s);
			fo = new FileOutputStream(t);
			in = fi.getChannel();// 得到对应的文件通道
			out = fo.getChannel();// 得到对应的文件通道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道

			// out.transferFrom(in, 0, in.size());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
