package by.bsk.oracle.view.util;

import java.awt.Toolkit;

public final class Coordinate {

	private Coordinate() {
	}

	public static final int JFRAME_X = 0;
	public static final int JFRAME_Y = 0;
	public static final int JFRAME_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width
			- (Toolkit.getDefaultToolkit().getScreenSize().width * 20 / 100);
	public static final int JFRAME_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height
			- (Toolkit.getDefaultToolkit().getScreenSize().height * 20 / 100);
	public static final int JTABLE_HEIGHT = JFRAME_HEIGHT - (JFRAME_HEIGHT * 20 / 100);
	public static final int JTABLE_Y = 60;
	public static final int JTOOLBAR_HEIGHT = 30;

	public static final int NORMAL_DIALOG_X = 100;
	public static final int NORMAL_DIALOG_Y = 100;
	public static final int NORMAL_DIALOG_WIDTH = 430;
	public static final int NORMAL_DIALOG_HEIGHT = 250;

	public static final int DIALOG_LBL_NAME_X = 85;
	public static final int DIALOG_LBL_NAME_Y = 65;
	public static final int DIALOG_LBL_NAME_WIDTH = 95;
	public static final int DIALOG_LBL_NAME_HEIGHT = 25;

	public static final int DIALOG_TEXT_NAME_X = 183;
	public static final int DIALOG_TEXT_NAME_Y = 66;
	public static final int DIALOG_TEXT_NAME_WIDTH = 147;
	public static final int DIALOG_TEXT_NAME_HEIGHT = 22;

	public static final int DIALOG_BTN_NAME_X = 150;
	public static final int DIALOG_BTN_NAME_Y = 140;
	public static final int DIALOG_BTN_NAME_WIDTH = 100;
	public static final int DIALOG_BTN_NAME_HEIGHT = 30;

}
