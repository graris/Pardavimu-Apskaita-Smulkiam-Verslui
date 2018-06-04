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
import funkcionalumas.DuomenuBaze.SQLuzklausos;
import funkcionalumas.DuomenuBaze.DBduomenys;

public class ImoniuIrasuPanele extends IrasuPerziurosPanele{

	protected JPanel paneleMygtukams;
	protected JPanel paneleRedSalMygtukams;
	
	protected JButton redaguoti;
	protected JButton salinti;
	protected JButton prideti;
	
	protected int pasirinktaEilute;
	
	protected ImoniuIrasuPanele imoniuIrasuPanele = this;

	public ImoniuIrasuPanele(Statement st) {
		super(st, new PirkejuAtributai(), new SQLuzklausos().getVisuPirkejuDuomenys());
		
	}
	
	public ImoniuIrasuPanele(Statement st, String sqlUzklausa) {
		super(st, new PirkejuAtributai(), sqlUzklausa);
		
	}
	
	public ImoniuIrasuPanele(Statement st, Atributai imoniuAtributai, String sqlUzklausa) {
		super(st, imoniuAtributai, sqlUzklausa);
		
	}
	
	protected void render() {
		
		super.render();
		//----------------------------------------------------
		
		//---------Mygtuku isdestymai mygtuku paneleje--------
		
			//----------------------------------------------------
			
			paneleRedSalMygtukams.add(redaguoti);
			paneleRedSalMygtukams.add(salinti);
			
			//----------------------------------------------------
		
		paneleMygtukams.add(paneleRedSalMygtukams);
		paneleMygtukams.add(prideti);
		
		//----------------------------------------------------
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;			
		
		paneleIrasams.add(paneleMygtukams, gbc);				

	}
	
	protected void init() {
		
		super.init();
		
		paneleMygtukams = new JPanel(new GridLayout(1, 2, 1, 1));
		paneleRedSalMygtukams = new JPanel(new GridLayout(2, 1, 1, 1));
		
		redaguoti = new JButton("Redaguoti");
		salinti = new JButton("Ðalinti");
		prideti = new JButton("Pridëti");	
		
	}


}
