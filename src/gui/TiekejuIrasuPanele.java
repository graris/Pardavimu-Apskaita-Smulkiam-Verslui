package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import atributai.Atributai;
import atributai.PirkejuAtributai;
import atributai.TiekejuAtributai;
import funkcionalumas.DuomenuBaze.SQLuzklausos;
import funkcionalumas.DuomenuBaze.DBduomenys;

public class TiekejuIrasuPanele extends ImoniuIrasuPanele{

	public TiekejuIrasuPanele(Statement st) {
		super(st, new TiekejuAtributai(), new SQLuzklausos().getVisuTiekejuDuomenys());
		
	}
	
	public TiekejuIrasuPanele(Statement st, String sqlUzklausa) {
		super(st, sqlUzklausa);
		
	}
	
	public TiekejuIrasuPanele(Statement st, Atributai imoniuAtributai, String sqlUzklausa) {
		super(st, imoniuAtributai, sqlUzklausa);
		
	}
	
	
	protected void render() {
		
		super.render();

    	redaguoti.setEnabled(false);
    	salinti.setEnabled(false);
	}
	
	protected void init() {
		
		super.init();	
			
		addActionListenersToButtons();
	}
	
	private void addActionListenersToButtons() {
		
		prideti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new IrasoPridejimoDialog(imoniuIrasuPanele, "Tiekejas");
				 
			}
		});
	
		redaguoti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new IrasoRedagavimoDialog(imoniuIrasuPanele, pasirinktaEilute+1, "Tiekejas");
				 
			}
		});		
		
		salinti.addActionListener(new ActionListener( ) {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new DBduomenys(imoniuIrasuPanele.getSt()
					, imoniuIrasuPanele.getDuomenuIrasai()).vykdykQuery(
						new SQLuzklausos().deleteTiekejoDuomenys(
								modelisIrasams.getValueAt(pasirinktaEilute, 0).toString()));
			
				imoniuIrasuPanele.refreshTable();
			
			}
			
			
			
		});
		
		lenteleIrasams.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		ListSelectionModel rowSM_imonems = lenteleIrasams.getSelectionModel();
		
		rowSM_imonems.addListSelectionListener(new ListSelectionListener() {
			
		    public void valueChanged(ListSelectionEvent e) {
		    	
		        if (e.getValueIsAdjusting()) return;
	
		        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
	
		        pasirinktaEilute = lsm.getMinSelectionIndex();
		        
		        if (lsm.isSelectionEmpty()) {

		        	redaguoti.setEnabled(false);
			    	
		        	salinti.setEnabled(false);
		        	
			    } 
		        	else {

		        		redaguoti.setEnabled(true);
				    	
		        		salinti.setEnabled(true);
	
			         }

		    }
		    
		});
		
		
	}

}
