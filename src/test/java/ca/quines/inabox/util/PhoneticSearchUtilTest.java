package ca.quines.inabox.util;

import org.apache.commons.codec.language.DoubleMetaphone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneticSearchUtilTest {

	private OrdinalCardinalUtil ordinalSearchUtil;
	private DoubleMetaphone metaphone;
	private PhoneticSearchUtil phoneticSearchUtil;

	@BeforeEach
	void setUp() {
		ordinalSearchUtil = new OrdinalCardinalUtil();
		metaphone = new DoubleMetaphone();
		phoneticSearchUtil = new PhoneticSearchUtil();
	}

	@Test
	void testCardinalPhoneticEqualitySplit() {
		String numeric = "1";
		String verbal = "one";

		String code1 = metaphone.encode(ordinalSearchUtil.normalize(numeric));
		String code2 = metaphone.encode(verbal);

		assertEquals(code1, code2, "Phonetic code for '1' should match 'one'");
	}

	@Test
	void testOrdinalPhoneticEqualitySplit() {
		String numeric = "1st";
		String verbal = "first";

		String code1 = metaphone.encode(ordinalSearchUtil.normalize(numeric));
		String code2 = metaphone.encode(verbal);

		assertEquals(code1, code2, "Phonetic code for '1st' should match 'first'");
	}

	@Test
	void testMixedStringSplit() {
		// Voice-to-text might give "2nd chance" or "second chance".  Handle both.
		String variantA = ordinalSearchUtil.normalize("2nd chance");
		String variantB = ordinalSearchUtil.normalize("second chance");

		assertEquals(metaphone.encode(variantA), metaphone.encode(variantB),
				"Phonetic code for '2nd chance' should match 'second chance'");
	}

	@Test
	void testCardinalPhoneticEqualityCombined() {
		assertEquals(phoneticSearchUtil.normalize("1"), phoneticSearchUtil.normalize("one"),
				"Phonetic code for '1' should match 'one'");
	}

	@Test
	void testOrdinalPhoneticEqualityCombined() {
		assertEquals(phoneticSearchUtil.normalize("1st"), phoneticSearchUtil.normalize("first"),
				"Phonetic code for '1st' should match 'first'");
	}

	@Test
	void testMixedStringCombined() {
		// Voice-to-text might give "2nd chance" or "second chance".  Handle both.
		assertEquals(phoneticSearchUtil.normalize("2nd chance"), phoneticSearchUtil.normalize("second chance"),
				"Phonetic code for '2nd chance' should match 'second chance'");
	}

}
