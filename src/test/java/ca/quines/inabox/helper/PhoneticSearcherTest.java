package ca.quines.inabox.helper;

import org.apache.commons.codec.language.DoubleMetaphone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhoneticSearcherTest {

	@Test
	private static void phoneticWithWords() {
		DoubleMetaphone engine = new DoubleMetaphone();

		String original = "Knight";
		String userInput = "Night";

		// Generate codes
		String code1 = engine.encode(original);
		String code2 = engine.encode(userInput);

		// Verification
		assertEquals("NT", code1);
		assertEquals("NT", code2);

		assertTrue(engine.isDoubleMetaphoneEqual(original, userInput));
	}

	@Test
	private static void phoneticWithNumbers() {
		DoubleMetaphone engine = new DoubleMetaphone();

		String original = "Knight 1";
		String userInput = "Night One";

		// Generate codes
		String code1 = engine.encode(original);
		String code2 = engine.encode(userInput);

		// Verification
		assertEquals("NT", code1);
		assertEquals("NT", code2);

		assertTrue(engine.isDoubleMetaphoneEqual(original, userInput));
	}

	public static void main(String[] args) {
		phoneticWithWords();
		phoneticWithNumbers();
	}

}
