package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Statement;

import javax.swing.JFrame;

import atributai.PrekesAtributai;
import funkcionalumas.DuomenuBaze.SQLuzklausos;

public class PrekesLikuciuDuomenuLangas extends JFrame{

	private Dimension EkranoDydis = Toolkit.getDefaultToolkit().getScreenSize();
	
	private double plotis = EkranoDydis.getWidth();
	
	private double aukstis = EkranoDydis.getHeight();	
	
	
	private Statement st;
	private String prekesKodas;

	private IrasuPerziurosPanele ipp;

	public PrekesLikuciuDuomenuLangas(Statement st,
	                                  String prekesKodas) {			
			
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Prekës kortelë");
		setSize((int)plotis/2, (int)(aukstis-50)/2);
		setResizable(true);
		
		this.st = st;
		this.prekesKodas = prekesKodas;
		
		init();
		render();
		
		setVisible(true);
		
		
	}

	private void render() {
		
		setLayout(new BorderLayout());		
		
		add(ipp, BorderLayout.CENTER);
		
	}


	private void init() {
		
		ipp = new IrasuPerziurosPanele(st, new PrekesAtributai(), 
				new SQLuzklausos().selectVisusPrekesLikuciuDuomenisPagalPrekesKoda(prekesKodas));
		
	}
}
