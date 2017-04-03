package src;
import java.sql.*;

public class Codici{
	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/bacchetta";
	//private static final String DB_URL = "jdbc:mysql://localhost:3306";
	
	static final String UTENTE = "root";
	static final String PASS = "";
	
	@SuppressWarnings("unused")
	private static final Codici db = new Codici();
	
	private Codici(){
		System.out.println("Connessione al Database...");
		try{
			Class.forName(Codici.JDBC_DRIVER);
		}catch(ClassNotFoundException e){
			System.out.println("ERRORE: driver JDBC non trovato");
		}
			
	}
	
	private static Connection getConnessione(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, UTENTE, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	public static boolean codicePresente(int codice){
		Connection conn =  getConnessione();
		try(
				Statement stmt = conn.createStatement();
		) {
			String sql = "SELECT * FROM Dispositivi WHERE Codice='"+codice+"';";
			ResultSet rs = stmt.executeQuery(sql);
			if(!rs.next()){
				conn.close();
				return false;
			}
			else{
				conn.close();
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return true;
		}
	}
	
	public static void assegnazioneCodice(int codice){
		Connection conn = getConnessione();
		try(
				Statement cmd = conn.createStatement();
		){
			String sql = "INSERT INTO Dispositivi (Codice) VALUES ('" +codice +"');";
			cmd.executeUpdate(sql);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
}
