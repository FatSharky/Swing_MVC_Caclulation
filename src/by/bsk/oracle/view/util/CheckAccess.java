package by.bsk.oracle.view.util;

import by.bsk.oracle.domain.Access;

public final class CheckAccess {

	private CheckAccess() {
	}

	public static String checkAccess(Access access) {
		String returnAccess = null;
		switch (access) {
		case FULL:
			returnAccess = "������ ������";
			break;

		case WATCH:
			returnAccess = "������ ��������";
			break;
		case ADD_UPDATE:
			returnAccess = "������ ���������� � ���������";
			break;
		case DELETE:
			returnAccess = "������ ��������";
			break;
		default:
			returnAccess = "��� �� ����� �� ���";
			break;
		}
		return returnAccess;
	}
}
