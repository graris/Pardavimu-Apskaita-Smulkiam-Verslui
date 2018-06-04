package atributai;

public class PirkejuAtributai extends Atributai {

	private String  imonesKodas = "IMONES_KODAS",
			pavadinimas = "PAVADINIMAS",
			pvmKodas = "PVM_KODAS",
			adresas = "ADRESAS",
			elPastoAdresas = "El_PASTO_ADRESAS";


	// Konstruktoriai:
	
	public PirkejuAtributai() {
	
		atributai.put(imonesKodas, "Imonës kodas");
		atributai.put(pavadinimas, "Pavadinimas");
		atributai.put(pvmKodas, "PVM kodas");
		atributai.put(adresas, "Adresas");
		atributai.put(elPastoAdresas, "El. paðto adresas");
		
		
		mandatoryAttributes.add(imonesKodas);
		mandatoryAttributes.add(pavadinimas);
		mandatoryAttributes.add(pvmKodas);
		mandatoryAttributes.add(adresas);
		mandatoryAttributes.add(elPastoAdresas);
		
	}
	
}
