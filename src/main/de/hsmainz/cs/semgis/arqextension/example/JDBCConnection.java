package de.hsmainz.cs.semgis.arqextension.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgis.PGgeometry;
import org.postgresql.util.PGobject;

public class JDBCConnection {

	public static Connection jdbcConnection;
	
	Integer resultSize=0;
	
	java.sql.Connection conn; 
	
	public JDBCConnection() {
	
		try { 
		    /* 
		    * Load the JDBC driver and establish a connection. 
		    */
		    Class.forName("org.postgresql.Driver"); 
		    String url = "jdbc:postgresql://localhost:5432/database"; 
		    conn = DriverManager.getConnection(url, "postgres", ""); 
		    /* 
		    * Add the geometry types to the connection. Note that you 
		    * must cast the connection to the pgsql-specific connection 
		    * implementation before calling the addDataType() method. 
		    */
		    ((org.postgresql.PGConnection)conn).addDataType("geometry",(Class<? extends PGobject>) Class.forName("org.postgis.PGgeometry"));
		    ((org.postgresql.PGConnection)conn).addDataType("box3d",(Class<? extends PGobject>) Class.forName("org.postgis.PGbox3d"));

		    /* 
		    * Create a statement and execute a select query. 
		    */ 
		    Statement s = conn.createStatement(); 
		    ResultSet r = s.executeQuery("select geom,id from geomtable"); 
		    while( r.next() ) { 
		      /* 
		      * Retrieve the geometry as an object then cast it to the geometry type. 
		      * Print things out. 
		      */ 
		      PGgeometry geom = (PGgeometry)r.getObject(1); 
		      int id = r.getInt(2); 
		      System.out.println("Row " + id + ":");
		      System.out.println(geom.toString()); 
		    } 
		    s.close(); 
		    conn.close(); 
		  } 
		catch( Exception e ) { 
		  e.printStackTrace(); 
		  } 
		}  	
	
	public static void executeQuery(String query) throws SQLException {
		PreparedStatement p = jdbcConnection.prepareStatement(query);
		p.executeQuery();
	}
}
