package src;
import java.sql.*;

import util.Database;

public class Codici{
	
	public static boolean codicePresente(int codice){
		Connection conn = Database.getConnessione();
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
	
}
