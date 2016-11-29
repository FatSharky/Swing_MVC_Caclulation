package by.bsk.oracle.view.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import by.bsk.oracle.domain.Division;
import by.bsk.oracle.domain.User;
import by.bsk.oracle.service.DivisionService;
import by.bsk.oracle.service.exception.ServiceException;
import by.bsk.oracle.service.factory.ServiceFactory;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import javax.swing.JMenuItem;

public class UserFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPaneFist;

	/**
	 * Create the frame.
	 */
	public UserFrame() {
		try {
			FileInputStream fis = new FileInputStream("temp.txt");
			ObjectInputStream inputStream = new ObjectInputStream(fis);
			User user = (User) inputStream.readObject();
			setTitle(user.getDivision().getName());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 770, 352);

			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);

			JMenu mnNewMenu = new JMenu("\u0420\u0430\u0431\u043E\u0442\u0430");
			menuBar.add(mnNewMenu);

			JMenuItem mntmNewMenuItem = new JMenuItem(
					"\u041D\u0430\u0446\u0435\u043D\u043E\u0447\u043D\u044B\u0435 \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438");
			mnNewMenu.add(mntmNewMenuItem);

			JMenuItem menuItem = new JMenuItem("");
			mnNewMenu.add(menuItem);

			JMenu menu = new JMenu(
					"\u041D\u0430\u0446\u0435\u043D\u043E\u0447\u043D\u044B\u0435 \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438");
			menuBar.add(menu);
			List<Division> divisions;
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			DivisionService divisionService = serviceFactory.getDivisionService();
			try {
				divisions = divisionService.selectAllDivision();
				for (Division division : divisions) {
					JMenuItem jMenuItime = new JMenuItem(division.getName());
					menu.add(jMenuItime);
				}
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JMenuItem menuItem_2 = new JMenuItem("");
			menu.add(menuItem_2);

			JMenu mnNewMenu_1 = new JMenu(
					"                                                                                            ");
			menuBar.add(mnNewMenu_1);

			JMenu mUsername = new JMenu(user.getLogin());
			menuBar.add(mUsername);
			contentPaneFist = new JPanel();
			contentPaneFist.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPaneFist);
			contentPaneFist.setLayout(null);

			JPanel panelSecond = new JPanel();
			panelSecond.setLayout(null);
			panelSecond.setBorder(new EmptyBorder(5, 5, 5, 5));
			panelSecond.setBounds(0, 0, 521, 279);
			contentPaneFist.add(panelSecond);

			JPanel panelFist = new JPanel();
			panelFist.setBounds(0, 0, 521, 279);
			contentPaneFist.add(panelFist);
			panelFist.setLayout(null);
			panelFist.setBorder(new EmptyBorder(5, 5, 5, 5));
			inputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
