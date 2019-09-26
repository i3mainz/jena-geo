package de.hsmainz.cs.semgis.arqextension.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgis.PGgeometry;
import org.postgresql.jdbc2.optional.ConnectionPool;
import org.postgresql.util.PGobject;

public class JDBCConnection {

	public static Connection jdbcConnection;
	
	Integer resultSize=0;
	
	static java.sql.Connection conn; 
	
	static ConnectionPool pool;
	
	public static JDBCConnection INSTANCE;
	
	public JDBCConnection() {	
		try { 
			
			this.pool=new ConnectionPool();
		    pool.setUrl("jdbc:postgresql://localhost:5432/geographica_raster");
		    pool.setUser("postgres");
		    pool.setPassword("postgres");
		    pool.setProtocolVersion(3);
		    conn = pool.getConnection();
		    /* 
		    * Load the JDBC driver and establish a connection. 
		    */
		    Class.forName("org.postgresql.Driver"); 
		    String url = "jdbc:postgresql://127.0.0.1:5432/postgres"; 
		   	
		    ConnectionPool pool = new ConnectionPool();
		    pool.setUrl("jdbc:postgresql://localhost:5432/geographica_raster");
		    pool.setUser("postgres");
		    pool.setPassword("postgres");
		    pool.setProtocolVersion(3);
		    Connection conn = pool.getConnection();
		    
		    //conn = DriverManager.getConnection(url, "postgres", "postgres"); 
		    
		    
		    /* 
		    * Add the geometry types to the connection. Note that you 
		    * must cast the connection to the pgsql-specific connection 
		    * implementation before calling the addDataType() method. 
		    */
		    //((org.postgresql.PGConnection)conn).addDataType("geometry",(Class<? extends PGobject>) Class.forName("org.postgis.PGgeometry"));
		    //((org.postgresql.PGConnection)conn).addDataType("box3d",(Class<? extends PGobject>) Class.forName("org.postgis.PGbox3d"));

		    /* 
		    * Create a statement and execute a select query. 
		    */ 
		   /* Statement s = conn.createStatement(); 
		    ResultSet r = s.executeQuery("select wkb_geometry from geographica_vector"); 
		    while( r.next() ) { 
		      /* 
		      * Retrieve the geometry as an object then cast it to the geometry type. 
		      * Print things out. 
		      
		      PGgeometry geom = (PGgeometry)r.getObject(1); 
		      int id = r.getInt(1); 
		      System.out.println("Row " + id + ":");
		      System.out.println(geom.toString()); 
		    } 
		    s.close(); 
		    conn.close(); */
		  } 
		catch( Exception e ) { 
		  e.printStackTrace(); 
		  } 
		}  
	
	public static JDBCConnection getInstance() {
		if(INSTANCE==null) {
			INSTANCE=new JDBCConnection();
		}
		return INSTANCE;
	}
	
	public static void executeQuery(String query) throws SQLException {
		if(conn==null) {
			conn=pool.getConnection();
		}
		Statement s = conn.createStatement(); 
	    ResultSet r = s.executeQuery(query); 
	    s.close(); 
	    conn.close(); 
	}
	
	public static void main(String[] args) throws SQLException {
		JDBCConnection.getInstance().executeQuery("select * from geographica_vector");
	}
}
