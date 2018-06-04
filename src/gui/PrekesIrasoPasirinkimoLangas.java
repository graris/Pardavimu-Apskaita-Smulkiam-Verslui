package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrekesIrasoPasirinkimoLangas extends IrasoPasirinkimoLangas {

	public PrekesIrasoPasirinkimoLangas(ApskaitosDokumentoFormavimoFrame adff, IrasuPerziurosPanele ipp) {
		super(adff, ipp);

		addActionListenersToButtons();
	}
	
	protected void addActionListenersToButtons() {

		pasirinkti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int row = ipp.getLenteleIrasams().getSelectedRow();
				
				adff.pif.prekesKodasTxtFld.setText(ipp.getLenteleIrasams().getValueAt(row, 0).toString());
				adff.pif.barKodasTxtFld.setText(ipp.getLenteleIrasams().getValueAt(row, 1).toString());
				adff.pif.pavadinimasTxtFld.setText(ipp.getLenteleIrasams().getValueAt(row, 2).toString());
			}
			
	
			
		});
		
		
	}		
	

}
