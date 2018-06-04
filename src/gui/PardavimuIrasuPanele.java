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
import atributai.SaskaitosAtributai;
import funkcionalumas.DuomenuBaze.DBduomenys;
import funkcionalumas.DuomenuBaze.SQLuzklausos;

public class PardavimuIrasuPanele extends IrasuPerziurosPanele{

	private JPanel paneleMygtukams;
	private JPanel paneleRedSalMygtukams;
	
	private JButton redaguoti;
	private JButton salinti;
	private JButton israsytiSaskaitaFaktura;
	private JButton pazymetiKaipApmoketa;
	
	private PardavimuIrasuPanele pip = this;
	
    private int pasirinktaEilute;
	
	public PardavimuIrasuPanele(Statement st) {
		
		super(st, new SaskaitosAtributai(), new SQLuzklausos().getVisiSaskaitosDuomenys());

		addActionListenersToButtons();
		
	}

	protected void refreshTable() {
		
		super.refreshTable();
		
	    TableColumn tc = lenteleIrasams.getColumnModel().getColumn(8);
	    tc.setCellEditor(lenteleIrasams.getDefaultEditor(Boolean.class));
	    tc.setCellRenderer(lenteleIrasams.getDefaultRenderer(Boolean.class));
	}
	
	protected void render() {
		
		super.render();
		
	    TableColumn tc = lenteleIrasams.getColumnModel().getColumn(8);
	    tc.setCellEditor(lenteleIrasams.getDefaultEditor(Boolean.class));
	    tc.setCellRenderer(lenteleIrasams.getDefaultRenderer(Boolean.class));
		//----------------------------------------------------
		
		//---------Mygtuku isdestymai mygtuku paneleje--------
		
			//----------------------------------------------------
			
			paneleRedSalMygtukams.add(redaguoti);
			paneleRedSalMygtukams.add(pazymetiKaipApmoketa);
			
			//----------------------------------------------------
		
		paneleMygtukams.add(paneleRedSalMygtukams);
		paneleMygtukams.add(israsytiSaskaitaFaktura);
		
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
		paneleRedSalMygtukams = new JPanel(new GridLayout(2, 1, 1, 1));
		
		redaguoti = new JButton("Redaguoti");
		salinti = new JButton("Ðalinti");
		pazymetiKaipApmoketa = new JButton("Paþymëti sàskaità kaip apmokëtà");		
		israsytiSaskaitaFaktura = new JButton("Iðraðyti sàskaità - faktûrà");		
		
	}
	

	private void addActionListenersToButtons() {


		israsytiSaskaitaFaktura.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SaskaitosFakturosFormavimoFrame(getSt(), pip);
			}
			
			
			
			
		});
		
		pazymetiKaipApmoketa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new DBduomenys(pip.getSt()).vykdykQuery(
					new SQLuzklausos().updateApmoketiSaskaitaFaktura(
						pip.modelisIrasams.getValueAt(pasirinktaEilute, 0).toString()
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
