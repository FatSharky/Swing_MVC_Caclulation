package by.bsk.oracle.view.frame;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import by.bsk.oracle.command.AddUserCommand;
import by.bsk.oracle.command.DeleteUserCommand;
import by.bsk.oracle.command.ShowJTable;
import by.bsk.oracle.command.UpdateUserCommand;
import by.bsk.oracle.command.mouse.ClickCommand;
import by.bsk.oracle.domain.User;
import by.bsk.oracle.view.form.AddUserDialog;
import by.bsk.oracle.view.form.DeleteUserDialog;
import by.bsk.oracle.view.util.CheckAccess;
import by.bsk.oracle.view.util.Field;
import by.bsk.oracle.view.util.GifPlace;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class AdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private FileInputStream fis;
	private ObjectInputStream inputStream;

	private JPanel contentPane;
	private JPanel panel;

	private JTable table;

	private JTextField tLogin;
	private JTextField tPassword;

	private JScrollPane scrollPane;
	private DefaultTableModel tModel;

	private JLabel lblInfo;
	private JLabel lblLogin;
	private JLabel lblPassword;
	private JLabel lblDivision;

	private JButton btnUpdateUser1;
	private JButton btnDeleteUser1;
	private JButton btnAddUser1;

	private JToolBar toolBar;
	private JButton btnAddUser;
	private JButton btnUpdateUser;
	private JButton btnDeleteUser;

	private MouseAdapter click;

	private static AdminFrame adminFrame;

	private ActionListener updateUser;
	private ActionListener deleteUser;
	private ActionListener addUser;

	private JMenuBar menuBar;
	private JMenu mnForUser;
	private JMenuItem mAddUser;
	private JMenuItem mUpdateUser;
	private JMenuItem mDeleteUser;
	private JMenuItem mHide;
	private JMenuItem mInfo;
	private JMenuItem mHide1;

	private AdminFrame() {
		try {
			fis = new FileInputStream("temp.txt");
			inputStream = new ObjectInputStream(fis);
			User user = (User) inputStream.readObject();

			ResourceBundle resourceBundle = ResourceBundle.getBundle(Field.BUNDLE_NAME);

			setTitle(user.getDivision().getName());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 1180, 700);

			menuBar = new JMenuBar();
			setJMenuBar(menuBar);

			mnForUser = new JMenu(resourceBundle.getString(Field.MENU_FOR_USER));
			menuBar.add(mnForUser);

			mAddUser = new JMenuItem(resourceBundle.getString(Field.ADD_USER));
			mnForUser.add(mAddUser);

			mUpdateUser = new JMenuItem(resourceBundle.getString(Field.UPDATE_USER));
			mnForUser.add(mUpdateUser);

			mDeleteUser = new JMenuItem(resourceBundle.getString(Field.DELETE_USER));
			mnForUser.add(mDeleteUser);

			mHide = new JMenuItem("");
			menuBar.add(mHide);

			mHide1 = new JMenuItem("");
			menuBar.add(mHide1);

			String access = CheckAccess.checkAccess(user.getAccess());
			mInfo = new JMenuItem(resourceBundle.getString(Field.LOGIN) + " " + user.getLogin() + "         "
					+ resourceBundle.getString(Field.ACCESS) + " " + access);
			mInfo.setHorizontalAlignment(SwingConstants.LEFT);
			menuBar.add(mInfo);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 60, 1150, 501);
			contentPane.add(scrollPane);

			tModel = new DefaultTableModel();
			tModel = ShowJTable.showOnlyUserTable(user.getDivision().getIdDivision());
			table = new JTable();
			scrollPane.setViewportView(table);
			table.setModel(tModel);

			// Hide column id
			table.getColumnModel().getColumn(0).setMinWidth(0);
			table.getColumnModel().getColumn(0).setMaxWidth(0);

			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(0, 574, 296, 21);
			contentPane.add(panel);
			panel.setLayout(null);

			lblInfo = new JLabel();
			lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
			lblInfo.setBounds(12, 131, 272, 30);
			panel.add(lblInfo);

			tLogin = new JTextField();
			tLogin.setBounds(87, 13, 197, 39);
			panel.add(tLogin);
			tLogin.setColumns(10);

			tPassword = new JTextField();
			tPassword.setColumns(10);
			tPassword.setBounds(87, 76, 197, 39);
			panel.add(tPassword);

			lblLogin = new JLabel(resourceBundle.getString(Field.LBL_LOGIN));
			lblLogin.setBounds(12, 24, 56, 16);
			panel.add(lblLogin);

			lblPassword = new JLabel(resourceBundle.getString(Field.LBL_PASSWORD));
			lblPassword.setBounds(12, 87, 56, 16);
			panel.add(lblPassword);

			btnUpdateUser1 = new JButton("");
			btnUpdateUser1.setEnabled(false);
			updateUser = new UpdateUserCommand(tLogin, tPassword, table, lblInfo, tModel);
			btnUpdateUser1.addActionListener(updateUser);
			btnUpdateUser1.setBounds(47, 294, 195, 66);
			panel.add(btnUpdateUser1);

			btnDeleteUser1 = new JButton("");
			btnDeleteUser1.setEnabled(false);
			deleteUser = new DeleteUserCommand(table, tModel, lblInfo);
			btnDeleteUser1.addActionListener(deleteUser);
			btnDeleteUser1.setBounds(47, 406, 195, 66);
			panel.add(btnDeleteUser1);

			btnAddUser1 = new JButton("");
			// /addUser = new AddUserCommand(tLogin, tPassword, lblInfo, table,
			// tModel, user.getDivision().getIdDivision());
			// btnAddUser1.addActionListener(addUser);
			btnAddUser1.setBounds(47, 178, 195, 66);
			panel.add(btnAddUser1);

			lblDivision = new JLabel(user.getDivision().getName());
			lblDivision.setFont(new Font("Tahoma", Font.BOLD, 19));
			lblDivision.setBounds(10, 27, 314, 32);
			contentPane.add(lblDivision);

			click = new ClickCommand(tLogin, tPassword, table, tModel, btnDeleteUser1, btnUpdateUser1);

			toolBar = new JToolBar();
			toolBar.setBounds(0, 0, 1273, 32);
			contentPane.add(toolBar);

			btnAddUser = new JButton("");
			btnAddUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					AddUserDialog addUser = new AddUserDialog(table, tModel, user.getDivision().getIdDivision());
					addUser.setVisible(true);
				}
			});
			btnAddUser.setToolTipText(resourceBundle.getString(Field.ADD_INFO));

			btnAddUser.setIcon(new ImageIcon(AdminFrame.class.getResource(GifPlace.GIF_ADD)));
			toolBar.add(btnAddUser);

			btnUpdateUser = new JButton("");
			btnUpdateUser.setToolTipText(resourceBundle.getString(Field.UPDATE_INFO));
			btnUpdateUser.setIcon(new ImageIcon(AdminFrame.class.getResource(GifPlace.GIF_UPDATE)));
			toolBar.add(btnUpdateUser);

			btnDeleteUser = new JButton("");
			btnDeleteUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DeleteUserDialog deleteUser = new DeleteUserDialog(table, tModel);
					deleteUser.setVisible(true);
				}
			});
			btnDeleteUser.setToolTipText(resourceBundle.getString(Field.DELETE_INFO));
			btnDeleteUser.setIcon(new ImageIcon(AdminFrame.class.getResource(GifPlace.GIF_DELETE)));
			btnDeleteUser.setHorizontalAlignment(SwingConstants.RIGHT);
			toolBar.add(btnDeleteUser);
			table.addMouseListener(click);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {

			}
		}
	}

	public static AdminFrame getInstance() {
		if (adminFrame == null) {
			synchronized (AdminFrame.class) {
				if (adminFrame == null) {
					adminFrame = new AdminFrame();
				}
			}
		}
		return adminFrame;
	}
}
