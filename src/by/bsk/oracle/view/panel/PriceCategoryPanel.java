package by.bsk.oracle.view.panel;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.command.ShowJTable;
import by.bsk.oracle.domain.User;
import by.bsk.oracle.view.dialog.price.AddPCategoryDialog;
import by.bsk.oracle.view.dialog.price.DeletePCategoryDialog;
import by.bsk.oracle.view.util.ExceptionMessage;
import by.bsk.oracle.view.util.Field;
import by.bsk.oracle.view.util.GifPlace;
import by.bsk.oracle.view.util.ReturnIcon;
import by.bsk.oracle.view.util.TextFile;

import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

public class PriceCategoryPanel extends JPanel {

	private static final Logger logger = LogManager.getLogger(PriceCategoryPanel.class);
	private ResourceBundle resourceBundle = ResourceBundle.getBundle(Field.BUNDLE_NAME);
	private static final long serialVersionUID = 1L;
	private static PriceCategoryPanel pricePanel;

	private FileInputStream fis;
	private ObjectInputStream inputStream;

	private JLabel lName;

	private JTable table;
	private DefaultTableModel tModel;

	private JButton bAddPCategory;
	private JButton bEditPCategoty;
	private JButton bDeletePCategory;

	private JScrollPane scrollPane;
	private JMenuBar menuBar;

	private PriceCategoryPanel() {
		readUser();
	}

	private void readUser() {
		try {
			fis = new FileInputStream(TextFile.SESSION_FILE);
			inputStream = new ObjectInputStream(fis);
			User user = (User) inputStream.readObject();
			configure();
			createPanel(user);
			registerListeners(user);
			} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, ExceptionMessage.FILE_NOT_FOUND);
			logger.error(ExceptionMessage.FILE_NOT_FOUND);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ExceptionMessage.OPEN_IO);
			logger.error(ExceptionMessage.OPEN_IO);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, ExceptionMessage.CLASS_NOT_FOUND);
			logger.error(ExceptionMessage.CLASS_NOT_FOUND);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, ExceptionMessage.CLOSE_IO);
				logger.error(ExceptionMessage.CLOSE_IO);
			}
		}
	}

	private void configure() {
		setBorder(new CompoundBorder());
		setLayout(null);
	}

	private void createPanel(User user) {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 1224, 447);
		add(scrollPane);

		tModel = new DefaultTableModel();
		tModel = ShowJTable.priceCategoryTable(user.getDivision().getIdDivision());
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(tModel);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1250, 27);
		add(menuBar);

		lName = new JLabel(resourceBundle.getString(Field.PRICE_PANEL_LABEL) + "   ");
		lName.setFont(new Font("Tahoma", Font.BOLD, 16));
		menuBar.add(lName);

		bAddPCategory = createButton(menuBar, resourceBundle.getString(Field.PRICE_PANEL_ADD_INFO), GifPlace.GIF_ADD);
		bEditPCategoty = createButton(menuBar, resourceBundle.getString(Field.PRICE_PANEL_EDIT_INFO),
				GifPlace.GIF_UPDATE);
		bDeletePCategory = createButton(menuBar, resourceBundle.getString(Field.PRICE_PANEL_DELETE_INFO),
				GifPlace.GIF_DELETE);
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

	// ADDBUTTONS
	private JButton createButton(JMenuBar jMenuBar, String info, String icon) {
		JButton jButton = new JButton();
		jButton.setToolTipText(info);
		jButton.setIcon(ReturnIcon.getIcon(getClass(), icon));
		jMenuBar.add(jButton);
		return jButton;

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
