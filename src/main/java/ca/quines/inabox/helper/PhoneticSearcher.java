package ca.quines.inabox.helper;

import org.apache.commons.codec.language.DoubleMetaphone;

public class PhoneticSearcher {

	private static void phoneticWithWords() {
		DoubleMetaphone engine = new DoubleMetaphone();

		String original = "Knight";
		String userInput = "Night";

		// Generate codes
		String code1 = engine.encode(original);
		String code2 = engine.encode(userInput);

		System.out.println("Code 1: " + code1); // Output: NT
		System.out.println("Code 2: " + code2); // Output: NT

		if (engine.isDoubleMetaphoneEqual(original, userInput)) {
			System.out.println("Match found! The words sound the same.");
		}
	}

	private static void phoneticWithNumbers() {
		DoubleMetaphone engine = new DoubleMetaphone();

		String original = "Knight 1";
		String userInput = "Night One";

		// Generate codes
		String code1 = engine.encode(original);
		String code2 = engine.encode(userInput);

		System.out.println("Code 1: " + code1); // Output: NT
		System.out.println("Code 2: " + code2); // Output: NT

		if (engine.isDoubleMetaphoneEqual(original, userInput)) {
			System.out.println("Match found! The words sound the same.");
		}
	}

	public static void main(String[] args) {
		phoneticWithWords();
		phoneticWithNumbers();
	}

}
