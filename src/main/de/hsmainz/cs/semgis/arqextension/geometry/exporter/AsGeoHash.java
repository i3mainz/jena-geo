package de.hsmainz.cs.semgis.arqextension.geometry.exporter;

import java.math.BigInteger;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.apache.sis.referencing.CommonCRS;
import org.apache.sis.referencing.gazetteer.GeohashReferenceSystem;
import org.locationtech.jts.algorithm.Angle;
import org.opengis.referencing.operation.TransformException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Return a GeoHash representation of the geometry
 *
 */
public class AsGeoHash extends FunctionBase2 {
	
	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v1);
		BigInteger maxchars=v2.getInteger();
		GeohashReferenceSystem refsys;
		try {
			refsys = new GeohashReferenceSystem(GeohashReferenceSystem.Format.BASE32, CommonCRS.WGS84.normalizedGeographic());
			GeohashReferenceSystem.Coder coder=refsys.createCoder();
			if(geom1.getGeometryType().equalsIgnoreCase("Point")) {
				String geohash = coder.encode(Angle.toDegrees(geom1.getXYGeometry().getCoordinate().getX()), Angle.toDegrees(geom1.getXYGeometry().getCoordinate().getY()));
				return NodeValue.makeString(geohash);
			}
			throw new RuntimeException("Input geometry needs to be a Point");
		} catch (TransformException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}

		
	}

}
