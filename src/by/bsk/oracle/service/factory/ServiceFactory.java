package by.bsk.oracle.service.factory;

import by.bsk.oracle.service.DivisionService;
import by.bsk.oracle.service.PriceCategorySevice;
import by.bsk.oracle.service.UserService;
import by.bsk.oracle.service.impl.DivisionServiceImpl;
import by.bsk.oracle.service.impl.PriceCategoryServiceImpl;
import by.bsk.oracle.service.impl.UserServiceImpl;

public class ServiceFactory {

	private ServiceFactory() {
	}

	private static final ServiceFactory INSTANCE = new ServiceFactory();

	private final UserService userService = new UserServiceImpl();
	private final DivisionService divisionService = new DivisionServiceImpl();
	private final PriceCategorySevice priceCategorySevice = new PriceCategoryServiceImpl();

	public static ServiceFactory getInstance() {
		return INSTANCE;
	}

	public UserService getUserServie() {
		return userService;
	}

	public DivisionService getDivisionService() {
		return divisionService;
	}

	public PriceCategorySevice getPriceCategory() {
		return priceCategorySevice;
	}

}
