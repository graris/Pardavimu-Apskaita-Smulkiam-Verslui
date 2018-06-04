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
import atributai.PrekesAtributai;
import funkcionalumas.DuomenuBaze.SQLuzklausos;

public class PrekiuIrasuPanele extends IrasuPerziurosPanele{

	private JPanel paneleMygtukams;
	
	private JButton atidarytiKortele;
	private Statement st;
	
	private PrekiuIrasuPanele pip = this;

	
	public PrekiuIrasuPanele(Statement st) {
		
		super(st, new PrekesAtributai(), new SQLuzklausos().selectPrekesGrupuojantPagalPrekesKoda());

		this.st = st;
		
		addActionListeners();
	}
	

	
	private void addActionListeners() {
		atidarytiKortele.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new PrekesKortele(st,
						pip.modelisIrasams.getValueAt(pip.lenteleIrasams.getSelectedRow(), 0).toString());
				
				refreshTable();
				
			}
			
			
			
		});
		
	
		lenteleIrasams.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		ListSelectionModel rowSM_imonems = lenteleIrasams.getSelectionModel();
		
		rowSM_imonems.addListSelectionListener(new ListSelectionListener() {
			
		    public void valueChanged(ListSelectionEvent e) {
		    	
		        if (e.getValueIsAdjusting()) return;
	
		        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		        
		        if (lsm.isSelectionEmpty()) {

		        	atidarytiKortele.setEnabled(false);
		        	
			    } 
		        	else {

		        		atidarytiKortele.setEnabled(true);
	
			         }

		    }
		    
		});
		
		
	}



	protected void render() {
		
		super.render();

		paneleMygtukams.add(atidarytiKortele);
		
		//----------------------------------------------------
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;			
		
		paneleIrasams.add(paneleMygtukams, gbc);		
		
		atidarytiKortele.setEnabled(false);
		
	}
	
	protected void init() {
		
		super.init();
		
		paneleMygtukams = new JPanel(new GridLayout(1, 2, 1, 1));
		
		atidarytiKortele = new JButton("Atidaryti kortelæ");		
		
	}
	
	

}
