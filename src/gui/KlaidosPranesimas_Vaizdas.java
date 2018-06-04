package gui;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class KlaidosPranesimas_Vaizdas extends JDialog {
	
	private JOptionPane optionPane;
	
	public KlaidosPranesimas_Vaizdas(String klaidosPranesimas) {
		
		setKlaidosPranesimas(klaidosPranesimas);
		
		setTitle("Praneðimas");
		setModal(true);	
		setContentPane(optionPane);	
		pack();			
		setVisible(true);
		
	}
	
	private void setKlaidosPranesimas(String klaidosPranesimas) {
		
		optionPane = new JOptionPane(klaidosPranesimas, JOptionPane.INFORMATION_MESSAGE, 
				JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
	
		optionPane.setMessageType(2);
		
	}

}
