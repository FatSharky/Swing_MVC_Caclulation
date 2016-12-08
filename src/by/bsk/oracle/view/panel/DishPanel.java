package by.bsk.oracle.view.panel;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.command.ShowJTable;
import by.bsk.oracle.domain.User;
import by.bsk.oracle.view.dialog.dish.AddDishDialog;
import by.bsk.oracle.view.dialog.dish.DeleteDishDialog;
import by.bsk.oracle.view.util.Field;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DishPanel extends JPanel {

	private static final Logger logger = LogManager.getLogger(DishPanel.class);
	private ResourceBundle resourceBundle = ResourceBundle.getBundle(Field.BUNDLE_NAME);
	private static DishPanel dishPanel;
	private FileInputStream fis;
	private ObjectInputStream inputStream;
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JMenuBar menuBar;
	private JLabel lInfo;
	private JButton bAddDish;
	private JButton bUpdateDish;
	private JButton bDeleteDish;
	private JScrollPane scrollPane;
	private DefaultTableModel tModel;

	/**
	 * Create the panel.
	 */
	private DishPanel() {
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

		lInfo = new JLabel("\u0411\u043B\u044E\u0434\u0430     ");
		lInfo.setFont(new Font("Tahoma", Font.BOLD, 16));
		menuBar.add(lInfo);

		bAddDish = new JButton("");
		bAddDish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddDishDialog addDishDialog = new AddDishDialog(table, tModel, user.getDivision().getIdDivision());
				addDishDialog.setLocationRelativeTo(dishPanel);
				addDishDialog.setVisible(true);
			}
		});
		bAddDish.setIcon(new ImageIcon(DishPanel.class.getResource("/icon/add_obj.gif")));
		menuBar.add(bAddDish);

		bUpdateDish = new JButton("");
		bUpdateDish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		bUpdateDish.setIcon(new ImageIcon(DishPanel.class.getResource("/icon/edit.gif")));
		menuBar.add(bUpdateDish);

		bDeleteDish = new JButton("");
		bDeleteDish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JDialog deleteDishDialog = new DeleteDishDialog(table, tModel);
				deleteDishDialog.setLocationRelativeTo(dishPanel);
				deleteDishDialog.setVisible(true);
			}
		});
		bDeleteDish.setIcon(new ImageIcon(DishPanel.class.getResource("/icon/remove.gif")));
		menuBar.add(bDeleteDish);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 1228, 448);
		add(scrollPane);

		tModel = new DefaultTableModel();
		tModel = ShowJTable.dishTable(user.getDivision().getIdDivision());
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(tModel);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
	}

	public static DishPanel getInstance() {
		if (dishPanel == null) {
			synchronized (DishPanel.class) {
				if (dishPanel == null) {
					dishPanel = new DishPanel();
				}
			}
		}
		return dishPanel;
	}
}
