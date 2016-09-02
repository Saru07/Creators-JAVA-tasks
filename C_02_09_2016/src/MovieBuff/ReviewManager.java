package com.creators.movie;

public class ReviewManager {
	private int totalReviews;
	private int totalViewers;
	
	public ReviewManager(int totalReviews, int totalViewers) {
		super();
		this.totalReviews = totalReviews;
		this.totalViewers = totalViewers;
	}

	public int getTotalReviews() {
		return totalReviews;
	}

	public void setTotalReviews(int totalReviews) {
		this.totalReviews = totalReviews;
	}

	public int getTotalViewers() {
		return totalViewers;
	}

	public void setTotalViewers(int totalViewers) {
		this.totalViewers = totalViewers;
	}

	@Override
	public String toString() {
		return "BuffReview [Average Rating : " + (totalReviews/totalViewers) + " & " + totalViewers + " review(s) ]" ;
	}
	
	
}
