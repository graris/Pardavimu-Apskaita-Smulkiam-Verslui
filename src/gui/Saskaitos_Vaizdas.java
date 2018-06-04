package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import kitosKlases.SaskaitosPateikimas;

public class Saskaitos_Vaizdas extends JDialog{

	private JEditorPane saskaita= new JEditorPane();
	
	private JScrollPane scrollpaneSaskaitai = new JScrollPane(saskaita);
	
	private JButton israsyti = new JButton("Iðraðyti");
	
//	private Israsymo_Vaizdas israsymoVaizdas;
	
	public Saskaitos_Vaizdas() {//Israsymo_Vaizdas israsymoVaizdas) {
		
		//this.israsymoVaizdas = israsymoVaizdas;
		
		init();
		
	}
	
	public JButton getIsrasyti() {
		return israsyti;
	}

	private void init() {
		
		scrollpaneSaskaitai.setPreferredSize(new Dimension(600, 800));
		
		Object complexMsg[] = {scrollpaneSaskaitai, israsyti};
		
		JOptionPane optionPane = new JOptionPane(complexMsg, JOptionPane.INFORMATION_MESSAGE, 
				JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

		optionPane.setMessageType(-1);
		
		setTitle("Sàskaita-faktûra");
		setModal(true);

		setContentPane(optionPane);
			
		
		addComponentListener(new ComponentAdapter() {
			 
		  public void componentHidden(ComponentEvent e) {
				  
			  optionPane.removeAll();
			  System.out.println("Iðjungtas");
		  }
		
		  public void componentShown(ComponentEvent e) {				  

				try {		
					
					File file= new File("saskaita.html");
				
					URL page = file.toURI().toURL();
					
					saskaita.setContentType("text/html");
					saskaita.setEditable(false);
					saskaita.setPage(page);
					
				
				}
					catch(IOException ex) {
						
						System.out.println("Nëra failo...");
						
					}	
		  }
		  	
		});
			
		israsyti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new SaskaitosPateikimas().Spausdinimo_langas();//israsymoVaizdas);
			}	
		});
		
		pack();
		
		setVisible(true);
	}
	
}
