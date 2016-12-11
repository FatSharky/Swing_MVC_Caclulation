package by.bsk.oracle.view.dialog.price;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import by.bsk.oracle.command.AddPCategoryCommand;
import by.bsk.oracle.view.util.Coordinate;
import by.bsk.oracle.view.util.Field;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public final class AddPCategoryDialog extends JDialog {

	private ResourceBundle resourceBundle = ResourceBundle.getBundle(Field.BUNDLE_NAME);
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable jTable;
	private DefaultTableModel jTableModel;
	private int idDivision;
	private JButton bAddPriceCategory;

	public AddPCategoryDialog(JTable jTable, DefaultTableModel jTableModel, int idDivision) {
		this.jTable = jTable;
		this.jTableModel = jTableModel;
		this.idDivision = idDivision;
		createDialog();
	}

	private void createDialog() {
		setTitle(resourceBundle.getString(Field.ADD_PRICE_DIALOG_TITLE));
		setBounds(Coordinate.NORMAL_DIALOG_X, Coordinate.NORMAL_DIALOG_Y, Coordinate.NORMAL_DIALOG_WIDTH,
				Coordinate.NORMAL_DIALOG_HEIGHT);
		getContentPane().setLayout(null);

		JLabel lblName = new JLabel(resourceBundle.getString(Field.PRICE_DIALOG_NAME));
		lblName.setBounds(Coordinate.DIALOG_LBL_NAME_X, Coordinate.DIALOG_LBL_NAME_Y, Coordinate.DIALOG_LBL_NAME_WIDTH,
				Coordinate.DIALOG_LBL_NAME_HEIGHT);
		getContentPane().add(lblName);

		textField = new JTextField();
		textField.setBounds(Coordinate.DIALOG_TEXT_NAME_X, Coordinate.DIALOG_TEXT_NAME_Y,
				Coordinate.DIALOG_TEXT_NAME_WIDTH, Coordinate.DIALOG_TEXT_NAME_HEIGHT);
		getContentPane().add(textField);

		bAddPriceCategory = new JButton(resourceBundle.getString(Field.ADD_PRICE_DIALOG_BTN));
		ActionListener addPrice = new AddPCategoryCommand(textField, lblName, jTable, jTableModel, idDivision);
		bAddPriceCategory.addActionListener(addPrice);
		bAddPriceCategory.setBounds(Coordinate.DIALOG_BTN_NAME_X, Coordinate.DIALOG_BTN_NAME_Y,
				Coordinate.DIALOG_BTN_NAME_WIDTH, Coordinate.DIALOG_BTN_NAME_HEIGHT);
		getContentPane().add(bAddPriceCategory);
	}
}
