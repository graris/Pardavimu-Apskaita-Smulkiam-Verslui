package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImonesIrasoPasirinkimoLangas extends IrasoPasirinkimoLangas{

	public ImonesIrasoPasirinkimoLangas(ApskaitosDokumentoFormavimoFrame adff, IrasuPerziurosPanele ipp) {
		super(adff, ipp);

		addActionListenersToButtons();
	}
	
	private void addActionListenersToButtons() {

		pasirinkti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int row = ipp.getLenteleIrasams().getSelectedRow();
				
				
				adff.getAdresasFld().setText(ipp.getLenteleIrasams().getValueAt(row, 3).toString());
				adff.getImonesKodasFld().setText(ipp.getLenteleIrasams().getValueAt(row, 0).toString());
				adff.getPavadinimasFld().setText(ipp.getLenteleIrasams().getValueAt(row, 1).toString());
				adff.getPvmKodasFld().setText(ipp.getLenteleIrasams().getValueAt(row, 2).toString());
				
			}
			
			
			
		});
		
		
	}	
	

}
