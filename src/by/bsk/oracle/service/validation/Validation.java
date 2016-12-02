package by.bsk.oracle.service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validation {

	private static final int LETTERS_LENGTH = 10;
	private static final int SMALL_LETTERS_LENGTH = 30;
	private static final int SMALLEST_LETTERS_LENGTH = 15;
	private static final int TEXT_LENGTH = 1000;

	private Validation() {

	}

	private static boolean checkStringField(String patternStr, String field) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(field);
		return matcher.matches();
	}

	private static boolean checkLength(String value, int length) {
		return (value.length() == 0 || value.length() > length);

	}

	public static boolean validateMultyTextField(String value) {
		return checkLength(value, LETTERS_LENGTH);
	}
}
