package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import atributai.Atributai;
import funkcionalumas.DuomenuPaieska;
import funkcionalumas.DuomenuBaze.DBduomenys;
import kitosKlases.InfoValdymas;

public class IrasuPerziurosPanele extends JPanel{
	
	private ArrayList <ArrayList<String>> duomenuIrasai = new  ArrayList <ArrayList<String>>();
	
	private JLabel etiketeIrasuPaieskai;
	
	protected DefaultTableModel modelisIrasams;
	protected JTable lenteleIrasams;
	
	private JScrollPane scrollpaneIrasams;
	
	private JPanel paneleIrasuPaieskai;
	
	private Statement st;

	protected JPanel paneleIrasams;
	protected GridBagConstraints gbc;
	
	private int pasirinktaEilute;
	
	Atributai atributai;
	String sqlUzklausa;
	
	public IrasuPerziurosPanele(Statement st, 
		Atributai atributai,
		String sqlUzklausa) {
		
		this.st = st;

		this.atributai = atributai;
		this.sqlUzklausa = sqlUzklausa;
		
		init();
		render();
		
	}
	


	public Statement getSt() {
		return st;
	}	
	
	public ArrayList <ArrayList<String>> getDuomenuIrasai () {
		
		return duomenuIrasai;
	}
	
	public int getPasirinktaEilute() {
		return pasirinktaEilute;
	}
	
	public JTable getLenteleIrasams() {
		return lenteleIrasams;
	}
	
	protected void refreshTable() {
		
		new DBduomenys(st, duomenuIrasai).getDBlentelesDuomenys(atributai, sqlUzklausa);
		
		new InfoValdymas().Surasymas_i_lentele(duomenuIrasai, modelisIrasams);
	}

	protected void render() {
		
		setLayout(new BorderLayout());

		refreshTable();
		
		//-------------------------------------------------

		gbc.insets = new Insets(2, 2, 2, 2);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;	
		
		paneleIrasams.add(etiketeIrasuPaieskai, gbc);
		
			//---------------------Irasu paiesku paneles-----------------------
 
			
		 	
		
			ArrayList <JTextField> irasuTekstoLaukai = new ArrayList <JTextField>();
			
			for (int i=0; i<duomenuIrasai.get(0).size(); i++) {
				
				JPanel PaneleKainai = new JPanel(new GridLayout(1, 2, 5, 5));
				
				if (!duomenuIrasai.get(0).get(i).equals("Pirkimo kaina")) {
				
					JTextField textfield = new JTextField(duomenuIrasai.get(0).get(i));
					
					paneleIrasuPaieskai.add(textfield);
					
					irasuTekstoLaukai.add(textfield);
				
				} 
					else {
							
							JTextField textfield1 = new JTextField("Nuo");
							
							PaneleKainai.add(textfield1);
							
							irasuTekstoLaukai.add(textfield1);
							
							JTextField textfield2 = new JTextField("Iki");
							
							PaneleKainai.add(textfield2);
							
							irasuTekstoLaukai.add(textfield2);
							
							paneleIrasuPaieskai.add(PaneleKainai);
							
					}
				
			}
		
		
			
			for (int i=0; i<irasuTekstoLaukai.size(); i++) {
								
				DuomenuPaieska.Paieskos_Laukas(duomenuIrasai, irasuTekstoLaukai,
						modelisIrasams, irasuTekstoLaukai.get(i), 
						irasuTekstoLaukai.get(i).getText());
				
			}
			
			//----------------------------------------------------------------------------
			
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;	
		
		paneleIrasams.add(paneleIrasuPaieskai, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = java.awt.GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;	
		
		paneleIrasams.add(scrollpaneIrasams, gbc);		
			
		add(paneleIrasams, BorderLayout.CENTER);
	
	}

	protected void init() {

		etiketeIrasuPaieskai = new JLabel("Paieðkos laukai:");

		paneleIrasuPaieskai = new JPanel(new GridLayout(1, 0, 5, 5));		
		paneleIrasams = new JPanel(new GridBagLayout());
		
		gbc = new GridBagConstraints();
		
		modelisIrasams = new DefaultTableModel() {

			public boolean isCellEditable(int row, int column) {

		        return false;
		        
		    }
		    
		};
		
		
		lenteleIrasams = new JTable(modelisIrasams);		
		
		lenteleIrasams.getTableHeader().setReorderingAllowed(false);
		
		scrollpaneIrasams = new JScrollPane(lenteleIrasams);
		
	}

}
