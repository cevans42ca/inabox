package ca.quines.inabox.helper.content;

import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import ca.quines.inabox.dto.Box;

/**
 * Convert match results to full English sentences, to be read by the client's text to speech capabilities.
 * 
 * Use the @Order annotation to make the instance of this class the one first in any list we create so that we can
 * use the first item as the default.  See {@link ContentServiceProvider#getService}.
 */
@Service
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EnglishHelper implements ContentHelperService {

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

			return "I found " + list.size() + " " + labelMany + ":  " + listToPhrase(names, ", ", " and ") + ".";
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
