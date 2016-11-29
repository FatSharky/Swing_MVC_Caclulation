package by.bsk.oracle.command.mouse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ClickCommand extends MouseAdapter {
	private JTextField jLogin = new JTextField();
	private JTable jTable = new JTable();
	private JTextField jPassword = new JTextField();
	private DefaultTableModel tModel = new DefaultTableModel();
	private JButton btnUpdate = new JButton();
	private JButton btnDelete = new JButton();

	public ClickCommand(JTextField jLogin, JTextField jPassword, JTable jTable, DefaultTableModel tModel,
			JButton btnUpdate, JButton btnDelete) {
		this.jLogin = jLogin;
		this.jPassword = jPassword;
		this.jTable = jTable;
		this.tModel = tModel;
		this.btnDelete = btnDelete;
		this.btnUpdate = btnUpdate;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		super.mouseClicked(arg0);
		int i = jTable.getSelectedRow();
		String username = tModel.getValueAt(i, 1).toString();
		jLogin.setText(username);
		String password = tModel.getValueAt(i, 2).toString();
		jPassword.setText(password);
		btnDelete.setEnabled(true);
		btnUpdate.setEnabled(true);

	}
}
