package org.creators;

import org.student.Student;

public class Solution {

	public static void main(String[] args) {
		SetOpt<String> s1 = new SetOpt<String>();
		SetOpt<String> s2 = new SetOpt<String>();
		s1.add("saru");
		s1.add("latha");
		s1.add("louis");
		s1.add("tomlinson");
		s1.add("harry");
		s1.add("styles");
		s2.add("harry");
		s2.add("saru");
		s2.add("styles");
		s2.add("louis");
		System.out.println(s1 + " " + s2);
		System.out.println("UNION : " + s1.union(s2));
		System.out.println("INTERSECTION : " + s1.intersection(s2));
		System.out.println("DIFFERENCE (A - B) : " + s1.difference(s2));
		System.out.println("DIFFERENCE (B - A) : " + s2.difference(s1));
		System.out.println("SYMMETRIC DIFFERENCE : " + s1.symmetricDifference(s2));
		System.out.println("SUPER SET : " + ((s1.isSuperSet(s2))?"YES":"NO"));
		System.out.println("SUBSSET : " + ((s2.isSubSet(s1))?"YES":"NO"));
		
		SetOpt<Student> st1 = new SetOpt<Student>();
		SetOpt<Student> st2 = new SetOpt<Student>();
		st1.add(new Student(1,"Ammu",false));
		st1.add(new Student(2,"Akshara",false));
		st1.add(new Student(5,"Saru",false));
		st2.add(new Student(3,"Harry",true));
		st2.add(new Student(4,"Louis",true));
		st2.add(new Student(6,"Tommo",true));
		st2.add(new Student(2,"Akshara",false));
		st2.add(new Student(5,"Saru",false));
		System.out.println(st1 + " " + st2);
		System.out.println("UNION : " + st1.union(st2));
		System.out.println("INTERSECTION : " + st1.intersection(st2));
		System.out.println("DIFFERENCE (A - B) : " + st1.difference(st2));
		System.out.println("DIFFERENCE (B - A) : " + st2.difference(st1));
		System.out.println("SYMMETRIC DIFFERENCE : " + st1.symmetricDifference(st2));
		System.out.println("SUPER SET : " + ((st1.isSuperSet(st2))?"YES":"NO"));
		System.out.println("SUBSSET : " + ((st2.isSubSet(st1))?"YES":"NO"));
 		}
}
