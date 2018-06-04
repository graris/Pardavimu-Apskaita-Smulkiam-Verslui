package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import funkcionalumas.ArTinkamasTipas;
import funkcionalumas.DuomenuBaze.DBduomenys;
import funkcionalumas.DuomenuBaze.SQLuzklausos;

public class IrasoPridejimoDialog extends JDialog{

	private DefaultTableModel modelis;
	private JTable miniLentele;
	private JScrollPane scrollpane;
	private JButton mygtukas;
	
	private String sqlTableName;
	
	private IrasuPerziurosPanele irasuPerziurosPanele;
	
	public IrasoPridejimoDialog(IrasuPerziurosPanele irasuPerziurosPanele, String sqlTableName) {
	
		this.irasuPerziurosPanele = irasuPerziurosPanele;
		this.sqlTableName = sqlTableName;
		
		init();	
	}
	
	public JButton getMygtukas() {
		return mygtukas;
	}

	public DefaultTableModel getModelis() {
		return modelis;
	}

	public JTable getMiniLentele() {
		return miniLentele;
	}

	private void init() {
		
		modelis = new DefaultTableModel();
		miniLentele = new JTable(modelis);
		scrollpane = new JScrollPane(miniLentele);
	
		scrollpane.setPreferredSize(new Dimension(400, 2*miniLentele.getRowHeight(0)+10));
		
		mygtukas = new JButton("Pridëti");
		
		Object complexMsg[] = {scrollpane, mygtukas};
		
		JOptionPane optionPane = new JOptionPane(complexMsg, JOptionPane.INFORMATION_MESSAGE, 
				JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
	
		optionPane.setMessageType(-1);
		
		setTitle("Papildymas duomenimis");
		
		setContentPane(optionPane);
	
		pack();	
		
		mygtukas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(new ArTinkamasTipas(modelis.getValueAt(0, 0).toString())
						.yraTeigiamasSveikasSkaicius()) {		
					
					if (sqlTableName.equals("Pirkejas")) {
					
						new DBduomenys(irasuPerziurosPanele.getSt(), irasuPerziurosPanele.getDuomenuIrasai())
							.vykdykQuery(new SQLuzklausos().insertPirkejoDuomenys(
								modelis.getValueAt(0, 0).toString(),
								modelis.getValueAt(0, 1).toString(),
								modelis.getValueAt(0, 2).toString(),
								modelis.getValueAt(0, 3).toString(),
								modelis.getValueAt(0, 4).toString()
							));
					}
					
					if (sqlTableName.equals("Tiekejas")) {
						
						new DBduomenys(irasuPerziurosPanele.getSt(), irasuPerziurosPanele.getDuomenuIrasai())
							.vykdykQuery(new SQLuzklausos().insertTiekejoDuomenys(
								modelis.getValueAt(0, 0).toString(),
								modelis.getValueAt(0, 1).toString(),
								modelis.getValueAt(0, 2).toString(),
								modelis.getValueAt(0, 3).toString(),
								modelis.getValueAt(0, 4).toString()
							));
					}					
					irasuPerziurosPanele.refreshTable();
				}
			}
			
			
		});
		
		prepareTable();
		
		this.setVisible(true);		
		
	}
	
	
	private void prepareTable() {
		
		modelis.setColumnCount(0);
		
		Object[] stulp = new Object[1];
		
		stulp[0] = "";

		for (int i=0;i<irasuPerziurosPanele.getDuomenuIrasai().get(0).size(); i++) {
			
			modelis.addColumn(irasuPerziurosPanele.getDuomenuIrasai().get(0).get(i), stulp);
		}
	}
}
