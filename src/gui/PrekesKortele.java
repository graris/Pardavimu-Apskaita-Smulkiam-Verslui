package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import atributai.Atributai;
import atributai.PrekesAtributai;
import funkcionalumas.DuomenuBaze.DBduomenys;
import funkcionalumas.DuomenuBaze.SQLuzklausos;
import kitosKlases.InfoValdymas;

public class PrekesKortele extends JFrame{
	
	IrasuPerziurosPanele ipp;
	
	private Dimension EkranoDydis = Toolkit.getDefaultToolkit().getScreenSize();
	
	private double plotis = EkranoDydis.getWidth();
	
	private double aukstis = EkranoDydis.getHeight();

	private Statement st;

	private JButton paziuretiLikuciuDuomenis;
	
	private String prekesKodas;
	
	
	public PrekesKortele(Statement st,
						 String prekesKodas) {			
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Prekës kortelë");
		setSize((int)plotis/2, (int)(aukstis-50)/2);
		setResizable(true);
		
		this.st = st;
		this.prekesKodas = prekesKodas;
		
		init();
		addActionListeners();
		render();
		
		setVisible(true);


	}

	private void render() {
		setLayout(new BorderLayout());
		
		
		add(ipp, BorderLayout.CENTER);
		
		add(paziuretiLikuciuDuomenis, BorderLayout.PAGE_END);
		
		
	}

	private void addActionListeners() {
		
		paziuretiLikuciuDuomenis.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new PrekesLikuciuDuomenuLangas(st, prekesKodas);
				
			}
			
			
			
		});
		
		
	}

	private void init() {
			//selectPrekesPirkimusIrPardavimusPagalPrekesKoda
//		ipp = new IrasuPerziurosPanele(st, new PrekesAtributai(), 
//			new SQLuzklausos().selectVisusPrekesLikuciuDuomenisPagalPrekesKoda(prekesKodas));
		
		ipp = new IrasuPerziurosPanele(st, new PrekesAtributai(), 
				new SQLuzklausos().selectPrekesPirkimusIrPardavimusPagalPrekesKoda(prekesKodas));

		paziuretiLikuciuDuomenis = new JButton("Paþiûrëti likuèius");
	}
	
	
}
