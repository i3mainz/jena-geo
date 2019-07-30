/*
 * Copyright 2018 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.galbiston.geosparql_jena.implementation.datatype;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

import java.text.ParseException;

import org.apache.sis.index.GeoHashCoder;
import org.locationtech.jts.algorithm.Angle;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.opengis.geometry.DirectPosition;
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
public class GeoHashDatatype extends GeometryDatatype {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeoHashDatatype.class);

    /**
     * The default WKT type URI.
     */
    public static final String URI = PostGISGeo.GeoHash;

    /**
     * A static instance of WKTDatatype.
     */
    public static final GeoHashDatatype INSTANCE = new GeoHashDatatype();
    

    /**
     * private constructor - single global instance.
     */
    private GeoHashDatatype() {
        super(URI);
    }
    
    GeoHashCoder coder=new GeoHashCoder();

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
            Geometry geom1 = ((GeometryWrapper)geometry).getXYGeometry();
            if(geom1.getGeometryType().equalsIgnoreCase("Point")) {
            	String geohash = coder.encode(Angle.toDegrees(geom1.getCoordinate().getX()), Angle.toDegrees(geom1.getCoordinate().getY()));
            	return geohash;
            } else {
                throw new AssertionError("Object passed to GeoHashDatatype is not a Point: " + geometry);
            }
    	} else {
            throw new AssertionError("Object passed to GeoHashDatatype is not a GeometryWrapper: " + geometry);
        }	
    }

    @Override
    public GeometryWrapper read(String geometryLiteral) {
    	try {
			DirectPosition pos=coder.decode(geometryLiteral);
			return GeometryWrapperFactory.createPoint(new Coordinate(pos.getCoordinate()[0],pos.getCoordinate()[1]), URI);
		} catch (ParseException e) {
			throw new AssertionError("Could not read GeoHash representation of: " + geometryLiteral);
		}

    }


    @Override
    public String toString() {
        return "GeoHashDatatype{" + URI + '}';
    }

}