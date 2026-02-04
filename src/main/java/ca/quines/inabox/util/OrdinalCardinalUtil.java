package ca.quines.inabox.util;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.ULocale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.language.DoubleMetaphone;
import org.springframework.stereotype.Service;

/**
 * Convert ordinals (1st, 2nd, 3rd) to first, second, third and cardinals (1, 2, 3) to
 * one, two, three, in preparation for converting to a phonetic form (see {@link DoubleMetaphone}).
 */
@Service
public class OrdinalCardinalUtil {

	public OrdinalCardinalUtil() {
		// EMPTY
	}

	public String normalize(String text) {
		// Initialize for English, spelling out (e.g., 1 -> "one")
		// RuleBasedNumberFormat is not thread-safe.
		RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(ULocale.US, RuleBasedNumberFormat.SPELLOUT);

		// Regex to find numbers with or without ordinal suffixes (1, 2nd, 3rd, etc.)
		Pattern pattern = Pattern.compile("(\\d+)(st|nd|rd|th)?");
		Matcher matcher = pattern.matcher(text.toLowerCase());
		StringBuilder sb = new StringBuilder();
		int lastEnd = 0;

		while (matcher.find()) {
			sb.append(text, lastEnd, matcher.start());

			long num = Long.parseLong(matcher.group(1));
			String suffix = matcher.group(2);

			if (suffix != null) {
	            // This name is standard in English locales for 1 -> "first"
	            sb.append(formatter.format(num, "%spellout-ordinal"));
	        } else {
	            // Default format() without a string name 
	            // is the cardinal "one, two, three"
	            sb.append(formatter.format(num)); 
	        }
			lastEnd = matcher.end();
		}

		sb.append(text.substring(lastEnd));

		return sb.toString();
	}

}
