package by.bsk.oracle.view.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import by.bsk.oracle.command.ShowJTable;
import by.bsk.oracle.domain.Division;
import by.bsk.oracle.service.DivisionService;
import by.bsk.oracle.service.UserService;
import by.bsk.oracle.service.exception.ServiceException;
import by.bsk.oracle.service.factory.ServiceFactory;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Panel;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class RootAdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField tLogin;
	private JTextField tPassword;
	private JTabbedPane tabbedPane;
	private List<Division> divisons;
	private static RootAdminFrame rootAdminFrame;

	private RootAdminFrame() {
		setTitle(
				"\u041F\u0430\u043D\u0435\u043B\u044C \u0430\u0434\u043C\u0438\u043D\u0438\u0441\u0442\u0440\u0430\u0442\u043E\u0440\u0430");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 16, 1158, 543);
		contentPane.add(tabbedPane);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		DivisionService divisionService = serviceFactory.getDivisionService();
		try {
			divisons = divisionService.selectAllDivision();
			for (Division division : divisons) {
				Panel panel = new Panel();
				tabbedPane.addTab(division.getName(), null, panel, null);
				panel.setLayout(null);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(396, 13, 745, 487);
				panel.add(scrollPane);
				DefaultTableModel tModel = new DefaultTableModel();
				table = new JTable();
				scrollPane.setViewportView(table);
				tModel = ShowJTable.showUsersTable(division.getIdDivision());
				table.setModel(tModel);
				// Hide column id
				table.getColumnModel().getColumn(0).setMinWidth(0);
				table.getColumnModel().getColumn(0).setMaxWidth(0);

				tLogin = new JTextField();
				tLogin.setBounds(91, 29, 168, 40);
				panel.add(tLogin);
				tLogin.setColumns(10);

				tPassword = new JTextField();
				tPassword.setColumns(10);
				tPassword.setBounds(91, 94, 168, 40);
				panel.add(tPassword);

				JLabel lblLogin = new JLabel("\u041B\u043E\u0433\u0438\u043D:");
				lblLogin.setBounds(23, 41, 56, 16);
				panel.add(lblLogin);

				JLabel lblPassword = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C:");
				lblPassword.setBounds(23, 106, 56, 16);
				panel.add(lblPassword);

				JButton btnAddUser = new JButton(
						"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
				btnAddUser.setToolTipText("");

				btnAddUser.setBounds(33, 215, 226, 64);
				panel.add(btnAddUser);

				JButton btnDeleteUser = new JButton(
						"\u0423\u0434\u0430\u043B\u0438\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
				btnDeleteUser.setBounds(33, 401, 226, 64);
				panel.add(btnDeleteUser);

				JButton btnUpdateUser = new JButton(
						"\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
				btnUpdateUser.setBounds(33, 311, 226, 64);
				panel.add(btnUpdateUser);
				int countAdmin = 0;
				UserService userService = serviceFactory.getUserServie();
				countAdmin = userService.countAdmin(division.getIdDivision());
				if (countAdmin < 1) {
					JButton btnAddAdmin = new JButton(
							"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u0430\u0434\u043C\u0438\u043D\u0430");
					btnAddAdmin.setBounds(67, 158, 156, 44);
					panel.add(btnAddAdmin);
				}
			}
		} catch (ServiceException e) {

		}
		Panel panel = new Panel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(396, 13, 745, 487);
		panel.add(scrollPane);

		tLogin = new JTextField();
		tLogin.setBounds(91, 29, 168, 40);
		panel.add(tLogin);
		tLogin.setColumns(10);

		tPassword = new JTextField();
		tPassword.setColumns(10);
		tPassword.setBounds(91, 94, 168, 40);
		panel.add(tPassword);

		JLabel lblLogin = new JLabel("\u041B\u043E\u0433\u0438\u043D:");
		lblLogin.setBounds(23, 41, 56, 16);
		panel.add(lblLogin);

		JLabel lblPassword = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C:");
		lblPassword.setBounds(23, 106, 56, 16);
		panel.add(lblPassword);

		JButton btnAddUser = new JButton(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
		btnAddUser.setToolTipText("");
		btnAddUser.setBounds(33, 215, 226, 64);
		panel.add(btnAddUser);

		JButton btnDeleteUser = new JButton(
				"\u0423\u0434\u0430\u043B\u0438\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
		btnDeleteUser.setBounds(33, 401, 226, 64);
		panel.add(btnDeleteUser);

		JButton btnUpdateUser = new JButton(
				"\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
		btnUpdateUser.setBounds(33, 311, 226, 64);
		panel.add(btnUpdateUser);

		JButton btnAddAdmin = new JButton(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u0430\u0434\u043C\u0438\u043D\u0430");

		btnAddAdmin.setBounds(67, 158, 156, 44);
		panel.add(btnAddAdmin);
	}

	public static RootAdminFrame getInstance() {
		if (rootAdminFrame == null) {
			synchronized (RootAdminFrame.class) {
				if (rootAdminFrame == null) {
					rootAdminFrame = new RootAdminFrame();
				}
			}
		}
		return rootAdminFrame;
	}
}
