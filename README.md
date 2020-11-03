# jena-geo

This implementation extends the ARQ query processor of Apache Jena GeoSPARQL with further functions to support geometry operations.
This implementation builds upon and extends work by Greg Albiston:

https://github.com/galbiston/geosparql-jena

Please also visit the sister projects of this implementation with aim for similar implementations for RDF4J and Apache Marmotta:

- https://github.com/i3mainz/rdf4j-geo

- https://github.com/i3mainz/kiwi-postgis

Testbench at: http://www.i3mainz.de/projekte/semgis/postgis-jena

## Extensions of geospatial support

The main contribution of this implementation is the provision of further geospatial processing functions which are available in comparable relational database implementations such as POSTGIS or Oracle Spatial but not yet in a Semantic Web implementation.
Particular focus is given to highlight the compatibility of given functions with raster/coverage data representations.

### Spatial Aggregate Functions

<details>
  <summary>Spatial aggregate functions define aggregate functions which can be applied to geometry and coverage literals.</summary>


| Function  | Return Value  | Description | Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_AVGX](http://www.opengis.net/ont/geosparqlplus#st_avgx)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the aggregate of all given X coordinate values | Aggregate | No | Todo  | Yes  | 
| [geo2:ST_AVGXDistinct](http://www.opengis.net/ont/geosparqlplus#st_avgxdistinct)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the aggregate of all given distinct X coordinate values | Aggregate | No  | Todo  | Yes  | 
| [geo2:ST_AVGY](http://www.opengis.net/ont/geosparqlplus#st_avgy)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the aggregate of all given Y coordinate values | Aggregate | No | Todo  | Yes  |
| [geo2:ST_AVGYDistinct](http://www.opengis.net/ont/geosparqlplus#st_avgydistinct)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the aggregate of all given Y coordinate values | Aggregate | No | Todo  | Yes  |
| [geo2:ST_AVGZDistinct](http://www.opengis.net/ont/geosparqlplus#st_avgzdistinct)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the aggregate of all given Z coordinate values | Aggregate | No | Todo  | Yes  |
| [geo2:ST_BoundingBox](http://www.opengis.net/ont/geosparqlplus#st_boundingbox)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Calculates an aggergate boundingbox of all given geometries | Aggregate | No | Todo  | Yes  |
| [geo2:ST_BoundingBoxDistinct](http://www.opengis.net/ont/geosparqlplus#st_boundingboxdistinct)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Calculates an aggregate boundingbox of all given distinct geometries | Aggregate | No | Todo  | No  |
| [geo2:ST_MaxX](http://www.opengis.net/ont/geosparqlplus#st_maxx)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the maximum X coordinate of all given geometries | Aggregate | No | Todo  | Yes  |
| [geo2:ST_MaxY](http://www.opengis.net/ont/geosparqlplus#st_maxy)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the maximum Y coordinate of all given geometries | Aggregate | No | Todo  | Yes |
| [geo2:ST_MaxZ](http://www.opengis.net/ont/geosparqlplus#st_maxz)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the maximum Z coordinate of all given geometries | Aggregate | No | Todo  | Yes |
| [geo2:ST_MinX](http://www.opengis.net/ont/geosparqlplus#st_minx)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the minimum X coordinate of all given geometries | Aggregate | No | Todo  | Yes  |
| [geo2:ST_MinXDistinct](http://www.opengis.net/ont/geosparqlplus#st_minxdistinct)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the minimum X coordinate of all distinct given geometries | Aggregate | No | Todo  | Yes  |
| [geo2:ST_MinY](http://www.opengis.net/ont/geosparqlplus#st_miny)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the minimum Y coordinate of all given geometries | Aggregate | No | Todo  | No  |
| [geo2:ST_MinYDistinct](http://www.opengis.net/ont/geosparqlplus#st_minxdistinct)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the minimum Y coordinate of all distinct given geometries | Aggregate | No | Todo  | Yes  |
| [geo2:ST_MinZ](http://www.opengis.net/ont/geosparqlplus#st_miny)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the minimum Z coordinate of all given geometries | Aggregate | No | Todo  | No  |
| [geo2:ST_MinZDistinct](http://www.opengis.net/ont/geosparqlplus#st_minzdistinct)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the minimum Z coordinate of all distinct given geometries | Aggregate | No | Todo  | Yes |

</details>

### Geometries

This section defines additional functions for vector geometries which are supported by the implementation.
If functions from GeoSPARQL are reimplemented they are marked as such.
If functions may also relate vector geometries to raster data, they are marked as such.
Functions which are not fully implemented or unstable are marked in the table.

#### Geometry Literals

<details>
  <summary>This implementation supports the following geometry literal types:</summary>

- [Encoded Polyline](https://developers.google.com/maps/documentation/utilities/polylinealgorithm)
- [GeoURI](https://tools.ietf.org/html/rfc5870) Literals
- [GeoJSON](https://tools.ietf.org/html/rfc7946) Literals
- [Geography Markup Language (GML)](https://www.ogc.org/standards/gml) Literals (as defined in GeoSPARQL)
- [GPS Exchange Format (GPX)](https://www.topografix.com/gpx.asp) Literals
- [Hexadecimal WKB](https://en.wikipedia.org/wiki/Well-known_text_representation_of_geometry) Literals (HexWKB)
- [Keyhole Markup Language (KML)](https://www.ogc.org/standards/kml) Literals
- [Tiny Well-Known-Binary (TWKB)](https://github.com/TWKB/Specification/blob/master/twkb.md) Literals
- [Well-Known-Binary (WKB)](https://en.wikipedia.org/wiki/Well-known_text_representation_of_geometry) Literals
- [Well-Known-Text (WKT)](https://en.wikipedia.org/wiki/Well-known_text_representation_of_geometry) Literals (as defined in GeoSPARQL)

Planned further implementations:

- [ESRIJSON](https://doc.arcgis.com/en/iot/ingest/esrijson.htm)
- [GeoBuf](https://github.com/mapbox/geobuf)
- [TopoJSON](https://github.com/topojson/topojson)

</details>

#### Geometry Attribute Functions

<details>
  <summary>These functions return attributes of a given vector or raster literal which may be used e.g. in SPARQL FILTER expressions.</summary>

| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_Area](http://www.opengis.net/ont/geosparqlplus#st_area) (sf:Geometry geom) | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the area of the geometry | Attribute  | No  | Yes  | Yes  | 
| [geo2:ST_Area3D](http://www.opengis.net/ont/geosparqlplus#st_area3d) (sf:Geometry geom) | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the area of a 3d geometry | Attribute  | No  | Todo  | No  | 
| [geo2:ST_Boundary](http://www.opengis.net/ont/geosparqlplus#boundary) (sf:Geometry geom) | [sf:LineString](http://www.opengis.net/ont/sf#LineString) | Calculates the boundary of a geometry | Attribute  | Yes  | Yes  | Yes  | 
| [geo2:ST_BoundingDiagonal](http://www.opengis.net/ont/geosparqlplus#boundingDiagonal) (sf:Geometry geom) | [sf:LineString](http://www.opengis.net/ont/sf#LineString) | Calculates the bounding diagonal of a geometry | Attribute  | No  | Todo  | Yes  | 
| [geo2:ST_Centroid](http://www.opengis.net/ont/geosparqlplus#centroid) (sf:Geometry geom) | [sf:Point](http://www.opengis.net/ont/sf#Point) | Calculates the centroid of a geometry | Attribute  | No  | Todo  | Yes  |
| [geo2:ST_CompactnessRatio](http://www.opengis.net/ont/geosparqlplus#compactnessRatio) (sf:Geometry geom) | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the compactness ratio of a geometry | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_ConcaveHull](http://www.opengis.net/ont/geosparqlplus#concaveHull) (sf:Geometry geom) |  [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Calculates the concave hull of a geometry | Attribute  | No  | Todo  | Yes  |
| [geo2:ST_ConvexHull](http://www.opengis.net/ont/geosparqlplus#convexHull) (sf:Geometry geom)  |  [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Calculates the convex hull of a geometry | Attribute  | No  | Todo  | Yes  |
| [geo2:ST_Dimension](http://www.opengis.net/ont/geosparqlplus#dimension) (sf:Geometry geom) |  [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the dimension of the given geometry | Attribute  | No  | Todo  | Yes  |
| [geo2:ST_GeometryN](http://www.opengis.net/ont/geosparqlplus#geometryN) (sf:Geometry geom) |  [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns the nth geometry of a Geometry Collection | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_GeometryType](http://www.opengis.net/ont/geosparqlplus#geometryType) (sf:Geometry geom) |  [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns the type of a the given Geometry | Attribute  | No  | Todo  | Yes  |
| [geo2:ST_HasHorizontalCRS](http://www.opengis.net/ont/geosparqlplus#hasHorizontalCRS) (sf:Geometry geom) |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the CRS of the given geometry is horizontal | Attribute  | No  | Todo  | Yes  |
| [geo2:ST_HasRepeatedPoints](http://www.opengis.net/ont/geosparqlplus#hasRepeatedPoints) (sf:Geometry geom) |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether geometry has repeated coordinates | Attribute  | No  | No  | Yes  |
| [geo2:ST_InteriorPoint](http://www.opengis.net/ont/geosparqlplus#interiorPoint) (sf:Geometry geom) |   [sf:Point](http://www.opengis.net/ont/sf#Point) | Returns a point guaranteed to be covered by the geometry | Attribute  | No  | Todo  | Yes  |
| [geo2:ST_Is3D](http://www.opengis.net/ont/geosparqlplus#is3D) (sf:Geometry geom) |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the given geometry is a 3D geometry | Attribute  | No  | Todo  | Yes  |
| [geo2:ST_IsCollection](http://www.opengis.net/ont/geosparqlplus#isCollection) (sf:Geometry geom) |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the given geometry is a GeometryCollection | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_IsInCRSAreaOfValidity](http://www.opengis.net/ont/geosparqlplus#isInCRSAreaOfValidity) (sf:Geometry geom) |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the point is in a valid area defined by its CRS | Attribute | No  | N/A  | Yes  |
| [geo2:ST_IsMeasured](http://www.opengis.net/ont/geosparqlplus#isMeasured) (sf:Geometry geom) |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the geometry contains a measurement coordinate | Attribute | No  | N/A  | Yes  |
| [geo2:ST_IsPlanar](http://www.opengis.net/ont/geosparqlplus#isPlanar) (sf:Geometry geom) |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the geometry is 2D | Attribute | No  | N/A  | Yes  |
| [geo2:ST_IsRectangle](http://www.opengis.net/ont/geosparqlplus#isRectangle) (sf:Geometry geom) |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the geometry is a rectangle | Attribute | No  | N/A  | Yes  |
| [geo2:ST_IsSolid](http://www.opengis.net/ont/geosparqlplus#isSolid) (sf:Geometry geom) |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the geometry is 3D (see also geo2:ST_Is3D) | Attribute | No  | N/A  | Yes  |
| [geo2:ST_IsSquare](http://www.opengis.net/ont/geosparqlplus#isSquare) (sf:Geometry geom) |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the geometry is a square | Attribute | No  | N/A  | Yes  |
| [geo2:ST_IsValidDetail](http://www.opengis.net/ont/geosparqlplus#isValidDetail) (sf:Geometry geom) |  [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Indicates whether the geometry is valid and the invlid part of the geometry as a String on error | Attribute | No  | N/A  | Yes  |
| [geo2:ST_IsValidReason](http://www.opengis.net/ont/geosparqlplus#isValidReason) (sf:Geometry geom) |  [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Indicates whether the geometry is valid, returns a reason for this assessment | Attribute | No  | N/A  | Yes  |
| [geo2:ST_Length](http://www.opengis.net/ont/geosparqlplus#Length) (sf:Geometry geom) |  [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Indicates whether the length of the geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_Length3D](http://www.opengis.net/ont/geosparqlplus#Length3D) (sf:Geometry geom) |  [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Indicates whether the 3d length of the geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_MinimumBoundingCircle](http://www.opengis.net/ont/geosparqlplus#MinimumBoundingCircle) (sf:Geometry geom) |  [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns the miminum bounding circle of the given geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_MinimumBoundingCircleCenter](http://www.opengis.net/ont/geosparqlplus#MinimumBoundingCircleCenter) (sf:Geometry geom) |  [sf:Point](http://www.opengis.net/ont/sf#Point) | Returns the center point of the miminum bounding circle of the given geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_MinimumBoundingCircleRadius](http://www.opengis.net/ont/geosparqlplus#MinimumBoundingCircleCenter) (sf:Geometry geom) |  [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the radius of the miminum bounding circle of the given geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_MinimumClearance](http://www.opengis.net/ont/geosparqlplus#MinimumClearance) (sf:Geometry geom) |  [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the miminum clearance of the given geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_MinimumClearanceLine](http://www.opengis.net/ont/geosparqlplus#MinimumClearanceLine) (sf:Geometry geom) |  [sf:LineString](http://www.opengis.net/ont/sf#LineString) | Returns the miminum clearance line of the given geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_MinimumDiameter](http://www.opengis.net/ont/geosparqlplus#MinimumDiameter) (sf:Geometry geom) |  [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the miminum diameter of the given geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_MinimumDiameterLine](http://www.opengis.net/ont/geosparqlplus#MinimumDiameterLine) (sf:Geometry geom) | [sf:LineString](http://www.opengis.net/ont/sf#LineString) | Returns the miminum diameter line of the given geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_MinimumRectangle](http://www.opengis.net/ont/geosparqlplus#MinimumRectangle) (sf:Geometry geom) | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns the miminum rectangle around the given geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_NDims](http://www.opengis.net/ont/geosparqlplus#NDims) (sf:Geometry geom) | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of dimensions for a given geometry | Attribute | No  | N/A  | Yes  |
| [geo2:ST_NumDistinctGeometries](http://www.opengis.net/ont/geosparqlplus#NumDistinctGeometries) (sf:Geometry geom) | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of distinct geometries of the given geometry literal | Attribute | No  | N/A  | Yes  |
| [geo2:ST_NumDistinctPoints](http://www.opengis.net/ont/geosparqlplus#NumDistinctPoints) (sf:Geometry geom) | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of distinct points of the given geometry literal | Attribute | No  | N/A | Yes  |
| [geo2:ST_NumGeometries](http://www.opengis.net/ont/geosparqlplus#NumGeometries) (sf:Geometry geom) | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of geometries of the given geometry literal | Attribute | No  | N/A | Yes  |
| [geo2:ST_NumPatches](http://www.opengis.net/ont/geosparqlplus#NumPatches) (sf:Geometry geom) | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of patches of the given geometry literal | Attribute | No  | N/A | No  |
| [geo2:ST_NumPoints](http://www.opengis.net/ont/geosparqlplus#NumPoints) (sf:Geometry geom) | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of points of the given geometry literal | Attribute | No  | N/A | Yes  |
| [geo2:ST_PatchN](http://www.opengis.net/ont/geosparqlplus#PatchN) (sf:Geometry geom) | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the nth patch of the given geometry literal | Attribute | No  | N/A | No |
| [geo2:ST_Perimeter](http://www.opengis.net/ont/geosparqlplus#Perimeter) (sf:Geometry geom) | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the perimeter of the given geometry literal | Attribute | No  | Todo | Yes |
| [geo2:ST_Perimeter3D](http://www.opengis.net/ont/geosparqlplus#Perimeter3D) (sf:Geometry geom) | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the 3d perimeter of the given geometry literal | Attribute | No  | Todo | No |
| [geo2:ST_PointN](http://www.opengis.net/ont/geosparqlplus#PointN) (sf:Geometry geom) | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the nth point of the given geometry literal | Attribute | No  | N/A | No |
| [geo2:ST_PointOnSurface](http://www.opengis.net/ont/geosparqlplus#PointOnSurface) (sf:Geometry geom) | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns a point guaranteed to lie on the surface of the given geometry literal | Attribute | No  | Todo | No |
</details>

#### Geometry BoundingBox (Envelope) Functions

<details>
  <summary>These functions work with boundingboxes (envelopes) of geometries and raster geometries of raster data. All functions are compatible with raster representations. They may be for example be used in SPARQL FILTER expressions.</summary>

| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_BBOXAbove](http://www.opengis.net/ont/geosparqlplus#st_bboxabove) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely above the boundingbox of literal two. | Relation  | No  | Yes  | Yes  |
| [geo2:ST_BBOXBelow](http://www.opengis.net/ont/geosparqlplus#st_bboxbelow) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely below the boundingbox of literal two. | Relation  | No  | Yes  | Yes  | 
| [geo2:ST_BBOXContains](http://www.opengis.net/ont/geosparqlplus#st_bboxcontains) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely contains the boundingbox of literal two. | Relation  | No  | Yes  | Yes  | 
| [geo2:ST_BBOXEquals](http://www.opengis.net/ont/geosparqlplus#st_bboxcontains) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one equals the boundingbox of literal two. | Relation  | No  | Yes  | Yes | 
| [geo2:ST_BBOXFPIntersects](http://www.opengis.net/ont/geosparqlplus#st_bboxfpintersects) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one intersects the boundingbox of literal two measuring with floating point precision. | Relation  | No  | Yes  | Yes  | 
| [geo2:ST_BBOXIntersects](http://www.opengis.net/ont/geosparqlplus#st_bboxintersects) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one intersects the boundingbox of literal two. | Relation  | No  | Yes  | Yes  | 
| [geo2:ST_BBOXIsContainedBy](http://www.opengis.net/ont/geosparqlplus#st_bboxisContainedBy) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely contained by the boundingbox of literal two. | Relation  | No  | Yes  | Yes  | 
| [geo2:ST_BBOXLeftOf](http://www.opengis.net/ont/geosparqlplus#st_bboxLeftOf) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely left of the boundingbox of literal two. | Relation  | No  | Yes  | Yes  | 
| [geo2:ST_BBOXOverlapsAbove](http://www.opengis.net/ont/geosparqlplus#st_bboxOverlapsAbove) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is above of the boundingbox of literal two but overlaps with it. | Relation  | No  | Yes  | Yes | 
| [geo2:ST_BBOXOverlapsBelow](http://www.opengis.net/ont/geosparqlplus#st_bboxOverlapsBelow) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is below of the boundingbox of literal two but overlaps with it. | Relation  | No  | Yes  | Yes  | 
| [geo2:ST_BBOXOverlapsLeft](http://www.opengis.net/ont/geosparqlplus#st_bboxOverlapsLeft) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is left of the boundingbox of literal two but overlaps with it. | Relation  | No  | Yes  | Yes  | 
| [geo2:ST_BBOXOverlapsRight](http://www.opengis.net/ont/geosparqlplus#st_bboxOverlapsRight) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is right of the boundingbox of literal two but overlaps with it. | Relation  | No  | Yes  | Yes  | 
| [geo2:ST_BBOXRightOf](http://www.opengis.net/ont/geosparqlplus#st_bboxOverlapsRight) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely right of the boundingbox of literal two. | Relation  | No  | Yes  | Yes  | 
</details>

#### Geometry Constructors

<details>
  <summary>These functions create vector literal representations from Strings.</summary>
  
| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_GeomFromGeoJSON](http://www.opengis.net/ont/geosparqlplus#st_geomFromGeoJSON) (xsd:string input)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns the parsed geometry from a GeoJSON String. | Constructor  | No  | N/A  | Yes  |
| [geo2:ST_GeomFromGML](http://www.opengis.net/ont/geosparqlplus#st_geomFromGML) (xsd:string input)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns the parsed geometry from a GML String. | Constructor  | No  | N/A  | Yes  |
| [geo2:ST_GeomFromKML](http://www.opengis.net/ont/geosparqlplus#st_geomFromKML) (xsd:string input)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns the parsed geometry from a KML String. | Constructor  | No  | N/A  | Yes  |
| [geo2:ST_GeomFromText](http://www.opengis.net/ont/geosparqlplus#st_geomFromText) (xsd:string input)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns the parsed geometry from a WKT String. | Constructor  | No  | N/A  | Yes  |
| [geo2:ST_GeomFromWKB](http://www.opengis.net/ont/geosparqlplus#st_geomFromWKB) (xsd:string input)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns the parsed geometry from a WKB String. | Constructor  | No  | N/A  | Yes  |

</details>

#### Geometry Editor Functions

<details>
  <summary>These functions manipulate vector geometry literals. They are not applicable to raster literals.</summary>
  
| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_AddGeometry](http://www.opengis.net/ont/geosparqlplus#st_addGeometry) (sf:Geometry geom,sf:Geometry toadd)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a GeometryCollection with the added Geometry. | Editor  | No  | N/A  | Yes  |
| [geo2:ST_EnsureClosed](http://www.opengis.net/ont/geosparqlplus#st_EnsureClosed) (sf:Geometry geom)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a closed Geometry. | Editor  | No  | N/A  | Yes  |
| [geo2:ST_RemoveGeometry](http://www.opengis.net/ont/geosparqlplus#st_removeGeometry)  (sf:Geometry geom,xsd:integer position) | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a GeometryCollection with a removed Geometry at a given position. | Editor  | No  | N/A | Yes  |
| [geo2:ST_RemoveRepeatedPoints](http://www.opengis.net/ont/geosparqlplus#st_removeRepeatedPoints)  (sf:Geometry geom,xsd:double tolerance) | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a Geometry with repeated points remove according to a given tolerance. | Editor  | No  | N/A  | Yes  |
| [geo2:ST_SetGeometry](http://www.opengis.net/ont/geosparqlplus#st_setGeometry)  (sf:Geometry geom,xsd:integer position) | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Sets a geometry at the given position of the GeometryCollection. | Editor  | No  | N/A  | Yes  |

</details>

#### Geometry Exporter Functions

<details>
  <summary>These functions convert vector literals to other geospatial vector data formats. These functions are applicable to rasters if they were vectorized beforehand.</summary>
  
| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_AsBinary](http://www.opengis.net/ont/geosparqlplus#st_asBinary) (sf:Geometry geom)  | [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns a Well-Known-Binary representation of the given geometry . | Export  | No  | N/A  | Yes  |
| [geo2:ST_AsGeoJSON](http://www.opengis.net/ont/geosparqlplus#st_asGeoJSON) (sf:Geometry geom)  | [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns a GeoJSON representation of the given geometry . | Export  | No  | N/A  | Yes  |
| [geo2:ST_AsGeoURI](http://www.opengis.net/ont/geosparqlplus#st_asGeoURI) (sf:Geometry geom)  | [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns a GeoURI representation of the given geometry or of its centroid if not a point . | Export  | No  | N/A  | Yes  |
| [geo2:ST_AsGML](http://www.opengis.net/ont/geosparqlplus#st_asGML) (sf:Geometry geom)  | [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns a GML representation of the given geometry. | Export  | No  | N/A  | Yes  |
| [geo2:ST_AsGPX](http://www.opengis.net/ont/geosparqlplus#st_asGPX) (sf:Geometry geom)  | [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns a GPX representation of the given geometry. | Export | No  | N/A  | Yes  |
| [geo2:ST_AsKML](http://www.opengis.net/ont/geosparqlplus#st_asKML) (sf:Geometry geom)  | [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns a KML representation of the given geometry. | Export | No  | N/A  | Yes  |
| [geo2:ST_AsLatLonText](http://www.opengis.net/ont/geosparqlplus#st_asLatLonText) (sf:Geometry geom)  | [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns a Latitude/Longitude text representation of the given geometry. | Export | No  | N/A  | Yes  |
| [geo2:ST_AsText](http://www.opengis.net/ont/geosparqlplus#st_asText) (sf:Geometry geom)  | [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns a Well-Known-Text representation of the given geometry. | Export | No  | N/A  | Yes  |
| [geo2:ST_AsTWKB](http://www.opengis.net/ont/geosparqlplus#st_asText) (sf:Geometry geom)  | [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns a Tiny Well-Known-Binary representation of the given geometry. | Export | No  | N/A  | Yes  |

</details>

#### Geometry/Raster Relation Functions

<details>
  <summary>These functions relate vector literals to other vector literals and/or raster data.</summary>
  
| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_AreaSimilarity](http://www.opengis.net/ont/geosparqlplus#st_AreaSimilarity) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the area similarity score of two vector geometry literals. | Relation  | No  | Todo  | Yes  |
| [geo2:ST_CentroidDistance](http://www.opengis.net/ont/geosparqlplus#st_AreaSimilarity) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the centroid distance between two geometry literals. | Relation  | No  | Todo  | Yes  |
| [geo2:ST_ClosestCoordinate](http://www.opengis.net/ont/geosparqlplus#st_ClosestCoordinate) (sf:Geometry geom, sf:Geometry geom2)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns the closest coordinate between two geometry literals. | Relation  | No  | Todo  | Yes  |
| [geo2:ST_Contains](http://www.opengis.net/ont/geosparqlplus#st_Contains) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the first geometry contains the second. | Relation  | Yes  | Yes  | Yes  |
| [geo2:ST_ContainsProperly](http://www.opengis.net/ont/geosparqlplus#st_ContainsProperly) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the first geometry properly contains the second. | Relation  | Yes  | Yes  | Yes  |
| [geo2:ST_CoveredBy](http://www.opengis.net/ont/geosparqlplus#st_CoveredBy) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the first geometry is covered by the second. | Relation  | Yes  | Yes  | Yes  |
| [geo2:ST_Covers](http://www.opengis.net/ont/geosparqlplus#st_Covers) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the first geometry covers the second. | Relation  | Yes  | Yes  | Yes  |
| [geo2:ST_Crosses](http://www.opengis.net/ont/geosparqlplus#st_Crosses) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the first geometry crosses the second. | Relation  | Yes  | Yes  | Yes  |
| [geo2:ST_DFullyWithin](http://www.opengis.net/ont/geosparqlplus#st_DFullyWithin) (sf:Geometry geom, sf:Geometry geom2, xsd:double distance)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#double) | Returns true if geometry literal one is fully within a given distance of geometry literal two. | Relation  | Yes  | Todo  | Yes  |
| [geo2:ST_Disjoint](http://www.opengis.net/ont/geosparqlplus#st_Disjoint) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the first geometry and second geometry are disjoint. | Relation  | Yes  | Yes  | Yes  |
| [geo2:ST_Distance](http://www.opengis.net/ont/geosparqlplus#st_Distance) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the distance between two geometry literals. | Relation  | Yes  | Yes  | Yes  |
| [geo2:ST_DistanceSphere](http://www.opengis.net/ont/geosparqlplus#st_DistanceSphere) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the spheroid distance between two geometry literals. | Relation  | Yes  | Yes  | Yes  |
| [geo2:ST_DWithin](http://www.opengis.net/ont/geosparqlplus#st_DWithin) (sf:Geometry geom, sf:Geometry geom2, xsd:double distance)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if geometry literal one is within a given distance of geometry literal two. | Relation  | Yes  | Todo  | Yes  |
| [geo2:ST_Equals](http://www.opengis.net/ont/geosparqlplus#st_Equals) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the two geometries are equal. | Relation  | No  | Yes  | Yes  |
| [geo2:ST_EqualNorm](http://www.opengis.net/ont/geosparqlplus#st_EqualNorm) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the two geometries are equal in their normalized form. | Relation  | No  | Yes  | Yes  |
| [geo2:ST_EqualSRS](http://www.opengis.net/ont/geosparqlplus#st_EqualSRS) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the two geometries are using the same spatial reference system. | Relation  | No  | Yes  | Yes  |
| [geo2:ST_EqualTopo](http://www.opengis.net/ont/geosparqlplus#st_EqualTopo) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the two geometries are topologically equal. | Relation  | No  | Yes  | Yes  |
| [geo2:ST_EqualType](http://www.opengis.net/ont/geosparqlplus#st_EqualTopo) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the two geometry types are equal. | Relation  | No  | Yes  | Yes  |
| [geo2:ST_FrechetDistance](http://www.opengis.net/ont/geosparqlplus#st_FrechetDistance) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the Frechet Distance between two geometries. | Relation  | No  | Todo  | Yes  |
| [geo2:ST_Intersection](http://www.opengis.net/ont/geosparqlplus#st_Intersection) (sf:Geometry geom, sf:Geometry geom2)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Calculates the intersection between two geometries. | Relation  | No  | Yes  | Yes  |
| [geo2:ST_Intersects](http://www.opengis.net/ont/geosparqlplus#st_Intersects) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the two geometries intersect. | Relation  | No  | Yes  | Yes  |
| [geo2:ST_IntersectionMatrix](http://www.opengis.net/ont/geosparqlplus#st_IntersectionMatrix) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns the intersection matrix between to geometries. | Relation  | No  | Todo  | Yes  |
| [geo2:ST_IntersectionPercentage](http://www.opengis.net/ont/geosparqlplus#st_IntersectionPercentage) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the intersection matrix between to geometries. | Relation  | No  | Yes  | Yes  |
| [geo2:ST_MaxDistance](http://www.opengis.net/ont/geosparqlplus#st_MaxDistance) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the distance between the most distance coordinates of the two given geometries. | Relation  | No  | Yes  | Yes  |
| [geo2:ST_MaxDistance3D](http://www.opengis.net/ont/geosparqlplus#st_MaxDistance) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the distance between the most distance coordinates of the two given 3d geometries. | Relation  | No  | Todo  | Yes  |
| [geo2:ST_Overlaps](http://www.opengis.net/ont/geosparqlplus#st_Overlaps) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the two geometries overlap. | Relation  | No  | Yes  | Yes  |
| [geo2:ST_ShortestLine](http://www.opengis.net/ont/geosparqlplus#st_ShortestLine) (sf:Geometry geom, sf:Geometry geom2)  | [sf:LineString](http://www.opengis.net/ont/sf#LineString) | Returns the shortest line between two geometries. | Relation  | No  | Todo  | Yes  |
| [geo2:ST_ShortestLine3D](http://www.opengis.net/ont/geosparqlplus#st_ShortestLine3D) (sf:Geometry geom, sf:Geometry geom2)  | [sf:LineString](http://www.opengis.net/ont/sf#LineString) | Returns the shortest line between two 3d geometries. | Relation  | No  | Todo  | No  |
| [geo2:ST_Touches](http://www.opengis.net/ont/geosparqlplus#st_Touches) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the two geometries touch each other. | Relation  | No  | Yes  | Yes  |
| [geo2:ST_Within](http://www.opengis.net/ont/geosparqlplus#st_Within) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the first geometry is within the second geometry. | Relation  | No  | Yes  | Yes  |
</details>

#### Geometry Transformation Functions

<details>
  <summary>These functions transform vector literals according to the specified function. They are mostly not applicable to raster data.</summary>

| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_CollectionExtract](http://www.opengis.net/ont/geosparqlplus#st_CollectionExtract) (sf:Geometry geom,xsd:string type)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a GeometryCollection version of the given geometry. | Transformation  | No  | N/A  | Yes  |  
| [geo2:ST_CollectionHomogenize](http://www.opengis.net/ont/geosparqlplus#st_CollectionExtract) (sf:Geometry geom)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns the simplest representation of a given geometry. | Transformation  | No  | N/A  | Yes  |  
| [geo2:ST_Densify](http://www.opengis.net/ont/geosparqlplus#st_Densify) (sf:Geometry geom, xsd:double tolerance)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a densified version of the given geometry. | Transformation  | No  | N/A  | Yes  |  
| [geo2:ST_FlipCoordinates](http://www.opengis.net/ont/geosparqlplus#st_FlipCoordinates) (sf:Geometry geom)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a given geometry with the X/Y axis flipped. | Transformation  | No  | N/A  | Yes  |  
| [geo2:ST_Force2D](http://www.opengis.net/ont/geosparqlplus#st_Force2D) (sf:Geometry geom)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a 2D representation of a given geometry. | Transformation  | No  | N/A  | Yes  |  
| [geo2:ST_Force3D](http://www.opengis.net/ont/geosparqlplus#st_Force3D) (sf:Geometry geom)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a 3D representation of a given geometry. | Transformation  | No  | N/A  | Yes  |  
| [geo2:ST_Force3DM](http://www.opengis.net/ont/geosparqlplus#st_Force3DM) (sf:Geometry geom)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a 3DM representation of a given geometry. | Transformation  | No  | N/A  | Yes  |  
| [geo2:ST_Force4D](http://www.opengis.net/ont/geosparqlplus#st_Force4D) (sf:Geometry geom)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a 4D representation of a given geometry. | Transformation  | No  | N/A  | Yes  |
| [geo2:ST_ForceCollection](http://www.opengis.net/ont/geosparqlplus#st_ForceCollection) (sf:Geometry geom)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a gepometry collection of a given geometry. | Transformation  | No  | N/A  | Yes  |  
| [geo2:ST_Multi](http://www.opengis.net/ont/geosparqlplus#st_Multi) (sf:Geometry geom)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a "Multi" version of the given geometry if applicable. | Transformation  | No  | N/A  | Yes  | 
| [geo2:ST_Normalize](http://www.opengis.net/ont/geosparqlplus#st_Normalize) (sf:Geometry geom)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a normalized version of the given geometry. | Transformation  | No  | N/A  | Yes  | 
| [geo2:ST_PrecisionReducer](http://www.opengis.net/ont/geosparqlplus#st_PrecisionReducer) (sf:Geometry geom, xsd:double precisionvalue)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a version of the given geometry with reduced precision. | Transformation  | No  | N/A  | Yes  | 
| [geo2:ST_Reverse](http://www.opengis.net/ont/geosparqlplus#st_Reverse) (sf:Geometry geom)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a geometry with reverse coordinates. | Transformation  | No  | N/A  | Yes  | 
| [geo2:ST_Simplify](http://www.opengis.net/ont/geosparqlplus#st_Simplify) (sf:Geometry geom)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a simplified geometry according to the Douglas-Peucker algorithm. | Transformation  | No  | N/A  | Yes  |  
| [geo2:ST_SimplifyPreserveTopology](http://www.opengis.net/ont/geosparqlplus#st_SimplifyPreserveTopology) (sf:Geometry geom)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a simplified geometry preserving its topology. | Transformation  | No  | N/A  | Yes  |
| [geo2:ST_SimplifyVW](http://www.opengis.net/ont/geosparqlplus#st_SimplifyVW) (sf:Geometry geom)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a simplified geometry calculated with the Visvalingam-Whyatt algorithm. | Transformation  | No  | N/A  | Yes  |  
| [geo2:ST_SwapOrdinates](http://www.opengis.net/ont/geosparqlplus#st_SwapOrdinates) (sf:Geometry geom, xsd:string ordinatecode)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Swaps ordinate values according to a given ordinate String code. | Transformation  | No  | N/A  | Yes  |  
</details>


#### LineString Functions

These functions are applicable to LineString representations only and cannot be used with raster literals or other types of Geometries.

##### LineString Attribute Functions

<details>
  <summary>These functions return LineString specific attributes.</summary>

| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_EndPoint](http://www.opengis.net/ont/geosparqlplus#st_EndPoint) (sf:Geometry geom)  | [sf:Point](http://www.opengis.net/ont/sf#Point) | Returns the last point in the given LineString. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_IsClosed](http://www.opengis.net/ont/geosparqlplus#st_IsClosed) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the LineString is closed, i. e. its first and last point are the same. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_SelfIntersections](http://www.opengis.net/ont/geosparqlplus#st_SelfIntersections) (sf:Geometry geom)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a MultiPoint of self intersections of the LineString. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_StartPoint](http://www.opengis.net/ont/geosparqlplus#st_StartPoint) (sf:Geometry geom)  | [sf:Point](http://www.opengis.net/ont/sf#Point) | Returns the first point in the given LineString. | Attribute | No  | N/A  | Yes  | 
</details>

#### Point Functions



#### Polygon Functions

These functions are applicable to Polygon representations only and cannot be used with raster literals or other types of Geometries.

##### Polygon Attribute Functions

<details>
  <summary>These functions return LineString specific attributes.</summary>

| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_CircularityIndex](http://www.opengis.net/ont/geosparqlplus#st_CircularityIndex) (sf:Geometry geom)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double)| Calculates the circularity index of the Polygon. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_Circumcentre](http://www.opengis.net/ont/geosparqlplus#st_Circumcentre) (sf:Geometry geom)  | [sf:Point](http://www.opengis.net/ont/sf#Point)| Returns the circumcentre of the Polygon. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_ExteriorRing](http://www.opengis.net/ont/geosparqlplus#st_ExteriorRing) (sf:Geometry geom)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry)| Returns the exterior ring of the Polygon. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_HasDuplicateRings](http://www.opengis.net/ont/geosparqlplus#st_HasDuplicateRings) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the polygon has duplicate rings. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_InteriorRingN](http://www.opengis.net/ont/geosparqlplus#st_InteriorRingN) (sf:Geometry geom, xsd:integer ringnum)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry)| Returns the nth interior ring of the Polygon. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_IsAcute](http://www.opengis.net/ont/geosparqlplus#st_IsAcute) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the polygon is acute. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_IsConvex](http://www.opengis.net/ont/geosparqlplus#st_IsConvex) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the polygon is convex. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_IsEquilateralTriangle](http://www.opengis.net/ont/geosparqlplus#st_IsEquilateralTriangle) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the polygon is an equilateral triangle. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_IsIsocelesTriangle](http://www.opengis.net/ont/geosparqlplus#st_IsIsocelesTriangle) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the polygon is an isoceles triangle. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_IsPointedTriangle](http://www.opengis.net/ont/geosparqlplus#st_IsPointedTriangle) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the polygon is a pointed triangle. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_IsRightTriangle](http://www.opengis.net/ont/geosparqlplus#st_IsRightTriangle) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the polygon is a right triangle. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_IsScaleneTriangle](http://www.opengis.net/ont/geosparqlplus#st_IsScaleneTriangle) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the polygon is a scalene triangle. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_IsTriangle](http://www.opengis.net/ont/geosparqlplus#st_IsTriangle) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the polygon is a triangle. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_NRings](http://www.opengis.net/ont/geosparqlplus#st_Nrings) (sf:Geometry geom)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of rings of the polygon. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_NumInteriorRings](http://www.opengis.net/ont/geosparqlplus#st_NInteriorRings) (sf:Geometry geom)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of interior rings of the polygon. | Attribute | No  | N/A  | Yes  | 
</details>

##### Polygon Constructor Functions

<details>
  <summary>These functions create Polygons from String representations.</summary>

| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_PolygonFromText](http://www.opengis.net/ont/geosparqlplus#st_PolygonFromText) (xsd:string text)  | [sf:Polygon](http://www.opengis.net/ont/sf#Polygon) | Parses a polygon from a given WKT string. | Editor | No  | N/A  | Yes  | 
| [geo2:ST_PolygonFromWKB](http://www.opengis.net/ont/geosparqlplus#st_PolygonFromWKB) (xsd:string text)  | [sf:Polygon](http://www.opengis.net/ont/sf#Polygon) | Parses a polygon from a given WKB string. | Editor | No  | N/A  | Yes  | 

</details>

##### Polygon Editor Functions

<details>
  <summary>These functions modify Polygon representations.</summary>

| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_AddRing](http://www.opengis.net/ont/geosparqlplus#st_AddRing) (sf:Polygon geom, sf:LineString ring)  | [sf:Polygon](http://www.opengis.net/ont/sf#Polygon) | Adds a ring to the given Polygon. | Editor | No  | N/A  | Yes  | 
| [geo2:ST_RemoveRing](http://www.opengis.net/ont/geosparqlplus#st_RemoveRing) (sf:Polygon geom, xsd:integer ringnum)  | [sf:Polygon](http://www.opengis.net/ont/sf#Polygon) | Removes a ring from the given Polygon. | Editor | No  | N/A  | Yes  | 
| [geo2:ST_SetRing](http://www.opengis.net/ont/geosparqlplus#st_SetRing) (sf:Polygon geom, sf:LineString ring, xsd:integer ringnum)  | [sf:Polygon](http://www.opengis.net/ont/sf#Polygon) | Sets a ring in the given Polygon. | Editor | No  | N/A  | Yes  | 

</details>

#### Coordinate Reference System Functions

<details>
  <summary>These functions are applicable to raster and vector data as they aim to deal with their coordinate reference system.</summary>

| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_EPSGToSRID](http://www.opengis.net/ont/geosparqlplus#st_EPSGToSRID) (xsd:string epsg)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Converts an EPSG CRS representation to an SRID representation. | SRID  | No  | Yes  | Yes  |  
| [geo2:ST_SetSRID](http://www.opengis.net/ont/geosparqlplus#st_SetSRID) (sf:Geometry geom, xsd:string epsg)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Sets the SRID of a given literal. | SRID  | No  | Yes  | Yes  |  
| [geo2:ST_GetAxis1Name](http://www.opengis.net/ont/geosparqlplus#st_GetAxis1Name) (sf:Geometry geom)  | [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns the name of the first axis of the CRS. | SRID  | No  | Yes  | Yes  | 
| [geo2:ST_GetAxis1Orientation](http://www.opengis.net/ont/geosparqlplus#st_GetAxis1Orientation) (sf:Geometry geom)  | [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns the orientation of the first axis of the CRS. | SRID  | No  | Yes  | Yes  | 
| [geo2:ST_GetAxis2Name](http://www.opengis.net/ont/geosparqlplus#st_GetAxis2Name) (sf:Geometry geom)  | [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns the name of the second axis of the CRS. | SRID  | No  | Yes  | Yes  | 
| [geo2:ST_GetAxis2Orientation](http://www.opengis.net/ont/geosparqlplus#st_GetAxis2Orientation) (sf:Geometry geom)  | [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns the orientation of the second axis of the CRS. | SRID  | No  | Yes  | Yes  | 
| [geo2:ST_SRIDHasFlippedAxis](http://www.opengis.net/ont/geosparqlplus#st_SRIDHasFlippedAxis) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the SRID exhibits a flipped axis. | SRID  | No  | Yes  | Yes  | 
| [geo2:ST_SRIDToEPSG](http://www.opengis.net/ont/geosparqlplus#st_SRIDHasFlippedAxis) (xsd:integer srid)  | [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Converts an SRID CRS representation to an EPSG representation. | SRID  | No  | Yes  | Yes  | 
</details>

### Raster/Coverage support

This section introduces supported raster/coverage functions.

#### Raster literals
<details>
  <summary>This section includes raster literals.</summary>
  
- [CoverageJSON](https://covjson.org)
- [RasterWKB](https://github.com/ihmeuw/wkb-raster)
- [RasterHexWKB](https://github.com/ihmeuw/wkb-raster)

Planned further implementations:

- [XYZ Gridded ASCII](https://gdal.org/drivers/raster/xyz.html) for the integration of Digital Elevation models
- [ASCIIGrid Format](https://gdal.org/drivers/raster/aaigrid.html)
- [GeoTIFF](https://gdal.org/drivers/raster/gtiff.html)
- [NetCDF](https://gdal.org/drivers/raster/netcdf.html)
</details>

#### Raster Attribute Functions

<details>
  <summary>These functions returns attributes of a coverage/raster. They are not applicable to vector data.</summary>

| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_Envelope](http://www.opengis.net/ont/geosparqlplus#st_Envelope) (geo2:Raster rast)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns the envelope/minimum bounding box of the given raster. | Attribute  | No  | Yes  | Yes  |
| [geo2:ST_HasNoBand](http://www.opengis.net/ont/geosparqlplus#st_HasNoBand) (geo2:Raster rast, xsd:integer bandnum)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if a raster band of the given number exists, false otherwise. | Attribute  | No  | Yes  | Yes  | 
| [geo2:ST_Height](http://www.opengis.net/ont/geosparqlplus#st_Height) (geo2:Raster rast)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the height of the given raster. | Attribute  | No  | Yes  | Yes  | 
| [geo2:ST_IsEmpty](http://www.opengis.net/ont/geosparqlplus#st_IsEmpty) (geo2:Raster rast)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns if the raster contains atomic values. | Attribute  | No  | Yes  | Yes  | 
| [geo2:ST_NumBands](http://www.opengis.net/ont/geosparqlplus#st_NumBands) (geo2:Raster rast)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of raster bands of the given raster. | Attribute  | No  | Yes  | Yes  | 
| [geo2:ST_NumXTiles](http://www.opengis.net/ont/geosparqlplus#st_NumXTiles) (geo2:Raster rast)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of X tiles of the given raster. | Attribute  | No  | Yes  | Yes  | 
| [geo2:ST_NumYTiles](http://www.opengis.net/ont/geosparqlplus#st_NumYTiles) (geo2:Raster rast)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of Y tiles of the given raster. | Attribute  | No  | Yes  | Yes  | 
| [geo2:ST_Value](http://www.opengis.net/ont/geosparqlplus#st_Value) (geo2:Raster rast, xsd:integer bandnum=1, xsd:integer col, xsd:integer row)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the atomic value in the given column/row of the raster. | Attribute  | No  | Yes  | Yes  | 
| [geo2:ST_Width](http://www.opengis.net/ont/geosparqlplus#st_Width) (geo2:Raster rast)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the height of the given raster. | Attribute  | No  | Yes  | Yes  | 
</details>

#### Raster Algebra Functions

<details>
  <summary>These functions implemeent raster/map algebra operations.</summary>

| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_Add](http://www.opengis.net/ont/geosparqlplus#st_Add) (geo2:Raster rast, geo2:Raster rast, xsd:bandnum=1, xsd:bandnum=1)  | [geo2:Raster](http://www.opengis.net/ont/geosparqlplus#Raster) | Returns the sum of the two rasters. | Attribute  | No  | Yes  | Yes  |
| [geo2:ST_AddConst](http://www.opengis.net/ont/geosparqlplus#st_AddConst) (geo2:Raster rast, xsd:integer badnum=1, xsd:double const)  | [geo2:Raster](http://www.opengis.net/ont/geosparqlplus#Raster) | Returns the raster added by a constant value . | Attribute  | No  | Yes  | Yes  |
</details>

#### Raster Constructors

#### Raster Editor Functions

<details>
  <summary>These functions raster export functions.</summary>

| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_SetBandNoDataValue](http://www.opengis.net/ont/geosparqlplus#st_SetBandNoDataValue) (geo2:Raster rast,xsd:integer bandnum, xsd:double value)  | [geo2:Raster](http://www.opengis.net/ont/geosparqlplus#Raster) | Returns the raster with modified nodata value. | Attribute  | No  | Yes  | Yes  |
</details>

#### Raster Exporter Functions

<details>
  <summary>These functions raster export functions.</summary>

| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_AsRastHexWKB](http://www.opengis.net/ont/geosparqlplus#st_Add) (geo2:Raster rast)  | [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns the raster in rasterwkb representation. | Attribute  | No  | Yes  | Yes  |
</details>

#### Raster Relation Functions

See also Geometry/Raster relation functions

#### Raster Transformation Functions

To be done

### Topology support

This section will include descriptions for topology support which is currently not finished in the given implementation.

### Spatiotemporal support

This section will include spatiotemporal support for geometries and possibly rasters. The implementation will begun when the standardization process of GeoSPARQL 2.0 has been matured and decides to include support for spatiotemporal operations.

<details>
  <summary>These functions await standardization and may or may not be removed from the system. In particular they are not stable currently.</summary>

| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_After](http://www.opengis.net/ont/geosparqlplus#st_After) (geo2:TemporalRange range, geo2:TemporalRange range2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns if the first temporal range lies after the second temporal range. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_Before](http://www.opengis.net/ont/geosparqlplus#st_Before) (geo2:TemporalRange range, geo2:TemporalRange range2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns if the first temporal range lies before the second temporal range. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_Between](http://www.opengis.net/ont/geosparqlplus#st_Between) (geo2:TemporalRange range, geo2:TemporalRange range2, geo2:TempralRange range3)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns if the first temporal range lies in between the second and third temporal range. | Attribute  | No  | N/A  | No |
| [geo2:ST_During](http://www.opengis.net/ont/geosparqlplus#st_During) (geo2:TemporalRange range, geo2:TemporalRange range2, geo2:TempralRange range3)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns if the first temporal range lies in between the second and third temporal range. | Attribute  | No  | N/A  | No |
</details>

### Utility Functions

Utility functions help to convert units and accompanying information for geospatial data.

#### Unit Conversion Functions

<details>
  <summary>These functions convert between units beginning from an S/I Base Unit.</summary>

| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_CentimeterToMeter](http://www.opengis.net/ont/geosparqlplus#st_CentimeterToMeter) (xsd:double cm)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts centimeter to meter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_ChainToMeter](http://www.opengis.net/ont/geosparqlplus#st_ChainToMeter) (xsd:double chain)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts chain to meter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_DecimeterToMeter](http://www.opengis.net/ont/geosparqlplus#st_DecimeterToMeter) (xsd:double decimeter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts decimeter to meter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_FathomToMeter](http://www.opengis.net/ont/geosparqlplus#st_FathomToMeter) (xsd:double fathom)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts fathom to meter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_FootToMeter](http://www.opengis.net/ont/geosparqlplus#st_FootToMeter) (xsd:double foot)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts foot to meter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_InchToMeter](http://www.opengis.net/ont/geosparqlplus#st_InchToMeter) (xsd:double inch)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts inch to meter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_KilometerToMeter](http://www.opengis.net/ont/geosparqlplus#st_KilometerToMeter) (xsd:double km)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts kilometer to meter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_LinkToMeter](http://www.opengis.net/ont/geosparqlplus#st_LinkToMeter) (xsd:double link)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts  link to meter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_MeterToCentimeter](http://www.opengis.net/ont/geosparqlplus#st_MeterToCentimeter) (xsd:double meter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts meter to centimeter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_MeterToChain](http://www.opengis.net/ont/geosparqlplus#st_MeterToChain) (xsd:double meter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts meter to chain. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_MeterToDecimeter](http://www.opengis.net/ont/geosparqlplus#st_MeterToDecimeter) (xsd:double meter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts meter to decimeter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_MeterToFathom](http://www.opengis.net/ont/geosparqlplus#st_MeterToFathom) (xsd:double meter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts meter to fathom. | Attribute  | No  | N/A | Yes  |
| [geo2:ST_MeterToFoot](http://www.opengis.net/ont/geosparqlplus#st_MeterToFoot) (xsd:double meter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts meter to foot. | Attribute  | No  | N/A | Yes  |
| [geo2:ST_MeterToInch](http://www.opengis.net/ont/geosparqlplus#st_MeterToInch) (xsd:double meter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts meter to inch. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_MeterToKilometer](http://www.opengis.net/ont/geosparqlplus#st_MeterToKilometer) (xsd:double meter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts meter to  kilometer. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_MeterToLink](http://www.opengis.net/ont/geosparqlplus#st_MeterToLink) (xsd:double meter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts meter to  link. | Attribute  | No  | N/A | Yes  |
| [geo2:ST_MeterToMile](http://www.opengis.net/ont/geosparqlplus#st_MeterToMile) (xsd:double meter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts meter to  mile. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_MeterToMillimeter](http://www.opengis.net/ont/geosparqlplus#st_MeterToMillimeter) (xsd:double meter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts meter to  millimeter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_MeterToNauticalMile](http://www.opengis.net/ont/geosparqlplus#st_MeterToNauticalMile) (xsd:double meter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts meter to  nautical mile. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_MeterToUSFoot](http://www.opengis.net/ont/geosparqlplus#st_MeterToUSFoot) (xsd:double meter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts meter to US foot. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_MeterToUSInch](http://www.opengis.net/ont/geosparqlplus#st_MeterToUSInch) (xsd:double meter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts meter to US inch. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_MeterToUSMile](http://www.opengis.net/ont/geosparqlplus#st_MeterToUSMile) (xsd:double meter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts meter to US mile. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_MeterToUSYard](http://www.opengis.net/ont/geosparqlplus#st_MeterToUSYard) (xsd:double meter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts meter to US yard. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_MeterToYard](http://www.opengis.net/ont/geosparqlplus#st_MeterToYard) (xsd:double meter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts meter to yard. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_MileToMeter](http://www.opengis.net/ont/geosparqlplus#st_MileToMeter) (xsd:double mile)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts mile to meter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_MillimeterToMeter](http://www.opengis.net/ont/geosparqlplus#st_MillimeterToMeter) (xsd:double millimeter)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts millimeter to meter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_NauticalMileToMeter](http://www.opengis.net/ont/geosparqlplus#st_NauticalMileToMeter) (xsd:double mile)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts nautical mile to meter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_USFootToMeter](http://www.opengis.net/ont/geosparqlplus#st_USFootToMeter) (xsd:double usfoot)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts US foot to meter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_USInchToMeter](http://www.opengis.net/ont/geosparqlplus#st_USInchToMeter) (xsd:double usinch)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts US inch to meter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_USMileToMeter](http://www.opengis.net/ont/geosparqlplus#st_USMileToMeter) (xsd:double usmile)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts US mile to meter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_USYardToMeter](http://www.opengis.net/ont/geosparqlplus#st_USYardToMeter) (xsd:double usyard)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts US yard to meter. | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_YardToMeter](http://www.opengis.net/ont/geosparqlplus#st_YardToMeter) (xsd:double yard)  | [xsd:double](http://www.opengis.net/ont/geosparqlplus#double) | Converts  yard to meter. | Attribute  | No  | N/A  | Yes  |
</details>

## GeoSPARQL Ontology extension

The GeoSPARQL ontology has been extended to include support for Coverage representations.

### Ontology of functions

### Ontology for modelling geospatial data

