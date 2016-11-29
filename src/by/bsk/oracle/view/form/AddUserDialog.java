package by.bsk.oracle.view.form;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import by.bsk.oracle.command.AddUserCommand;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;

public class AddUserDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JTextField tLogin;
	private JTextField tPassword;
	private JTable jTable;
	private DefaultTableModel jTableModel;
	private ActionListener addUser;
	private int idDivision;

	public AddUserDialog(JTable jTable, DefaultTableModel jTableModel, int idDivision) {
		super();
		this.jTable = jTable;
		this.jTableModel = jTableModel;
		this.idDivision = idDivision;
		initComponents();
	}

	private void initComponents() {
		setTitle(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
		setBounds(100, 100, 446, 248);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUserLogin = new JLabel("\u041B\u043E\u0433\u0438\u043D:");
			lblUserLogin.setBounds(88, 13, 56, 16);
			contentPanel.add(lblUserLogin);
		}
		{
			JLabel lblUserPassword = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C:");
			lblUserPassword.setBounds(88, 48, 56, 16);
			contentPanel.add(lblUserPassword);
		}

		tLogin = new JTextField();
		tLogin.setBounds(156, 10, 151, 22);
		contentPanel.add(tLogin);
		tLogin.setColumns(10);

		tPassword = new JTextField();
		tPassword.setBounds(156, 45, 151, 22);
		contentPanel.add(tPassword);
		tPassword.setColumns(10);

		JComboBox<String> comboBox = new JComboBox<String>();
		String[] args = new String[] { "Полный", "Добавление и изменение", "Удаление", "Просмотр" };
		comboBox.setModel(new DefaultComboBoxModel<String>(args));
		comboBox.setBounds(156, 80, 151, 22);
		contentPanel.add(comboBox);

		JLabel lblAccess = new JLabel("\u0414\u043E\u0441\u0442\u0443\u043F:");
		lblAccess.setBounds(88, 83, 56, 16);
		contentPanel.add(lblAccess);

		JLabel lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(55, 103, 309, 47);
		contentPanel.add(lblInfo);

		JButton btnAddUser = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		addUser = new AddUserCommand(tLogin, tPassword, lblInfo, jTable, jTableModel, comboBox, idDivision);
		btnAddUser.addActionListener(addUser);
		btnAddUser.setBounds(150, 152, 114, 36);
		contentPanel.add(btnAddUser);

	}
}
