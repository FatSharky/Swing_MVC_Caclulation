package by.bsk.oracle.view.dialog.user;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import by.bsk.oracle.command.UpdateUserCommand;
import by.bsk.oracle.view.util.Field;

public class UpdateUserDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JTextField tLogin;
	private JTextField tPassword;
	private JTable jTable;
	private DefaultTableModel jTableModel;
	private ActionListener addUser;
	private int idDivision;
	private ResourceBundle resourceBundle = ResourceBundle.getBundle(Field.BUNDLE_NAME);

	public UpdateUserDialog(JTable jTable, DefaultTableModel jTableModel) {
		super();
		this.jTable = jTable;
		this.jTableModel = jTableModel;
		configure();
		initComponents();
	}

	private void configure() {
		setTitle(resourceBundle.getString(Field.ADD_DIALOG));
		setBounds(100, 100, 446, 248);
		getContentPane().setLayout(new BorderLayout());
	}

	private void initComponents() {

		int i = jTable.getSelectedRow();
		String id = jTableModel.getValueAt(i, 0).toString();
		String name = jTableModel.getValueAt(i, 1).toString();
		String password = jTableModel.getValueAt(i, 2).toString();

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblUserLogin = new JLabel(resourceBundle.getString(Field.LBL_LOGIN));
		lblUserLogin.setBounds(88, 13, 56, 16);
		contentPanel.add(lblUserLogin);

		JLabel lblUserPassword = new JLabel(resourceBundle.getString(Field.LBL_PASSWORD));
		lblUserPassword.setBounds(88, 48, 56, 16);
		contentPanel.add(lblUserPassword);

		tLogin = new JTextField();
		tLogin.setText(name);
		tLogin.setBounds(156, 10, 151, 22);
		contentPanel.add(tLogin);
		tLogin.setColumns(10);

		tPassword = new JTextField();
		tPassword.setText(password);
		tPassword.setBounds(156, 45, 151, 22);
		contentPanel.add(tPassword);
		tPassword.setColumns(10);

		JComboBox<String> comboBox = new JComboBox<String>();
		String[] args = new String[] { resourceBundle.getString(Field.DADD_FULL),
				resourceBundle.getString(Field.DADD_ADD_UPDATE), resourceBundle.getString(Field.DADD_DELETE),
				resourceBundle.getString(Field.DADD_WATCH) };
		comboBox.setModel(new DefaultComboBoxModel<String>(args));
		comboBox.setBounds(156, 80, 151, 22);
		contentPanel.add(comboBox);

		JLabel lblAccess = new JLabel(resourceBundle.getString(Field.ACCESS));
		lblAccess.setBounds(88, 83, 56, 16);
		contentPanel.add(lblAccess);

		JLabel lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(55, 103, 309, 47);
		contentPanel.add(lblInfo);

		JButton btnAddUser = new JButton(resourceBundle.getString(Field.DADD_ADD_BUTTON));
		addUser = new UpdateUserCommand(tLogin, tPassword, jTable, jTableModel, id);
		btnAddUser.addActionListener(addUser);
		btnAddUser.setBounds(150, 152, 114, 36);
		contentPanel.add(btnAddUser);

	}
}
