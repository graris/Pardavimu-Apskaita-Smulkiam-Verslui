package kitosKlases;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.StringTokenizer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import atributai.PirkejuAtributai;
import atributai.PrekesAtributai;
import funkcionalumas.DuomenuBaze.DBduomenys;
import funkcionalumas.DuomenuBaze.SQLuzklausos;


//----------------------------------------------------------------------------------
//##################################################################################
//----------------------------------------------------------------------------------
//  Sioje klaseje talpinami metodai skirti ivariems veiksmams su duomenimis atlikti
//  (perskaityti is failo, irasyti i faila, taip pat - salinti, pakeisti ir prideti
//  duomenis, priskirti duomenims identifikacijos koda ir t.t)
//----------------------------------------------------------------------------------
//##################################################################################
//----------------------------------------------------------------------------------
public class InfoValdymas {
	
	public void Surasymas_i_lentele(ArrayList <ArrayList<String>> duomenys, DefaultTableModel modelis) {
		
		modelis.setColumnCount(0); 
		
		modelis.setRowCount(0); 
			
		ArrayList <String> stulpelis = new ArrayList <String>();
		
		for(int i = 0; i < duomenys.get(0).size(); i++) {
			if (!(duomenys.get(0).get(i).equals("APMOKETA") || duomenys.get(0).get(i).equals("Apmokëta"))) {
				for (int j = 1; j < duomenys.size(); j++) {
					
					stulpelis.add(duomenys.get(j).get(i));
					
				}
	
				modelis.addColumn(duomenys.get(0).get(i), stulpelis.toArray());
				//
	//			
	//			
	//			ArrayList <String> boolStulpelis = new ArrayList <String>();
	//			
	//			
	//			}
	//			
	//			Boolean.parseBoolean(s)
				//			
				stulpelis.clear();
				
			}
			else {
				ArrayList <Boolean> boolStulpelis = new ArrayList <Boolean>();
				for (int j = 1; j < duomenys.size(); j++) {
					
					if (duomenys.get(j).get(i).equals("1"))
						boolStulpelis.add(true);
					else
						boolStulpelis.add(false);	
					
				}
				
				modelis.addColumn(duomenys.get(0).get(i), boolStulpelis.toArray());
				
				boolStulpelis.clear();
				
			}
			
		}
		
	}


	//#########################################################################################
	//  Sis metodas suranda sutampancius duomenis su pasirinktais lenteleje ir grazina 
	//  ju ID
	//#########################################################################################
	public static long Identisko_duomens_paieska(ArrayList <ArrayList<String>> Duomenys, JTable lentele, 
			int pasirinkta_eile) {
		
		long elemento_vieta = 0; 
		
		int	pasikartojimas = 0,
			pasikartojimu_sk = 0;
		
		boolean rasta = false;
		
		
		for (int i=1; i<Duomenys.size(); i++) {
			
			for (int j=0; j<Duomenys.get(0).size(); j++) {
				
				for (int k=0; k<Duomenys.get(0).size(); k++) {
					
					if (!rasta) {
							
						if (lentele.getModel().getValueAt(pasirinkta_eile, k).toString().equals(Duomenys.get(i).get(j))) {
							
							pasikartojimas++;		
							
						}
					
						if (pasikartojimas>0) {
							
							pasikartojimu_sk++;
							
							pasikartojimas = 0;
							
						}
						
						if (pasikartojimu_sk == Duomenys.get(0).size()){
							
							elemento_vieta = Long.parseLong(Duomenys.get(i).get(0));
							
							rasta = true;
							
						}
							
					}
				
				}
			
			}

			pasikartojimu_sk = 0;
			
		}
		
		return elemento_vieta;
		
	}
	
	//#########################################################################################
	//  Metodas grazinantis ieskomu duomenu vietos numeri nagrinejamame liste
	//#########################################################################################
	public static int Gauti_Duomenis_Pagal_ID(int id, ArrayList <ArrayList<String>> listas) {
	
		int vieta = 0;
		
		for (int i=1; i<listas.size(); i++) {
			
			if (Integer.parseInt(listas.get(i).get(0)) == id) {
				
				vieta = i;

			}
			
		}
		
		
		return vieta;
		
	}
	
	public ArrayList<String> Gauti2DListStulpeli(ArrayList< ArrayList<String> > list2D, int stulpNr) {
		
		ArrayList<String> stulpElementuList = new ArrayList<String>();
		
		for(int i = 0; i<list2D.get(stulpNr).size(); i++) {
			
			stulpElementuList.add(list2D.get(i).get(stulpNr));
			
		}
		
		return stulpElementuList;

	}
	
}
