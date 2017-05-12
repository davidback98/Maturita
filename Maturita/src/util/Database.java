package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Database {
	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/bacchetta";
	
	static final String UTENTE = "root";
	static final String PASS = "";
	
	@SuppressWarnings("unused")
	private static final Database db = new Database();
	
	private Database(){
		System.out.println("Connessione al Database...");
		try{
			Class.forName(Database.JDBC_DRIVER);
		}catch(ClassNotFoundException e){
			System.out.println("ERRORE: driver JDBC non trovato");
		}
			
	}
	
	public static final Connection getConnessione(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, UTENTE, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}
