package by.bsk.oracle.view.panel;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ResourceBundle;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.command.ShowJTable;
import by.bsk.oracle.domain.User;
import by.bsk.oracle.view.util.Field;
import by.bsk.oracle.view.util.GifPlace;
import by.bsk.oracle.view.util.ReturnIcon;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;

public class RecipePanel extends JPanel {

	private static final Logger logger = LogManager.getLogger(RecipePanel.class);
	private ResourceBundle resourceBundle = ResourceBundle.getBundle(Field.BUNDLE_NAME);
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tModel;
	private FileInputStream fis;
	private ObjectInputStream inputStream;
	private static RecipePanel productPanel;
	private JMenuBar menuBar;
	private JLabel label;
	private JButton bAddProduct;
	private JButton bUpdateProduct;
	private JButton bDeleteButton;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	private RecipePanel() {
		readUser();
	}

	private void readUser() {

		try {
			fis = new FileInputStream("temp.txt");
			inputStream = new ObjectInputStream(fis);
			User user = (User) inputStream.readObject();
			createPanel(user);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Не могу найти файл конфигурации");
			logger.error("Невозможно найти файл конфигурации");
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Не могу закрыть файл конфигурации");
				logger.error("Невозможно закрыть файл конфигурации");
			}
		}
	}

	private void createPanel(User user) {
		setLayout(null);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1250, 26);
		add(menuBar);

		label = new JLabel(resourceBundle.getString(Field.RECIPE_PANEL_LABEL) + "  ");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		menuBar.add(label);

		bAddProduct = createButton(menuBar, resourceBundle.getString(Field.RECIPE_PANEL_ADD_INFO), GifPlace.GIF_ADD);
		// bAddProduct = new JButton("");
		// bAddProduct.setToolTipText("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C
		// \u043D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435
		// \u0433\u0440\u0443\u043F\u043F\u044B
		// \u0438\u0437\u0434\u0435\u043B\u0438\u0439");
		// bAddProduct.setIcon(new
		// ImageIcon(ProductCategoryPanel.class.getResource("/icon/add_obj.gif")));
		// menuBar.add(bAddProduct);

		bUpdateProduct = createButton(menuBar, resourceBundle.getString(Field.RECIPE_PANEL_EDIT_INFO),
				GifPlace.GIF_UPDATE);
		// bUpdateProduct = new JButton("");
		// bUpdateProduct.setToolTipText(
		// "\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C
		// \u043D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435
		// \u0433\u0440\u0443\u043F\u043F\u044B
		// \u0438\u0437\u0434\u0435\u043B\u0438\u0439");
		// bUpdateProduct.setIcon(new
		// ImageIcon(ProductCategoryPanel.class.getResource("/icon/edit.gif")));
		// menuBar.add(bUpdateProduct);

		bDeleteButton = createButton(menuBar, resourceBundle.getString(Field.RECIPE_PANEL_DELETE_INFO),
				GifPlace.GIF_DELETE);
		// bDeleteButton = new JButton("");
		// bDeleteButton.setToolTipText(
		// "\u0423\u0434\u0430\u043B\u0438\u0442\u044C
		// \u043D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435
		// \u0433\u0440\u0443\u043F\u043F\u043F\u044B
		// \u0438\u0437\u0434\u0435\u043B\u0438\u0439");
		// bDeleteButton.setIcon(new
		// ImageIcon(ProductCategoryPanel.class.getResource("/icon/remove.gif")));
		// menuBar.add(bDeleteButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 1228, 448);
		add(scrollPane);

		tModel = new DefaultTableModel();
		tModel = ShowJTable.productCategoryTable(user.getDivision().getIdDivision());
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(tModel);

		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
	}

	// ADDBUTTONS
	private JButton createButton(JMenuBar jMenuBar, String info, String icon) {
		JButton jButton = new JButton();
		jButton.setToolTipText(info);
		jButton.setIcon(ReturnIcon.getIcon(getClass(), icon));
		jMenuBar.add(jButton);
		return jButton;

	}

	public static RecipePanel getInstance() {
		if (productPanel == null) {
			synchronized (RecipePanel.class) {
				if (productPanel == null) {
					productPanel = new RecipePanel();
				}
			}
		}
		return productPanel;
	}

}
