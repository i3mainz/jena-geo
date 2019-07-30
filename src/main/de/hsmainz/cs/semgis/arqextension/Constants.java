/*******************************************************************************
 * Copyright (c) 2017 Timo Homburg, i3Mainz.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the BSD License
 * which accompanies this distribution, and is available at
 * https://directory.fsf.org/wiki/License:BSD_4Clause
 *
 * This project extends work by Ian Simmons who developed the Parliament Triple Store.
 * http://parliament.semwebcentral.org and published his work und BSD License as well.
 *
 *     
 *******************************************************************************/
package de.hsmainz.cs.semgis.arqextension;

public class Constants {
	
	   public static final String OGC_NS = "http://www.opengis.net/";
	   public static final String SRS_NS = OGC_NS + "def/crs/";
	   public static final String OGC_SRS_NS = SRS_NS + "OGC/1.3/";
	   public static final String EPSG_SRS_NS = SRS_NS + "EPSG/0/";
	   public static final String OGC_FUNCTION_NS = OGC_NS + "def/geosparql/function/";

   /**
    * Size of query cache.
    */
   public static final int QUERY_CACHE_SIZE = 100;

   public static final String INDEX_PREFIX = "spatial";
   // configuration properties
   public static final String USERNAME = "username";
   public static final String PASSWORD = "password";
   public static final String JDBC_URL = "jdbcUrl";

   public static final String INDEX_DIRECTORY = "indexDirectory";
   public static final String GEOSPARQL_ENABLED = "geoSPARQL";
   public static final String GEOMETRY_INDEX_TYPE = "indexType";
   public static final String GEOMETRY_INDEX_JTS = "JTS";
   public static final String GEOMETRY_INDEX_POSTGRESQL = "PostgreSQL";
   public static final String GEOMETRY_INDEX_RTREE = "RTree";

   /**
    * Spatial Reference ID for WGS84
    */
   public static final int WGS84_SRID = 4326;
   public static final int DEFAULT_SRID = 0;
   public static final String DEFAULT_CRS = "CRS:84";

   /**
    * Internal coordinate reference system code. All geometries are represented
    * in this CRS.
    */
//   public static final String INTERNAL_CRS = "EPSG:4326";
   public static final String INTERNAL_CRS = "CRS:84";

   public static final String SPATIAL_FUNCTION_NS = "http://parliament.semwebcentral.org/spatial/pfunction#";
}
