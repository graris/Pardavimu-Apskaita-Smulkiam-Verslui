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
		
			case '�': kodas = lt1;
					  break;
					  
			case '�': kodas = lt2;
			  		  break;
			  		  
			case '�': kodas = lt3;
			  		  break;
			  		  
			case '�': kodas = lt4;
					  break;
					  
			case '�': kodas = lt5;
			  		  break;
			  		  
			case '�': kodas = lt6;
			  		  break;
			  		  
			case '�': kodas = lt7;
			  		  break;
			  		  
			case '�': kodas = lt8;
			  		  break;
			  		  
			case '�': kodas = lt9;
			  		  break;
			
			case '�': kodas = lt10;
			  		  break;
			  
			case '�': kodas = lt11;
			  		  break;
			  		  
			case '�': kodas = lt12;
			  		  break;
			  		  
			case '�': kodas = lt13;
					  break;
					  
			case '�': kodas = lt14;
			  		  break;
			  		  
			case '�': kodas = lt15;
			  		  break;
			  		  
			case '�': kodas = lt16;
			  		  break;
			  		  
			case '�': kodas = lt17;
			  		  break;
			  		  
			case '�': kodas = lt18;
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
			
			raide = '�';
			
		} 
			else if (kodas.equals(lt2)) {
				
				raide = '�';
				
			} 
				else if (kodas.equals(lt3)) {
					
					raide = '�';
					
				} 
					else if (kodas.equals(lt4)) {
						
						raide = '�';
						
					} 
						else if (kodas.equals(lt5)) {
							
							raide = '�';
							
						} 
							else if (kodas.equals(lt6)) {
								
								raide = '�';
								
							} 
								else if (kodas.equals(lt7)) {
									
									raide = '�';
									
									
								} 
									else if (kodas.equals(lt8)) {
										
										raide = '�';
										
									} 
										else if (kodas.equals(lt9)) {
											
											raide = '�';
											
										} 
											else if (kodas.equals(lt10)) {
												
												raide = '�';
												
											} 
												else if (kodas.equals(lt11)) {
													
													raide = '�';
													
												} 
													else if (kodas.equals(lt12)) {
														
														raide = '�';
														
													} 
														else if (kodas.equals(lt13)) {
															
															raide = '�';
															
														} 
															else if (kodas.equals(lt14)) {
																
																raide = '�';
																
															} 
																else if (kodas.equals(lt15)) {
																	
																	raide = '�';
																	
																} 
																	else if (kodas.equals(lt16)) {
																		
																		raide = '�';
																		
																	} 
																		else if (kodas.equals(lt17)) {
																			
																			raide = '�';
																			
																		} 
										
																			else if (kodas.equals(lt18)) {
																				
																				raide = '�';
																				
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
