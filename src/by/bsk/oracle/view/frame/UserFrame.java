package by.bsk.oracle.view.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import by.bsk.oracle.domain.User;
import by.bsk.oracle.view.panel.PriceCategoryPanel;
import by.bsk.oracle.view.panel.ProductCategoryPanel;
import by.bsk.oracle.view.panel.StructuralUnitPanel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLayeredPane;

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

	// private PriceCategoryPanel pPrice;
	// private StructuralUnitPanel pUnit;
	private JButton btnStructuralUnit;
	private JButton btnProductCategory;

	private JMenuItem mStructuralUnit;
	private JMenuItem mProductCategory;
	// private ProductCategoryPanel pProduct;
	private JLayeredPane layeredPane;
	private JPanel panel;

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

		mStructuralUnit = new JMenuItem(
				"\u0421\u0442\u0440\u0443\u043A\u0442\u0443\u0440\u043D\u044B\u0435 \u043F\u043E\u0434\u0440\u0430\u0437\u0434\u0435\u043B\u043D\u0435\u0438\u044F");
		mDirectory.add(mStructuralUnit);

		mProductCategory = new JMenuItem(
				"\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u044F \u0433\u0440\u0443\u043F\u043F \u0438\u0437\u0434\u0435\u043B\u0438\u0439");
		mDirectory.add(mProductCategory);
		contentPaneFist = new JPanel();
		contentPaneFist.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneFist);
		contentPaneFist.setLayout(null);

		// pPrice = PriceCategoryPanel.getInstance();
		// pPrice.setBounds(10, 42, 1241, 503);
		// contentPaneFist.add(pPrice);
		// pPrice.setVisible(false);

		// pUnit = new StructuralUnitPanel();
		// pUnit.setBounds(10, 515, 1241, 30);
		// contentPaneFist.add(pUnit);
		// pUnit.setVisible(false);

		// pProduct = new ProductCategoryPanel();
		// pProduct.setBounds(10, 515, 1241, 30);
		// contentPaneFist.add(pProduct);
		// pProduct.setVisible(false);

		toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 1261, 36);
		contentPaneFist.add(toolBar);

		// pPrice.setVisible(false);
		btnNewButton = new JButton(
				"\u041D\u0430\u0446\u0435\u043D\u043E\u0447\u043D\u044B\u0435 \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438");

		toolBar.add(btnNewButton);

		btnStructuralUnit = new JButton(
				"\u0421\u0442\u0440\u0443\u043A\u0442\u0443\u0440\u043D\u044B\u0435 \u043F\u043E\u0434\u0440\u0430\u0437\u0434\u0435\u043B\u0435\u043D\u0438\u044F");

		toolBar.add(btnStructuralUnit);

		btnProductCategory = new JButton(
				"\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435 \u0433\u0440\u0443\u043F\u043F \u0438\u0437\u0434\u0435\u043B\u0438\u0439");

		toolBar.add(btnProductCategory);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 38, 1239, 504);
		contentPaneFist.add(layeredPane);

		panel = new JPanel();
		panel.setBounds(0, 0, 1239, 504);
		layeredPane.add(panel);

	}

	private void registerListeners(User user) {
		mPriceCategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				PriceCategoryPanel categoryPanel = PriceCategoryPanel.getInstance();
				categoryPanel.setBounds(0, 0, 1239, 504);
				layeredPane.add(categoryPanel);
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				PriceCategoryPanel categoryPanel = PriceCategoryPanel.getInstance();
				categoryPanel.setBounds(0, 0, 1239, 504);
				layeredPane.add(categoryPanel);
			}
		});
		btnStructuralUnit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				StructuralUnitPanel structuralUnitPanel = new StructuralUnitPanel();
				structuralUnitPanel.setBounds(0, 0, 1239, 504);
				layeredPane.add(structuralUnitPanel);
			}
		});
		mStructuralUnit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				StructuralUnitPanel structuralUnitPanel = new StructuralUnitPanel();
				structuralUnitPanel.setBounds(0, 0, 1239, 504);
				layeredPane.add(structuralUnitPanel);
			}
		});
		btnProductCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				ProductCategoryPanel productCategoryPanel = ProductCategoryPanel.getInstance();
				productCategoryPanel.setBounds(0, 0, 1239, 504);
				layeredPane.add(productCategoryPanel);
			}
		});
		mProductCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				ProductCategoryPanel productCategoryPanel = ProductCategoryPanel.getInstance();
				productCategoryPanel.setBounds(0, 0, 1239, 504);
				layeredPane.add(productCategoryPanel);
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
