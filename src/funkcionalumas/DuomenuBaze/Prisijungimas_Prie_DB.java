package funkcionalumas.DuomenuBaze;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//--------------------------------------------------------------------------------------------
//############################################################################################
//--------------------------------------------------------------------------------------------
//	Sioje klaseje parasytas prisijungimui prie duomenu bazes reikalingas kodas
//--------------------------------------------------------------------------------------------
//############################################################################################
//--------------------------------------------------------------------------------------------


public class Prisijungimas_Prie_DB {
	
	// Objekto kintamieji:
	
	private String host,
				   uName,
				   uPass;
	
	private Connection connection;
	

    // Konstruktoriai:
	
	public Prisijungimas_Prie_DB() {
	
		connection = null;
		
		host = "jdbc:mysql://localhost:3306/pardavimupirkimuapskaitosdb?characterEncoding=utf8";
		uName = "root";
		uPass = "";

	}
	
	
	// Kiti metodai:
	
	public Connection GautiPrisijungima() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
		}
			catch (Exception err) {
				
				System.out.println(err.getMessage());
			}
		
		try {
		
			connection = DriverManager.getConnection(host, uName, uPass);
			
			System.out.println("Prie duomenu bazes prisijungta!");
		
		}
			catch (SQLException err) {
				
				System.out.println("Nepavyksta prisijungti prie duomenu bazes: " + err);
				
			}
		
		
		return connection;
		
	}

}
