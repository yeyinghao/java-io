package com.yeyh.io.dealwithcharacter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;

public class CopyByCharacter {

	public static void copy(String url1, String url2) {
		Reader in = null;
		Writer out = null;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream("f:\\RUNNING.txt")));
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("f:\\RUNNING1.txt")));

			int ch = 0;
			// 以字符方式显示文件内容
			while ((ch = in.read()) != -1) {
				System.out.print((char) ch);
				out.write(ch);
			}
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

	public static void main(String[] args) throws IOException {
		copy(null, null);
		Files.copy((new File("f:\\RUNNING.txt")).toPath(), (new File("f:\\RUNNING23.txt")).toPath());
	}
}
