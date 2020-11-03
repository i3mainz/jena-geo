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

#### Geometry Literals

This implementation supports the following geometry literal types:

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
| [geo2:ST_BBOXAbove](http://www.opengis.net/ont/geosparqlplus#st_bboxabove) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely above the boundingbox of literal two. | Relation  | No  | Yes  | Todo  |
| [geo2:ST_BBOXBelow](http://www.opengis.net/ont/geosparqlplus#st_bboxbelow) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely below the boundingbox of literal two. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXContains](http://www.opengis.net/ont/geosparqlplus#st_bboxcontains) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely contains the boundingbox of literal two. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXEquals](http://www.opengis.net/ont/geosparqlplus#st_bboxcontains) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one equals the boundingbox of literal two. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXFPIntersects](http://www.opengis.net/ont/geosparqlplus#st_bboxfpintersects) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one intersects the boundingbox of literal two measuring with floating point precision. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXIntersects](http://www.opengis.net/ont/geosparqlplus#st_bboxintersects) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one intersects the boundingbox of literal two. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXIsContainedBy](http://www.opengis.net/ont/geosparqlplus#st_bboxisContainedBy) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely contained by the boundingbox of literal two. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXLeftOf](http://www.opengis.net/ont/geosparqlplus#st_bboxLeftOf) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely left of the boundingbox of literal two. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXOverlapsAbove](http://www.opengis.net/ont/geosparqlplus#st_bboxOverlapsAbove) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is above of the boundingbox of literal two but overlaps with it. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXOverlapsBelow](http://www.opengis.net/ont/geosparqlplus#st_bboxOverlapsBelow) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is below of the boundingbox of literal two but overlaps with it. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXOverlapsLeft](http://www.opengis.net/ont/geosparqlplus#st_bboxOverlapsLeft) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is left of the boundingbox of literal two but overlaps with it. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXOverlapsRight](http://www.opengis.net/ont/geosparqlplus#st_bboxOverlapsRight) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is right of the boundingbox of literal two but overlaps with it. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXRightOf](http://www.opengis.net/ont/geosparqlplus#st_bboxOverlapsRight) (sf:Geometry geom) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely right of the boundingbox of literal two. | Relation  | No  | Yes  | Todo  | 
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
| [geo2:ST_AddGeometry](http://www.opengis.net/ont/geosparqlplus#st_addGeometry) (sf:Geometry geom,sf:Geometry toadd)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a GeometryCollection with the added Geometry. | Relation  | No  | N/A  | Yes  |
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

#### Geometry Relation Functions

<details>
  <summary>These functions relate vector literals to other vector literals and/or raster data.</summary>
  
| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_AreaSimilarity](http://www.opengis.net/ont/geosparqlplus#st_AreaSimilarity) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the area similarity score of two vector geometry literals. | Relation  | No  | Todo  | Yes  |
| [geo2:ST_CentroidDistance](http://www.opengis.net/ont/geosparqlplus#st_AreaSimilarity) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the centroid distance between two geometry literals. | Relation  | No  | Todo  | Yes  |
| [geo2:ST_ClosestCoordinate](http://www.opengis.net/ont/geosparqlplus#st_ClosestCoordinate) (sf:Geometry geom, sf:Geometry geom2)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns the closest coordinate between two geometry literals. | Relation  | No  | Todo  | Yes  |
| [geo2:ST_Distance](http://www.opengis.net/ont/geosparqlplus#st_Distance) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the difference between two geometry literals. | Relation  | Yes  | Todo  | Yes  |
| [geo2:ST_DWithin](http://www.opengis.net/ont/geosparqlplus#st_DWithin) (sf:Geometry geom, sf:Geometry geom2, xsd:double distance)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#double) | Returns true if geometry literal one is within a given distance of geometry literal two. | Relation  | Yes  | Todo  | Yes  |
| [geo2:ST_EqualNorm](http://www.opengis.net/ont/geosparqlplus#st_EqualNorm) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the two geometries are equal in their normalized form. | Relation  | No  | Yes  | Yes  |
| [geo2:ST_EqualSRS](http://www.opengis.net/ont/geosparqlplus#st_EqualSRS) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the two geometries are using the same spatial reference system. | Relation  | No  | Yes  | Yes  |
| [geo2:ST_EqualTopo](http://www.opengis.net/ont/geosparqlplus#st_EqualTopo) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the two geometries are topologically equal. | Relation  | No  | Yes  | Yes  |
| [geo2:ST_EqualType](http://www.opengis.net/ont/geosparqlplus#st_EqualTopo) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Returns true if the two geometry types are equal. | Relation  | No  | Yes  | Yes  |
| [geo2:ST_FrechetDistance](http://www.opengis.net/ont/geosparqlplus#st_FrechetDistance) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the Frechet Distance between two geometries. | Relation  | No  | Todo  | Yes  |
| [geo2:ST_IntersectionMatrix](http://www.opengis.net/ont/geosparqlplus#st_IntersectionMatrix) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns the intersection matrix between to geometries. | Relation  | No  | Todo  | Yes  |
| [geo2:ST_IntersectionPercentage](http://www.opengis.net/ont/geosparqlplus#st_IntersectionPercentage) (sf:Geometry geom, sf:Geometry geom2)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the intersection matrix between to geometries. | Relation  | No  | Yes  | Yes  |

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
| [geo2:ST_Reverse](http://www.opengis.net/ont/geosparqlplus#st_Reverse) (sf:Geometry geom)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns a geometry with reverse coordinates. | Transformation  | No  | N/A  | Yes  |  
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
| [geo2:ST_HasDuplicateRings](http://www.opengis.net/ont/geosparqlplus#st_HasDuplicateRings) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the polygon has duplicate rings. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_InteriorRingN](http://www.opengis.net/ont/geosparqlplus#st_InteriorRingN) (sf:Geometry geom, xsd:integer ringnum)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry)| Returns the nth interior ring of the Polygon. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_IsEquilateralTriangle](http://www.opengis.net/ont/geosparqlplus#st_IsEquilateralTriangle) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the polygon is an equilateral triangle. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_IsIsocelesTriangle](http://www.opengis.net/ont/geosparqlplus#st_IsIsocelesTriangle) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the polygon is an isoceles triangle. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_IsPointedTriangle](http://www.opengis.net/ont/geosparqlplus#st_IsPointedTriangle) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the polygon is a pointed triangle. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_IsRightTriangle](http://www.opengis.net/ont/geosparqlplus#st_IsRightTriangle) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the polygon is a right triangle. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_IsScaleneTriangle](http://www.opengis.net/ont/geosparqlplus#st_IsScaleneTriangle) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the polygon is a scalene triangle. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_IsTriangle](http://www.opengis.net/ont/geosparqlplus#st_IsTriangle) (sf:Geometry geom)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the polygon is a triangle. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_NRings](http://www.opengis.net/ont/geosparqlplus#st_Nrings) (sf:Geometry geom)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of rings of the polygon. | Attribute | No  | N/A  | Yes  | 
| [geo2:ST_NumInteriorRings](http://www.opengis.net/ont/geosparqlplus#st_NInteriorRings) (sf:Geometry geom)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of interior rings of the polygon. | Attribute | No  | N/A  | Yes  | 
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


#### Raster literals

#### Raster Attribute Functions

#### Raster Algebra Functions

#### Raster Constructors

#### Raster Editor Functions

#### Raster Exporter Functions

#### Raster Relation Functions

#### Raster Transformation Functions

### Topology support

### Spatiotemporal support

### Utility Functions

Utility functions help to convert units and accompanying information for geospatial data.

#### Unit Conversion Functions

## GeoSPARQL Ontology extension

The GeoSPARQL ontology has been extended to include support for Coverage representations.

### Ontology of functions

### Ontology for modelling geospatial data

