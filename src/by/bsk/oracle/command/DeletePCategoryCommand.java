package by.bsk.oracle.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import by.bsk.oracle.service.PriceCategorySevice;
import by.bsk.oracle.service.exception.ServiceException;
import by.bsk.oracle.service.factory.ServiceFactory;

public class DeletePCategoryCommand implements ActionListener {

	private DefaultTableModel tModel = new DefaultTableModel();
	private JTable jTable = new JTable();

	public DeletePCategoryCommand(JTable jTable, DefaultTableModel tModel) {
		this.jTable = jTable;
		this.tModel = tModel;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int i = jTable.getSelectedRow();

		String id = tModel.getValueAt(i, 0).toString();

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		PriceCategorySevice priceService = serviceFactory.getPriceCategory();

		try {
			priceService.deleteCategory(id);
			tModel.removeRow(i);
			jTable.setModel(tModel);
			jTable.getColumnModel().getColumn(0).setMinWidth(0);
			jTable.getColumnModel().getColumn(0).setMaxWidth(0);
		} catch (ServiceException e1) {
			// TODO: handle exception
		}

	}

}
