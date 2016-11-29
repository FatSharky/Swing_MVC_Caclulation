package by.bsk.oracle.service.parser;

import by.bsk.oracle.domain.Access;
import by.bsk.oracle.service.parser.exception.ParserException;

public final class Parser {
	private Parser() {

	}

	public static Access fromStringToAccess(String value) throws ParserException {
		if (value != null) {
			for (Access access : Access.values()) {
				if (value.equalsIgnoreCase(access.getCurrencyAccess())) {
					return access;
				}
			}
		}
		throw new ParserException("Can't find such access");

	}
}
