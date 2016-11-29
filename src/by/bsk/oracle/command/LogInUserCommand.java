package by.bsk.oracle.command;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import by.bsk.oracle.domain.User;
import by.bsk.oracle.service.UserService;
import by.bsk.oracle.service.exception.ServiceException;
import by.bsk.oracle.service.factory.ServiceFactory;
import by.bsk.oracle.view.frame.AdminFrame;
import by.bsk.oracle.view.frame.RootAdminFrame;
import by.bsk.oracle.view.frame.UserFrame;

public class LogInUserCommand implements ActionListener {

	private JTextField jLogin = new JTextField();
	private JTextField jPassword = new JTextField();
	private JFrame jAdminFrame = new JFrame();
	private JLabel jLabel = new JLabel();

	public LogInUserCommand(JTextField jLogin, JTextField jPassword, JLabel jLabel, JFrame jAdminFrame) {
		super();
		this.jLogin = jLogin;
		this.jPassword = jPassword;
		this.jLabel = jLabel;
		this.jAdminFrame = jAdminFrame;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String login = jLogin.getText();
		String password = jPassword.getText();
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserServie();
		User user = null;
		try {
			if (userService.userExist(login, password)) {
				user = userService.userLogInAsRootAdmin(login, password);
				if (user.getRole().equals("root")) {
					user = userService.userLogInAsRootAdmin(login, password);
					CreateConfigFile(user);
					RootAdminFrame rootAdminFrame = RootAdminFrame.getInstance();
					rootAdminFrame.setVisible(true);
					jLabel.setText("");
				} else {
					user = userService.userLogIn(login, password);
					CreateConfigFile(user);
					if (user.getRole().equals("admin")) {
						AdminFrame adminFrame = AdminFrame.getInstance();
						adminFrame.setVisible(true);
						jLabel.setText("");
					} else if (user.getRole().equals("user")) {
						JFrame userFrame = new UserFrame();
						userFrame.setVisible(true);
						jLabel.setText("");
					}
				}
				// jAdminFrame.setVisible(false);
			} else {
				jLabel.setForeground(Color.RED);
				jLabel.setText("Пользователя с таким именем не существует");
			}
		} catch (ServiceException e) {
			jLabel.setForeground(Color.RED);
			jLabel.setText("Критиическая ошибка с кодом");
		} catch (FileNotFoundException e) {
			jLabel.setForeground(Color.RED);
			jLabel.setText("Прблемы с фалом конфигуации");
		} catch (IOException e) {
			jLabel.setForeground(Color.RED);
			jLabel.setText("Проблема с потоками");
		}

	}

	private void CreateConfigFile(User user) throws IOException {
		FileOutputStream fos = new FileOutputStream("temp.txt");
		ObjectOutputStream outputStream = new ObjectOutputStream(fos);
		outputStream.writeObject(user);
		outputStream.flush();
		outputStream.close();
	}

}
