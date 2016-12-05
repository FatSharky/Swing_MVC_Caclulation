package by.bsk.oracle.view.panel;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JButton;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import by.bsk.oracle.command.ShowJTable;
import by.bsk.oracle.domain.User;

import javax.swing.JScrollPane;

public class ProductCategoryPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tModel;
	private FileInputStream fis;
	private ObjectInputStream inputStream;

	/**
	 * Create the panel.
	 */
	public ProductCategoryPanel() {
		readUser();
	}

	private void readUser() {

		try {
			fis = new FileInputStream("temp.txt");
			inputStream = new ObjectInputStream(fis);
			User user = (User) inputStream.readObject();
			createPanel(user);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {

			}
		}
	}

	private void createPanel(User user) {
		setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1250, 26);
		add(menuBar);

		JButton bAddProduct = new JButton("");
		bAddProduct.setIcon(new ImageIcon(ProductCategoryPanel.class.getResource("/icon/add_obj.gif")));
		menuBar.add(bAddProduct);

		JButton bUpdateProduct = new JButton("");
		bUpdateProduct.setIcon(new ImageIcon(ProductCategoryPanel.class.getResource("/icon/edit.gif")));
		menuBar.add(bUpdateProduct);

		JButton bDeleteButton = new JButton("");
		bDeleteButton.setIcon(new ImageIcon(ProductCategoryPanel.class.getResource("/icon/remove.gif")));
		menuBar.add(bDeleteButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 1228, 448);
		add(scrollPane);
		tModel = new DefaultTableModel();
		tModel = ShowJTable.productCategoryTable(user.getDivision().getIdDivision());
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(tModel);
	}

}
