package by.bsk.oracle.view.dialog.price;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import by.bsk.oracle.command.DeletePCategoryCommand;
import by.bsk.oracle.view.util.Field;
import by.bsk.oracle.view.util.GifPlace;
import by.bsk.oracle.view.util.ReturnIcon;

public class DeletePCategoryDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTable jTable = new JTable();
	private DefaultTableModel tModel = new DefaultTableModel();
	private ResourceBundle resourceBundle = ResourceBundle.getBundle(Field.BUNDLE_NAME);

	private JButton btnCancel;
	private JLabel lblInfo;
	private JButton btnDelete;

	public DeletePCategoryDialog(JTable jTable, DefaultTableModel tModel) {
		this.jTable = jTable;
		this.tModel = tModel;
		initComponents();
	}

	private void initComponents() throws IndexOutOfBoundsException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int i = jTable.getSelectedRow();

		String name = tModel.getValueAt(i, 1).toString();
		setTitle(resourceBundle.getString(Field.DELETE_DIALOG_TITLE));
		setBounds(100, 100, 437, 222);
		getContentPane().setLayout(null);

		btnCancel = new JButton(resourceBundle.getString(Field.DD_CANCEL));

		btnCancel.setBounds(219, 106, 109, 25);
		getContentPane().add(btnCancel);

		lblInfo = new JLabel(resourceBundle.getString(Field.DD_SURE) + name + " ?");
		lblInfo.setBounds(53, 33, 318, 60);
		getContentPane().add(lblInfo);

		btnDelete = new JButton(resourceBundle.getString(Field.DD_DELETE));

		btnDelete.setIcon(ReturnIcon.getIcon(getClass(), GifPlace.GIF_DELETE));
		btnDelete.setBounds(63, 106, 109, 25);
		getContentPane().add(btnDelete);
		registerListeners();
	}

	private void registerListeners() {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		ActionListener deleteUser = new DeletePCategoryCommand(jTable, tModel);
		btnDelete.addActionListener(deleteUser);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

	}

}
