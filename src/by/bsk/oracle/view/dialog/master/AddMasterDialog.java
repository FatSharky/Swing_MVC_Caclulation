package by.bsk.oracle.view.dialog.master;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import by.bsk.oracle.command.AddPCategoryCommand;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;

public class AddMasterDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable jTable;
	private DefaultTableModel jTableModel;
	private int idDivision;

	public AddMasterDialog(JTable jTable, DefaultTableModel jTableModel, int idDivision) {
		this.jTable = jTable;
		this.jTableModel = jTableModel;
		this.idDivision = idDivision;
		createDialog();
	}

	public void createDialog() {
		setTitle(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043C\u0430\u0441\u0442\u0435\u0440\u0430 \u0441\u043C\u0435\u043D\u044B");
		setBounds(100, 100, 447, 247);
		getContentPane().setLayout(null);

		JLabel lblName = new JLabel("\u0424\u0418\u041E: ");
		lblName.setBounds(98, 65, 53, 25);
		getContentPane().add(lblName);

		textField = new JTextField();
		textField.setBounds(163, 66, 169, 22);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		ActionListener addPrice = new AddPCategoryCommand(textField, lblName, jTable, jTableModel, idDivision);
		btnNewButton.addActionListener(addPrice);
		btnNewButton.setBounds(149, 140, 97, 25);
		getContentPane().add(btnNewButton);
	}
}
