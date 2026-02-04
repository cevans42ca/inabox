package ca.quines.inabox.util;

import org.apache.commons.codec.language.DoubleMetaphone;
import org.springframework.stereotype.Service;

@Service
public class PhoneticSearchUtil {

	private OrdinalCardinalUtil phoneticSearchUtil;

	/**
	 * According to the JavaDoc, an instance of this class is thread-safe if it is shared between threads only after
	 * initial setup and the setMaxCodeLen(int) method is never invoked after that point. Callers must ensure
	 * suitable synchronization for safe publication.  See {@link https://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/language/DoubleMetaphone.html#:~:text=All%20Implemented%20Interfaces:%20Encoder%20%2C%20StringEncoder,See%20Also:}
	 */
	private DoubleMetaphone metaphone;

	public PhoneticSearchUtil() {
		phoneticSearchUtil = new OrdinalCardinalUtil();
		metaphone = new DoubleMetaphone();
	}

	public String normalize(String input) {
		return metaphone.encode(phoneticSearchUtil.normalize(input));
	}

}
