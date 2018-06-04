package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;

import javax.swing.JButton;

import atributai.TiekejuAtributai;
import funkcionalumas.DuomenuBaze.DBduomenys;
import funkcionalumas.DuomenuBaze.SQLuzklausos;

public class PajamavimoFrame extends ApskaitosDokumentoFormavimoFrame {

	private JButton pajamuoti;
	
	public PajamavimoFrame(Statement st, PirkimuIrasuPanele pip) {
		super(st, pip);
		
		setTitle("Pajamavimas");
		
	}
	
	
	protected void init() {
		
		super.init();
		
		tab1Caption = "Tiekëjas";
		tab2Caption = "Prekës";
		
		pajamuoti = new JButton("Pajamuoti");
		
	}
	
	protected void render() {
		
		super.render();
		
		modelisPasirinktomsPrekems.setColumnIdentifiers(new String[]{
				"Prekes kodas",
				"Bar kodas",
				"Pavadinimas",
				"Matavimo vienetas",
				"Pirkimo kaina",
				"Antkainio procentas",
				"Kiekis", 
				"PVM"
			});
		
		mygtukuPanele.add(pajamuoti, BorderLayout.LINE_END);	
		
	}
	
	protected void addActionListeners() {
		
		super.addActionListeners();
		
		pasirinktiPreke.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				pif = new PrekesIvedimoFormaPajamavimui(adff);
				
			}
			
				
		});
		
		
		pajamuoti.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new DBduomenys(st, ipp.getDuomenuIrasai())
				.vykdykQuery(new SQLuzklausos().insertPajamavimuDuomenys(
					serijaTxtFld.getText(),
					Integer.valueOf(numerisTxtFld.getText()),
					imonesKodasFld.getText(), 
					1, 
					dataModel.getYear()+"-"+(dataModel.getMonth()+1)+"-"+dataModel.getDay(), 
					apmoketiIkiModel.getYear()+"-"+(apmoketiIkiModel.getMonth()+1)+"-"+apmoketiIkiModel.getDay(), 
					modelisPasirinktomsPrekems.getValueAt(0, 7).toString(), 
					apmoketaCheckBox.isSelected()? 1 : 0		
				));
				
				
				for (int i=0;i<modelisPasirinktomsPrekems.getRowCount();i++) {
					
					String uuid = new DBduomenys(st).vykdykQueryIrGrazinkVienaStringReiksme(
						new SQLuzklausos().selectUUID()
					);
					
					new DBduomenys(st, ipp.getDuomenuIrasai())
					.vykdykQuery(new SQLuzklausos().insertPrekesDuomenys(
						uuid,
						modelisPasirinktomsPrekems.getValueAt(i, 0).toString(),		//	PREKES_KODAS, 
						modelisPasirinktomsPrekems.getValueAt(i, 1).toString(),		//	BAR_KODAS, 
						modelisPasirinktomsPrekems.getValueAt(i, 2).toString(),		//	PAVADINIMAS, 
						modelisPasirinktomsPrekems.getValueAt(i, 3).toString(),		//	MATAVIMO_VIENETAS, 
						modelisPasirinktomsPrekems.getValueAt(i, 4).toString(),		//	PIRKIMO_KAINA, 
						modelisPasirinktomsPrekems.getValueAt(i, 5).toString(),		//	ANTKAINIO_PROCENTAS, 
						modelisPasirinktomsPrekems.getValueAt(i, 6).toString(),		//	KIEKIS, 
						dataModel.getYear()+"-"+(dataModel.getMonth()+1)+"-"+dataModel.getDay(),		//	PIRKIMO_DATA, 
						imonesKodasFld.getText(),		//	TIEKEJO_IMONES_KODAS, 
						modelisPasirinktomsPrekems.getValueAt(i, 7).toString()		//	PVM
					));
				
					new DBduomenys(st, ipp.getDuomenuIrasai())
					.vykdykQuery(new SQLuzklausos().insertPirktosPrekesDuomenys(
						serijaTxtFld.getText(),
						Integer.parseInt(numerisTxtFld.getText()), 
						uuid, 
						modelisPasirinktomsPrekems.getValueAt(i, 6).toString(), // parduodamas prekes kiekis pagal UUID
						modelisPasirinktomsPrekems.getValueAt(i, 4).toString(),
						modelisPasirinktomsPrekems.getValueAt(i, 5).toString()
					));
				}
				ipp.refreshTable();
			}
			
			
			
		});
		
		pasirinktiImone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new ImonesIrasoPasirinkimoLangas(adff,
						new IrasuPerziurosPanele(st, new TiekejuAtributai(), 
								new SQLuzklausos().getVisuTiekejuDuomenys())
						);
				
			}
			
		});
		
	}

}
