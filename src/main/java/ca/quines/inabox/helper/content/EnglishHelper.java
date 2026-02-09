package ca.quines.inabox.helper.content;

import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ca.quines.inabox.dto.Box;

@Service
public class EnglishHelper implements ContentHelperService {

	/**
	 * Returns a comma-separated list for text to speech.
	 * 
	 * @param list
	 * @return
	 */
	public String listToPhrase(List<String> list) {
		int size = list.size();
		if (size > 1) {
			// Create a view of all elements except the last one.
			List<String> allButLast = list.subList(0, size - 1);
			String last = list.get(size - 1);

			return String.join(", ", allButLast) + " and " + last;
		}

		return list.isEmpty() ? "" : list.get(0);
	}

	public String matchBoxListToSentence(List<Box> boxes) {
		return matchListToSentence(boxes, "box", "boxes", Box::getName, Box::getLocation);
	}

	public <T> String matchListToSentence(List<T> list, String labelOne, String labelMany,
			Function<T, String> fieldExtractor, Function<T, String> locationExtractor)
	{
		if (list.size() == 0) {
			return "No matching " + labelMany + " found.";
		}
		else if (list.size() == 1) {
			T item = list.get(0);
			return "I found one " + labelOne + " with the name " + fieldExtractor.apply(item) + ".  It's in the "
			+ locationExtractor.apply(item) + ".";
		}
		else if (list.size() < 5) {
			List<String> names = list.stream()
					.map(fieldExtractor)
					.collect(Collectors.toList());

			return "I found " + list.size() + " " + labelMany + ":  " + listToPhrase(names) + ".";
		}
		else {
			return "I found " + list.size() + " " + labelMany + ".  Please try a more specific name.";
		}
	}

	@Override
	public boolean supports(Locale locale) {
		return locale.getLanguage().equals("en");
	}

}
