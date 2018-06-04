package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import atributai.PirkejuAtributai;
import atributai.PrekesAtributai;
import atributai.SaskaitosAtributai;
import funkcionalumas.DuomenuBaze.SQLuzklausos;

public class Pagrindinis_Langas extends JFrame{

	//
	
	private GridBagLayout gbl;
	private GridLayout gl;
	private CardLayout cl;
	
	private static JPanel contentPanel = new JPanel();
	
	private JButton pirkejuIrasai,
					tiekejuIrasai,
					prekiuIrasai,
					pardavimai,
					pirkimai;
	
	private String  pirkejuTekstas = "Pirkëjai",
					tiekejuTekstas = "Tiekëjai",
					prekesTekstas = "Prekës",
					pardavimaiTekstas = "Pardavimai",
					pirkimaiTekstas = "Pirkimai";
	
	
	private Dimension EkranoDydis = Toolkit.getDefaultToolkit().getScreenSize();
	
	private double plotis = EkranoDydis.getWidth();
	
	private double aukstis = EkranoDydis.getHeight();
	
	private Statement st;
	
	private IrasuPerziurosPanele pan3;
	
	//
	
	public Pagrindinis_Langas(Statement st) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Pirkimø - pardavimø apskaita");
		setSize((int)plotis, (int)aukstis-50);
		setResizable(true);
		
		this.st = st;
		
		init();
		render();
		addActionListenersToMenuButtons();
		
		setVisible(true);
	}

	
	//
	
	private void render() {
		
		setLayout(new BorderLayout());
		
		JPanel menuContainerPanel = new JPanel();
		menuContainerPanel.setBackground(Color.gray);
		menuContainerPanel.setLayout(gbl);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(gl);
		
		menuPanel.add(pirkejuIrasai);
		menuPanel.add(tiekejuIrasai);
		menuPanel.add(prekiuIrasai);
		menuPanel.add(pardavimai);
		menuPanel.add(pirkimai);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		menuContainerPanel.add(menuPanel, c);
		

		contentPanel.setBackground(Color.lightGray);
		contentPanel.setLayout(cl);		

		
		//---
		JPanel pan0 = new JPanel();
		pan0.setBackground(Color.lightGray);
		pan0.setLayout(cl);
		
		IrasuPerziurosPanele pan1 = new PirkejuIrasuPanele(st);
		pan1.setLayout(cl);	
		
		IrasuPerziurosPanele pan2 = new TiekejuIrasuPanele(st);
		pan2.setLayout(cl);	
		
		pan3 = new PrekiuIrasuPanele(st);
		pan3.setLayout(cl);	
		
		IrasuPerziurosPanele pan4 = new PardavimuIrasuPanele(st);
		pan4.setLayout(cl);	
		
		IrasuPerziurosPanele pan5 = new PirkimuIrasuPanele(st);
		pan5.setLayout(cl);	

		//-----
		
		contentPanel.add(pan0, "");
		contentPanel.add(pan1, pirkejuTekstas);
		contentPanel.add(pan2, tiekejuTekstas);
		contentPanel.add(pan3, prekesTekstas);
		contentPanel.add(pan4, pardavimaiTekstas);
		contentPanel.add(pan5, pirkimaiTekstas);
	
		JPanel panel = new JPanel();
		
		panel.add(menuContainerPanel);
		
		add(panel, BorderLayout.LINE_START);
		add(contentPanel, BorderLayout.CENTER);
	}

	private void init() {
		
		gbl = new GridBagLayout();
		gl = new GridLayout(5, 0);
		cl = new CardLayout();

		pirkejuIrasai = new JButton(pirkejuTekstas);
		tiekejuIrasai = new JButton(tiekejuTekstas);
		prekiuIrasai = new JButton(prekesTekstas);
		pardavimai = new JButton(pardavimaiTekstas);
		pirkimai = new JButton(pirkimaiTekstas);		
	}

	private void addActionListenersToMenuButtons() {
		
		pirkejuIrasai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(contentPanel, pirkejuTekstas);
				
			}
			
			
		});
		
		tiekejuIrasai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(contentPanel, tiekejuTekstas);
				
			}
			
			
		});


		prekiuIrasai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(contentPanel, prekesTekstas);
				pan3.refreshTable();
			}
			
			
		});
		
		
		pardavimai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(contentPanel, pardavimaiTekstas);
				
			}
			
			
		});
		
		pirkimai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(contentPanel, pirkimaiTekstas);
				
			}
			
			
		});
	}
	
}
