package de.hsmainz.cs.semgis.arqextension.geometry.exporter;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

public class AsGeoRSS extends FunctionBase1 {

	String out="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
			  "<rss version=\"2.0\""+
			       "xmlns:georss=\"http://www.georss.org/georss\""+
			       "xmlns:gml=\"http://www.opengis.net/gml\">"+
			    "<channel>"+
			    "<link>http://www.i3mainz.de/postgis-jena</link>"+
			    "<title>Cambridge Neighborhoods</title>"+
			    "<description>One guy's view of Cambridge, MA</description>"+
			    "<item>"+
			      "<guid isPermaLink=\"false\">00000111c36421c1321d3</guid>"+
			      "<pubDate>"+System.currentTimeMillis()+"</pubDate>";
	String out2="</item></channel></rss>";
	
	
	
	@Override
	public NodeValue exec(NodeValue v) {
		
		 try {
	            AsGML asgml=new AsGML();
	            NodeValue val=asgml.exec(v);
	            return NodeValue.makeString(out+val.getString()+out2);
	        } catch (DatatypeFormatException ex) {
	            throw new ExprEvalException(ex.getMessage(), ex);
	        }

	}

	

	
}
