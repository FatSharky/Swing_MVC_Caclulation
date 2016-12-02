package by.bsk.oracle.view.panel;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import by.bsk.oracle.command.DeletePCategoryCommand;
import by.bsk.oracle.command.ShowJTable;
import by.bsk.oracle.domain.User;
import by.bsk.oracle.view.form.AddPCategoryDialog;
import by.bsk.oracle.view.form.DeletePCategoryDialog;

import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PriceCategoryPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private FileInputStream fis;
	private ObjectInputStream inputStream;
	private static PriceCategoryPanel pricePanel;
	private JTable table;
	private DefaultTableModel tModel;

	private JButton bAddPCategory;
	private JButton bEditPCategoty;
	private JButton bDeletePCategory;
	private JLabel label;
	private JToolBar toolBar;
	private JScrollPane scrollPane;

	private PriceCategoryPanel() {
		readUser();
	}

	private void readUser() {
		try {
			fis = new FileInputStream("temp.txt");
			inputStream = new ObjectInputStream(fis);
			User user = (User) inputStream.readObject();
			createPanel(user);
			registerListeners(user);
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
		setBorder(new CompoundBorder());
		setLayout(null);

		toolBar = new JToolBar();
		toolBar.setBounds(12, 0, 1220, 45);
		add(toolBar);

		label = new JLabel(
				"\u041D\u0430\u0446\u0435\u043D\u043E\u0447\u043D\u044B\u0435 \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438     ");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		toolBar.add(label);

		bAddPCategory = new JButton("");
		bAddPCategory.setToolTipText(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043D\u0430\u0446\u0435\u043D\u043E\u0447\u043D\u0443\u044E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044E");

		bAddPCategory.setIcon(new ImageIcon(PriceCategoryPanel.class.getResource("/icon/add_obj.gif")));
		toolBar.add(bAddPCategory);

		bEditPCategoty = new JButton("");
		bEditPCategoty.setToolTipText(
				"\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C \u043D\u0430\u0446\u0435\u043D\u043E\u0447\u043D\u0443\u044E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044E");

		bEditPCategoty.setIcon(new ImageIcon(PriceCategoryPanel.class.getResource("/icon/edit.gif")));
		toolBar.add(bEditPCategoty);

		bDeletePCategory = new JButton("");
		bDeletePCategory.setToolTipText(
				"\u0423\u0434\u0430\u043B\u0438\u0442\u044C \u043D\u0430\u0446\u0435\u043D\u043E\u0447\u043D\u0443\u044E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044E");

		bDeletePCategory.setIcon(new ImageIcon(PriceCategoryPanel.class.getResource("/icon/remove.gif")));
		toolBar.add(bDeletePCategory);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 1222, 445);
		add(scrollPane);

		tModel = new DefaultTableModel();
		tModel = ShowJTable.priceCategoryTable(user.getDivision().getIdDivision());
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(tModel);

		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
	}

	private void registerListeners(User user) {

		bAddPCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddPCategoryDialog addCategory = new AddPCategoryDialog(table, tModel,
						user.getDivision().getIdDivision());
				addCategory.setLocationRelativeTo(pricePanel);
				addCategory.setVisible(true);
			}
		});

		bEditPCategoty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		bDeletePCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeletePCategoryDialog deletePCategoryDialog = new DeletePCategoryDialog(table, tModel);
				deletePCategoryDialog.setLocationRelativeTo(pricePanel);
				deletePCategoryDialog.setVisible(true);
			}
		});
	}

	public static PriceCategoryPanel getInstance() {
		if (pricePanel == null) {
			synchronized (PriceCategoryPanel.class) {
				if (pricePanel == null) {
					pricePanel = new PriceCategoryPanel();
				}
			}
		}
		return pricePanel;
	}
}
