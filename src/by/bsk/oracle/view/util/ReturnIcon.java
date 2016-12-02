package by.bsk.oracle.view.util;

import javax.swing.ImageIcon;

public final class ReturnIcon {
	private ReturnIcon() {
	}

	private static final String ICONS_PATH = "/icon/";

	public static ImageIcon getIcon(Class<?> kclass, String icone) {
		return new ImageIcon(kclass.getResource(ICONS_PATH + icone + ".gif"));
	}
}
