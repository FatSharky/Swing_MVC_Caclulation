package by.bsk.oracle.command;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.service.UserService;
import by.bsk.oracle.service.exception.ServiceException;
import by.bsk.oracle.service.factory.ServiceFactory;

public class UpdateUserCommand implements ActionListener {

	private static final Logger logger = LogManager.getLogger(UpdateUserCommand.class);

	private JTextField jLogin = new JTextField();
	private JTextField jPassword = new JTextField();
	private JTable jTable = new JTable();
	private DefaultTableModel tModel = new DefaultTableModel();
	private String id;

	public UpdateUserCommand(JTextField jLogin, JTextField jPassword, JTable jTable, DefaultTableModel tModel,
			String id) {
		this.jLogin = jLogin;
		this.jPassword = jPassword;
		this.jTable = jTable;
		this.tModel = tModel;
		this.id = id;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String login = jLogin.getText();
		String password = jPassword.getText();
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserServie();
		try {
			userService.updateUser(login, password, id);
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(null, "Что то пошло совершенно не так");
			logger.error("что то пошло совершенно не так");
		}
	}

}
