package test.de.hsmainz.cs.semgis.arqextension.geometry;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.io.ParseException;

import de.hsmainz.cs.semgis.arqextension.geometry.CollectionHomogenize;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.GeoJSONDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class GeomFromTextTest {

	public static final String geoJsonTestGeom="{\"type\":\"Point\",\"coordinates\":[-48.23456,20.12345]}";
	
	@Test
	public void testGeomFromGeoJSON() throws ParseException {
        CollectionHomogenize instance=new CollectionHomogenize();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(-48.23456,20.12345)); 
        NodeValue expResult = GeometryWrapperFactory.createPoint(coords.get(0), WKTDatatype.URI).asNodeValue();      
        NodeValue result = instance.exec(WKTDatatype.INSTANCE.parse(geoJsonTestGeom).asNodeValue());
        assertEquals(expResult, result);
	}
	
}
