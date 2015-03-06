package com.goegge.mh;

public class Main {

	public static void main(String[] args) {
		PrivateKey privateKey = new PrivateKey();
		System.out.print("Privater Schlüssel: ");
		MerkleHellmanUtils.printIntergerArray(privateKey.getKnapsack());
		PublicKey publicKey = privateKey.createPublicKey();
		System.out.print("Öffentlicher Schlüssel: ");
		MerkleHellmanUtils.printIntergerArray(publicKey.getKnapsack());

		Cryptor cryptor = new Cryptor();
		
		String msg = "abcdefghijklmnopqrstuvwxyz!\"§$%&/()=?";
		System.out.println("Klartext: " + msg);
		System.out.println("Verschlüsseln...");
		int[] cipher = cryptor.encrypt(msg, publicKey);
		
		System.out.print("Geheimtext: ");
		MerkleHellmanUtils.printIntergerArray(cipher);
		
		System.out.println("Entschlüsseln...");
		char[] clear = cryptor.decrypt(cipher, privateKey);
		
		String clearText = new String(clear);
		System.out.println("Klartext: " + clearText);
	}

}
