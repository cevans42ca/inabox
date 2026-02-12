package ca.quines.inabox.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;

import ca.quines.inabox.dto.Box;
import ca.quines.inabox.helper.content.EnglishHelper;

@SpringBootTest
@AutoConfigureRestTestClient
class EnglishHelperTest {

	@Autowired
	private EnglishHelper englishHelper;

	@Test
	void testListToPhraseEmpty() {
		List<String> list = new ArrayList<>();

		assertEquals("", englishHelper.listToPhrase(list, ", ", " and "));
	}

	@Test
	void testListToPhraseOneItem() {
		List<String> list = List.of("one");

		assertEquals("one", englishHelper.listToPhrase(list, ", ", " and "));
	}

	@Test
	void testListToPhraseTwoItems() {
		List<String> list = List.of("one", "two");

		assertEquals("one and two", englishHelper.listToPhrase(list, ", ", " and "));
	}

	@Test
	void testListToPhraseThreeItems() {
		List<String> list = List.of("one", "two", "three");

		assertEquals("one, two and three", englishHelper.listToPhrase(list, ", ", " and "));
	}

	@Test
	void testListToPhraseFourItems() {
		List<String> list = List.of("one", "two", "three", "four");

		assertEquals("one, two, three and four", englishHelper.listToPhrase(list, ", ", " and "));
	}

	@Test
	void testListToSentenceEmpty() {
		List<Box> list = new ArrayList<>();

		assertEquals("No matching boxes found.",
				englishHelper.matchListToSentence(list, "box", "boxes", Box::getName, Box::getLocation));
	}

	@Test
	void testListToSentenceOneItem() {
		List<Box> list = new ArrayList<>();
		Box box = new Box();
		box.setName("Miscellaneous 1");
		box.setLocation("Upside Down");
		list.add(box);

		assertEquals("I found one box with the name Miscellaneous 1.  It's in the Upside Down.",
				englishHelper.matchListToSentence(list, "box", "boxes", Box::getName, Box::getLocation));
	}

	@Test
	void testListToSentenceTwoItems() {
		List<Box> list = new ArrayList<>();

		Box box = new Box();
		box.setName("Miscellaneous 1");
		box.setLocation("Upside Down");
		list.add(box);

		box = new Box();
		box.setName("Miscellaneous 2");
		box.setLocation("Upside Down");
		list.add(box);

		assertEquals("I found 2 boxes:  Miscellaneous 1 and Miscellaneous 2.",
				englishHelper.matchListToSentence(list, "box", "boxes", Box::getName, Box::getLocation));
	}

	@Test
	void testListToSentenceThreeItems() {
		List<Box> list = new ArrayList<>();

		for (int i=1; i<=3; i++) {
			Box box = new Box();
			box.setName("Miscellaneous " + i);
			box.setLocation("Upside Down");
			list.add(box);
		}

		assertEquals("I found 3 boxes:  Miscellaneous 1, Miscellaneous 2 and Miscellaneous 3.",
				englishHelper.matchListToSentence(list, "box", "boxes", Box::getName, Box::getLocation));
	}

	@Test
	void testListToSentenceFourItems() {
		List<Box> list = new ArrayList<>();

		for (int i=1; i<=4; i++) {
			Box box = new Box();
			box.setName("Miscellaneous " + i);
			box.setLocation("Upside Down");
			list.add(box);
		}

		assertEquals("I found 4 boxes:  Miscellaneous 1, Miscellaneous 2, Miscellaneous 3 and Miscellaneous 4.",
				englishHelper.matchListToSentence(list, "box", "boxes", Box::getName, Box::getLocation));
	}

	@Test
	void testListToSentenceFiveItems() {
		List<Box> list = new ArrayList<>();

		for (int i=1; i<=5; i++) {
			Box box = new Box();
			box.setName("Miscellaneous " + i);
			box.setLocation("Upside Down");
			list.add(box);
		}

		assertEquals("I found 5 boxes.  Please try a more specific name.",
				englishHelper.matchListToSentence(list, "box", "boxes", Box::getName, Box::getLocation));
	}

}
