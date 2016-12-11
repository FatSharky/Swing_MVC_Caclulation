package by.bsk.oracle.view.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.domain.User;
import by.bsk.oracle.view.panel.DishPanel;
import by.bsk.oracle.view.panel.PriceCategoryPanel;
import by.bsk.oracle.view.panel.ProductPanel;
import by.bsk.oracle.view.panel.RecipePanel;
import by.bsk.oracle.view.panel.ShiftMasterPanel;
import by.bsk.oracle.view.panel.StructuralUnitPanel;
import by.bsk.oracle.view.util.ExceptionMessage;
import by.bsk.oracle.view.util.TextFile;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLayeredPane;

public class UserFrame extends JFrame {

	private static final Logger logger = LogManager.getLogger(UserFrame.class);

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

	private JButton btnStructuralUnit;
	private JButton btnRecipe;

	private JMenuItem mStructuralUnit;
	private JMenuItem mRecipe;
	private JLayeredPane layeredPane;
	private JPanel panel;
	private JButton btnDish;
	private JMenuItem mDish;
	private JButton btnShiftMasters;
	private JMenuItem mShiftMasters;
	private JButton btnProduct;
	private JMenuItem mProduct;

	private UserFrame() {
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

		mRecipe = new JMenuItem("\u0420\u0435\u0446\u0435\u043F\u0442\u044B");
		mDirectory.add(mRecipe);

		mDish = new JMenuItem(
				"\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u044F \u0431\u043B\u044E\u0434");
		mDirectory.add(mDish);

		mShiftMasters = new JMenuItem("\u041C\u0430\u0441\u0442\u0435\u0440\u0430 \u0441\u043C\u0435\u043D");
		mDirectory.add(mShiftMasters);

		mProduct = new JMenuItem("\u041F\u0440\u043E\u0434\u0443\u043A\u0442\u044B");
		mDirectory.add(mProduct);
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

		btnRecipe = new JButton("\u0420\u0435\u0446\u0435\u043F\u0442\u044B");

		toolBar.add(btnRecipe);

		btnDish = new JButton(
				"\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435 \u0431\u043B\u044E\u0434");

		toolBar.add(btnDish);

		btnShiftMasters = new JButton("\u041C\u0430\u0441\u0442\u0435\u0440\u0430 \u0441\u043C\u0435\u043D");
		toolBar.add(btnShiftMasters);

		btnProduct = new JButton("\u041F\u0440\u043E\u0434\u0443\u043A\u0442\u044B");

		toolBar.add(btnProduct);

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
		btnRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				RecipePanel productCategoryPanel = RecipePanel.getInstance();
				productCategoryPanel.setBounds(0, 0, 1239, 504);
				layeredPane.add(productCategoryPanel);
			}
		});
		mRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				RecipePanel productCategoryPanel = RecipePanel.getInstance();
				productCategoryPanel.setBounds(0, 0, 1239, 504);
				layeredPane.add(productCategoryPanel);
			}
		});
		btnDish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				DishPanel dishPanel = DishPanel.getInstance();
				dishPanel.setBounds(0, 0, 1239, 504);
				layeredPane.add(dishPanel);
			}
		});
		mDish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				DishPanel dishPanel = DishPanel.getInstance();
				dishPanel.setBounds(0, 0, 1239, 504);
				layeredPane.add(dishPanel);
			}
		});
		btnShiftMasters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				ShiftMasterPanel masterPanel = new ShiftMasterPanel();
				masterPanel.setBounds(0, 0, 1239, 504);
				layeredPane.add(masterPanel);
			}
		});
		mShiftMasters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				ShiftMasterPanel masterPanel = new ShiftMasterPanel();
				masterPanel.setBounds(0, 0, 1239, 504);
				layeredPane.add(masterPanel);
			}
		});
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				ProductPanel panel = new ProductPanel();
				panel.setBounds(0, 0, 1239, 504);
				layeredPane.add(panel);
			}
		});
		mProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				ProductPanel panel = new ProductPanel();
				panel.setBounds(0, 0, 1239, 504);
				layeredPane.add(panel);
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
