package by.bsk.oracle.domain;

public enum Access {
	FULL {
		{
			currencyAccess = "������";
		}
	},
	WATCH {
		{
			currencyAccess = "��������";
		}
	},
	ADD_UPDATE {
		{
			currencyAccess = "���������� � ���������";
		}
	},
	DELETE {
		{
			currencyAccess = "��������";
		}
	};

	String currencyAccess;

	public String getCurrencyAccess() {
		return currencyAccess;
	}
}
