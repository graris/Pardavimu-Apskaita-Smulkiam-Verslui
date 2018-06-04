package funkcionalumas;

import gui.KlaidosPranesimas_Vaizdas;

public class ArTinkamasTipas {
	
	private String tikrinamasTekstas;
	
	
	public ArTinkamasTipas (String tikrinamasTekstas) {
		
		this.tikrinamasTekstas = tikrinamasTekstas; 
		
	}
	
	public boolean yraTeigiamasSveikasSkaicius() {
		
		if (!tikrinamasTekstas.equals("")) {
			
			if (!tikrinamasTekstas.contains(",")) {
						
						try {
							Integer.parseInt(tikrinamasTekstas);
							
							if (Integer.parseInt(tikrinamasTekstas) == 0)
								new KlaidosPranesimas_Vaizdas("Nulin� reik�m� negalima!");						
								else if (Integer.parseInt(tikrinamasTekstas)<0)
									new KlaidosPranesimas_Vaizdas("Neigiama reik�m� negalima!");
									else 
										return true;
									
						} catch (Exception ex) {
							
							new KlaidosPranesimas_Vaizdas("Netinkamas duomen� tipas!");		
						}
	
			} 
				else {
					new KlaidosPranesimas_Vaizdas("Netinkamas duomen� tipas!");	
		
				}
		}
			else {
				new KlaidosPranesimas_Vaizdas("Ne�ved�te jokios reik�m�s!");	
	
			}
		return false;
		
	}
	
	public boolean yraTeigiamasSlankiojoKablelioSkaicius() {
		
		
		if (!tikrinamasTekstas.equals("")) {
			
			if (!tikrinamasTekstas.contains(",")) {
						
				try {
					Float.parseFloat(tikrinamasTekstas);
					
					if (Float.parseFloat(tikrinamasTekstas) == 0)
						new KlaidosPranesimas_Vaizdas("Nulin� reik�m� negalima!");						
						else if (Float.parseFloat(tikrinamasTekstas)<0)
							new KlaidosPranesimas_Vaizdas("Neigiama reik�m� negalima!");
							else 
								return true;
							
				} catch (Exception ex) {
					
					new KlaidosPranesimas_Vaizdas("Netinkamas duomen� tipas!");		
				}
	
			} 
				else {
					new KlaidosPranesimas_Vaizdas("Simbolis ',' yra nepalaikomas - naudokite ta�k�!");	
		
				}
		}
			else {
				new KlaidosPranesimas_Vaizdas("Ne�ved�te jokios reik�m�s!");	
	
			}
		return false;		
		
		
	}	
	
	
}
