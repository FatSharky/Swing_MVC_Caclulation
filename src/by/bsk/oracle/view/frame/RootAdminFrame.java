package by.bsk.oracle.view.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import by.bsk.oracle.command.ShowJTable;
import by.bsk.oracle.domain.Division;
import by.bsk.oracle.service.DivisionService;
import by.bsk.oracle.service.exception.ServiceException;
import by.bsk.oracle.service.factory.ServiceFactory;
import by.bsk.oracle.view.dialog.user.AddUserDialog;
import by.bsk.oracle.view.util.Field;
import by.bsk.oracle.view.util.GifPlace;
import by.bsk.oracle.view.util.ReturnIcon;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.JToolBar;
import javax.swing.JMenuItem;

public class RootAdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable table;
	private JTabbedPane tabbedPane;
	private List<Division> divisons;
	private JMenuBar menuBar;
	private static RootAdminFrame rootAdminFrame;

	private JMenuItem mHide2;
	private JMenuItem mHide;
	private JMenuItem mInfo;
	
	private DefaultTableModel tModel;
	private JButton btnAddUser;

	private ResourceBundle resourceBundle = ResourceBundle.getBundle(Field.BUNDLE_NAME);
	private JButton btnNewButton;

	private RootAdminFrame() {
		configure();
		createForm();
	}

	private void configure() {
		setTitle(resourceBundle.getString(Field.ROOT_ADMIN_FRAME_TITLE));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
	}

	private void createForm() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mHide2 = new JMenuItem();
		menuBar.add(mHide2);

		mHide = new JMenuItem();
		menuBar.add(mHide);

		mInfo = new JMenuItem(resourceBundle.getString(Field.ROOT_ADMIN_FRAME_MENU_ITEM));
		menuBar.add(mInfo);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(0, 0, 1182, 652);
		contentPane.add(tabbedPane);
		
		btnNewButton = new JButton("New button");
		tabbedPane.addTab("New tab", null, btnNewButton, null);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		DivisionService divisionService = serviceFactory.getDivisionService();
		try {
			divisons = divisionService.selectAllDivision();
			for (Division division : divisons) {
				Panel panel = new Panel();
				panel.setEnabled(false);
				panel.setBackground(UIManager.getColor("scrollbar"));
				tabbedPane.addTab(division.getName(), null, panel, null);
				panel.setLayout(null);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 47, 1177, 542);
				panel.add(scrollPane);

				tModel = new DefaultTableModel();
				table = new JTable();
				scrollPane.setViewportView(table);
				tModel = ShowJTable.showUsersTable(division.getIdDivision());
				table.setModel(tModel);
				// Hide column id
				table.getColumnModel().getColumn(0).setMinWidth(0);
				table.getColumnModel().getColumn(0).setMaxWidth(0);

				JToolBar toolBar = new JToolBar();
				toolBar.setBounds(0, 0, 1177, 34);
				panel.add(toolBar);

				btnAddUser = createButton(toolBar, resourceBundle.getString(Field.ADD_INFO), GifPlace.GIF_ADD);
				// = new JButton("");
				// btnNewButton.setIcon(new
				// ImageIcon(RootAdminFrame.class.getResource("/icon/add_obj.gif")));
				// toolBar.add(btnNewButton);
				JButton btnUpdateUser = createButton(toolBar, resourceBundle.getString(Field.UPDATE_INFO),
						GifPlace.GIF_UPDATE);
				// JButton btnNewButton_1 = new JButton("");
				// btnNewButton_1.setIcon(new
				// ImageIcon(RootAdminFrame.class.getResource("/icon/edit.gif")));
				// toolBar.add(btnNewButton_1);
				JButton btnDeleteUser = createButton(toolBar, resourceBundle.getString(Field.DELETE_INFO),
						GifPlace.GIF_DELETE);
				// JButton btnNewButton_2 = new JButton("");
				// btnNewButton_2.setIcon(new
				// ImageIcon(RootAdminFrame.class.getResource("/icon/remove.gif")));
				// toolBar.add(btnNewButton_2);

				JButton bAddAdmin = new JButton(resourceBundle.getString(Field.ROOT_ADMIN_BUTTON_ADMIN));
				toolBar.add(bAddAdmin);
				btnAddUser.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						AddUserDialog addUser = new AddUserDialog(table, tModel, division.getIdDivision());
						addUser.setLocationRelativeTo(rootAdminFrame);
						addUser.setVisible(true);
					}
				});
			}
		} catch (ServiceException e) {

		}
	}

	// ADDBUTTONS
	private JButton createButton(JToolBar jToolBar, String info, String icon) {
		JButton jButton = new JButton("");
		jButton.setToolTipText(info);
		jButton.setIcon(ReturnIcon.getIcon(getClass(), icon));
		jToolBar.add(jButton);
		return jButton;

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
