package io.github.galbiston.geosparql_jena.implementation.datatype;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

import de.hsmainz.cs.semgis.arqextension.vocabulary.PostGISGeo;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class GPXDatatype extends GeometryDatatype {

	private static final Logger LOGGER = LoggerFactory.getLogger(GPXDatatype.class);

    /**
     * The default WKT type URI.
     */
    public static final String URI = PostGISGeo.HEXWKB;

    /**
     * A static instance of WKTDatatype.
     */
    public static final GPXDatatype INSTANCE = new GPXDatatype();
    
	String out=		"<?xml version='1.0' encoding='UTF-8' standalone='no' ?>" + 
			"<gpx version='1.0'>"+
			"<name>Example gpx</name>";
		String out2= 
			"  <trk>" + 
			"    <name>Example gpx</name>" + 
			"    <trkseg>";
	String out3=" </trkseg></trk></gpx>";
	
	public GPXDatatype() {
		super(URI);
	}

	@Override
	public GeometryWrapper read(String geometryLiteral) {

		try {
			SAXParser parser=SAXParserFactory.newInstance().newSAXParser();
			InputStream stream = new ByteArrayInputStream(geometryLiteral.getBytes(StandardCharsets.UTF_8));
			de.hsmainz.cs.semgis.arqextension.util.GPXHandler handler=new de.hsmainz.cs.semgis.arqextension.util.GPXHandler();
			parser.parse(stream, handler);
			if(handler.coordinates.size()==1 && handler.coordinates.get(0).size()==1) {
				return GeometryWrapperFactory.createPoint(handler.coordinates.get(0).get(0), URI);
			}
			if(handler.coordinates.size()==1 && handler.coordinates.get(0).size()>1) {
				return GeometryWrapperFactory.createLineString(handler.coordinates.get(0), URI);
			}
			/*if(handler.coordinates.size()>1 && handler.coordinates.get(0).size()>1) {
			return GeometryWrapperFactory.createMultiLineString(lineStrings, URI);
			}*/
		} catch (SAXException | IOException | ParserConfigurationException e) {
			throw new AssertionError("There was an error parsing GPXLiteral: " + geometryLiteral);
		}
		throw new AssertionError("Error parsing GPXLiteral: " + geometryLiteral);
	}
	
	
	
	@Override
	public String unparse(Object geometry) {
        if (geometry instanceof GeometryWrapper) {
            GeometryWrapper geometryWrapper = (GeometryWrapper) geometry;
            Geometry geom=geometryWrapper.getXYGeometry();
            StringBuilder trackpoints=new StringBuilder();
            for(Coordinate coord:geom.getCoordinates()) {
            	trackpoints.append("<trkpt lat='"+coord.x+"' lon='"+coord.y+"'>");
            	if(!Double.isNaN(coord.getZ()))
            		trackpoints.append("<ele>"+coord.getZ()+"</ele>");
            	trackpoints.append("</trkpt>");
            }
            return out+out2+trackpoints+out3;
        } else {
            throw new AssertionError("Object passed to GPXDatatype is not a GeometryWrapper: " + geometry);
        }
	}

}
