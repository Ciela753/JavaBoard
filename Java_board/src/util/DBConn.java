package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {
	private Connection connection;
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@db.codingmonkey.co.kr:1521:xe", "JAVABOARD", "JAVABOARD");
			System.out.println("성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
//		
		return connection;
	}
	
	public static void main(String[] args) {
		getConnection();
	}
}
