package com.yeyh.io.dealwithcharacter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;

public class CopyByCharacter {

	public static void copy(String url1, String url2) {
		FileReader in = null;
		FileWriter out = null;
		try {
			in = new FileReader("f:\\RUNNING.txt");
			out = new FileWriter("f:\\RUNNING511.txt");

			char[] cs = new char[1024];

			int ch = 0;
			// 以字符方式显示文件内容
			while ((ch = in.read(cs)) != -1) {
				// System.out.print((char) ch);
				out.write(cs, 0, ch);
			}

			String author = "叶英豪";

			out.write(author);
			// out.flush();

			System.out.println("复制完成");
		} catch (Exception e) {
		} finally {
			try {
				in.close();
				out.close();
			} catch (Exception e2) {
			}
		}
	}

	public static void test() {
		try {
			// 使用Writer类可以直接输出字符串
			FileWriter out = new FileWriter("e:\\io.txt");
			String str = "你好世界Hello Word!";
			String str1 = "你好世界Hello Word!!";
			out.write(str);
			out.write("\r\n" + str1);// 为什么out.write("\n + str1")不能实现转行？
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			// 在文件后追加写入一个字符串
			Writer out = new FileWriter("e:\\io.txt", true);
			String str = "\r\n你好世界Hello Word!!!";
			out.write(str);
			out.close();
			Reader in = new FileReader("e:\\io.txt");
			int b;
			while ((b = in.read()) != -1) {
				System.out.print((char) b);// 字符流传输数据是两个直接，中文可以正确显示出来。
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		// copy(null, null);
		// Files.copy((new File("f:\\RUNNING.txt")).toPath(), (new
		//// File("f:\\RUNNING23.txt")).toPath());
		// test();
		keyboard();
	}

	public static void keyboard() throws Exception {
		// 键盘的最常见写法
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("goods1.txt"), "utf-8"));
		String line = null;
		while ((line = bufr.readLine()) != null) {
			if ("over".equals(line))
				break;
			bufw.write(line.toUpperCase());
			bufw.newLine();
			bufw.flush();
		}
		
		bufr.close();
		bufw.close();
	}

}
