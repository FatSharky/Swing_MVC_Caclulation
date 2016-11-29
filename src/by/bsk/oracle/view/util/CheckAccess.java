package by.bsk.oracle.view.util;

import by.bsk.oracle.domain.Access;

public final class CheckAccess {

	private CheckAccess() {
	}

	public static String checkAccess(Access access) {
		String returnAccess = null;
		switch (access) {
		case FULL:
			returnAccess = "Полный доступ";
			break;

		case WATCH:
			returnAccess = "Только просмотр";
			break;
		case ADD_UPDATE:
			returnAccess = "Только добавление и изменение";
			break;
		case DELETE:
			returnAccess = "Только удаление";
			break;
		default:
			returnAccess = "Что то пошло не так";
			break;
		}
		return returnAccess;
	}
}
