package com.goegge.mh;

public class Cryptor {

	/**
	 * Verschlüsselt @param text mit @param publicKey und gibt @return cipher
	 * zurück.
	 */
	public int[] encrypt(String text, PublicKey publicKey) {
		int textLength = text.length();

		String[] binary = MerkleHellmanUtils.charsToBinary(text.toCharArray());
		int[] cipher = new int[textLength];

		for (int i = 0; i < textLength; i++) {
			int sum = 0;
			for (int j = 0; j < 8; j++) {
				String sBinary = "" + binary[i].charAt(j);
				int iBinary = Integer.parseInt(sBinary);
				int product = iBinary * publicKey.at(j);
				sum += product;
			}
			cipher[i] = sum;
		}

		return cipher;
	}

	/**
	 * Entschlüsselt @param cipher mit @param privateKey und gibt @return
	 * clearText zurück.
	 */
	public char[] decrypt(int[] cipher, PrivateKey privateKey) {
		int cipherLength = cipher.length;

		for (int i = 0; i < cipherLength; i++) {
			cipher[i] = (cipher[i] * MerkleHellmanUtils.M_INVERSE)
					% MerkleHellmanUtils.M;
		}
		String[] bClearTexts = MerkleHellmanUtils.solveKnapSack(privateKey.getKnapsack(), cipher);
		
		int[] cCipher = new int[cipherLength];
		for (int i = 0; i < bClearTexts.length; i++) {
			int sum = 0;
			for (int j = 0; j < 8; j++) {
				String sBinary = "" + bClearTexts[i].charAt(j);
				int iBinary = Integer.parseInt(sBinary);
				int product = iBinary * privateKey.at(j);
				sum += product;
			}
			cCipher[i] = sum;
		}
		
		char[] clearText = MerkleHellmanUtils.toCharsFromBinaryStrings(bClearTexts);
		return clearText;
	}

}
