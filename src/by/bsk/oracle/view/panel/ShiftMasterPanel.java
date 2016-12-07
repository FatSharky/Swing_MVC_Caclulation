package by.bsk.oracle.view.panel;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.command.ShowJTable;
import by.bsk.oracle.domain.PriceCategory;
import by.bsk.oracle.domain.StructuralUnit;
import by.bsk.oracle.domain.User;
import by.bsk.oracle.view.combobox.PriceCategoryComboBox;
import by.bsk.oracle.view.combobox.StructuralUnitCombobox;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class ShiftMasterPanel extends JPanel {
	private static final Logger logger = LogManager.getLogger(DishPanel.class);
	private JTable table;
	private FileInputStream fis;
	private JComboBox<PriceCategory> priceCategotyCB;
	private ObjectInputStream inputStream;
	private StructuralUnitCombobox structUnitCB;
	private DefaultTableModel tModel;

	/**
	 * Create the panel.
	 */
	public ShiftMasterPanel() {
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

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1250, 26);
		add(menuBar);

		JLabel lInfo = new JLabel("\u041C\u0430\u0441\u0442\u0435\u0440\u0430 \u0441\u043C\u0435\u043D     ");
		lInfo.setFont(new Font("Tahoma", Font.BOLD, 16));
		menuBar.add(lInfo);

		JButton bAddMaster = new JButton("");
		bAddMaster.setIcon(new ImageIcon(ShiftMasterPanel.class.getResource("/icon/add_obj.gif")));
		menuBar.add(bAddMaster);

		JButton bUpdateMaster = new JButton("");
		bUpdateMaster.setIcon(new ImageIcon(ShiftMasterPanel.class.getResource("/icon/edit.gif")));
		menuBar.add(bUpdateMaster);

		JButton bDeleteMaster = new JButton("");
		bDeleteMaster.setIcon(new ImageIcon(ShiftMasterPanel.class.getResource("/icon/remove.gif")));
		menuBar.add(bDeleteMaster);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 96, 1228, 391);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		priceCategotyCB = new PriceCategoryComboBox(user.getDivision().getIdDivision());
		priceCategotyCB.setBounds(76, 57, 234, 22);
		add(priceCategotyCB);

		JLabel lInfoPriceCategories = new JLabel(
				"\u041D\u0430\u0446\u0435\u043D\u043E\u0447\u043D\u044B\u0435 \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438");
		lInfoPriceCategories.setBounds(76, 39, 152, 16);
		add(lInfoPriceCategories);

		JLabel lInfoStructuralUnits = new JLabel(
				"\u0421\u0442\u0440\u0443\u043A\u0442\u0443\u0440\u043D\u044B\u0435 \u043F\u043E\u0434\u0440\u0430\u0437\u0434\u0435\u043B\u0435\u043D\u0438\u044F");
		lInfoStructuralUnits.setBounds(443, 39, 181, 16);
		add(lInfoStructuralUnits);
		structUnitCB = new StructuralUnitCombobox();
		structUnitCB.setBounds(437, 57, 241, 22);
		add(structUnitCB);
		// Object item = priceCategotyCB.getSelectedItem();
		// int value = ((PriceCategory) item).getIdPrice();
		// structUnitCB = new StructuralUnitCombobox(value);
		priceCategotyCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				structUnitCB.removeAllItems();
				Object item = priceCategotyCB.getSelectedItem();
				int value = ((PriceCategory) item).getIdPrice();
				structUnitCB.createComboBox(value);
			}
		});

		structUnitCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object item = structUnitCB.getSelectedItem();
				if (!(item == null)) {
					Integer value = ((StructuralUnit) item).getIdUnit();
					tModel = ShowJTable.shiftMasterTable(value);
					table.setModel(tModel);
					table.getColumnModel().getColumn(0).setMinWidth(0);
					table.getColumnModel().getColumn(0).setMaxWidth(0);
				}
			}
		});

	}
}
