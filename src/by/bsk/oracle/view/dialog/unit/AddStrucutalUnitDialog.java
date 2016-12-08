package by.bsk.oracle.view.dialog.unit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import by.bsk.oracle.domain.PriceCategory;
import by.bsk.oracle.service.StructuralUnitService;
import by.bsk.oracle.service.factory.ServiceFactory;

import javax.swing.JLabel;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddStrucutalUnitDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField tName;
	private JTextField tMarkUp;
	private JTextField tTax;
	private JTextField tFare;
	private JTextField tDiscount;
	private JTextField tAllowance;

	private JComboBox<PriceCategory> jComboBox;
	private JTable jTable;
	private DefaultTableModel jTableModel;

	/**
	 * Create the dialog.
	 */
	public AddStrucutalUnitDialog(JComboBox<PriceCategory> jComboBox, JTable jTable, DefaultTableModel jTableModel) {
		this.jComboBox = jComboBox;
		this.jTable = jTable;
		this.jTableModel = jTableModel;
		createFrame();

	}

	private void createFrame() {
		setTitle(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u0441\u0442\u0440\u0443\u043A\u0442\u0443\u0440\u043D\u043E\u0435 \u043F\u043E\u0434\u0440\u0430\u0437\u0434\u0435\u043B\u0435\u043D\u0438\u0435");
		setBounds(100, 100, 399, 349);
		getContentPane().setLayout(null);

		tName = new JTextField();
		tName.setBounds(118, 13, 156, 22);
		getContentPane().add(tName);
		tName.setColumns(10);

		JLabel lblName = new JLabel("\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435:");
		lblName.setBounds(10, 16, 106, 16);
		getContentPane().add(lblName);

		JLabel lblMarkUp = new JLabel("\u041D\u0430\u0446\u0435\u043D\u043A\u0430:");
		lblMarkUp.setBounds(10, 45, 56, 16);
		getContentPane().add(lblMarkUp);

		tMarkUp = new JTextField();
		tMarkUp.setBounds(118, 42, 156, 22);
		getContentPane().add(tMarkUp);
		tMarkUp.setColumns(10);

		JLabel lblTax = new JLabel("\u041D\u0430\u043B\u043E\u0433\u0438:");
		lblTax.setBounds(10, 80, 56, 16);
		getContentPane().add(lblTax);

		tTax = new JTextField();
		tTax.setBounds(118, 77, 156, 22);
		getContentPane().add(tTax);
		tTax.setColumns(10);

		tFare = new JTextField();
		tFare.setBounds(118, 112, 156, 22);
		getContentPane().add(tFare);
		tFare.setColumns(10);

		JLabel lblFare = new JLabel("\u0422\u0440. \u0440\u0430\u0441\u0445\u043E\u0434\u044B:");
		lblFare.setBounds(10, 118, 84, 16);
		getContentPane().add(lblFare);

		JLabel lblDiscount = new JLabel("C\u043A\u0438\u0434\u043A\u0430:");
		lblDiscount.setBounds(10, 153, 56, 16);
		getContentPane().add(lblDiscount);

		tDiscount = new JTextField();
		tDiscount.setBounds(118, 147, 156, 22);
		getContentPane().add(tDiscount);
		tDiscount.setColumns(10);

		JLabel lblAllowance = new JLabel("\u041D\u0430\u0434\u0431\u0430\u0432\u043A\u0430:");
		lblAllowance.setBounds(10, 185, 74, 16);
		getContentPane().add(lblAllowance);

		tAllowance = new JTextField();
		tAllowance.setBounds(118, 182, 156, 22);
		getContentPane().add(tAllowance);
		tAllowance.setColumns(10);

		JButton btnAddUnit = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		btnAddUnit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object item = jComboBox.getSelectedItem();
				int value = ((PriceCategory) item).getIdPrice();
				String name = tName.getText();
				String markUp = tMarkUp.getText();
				String tax = tTax.getText();
				String fare = tFare.getText();
				String discount = tDiscount.getText();
				String allowance = tAllowance.getText();
				ServiceFactory serviceFactory = ServiceFactory.getInstance();
				StructuralUnitService structuralUnitService = serviceFactory.getStructuralUnit();
				try {
					int id = 0;
					structuralUnitService.addStructuralUnit(name, markUp, tax, fare, discount, allowance,
							String.valueOf(value));
					Object[] data = { id, name, markUp, tax, fare, discount, allowance };
					jTableModel.insertRow(jTable.getRowCount(), data);
				} catch (Exception e) {
				}
			}
		});
		btnAddUnit.setBounds(107, 241, 97, 25);
		getContentPane().add(btnAddUnit);
	}
}
