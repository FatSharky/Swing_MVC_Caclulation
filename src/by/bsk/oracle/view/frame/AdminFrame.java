package by.bsk.oracle.view.frame;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
import by.bsk.oracle.view.util.CheckAccess;

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

	private MouseAdapter click;

	private static AdminFrame adminFrame;

	private ActionListener updateUser;
	private ActionListener deleteUser;
	private ActionListener addUser;
	private JMenuItem mntmNewMenuItem_2;

	private AdminFrame() {
		try {
			fis = new FileInputStream("temp.txt");
			inputStream = new ObjectInputStream(fis);
			User user = (User) inputStream.readObject();

			setTitle(user.getDivision().getName());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 1291, 700);

			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);

			JMenu mnNewMenu = new JMenu(
					"\u0420\u0430\u0431\u043E\u0442\u0430 \u0441 \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F\u043C\u0438");
			menuBar.add(mnNewMenu);

			JMenuItem mntmNewMenuItem = new JMenuItem("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
			mnNewMenu.add(mntmNewMenuItem);

			JMenuItem menuItem_1 = new JMenuItem("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C");
			mnNewMenu.add(menuItem_1);

			JMenuItem menuItem = new JMenuItem("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
			mnNewMenu.add(menuItem);

			JMenuItem mntmHide = new JMenuItem("");
			menuBar.add(mntmHide);

			mntmNewMenuItem_2 = new JMenuItem("");
			menuBar.add(mntmNewMenuItem_2);
			String access = CheckAccess.checkAccess(user.getAccess());
			JMenuItem mntmNewMenuItem_1 = new JMenuItem("Логин: " + user.getLogin() + "     Уровень дотупа: " + access);
			mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.LEFT);
			menuBar.add(mntmNewMenuItem_1);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			scrollPane = new JScrollPane();
			scrollPane.setBounds(343, 82, 918, 501);
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
			panel.setBounds(0, 94, 296, 501);
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

			lblLogin = new JLabel("\u041B\u043E\u0433\u0438\u043D:");
			lblLogin.setBounds(12, 24, 56, 16);
			panel.add(lblLogin);

			lblPassword = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C:");
			lblPassword.setBounds(12, 87, 56, 16);
			panel.add(lblPassword);

			btnUpdateUser1 = new JButton(
					"\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
			btnUpdateUser1.setEnabled(false);
			updateUser = new UpdateUserCommand(tLogin, tPassword, table, lblInfo, tModel);
			btnUpdateUser1.addActionListener(updateUser);
			btnUpdateUser1.setBounds(47, 294, 195, 66);
			panel.add(btnUpdateUser1);

			btnDeleteUser1 = new JButton(
					"\u0423\u0434\u0430\u043B\u0438\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
			btnDeleteUser1.setEnabled(false);
			deleteUser = new DeleteUserCommand(table, tModel, lblInfo);
			btnDeleteUser1.addActionListener(deleteUser);
			btnDeleteUser1.setBounds(47, 406, 195, 66);
			panel.add(btnDeleteUser1);

			btnAddUser1 = new JButton(
					"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
			addUser = new AddUserCommand(tLogin, tPassword, lblInfo, table, tModel, user.getDivision().getIdDivision());
			btnAddUser1.addActionListener(addUser);
			btnAddUser1.setBounds(47, 178, 195, 66);
			panel.add(btnAddUser1);

			lblDivision = new JLabel(user.getDivision().getName());
			lblDivision.setFont(new Font("Tahoma", Font.BOLD, 19));
			lblDivision.setBounds(344, 37, 314, 32);
			contentPane.add(lblDivision);

			click = new ClickCommand(tLogin, tPassword, table, tModel, btnDeleteUser1, btnUpdateUser1);

			JToolBar toolBar = new JToolBar();
			toolBar.setBounds(0, 0, 1273, 32);
			contentPane.add(toolBar);

			JButton btnAddUser = new JButton("");
			btnAddUser.setToolTipText(
					"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
			btnAddUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnAddUser.setIcon(new ImageIcon(AdminFrame.class.getResource("/icon/add_obj.gif")));
			toolBar.add(btnAddUser);

			JButton btnUpdateUser = new JButton("");
			btnUpdateUser.setToolTipText(
					"\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
			btnUpdateUser.setIcon(new ImageIcon(AdminFrame.class.getResource("/icon/edit.gif")));
			toolBar.add(btnUpdateUser);

			JButton btnWatch = new JButton("");
			btnWatch.setToolTipText(
					"\u041F\u043E\u0441\u043C\u043E\u0442\u0440\u0435\u0442\u044C \u0438\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0438\u044E \u043E \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u0435");
			btnWatch.setIcon(new ImageIcon(AdminFrame.class.getResource("/icon/details.gif")));
			toolBar.add(btnWatch);

			JButton btnNewButton = new JButton("");
			btnNewButton.setToolTipText(
					"\u0423\u0434\u0430\u043B\u0438\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
			btnNewButton.setIcon(new ImageIcon(AdminFrame.class.getResource("/icon/remove.gif")));
			btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
			toolBar.add(btnNewButton);
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
