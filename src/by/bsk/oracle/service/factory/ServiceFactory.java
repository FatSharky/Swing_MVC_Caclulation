package by.bsk.oracle.service.factory;

import by.bsk.oracle.service.DivisionService;
import by.bsk.oracle.service.PriceCategorySevice;
import by.bsk.oracle.service.ProductCategoryService;
import by.bsk.oracle.service.StructuralUnitService;
import by.bsk.oracle.service.UserService;
import by.bsk.oracle.service.impl.DivisionServiceImpl;
import by.bsk.oracle.service.impl.PriceCategoryServiceImpl;
import by.bsk.oracle.service.impl.ProductCategoryServiceImpl;
import by.bsk.oracle.service.impl.StructuralUnitServiceImpl;
import by.bsk.oracle.service.impl.UserServiceImpl;

public class ServiceFactory {

	private ServiceFactory() {
	}

	private static ServiceFactory serviceFactory;

	private final UserService userService = new UserServiceImpl();
	private final DivisionService divisionService = new DivisionServiceImpl();
	private final PriceCategorySevice priceCategorySevice = new PriceCategoryServiceImpl();
	private final StructuralUnitService structuralUnitService = new StructuralUnitServiceImpl();
	private final ProductCategoryService productCategoryService = new ProductCategoryServiceImpl();

	public static ServiceFactory getInstance() {
		if (serviceFactory == null) {
			synchronized (ServiceFactory.class) {
				if (serviceFactory == null) {
					serviceFactory = new ServiceFactory();
				}
			}
		}
		return serviceFactory;
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

	public StructuralUnitService getStructuralUnit() {
		return structuralUnitService;
	}

	public ProductCategoryService getProductCategory() {
		return productCategoryService;
	}

}
