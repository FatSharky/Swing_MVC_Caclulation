package by.bsk.oracle.view.dialog.unit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import by.bsk.oracle.service.StructuralUnitService;
import by.bsk.oracle.service.factory.ServiceFactory;
import by.bsk.oracle.view.util.Field;
import by.bsk.oracle.view.util.GifPlace;
import by.bsk.oracle.view.util.ReturnIcon;

public class DeleteStrucuralUnitDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable jTable = new JTable();
	private DefaultTableModel tModel = new DefaultTableModel();
	private ResourceBundle resourceBundle = ResourceBundle.getBundle(Field.BUNDLE_NAME);

	private JButton btnCancel;
	private JLabel lblInfo;
	private JButton btnDelete;

	public DeleteStrucuralUnitDialog(JTable jTable, DefaultTableModel tModel) {
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
		setBounds(100, 100, 481, 222);
		getContentPane().setLayout(null);

		btnCancel = new JButton(resourceBundle.getString(Field.DD_CANCEL));

		btnCancel.setBounds(274, 106, 109, 25);
		getContentPane().add(btnCancel);

		lblInfo = new JLabel(resourceBundle.getString(Field.DD_SURE) + name + " ?");
		lblInfo.setBounds(12, 33, 451, 60);
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
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = jTable.getSelectedRow();
				String id = tModel.getValueAt(i, 0).toString();
				ServiceFactory serviceFactory = ServiceFactory.getInstance();
				StructuralUnitService structuralUnitService = serviceFactory.getStructuralUnit();
				try {
					structuralUnitService.deleteStructuralUnit(id);
					tModel.removeRow(i);
					jTable.setModel(tModel);
					jTable.getColumnModel().getColumn(0).setMinWidth(0);
					jTable.getColumnModel().getColumn(0).setMaxWidth(0);
					setVisible(false);
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

	}
}
