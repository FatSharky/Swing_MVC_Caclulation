package by.bsk.oracle.view.form;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;

public class DeleteUserDialog extends JDialog {

	private JTable jTable = new JTable();
	private DefaultTableModel tModel = new DefaultTableModel();

	private static final long serialVersionUID = 1L;

	public DeleteUserDialog(JTable jTable, DefaultTableModel tModel) {
		this.jTable = jTable;
		this.tModel = tModel;
		initComponents();
	}

	private void initComponents() {
		int i = jTable.getSelectedRow();
		String login = tModel.getValueAt(i, 1).toString();
		setTitle(
				"\u0423\u0434\u0430\u043B\u0435\u043D\u0438\u0435 \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
		setBounds(100, 100, 437, 222);
		getContentPane().setLayout(null);
		{
			JButton btnNewButton_1 = new JButton("\u041E\u0442\u043C\u0435\u043D\u0438\u0442\u044C");
			btnNewButton_1.setBounds(219, 106, 109, 25);
			getContentPane().add(btnNewButton_1);
		}
		{
			JLabel lblNewLabel = new JLabel(
					"\u0412\u044B \u0442\u043E\u0447\u043D\u043E \u0445\u043E\u0442\u0438\u0442\u0435 \u0443\u0434\u0430\u043B\u0438\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F "
							+ login + " ?");
			lblNewLabel.setBounds(53, 33, 318, 60);
			getContentPane().add(lblNewLabel);
		}
		{
			JButton button = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
			button.setIcon(new ImageIcon(DeleteUserDialog.class.getResource("/icon/remove.gif")));
			button.setBounds(63, 106, 109, 25);
			getContentPane().add(button);
		}
	}
}
