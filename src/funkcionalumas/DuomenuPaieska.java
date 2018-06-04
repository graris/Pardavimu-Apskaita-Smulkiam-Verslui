package funkcionalumas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import kitosKlases.InfoValdymas;
import kitosKlases.Koduote;

//----------------------------------------------------------------------------------
//##################################################################################
//----------------------------------------------------------------------------------
//  Sioje klaseje talpinami metodai skirti elementu paieskai vykdyti 
//----------------------------------------------------------------------------------
//##################################################################################
//----------------------------------------------------------------------------------
public class DuomenuPaieska {

	//#######################################################################################
	// Sis metodas paima visas imanomas kiekvieno imones/prekes duomens atkarpas ir palygina
	// su ivestu vienu kriteriju (isskyrus kainos kriterijumi) ir radus sutapimus tos 
	// imone/prekes duomenis iveda i lista
	//#######################################################################################
	private static ArrayList<ArrayList <String>> Paieskos_Algoritmas(ArrayList <ArrayList <String>> duomenys, 
			JTextField PaieskosLaukelis, int sk) {
		
		ArrayList <ArrayList <String>> FiltravimoListas = new ArrayList <ArrayList <String>>();
		
		if ((!PaieskosLaukelis.getText().equals(""))&&(!PaieskosLaukelis.getText().equals(duomenys.get(0).get(sk)))) {
			
			for (int i=1; i< duomenys.size(); i++) {			
				
				for (int j = 0; j<Koduote.KodasIRaide(duomenys.get(i).get(sk)).length(); j++) {
				
					for (int k = j+1; k<Koduote.KodasIRaide(duomenys.get(i).get(sk)).length()+1; k++) {
						
							if(Koduote.KodasIRaide(duomenys.get(i).get(sk)).substring(j, k).toLowerCase().equals(PaieskosLaukelis.getText().toLowerCase())) {
								
								int sutampa = 0;
								
								for (int l = 0; l<FiltravimoListas.size(); l++) {
									
									if (duomenys.get(i).get(0).equals(FiltravimoListas.get(l).get(0))) {
										
										sutampa++;
										
									}
									
								}
								
								if (sutampa==0) { 
									
									FiltravimoListas.add(new ArrayList<String>());
									
									for (int l = 0; l<duomenys.get(0).size(); l++) {
										
										FiltravimoListas.get(FiltravimoListas.size()-1).add(duomenys.get(i).get(l));
										
									}
									
								}
								
							} 
						
					
					
					}
				
				}
				
				
				
			}
		
		} 
			else {
				
				for (int i=1; i<duomenys.size(); i++) {
					
					FiltravimoListas.add(new ArrayList<String>());
				//	System.out.println(PaieskosLaukelis.getText());
					for (int j = 0; j<duomenys.get(0).size(); j++) {
						
						FiltravimoListas.get(FiltravimoListas.size()-1).add(duomenys.get(i).get(j));
					
						
						
					}
					
				}
					
			}
		return FiltravimoListas;
		
	}
	
	
	//#######################################################################################
	// Sis metodas paima kiekvienos prekes kainos reikme ir palygina su ivestais kainos 
	// kriterijais bei tenkinacios kriterijus prekes duomenis iveda i lista
	//#######################################################################################
	private static ArrayList <ArrayList <String>> Paieskos_Algoritmas_Intervalams(ArrayList <ArrayList <String>> duomenys,
			 int vieta, String nuo, 
			String iki, JTextField PaieskosLaukelisNuo, JTextField PaieskosLaukelisIki) {						
			
		ArrayList <ArrayList <String>> FiltravimoListas = new ArrayList <ArrayList <String>>();
		
		if (!nuo.equals("Nuo")&&!iki.equals("Iki")&&!nuo.equals("")&&!iki.equals("")) {
			
			for (int i=1; i<duomenys.size(); i++) {
				
				if (Double.parseDouble(duomenys.get(i).get(vieta))>=
					Double.parseDouble(PaieskosLaukelisNuo.getText())&&
					Double.parseDouble(duomenys.get(i).get(vieta))<=
					Double.parseDouble(PaieskosLaukelisIki.getText())) {
				
					FiltravimoListas.add(new ArrayList<String>());
					
					for (int j=0; j<duomenys.get(0).size(); j++) {
						
						FiltravimoListas.get(FiltravimoListas.size()-1).add(duomenys.get(i).get(j));
						
					}
				
				}
			
			}
			
		}
			else if ((nuo.equals("Nuo")||nuo.equals(""))&&
					!iki.equals("Iki")&&!iki.equals("")) {
				
				for (int i=1; i<duomenys.size(); i++) {
					
					if (Double.parseDouble(duomenys.get(i).get(vieta))<=
						Double.parseDouble(PaieskosLaukelisIki.getText())) {
					
						FiltravimoListas.add(new ArrayList<String>());
						
						for (int j=0; j<duomenys.get(0).size(); j++) {
							
							FiltravimoListas.get(FiltravimoListas.size()-1).add(duomenys.get(i).get(j));
							
						}
					
					}
				
				}
				
			}
				else if ((!nuo.equals("Nuo")&&!nuo.equals(""))&&
						(iki.equals("Iki")||iki.equals(""))) {
					
					for (int i=1; i<duomenys.size(); i++) {
						
						if (Double.parseDouble(duomenys.get(i).get(vieta))>=
							Double.parseDouble(PaieskosLaukelisNuo.getText())) {
						
							FiltravimoListas.add(new ArrayList<String>());
							
							for (int j=0; j<duomenys.get(0).size(); j++) {
								
								FiltravimoListas.get(FiltravimoListas.size()-1).add(duomenys.get(i).get(j));
								
							}
						
						}
					
					}
					
				}
					else if (((nuo.equals("Nuo"))&&(iki.equals("Iki")))||((nuo.equals(""))&&(iki.equals(""))) 
							||((nuo.equals("Nuo"))&&(iki.equals("")))||((nuo.equals(""))&&(iki.equals("Iki"))) ) {
						
						for (int i=1; i<duomenys.size(); i++) {
							
							FiltravimoListas.add(new ArrayList<String>());
							
							for (int j=0; j<duomenys.get(0).size(); j++) {
								
								FiltravimoListas.get(FiltravimoListas.size()-1).add(duomenys.get(i).get(j));
								
							}
						
						}
						
					}
		
		return FiltravimoListas;
		
	}	
	
	
	//#######################################################################################
	// Sis metodas suraso i viena lista visus listus, kuriuose talpinami prafiltruoti
	// prekes/imones duomenys pagal kiekviena kriteriju (atskirai)
	//#######################################################################################
	private static ArrayList <ArrayList<ArrayList<String>>> Paieskos_procesas(ArrayList <JTextField> TekstoLaukai, 
			ArrayList <ArrayList <String>> duomenys) {
		
		ArrayList <ArrayList<ArrayList<String>>> FiltruotiDuomenys = new ArrayList<ArrayList<ArrayList<String>>>();
		
		for (int i=0; i<duomenys.get(0).size(); i++) {
			
			if (!duomenys.get(0).get(i).equals("Kaina")) {
				
				FiltruotiDuomenys.add(Paieskos_Algoritmas(duomenys,
				TekstoLaukai.get(i), i));
			}	
				else {

					 FiltruotiDuomenys.add(Paieskos_Algoritmas_Intervalams(duomenys, i, TekstoLaukai.get(i).getText(), 
					TekstoLaukai.get(i+1).getText(), TekstoLaukai.get(i), TekstoLaukai.get(i+1)));		
				}
			
		}
		
		return FiltruotiDuomenys;
	
	}
	
	
	//#######################################################################################
	// Sis metodas paima lista'a kuriame yra listai pilni prafiltruotu duomenu pagal 
	// kiekviena kriteriju (atskirai) ir randa visu tu listu bendrus duomenis. Tuos
	// duomenis suraso i viena lista (taip pat i ji suraso ir duomenu rusiu pavadinimus) ir 
	// taip paruosia lista isvedimui i lentele...
	//#######################################################################################	
	private static ArrayList <ArrayList<String>> PrafiltruotiDuomenysIsvedimui(
			ArrayList <ArrayList <String>> duomenys,
			ArrayList <ArrayList<ArrayList<String>>> FiltruotiDuomenys) {
		
		ArrayList <ArrayList<String>> GautiDuomenys = new ArrayList <ArrayList<String>>();
		
		int pasikartoja = 1;
		
		GautiDuomenys.add(new ArrayList <String>());
		
		for (int i=0; i<duomenys.get(0).size(); i++) {
			
			GautiDuomenys.get(0).add(duomenys.get(0).get(i));
			
		}
		
		for (int i=0; i<FiltruotiDuomenys.get(0).size(); i++) {
			
			for (int j=1; j<FiltruotiDuomenys.size(); j++) {
				
				for (int k=0; k<FiltruotiDuomenys.get(j).size(); k++) {
					
					if (FiltruotiDuomenys.get(0).get(i).get(0).equals(
							FiltruotiDuomenys.get(j).get(k).get(0))) {
						
						pasikartoja++;
						
						System.out.println(FiltruotiDuomenys.get(0).get(i).get(0) + 
								"Prideda" + FiltruotiDuomenys.get(j).get(k).get(0));
					}
					
				}
				
			}
			if (pasikartoja == GautiDuomenys.get(0).size()) {
				
				GautiDuomenys.add(FiltruotiDuomenys.get(0).get(i));
				
			}
			
			pasikartoja = 1;
			
		}
		return GautiDuomenys;
		
	}

	
	//#######################################################################################
	// Sis metodas naudodamas metodus "addMouseListener" ir "getDocument" bei pritaikydamas
	// beveik visus sioje klaseje esancius metodus suteikia teksto laukams veiksmingumo
	//#######################################################################################	
	public static void Paieskos_Laukas(final ArrayList <ArrayList <String>> duomenys,
			final ArrayList <JTextField> TekstoLaukai,
			final DefaultTableModel LentelesModelis, 
			final JTextField TekstoLaukas, final String LaukoTekstas) {
		
		TekstoLaukas.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {

				if (TekstoLaukas.getText().equals(LaukoTekstas)) {
					
					TekstoLaukas.setText("");
					
				}
				
			}
			
		});
		
		TekstoLaukas.getDocument().addDocumentListener(new DocumentListener() {

			  public void changedUpdate(DocumentEvent e) {
				 
				  new InfoValdymas().Surasymas_i_lentele(PrafiltruotiDuomenysIsvedimui(duomenys,
					  Paieskos_procesas(TekstoLaukai, duomenys)), 
					  LentelesModelis);
				  
			  }
			  
			  public void removeUpdate(DocumentEvent e) {
				  new InfoValdymas().Surasymas_i_lentele(PrafiltruotiDuomenysIsvedimui(duomenys,
					  Paieskos_procesas(TekstoLaukai, duomenys)), 
					  LentelesModelis);

			  }
			  
			  public void insertUpdate(DocumentEvent e) {
				  
				  new InfoValdymas().Surasymas_i_lentele(PrafiltruotiDuomenysIsvedimui(duomenys,
					  Paieskos_procesas(TekstoLaukai, duomenys)), 
					  LentelesModelis);
					  
			  }
	
	
		});
		
	}
	
}
