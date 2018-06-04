package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import atributai.PrekesAtributai;
import funkcionalumas.DuomenuBaze.SQLuzklausos;
import layout.SpringUtilities;

public class PrekesIvedimoFormaPajamavimui extends PrekesIvedimoForma{

	JLabel  pirkimoKainaLbl,
			antkainioProcentasLbl,
			pardavimoKainaLbl;	
	
	JTextField	pirkimoKainaTxtFld,
				antkainioProcentasTxtFld,
				pardavimoKainaTxtFld;
	
	
	public PrekesIvedimoFormaPajamavimui(ApskaitosDokumentoFormavimoFrame adff) {
		super(adff);
	
		
	}

	protected void addActionListeners() {
		
		super.addActionListeners();
		
		
		ieskotiPrekes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new PrekesIrasoPasirinkimoLangas(adff,
						new IrasuPerziurosPanele(adff.st, new PrekesAtributai(), 
								new SQLuzklausos().selectPrekesPasirinkimuiIsrasantSaskaitaiArbaPajamuojant())
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
					pirkimoKainaTxtFld.getText(),
					antkainioProcentasTxtFld.getText(),
					kiekisTxtFld.getText(),
					pvmTxtFld.getText()				
				});

				
			}
			

			
		});
		
		
		antkainioProcentasTxtFld.getDocument().addDocumentListener(new DocumentListener() {
			
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
					Double.parseDouble(pirkimoKainaTxtFld.getText());
					if (pirkimoKainaTxtFld.getText()!="") {
						
						pardavimoKainaTxtFld.setText(String.valueOf(
							Double.parseDouble(pirkimoKainaTxtFld.getText())
							+(Double.parseDouble(pirkimoKainaTxtFld.getText())
							*Double.parseDouble(antkainioProcentasTxtFld.getText())/100)
						));
					}
					
					
					
				}
				catch(Exception ex) {
					// pirkimo kainos laukelyje buvo ivestas netinkamas duomenu tipas
				}
				
				
			}				
			
			
		});	
		
		pardavimoKainaTxtFld.getDocument().addDocumentListener(new DocumentListener() {
			
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
					Double.parseDouble(pirkimoKainaTxtFld.getText());
					if (pirkimoKainaTxtFld.getText()!="") {
						
						antkainioProcentasTxtFld.setText(String.valueOf(
							(Double.parseDouble(pardavimoKainaTxtFld.getText())
							-Double.parseDouble(pirkimoKainaTxtFld.getText()))
							*100/Double.parseDouble(pirkimoKainaTxtFld.getText())
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
		
		ivedimoLaukuRows = 9;
		
		pirkimoKainaLbl = new JLabel("Pirkimo kaina:", JLabel.TRAILING);
		antkainioProcentasLbl = new JLabel("Antkainio procentas:", JLabel.TRAILING);
		pardavimoKainaLbl = new JLabel("Pardavimo kaina:", JLabel.TRAILING);
		
		pirkimoKainaTxtFld = new JTextField();
		antkainioProcentasTxtFld = new JTextField();
		pardavimoKainaTxtFld = new JTextField();
		
	}
	
	
	protected void render() {
		
		super.render();
		
		ivedimoLaukuPanel.add(pirkimoKainaLbl);
		ivedimoLaukuPanel.add(pirkimoKainaTxtFld);
		ivedimoLaukuPanel.add(antkainioProcentasLbl);
		ivedimoLaukuPanel.add(antkainioProcentasTxtFld);
		ivedimoLaukuPanel.add(pardavimoKainaLbl);
		ivedimoLaukuPanel.add(pardavimoKainaTxtFld);
		
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
		
	}
	
	
	
}
