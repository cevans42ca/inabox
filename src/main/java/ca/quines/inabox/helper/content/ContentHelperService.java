package ca.quines.inabox.helper.content;

import java.util.List;
import java.util.Locale;

import ca.quines.inabox.dto.Box;

public interface ContentHelperService {

	/**
	 * Convert the list of Box matches to one or two sentences, to be read by the client's text-to-speech.
	 * 
	 * @param boxes
	 * @return
	 */
	public String matchBoxListToSentence(List<Box> boxes);

	public boolean supports(Locale locale);

	/**
	 * Returns a comma-separated list for text to speech.
	 * 
	 * @param list
	 * @return
	 */
	public default String listToPhrase(List<String> list, String serialJoiner, String terminalJoiner) {
		int size = list.size();
		if (size > 1) {
			// Create a view of all elements except the last one.
			List<String> allButLast = list.subList(0, size - 1);
			String last = list.get(size - 1);

			return String.join(serialJoiner, allButLast) + terminalJoiner + last;
		}

		return list.isEmpty() ? "" : list.get(0);
	}

}
