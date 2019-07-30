package io.github.galbiston.geosparql_jena.implementation.datatype;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; 
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.kabeja.dxf.Bounds;
import org.kabeja.dxf.DXFConstants;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFLine;
import org.kabeja.dxf.DXFPoint;
import org.kabeja.dxf.helpers.TextDocument;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.ParseException;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hsmainz.cs.semgis.arqextension.vocabulary.PostGISGeo;

/**
 * WKTDatatype class allows the URI "geo:wktLiteral" to be used as a datatype
 * and it will parse that datatype to a JTS Geometry.
 *
 * Req 10 All RDFS Literals of type geo:wktLiteral shall consist of an optional
 * URI identifying the coordinate reference system followed by Simple Features
 * Well Known Text (WKT) describing a geometric value. Valid geo:wktLiterals are
 * formed by concatenating a valid, absolute URI as defined in [RFC 2396], one
 * or more spaces (Unicode U+0020 character) as a separator, and a WKT string as
 * defined in Simple Features [ISO 19125-1].
 *
 * Req 11 The URI {@code <http://www.opengis.net/def/crs/OGC/1.3/CRS84>} shall
 * be assumed as the spatial reference system for geo:wktLiterals that do not *
 * specify an explicit spatial reference system URI.
 */
public class DXFDatatype extends GeometryDatatype {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncodedPolylineDatatype.class);

    /**
     * The default WKT type URI.
     */
    public static final String URI = PostGISGeo.DXF;

    /**
     * A static instance of WKTDatatype.
     */
    public static final DXFDatatype INSTANCE = new DXFDatatype();

    /**
     * private constructor - single global instance.
     */
    private DXFDatatype() {
        super(URI);
    }

    /**
     * This method Un-parses the JTS Geometry to the WKT literal
     *
     * @param geometry - the JTS Geometry to be un-parsed
     * @return WKT - the returned WKT Literal.
     * <br> Notice that the Spatial Reference System is not specified in
     * returned WKT literal.
     *
     */
    @Override
    public String unparse(Object geometry) {
        if (geometry instanceof GeometryWrapper) {
            GeometryWrapper geometryWrapper = (GeometryWrapper) geometry;
            Geometry geom=geometryWrapper.getXYGeometry();
            DXFDocument doc=new DXFDocument();
            if(geom instanceof Point) {
            	DXFPoint point=new DXFPoint();
            	point.setX(((Point)geom).getX());
            	point.setY(((Point)geom).getY());
            	doc.add
            }else if(geom instanceof LineString) {
            	DXFLine line=new DXFLine();
            }
            if(geometryWrapper.getXYGeometry().getGeometryType().equals("LineString")){
                return encodePolyline((LineString)geometryWrapper.getXYGeometry());            	
            }else {
                throw new AssertionError("Object passed to EncodedPolylineDatatype is not a LineString: " + geometry);
            }
        } else {
            throw new AssertionError("Object passed to EncodedPolylineDatatype is not a GeometryWrapper: " + geometry);
        }
    }
    
    public static ArrayList<LineString> readDXFLiteral(String literalContent) throws ParseException  {
        ArrayList<LineString> lines = new ArrayList<>();
        Parser parser = ParserBuilder.createDefaultParser();
        InputStream targetStream = new ByteArrayInputStream(literalContent.getBytes());
        parser.parse(targetStream, DXFParser.DEFAULT_ENCODING);
        DXFDocument doc = parser.getDocument();
        List<DXFLine> lst = doc.getDXFLayer("layername").getDXFEntities(DXFConstants.ENTITY_TYPE_LINE);
        GeometryFactory fac=new GeometryFactory();
        for (int index = 0; index < lst.size(); index++) {
            Bounds bounds = lst.get(index).getBounds();
            Coordinate[] coords=new Coordinate[2];
            coords[0]=new Coordinate(bounds.getMinimumX(), bounds.getMinimumY());
            coords[1]=new Coordinate(bounds.getMaximumX(),bounds.getMaximumY());
            LineString ls=fac.createLineString(coords);
            lines.add(ls);
        }
        return lines;
    }

    @Override
    public GeometryWrapper read(String geometryLiteral) {
	    GeometryWrapper wrapper;
		try {
			wrapper = GeometryWrapperFactory.createMultiLineString(readDXFLiteral(geometryLiteral), "<http://www.opengis.net/def/crs/EPSG/0/4326>", EncodedPolylineDatatype.URI);
			return wrapper;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
			
		}	
	    
    }
    
    
    @Override
    public String toString() {
        return "DXFDatatype{" + URI + '}';
    }

}

