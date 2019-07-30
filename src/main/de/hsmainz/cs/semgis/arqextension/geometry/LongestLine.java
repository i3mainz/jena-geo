package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class LongestLine extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1) {
        GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
        GeometryWrapper geom2 = GeometryWrapper.extract(arg1);
        Double maxDistance=0.;
        Coordinate maxCoord1=null;
        Coordinate maxCoord2=null;
        try {
			GeometryWrapper transGeom2 = geom2.transform(geom1.getSRID());
			for(Coordinate coord:geom1.getXYGeometry().getCoordinates()) {
				for(Coordinate coord2:transGeom2.getXYGeometry().getCoordinates()) {
					Double curdistance=coord.distance(coord2);
					if(curdistance>maxDistance) {
						maxDistance=curdistance;
						maxCoord1=coord;
						maxCoord2=coord2;
					}
				}
			}
			return GeometryWrapperFactory.createLineString(new Coordinate[] {maxCoord1,maxCoord2}, "<http://www.opengis.net/def/crs/EPSG/0/"+geom1.getSRID()+">", WKTDatatype.URI).asNodeValue();
        } catch (MismatchedDimensionException | TransformException | FactoryException e) {
        	throw new ExprEvalException("Error in distance calculation");
		}
	}

}
