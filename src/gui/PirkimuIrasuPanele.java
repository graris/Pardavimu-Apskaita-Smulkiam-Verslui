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
import javax.swing.table.TableColumn;

import atributai.Atributai;
import atributai.PajamavimoDokumentoAtributai;
import atributai.SaskaitosAtributai;
import funkcionalumas.DuomenuBaze.DBduomenys;
import funkcionalumas.DuomenuBaze.SQLuzklausos;

public class PirkimuIrasuPanele extends IrasuPerziurosPanele{

	private JPanel paneleMygtukams;
	private JPanel paneleMygtukamsKairëje;
	
	private JButton redaguoti;
	private JButton salinti;
	private JButton pajamuotiSaskaita;
	private JButton pazymetiKaipApmoketa;
	
	private PirkimuIrasuPanele pip = this;
	
	private int pasirinktaEilute;
	
	public PirkimuIrasuPanele(Statement st) {
		
		super(st, new PajamavimoDokumentoAtributai(), new SQLuzklausos().getVisiPajamavimuDuomenys());
	
		addActionListenersToButtons();
		
	}

	protected void refreshTable() {
		
		super.refreshTable();
		
	    TableColumn tc = lenteleIrasams.getColumnModel().getColumn(7);
	    tc.setCellEditor(lenteleIrasams.getDefaultEditor(Boolean.class));
	    tc.setCellRenderer(lenteleIrasams.getDefaultRenderer(Boolean.class));
	}
	
	protected void render() {
		
		super.render();
		
	    TableColumn tc = lenteleIrasams.getColumnModel().getColumn(7);
	    tc.setCellEditor(lenteleIrasams.getDefaultEditor(Boolean.class));
	    tc.setCellRenderer(lenteleIrasams.getDefaultRenderer(Boolean.class));
		//----------------------------------------------------
		
		//---------Mygtuku isdestymai mygtuku paneleje--------
		
			//----------------------------------------------------
			
			paneleMygtukamsKairëje.add(redaguoti);
			paneleMygtukamsKairëje.add(pazymetiKaipApmoketa);
			
			//----------------------------------------------------
		
		paneleMygtukams.add(paneleMygtukamsKairëje);
		paneleMygtukams.add(pajamuotiSaskaita);
		
		//----------------------------------------------------
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;			
		
		paneleIrasams.add(paneleMygtukams, gbc);		
	
    	redaguoti.setEnabled(false);
    	pazymetiKaipApmoketa.setEnabled(false);
		
	}
	
	protected void init() {
		
		super.init();
		
		paneleMygtukams = new JPanel(new GridLayout(1, 2, 1, 1));
		paneleMygtukamsKairëje = new JPanel(new GridLayout(2, 1, 1, 1));
		
		redaguoti = new JButton("Redaguoti");
		salinti = new JButton("Ðalinti");
		pajamuotiSaskaita = new JButton("Pajamuoti sàskaità");		
		pazymetiKaipApmoketa = new JButton("Paþymëti sàskaità kaip apmokëtà");		
		
	}


	private void addActionListenersToButtons() {


		pajamuotiSaskaita.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PajamavimoFrame(getSt(), pip);
			}
			
			
			
			
		});
		
		pazymetiKaipApmoketa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new DBduomenys(pip.getSt()).vykdykQuery(
					new SQLuzklausos().updateApmoketiPajamavimoDok(
						pip.modelisIrasams.getValueAt(pasirinktaEilute, 0).toString(), 
						pip.modelisIrasams.getValueAt(pasirinktaEilute, 1).toString()
					)
				);
				
				pip.refreshTable();
				
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
			    	
		        	pazymetiKaipApmoketa.setEnabled(false);
		        	
			    } 
		        	else {

		        		redaguoti.setEnabled(true);
				    	
		        		pazymetiKaipApmoketa.setEnabled(true);
	
			         }

		    }
		    
		});

		
		
	}
	
}
