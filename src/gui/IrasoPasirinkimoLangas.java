package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;

import atributai.Atributai;

public class IrasoPasirinkimoLangas extends JFrame{

	protected IrasuPerziurosPanele ipp;
	private GridBagConstraints gbc;
	
	private Dimension EkranoDydis = Toolkit.getDefaultToolkit().getScreenSize();
	
	private double plotis = EkranoDydis.getWidth();
	private double aukstis = EkranoDydis.getHeight();
	
	protected JButton pasirinkti;
	
	protected ApskaitosDokumentoFormavimoFrame adff;

	public IrasoPasirinkimoLangas(ApskaitosDokumentoFormavimoFrame adff, IrasuPerziurosPanele ipp) {
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Duomenø áraðo pasirinkimas");
		setSize((int)plotis/2, (int)aukstis/2);
		setResizable(true);

		this.adff = adff;
		this.ipp = ipp;
		
		init();
		render();
		
		setVisible(true);
	}

	private void render() {
		
		setLayout(new GridBagLayout());
		
		gbc.insets = new Insets(2, 2, 2, 2);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = java.awt.GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;	
		
		add(ipp, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		
		add(pasirinkti, gbc);
		
		
	}

	private void init() {
		
		gbc = new GridBagConstraints();
		
		pasirinkti = new JButton("Pasirinkti");
		
	}

}
