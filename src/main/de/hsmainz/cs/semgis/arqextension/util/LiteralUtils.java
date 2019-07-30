package de.hsmainz.cs.semgis.arqextension.util;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.sis.geometry.Envelope2D;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.OctagonalEnvelope;
import org.locationtech.jts.geom.Polygon;
import org.opengis.geometry.BoundingBox;

import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.GeometryDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.RasterDataType;

public class LiteralUtils {

	public static Wrapper rasterOrVector(NodeValue v) {
			GeometryDatatype datatype=GeometryDatatype.get(v.getDatatypeURI());
			if(datatype==null) {
				RasterDataType rdatatype=RasterDataType.get(v.getDatatypeURI());
				if(rdatatype==null) {
					throw new AssertionError("No valid raster or vector geometry definition given!");
				}else {
					return CoverageWrapper.extract(v);	
				}	
			}else{
				return  GeometryWrapper.extract(v);
			}	
	}
	
	public static Geometry getCorrectVectorRepresentation(Wrapper wrapper) {
		if(wrapper instanceof GeometryWrapper) {
			return ((GeometryWrapper) wrapper).getXYGeometry();
		}else {
			return toGeometry(((CoverageWrapper) wrapper).getXYGeometry().getEnvelope2D());
		}
	}
	
	public static Geometry toGeometry(final Envelope2D envelope) {
        GeometryFactory gf = new GeometryFactory();
        return gf.createPolygon(gf.createLinearRing(
                new Coordinate[]{
                    new Coordinate(envelope.getMinX(), envelope.getMinY()),
                    new Coordinate(envelope.getMaxX(), envelope.getMinY()),
                    new Coordinate(envelope.getMaxX(), envelope.getMaxY()),
                    new Coordinate(envelope.getMinX(), envelope.getMaxY()),
                    new Coordinate(envelope.getMinX(), envelope.getMinY())
                }), null);
    }
	
	public static Geometry toGeometry(final OctagonalEnvelope envelope) {
        GeometryFactory gf = new GeometryFactory();
        return gf.createPolygon(gf.createLinearRing(
                new Coordinate[]{
                    new Coordinate(envelope.getMinX(), envelope.getMinY()),
                    new Coordinate(envelope.getMaxX(), envelope.getMinY()),
                    new Coordinate(envelope.getMaxX(), envelope.getMaxY()),
                    new Coordinate(envelope.getMinX(), envelope.getMaxY()),
                    new Coordinate(envelope.getMinX(), envelope.getMinY())
                }), null);
    }
	
	public static Geometry toGeometry(final Envelope envelope) {
        GeometryFactory gf = new GeometryFactory();
        return gf.createPolygon(gf.createLinearRing(
                new Coordinate[]{
                    new Coordinate(envelope.getMinX(), envelope.getMinY()),
                    new Coordinate(envelope.getMaxX(), envelope.getMinY()),
                    new Coordinate(envelope.getMaxX(), envelope.getMaxY()),
                    new Coordinate(envelope.getMinX(), envelope.getMaxY()),
                    new Coordinate(envelope.getMinX(), envelope.getMinY())
                }), null);
    }
	
	public static Geometry toGeometry(final BoundingBox envelope) {
        GeometryFactory gf = new GeometryFactory();
        return gf.createPolygon(gf.createLinearRing(
                new Coordinate[]{
                    new Coordinate(envelope.getMinX(), envelope.getMinY()),
                    new Coordinate(envelope.getMaxX(), envelope.getMinY()),
                    new Coordinate(envelope.getMaxX(), envelope.getMaxY()),
                    new Coordinate(envelope.getMinX(), envelope.getMaxY()),
                    new Coordinate(envelope.getMinX(), envelope.getMinY())
                }), null);
    }
	
	 public static Geometry toGeometry(final Rectangle envelope) {
	        GeometryFactory gf = new GeometryFactory();
	        return gf.createPolygon(gf.createLinearRing(
	                new Coordinate[]{
	                    new Coordinate(envelope.getMinX(), envelope.getMinY()),
	                    new Coordinate(envelope.getMaxX(), envelope.getMinY()),
	                    new Coordinate(envelope.getMaxX(), envelope.getMaxY()),
	                    new Coordinate(envelope.getMinX(), envelope.getMaxY()),
	                    new Coordinate(envelope.getMinX(), envelope.getMinY())
	                }), null);
	    }
	
	 
		public static GeometryWrapper createGeometry(List<Coordinate> coordinates,String geomtype,GeometryWrapper geometry) {
			switch(geomtype) {
			case "Point":
				return GeometryWrapperFactory.createPoint(coordinates.get(0), geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
			case "MultiPoint":
				return GeometryWrapperFactory.createMultiPoint(coordinates, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
			case "LineString":
				return GeometryWrapperFactory.createLineString(coordinates, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
			case "Polygon":
				return GeometryWrapperFactory.createPolygon(coordinates, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
			case "MultiLineString":
				List<LineString> list=new LinkedList<LineString>();
				list.add((LineString)GeometryWrapperFactory.createLineString(coordinates, geometry.getSrsURI(), geometry.getGeometryDatatypeURI()).getXYGeometry());
				return GeometryWrapperFactory.createMultiLineString(list, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
			case "MultiPolygon":
				List<Polygon> plist=new LinkedList<Polygon>();
				plist.add((Polygon)GeometryWrapperFactory.createPolygon(coordinates, geometry.getSrsURI(), geometry.getGeometryDatatypeURI()).getXYGeometry());
				return GeometryWrapperFactory.createMultiPolygon(plist, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
			default:
				return null;
			}
		}
		
		public static GeometryWrapper createGeometry(Coordinate[] coordarray,String geomtype,GeometryWrapper geometry) {
			return createGeometry(Arrays.asList(coordarray), geomtype, geometry);
		}
}
