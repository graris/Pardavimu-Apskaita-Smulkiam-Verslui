package kitosKlases;


//----------------------------------------------------------------------------------
//##################################################################################
//----------------------------------------------------------------------------------
//  Sioje klaseje talpinami metodai skirti uzkoduoti/iskoduoti lietuviskus rasmenis
//  , taciau visu siu metodu nauda yra abejotina, jei sistemos koduote bus kita...
//----------------------------------------------------------------------------------
//##################################################################################
//----------------------------------------------------------------------------------
public class Koduote {
		
	private static final String lt1 = "#001",
								lt2 = "#002",
								lt3 = "#003",
								lt4 = "#004",
								lt5 = "#005",
								lt6 = "#006",
								lt7 = "#007",
								lt8 = "#008",
								lt9 = "#009",
								lt10 = "#101",
								lt11 = "#102",
								lt12 = "#103",
								lt13 = "#104",
								lt14 = "#105",
								lt15 = "#106",
								lt16 = "#107",
								lt17 = "#108",
								lt18 = "#109",
								groteles = "#010",
								bruksnys = "#011";
	
	private static String Gauti_koda(char raide) {
		
		String kodas = null;
		
		switch (raide) {
		
			case 'à': kodas = lt1;
					  break;
					  
			case 'è': kodas = lt2;
			  		  break;
			  		  
			case 'æ': kodas = lt3;
			  		  break;
			  		  
			case 'ë': kodas = lt4;
					  break;
					  
			case 'á': kodas = lt5;
			  		  break;
			  		  
			case 'ð': kodas = lt6;
			  		  break;
			  		  
			case 'ø': kodas = lt7;
			  		  break;
			  		  
			case 'û': kodas = lt8;
			  		  break;
			  		  
			case 'þ': kodas = lt9;
			  		  break;
			
			case 'À': kodas = lt10;
			  		  break;
			  
			case 'È': kodas = lt11;
			  		  break;
			  		  
			case 'Æ': kodas = lt12;
			  		  break;
			  		  
			case 'Ë': kodas = lt13;
					  break;
					  
			case 'Á': kodas = lt14;
			  		  break;
			  		  
			case 'Ð': kodas = lt15;
			  		  break;
			  		  
			case 'Ø': kodas = lt16;
			  		  break;
			  		  
			case 'Û': kodas = lt17;
			  		  break;
			  		  
			case 'Þ': kodas = lt18;
			  		  break;			  		  
  		  
			case '#': kodas = groteles;
			  		  break;
			  		 
			case '|': kodas = bruksnys;
			  		  break;
			  		  
			default: kodas = Character.toString(raide);
	  		  		 break;
	
		}
		
		return kodas;
		
	}
	
	private static char Gauti_raide(String kodas) {
		
		char raide = ' ';

		if (kodas.equals(lt1)) {
			
			raide = 'à';
			
		} 
			else if (kodas.equals(lt2)) {
				
				raide = 'è';
				
			} 
				else if (kodas.equals(lt3)) {
					
					raide = 'æ';
					
				} 
					else if (kodas.equals(lt4)) {
						
						raide = 'ë';
						
					} 
						else if (kodas.equals(lt5)) {
							
							raide = 'á';
							
						} 
							else if (kodas.equals(lt6)) {
								
								raide = 'ð';
								
							} 
								else if (kodas.equals(lt7)) {
									
									raide = 'ø';
									
									
								} 
									else if (kodas.equals(lt8)) {
										
										raide = 'û';
										
									} 
										else if (kodas.equals(lt9)) {
											
											raide = 'þ';
											
										} 
											else if (kodas.equals(lt10)) {
												
												raide = 'À';
												
											} 
												else if (kodas.equals(lt11)) {
													
													raide = 'È';
													
												} 
													else if (kodas.equals(lt12)) {
														
														raide = 'Æ';
														
													} 
														else if (kodas.equals(lt13)) {
															
															raide = 'Ë';
															
														} 
															else if (kodas.equals(lt14)) {
																
																raide = 'Á';
																
															} 
																else if (kodas.equals(lt15)) {
																	
																	raide = 'Ð';
																	
																} 
																	else if (kodas.equals(lt16)) {
																		
																		raide = 'Ø';
																		
																	} 
																		else if (kodas.equals(lt17)) {
																			
																			raide = 'Û';
																			
																		} 
										
																			else if (kodas.equals(lt18)) {
																				
																				raide = 'Þ';
																				
																			} 
																				else if (kodas.equals(groteles)) {
																					
																					raide = '#';
																					
																				} 
																					else if (kodas.equals(bruksnys)) {
																						
																						raide = '|';
																						
																					}
		
		return raide;
		
	}		
	
	public static String KodasIRaide(String elementas) {
		
		String kodas = "";
		
		for(int i=0;i<elementas.length();i++) {			
		
			if(elementas.charAt(i) == '#') {
				
				for(int j=i;j<i+4;j++) {
					
					kodas += elementas.charAt(j);
					
				}
				
				elementas = elementas.substring(0, i) + Gauti_raide(kodas) + elementas.substring(i+4);
				
				kodas = "";
				
			}	
			
		}
	
		return elementas;
		
	}
	
	public static String RaideIKoda(String elementas) {
		
		String zodis = "";
		
		for(int i=0;i<elementas.length();i++) {						
			
			zodis += Gauti_koda(elementas.charAt(i));
					
		}
	
		return zodis;
		
	}
}
