package com.chaos.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class demo11 {

	public static void main(String[] args) {
		List<String> strList = new ArrayList<String>();
		strList.add("1");
		strList.add("2");
		strList.add("3");
		strList.add("4");
		
//		for (String str : strList) {
//			if(str.equals("3")) {
//				strList.remove(str);
//			}
//		}
		
		Iterator<String> iterator = strList.iterator();
		while(iterator.hasNext()) {
			String next = iterator.next();
			if(next.equals("3")) {
				strList.remove(next);
			}
		}
		
//		new Thread() {
//		@Override
//		public void run() {
//			strList.add("32");
//			for (String str : strList) {
//				if(str.equals("3")) {
//					strList.remove(str);
//				}
//			}
//		}}.start();
		
		printAll(strList);
	}
	
	public static void printAll(List<String> list) {
		for (String string : list) {
			System.out.println(string);
		}
	}
	
}
