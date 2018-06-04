package atributai;

public class PajamavimoDokumentoAtributai extends Atributai{

	private String serija = "SERIJA",
			   pajamavimoDokumentoID = "PAJAMAVIMO_DOK_ID",
			   tiekejoImonesKodas = "TIEKEJO_IMONES_KODAS",
			   filialoID = "FILIALO_ID",
			   pirkimoData = "PIRKIMO_DATA",
			   apmokejimoData = "APMOKEJIMO_DATA",
			   pvm = "PVM",
			   apmoketa = "APMOKETA";
				
		

	public PajamavimoDokumentoAtributai() {
		
		atributai.put(serija, "Serija");
		atributai.put(pajamavimoDokumentoID, "Pajamavimo dokumento ID");
		atributai.put(tiekejoImonesKodas, "Tiekëjo ámonës kodas");
		atributai.put(filialoID, "Filialo ID");
		atributai.put(pirkimoData, "Pirkimo Data");
		atributai.put(apmokejimoData, "Apmokejimo Data");
		atributai.put(pvm, "PVM");
		atributai.put(apmoketa, "Apmokëta");
		
		mandatoryAttributes.add(serija);
		mandatoryAttributes.add(pajamavimoDokumentoID);
		mandatoryAttributes.add(tiekejoImonesKodas);
		mandatoryAttributes.add(filialoID);
		mandatoryAttributes.add(pirkimoData);
		mandatoryAttributes.add(apmokejimoData);
		mandatoryAttributes.add(pvm);
		mandatoryAttributes.add(apmoketa);
		
	}
	
}
