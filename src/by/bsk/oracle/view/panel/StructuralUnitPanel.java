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
import by.bsk.oracle.view.combobox.PriceCategoryComboBox;
import by.bsk.oracle.view.dialog.unit.AddStrucutalUnitDialog;
import by.bsk.oracle.view.dialog.unit.DeleteStrucuralUnitDialog;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;

public class StructuralUnitPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private FileInputStream fis;
	private ObjectInputStream inputStream;
	private DefaultTableModel tModel;
	private StructuralUnitPanel panel;
	private JComboBox<PriceCategory> comboBox;

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

			JLabel lblC = new JLabel(
					"C\u0442\u0440\u0443\u043A\u0442\u0443\u0440\u043D\u044B\u0435 \u043F\u043E\u0434\u0440\u0430\u0437\u0434\u0435\u043B\u0435\u043D\u0438\u044F ");
			lblC.setFont(new Font("Tahoma", Font.BOLD, 16));
			menuBar.add(lblC);

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
			comboBox = new PriceCategoryComboBox(user.getDivision().getIdDivision());
			comboBox.setBounds(426, 39, 467, 22);
			add(comboBox);
			tModel = new DefaultTableModel();
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 73, 1226, 414);
			add(scrollPane);
			table = new JTable();
			scrollPane.setViewportView(table);

			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object item = comboBox.getSelectedItem();
					int value = ((PriceCategory) item).getIdPrice();
					tModel = ShowJTable.structuralUnitTable(value);
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
