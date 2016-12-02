package by.bsk.oracle.view.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import by.bsk.oracle.command.ShowJTable;
import by.bsk.oracle.domain.Division;
import by.bsk.oracle.service.DivisionService;
import by.bsk.oracle.service.exception.ServiceException;
import by.bsk.oracle.service.factory.ServiceFactory;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Panel;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

public class RootAdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTabbedPane tabbedPane;
	private List<Division> divisons;
	private static RootAdminFrame rootAdminFrame;

	private RootAdminFrame() {
		setTitle(
				"\u041F\u0430\u043D\u0435\u043B\u044C \u0430\u0434\u043C\u0438\u043D\u0438\u0441\u0442\u0440\u0430\u0442\u043E\u0440\u0430");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("");
		menuBar.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("");
		menuBar.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Пользователь: root");
		menuBar.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(0, 0, 1182, 652);
		contentPane.add(tabbedPane);

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
				
				DefaultTableModel tModel = new DefaultTableModel();
				table = new JTable();
				scrollPane.setViewportView(table);
				tModel = ShowJTable.showUsersTable(division.getIdDivision());
				table.setModel(tModel);
				// Hide column id
				table.getColumnModel().getColumn(0).setMinWidth(0);
				table.getColumnModel().getColumn(0).setMaxWidth(0);

			//	tLogin = new JTextField();
			//	tLogin.setBounds(91, 29, 168, 40);
			//	panel.add(tLogin);
			//	tLogin.setColumns(10);

			//	tPassword = new JTextField();
			//	tPassword.setColumns(10);
			//	tPassword.setBounds(91, 94, 168, 40);
			//	panel.add(tPassword);

				JToolBar toolBar = new JToolBar();
				toolBar.setBounds(0, 0, 1177, 34);
				panel.add(toolBar);
				
				JButton btnNewButton = new JButton("");
				btnNewButton.setIcon(new ImageIcon(RootAdminFrame.class.getResource("/icon/add_obj.gif")));
				toolBar.add(btnNewButton);
				
				JButton btnNewButton_1 = new JButton("");
				btnNewButton_1.setIcon(new ImageIcon(RootAdminFrame.class.getResource("/icon/edit.gif")));
				toolBar.add(btnNewButton_1);
				
				JButton btnNewButton_2 = new JButton("");
				btnNewButton_2.setIcon(new ImageIcon(RootAdminFrame.class.getResource("/icon/remove.gif")));
				toolBar.add(btnNewButton_2);
			}
		} catch (ServiceException e) {

		}
		Panel panel = new Panel();
		panel.setEnabled(false);
		panel.setBackground(UIManager.getColor("scrollbar"));
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 47, 1177, 542);
		panel.add(scrollPane);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 1177, 34);
		panel.add(toolBar);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(RootAdminFrame.class.getResource("/icon/add_obj.gif")));
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(RootAdminFrame.class.getResource("/icon/edit.gif")));
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(RootAdminFrame.class.getResource("/icon/remove.gif")));
		toolBar.add(btnNewButton_2);
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
