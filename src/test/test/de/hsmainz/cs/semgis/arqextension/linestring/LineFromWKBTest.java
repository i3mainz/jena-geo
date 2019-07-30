package test.de.hsmainz.cs.semgis.arqextension.linestring;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsBinary;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class LineFromWKBTest {

	public static final String testWKBLineString="LINESTRING(1 2, 3 4)";
	
	public static void main(String[] args) {
		AsBinary wkb=new AsBinary();
		List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(1.,2.));
        coords.add(new Coordinate(3.,4.));
        NodeValue geom1 = GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
		NodeValue result=wkb.exec(geom1, NodeValue.makeString("XDR"));
		System.out.println(result.getString().toCharArray());
	}
	
}
