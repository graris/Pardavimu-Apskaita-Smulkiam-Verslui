package atributai;

import java.util.ArrayList;
import java.util.HashMap;

public class PrekesAtributai  extends Atributai {
	
//	// Objekto kintamieji:
//	
//	private HashMap<String, String> atributai = new HashMap<String, String>();
//	  
//	// -- Privalomi prekiø atributai (cia ateiciai - kai vartotojams bus suteikta galimybe prideti nauju atributu):
//	// --
//	private ArrayList<String> mandatoryAttributes = new ArrayList<String>();
	
	// -- Stulpeliu pavadinimai duombazeje
	// -- 
	private String  uuid = "UUID",
					prekesKodas = "PREKES_KODAS",
					barKodas = "BAR_KODAS",
					pavadinimas = "PAVADINIMAS",
					matavimoVienetas = "MATAVIMO_VIENETAS",
					pirkimoKaina = "PIRKIMO_KAINA",
					antkainioProcentas = "ANTKAINIO_PROCENTAS",
					likutis = "LIKUTIS",
					pirkimoData = "PIRKIMO_DATA", 
					tiekejoImonesKodas = "TIEKEJO_IMONES_KODAS", 
					pvm = "PVM";
	
	
	// Konstruktoriai:
	
	public PrekesAtributai() {

		atributai.put(uuid, "UUID");
		atributai.put(prekesKodas, "Prekës kodas");
		atributai.put(barKodas, "Bar kodas");
		atributai.put(pavadinimas, "Pavadinimas");
		atributai.put(matavimoVienetas, "Matavimo vienetas");
		atributai.put(pirkimoKaina, "Pirkimo kaina");
		atributai.put(antkainioProcentas, "Antkainio procentas");
		atributai.put(likutis, "Likutis");
		atributai.put(pirkimoData, "Pirkimo data");
		atributai.put(tiekejoImonesKodas, "Tiekëjo ámonës kodas");
		atributai.put(pvm, "PVM");
		
		
		mandatoryAttributes.add(uuid);
		mandatoryAttributes.add(prekesKodas);
		mandatoryAttributes.add(barKodas);
		mandatoryAttributes.add(pavadinimas);
		mandatoryAttributes.add(matavimoVienetas);
		mandatoryAttributes.add(pirkimoKaina);
		
	}
//	
//	
//	// Getter'iai ir Setter'iai
//	
//	public HashMap<String, String> getAtributai() {
//		return atributai;
//	}
//	
//	// Kiti metodai:
//	
//
//
//	public void pridetiAtributa(String attrName, String name) {
//		
//		atributai.put(attrName, name);
//		
//	}
//	
}
