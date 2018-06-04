package atributai;

public class SaskaitosAtributai extends Atributai {

	private String saskaitosID = "SASKAITOS_ID",
				   darbuotojoID = "DARBUOTOJO_ID",
				   pirkejoImonesKodas = "PIRKEJO_IMONES_KODAS",
				   filialoID = "FILIALO_ID",
				   pirkimoData = "PIRKIMO_DATA",
				   apmokejimoData = "APMOKEJIMO_DATA",
				   kasosNr = "KASOS_NR",
				   pvm = "PVM",
				   apmoketa = "APMOKETA";
					
			
	
	public SaskaitosAtributai() {
		
		atributai.put(saskaitosID, "Saskaitos ID");
		atributai.put(darbuotojoID, "Darbuotojo ID");
		atributai.put(pirkejoImonesKodas, "Pirkëjo ámonës kodas");
		atributai.put(filialoID, "Filialo ID");
		atributai.put(pirkimoData, "Pirkimo Data");
		atributai.put(apmokejimoData, "Apmokejimo Data");
		atributai.put(kasosNr, "Kasos Nr");
		atributai.put(pvm, "PVM");
		atributai.put(apmoketa, "Apmokëta");
		
		mandatoryAttributes.add(saskaitosID);
		mandatoryAttributes.add(darbuotojoID);
		mandatoryAttributes.add(pirkejoImonesKodas);
		mandatoryAttributes.add(filialoID);
		mandatoryAttributes.add(pirkimoData);
		mandatoryAttributes.add(apmokejimoData);
		mandatoryAttributes.add(kasosNr);
		mandatoryAttributes.add(pvm);
		mandatoryAttributes.add(apmoketa);
		
	}
	
}
