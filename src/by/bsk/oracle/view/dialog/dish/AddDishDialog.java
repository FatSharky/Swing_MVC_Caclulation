package by.bsk.oracle.view.dialog.dish;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import by.bsk.oracle.command.AddDishCommand;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;

public class AddDishDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField tName;
	private JTable jTable;
	private DefaultTableModel jTableModel;
	private int idDivision;

	public AddDishDialog(JTable jTable, DefaultTableModel jTableModel, int idDivision) {
		this.jTable = jTable;
		this.jTableModel = jTableModel;
		this.idDivision = idDivision;
		createDialog();
	}

	private void createDialog() {
		setTitle("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u0431\u043B\u044E\u0434\u043E");
		setBounds(100, 100, 447, 247);
		getContentPane().setLayout(null);

		JLabel lblName = new JLabel("\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435: ");
		lblName.setBounds(63, 65, 95, 25);
		getContentPane().add(lblName);

		tName = new JTextField();
		tName.setBounds(183, 66, 162, 22);
		getContentPane().add(tName);
		tName.setColumns(10);

		JButton bAddDish = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		bAddDish.setBounds(149, 140, 97, 25);
		getContentPane().add(bAddDish);

		JLabel lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(63, 99, 282, 16);
		getContentPane().add(lblInfo);
		ActionListener addDish = new AddDishCommand(tName, lblInfo, jTable, jTableModel, idDivision);
		bAddDish.addActionListener(addDish);
		bAddDish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}

}
