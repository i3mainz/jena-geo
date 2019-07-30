package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateArrays;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.vocabulary.SRS_URI;

/**
 * Removes the points contained in the second geometry from the first geometry.
 *
 */
public class RemovePoints extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		 GeometryWrapper geometry = GeometryWrapper.extract(v1);
	       Geometry geom = geometry.getXYGeometry();
	       GeometryWrapper geometry2 = GeometryWrapper.extract(v1);
	       Geometry geom2 = geometry2.getXYGeometry();
			GeometryFactory fac=new GeometryFactory();	       
		if (!geom2.intersects(geom)) {
            return GeometryWrapperFactory.createGeometry(fac.createPoint(), geometry.getSrsURI(),geometry.getGeometryDatatypeURI()).asNodeValue();
        }            

        Coordinate[] arrayOfCoordinate1 = new Coordinate[geom.getCoordinates().length];
        int j = 0;
        for (Coordinate coordinate : geom.getCoordinates()) {
            if (!geom2.contains(fac.createPoint(coordinate))) {
                arrayOfCoordinate1[(j++)] = coordinate;
            }
        }

        Coordinate[] arrayOfCoordinate2 = CoordinateArrays.removeNull(arrayOfCoordinate1);
        Coordinate[] localObject = arrayOfCoordinate2;
        if (((geom instanceof LinearRing)) && (arrayOfCoordinate2.length > 1) && (!arrayOfCoordinate2[(arrayOfCoordinate2.length - 1)].equals2D(arrayOfCoordinate2[0]))) {
            Coordinate[] arrayOfCoordinate3 = new Coordinate[arrayOfCoordinate2.length + 1];
            CoordinateArrays.copyDeep(arrayOfCoordinate2, 0, arrayOfCoordinate3, 0, arrayOfCoordinate2.length);
            arrayOfCoordinate3[(arrayOfCoordinate3.length - 1)] = new Coordinate(arrayOfCoordinate3[0]);
            localObject = arrayOfCoordinate3;
        }
        GeometryWrapper resultgeom=LiteralUtils.createGeometry(localObject, geom.getGeometryType(), geometry);
        return resultgeom.asNodeValue();

	}

}
