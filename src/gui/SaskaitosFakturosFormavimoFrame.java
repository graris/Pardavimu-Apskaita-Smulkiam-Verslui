package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import atributai.PirkejuAtributai;
import atributai.TiekejuAtributai;
import funkcionalumas.DuomenuBaze.DBduomenys;
import funkcionalumas.DuomenuBaze.SQLuzklausos;
import kitosKlases.InvoiceDesign;
import net.sf.dynamicreports.examples.complex.invoice.Customer;
import net.sf.dynamicreports.examples.complex.invoice.Item;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

public class SaskaitosFakturosFormavimoFrame extends ApskaitosDokumentoFormavimoFrame{

	private JButton issaugoti;
	private JButton issaugotiIrIsrasyti;
	
	private SaskaitosFakturosFormavimoFrame sfff = this;

	public SaskaitosFakturosFormavimoFrame(Statement st, IrasuPerziurosPanele ipp) {
		super(st, ipp);

		setTitle("Sàskaitos faktûros formavimas");
	}
	
	protected void addActionListeners() {
		
		super.addActionListeners();
			
		pasirinktiPreke.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				pifsi = new PrekesIvedimoFormaSaskaitosIsrasymui(adff);
				
			}
			
				
		});
			
		issaugoti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				for (int i=0;i<modelisPasirinktomsPrekems.getRowCount();i++) {
					
					int perkamasPrekiuKiekis = Integer.parseInt(
						modelisPasirinktomsPrekems.getValueAt(i, 6).toString()
					);
										
					int prekesLikutis = Integer.parseInt(
						new DBduomenys(st).vykdykQueryIrGrazinkVienaStringReiksme(
							new SQLuzklausos().selectPrekesLikutisSandelyjePagalPrekesKodà(
								modelisPasirinktomsPrekems.getValueAt(i, 0).toString()
							)
						)
					);
					
					
					
					if (prekesLikutis < perkamasPrekiuKiekis) {
						new KlaidosPranesimas_Vaizdas(
							"Prekës, kurios kodas \"" + modelisPasirinktomsPrekems.getValueAt(i, 0).toString()
							+ "\" parduodamas kiekis virðija sandëlyje esantá likutá");
						return;
					}
					
				}				
				
				
				new DBduomenys(st, ipp.getDuomenuIrasai())
					.vykdykQuery(new SQLuzklausos().insertSaskaitosDuomenys(
						Integer.valueOf(numerisTxtFld.getText()),
						1,
						imonesKodasFld.getText(), 
						1, 
						dataModel.getYear()+"-"+(dataModel.getMonth()+1)+"-"+dataModel.getDay(), 
						apmoketiIkiModel.getYear()+"-"+(apmoketiIkiModel.getMonth()+1)+"-"+apmoketiIkiModel.getDay(), 
						123,
						modelisPasirinktomsPrekems.getValueAt(0, 7).toString(), 
						apmoketaCheckBox.isSelected()? 1 : 0		
					));
				
				for (int i=0;i<modelisPasirinktomsPrekems.getRowCount();i++) {
				
					int parduodamasPrekiuKiekis = Integer.parseInt(modelisPasirinktomsPrekems.getValueAt(i, 6).toString());
					
					// Parduodant prekes jos ið sandelio nuskaièiuojamos naudojant FIFO metodà
					while (parduodamasPrekiuKiekis > 0) {
					
						String parduodamosSeniausiosSandelyPrekesUUID = new DBduomenys(st).vykdykQueryIrGrazinkVienaStringReiksme(
							new SQLuzklausos().selectSeniausiaiPirktosDarLikusiosSandelyPrekesUUIDpagalPrekesKoda(
								modelisPasirinktomsPrekems.getValueAt(i, 0).toString()
							)
					    );
						
						int prekesLikutis = Integer.parseInt(
							new DBduomenys(st).vykdykQueryIrGrazinkVienaStringReiksme(
								new SQLuzklausos().selectPrekesLikutisSandelyPagalUUID(
									parduodamosSeniausiosSandelyPrekesUUID
								)
							)
						);
						
						int minusuojamasPrekesKiekisPagalUUID = 0;
						
						if (prekesLikutis <= parduodamasPrekiuKiekis) {
							
							new DBduomenys(st).vykdykQuery(
								new SQLuzklausos().updatePrekesLikutisPagalUUID(0, parduodamosSeniausiosSandelyPrekesUUID)
							);
							
							minusuojamasPrekesKiekisPagalUUID = prekesLikutis;
						}
						else {
							
							new DBduomenys(st).vykdykQuery(
								new SQLuzklausos().updatePrekesLikutisPagalUUID(prekesLikutis-parduodamasPrekiuKiekis, parduodamosSeniausiosSandelyPrekesUUID)
							);
							
							minusuojamasPrekesKiekisPagalUUID = parduodamasPrekiuKiekis;
							
						}
										
						parduodamasPrekiuKiekis -= minusuojamasPrekesKiekisPagalUUID;		
						
		
						float antkainioProc = Float.parseFloat(		
							new DBduomenys(st).vykdykQueryIrGrazinkVienaStringReiksme(
										new SQLuzklausos().selectVeliausiaiPirktosPrekesAntkainioProcentaPagalPrekesKoda(
											modelisPasirinktomsPrekems.getValueAt(i, 0).toString()
										)
									)
						);
						float nuolaidosProc = Float.parseFloat(modelisPasirinktomsPrekems.getValueAt(i, 5).toString());
						
						float savikaina = Float.parseFloat(
							new DBduomenys(st).vykdykQueryIrGrazinkVienaStringReiksme(
								new SQLuzklausos().selectVeliausiaiPirktosPrekesSavikainaPagalPrekesKoda(
									modelisPasirinktomsPrekems.getValueAt(i, 0).toString()
								)
							)
						);
						
						float kainaSuAntkainiu = savikaina + (savikaina*antkainioProc/100);
						
						float kainaSuNuolaida = kainaSuAntkainiu - (kainaSuAntkainiu*nuolaidosProc/100);
						
						float antkainisParduodant = kainaSuNuolaida - savikaina;
						
						float antkainioProcentasParduodant = antkainisParduodant*100/savikaina;
						
						new DBduomenys(st, ipp.getDuomenuIrasai())
							.vykdykQuery(new SQLuzklausos().insertParduotosPrekesDuomenys(
								Integer.parseInt(numerisTxtFld.getText()), 
								parduodamosSeniausiosSandelyPrekesUUID, 
								String.valueOf(minusuojamasPrekesKiekisPagalUUID), // parduodamas prekes kiekis pagal UUID
								String.valueOf(savikaina),
								String.valueOf(antkainioProcentasParduodant)
							));
						
					}
					

				}		
			
				ipp.refreshTable();
				
			}					
			
		});
		
		pasirinktiImone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new ImonesIrasoPasirinkimoLangas(adff,
						new IrasuPerziurosPanele(st, new PirkejuAtributai(), 
								new SQLuzklausos().getVisuPirkejuDuomenys())
						);
				
			}
			
		});
		
		//
		
		issaugotiIrIsrasyti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				issaugoti.doClick();
				
				for (int i=0;i<modelisPasirinktomsPrekems.getRowCount();i++) {
					
					int perkamasPrekiuKiekis = Integer.parseInt(
						modelisPasirinktomsPrekems.getValueAt(i, 6).toString()
					);
										
					int prekesLikutis = Integer.parseInt(
						new DBduomenys(st).vykdykQueryIrGrazinkVienaStringReiksme(
							new SQLuzklausos().selectPrekesLikutisSandelyjePagalPrekesKodà(
								modelisPasirinktomsPrekems.getValueAt(i, 0).toString()
							)
						)
					);
					
					
					
					if (prekesLikutis < perkamasPrekiuKiekis) {
						new KlaidosPranesimas_Vaizdas(
							"Prekës, kurios kodas \"" + modelisPasirinktomsPrekems.getValueAt(i, 0).toString()
							+ "\" parduodamas kiekis virðija sandëlyje esantá likutá");
						return;
					}
					
				}	
				
				InvoiceDesign design = new InvoiceDesign();
				try {
					
					String invoicingDate = dataModel.getYear()+"-"+(dataModel.getMonth()+1)+"-"+dataModel.getDay();
					String susimoketiIki = apmoketiIkiModel.getYear()+"-"+(apmoketiIkiModel.getMonth()+1)+"-"+apmoketiIkiModel.getDay();
					
					Customer customer = new Customer();
					
					customer.setName("Dominykas ir ko");
					customer.setAddress("Agrastø g 10-10");
					customer.setCity("563465348");
					customer.setEmail("LT5744688");
					
					Customer customer1 = new Customer();
					
					customer1.setName(sfff.getPavadinimasFld().getText());
					customer1.setAddress(sfff.getAdresasFld().getText());
					customer1.setCity(sfff.getImonesKodasFld().getText());
					customer1.setEmail(sfff.getPvmKodasFld().getText());
					
					List<Item> items = new ArrayList<Item>();
					
					for (int i=0;i<modelisPasirinktomsPrekems.getRowCount();i++) {
						Item item = new Item();
						
						item.setDescription(modelisPasirinktomsPrekems.getValueAt(i, 2).toString());
						item.setQuantity(Integer.parseInt(
							modelisPasirinktomsPrekems.getValueAt(i, 6).toString()
						));
						item.setUnitprice(
							new BigDecimal(
								Double.parseDouble(
								modelisPasirinktomsPrekems
								.getValueAt(i, 4)
								.toString()))
							    .setScale(2, BigDecimal.ROUND_HALF_UP)
							.subtract(
								new BigDecimal(Double.parseDouble(
								modelisPasirinktomsPrekems
								.getValueAt(i, 4)
								.toString()))
							    .setScale(2, BigDecimal.ROUND_HALF_UP)
							.multiply(
								new BigDecimal(Double.parseDouble(
								modelisPasirinktomsPrekems
								.getValueAt(i, 5)
								.toString()))
							    .setScale(2, BigDecimal.ROUND_HALF_UP))
							.divide(
									new BigDecimal(100)
									.setScale(2, BigDecimal.ROUND_HALF_UP))
							)
								
							
						);
						
						items.add(item);
					}
					
					design.provideDataToInvoice(
							invoicingDate, 
							Integer.parseInt(sfff.numerisTxtFld.getText()), 
							customer, 
							sfff.serijaTxtFld.getText(), 
							customer1, 
							items, 
							susimoketiIki);
					
					JasperReportBuilder report = design.build();
					report.show(false);
				} catch (DRException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
	}
	
	protected void init() {
		
		super.init();
		
		super.serijaTxtFld.setText("DST");
		
		try {
			
			ResultSet rs = new DBduomenys(st).vykdykQueryIrGrazinkResultSet(
					new SQLuzklausos().getNaujasSaskaitosID());
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			rs.next();
			
			super.numerisTxtFld.setText(rs.getString(rsmd.getColumnName(1)));
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		super.tab1Caption = "Pirkëjas";
		super.tab2Caption = "Prekës";
		
		issaugoti = new JButton("Iðsaugoti");
		issaugotiIrIsrasyti = new JButton("Iðsaugoti ir iðraðyti");
	}
	
	protected void render() {
		
		super.render();
		
		modelisPasirinktomsPrekems.setColumnIdentifiers(new String[]{
				"Prekes kodas",
				"Bar kodas",
				"Pavadinimas",
				"Matavimo vienetas",
				"Kaina",
				"Nuolaida (%)",
				"Kiekis", 
				"PVM"
			});
		
		JPanel panel = new JPanel();
		
		panel.add(issaugoti);
		panel.add(issaugotiIrIsrasyti);
		
		
		mygtukuPanele.add(panel, BorderLayout.LINE_END);
		
	}
	
	

}
