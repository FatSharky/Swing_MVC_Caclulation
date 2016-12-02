package by.bsk.oracle.view.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import by.bsk.oracle.domain.User;
import by.bsk.oracle.view.panel.PriceCategoryPanel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static UserFrame userFrame;

	private FileInputStream fis;
	private ObjectInputStream inputStream;

	private JPanel contentPaneFist;

	private JMenuBar menuBar;
	private JMenu mDirectory;
	private JMenuItem mPriceCategory;

	private JToolBar toolBar;

	private JButton btnNewButton;

	private PriceCategoryPanel pPrice;

	private UserFrame() {
		readUser();
	}

	private void readUser() {
		try {
			fis = new FileInputStream("temp.txt");
			inputStream = new ObjectInputStream(fis);
			User user = (User) inputStream.readObject();
			configure(user);
			createForm(user);
			registerListeners(user);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {

			}
		}
	}

	private void configure(User user) {
		setTitle(user.getDivision().getName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1279, 628);
	}

	private void createForm(User user) {

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mDirectory = new JMenu("\u0421\u043F\u0440\u0430\u0432\u043E\u0447\u043D\u0438\u043A");
		menuBar.add(mDirectory);

		mPriceCategory = new JMenuItem(
				"\u041D\u0430\u0446\u0435\u043D\u043E\u0447\u043D\u044B\u0435 \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438");
		mDirectory.add(mPriceCategory);
		contentPaneFist = new JPanel();
		contentPaneFist.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneFist);
		contentPaneFist.setLayout(null);

		pPrice = PriceCategoryPanel.getInstance();
		pPrice.setBounds(10, 42, 1241, 503);
		contentPaneFist.add(pPrice);
		pPrice.setVisible(false);

		toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 1261, 36);
		contentPaneFist.add(toolBar);

		// pPrice.setVisible(false);
		btnNewButton = new JButton(
				"\u041D\u0430\u0446\u0435\u043D\u043E\u0447\u043D\u044B\u0435 \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438");

		toolBar.add(btnNewButton);

	}

	private void registerListeners(User user) {
		mPriceCategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pPrice.setVisible(true);
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				pPrice.setVisible(true);
			}
		});
	}

	public static UserFrame getInstance() {
		if (userFrame == null) {
			synchronized (UserFrame.class) {
				if (userFrame == null) {
					userFrame = new UserFrame();
				}
			}
		}
		return userFrame;
	}
}
