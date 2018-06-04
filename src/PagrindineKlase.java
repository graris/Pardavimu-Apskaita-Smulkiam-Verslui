import java.sql.SQLException;
import java.sql.Statement;

import funkcionalumas.DuomenuBaze.Prisijungimas_Prie_DB;
import gui.Pagrindinis_Langas;

// Dominykas Stakutis Prif-14/2

public class PagrindineKlase {

	public static void main(String[] args) {

		Statement st = null;
		try {
			st = new Prisijungimas_Prie_DB().GautiPrisijungima().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Pagrindinis_Langas pagrLangas = new Pagrindinis_Langas(st);
		
		
		
	}
	
}
