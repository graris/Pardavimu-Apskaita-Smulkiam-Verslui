package atributai;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Atributai {

	// Objekto kintamieji:
	
	protected HashMap<String, String> atributai = new HashMap<String, String>();
	  
	// -- Privalomi prekiø atributai. Tai turbut bus reikalinga ateiciai. 
	// -- Kai vartotojams bus suteikta galimybe prideti nauju atributu - jie cia nebus talpinami:
	// --
	protected ArrayList<String> mandatoryAttributes = new ArrayList<String>();
	
	
	// Getter'iai ir Setter'iai
	
	public HashMap<String, String> getAtributai() {
		return atributai;
	}
	
	// Kiti metodai:
	


	public void pridetiAtributa(String attrName, String name) {
		
		atributai.put(attrName, name);
		
	}
	
	
}
