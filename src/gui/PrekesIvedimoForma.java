package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import atributai.PrekesAtributai;
import atributai.TiekejuAtributai;
import funkcionalumas.DuomenuBaze.DBduomenys;
import funkcionalumas.DuomenuBaze.SQLuzklausos;
import layout.SpringUtilities;

public class PrekesIvedimoForma extends JFrame{
	
	protected JLabel    prekesKodasLbl,
						barKodasLbl,
						pavadinimasLbl,
						matavimoVienetasLbl,
						kiekisLbl,
						pvmLbl;
	
	protected JTextField    prekesKodasTxtFld,
							barKodasTxtFld,
							pavadinimasTxtFld,
							matavimoVienetasTxtFld,
							kiekisTxtFld,
							pvmTxtFld;
	
	JPanel ivedimoLaukuPanel;
	
	JButton ieskotiPrekes,
			pridetiPreke;
	
	protected GridBagConstraints gbc;

	private Dimension EkranoDydis = Toolkit.getDefaultToolkit().getScreenSize();
	
	private double plotis = EkranoDydis.getWidth();
	
	private double aukstis = EkranoDydis.getHeight();

	protected ApskaitosDokumentoFormavimoFrame adff;
	
	protected int ivedimoLaukuRows;
	
	public PrekesIvedimoForma(ApskaitosDokumentoFormavimoFrame adff) {
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Prekës ávedimas");
		setSize((int)plotis/3, (int)(aukstis/3)+100);
		setResizable(true);
		
		this.adff = adff;

		init();
		addActionListeners();
		render();
		
		setVisible(true);
	}

	protected void addActionListeners() {
		
//	
			
	}
		
		
		
		
		

	protected void render() {
		
		setLayout(new GridBagLayout());
		
		gbc.insets = new Insets(2, 2, 2, 2);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;	
		
		ivedimoLaukuPanel.add(prekesKodasLbl);
		ivedimoLaukuPanel.add(prekesKodasTxtFld);
		ivedimoLaukuPanel.add(barKodasLbl);
		ivedimoLaukuPanel.add(barKodasTxtFld);
		ivedimoLaukuPanel.add(pavadinimasLbl);
		ivedimoLaukuPanel.add(pavadinimasTxtFld);
		ivedimoLaukuPanel.add(matavimoVienetasLbl);
		ivedimoLaukuPanel.add(matavimoVienetasTxtFld);	
		ivedimoLaukuPanel.add(kiekisLbl);
		ivedimoLaukuPanel.add(kiekisTxtFld);
		ivedimoLaukuPanel.add(pvmLbl);
		ivedimoLaukuPanel.add(pvmTxtFld);
		
		
//		prekesKodasTxtFld.setEnabled(false);
//		prekesKodasTxtFld.setDisabledTextColor(Color.BLACK);
//		barKodasTxtFld.setEnabled(false);
//		barKodasTxtFld.setDisabledTextColor(Color.BLACK);
//		pavadinimasTxtFld.setEnabled(false);
//		pavadinimasTxtFld.setDisabledTextColor(Color.BLACK);

		
		matavimoVienetasTxtFld.setText("vnt.");
		matavimoVienetasTxtFld.setEnabled(false);
		matavimoVienetasTxtFld.setDisabledTextColor(Color.BLACK);
		
		pvmTxtFld.setText("21");
		
	}

	protected void init() {

		ivedimoLaukuRows = 6;
		
		prekesKodasLbl = new JLabel("Prekes kodas:", JLabel.TRAILING);
		barKodasLbl = new JLabel("Bar kodas:", JLabel.TRAILING);
		pavadinimasLbl = new JLabel("Pavadinimas:", JLabel.TRAILING);
		matavimoVienetasLbl = new JLabel("Matavimo vienetas:", JLabel.TRAILING);
		kiekisLbl = new JLabel("Kiekis:", JLabel.TRAILING);
		pvmLbl = new JLabel("PVM:", JLabel.TRAILING);


		prekesKodasTxtFld = new JTextField();
		barKodasTxtFld = new JTextField();
		pavadinimasTxtFld = new JTextField();
		matavimoVienetasTxtFld = new JTextField();
		kiekisTxtFld = new JTextField();
		pvmTxtFld = new JTextField();	
		
		gbc = new GridBagConstraints();
		ivedimoLaukuPanel = new JPanel(new SpringLayout());
		
		ieskotiPrekes = new JButton("Ieðkoti prekës");
		pridetiPreke = new JButton("Pridëti prekæ");
		
	}

}
