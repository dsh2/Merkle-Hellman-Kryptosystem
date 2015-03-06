package com.goegge.mh;

public class MerkleHellmanUtils {
	public static final int N = 11111;
	public static final int M = 16511;
	public static final int M_INVERSE = 584;

	static {
		//System.out.println(gcd(M, N));
	}

	/**
	 * Eklidischeralgorithmus zum Bestimmen des größten gemeinsamen Teilers von @param a, b.
	 */
	public static int gcd(int a, int b) {
		int c = 1;
		while (c > 0) {
			c = a % b;
			if (c == 0)
				break;
			a = b;
			b = c;
		}
		return b;
	}

	/**
	 * Gibt @param array in folgender Form aus: {array[i], array[i + 1], array[i
	 * + 2], array[i + 3],... , array[n]}
	 */
	public static void printIntergerArray(int[] array) {
		System.out.print("{");

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if (i != array.length - 1) {
				System.out.print(",");
			}
		}
		System.out.print("}\n");
	}

	/**
	 * Konvertiert ein Feld aus chars (@param buffer) in ein Feld(@return
	 * binary) aus den entsprechenden Binärcodes.
	 */
	public static String[] charsToBinary(char[] buffer) {
		int bufferLength = buffer.length;

		String[] binary = new String[bufferLength];

		for (int i = 0; i < bufferLength; i++) {
			String cBinary = Integer.toBinaryString((int) buffer[i]);

			if (cBinary.length() < 8) {
				cBinary = "000000000".substring(0, 8 - cBinary.length())
						.concat(cBinary);
			} else {
				cBinary = cBinary.substring(cBinary.length() - 8);
			}

			binary[i] = cBinary;
		}

		return binary;
	}

	/**
	 * Konvertiert ein Feld aus Binärcodes (@param binary) in ein char array (@return chars)
	 */
	public static char[] toCharsFromBinaryStrings(String[] binary) {
		char[] chars = new char[binary.length];
		for (int i = 0; i < binary.length; i++) {
			char c = (char) Integer.parseInt(binary[i], 2);
			chars[i] = c;
		}
		return chars;
	}

	/**
	 * Löst das Rucksackproblem für stark wachsende Vektoren und gibt die Lösungen in Binärform zurück.
	 * @param knapsack
	 * @param values
	 * @return
	 */
	public static String[] solveKnapSack(int[] knapsack, int[] values) {
		int cipherLength = values.length;
		String[] bClearTexts = new String[values.length];

		for (int i = 0; i < cipherLength; i++) {
			String bClearText = new String();
			int c = values[i];
			boolean solved = false;
			int[] savedIndecies = new int[knapsack.length];
			int lastIndex = 0;
			for (int j = knapsack.length - 1; j >= 0; j--) {

				int largest = knapsack[j];

				if (c > largest) {
					if (j < knapsack.length - 1) {
						int sum = 0;

						for (int k = 0; k < lastIndex; k++) {
							sum += knapsack[savedIndecies[k]];
						}
						sum += knapsack[j];

						if (sum > c) {
							bClearText += "0";
						} else if (sum == c) {
							bClearText += "1";
							savedIndecies[lastIndex++] = j;
							solved = true;
							break;
						} else if (sum < c) {
							bClearText += "1";
							savedIndecies[lastIndex++] = j;
						}

					} else {
						bClearText += "1";
						savedIndecies[lastIndex++] = j;
					}
				} else if (c == largest) {
					bClearText += "1";
					savedIndecies[lastIndex++] = j;
				} else if (c < largest) {
					bClearText += "0";
				}
			}

			String bClearTextTurned = new String();

			int dif = 8 - bClearText.length();
			if (dif > 0) {
				for (int h = 0; h < dif; h++) {
					bClearTextTurned += "0";
				}
			}

			for (int k = 0; k < bClearText.length(); k++) {
				bClearTextTurned += bClearText.charAt((bClearText.length() - 1)
						- k);
			}

			bClearTexts[i] = bClearTextTurned;
		}
		return bClearTexts;
	}
}
