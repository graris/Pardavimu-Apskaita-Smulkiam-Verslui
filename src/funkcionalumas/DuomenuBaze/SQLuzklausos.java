package funkcionalumas.DuomenuBaze;

public class SQLuzklausos {
	
	private String visiPrekesDuomenys = "SELECT * FROM preke;";
	
	private String visuPirkejuDuomenys = "SELECT * FROM pirkejas;";

	private String visuTiekejuDuomenys = "SELECT * FROM tiekejas;";
	
	private String visiSaskaitosDuomenys = "SELECT * FROM saskaita_faktura;";
	
	private String visiPajamavimuDuomenys = "SELECT * FROM pajamavimo_dokumentas;";
	

	public String getVisiPrekesDuomenys() {
		return visiPrekesDuomenys;
	}

	public String getVisuPirkejuDuomenys() {
		return visuPirkejuDuomenys;
	}

	public String getVisuTiekejuDuomenys() {
		return visuTiekejuDuomenys;
	}
	
	public String getVisiSaskaitosDuomenys() {
		return visiSaskaitosDuomenys;
	}
	
	public String getVisiPajamavimuDuomenys() {
		return visiPajamavimuDuomenys;
	}
	
	public String getSomePrekiuTblColumns() {
		
		return "SELECT *" + 
				"FROM INFORMATION_SCHEMA.COLUMNS" + 
				"WHERE TABLE_NAME = N'PREKE'";
	}
	
	
	public String selectPrekesGrupuojantPagalPrekesKoda() {
		
		return "SELECT" +
			   "  PREKES_KODAS, " + 
			   "  PAVADINIMAS, " + 
			   "  SUM(LIKUTIS) AS 'LIKUTIS' " + 
			   "FROM " + 
			   "  preke " + 
			   "GROUP BY " + 
			   "  PREKES_KODAS;";
	}
	
	public String selectPrekesPasirinkimuiIsrasantSaskaitaiArbaPajamuojant() {
		
		return "SELECT " + 
				"  PREKES_KODAS, " + 
				"  BAR_KODAS," + 
				"  PAVADINIMAS," + 
				"  SUM(LIKUTIS) AS LIKUTIS," + 
				"  ROUND(PIRKIMO_KAINA, 2) AS PIRKIMO_KAINA," + 
				"  ROUND((PIRKIMO_KAINA + (PIRKIMO_KAINA * ANTKAINIO_PROCENTAS / 100)), 2) AS \"Pardavimo kaina\" " + 
				"FROM " + 
				"  `preke` " + 
				"WHERE " + 
				"  LIKUTIS > 0 " + 
				"GROUP BY" + 
				"  PREKES_KODAS" + 
				"  , BAR_KODAS";
	}	
	
	public String selectPrekesPasirinkimuiIsrasantSaskaitai() {
		
		return "SELECT " + 
				"  PREKES_KODAS, " + 
				"  BAR_KODAS," + 
				"  PAVADINIMAS," + 
				"  SUM(LIKUTIS) AS LIKUTIS," +  
				"  ROUND((PIRKIMO_KAINA + (PIRKIMO_KAINA * ANTKAINIO_PROCENTAS / 100)), 2) AS Kaina " + 
				"FROM " + 
				"  `preke` " + 
				"WHERE " + 
				"  LIKUTIS > 0 " + 
				"GROUP BY" + 
				"  PREKES_KODAS" + 
				"  , BAR_KODAS";
	}	
	
	public String selectVisusPrekesLikuciuDuomenisPagalPrekesKoda(String PREKES_KODAS) {
		return "SELECT " + 
				"  likutis, " + 
				"  pirkimo_kaina, " + 
				"  pirkimo_data, " + 
				"  tiekejo_imones_kodas " +
				"FROM " + 
				"  preke " + 
				"WHERE " + 
				"  PREKES_KODAS =" + PREKES_KODAS + " " + 
				"ORDER BY "+ 
				"  pirkimo_data ASC;";
	}
	
	public String selectPrekesPirkimusIrPardavimusPagalPrekesKoda(String PREKES_KODAS) {
		return  "SELECT " + 
				"  \"Pardavimas\" AS Operacija, " + 
				"  saskaita_faktura.pirkimo_data AS Data, " + 
				"  parduota_preke.saskaitos_id AS \"Dokumento Nr.\", " + 
				"  pirkejo_imones_kodas AS \"Pirkëjas arba Tiekëjas\", " + 
				"  parduota_preke.kiekis AS Kiekis, " + 
				"  preke.likutis AS Likutis " + 
				"FROM " + 
				"  parduota_preke " + 
				"JOIN " + 
				"  saskaita_faktura " + 
				"ON " + 
				"  (saskaita_faktura.saskaitos_id = parduota_preke.saskaitos_id) " + 
				"JOIN " + 
				"  preke " + 
				"ON " + 
				"  (preke.UUID = parduota_preke.UUID)  " + 
				"WHERE " + 
				"  preke.PREKES_KODAS = " + PREKES_KODAS +" " + 
				"UNION   " + 
				"SELECT " + 
				"  \"Pirkimas\" AS Operacija, " + 
				"  pajamavimo_dokumentas.pirkimo_data AS Data, " + 
				"  pirkta_preke.pajamavimo_dok_id AS \"Dokumento Nr.\",        " + 
				"  pajamavimo_dokumentas.tiekejo_imones_kodas AS \"Pirkëjas arba Tiekëjas\", " + 
				"  pirkta_preke.kiekis AS Kiekis, " + 
				"  preke.likutis AS Likutis " + 
				"FROM " + 
				"  pirkta_preke " + 
				"JOIN " + 
				"  pajamavimo_dokumentas " + 
				"ON " + 
				"  (pirkta_preke.serija = pajamavimo_dokumentas.serija) " + 
				"  AND (pirkta_preke.pajamavimo_dok_id = pajamavimo_dokumentas.pajamavimo_dok_id) " + 
				"JOIN " + 
				"  preke " + 
				"ON " + 
				"  (preke.uuid = pirkta_preke.uuid) " + 
				"WHERE " + 
				"  preke.PREKES_KODAS = " + PREKES_KODAS + ";";
	}	
	
	public String selectUUID() {
		return "SELECT " + 
				"  UUID();";
	}	
	
	public String insertPrekesDuomenys(
		String UUID,
		String PREKES_KODAS, 
		String BAR_KODAS, 
		String PAVADINIMAS, 
		String MATAVIMO_VIENETAS,
		String PIRKIMO_KAINA, 
		String ANTKAINIO_PROCENTAS, 
		String LIKUTIS, 
		String PIRKIMO_DATA,
		String TIEKEJO_IMONES_KODAS, 
		String PVM) {
		
		return "INSERT INTO `preke`(" + 
				"    `UUID`, " + 
				"    `PREKES_KODAS`, " + 
				"    `BAR_KODAS`, " + 
				"    `PAVADINIMAS`, " + 
				"    `MATAVIMO_VIENETAS`, " + 
				"    `PIRKIMO_KAINA`, " + 
				"    `ANTKAINIO_PROCENTAS`, " + 
				"    `LIKUTIS`, " + 
				"    `PIRKIMO_DATA`, " + 
				"    `TIEKEJO_IMONES_KODAS`, " + 
				"    `PVM`) " + 
				"VALUES (" + "\'"  
				+    UUID + "\' " + ","  
				+    PREKES_KODAS + ","  
				+    BAR_KODAS  + "," + "\'" 
				+    PAVADINIMAS + "\'" + "," + "\'"  
				+    MATAVIMO_VIENETAS + "\'" + ","  
				+    PIRKIMO_KAINA + ","  
				+    ANTKAINIO_PROCENTAS + "," 
				+    LIKUTIS + "," + "\'"
				+    PIRKIMO_DATA + "\'" + "," 
				+    TIEKEJO_IMONES_KODAS + "," 
				+    PVM + ");";
	}

	public String insertPirkejoDuomenys(String IMONES_KODAS, String PAVADINIMAS, String PVM_KODAS, 
			String ADRESAS, String EL_PASTO_ADRESAS) {
		return "INSERT INTO pirkejas"
				+ "(IMONES_KODAS, PAVADINIMAS, PVM_KODAS, ADRESAS, EL_PASTO_ADRESAS)"
				+ "VALUES"
				+ "("+ IMONES_KODAS +", \'"+ PAVADINIMAS +"\', \'"+ PVM_KODAS +"\', \'"
				+ ADRESAS +"\', \'"+ EL_PASTO_ADRESAS +"\');";
	}	

	public String insertTiekejoDuomenys(String IMONES_KODAS, String PAVADINIMAS, String PVM_KODAS, 
			String ADRESAS, String EL_PASTO_ADRESAS) {
		return "INSERT INTO tiekejas"
				+ "(IMONES_KODAS, PAVADINIMAS, PVM_KODAS, ADRESAS, EL_PASTO_ADRESAS)"
				+ "VALUES"
				+ "("+ IMONES_KODAS +", \'"+ PAVADINIMAS +"\', \'"+ PVM_KODAS +"\', \'"
				+ ADRESAS +"\', \'"+ EL_PASTO_ADRESAS +"\');";
	}
	
	public String insertSaskaitosDuomenys(int SASKAITOS_ID, int DARBUOTOJO_ID, String PIRKEJO_IMONES_KODAS, 
			int FILIALO_ID, String PIRKIMO_DATA, String APMOKEJIMO_DATA, int KASOS_NR
			, String PVM, int APMOKETA) {
		
		return 	  "INSERT INTO saskaita_faktura"
				+ "("
					+ "SASKAITOS_ID, "
					+ "DARBUOTOJO_ID, "
					+ "PIRKEJO_IMONES_KODAS, "
					+ "FILIALO_ID, "
					+ "PIRKIMO_DATA,"
					+ "APMOKEJIMO_DATA, "
					+ "KASOS_NR, "
					+ "PVM,"
					+ "APMOKETA"
				+ ")"
				+ "VALUES"
				+ "("
					+ SASKAITOS_ID +","
					+ DARBUOTOJO_ID +","
					+ PIRKEJO_IMONES_KODAS +","
					+ FILIALO_ID +"," + "\'"
					+ PIRKIMO_DATA +"\', \'"
					+ APMOKEJIMO_DATA +"\', "
					+ KASOS_NR +", "
					+ PVM + ","
					+ APMOKETA 
				+")";	 
	}

	public String insertPajamavimuDuomenys(String SERIJA, int PAJAMAVIMO_DOK_ID, String TIEKEJO_IMONES_KODAS, 
			int FILIALO_ID, String PIRKIMO_DATA, String APMOKEJIMO_DATA, String PVM, int APMOKETA) {
		
		return 	  "INSERT INTO pajamavimo_dokumentas"
				+ "("
					+ "SERIJA, "
					+ "PAJAMAVIMO_DOK_ID, "
					+ "TIEKEJO_IMONES_KODAS, "
					+ "FILIALO_ID, "
					+ "PIRKIMO_DATA,"
					+ "APMOKEJIMO_DATA, "
					+ "PVM,"
					+ "APMOKETA"
					+ ")"
					+ "VALUES"
				+ "(" + "\'"
					+ SERIJA + "\'" +","
					+ PAJAMAVIMO_DOK_ID +","
					+ TIEKEJO_IMONES_KODAS +","
					+ FILIALO_ID +"," + "\'"
					+ PIRKIMO_DATA +"\', \'"
					+ APMOKEJIMO_DATA +"\', "
					+ PVM + ","
					+ APMOKETA
				+")";	 
	}
	
	public String selectSeniausiaiPirktosDarLikusiosSandelyPrekesUUIDpagalPrekesKoda(String prekes_kodas) {
		
		return  "SELECT  " + 
				"  uuid  " + 
				"FROM  " + 
				"  preke  " + 
				"WHERE  " + 
				"  likutis <> 0   " + 
				"  AND prekes_kodas = "+ prekes_kodas +" " + 
				"ORDER BY  " + 
				"  pirkimo_data ASC LIMIT 1;";
	}

	public String selectVeliausiaiPirktosPrekesSavikainaPagalPrekesKoda(String prekes_kodas) {
		
		return  "SELECT " + 
				"  savikaina " + 
				"FROM " + 
				"  ( " + 
				"    SELECT " + 
				"      MAX(pirkimo_kaina) AS savikaina,  " + 
				"      pirkimo_data " + 
				"    FROM " + 
				"      preke     " + 
				"    WHERE     " + 
				"      prekes_kodas = " + prekes_kodas + 
				"    GROUP BY " + 
				"      pirkimo_data " + 
				"  ) Q1 " + 
				"ORDER BY     " + 
				"  pirkimo_data DESC LIMIT 1;";
	}
	
	public String selectVeliausiaiPirktosPrekesAntkainioProcentaPagalPrekesKoda(String prekes_kodas) {
		
		return  "SELECT " + 
				"  antkainio_procentas " + 
				"FROM " + 
				"  ( " + 
				"    SELECT " + 
				"      MAX(pirkimo_kaina) AS savikaina,  " + 
				"      antkainio_procentas, " + 
				"      pirkimo_data " + 
				"    FROM " + 
				"      preke     " + 
				"    WHERE     " + 
				"      prekes_kodas =  " + prekes_kodas + " " + 
				"    GROUP BY " + 
				"      pirkimo_data " + 
				"  ) Q1 " + 
				"ORDER BY     " + 
				"  pirkimo_data DESC LIMIT 1;";
	}
	
	public String selectPrekesLikutisSandelyPagalUUID(String uuid) {
		
		return  "SELECT  " + 
				"  likutis  " + 
				"FROM  " + 
				"  preke  " + 
				"WHERE  " + 
				"  uuid = \""+ uuid +"\";";
	}	
	
	public String selectPrekesLikutisSandelyjePagalPrekesKodà(String prekes_kodas) {
		
		return "SELECT  " + 
				"  SUM(likutis)  " + 
				"FROM  " + 
				"  preke  " + 
				"WHERE  " + 
				"  prekes_kodas = " +  prekes_kodas +";";
	}
	
	public String insertParduotosPrekesDuomenys(int SASKAITOS_ID, String UUID, String KIEKIS, String SAVIKAINA, String ANTKAINIO_PROCENTAS) {
				
		return 	"INSERT INTO parduota_preke " + 
				"  ( " + 
				"    `SASKAITOS_ID`, " + 
				"    `UUID`,  " + 
				"    `KIEKIS`,  " + 
				"    `SAVIKAINA`, " +
				"    `ANTKAINIO_PROCENTAS` " + 
				"  )  " + 
				"VALUES  " + 
				"  ( " + 
				"  " + SASKAITOS_ID + ", " + 
				"\"" + UUID + "\", " + 
				"  " + KIEKIS + ", " + 
				"  " + SAVIKAINA + ", " + 
				"  " + ANTKAINIO_PROCENTAS + " " + 
				"  );";	 
	
	}
	
	public String insertPirktosPrekesDuomenys(String SERIJA, int PAJAMAVIMO_DOK_ID, String UUID, String KIEKIS, String SAVIKAINA, String ANTKAINIO_PROCENTAS) {
		
		return 	"INSERT INTO pirkta_preke " + 
				"  ( " + 
				"    `SERIJA`, " +
				"    `PAJAMAVIMO_DOK_ID`, " + 
				"    `UUID`,  " + 
				"    `KIEKIS`,  " + 
				"    `SAVIKAINA`, " +
				"    `ANTKAINIO_PROCENTAS` " + 
				"  )  " + 
				"VALUES  " + 
				"  ( " + 
				"\"" + SERIJA + "\", " + 
				"  " + PAJAMAVIMO_DOK_ID + ", " + 
				"\"" + UUID + "\", " + 
				"  " + KIEKIS + ", " + 
				"  " + SAVIKAINA + ", " + 
				"  " + ANTKAINIO_PROCENTAS + " " + 
				"  );";	 
	
	}
	
	public String updatePrekesLikutisPagalUUID(int likutis, String uuid) {
		
		return  "UPDATE  " + 
				"  preke  " + 
				"SET  " + 
				"  likutis = "+ likutis + " " + 
				"WHERE  " + 
				"  uuid = \""+ uuid +"\";";
	}
	
	public String updatePrekesDuomenys(String ESAMAS_BAR_KODAS, String BAR_KODAS, String PAVADINIMAS, 
			String MATAVIMO_VIENETAS, String KAINA) {
		//String naujuDuomenuIvedimas = 
		
		return "UPDATE preke SET "
				+ "BAR_KODAS = "+ BAR_KODAS + ", "
				+ "PAVADINIMAS = \'" + PAVADINIMAS + "\', "
				+ "MATAVIMO_VIENETAS = \'"+ MATAVIMO_VIENETAS +"\', "
				+ "KAINA =  " + KAINA 
				+ " WHERE "
				+ "BAR_KODAS = " + ESAMAS_BAR_KODAS +";";

	}

	public String updatePirkejoDuomenys(String ESAMAS_IMONES_KODAS, String IMONES_KODAS, String PAVADINIMAS, String PVM_KODAS, 
			String ADRESAS, String EL_PASTO_ADRESAS) {
		return "UPDATE pirkejas SET "
				+ "IMONES_KODAS = "+ IMONES_KODAS +", "
				+ "PAVADINIMAS = \'"+ PAVADINIMAS +"\', "
				+ "PVM_KODAS = \'"+ PVM_KODAS +"\', "
				+ "ADRESAS = \'" + ADRESAS +"\', "
				+ "EL_PASTO_ADRESAS = \'"+ EL_PASTO_ADRESAS +"\'"
				+ " WHERE "
				+ "IMONES_KODAS = "+ ESAMAS_IMONES_KODAS +";";
	}	

	public String updateTiekejoDuomenys(String ESAMAS_IMONES_KODAS, String IMONES_KODAS, String PAVADINIMAS, String PVM_KODAS, 
			String ADRESAS, String EL_PASTO_ADRESAS) {
		return "UPDATE tiekejas SET "
				+ "IMONES_KODAS = "+ IMONES_KODAS +", "
				+ "PAVADINIMAS = \'"+ PAVADINIMAS +"\', "
				+ "PVM_KODAS = \'"+ PVM_KODAS +"\', "
				+ "ADRESAS = \'" + ADRESAS +"\', "
				+ "EL_PASTO_ADRESAS = \'"+ EL_PASTO_ADRESAS +"\'"
				+ " WHERE "
				+ "IMONES_KODAS = "+ ESAMAS_IMONES_KODAS +";";
	}
	
	public String updateApmoketiSaskaitaFaktura(String saskaitos_id) {
		
		return  "UPDATE  " + 
				"  saskaita_faktura  " + 
				"SET  " + 
				"  apmoketa = 1 " + 
				"WHERE  " + 
				"  saskaitos_id = " + saskaitos_id + ";";
	}
	
	public String updateApmoketiPajamavimoDok(String serija, String pajamavimo_dok_id) {
		
		return  "UPDATE  " + 
				"  pajamavimo_dokumentas  " + 
				"SET  " + 
				"  apmoketa = 1 " + 
				"WHERE  " + 
				"  serija = \""+ serija +"\" "+ 
				"  AND pajamavimo_dok_id = "+ pajamavimo_dok_id +";";
	}
	
	public String deletePrekesDuomenys(String BAR_KODAS) {
		
		return 	  "DELETE from PREKE"
				+ " WHERE BAR_KODAS =" + BAR_KODAS +";";	 
	
	}	
	
	public String deletePirkejoDuomenys(String IMONES_KODAS) {
		
		return 	  "DELETE FROM pirkejas"
				+ " WHERE IMONES_KODAS = " + IMONES_KODAS +";";	 
	
	}
	
	public String deleteTiekejoDuomenys(String IMONES_KODAS) {
		
		return 	  "DELETE FROM tiekejas"
				+ " WHERE IMONES_KODAS = " + IMONES_KODAS +";";	 
	
	}
	
	public String getNaujasSaskaitosID() {
		
		return "SELECT " + 
				"  (CASE " + 
				"    WHEN MAX(saskaitos_id)+1 IS NULL THEN 1 " + 
				"    ELSE MAX(saskaitos_id)+1 " + 
				"  END) AS new_saskaitos_id " + 
				"FROM  " + 
				"  saskaita_faktura";
	}
	
}
