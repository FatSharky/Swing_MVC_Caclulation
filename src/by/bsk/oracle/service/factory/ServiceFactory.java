package by.bsk.oracle.service.factory;

import by.bsk.oracle.service.DishService;
import by.bsk.oracle.service.DivisionService;
import by.bsk.oracle.service.PriceCategorySevice;
import by.bsk.oracle.service.ProductService;
import by.bsk.oracle.service.RecipeService;
import by.bsk.oracle.service.ShiftMasterService;
import by.bsk.oracle.service.StructuralUnitService;
import by.bsk.oracle.service.UserService;
import by.bsk.oracle.service.impl.DishServiceImpl;
import by.bsk.oracle.service.impl.DivisionServiceImpl;
import by.bsk.oracle.service.impl.PriceCategoryServiceImpl;
import by.bsk.oracle.service.impl.ProductServiceImpl;
import by.bsk.oracle.service.impl.RecipeServiceImpl;
import by.bsk.oracle.service.impl.ShiftMasterServiceImpl;
import by.bsk.oracle.service.impl.StructuralUnitServiceImpl;
import by.bsk.oracle.service.impl.UserServiceImpl;

public final class ServiceFactory {

	private ServiceFactory() {
	}

	private static ServiceFactory serviceFactory;

	private final UserService userService = new UserServiceImpl();
	private final DivisionService divisionService = new DivisionServiceImpl();
	private final PriceCategorySevice priceCategorySevice = new PriceCategoryServiceImpl();
	private final StructuralUnitService structuralUnitService = new StructuralUnitServiceImpl();
	private final RecipeService recipeService = new RecipeServiceImpl();
	private final ShiftMasterService shiftMasterService = new ShiftMasterServiceImpl();
	private final DishService dishService = new DishServiceImpl();
	private final ProductService productService = new ProductServiceImpl();

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

	public RecipeService getRecipeService() {
		return recipeService;
	}

	public ShiftMasterService getShiftMasterService() {
		return shiftMasterService;
	}

	public DishService getDishService() {
		return dishService;
	}

	public ProductService getProductService() {
		return productService;
	}

}
