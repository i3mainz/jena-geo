package de.hsmainz.cs.semgis.arqextension.test.geometry.constructor;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.io.ParseException;

import de.hsmainz.cs.semgis.arqextension.geometry.transform.CollectionHomogenize;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.geometry.WKBDatatype;

public class GeomFromWKBTest {

	public static final String geoJsonTestGeom="\\001\\002\\000\\000\\000\\002\\000\\000\\000\\037\\205\\353Q\\270~\\\\\\300\\323Mb\\020X\\231C@\\020X9\\264\\310~\\\\\\300)\\\\\\217\\302\\365\\230C@";
	
	/*@Test
	public void testGeomFromWKB() throws ParseException {
        CollectionHomogenize instance=new CollectionHomogenize();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(-48.23456,20.12345)); 
        NodeValue expResult = GeometryWrapperFactory.createPoint(coords.get(0), WKTDatatype.URI).asNodeValue();      
        NodeValue result = instance.exec(WKBDatatype.INSTANCE.parse(geoJsonTestGeom).asNodeValue());
        assertEquals(expResult, result);
	}*/
	
}
