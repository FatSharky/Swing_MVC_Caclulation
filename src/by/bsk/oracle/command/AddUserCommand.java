package by.bsk.oracle.command;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import by.bsk.oracle.domain.User;
import by.bsk.oracle.service.UserService;
import by.bsk.oracle.service.factory.ServiceFactory;
import by.bsk.oracle.view.util.CheckAccess;

public class AddUserCommand implements ActionListener {
	private JTextField jLogin;
	private JTextField jPassword;
	private JLabel jLabel;
	private JTable jTable;
	private DefaultTableModel jTableModel;
	private JComboBox<String> jComboBox;
	private int idDivision;

	public AddUserCommand(JTextField jLogin, JTextField jPassword, JLabel jLabel, JTable jTable,
			DefaultTableModel jTableModel, JComboBox<String> jComboBox, int idDivision) {
		super();
		this.jLogin = jLogin;
		this.jPassword = jPassword;
		this.jLabel = jLabel;
		this.jTable = jTable;
		this.jTableModel = jTableModel;
		this.jComboBox = jComboBox;
		this.idDivision = idDivision;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String login = jLogin.getText();
		String password = jPassword.getText();
		String access = jComboBox.getSelectedItem().toString();
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserServie();
		try {
			if (!(userService.userExist(login, password))) {
				User user = null;
				userService.addUser(login, password, access, idDivision);
				user = userService.selectUserByLoginAndPass(login, password);
				int id = user.getIdUser();
				String aLogin = user.getLogin();
				String aPassword = user.getPassword();
				String role = user.getRole();
				String aAccess = CheckAccess.checkAccess(user.getAccess());
				Object[] data = { id, aLogin, aPassword, role, aAccess };
				jTableModel.insertRow(jTable.getRowCount(), data);
				jLabel.setForeground(Color.GREEN);
				jLabel.setText("ѕользователь успешно добавлен");
				jLogin.setText("");
				jPassword.setText("");

				jTable.setModel(jTableModel);

				jTable.getColumnModel().getColumn(0).setMinWidth(0);
				jTable.getColumnModel().getColumn(0).setMaxWidth(0);
			} else {
				jLabel.setForeground(Color.RED);
				jLabel.setText("ѕользователь уже существует в базе данных");

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
