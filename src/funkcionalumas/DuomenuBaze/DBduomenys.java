package funkcionalumas.DuomenuBaze;

import java.sql.*;
import java.util.ArrayList;

import atributai.Atributai;
import atributai.PrekesAtributai;


public class DBduomenys {
	
	private Statement st;
	
	private ArrayList <ArrayList<String>> duomenys;

	public DBduomenys(Statement st) {
		this.st = st;
		
	}
	
	public DBduomenys(Statement st, ArrayList<ArrayList<String>> duomenys) {
	
		this.duomenys = duomenys;
		this.st = st;
		
	}
	
	public void vykdykQuery(String query) {
		
		try {
			st.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ResultSet vykdykQueryIrGrazinkResultSet(String query) {
		
		try {
			return st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String vykdykQueryIrGrazinkVienaStringReiksme(String query) {
		
		try {
			
			ResultSet rs = st.executeQuery(query);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			rs.next();
			
			return rs.getString(rsmd.getColumnName(1));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ArrayList <ArrayList<String>> getDBlentelesDuomenys(Atributai atributai,  String query) {
			
		ResultSet rs;
		
		try {
			
			rs = st.executeQuery(query);
			
			int i = 1;
			
			ResultSetMetaData rsmd = rs.getMetaData();

			duomenys.clear();
			
			duomenys.add(new ArrayList<String>());
			
			for(int j=1; j<rsmd.getColumnCount()+1; j++) {
				
				if (atributai.getAtributai().get(rsmd.getColumnName(j)) != null)
					duomenys.get(0).add(atributai.getAtributai().get(rsmd.getColumnName(j)));	
				else
					duomenys.get(0).add(rsmd.getColumnName(j));
			
			}
			
			while(rs.next()) {
				
				duomenys.add(new ArrayList<String>());
				
				for(int j=1; j<rsmd.getColumnCount()+1; j++) 			
					duomenys.get(i).add(rs.getString(rsmd.getColumnName(j)));
						
				i++;
			};
			
		

				} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return duomenys;
	}

}
