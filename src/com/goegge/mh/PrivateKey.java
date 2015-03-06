package com.goegge.mh;

import java.util.Random;

public class PrivateKey {
	public int[] knapsack = new int[8];
	
	public PrivateKey() {
		createSuperIncreasingKnapSack();
	}
	
	/**
	 * Erstellt einen zufälligen, stark wachsenden "Rucksack".
	 */
	private void createSuperIncreasingKnapSack() {
		Random random = new Random();
		
		int h = random.nextInt(127) + 1;
		
		knapsack[0] = h;
		knapsack[1] = h + 1;
		
		int k = 1;		
		for(int i = 2; i < knapsack.length; i++) {
			k *= 2;
			knapsack[i] = (k * h) + k;
		}
		
		int sum = 0;
		for (int i = 0; i < knapsack.length; i++) {
			sum += knapsack[i];
		}
		System.out.println("Sum of private knapsack: " + sum);
	}
	
	/**
	 * Errechnet den Öffentlichen Schlüssel.
	 * @return
	 */
	public PublicKey createPublicKey() {
		int[] publicKeyKnapSack = new int[8];
		
		for(int i = 0; i < publicKeyKnapSack.length; i++) {
			publicKeyKnapSack[i] = (knapsack[i] * MerkleHellmanUtils.N) % MerkleHellmanUtils.M;
		}
		
		return new PublicKey(publicKeyKnapSack);
	}

	public int[] getKnapsack() {
		return knapsack;
	}
	
	public int getLength() {
		return knapsack.length;
	}
	
	public int at(int i) {
		return knapsack[i];
	}
}
