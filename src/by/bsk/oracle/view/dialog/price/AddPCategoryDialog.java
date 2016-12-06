package by.bsk.oracle.view.dialog.price;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import by.bsk.oracle.command.AddPCategoryCommand;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;

public class AddPCategoryDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable jTable;
	private DefaultTableModel jTableModel;
	private int idDivision;

	public AddPCategoryDialog(JTable jTable, DefaultTableModel jTableModel, int idDivision) {
		this.jTable = jTable;
		this.jTableModel = jTableModel;
		this.idDivision = idDivision;
		createDialog();
	}

	public void createDialog() {
		setTitle(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043D\u0430\u0446\u0435\u043D\u043E\u0447\u043D\u0443\u044E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044E");
		setBounds(100, 100, 447, 247);
		getContentPane().setLayout(null);

		JLabel lblName = new JLabel("\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435: ");
		lblName.setBounds(63, 65, 95, 25);
		getContentPane().add(lblName);

		textField = new JTextField();
		textField.setBounds(183, 66, 147, 22);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		ActionListener addPrice = new AddPCategoryCommand(textField, lblName, jTable, jTableModel, idDivision);
		btnNewButton.addActionListener(addPrice);
		btnNewButton.setBounds(149, 140, 97, 25);
		getContentPane().add(btnNewButton);
	}
}
