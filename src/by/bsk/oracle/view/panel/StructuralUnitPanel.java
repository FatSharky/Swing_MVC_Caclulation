package by.bsk.oracle.view.panel;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import by.bsk.oracle.command.ShowJTable;
import by.bsk.oracle.domain.PriceCategory;
import by.bsk.oracle.domain.User;
import by.bsk.oracle.service.PriceCategorySevice;
import by.bsk.oracle.service.factory.ServiceFactory;
import by.bsk.oracle.view.dialog.AddStrucutalUnitDialog;
import by.bsk.oracle.view.dialog.DeleteStrucuralUnitDialog;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class StructuralUnitPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private FileInputStream fis;
	private ObjectInputStream inputStream;
	DefaultTableModel tModel;
	private StructuralUnitPanel panel;

	/**
	 * Create the panel.
	 */
	public StructuralUnitPanel() {
		try {
			fis = new FileInputStream("temp.txt");
			inputStream = new ObjectInputStream(fis);
			User user = (User) inputStream.readObject();
			setLayout(null);

			JMenuBar menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 1250, 26);
			add(menuBar);

			JButton bAddUnit = new JButton("");

			bAddUnit.setIcon(new ImageIcon(StructuralUnitPanel.class.getResource("/icon/add_obj.gif")));
			menuBar.add(bAddUnit);

			JButton bUpdateUnit = new JButton("");
			bUpdateUnit.setIcon(new ImageIcon(StructuralUnitPanel.class.getResource("/icon/edit.gif")));
			menuBar.add(bUpdateUnit);

			JButton bDeleteUnit = new JButton("");
			bDeleteUnit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DeleteStrucuralUnitDialog deleteStrucuralUnitDialog = new DeleteStrucuralUnitDialog(table, tModel);
					deleteStrucuralUnitDialog.setLocationRelativeTo(panel);
					deleteStrucuralUnitDialog.setVisible(true);
				}
			});
			bDeleteUnit.setIcon(new ImageIcon(StructuralUnitPanel.class.getResource("/icon/remove.gif")));
			menuBar.add(bDeleteUnit);
			List<PriceCategory> priceCategory = null;
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			PriceCategorySevice categorySevice = serviceFactory.getPriceCategory();
			priceCategory = categorySevice.selectCategoryByIdDivision(user.getDivision().getIdDivision());
			JComboBox<PriceCategory> comboBox = new JComboBox<PriceCategory>();
			String[] array = new String[priceCategory.size()];

			for (int i = 0; i < array.length; i++) {
				PriceCategory price = new PriceCategory();
				price.setIdDivision(priceCategory.get(i).getIdPrice());
				price.setName(priceCategory.get(i).getName());
				comboBox.addItem(price);
			}
			tModel = new DefaultTableModel();
			comboBox.setBounds(426, 39, 467, 22);
			add(comboBox);
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 73, 1226, 414);
			add(scrollPane);
			table = new JTable();
			scrollPane.setViewportView(table);

			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object item = comboBox.getSelectedItem();
					int value = ((PriceCategory) item).getIdDivision();
					tModel = ShowJTable.StructuralUnitTable(value);
					table.setModel(tModel);
					table.getColumnModel().getColumn(0).setMinWidth(0);
					table.getColumnModel().getColumn(0).setMaxWidth(0);
				}
			});

			bAddUnit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					AddStrucutalUnitDialog addUnit = new AddStrucutalUnitDialog(comboBox, table, tModel);
					addUnit.setLocationRelativeTo(panel);
					addUnit.setVisible(true);
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {

			}
		}
	}
}
