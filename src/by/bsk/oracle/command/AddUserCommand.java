package by.bsk.oracle.command;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.domain.User;
import by.bsk.oracle.service.UserService;
import by.bsk.oracle.service.exception.ServiceException;
import by.bsk.oracle.service.exception.user.WrongLoginServiceException;
import by.bsk.oracle.service.exception.user.WrongPasswordServiceException;
import by.bsk.oracle.service.factory.ServiceFactory;

public class AddUserCommand implements ActionListener {

	private static final Logger logger = LogManager.getLogger(AddUserCommand.class);

	private JTextField jLogin;
	private JTextField jPassword;
	private JLabel jLabel;
	private JTable jTable;
	private DefaultTableModel jTableModel;
	private JComboBox<String> jComboBox;
	private int idDivision;

	public AddUserCommand(JTextField jLogin, JTextField jPassword, JLabel jLabel, JTable jTable,
			DefaultTableModel jTableModel, JComboBox<String> jComboBox, int idDivision) {
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
				String role = user.getRole();
				Object[] data = { id, login, password, role, access };
				jTableModel.insertRow(jTable.getRowCount(), data);
				jLabel.setForeground(Color.GREEN);
				jLabel.setText("Пользователь успешно добавлен");
				jLogin.setText("");
				jPassword.setText("");

				jTable.setModel(jTableModel);

				jTable.getColumnModel().getColumn(0).setMinWidth(0);
				jTable.getColumnModel().getColumn(0).setMaxWidth(0);
			} else {
				jLabel.setForeground(Color.RED);
				jLabel.setText("Пользователь уже существует в базе данных");

			}
		} catch (WrongLoginServiceException e) {
			jLabel.setForeground(Color.RED);
			jLabel.setText("Логин пользователя не должен быть пустым или >10 символов");
		} catch (WrongPasswordServiceException e) {
			jLabel.setForeground(Color.RED);
			jLabel.setText("Пароль пользователя не должен быть пустым или >10 символов");
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(null, "Что то пошло совершенно не так");
			logger.error("что то пошло совершенно не так");
		}

	}

}
