# postgis-jena

This implementation extends the ARQ query processor of Apache Jena GeoSPARQL with further functions to support geometry operations.
This implementation builds upon and extends work by Greg Albiston:

https://github.com/galbiston/geosparql-jena

Please also visit the sister projects of this implementation with aim for similar implementations for RDF4J and Apache Marmotta:

- https://github.com/i3mainz/rdf4j-geo

- https://github.com/i3mainz/kiwi-postgis

Testbench at: http://www.i3mainz.de/projekte/semgis/postgis-jena

## Extensions of geospatial support

The main contribution of this implementation is the provision of further geospatial processing functions which are available in comparable relational database implementations such as POSTGIS or Oracle Spatial but not yet in a Semantic Web implementation.

### Spatial Aggregate Functions

<details>
  <summary>Spatial aggregate functions define aggregate functions which can be applied to geometry and coverage literals.</summary>


| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|

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

- [GeoBuf](https://github.com/mapbox/geobuf)
- [TopoJSON](https://github.com/topojson/topojson)

#### Geometry Attribute Functions

<details>
  <summary>These functions return attributes of a given vector or raster literal which may be used e.g. in SPARQL FILTER expressions.</summary>

| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_Area](http://www.opengis.net/ont/geosparqlplus#st_area)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the area of the geometry | Attribute  | No  | Yes  | Yes  | 
| [geo2:ST_Area3D](http://www.opengis.net/ont/geosparqlplus#st_area3d)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the area of a 3d geometry | Attribute  | No  | Todo  | No  | 
| [geo2:ST_Boundary](http://www.opengis.net/ont/geosparqlplus#boundary)  | [sf:LineString](http://www.opengis.net/ont/sf#LineString) | Calculates the boundary of a geometry | Attribute  | Yes  | Yes  | Yes  | 
| [geo2:ST_BoundingDiagonal](http://www.opengis.net/ont/geosparqlplus#boundingDiagonal)  | [sf:LineString](http://www.opengis.net/ont/sf#LineString) | Calculates the bounding diagonal of a geometry | Attribute  | No  | Todo  | Yes  | 
| [geo2:ST_Centroid](http://www.opengis.net/ont/geosparqlplus#centroid)  | [sf:Point](http://www.opengis.net/ont/sf#Point) | Calculates the centroid of a geometry | Attribute  | No  | Todo  | Yes  |
| [geo2:ST_CompactnessRatio](http://www.opengis.net/ont/geosparqlplus#compactnessRatio)  | [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the compactness ratio of a geometry | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_ConcaveHull](http://www.opengis.net/ont/geosparqlplus#concaveHull)  |  [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Calculates the concave hull of a geometry | Attribute  | No  | Todo  | Yes  |
| [geo2:ST_ConvexHull](http://www.opengis.net/ont/geosparqlplus#convexHull)  |  [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Calculates the convex hull of a geometry | Attribute  | No  | Todo  | Yes  |
| [geo2:ST_Dimension](http://www.opengis.net/ont/geosparqlplus#dimension)  |  [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Calculates the dimension of the given geometry | Attribute  | No  | Todo  | Yes  |
| [geo2:ST_GeometryN](http://www.opengis.net/ont/geosparqlplus#geometryN)  |  [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns the nth geometry of a Geometry Collection | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_GeometryType](http://www.opengis.net/ont/geosparqlplus#geometryType)  |  [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Returns the type of a the given Geometry | Attribute  | No  | Todo  | Yes  |
| [geo2:ST_HasHorizontalCRS](http://www.opengis.net/ont/geosparqlplus#hasHorizontalCRS)  |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the CRS of the given geometry is horizontal | Attribute  | No  | Todo  | Yes  |
| [geo2:ST_HasRepeatedPoints](http://www.opengis.net/ont/geosparqlplus#hasRepeatedPoints)  |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether geometry has repeated coordinates | Attribute  | No  | No  | Yes  |
| [geo2:ST_InteriorPoint](http://www.opengis.net/ont/geosparqlplus#interiorPoint)  |   [sf:Point](http://www.opengis.net/ont/sf#Point) | Returns a point guaranteed to be covered by the geometry | Attribute  | No  | Todo  | Yes  |
| [geo2:ST_Is3D](http://www.opengis.net/ont/geosparqlplus#is3D)  |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the given geometry is a 3D geometry | Attribute  | No  | Todo  | Yes  |
| [geo2:ST_IsCollection](http://www.opengis.net/ont/geosparqlplus#isCollection)  |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the given geometry is a GeometryCollection | Attribute  | No  | N/A  | Yes  |
| [geo2:ST_IsInCRSAreaOfValidity](http://www.opengis.net/ont/geosparqlplus#isInCRSAreaOfValidity)  |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the point is in a valid area defined by its CRS | Attribute | No  | N/A  | Yes  |
| [geo2:ST_IsMeasured](http://www.opengis.net/ont/geosparqlplus#isMeasured)  |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the geometry contains a measurement coordinate | Attribute | No  | N/A  | Yes  |
| [geo2:ST_IsPlanar](http://www.opengis.net/ont/geosparqlplus#isPlanar)  |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the geometry is 2D | Attribute | No  | N/A  | Yes  |
| [geo2:ST_IsRectangle](http://www.opengis.net/ont/geosparqlplus#isRectangle)  |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the geometry is a rectangle | Attribute | No  | N/A  | Yes  |
| [geo2:ST_IsSolid](http://www.opengis.net/ont/geosparqlplus#isSolid)  |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the geometry is 3D (see also geo2:ST_Is3D) | Attribute | No  | N/A  | Yes  |
| [geo2:ST_IsSquare](http://www.opengis.net/ont/geosparqlplus#isSquare)  |  [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates whether the geometry is a square | Attribute | No  | N/A  | Yes  |
| [geo2:ST_IsValidDetail](http://www.opengis.net/ont/geosparqlplus#isValidDetail)  |  [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Indicates whether the geometry is valid and the invlid part of the geometry as a String on error | Attribute | No  | N/A  | Yes  |
| [geo2:ST_IsValidReason](http://www.opengis.net/ont/geosparqlplus#isValidReason)  |  [xsd:string](http://www.w3.org/2001/XMLSchema#string) | Indicates whether the geometry is valid, returns a reason for this assessment | Attribute | No  | N/A  | Yes  |
| [geo2:ST_Length](http://www.opengis.net/ont/geosparqlplus#Length)  |  [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Indicates whether the length of the geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_Length3D](http://www.opengis.net/ont/geosparqlplus#Length3D)  |  [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Indicates whether the 3d length of the geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_MinimumBoundingCircle](http://www.opengis.net/ont/geosparqlplus#MinimumBoundingCircle)  |  [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns the miminum bounding circle of the given geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_MinimumBoundingCircleCenter](http://www.opengis.net/ont/geosparqlplus#MinimumBoundingCircleCenter)  |  [sf:Point](http://www.opengis.net/ont/sf#Point) | Returns the center point of the miminum bounding circle of the given geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_MinimumBoundingCircleRadius](http://www.opengis.net/ont/geosparqlplus#MinimumBoundingCircleCenter)  |  [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the radius of the miminum bounding circle of the given geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_MinimumClearance](http://www.opengis.net/ont/geosparqlplus#MinimumClearance)  |  [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the miminum clearance of the given geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_MinimumClearanceLine](http://www.opengis.net/ont/geosparqlplus#MinimumClearanceLine)  |  [sf:LineString](http://www.opengis.net/ont/sf#LineString) | Returns the miminum clearance line of the given geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_MinimumDiameter](http://www.opengis.net/ont/geosparqlplus#MinimumDiameter)  |  [xsd:double](http://www.w3.org/2001/XMLSchema#double) | Returns the miminum diameter of the given geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_MinimumDiameterLine](http://www.opengis.net/ont/geosparqlplus#MinimumDiameterLine)  | [sf:LineString](http://www.opengis.net/ont/sf#LineString) | Returns the miminum diameter line of the given geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_MinimumRectangle](http://www.opengis.net/ont/geosparqlplus#MinimumRectangle)  | [sf:Geometry](http://www.opengis.net/ont/sf#Geometry) | Returns the miminum rectangle around the given geometry | Attribute | No  | Todo  | Yes  |
| [geo2:ST_NDims](http://www.opengis.net/ont/geosparqlplus#NDims)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of dimensions for a given geometry | Attribute | No  | N/A  | Yes  |
| [geo2:ST_NumDistinctGeometries](http://www.opengis.net/ont/geosparqlplus#NumDistinctGeometries)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of distinct geometries of the given geometry literal | Attribute | No  | N/A  | Yes  |
| [geo2:ST_NumDistinctPoints](http://www.opengis.net/ont/geosparqlplus#NumDistinctPoints)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of distinct points of the given geometry literal | Attribute | No  | N/A | Yes  |
| [geo2:ST_NumGeometries](http://www.opengis.net/ont/geosparqlplus#NumGeometries)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of geometries of the given geometry literal | Attribute | No  | N/A | Yes  |
| [geo2:ST_NumPatches](http://www.opengis.net/ont/geosparqlplus#NumPatches)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of patches of the given geometry literal | Attribute | No  | N/A | No  |
| [geo2:ST_NumPoints](http://www.opengis.net/ont/geosparqlplus#NumPoints)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the number of points of the given geometry literal | Attribute | No  | N/A | Yes  |
| [geo2:ST_PatchN](http://www.opengis.net/ont/geosparqlplus#PatchN)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the nth patch of the given geometry literal | Attribute | No  | N/A | No |
| [geo2:ST_Perimeter](http://www.opengis.net/ont/geosparqlplus#Perimeter)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the perimeter of the given geometry literal | Attribute | No  | Todo | Yes |
| [geo2:ST_Perimeter3D](http://www.opengis.net/ont/geosparqlplus#Perimeter3D)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the 3d perimeter of the given geometry literal | Attribute | No  | Todo | No |
| [geo2:ST_PointN](http://www.opengis.net/ont/geosparqlplus#PointN)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns the nth point of the given geometry literal | Attribute | No  | N/A | No |
| [geo2:ST_PointOnSurface](http://www.opengis.net/ont/geosparqlplus#PointOnSurface)  | [xsd:integer](http://www.w3.org/2001/XMLSchema#integer) | Returns a point guaranteed to lie on the surface of the given geometry literal | Attribute | No  | Todo | No |
</details>

#### Geometry BoundingBox (Envelope) Functions

<details>
  <summary>These functions work with boundingboxes (envelopes) of geometries and raster geometries of raster data. All functions are compatible with raster representations. They may be for example be used in SPARQL FILTER expressions.</summary>

| Function  | Return Value  | Description |  Type | In GeoSPARQL?  | Supports raster? | Stable?  |
|---|---|---|---|---|---|---|
| [geo2:ST_BBOXAbove](http://www.opengis.net/ont/geosparqlplus#st_bboxabove)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely above the boundingbox of literal two. | Relation  | No  | Yes  | Todo  |
| [geo2:ST_BBOXBelow](http://www.opengis.net/ont/geosparqlplus#st_bboxbelow)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely below the boundingbox of literal two. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXContains](http://www.opengis.net/ont/geosparqlplus#st_bboxcontains)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely contains the boundingbox of literal two. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXEquals](http://www.opengis.net/ont/geosparqlplus#st_bboxcontains)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one equals the boundingbox of literal two. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXFPIntersects](http://www.opengis.net/ont/geosparqlplus#st_bboxfpintersects)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one intersects the boundingbox of literal two measuring with floating point precision. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXIntersects](http://www.opengis.net/ont/geosparqlplus#st_bboxintersects)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one intersects the boundingbox of literal two. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXIsContainedBy](http://www.opengis.net/ont/geosparqlplus#st_bboxisContainedBy)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely contained by the boundingbox of literal two. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXLeftOf](http://www.opengis.net/ont/geosparqlplus#st_bboxLeftOf)  | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely left of the boundingbox of literal two. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXOverlapsAbove](http://www.opengis.net/ont/geosparqlplus#st_bboxOverlapsAbove) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is above of the boundingbox of literal two but overlaps with it. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXOverlapsBelow](http://www.opengis.net/ont/geosparqlplus#st_bboxOverlapsBelow) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is below of the boundingbox of literal two but overlaps with it. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXOverlapsLeft](http://www.opengis.net/ont/geosparqlplus#st_bboxOverlapsLeft) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is left of the boundingbox of literal two but overlaps with it. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXOverlapsRight](http://www.opengis.net/ont/geosparqlplus#st_bboxOverlapsRight) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is right of the boundingbox of literal two but overlaps with it. | Relation  | No  | Yes  | Todo  | 
| [geo2:ST_BBOXRightOf](http://www.opengis.net/ont/geosparqlplus#st_bboxOverlapsRight) | [xsd:boolean](http://www.w3.org/2001/XMLSchema#boolean) | Indicates if the boundingbox of literal one is completely right of the boundingbox of literal two. | Relation  | No  | Yes  | Todo  | 
</details>

#### Geometry Constructors

#### Geometry Editor Functions

#### Geometry Exporter Functions

#### Geometry Relation Functions

#### Geometry Transformation Functions

#### LineString Functions

#### Point Functions

#### Polygon Functions

#### Coordinate Reference System Functions

### Raster/Coverage support

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

#### Unit Conversion Functions



