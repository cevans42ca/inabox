package ca.quines.inabox.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

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

	/**
	 * Split the input String on spaces before passing to DoubleMetaphone as it has a max key length.
	 * 
	 * @param input
	 * @return
	 */
	public String normalize(String input) {
		if (input == null || input.isEmpty()) return "";

		String preNormalized = phoneticSearchUtil.normalize(input);

		// Split on spaces, remove empty words, encode each word, remove nulls for safety, and smash it all together
		// with no spaces so we can handle issues with word boundaries.
		return Arrays.stream(preNormalized.split("\\s+"))
                .filter(word -> !word.isEmpty())
                .map(word -> metaphone.encode(word))
                .filter(code -> code != null)
                .collect(Collectors.joining());
	}

	public static void main(String[] args) throws IOException {
		PhoneticSearchUtil phoneticSearchUtil = new PhoneticSearchUtil();

		if (args.length > 0) {
			String result = Arrays.stream(args).map(phoneticSearchUtil::normalize).collect(Collectors.joining(" "));

			System.out.println(result);
		}
		else {
			System.out.println("Send an empty line to quit.");
			try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				String line;
				while ((line = br.readLine()) != null) {
					line = line.trim();
					if (line.length() == 0) {
						break;
					}

					System.out.println("Normalized output:  " + phoneticSearchUtil.normalize(line));
				}
			}
		}
	}

}
