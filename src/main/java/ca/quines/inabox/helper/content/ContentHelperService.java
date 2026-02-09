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

}
