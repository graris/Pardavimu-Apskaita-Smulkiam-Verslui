package kitosKlases;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import atributai.PirkejuAtributai;
import atributai.PrekesAtributai;
import funkcionalumas.DuomenuBaze.DBduomenys;
import funkcionalumas.DuomenuBaze.SQLuzklausos;


public class SaskaitosPateikimas extends InfoValdymas{
	
	private static Formatter rasymas;
	
	private JEditorPane saskaita= new JEditorPane();
	
	private JScrollPane  scrollpaneSaskaitai = new JScrollPane(saskaita);
	
	public void Spausdinimo_langas() {     //Israsymo_Vaizdas israsymoVaizdas) {
			
			try {
			
//			boolean complete = israsymoVaizdas.getSaskaita().print();
			
			boolean complete = saskaita.print();	
				
			if (complete) {
				
				JOptionPane.showMessageDialog(null, "Spausdinimui pasiruoðta!", "Informacija",
						JOptionPane.INFORMATION_MESSAGE);
				
			} 
				else {
					
					JOptionPane.showMessageDialog(null, "Kraunasi...", "Spausdintuvas",
							JOptionPane.ERROR_MESSAGE);

				}
			
			}
				catch (PrinterException e1) {
					
					JOptionPane.showMessageDialog(null, e1);
					
				}	
		
	}
	
	public void Saskaita (ArrayList <ArrayList<String>> DuomenysSaskaitai, String FailoVardas, Statement st) {
	
		int PasirinktosImonesVieta = 0,
			PasirinktosPrekesVieta = 0;

		double suma = 0,
			   sumaPVM = 0;
		
		ArrayList <ArrayList<String>> JusuImonesDuomenys = new ArrayList <ArrayList<String>>();
		
		ArrayList <ArrayList<String>> ImonesDuomenys = new ArrayList <ArrayList<String>>();
		
		ArrayList <ArrayList<String>> PrekesDuomenys = new ArrayList <ArrayList<String>>();
		
		int max = 0,
			min = 0;

		JusuImonesDuomenys.add(new ArrayList<String>());
		JusuImonesDuomenys.add(new ArrayList<String>());
		JusuImonesDuomenys.add(new ArrayList<String>());
		JusuImonesDuomenys.add(new ArrayList<String>());
		JusuImonesDuomenys.add(new ArrayList<String>());
		
		JusuImonesDuomenys.get(0).add("PVM");		
		JusuImonesDuomenys.get(0).add("Serija");
		JusuImonesDuomenys.get(0).add("Ámonës pavadinimas");
		JusuImonesDuomenys.get(0).add("Adresas");
		JusuImonesDuomenys.get(0).add("Ámonës kodas");
		JusuImonesDuomenys.get(1).add("20");
		JusuImonesDuomenys.get(1).add("DKO");
		JusuImonesDuomenys.get(1).add("Dominykas ir ko");
		JusuImonesDuomenys.get(1).add("Agrastø 12-13");
		JusuImonesDuomenys.get(1).add("124312312332");
		
		ImonesDuomenys = new DBduomenys(st, ImonesDuomenys).getDBlentelesDuomenys( 
				new PirkejuAtributai(), new SQLuzklausos().getVisuPirkejuDuomenys());
		
		PrekesDuomenys = new DBduomenys(st, PrekesDuomenys).getDBlentelesDuomenys(
				new PrekesAtributai(), new SQLuzklausos().getVisiPrekesDuomenys());
		
		
		String DabartineData;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		
		DabartineData = dateFormat.format(new Date());
		
		try{
			
			rasymas = new Formatter(FailoVardas);
			
		} 
			catch(FileNotFoundException e) {
				
				e.printStackTrace();
				
			}
		
		//-----------------------------------------------------------------------------------------------------
		
		for (int i=1; i<PrekesDuomenys.get(0).size(); i++) {
			
			
			
			for (int j=0; j<DuomenysSaskaitai.get(1).size(); j++) {
				
				if (PrekesDuomenys.get(0).get(i).equals("Kaina")) {
					
					PasirinktosPrekesVieta = InfoValdymas.Gauti_Duomenis_Pagal_ID(
							Integer.parseInt(DuomenysSaskaitai.get(2).get(j)), 
							PrekesDuomenys);
					
					suma += Double.parseDouble(DuomenysSaskaitai.get(1).get(j))
							*Double.parseDouble(PrekesDuomenys.get(PasirinktosPrekesVieta).get(i));
					
				}
				
			}
			
			
		}
		
		sumaPVM = suma*Double.parseDouble(JusuImonesDuomenys.get(1).get(0))/100;
		//-----------------------------------------------------------------------------------------------------		
		
		ResultSet rs = new DBduomenys(st).vykdykQueryIrGrazinkResultSet(new SQLuzklausos().getNaujasSaskaitosID());
		
		
		try {
			
			rs.next();
			
			rasymas.format( "<html>"+
							"<head>\n" +
							"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=Windows-1257\" />\n" +
							"</head>\n"+
							
							"<body>\n"+
							
							"<table width=\"350 px\"  border=\"0\">\n"+
							
							"<tr>\n"+
							"<td rowspan=\"2\"><img src=\"icon.jpg\" width=\"315px\" height= \"125px\"></img></td>\n"+
							"<td colspan=\"4\">PVM SÀSKAITA FAKTÛRA</td>\n"+
							"<td></td>\n"+
							"</tr>\n"+
							
							"<tr>\n"+
							"<td colspan=\"2\">Serija: <b>%s</b></td>\n"+  
							"<td colspan=\"2\">Nr: <b>%d</b></td>\n"+     
							"<td><b>%s</b></td>\n"+  
							"</tr>\n" +
							
							"<tr>\n" +
							"<td><u><font size=\"3\">PARDAVËJAS:</font></u></td>\n" +
							"<td colspan=\"2\"><u><font size=\"3\">PIRKËJAS:</font></u></td>\n" +
							"<td></td>\n" +
							"<td></td>\n" +
							"<td></td>\n" +
							"</tr>\n", 
			
							Koduote.KodasIRaide(JusuImonesDuomenys.get(1).get(1)), 
							rs.getInt(1) - 1, 
							DabartineData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//------------------------------------------------------------------------
		PasirinktosImonesVieta = Gauti_Duomenis_Pagal_ID(Integer.parseInt(DuomenysSaskaitai.get(0).get(0)), 
														 ImonesDuomenys);
		//------------------------------------------------------------------------
		
		if (JusuImonesDuomenys.get(0).size()-2 > ImonesDuomenys.get(0).size()-1) {
			
			max = JusuImonesDuomenys.get(0).size()-2;
			min = ImonesDuomenys.get(0).size()-1;
			
		}
			else {
				
				max = ImonesDuomenys.get(0).size()-1;
				min = JusuImonesDuomenys.get(0).size()-2;
				
			}
		
		System.out.println("MAX: "+max+". MIN: "+min);
		for (int i=0; i<max; i++) {
			
			if (i < min) {
				
				rasymas.format( "<tr>\n" +
								"<td><b><font size=\"2\">%s: %s</font></b></td>\n" +
								"<td colspan=\"2\"><b><font size=\"2\">%s</font></b></td>\n" +
								"<td><font size=\"2\">%s</font></td>\n" +
								"<td></td>\n" +
								"<td></td>\n" +
								"</tr>\n",
								
								Koduote.KodasIRaide(JusuImonesDuomenys.get(0).get(i+2)), 
								Koduote.KodasIRaide(JusuImonesDuomenys.get(1).get(i+2)), 
								Koduote.KodasIRaide(ImonesDuomenys.get(0).get(i+1)),
								Koduote.KodasIRaide(ImonesDuomenys.get(PasirinktosImonesVieta).get(i+1)));
				
			} 
				else {
					
					if (JusuImonesDuomenys.get(0).size()-2 == max) {
						
						rasymas.format( "<tr>\n" +
										"<td><b><font size=\"2\">%s: %s</font></b></td>\n" +
										"<td colspan=\"2\"></td>\n" +
										"<td></td>\n" +
										"<td></td>\n" +
										"<td></td>\n" +
										"</tr>\n",
						
										Koduote.KodasIRaide(JusuImonesDuomenys.get(0).get(i+2)),
										Koduote.KodasIRaide(JusuImonesDuomenys.get(1).get(i+2)));
						
					}
						else {

							rasymas.format( "<tr>\n" +
											"<td></td>\n" +
											"<td colspan=\"2\"><b><font size=\"2\">%s</font></b></td>\n" +
											"<td><font size=\"2\">%s</font></td>\n" +
											"<td></td>\n" +
											"<td></td>\n" +
											"</tr>\n",
											
											Koduote.KodasIRaide(ImonesDuomenys.get(0).get(i+1)),
											Koduote.KodasIRaide(ImonesDuomenys.get(PasirinktosImonesVieta).get(i+1))); 
											
						}
					
				}
			
			
		}
		
		rasymas.format( "<tr>\n" +
						"<td></td>\n" +
						"<td colspan = \"2\"><font size=\"2\">PVM %s%s - </font></td>\n" + //PVM  
						"<td><font size=\"2\">%.2f Lt</font></td>\n" +  //suma  
						"<td><font size=\"2\">Apmokëti iki:</font></td>\n" +
						"<td><font size=\"2\">%s</font></td>\n" + //data  
						"</tr>\n" +
						"</table>\n",
						
						JusuImonesDuomenys.get(1).get(0),
						"%",
						sumaPVM,
						DuomenysSaskaitai.get(3).get(0)+"."
						+DuomenysSaskaitai.get(3).get(1)+"."
						+DuomenysSaskaitai.get(3).get(2));
			//	DuomenysSaskaitai.get(index)	);DabartineData
		
		
		rasymas.format(	"<table width=\"335 px\"  border=\"1\">\n" +
						"<tr>\n" +
						"<td colspan = \"2\"><b><font size=\"4\">Pavadinimas</font></b></td>\n" +
						"<td><b><font size=\"4\">Mato vnt.</font></b></td>\n" +
						"<td><b><font size=\"4\">Kiekis</font></b></td>\n" +
						"<td><b><font size=\"4\">Kaina</font></b></td>\n" +
						"<td><b><font size=\"4\">Suma</font></b></td>\n" +
						"</tr>\n");

		
		
		for (int i=0; i<DuomenysSaskaitai.get(1).size(); i++) {
			
			PasirinktosPrekesVieta = InfoValdymas.Gauti_Duomenis_Pagal_ID(
							Integer.parseInt(DuomenysSaskaitai.get(2).get(i)),
							PrekesDuomenys);
			
			for (int j=1; j<PrekesDuomenys.get(0).size(); j++) {
				
				if (PrekesDuomenys.get(0).get(j).equals("Pavadinimas")) {
					
					rasymas.format( "<tr>\n" +
									"<td colspan = \"2\">%s</td>\n",
									Koduote.KodasIRaide(PrekesDuomenys.get(PasirinktosPrekesVieta).get(j)));
					
				}
				
			}
			
			for (int j=1; j<PrekesDuomenys.get(0).size(); j++) {
				
				if (PrekesDuomenys.get(0).get(j).equals("Matavimo vienetas")) {
					
					rasymas.format( "<td>%s</td>\n",
							Koduote.KodasIRaide(PrekesDuomenys.get(PasirinktosPrekesVieta).get(j)));
					
				}
				
			}
			
			rasymas.format( "<td>%s</td>\n",
					DuomenysSaskaitai.get(1).get(i));
			
			for (int j=1; j<PrekesDuomenys.get(0).size(); j++) {
				
				if (PrekesDuomenys.get(0).get(j).equals("Kaina")) {
					
					rasymas.format( "<td>%.2f</td>\n",
							Double.parseDouble(PrekesDuomenys.get(PasirinktosPrekesVieta).get(j)));
					
					rasymas.format( "<td>%.2f</td>\n",
							Double.parseDouble(DuomenysSaskaitai.get(1).get(i))*
							Double.parseDouble(PrekesDuomenys.get(PasirinktosPrekesVieta).get(j)));
					
				}
				
			}
			
		}
		
		rasymas.format("</table>\n");
		
		rasymas.format(	"<table width=\"360 px\"  border=\"0\">\n" +
						"<tr>\n" +
						"<td colspan = \"2\"></td>\n" +
						"<td colspan = \"3\"><font size=\"2\">Suma be PVM</font></td>\n" +
						"<td><font size=\"2\">%.2f</font></td>\n" +
						"</tr>\n",
						
						suma);
		
		rasymas.format(	"<tr>" +
						"<td colspan = \"2\"><b><font size=\"2\">Kasos aparato Nr.%d  Èekio Nr._______________</font></b></td>" +
						"<td colspan = \"3\"><font size=\"2\">PVM %s%s - </font></td>" +
						"<td><font size=\"2\">%.2f</font></td>" +
						"</tr>",
						
						123,
						JusuImonesDuomenys.get(1).get(0),
						"%",
						sumaPVM);
		
		rasymas.format(	"<tr>" +
						"<td colspan = \"2\"></td>" +
						"<td colspan = \"3\"><b><font size=\"2\">Suma apmokëjimui</font></b></td>" +
						"<td><font size=\"2\">%.2f</font></td>" +
						"</tr>",
						
						suma+sumaPVM);
		
		rasymas.format(	"<tr>" +
						"<td colspan = \"6\"></td>" +
						"</tr>");
		
		rasymas.format(	"<tr>" +
						"<td colspan = \"6\"></td>" +
						"</tr>");
		
		rasymas.format(	"<tr>" +
						"<td colspan = \"6\"></td>" +
						"</tr>");
		
		rasymas.format(	"<tr>" +
						"<td colspan = \"6\"></td>" +
						"</tr>");
		
		rasymas.format(	"<tr>" +
						"<td colspan = \"6\"></td>" +
						"</tr>");
		
		rasymas.format(	"<tr>" +
						"<td colspan = \"3\"><font size=\"2\">Sàskaità iðraðë:</font></td>" +
						"<td colspan = \"3\"><font size=\"2\">Prekes gavo:</font></td>" +
						"</tr>");
		
		rasymas.format(	"<tr>" +
						"<td colspan = \"6\"></td>" +
						"</tr>");
		
		rasymas.format(	"<tr>" +
						"<td colspan = \"3\">_________________________</td>" +
						"<td colspan = \"3\">_________________________</td>" +
						"</tr>");

		rasymas.format(	"<tr>" +
				"<td colspan = \"3\"><font size=\"2\">Darbuotojas %s</font></td>" +
				"<td colspan = \"3\"><font size=\"2\">(pareigos, vardas, pavardë, paraðas)</font></td>" +
				"</tr>",
				
				"Fanfanas Tulpë");
		
		rasymas.format("</table>\n" +
					   "</body>\n" +
					   "</html>\n");
		
		rasymas.close();
		
	}
	
}
