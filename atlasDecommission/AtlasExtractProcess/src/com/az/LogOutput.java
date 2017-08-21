package com.az;

import java.io.BufferedWriter;
import java.io.IOException;

public class LogOutput {

	private static void outSummary(BufferedWriter bbw, String s) {
		try {
			bbw.write(s + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void out(BufferedWriter bbw, BufferedWriter bw, String s) {
		try {
			System.out.println(s);
			outSummary(bbw, s);
			bw.write(s + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void out(BufferedWriter bw, String s) {
		try {
			System.out.println(s);
			bw.write(s + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void outBar(BufferedWriter bw) {
		try {
			System.out.println("\n========================================================");
			bw.write("\n========================================================\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
