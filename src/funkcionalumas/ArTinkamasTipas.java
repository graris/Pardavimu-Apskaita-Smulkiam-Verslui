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
								new KlaidosPranesimas_Vaizdas("Nulinë reikðmë negalima!");						
								else if (Integer.parseInt(tikrinamasTekstas)<0)
									new KlaidosPranesimas_Vaizdas("Neigiama reikðmë negalima!");
									else 
										return true;
									
						} catch (Exception ex) {
							
							new KlaidosPranesimas_Vaizdas("Netinkamas duomenø tipas!");		
						}
	
			} 
				else {
					new KlaidosPranesimas_Vaizdas("Netinkamas duomenø tipas!");	
		
				}
		}
			else {
				new KlaidosPranesimas_Vaizdas("Neávedëte jokios reikðmës!");	
	
			}
		return false;
		
	}
	
	public boolean yraTeigiamasSlankiojoKablelioSkaicius() {
		
		
		if (!tikrinamasTekstas.equals("")) {
			
			if (!tikrinamasTekstas.contains(",")) {
						
				try {
					Float.parseFloat(tikrinamasTekstas);
					
					if (Float.parseFloat(tikrinamasTekstas) == 0)
						new KlaidosPranesimas_Vaizdas("Nulinë reikðmë negalima!");						
						else if (Float.parseFloat(tikrinamasTekstas)<0)
							new KlaidosPranesimas_Vaizdas("Neigiama reikðmë negalima!");
							else 
								return true;
							
				} catch (Exception ex) {
					
					new KlaidosPranesimas_Vaizdas("Netinkamas duomenø tipas!");		
				}
	
			} 
				else {
					new KlaidosPranesimas_Vaizdas("Simbolis ',' yra nepalaikomas - naudokite taðkà!");	
		
				}
		}
			else {
				new KlaidosPranesimas_Vaizdas("Neávedëte jokios reikðmës!");	
	
			}
		return false;		
		
		
	}	
	
	
}
