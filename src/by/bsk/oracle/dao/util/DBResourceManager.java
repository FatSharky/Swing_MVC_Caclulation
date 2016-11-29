package by.bsk.oracle.dao.util;

import java.util.ResourceBundle;

/**
 * Класс {@code DBResourceManager} устанаваливает связь с property файломм.
 * 
 * @author Vladislav
 *
 */
public final class DBResourceManager {
	public static final String DB_BUNDLE_PROPERTIES = "resource.db";

	private static DBResourceManager instance = new DBResourceManager();
	private ResourceBundle resourceBundle;

	private DBResourceManager() {
		resourceBundle = ResourceBundle.getBundle(DB_BUNDLE_PROPERTIES);
	}

	public static DBResourceManager getInstance() {
		return instance;
	}

	public String getProperty(String name) {
		return resourceBundle.getString(name);
	}
}
