package com.chaos.demo.littleDemo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.chaos.model.Member;
import com.chaos.model.User;
import com.google.common.collect.Lists;

public class demo3 {

	public static void main(String[] args) {
		List<User> list = Lists.newArrayList();
		User user1 = new User(23);
		User user2 = new User(2);
		User user3 = new User(43);
		list.add(user1);
		list.add(user2);
		list.add(user3);
		Collections.sort(list);
//		for (User user : list) {
//			System.out.println(user.getAge());
//		}
		
		List<Member> mList = Lists.newArrayList();
		Member member1 = new Member("李鸿章",4);
		Member member2 = new Member("张志红",2);
		Member member3 = new Member("张会生",3);
		mList.add(member1);
		mList.add(member3);
		mList.add(member2);
		//1、用Comparator匿名内部类来进行排序
		Collections.sort(mList, new Comparator<Member>() {
			@Override
			public int compare(Member o1, Member o2) {
				return o1.getAge()-o2.getAge();
			}
			
		});
//		for (Member member : mList) {
//			System.out.println(member.getAge()+"---"+member.getNickname());
//		}
		
		
		//多条件排序
		Collections.sort(mList, new Comparator<Member>() {
			@Override
			public int compare(Member o1, Member o2) {
				int rs=0;
				int a = o1.getNickname().compareTo(o2.getNickname());
				if(a!=0) {
					rs= a>0?-1:2;
				}else {
					a=o1.getAge().compareTo(o2.getAge());
					rs= a>0?1:-1;
				}
				return rs;
			}
			
		});
		for (Member member : mList) {
			System.out.println(member.getAge()+"---"+member.getNickname());
		}
		//2、用lambda表达式进行排序
//		mList.sort((Member m1,Member m2) ->m1.getAge().compareTo(m2.getAge()));
//		Collections.sort(mList,(Member m1, Member m2) ->m1.getAge().compareTo(m2.getAge()));
//		for (Member member : mList) {
//			System.out.println(member.getAge()+"---"+member.getNickname());
//		}
	}
	
}
