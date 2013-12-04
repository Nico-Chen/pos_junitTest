package at.spengergasse.jdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.PreparedStatement;

public class Haupt {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbc_uebung?user=root&password=");
			//createSampleTables(conn);

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM schueler");

			while ( rs.next() ) {
				//System.out.printf( "%s, %s, %s %n", rs.getString(1));
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}
			
			System.out.println("---Insert von Schüeler---");
			
			stmt.executeUpdate("insert into schueler values (4,'Susi')");
			
			rs = stmt.executeQuery("SELECT * FROM schueler");

			while ( rs.next() ) {
				//System.out.printf( "%s, %s, %s %n", rs.getString(1));
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}
			
			System.out.println("---Delete von Schüler---");
			
			stmt.executeUpdate("DELETE FROM schueler WHERE id = 4;");
			
			rs = stmt.executeQuery("SELECT * FROM schueler");

			while ( rs.next() ) {
				//System.out.printf( "%s, %s, %s %n", rs.getString(1));
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}
			
			System.out.println("---PrepareStatement---");
			
			stmt.close();

			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM schueler");

			pstmt.executeUpdate("UPDATE schueler SET name = 'Anton' WHERE id = 1");
			
			rs = pstmt.executeQuery("SELECT * FROM schueler");

			while ( rs.next() ) {
				//System.out.printf( "%s, %s, %s %n", rs.getString(1));
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}
			
			rs.close();
			pstmt.close();
			

		} catch (Exception e) {
			// For the sake of this tutorial, let's keep exception handling simple
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

	private static void createSampleTables(Connection con) {
		try (Statement stmt = con.createStatement()){
			//stmt.execute("set schema 'SAMP'");

			try {
				stmt.executeUpdate("DROP TABLE app.posts");
			} catch (SQLException e) {
				; // do nothing
			}

			// Create empty tables.
			stmt.execute("CREATE TABLE app.posts ("
					+ "post_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
					+ "postname VARCHAR(50),"
					+ "comments VARCHAR(512) NOT NULL"
					+ ")"
					);

			stmt.executeUpdate("INSERT INTO app.posts(postname, comments) VALUES('Fred Jones', 'Derby is cool, and the Eclipse plugin makes using it a snap!')");
			stmt.executeUpdate("INSERT INTO app.posts(postname, comments) VALUES('Wilma Harris', 'Tomcat lets me register DataSources using a file in my web project? That''s great stuff!')");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
