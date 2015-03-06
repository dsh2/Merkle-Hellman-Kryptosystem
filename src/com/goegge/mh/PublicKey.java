package com.goegge.mh;

public class PublicKey {
	private int[] knapsack = new int[8];
	
	public PublicKey() {
	}
	
	public PublicKey(int[] knapsack) {
		this.knapsack = knapsack.clone();
	}
	
	public int[] getKnapsack() {
		return knapsack;
	}

	public int at(int i) {
		return knapsack[i];
	}
}
