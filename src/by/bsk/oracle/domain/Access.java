package by.bsk.oracle.domain;

public enum Access {
	FULL {
		{
			currencyAccess = "Полный";
		}
	},
	WATCH {
		{
			currencyAccess = "Просмотр";
		}
	},
	ADD_UPDATE {
		{
			currencyAccess = "Добавление и изменение";
		}
	},
	DELETE {
		{
			currencyAccess = "Удаление";
		}
	};

	String currencyAccess;

	public String getCurrencyAccess() {
		return currencyAccess;
	}
}
