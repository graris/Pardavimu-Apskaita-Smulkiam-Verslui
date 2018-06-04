package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrekesIrasoPasirinkimoSaskaituIsrasymuiLangas extends PrekesIrasoPasirinkimoLangas{

	public PrekesIrasoPasirinkimoSaskaituIsrasymuiLangas(ApskaitosDokumentoFormavimoFrame adff,
			IrasuPerziurosPanele ipp) {
		super(adff, ipp);
		
		
	}
	
	protected void addActionListenersToButtons() {

		pasirinkti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int row = ipp.getLenteleIrasams().getSelectedRow();
				
				adff.pifsi.prekesKodasTxtFld.setText(ipp.getLenteleIrasams().getValueAt(row, 0).toString());
				adff.pifsi.barKodasTxtFld.setText(ipp.getLenteleIrasams().getValueAt(row, 1).toString());
				adff.pifsi.pavadinimasTxtFld.setText(ipp.getLenteleIrasams().getValueAt(row, 2).toString());
				adff.pifsi.kainaTxtFld.setText(ipp.getLenteleIrasams().getValueAt(row, 4).toString());
			}
			
	
			
		});
		
		
	}	

}
