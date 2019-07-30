package de.hsmainz.cs.semgis.arqextension.geometry;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

/**
 * Given a (multi)geometry, return a (multi)geometry consisting only of elements of the specified type. 
 *
 */
public class CollectionExtract extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1) {
        try {
            GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
            BigInteger type=arg1.getInteger();
            GeometryCollection collection=(GeometryCollection)geom1.getXYGeometry();
            switch(type.intValue()) {
            case 1:
            	List<Coordinate> coords=new ArrayList<Coordinate>();
            	for(int i=0;i<collection.getNumGeometries();i++) {
            		for(Coordinate coord2:collection.getGeometryN(i).getCoordinates()) {
            			coords.add(coord2);
            		}
            	}
            	GeometryWrapper pointWrapper = GeometryWrapperFactory.createMultiPoint(coords, "<http://www.opengis.net/def/crs/EPSG/0/"+geom1.getSRID()+">", WKTDatatype.URI);	
                return pointWrapper.asNodeValue();
            case 2:
            	List<LineString> lines=new ArrayList<LineString>();
            	for(int i=0;i<collection.getNumGeometries();i++) {
            			lines.add((LineString)collection.getGeometryN(i));
            	}
            	GeometryWrapper lineWrapper = GeometryWrapperFactory.createMultiLineString(lines, "<http://www.opengis.net/def/crs/EPSG/0/"+geom1.getSRID()+">", WKTDatatype.URI);	
            	return lineWrapper.asNodeValue();
            case 3: 
            	List<Polygon> polys=new ArrayList<Polygon>();
            	for(int i=0;i<collection.getNumGeometries();i++) {
            			polys.add((Polygon)collection.getGeometryN(i));
            	}
            	GeometryWrapper polyWrapper = GeometryWrapperFactory.createMultiPolygon(polys, "<http://www.opengis.net/def/crs/EPSG/0/"+geom1.getSRID()+">", WKTDatatype.URI);	
                return polyWrapper.asNodeValue();
            }
            throw new ExprEvalException("Invalid geometry type given");
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
