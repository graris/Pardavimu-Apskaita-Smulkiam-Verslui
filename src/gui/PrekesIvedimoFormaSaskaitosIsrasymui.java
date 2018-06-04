package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import atributai.PrekesAtributai;
import funkcionalumas.DuomenuBaze.SQLuzklausos;
import layout.SpringUtilities;

public class PrekesIvedimoFormaSaskaitosIsrasymui extends PrekesIvedimoForma {

	private JLabel  kainaLbl,
					nuolaidosProcentasLbl,
					kainaSuNuolaidaLbl;
	
	protected JTextField  kainaTxtFld,
						nuolaidosProcentasTxtFld,
						kainaSuNuolaidaTxtFld;


	public PrekesIvedimoFormaSaskaitosIsrasymui(ApskaitosDokumentoFormavimoFrame adff) {
		super(adff);
		
	}
	
	protected void addActionListeners() {
		
		super.addActionListeners();
		

		ieskotiPrekes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new PrekesIrasoPasirinkimoSaskaituIsrasymuiLangas(adff,
						new IrasuPerziurosPanele(adff.st, new PrekesAtributai(), 
								new SQLuzklausos().selectPrekesPasirinkimuiIsrasantSaskaitai())
						);
				
			}
			
			
			
		});			
		
		pridetiPreke.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				adff.getModelisPasirinktomsPrekems().addRow(new Object[] {
					prekesKodasTxtFld.getText(),
					barKodasTxtFld.getText(),
					pavadinimasTxtFld.getText(),
					matavimoVienetasTxtFld.getText(),
					kainaTxtFld.getText(),
					nuolaidosProcentasTxtFld.getText(),
					kiekisTxtFld.getText(),
					pvmTxtFld.getText()				
				});

				
			}
			

			
		});
		
		nuolaidosProcentasTxtFld.getDocument().addDocumentListener(new DocumentListener() {
			
			public void changedUpdate(DocumentEvent e) {
				
			}
			
			public void removeUpdate(DocumentEvent e) {
				countPardavimoKainaILaukeli();
			}


			
			public void insertUpdate(DocumentEvent e) {
				countPardavimoKainaILaukeli();
			}
							  
			private void countPardavimoKainaILaukeli() {
				
				try {
					Double.parseDouble(kainaTxtFld.getText());
					if (kainaTxtFld.getText()!="") {
						
						kainaSuNuolaidaTxtFld.setText(String.valueOf(
							Double.parseDouble(kainaTxtFld.getText())
							-(Double.parseDouble(kainaTxtFld.getText())
							*Double.parseDouble(nuolaidosProcentasTxtFld.getText())/100)
						));
					}
					
					
					
				}
				catch(Exception ex) {
					// pirkimo kainos laukelyje buvo ivestas netinkamas duomenu tipas
				}
				
				
			}				
			
			
		});	
		
		kainaSuNuolaidaTxtFld.getDocument().addDocumentListener(new DocumentListener() {
			
			public void changedUpdate(DocumentEvent e) {
				
			}
			
			public void removeUpdate(DocumentEvent e) {
				countAntkainioProcentaILaukeli();
			}


			
			public void insertUpdate(DocumentEvent e) {
				countAntkainioProcentaILaukeli();
			}
							  
			private void countAntkainioProcentaILaukeli() {
				
				
				try {
					Double.parseDouble(kainaTxtFld.getText());
					if (kainaTxtFld.getText()!="") {
						
						nuolaidosProcentasTxtFld.setText(String.valueOf(
							(Double.parseDouble(kainaSuNuolaidaTxtFld.getText())
							-Double.parseDouble(kainaTxtFld.getText()))
							*100/Double.parseDouble(kainaTxtFld.getText())
						));
					}
					
					
					
				}
				catch(Exception ex) {
					// pirkimo kainos laukelyje buvo ivestas netinkamas duomenu tipas
				}
				
				
			}				
			
		});			
		
		
	}
	
	protected void init() {
		
		super.init();
		
		kainaLbl = new JLabel("Kaina:", JLabel.TRAILING);
		nuolaidosProcentasLbl = new JLabel("Nuolaida(%):", JLabel.TRAILING);
		kainaSuNuolaidaLbl = new JLabel("Kaina su nuolaida:", JLabel.TRAILING);

		kainaTxtFld = new JTextField();
		nuolaidosProcentasTxtFld = new JTextField();
		kainaSuNuolaidaTxtFld = new JTextField();
		
	}
	
	
	protected void render() {
		
		super.render();
		
		ivedimoLaukuPanel.add(kainaLbl);
		ivedimoLaukuPanel.add(kainaTxtFld);
		ivedimoLaukuPanel.add(nuolaidosProcentasLbl);
		ivedimoLaukuPanel.add(nuolaidosProcentasTxtFld);
		ivedimoLaukuPanel.add(kainaSuNuolaidaLbl);
		ivedimoLaukuPanel.add(kainaSuNuolaidaTxtFld);
		
        SpringUtilities.makeCompactGrid(ivedimoLaukuPanel,
        		9, 2,  //rows, cols
                3, 3,  //initX, initY
                3, 3); //xPad, yPad	
        
        
        
        add(ivedimoLaukuPanel, gbc);
        
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;		
		
        add(ieskotiPrekes, gbc);
        
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;	
		
		add(pridetiPreke, gbc);
		
		prekesKodasTxtFld.setEnabled(false);
		prekesKodasTxtFld.setDisabledTextColor(Color.BLACK);
		barKodasTxtFld.setEnabled(false);
		barKodasTxtFld.setDisabledTextColor(Color.BLACK);
		pavadinimasTxtFld.setEnabled(false);
		pavadinimasTxtFld.setDisabledTextColor(Color.BLACK);
		kainaTxtFld.setEnabled(false);
		kainaTxtFld.setDisabledTextColor(Color.BLACK);
		
	}
	
}
