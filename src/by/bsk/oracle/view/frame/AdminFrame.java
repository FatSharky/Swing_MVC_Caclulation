package by.bsk.oracle.view.frame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.command.ShowJTable;
import by.bsk.oracle.domain.User;
import by.bsk.oracle.view.dialog.user.UpdateUserDialog;
import by.bsk.oracle.view.dialog.user.AddUserDialog;
import by.bsk.oracle.view.dialog.user.DeleteUserDialog;
import by.bsk.oracle.view.util.CheckAccess;
import by.bsk.oracle.view.util.Coordinate;
import by.bsk.oracle.view.util.ExceptionMessage;
import by.bsk.oracle.view.util.Field;
import by.bsk.oracle.view.util.GifPlace;
import by.bsk.oracle.view.util.ReturnIcon;
import by.bsk.oracle.view.util.TextFile;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class AdminFrame extends JFrame {

	private static final Logger logger = LogManager.getLogger(AdminFrame.class);

	private static final long serialVersionUID = 1L;
	private FileInputStream fis;
	private ObjectInputStream inputStream;

	private JPanel contentPane;

	private JTable table;

	private JScrollPane scrollPane;
	private DefaultTableModel tModel;
	private JLabel lblDivision;

	private JToolBar toolBar;
	private JButton btnAddUser;
	private JButton btnUpdateUser;
	private JButton btnDeleteUser;

	private MouseAdapter click;

	private static AdminFrame adminFrame;

	private JMenuBar menuBar;
	private JMenu mnForUser;
	private JMenuItem mAddUser;
	private JMenuItem mUpdateUser;
	private JMenuItem mDeleteUser;
	private JMenuItem mHide;
	private JMenuItem mInfo;
	private JMenuItem mHide1;
	private ResourceBundle resourceBundle = ResourceBundle.getBundle(Field.BUNDLE_NAME);

	private AdminFrame() {
		readUser();
	}

	private void readUser() {
		try {
			fis = new FileInputStream(TextFile.SESSION_FILE);
			inputStream = new ObjectInputStream(fis);
			User user = (User) inputStream.readObject();
			configure(user);
			createForm(user);
			registerListeners(user);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, ExceptionMessage.FILE_NOT_FOUND);
			logger.error(ExceptionMessage.FILE_NOT_FOUND);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ExceptionMessage.OPEN_IO);
			logger.error(ExceptionMessage.OPEN_IO);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, ExceptionMessage.CLASS_NOT_FOUND);
			logger.error(ExceptionMessage.CLASS_NOT_FOUND);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, ExceptionMessage.CLOSE_IO);
				logger.error(ExceptionMessage.CLOSE_IO);
			}
		}
	}

	private void configure(User user) {
		setTitle(user.getDivision().getName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(Coordinate.JFRAME_X, Coordinate.JFRAME_Y, Coordinate.JFRAME_WIDTH, Coordinate.JFRAME_HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	private void createForm(User user) {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnForUser = new JMenu(resourceBundle.getString(Field.MENU_FOR_USER));
		menuBar.add(mnForUser);

		mAddUser = createMenuItem(mnForUser, resourceBundle.getString(Field.ADD_USER), GifPlace.GIF_ADD,
				KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0));
		mUpdateUser = createMenuItem(mnForUser, resourceBundle.getString(Field.UPDATE_USER), GifPlace.GIF_UPDATE,
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.ALT_MASK));

		mDeleteUser = createMenuItem(mnForUser, resourceBundle.getString(Field.DELETE_USER), GifPlace.GIF_DELETE,
				KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));

		mHide = new JMenuItem();
		menuBar.add(mHide);

		mHide1 = new JMenuItem();
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
		scrollPane.setBounds(Coordinate.JFRAME_X, Coordinate.JTABLE_Y, Coordinate.JFRAME_WIDTH,
				Coordinate.JTABLE_HEIGHT);
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

		lblDivision = new JLabel(user.getDivision().getName());
		lblDivision.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblDivision.setBounds(10, 27, 314, 32);
		contentPane.add(lblDivision);

		toolBar = new JToolBar();
		toolBar.setBounds(Coordinate.JFRAME_X, Coordinate.JFRAME_Y, Coordinate.JFRAME_WIDTH,
				Coordinate.JTOOLBAR_HEIGHT);
		contentPane.add(toolBar);

		btnAddUser = createButton(toolBar, resourceBundle.getString(Field.ADD_INFO), GifPlace.GIF_ADD);
		btnUpdateUser = createButton(toolBar, resourceBundle.getString(Field.UPDATE_INFO), GifPlace.GIF_UPDATE);
		btnDeleteUser = createButton(toolBar, resourceBundle.getString(Field.DELETE_INFO), GifPlace.GIF_DELETE);
		table.addMouseListener(click);

	}

	private JMenuItem createMenuItem(JMenu jMenu, String name, String icon, KeyStroke keyStroke) {
		JMenuItem jMenuItem = new JMenuItem(name);
		jMenuItem.setIcon(ReturnIcon.getIcon(getClass(), icon));
		jMenuItem.setAccelerator(keyStroke);
		jMenu.add(jMenuItem);
		return jMenuItem;

	}

	// ADDBUTTONS
	private JButton createButton(JToolBar jToolBar, String info, String icon) {
		JButton jButton = new JButton("");
		jButton.setToolTipText(info);
		jButton.setIcon(ReturnIcon.getIcon(getClass(), icon));
		jToolBar.add(jButton);
		return jButton;

	}

	// LISTENER
	private void registerListeners(User user) {
		mAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddUserDialog addUser = new AddUserDialog(table, tModel, user.getDivision().getIdDivision());
				addUser.setLocationRelativeTo(adminFrame);
				addUser.setVisible(true);
			}
		});
		mUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					UpdateUserDialog addUser = new UpdateUserDialog(table, tModel);
					addUser.setLocationRelativeTo(adminFrame);
					addUser.setVisible(true);
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, ExceptionMessage.OPERATION_ON_USER);
					logger.error(ExceptionMessage.OPERATION_ON_USER);
				}
			}
		});
		mDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DeleteUserDialog deleteUser = new DeleteUserDialog(table, tModel);
					deleteUser.setVisible(true);
					deleteUser.setLocationRelativeTo(adminFrame);
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, ExceptionMessage.OPERATION_ON_USER);
					logger.error(ExceptionMessage.OPERATION_ON_USER);
				}
			}
		});

		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddUserDialog addUser = new AddUserDialog(table, tModel, user.getDivision().getIdDivision());
				addUser.setLocationRelativeTo(adminFrame);
				addUser.setVisible(true);
			}
		});
		btnUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					UpdateUserDialog addUser = new UpdateUserDialog(table, tModel);
					addUser.setLocationRelativeTo(adminFrame);
					addUser.setVisible(true);
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, ExceptionMessage.OPERATION_ON_USER);
					logger.error(ExceptionMessage.OPERATION_ON_USER);
				}
			}
		});
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DeleteUserDialog deleteUser = new DeleteUserDialog(table, tModel);
					deleteUser.setVisible(true);
					deleteUser.setLocationRelativeTo(adminFrame);
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, ExceptionMessage.OPERATION_ON_USER);
					logger.error(ExceptionMessage.OPERATION_ON_USER);
				}
			}
		});
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
