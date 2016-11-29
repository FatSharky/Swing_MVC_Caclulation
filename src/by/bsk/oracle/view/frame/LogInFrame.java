package by.bsk.oracle.view.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import by.bsk.oracle.command.LogInUserCommand;
import by.bsk.oracle.view.util.Field;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.SwingConstants;
import java.awt.Color;

public class LogInFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField tLogin;
	private JTextField tPassword;

	private JLabel lblLogin;
	private JLabel lblPassword;

	private JButton btnLogIn;

	private JLabel lblInfro;

	private static LogInFrame logIn;

	private LogInFrame() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(Field.BUNDLE_NAME);

		setTitle(resourceBundle.getString(Field.APPLICATION_TITLE));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblLogin = new JLabel(resourceBundle.getString(Field.LBL_LOGIN));
		lblLogin.setBounds(67, 48, 56, 16);
		contentPane.add(lblLogin);

		lblPassword = new JLabel(resourceBundle.getString(Field.LBL_PASSWORD));
		lblPassword.setBounds(67, 110, 56, 16);
		contentPane.add(lblPassword);

		tLogin = new JTextField();
		tLogin.setBounds(135, 39, 171, 35);
		contentPane.add(tLogin);
		tLogin.setColumns(10);

		tPassword = new JTextField();
		tPassword.setText("");
		tPassword.setBounds(135, 101, 171, 35);
		contentPane.add(tPassword);
		tPassword.setColumns(10);

		lblInfro = new JLabel();
		lblInfro.setForeground(Color.RED);
		lblInfro.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfro.setBounds(67, 149, 313, 28);
		contentPane.add(lblInfro);

		btnLogIn = new JButton(resourceBundle.getString(Field.BTN_LOGIN));
		ActionListener command = new LogInUserCommand(tLogin, tPassword, lblInfro, logIn);
		btnLogIn.addActionListener(command);

		btnLogIn.setBounds(147, 191, 133, 49);
		contentPane.add(btnLogIn);
	}

	public static LogInFrame getInstance() {
		if (logIn == null) {
			synchronized (LogInFrame.class) {
				if (logIn == null) {
					logIn = new LogInFrame();
				}
			}
		}
		return logIn;
	}
}
