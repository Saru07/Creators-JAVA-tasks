package com.creators.movie;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MovieBuff {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<String, ReviewManager> rev = new TreeMap<String, ReviewManager>();
		String movieName, dummy;
		int rating;
		while(true) {
			System.out.println("1. Add Review\n 2. End\n Enter your choice : ");
			int choice = sc.nextInt();
			dummy = sc.nextLine();
			switch(choice) {
			case 1: System.out.print("Movie Name : ");
					movieName = sc.nextLine();
					System.out.print("Rating : ");
					rating = sc.nextInt();
					if(rev.containsKey(movieName)) {
						ReviewManager rm = rev.get(movieName);
						rm.setTotalReviews(rm.getTotalReviews() + rating);
						rm.setTotalViewers(rm.getTotalViewers() + 1);
					}
					else {
						rev.put(movieName, new ReviewManager(rating, 1));
					}
					break;
			case 2:break;
			}
			if(choice == 2) {
				for(Map.Entry<String, ReviewManager> each:rev.entrySet()) {
					System.out.println(each.getKey() + "\t" + each.getValue());
				}
				break;
			}
		}
	}

}
