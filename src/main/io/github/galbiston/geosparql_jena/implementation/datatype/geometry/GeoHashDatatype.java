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
package io.github.galbiston.geosparql_jena.implementation.datatype.geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

import org.apache.sis.referencing.CommonCRS;
import org.apache.sis.referencing.gazetteer.AbstractLocation;
import org.apache.sis.referencing.gazetteer.GeohashReferenceSystem;
import org.locationtech.jts.algorithm.Angle;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Location;
import org.opengis.referencing.operation.TransformException;

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
            GeohashReferenceSystem refsys;
    		try {
    			refsys = new GeohashReferenceSystem(GeohashReferenceSystem.Format.BASE32, CommonCRS.WGS84.normalizedGeographic());
    			GeohashReferenceSystem.Coder coder=refsys.createCoder();
    			if(geom1.getGeometryType().equalsIgnoreCase("Point")) {
    				String geohash = coder.encode(Angle.toDegrees(geom1.getCoordinate().getX()), Angle.toDegrees(geom1.getCoordinate().getY()));
    				return geohash;
    			}
    			throw new AssertionError("Object passed to GeoHashDatatype is not a Point: " + geometry);
    		} catch (TransformException e) {
    			// TODO Auto-generated catch block
    			throw new RuntimeException(e.getMessage());
    		}
    	} else {
            throw new AssertionError("Object passed to GeoHashDatatype is not a GeometryWrapper: " + geometry);
        }	
    }

    @Override
    public GeometryWrapper read(String geometryLiteral) {
    	try {
            GeohashReferenceSystem refsys= new GeohashReferenceSystem(GeohashReferenceSystem.Format.BASE32, CommonCRS.WGS84.normalizedGeographic());
    		GeohashReferenceSystem.Coder coder=refsys.createCoder();
			AbstractLocation pos=coder.decode(geometryLiteral);
			return GeometryWrapperFactory.createPoint(new Coordinate(pos.getPosition().getDirectPosition().getCoordinate()[0],pos.getPosition().getDirectPosition().getCoordinate()[1]), URI);
		} catch (TransformException e) {
			throw new AssertionError("Could not read GeoHash representation of: " + geometryLiteral);
		}

    }


    @Override
    public String toString() {
        return "GeoHashDatatype{" + URI + '}';
    }

}