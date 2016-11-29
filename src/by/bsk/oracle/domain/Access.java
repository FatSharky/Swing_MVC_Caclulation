package by.bsk.oracle.domain;

public enum Access {
	FULL {
		{
			currencyAccess = "full";
		}
	},
	WATCH {
		{
			currencyAccess = "watch";
		}
	},
	ADD_UPDATE {
		{
			currencyAccess = "add update";
		}
	},
	DELETE {
		{
			currencyAccess = "delete";
		}
	};

	String currencyAccess;

	public String getCurrencyAccess() {
		return currencyAccess;
	}
}
