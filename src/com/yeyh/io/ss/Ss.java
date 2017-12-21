package com.yeyh.io.ss;

import java.util.Scanner;

public class Ss {
	public static void main(String[] args) {
		System.out.print("请输入个人收入：");
		Scanner input = new Scanner(System.in); // 获取控制台输入对象
		double sr = input.nextDouble();
		System.out.println("应交个人所得税：" + getTax(sr));// 调用计算所得税方法
	}

	public static double getTax(double sal) {
		double t = 0; // t代表应交税款
		double b = sal - 3500; // b代表应纳税所得额
		if (b <= 500) // 计算级数1的税款
		{
			t = b * 0.05;
			return t;
		} else if (b <= 2000) // 计算级数2的税款
		{
			t = 25 + (b - 500) * 0.1;
			return t;
		} else if (b <= 5000) // 计算级数3的税款
		{
			t = 175 + (b - 2000) * 0.15;
			return t;
		} else if (b <= 20000) // 计算级数4的税款
		{
			t = 625 + (b - 5000) * 0.2;
			return t;
		} else if (b <= 40000) // 计算级数5的税款
		{
			t = 3625 + (b - 20000) * 0.25;
			return t;
		} else if (b <= 60000) // 计算级数6的税款
		{
			t = 8625 + (b - 40000) * 0.3;
			return t;
		} else if (b <= 80000) // 计算级数7的税款
		{
			t = 14625 + (b - 60000) * 0.35;
			return t;
		} else if (b <= 100000) // 计算级数8的税款
		{
			t = 21625 + (b - 80000) * 0.4;
			return t;
		} else // 计算级数9的税款
		{
			t = 29625 + (b - 100000) * 0.45;
			return t;
		}
	}
}