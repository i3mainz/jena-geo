/*******************************************************************************
- * Copyright (c) 2017 Timo Homburg, i3Mainz.
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
package de.hsmainz.cs.semgis.arqextension.vocabulary;

import org.apache.jena.graph.Node;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

public class PostGISGeo {
   public static final String uri = "http://www.opengis.net/ont/geosparql#";




   protected static final Resource resource(String local) {
      return ResourceFactory.createResource(uri + local);
   }

   protected static final Property property(String local) {
      return ResourceFactory.createProperty(uri, local);
   }

   public static final Resource SpatialObject = resource("SpatialObject");
   public static final Resource Feature = resource("Feature");

   // simple features topological relations
   public static final Property st_3ddwithin = property("ST_3DDWithin");
   public static final Property st_3dclosestpoint = property("ST_3DClosestPoint");
   public static final Property st_3dlongestLine = property("ST_3dLongestLine");
   public static final Property st_3dmaxDistance = property("ST_3DMaxDistance");
   public static final Property st_3dshortestline = property("ST_3DShortestLine");
   public static final Property st_accum = property("ST_Accum");
   public static final Property st_addband = property("ST_AddBand");
   public static final Property st_addpoint = property("ST_AddPoint");
   public static final Property st_addz = property("ST_AddZ");
   public static final Property st_area = property("ST_Area");
   public static final Property st_areasimilarity = property("ST_AreaSimilarity");
   public static final Property st_asbinary = property("ST_AsBinary");
   public static final Property st_asencodedpolyline = property("ST_AsEncodedPolyline");
   public static final Property st_askml = property("ST_AsKML");
   public static final Property st_asgml = property("ST_AsGML");
   public static final Property st_asgeobuf = property("ST_AsGeoBuf");
   public static final Property st_asgeojson = property("ST_AsGeoJSON");
   public static final Property st_asgeojsonld = property("ST_AsGeoJSONLD");
   public static final Property st_asgeorss = property("ST_AsGeoRSS");
   public static final Property st_asgeouri = property("ST_AsGeoURI");
   public static final Property st_asgpx = property("ST_AsGPX");
   public static final Property st_ashexewkb = property("ST_AsHexEWKB");
   public static final Property st_asjpg = property("ST_AsJPG");
   public static final Property st_aslatlontext = property("ST_AsLatLonText");
   public static final Property st_asmvt = property("ST_AsMVT");
   public static final Property st_asmvtgeom = property("ST_AsMVTGeom");
   public static final Property st_aspect = property("ST_Aspect");
   public static final Property st_aspng = property("ST_AsPNG");
   public static final Property st_aspolyshape = property("ST_AsPolyshape");
   public static final Property st_assvg = property("ST_AsSVG");
   public static final Property st_astext = property("ST_AsText");
   public static final Property st_astextraw = property("ST_AsTextRaw");
   public static final Property st_astextround = property("ST_AsTextRound");
   public static final Property st_astiff = property("ST_AsTIFF");
   public static final Property st_astopojson = property("ST_AsTopoJSON");
   public static final Property st_astwkb = property("ST_AsTWKB");
   public static final Property st_aswkb = property("ST_AsWKB");
   public static final Property st_aswkt = property("ST_AsWKT");
   public static final Property st_asx3d = property("ST_AsX3D");
   public static final Property st_azimuth = property("ST_Azimuth");
   public static final Property st_band = property("ST_Band");
   public static final Property st_bandmetadata = property("ST_BandMetaData");
   public static final Property st_bandnodatavalue = property("ST_BandNoDataValue");
   public static final Property st_bandpixeltype = property("ST_BandPixelType");
   public static final Property st_bboxabove = property("ST_BBOXAbove");
   public static final Property st_bboxbelow = property("ST_BBOXBelow");
   public static final Property st_bboxcontains = property("ST_BBOXContains");
   public static final Property st_bboxdistance = property("ST_BBOXDistance");
   public static final Property st_bboxequals = property("ST_BBOXEquals");
   public static final Property st_bboxfpintersects = property("ST_BBOXFPIntersects");
   public static final Property st_bboxintersect = property("ST_BBOXIntersect");
   public static final Property st_bboxiscontainedby = property("ST_BBOXIsContainedBy");
   public static final Property st_bboxleftof = property("ST_BBOXLeftOf");
   public static final Property st_bboxoverlapsabove = property("ST_BBOXOverlapsAbove");
   public static final Property st_bboxoverlapsbelow = property("ST_BBOXOverlapsBelow");
   public static final Property st_bboxoverlapsleft = property("ST_BBOXOverlapsLeft");
   public static final Property st_bboxoverlapsright = property("ST_BBOXOverlapsRight");
   public static final Property st_bboxrightof = property("ST_BBOXRightOf");
   public static final Property st_bezierSmoothing = property("ST_BezierSmoothing");
   public static final Property st_boundary = property("ST_Boundary");
   public static final Property st_boundingdiagonal = property("ST_BoundingDiagonal");
   public static final Property st_centroid = property("ST_Centroid");
   public static final Property st_centroidDistance = property("ST_CentroidDistance");
   public static final Property st_chaikinSmooting = property("ST_ChaikinSmoothing");
   public static final Property st_clip = property("ST_Clip");
   public static final Property st_clipByBox2D = property("ST_ClipByBox2D");
   public static final Property st_closestCooordinate = property("ST_ClosestCoordinate");
   public static final Property st_closestPoint = property("ST_ClosestPoint");
   public static final Property st_closestPoint3d = property("ST_3DClosestPoint");
   public static final Property st_closestPointOfApproach = property("ST_ClosestPointOfApproach");
   public static final Property st_clusterIntersecting = property("ST_ClusterIntersecting");
   public static final Property st_clusterKMeans = property("ST_ClusterKMeans");
   public static final Property st_clusterWithin = property("ST_ClusterWithin");
   public static final Property st_collectionExtract = property("ST_CollectionExtract");
   public static final Property st_collectionHomogenize = property("ST_CollectionHomogenize");
   public static final Property st_compactnessRatio = property("ST_CompactnessRatio");
   public static final Property st_convexHull = property("ST_ConvexHull");
   public static final Property st_concaveHull = property("ST_ConcaveHull");
   public static final Property st_contains = property("ST_Contains");
   public static final Property st_containsProperly = property("ST_ContainsProperly");
   public static final Property st_count = property("ST_Count");
   public static final Property st_curveToLine = property("ST_CurveToLine");
   public static final Property st_densify = property("ST_Densify");
   public static final Property st_delaunayTriangles = property("ST_DelaunayTriangles");
   public static final Property st_dimension = property("ST_Dimension");
   public static final Property st_distance = property("ST_Distance");
   public static final Property st_distance3d = property("ST_3DDistance");
   public static final Property st_distancesphere = property("ST_DistanceSphere");
   public static final Property st_dWithin = property("ST_DWithin");
   public static final Property st_dwithin3d = property("ST_DWithin3D");
   public static final Property st_endPoint = property("ST_EndPoint");
   public static final Property st_envelope = property("ST_Envelope");
   public static final Property st_epsgToSRID = property("ST_EPSGToSRID");
   public static final Property st_equals = property("ST_Equals");
   public static final Property st_equalSRS = property("ST_EqualSRS");
   public static final Property st_equalsNorm = property("ST_EqualsNorm");
   public static final Property st_equalsTopo = property("ST_EqualsTopo");
   public static final Property st_explode = property("ST_Explode");
   public static final Property st_filterByM = property("ST_FilterByM");
   public static final Property st_filterByT = property("ST_FilterByT");
   public static final Property st_flipCoordinates = property("ST_FlipCoordinates");
   public static final Property st_force2d = property("ST_Force2D");
   public static final Property st_force3d = property("ST_Force3D");
   public static final Property st_force3dm = property("ST_Force3DM");
   public static final Property st_force3dz = property("ST_Force3DZ");
   public static final Property st_force4d = property("ST_Force4D");
   public static final Property st_forceCollection = property("ST_ForceCollection");
   public static final Property st_forceCurve = property("ST_ForceCurve");
   public static final Property st_forceLHR = property("ST_ForceLHR");
   public static final Property st_forcePolygonCW = property("ST_ForcePolygonCW");
   public static final Property st_forcePolygonCCW = property("ST_ForcePolygonCCW");
   public static final Property st_forceSFS = property("ST_ForceSFS");
   public static final Property st_frechetDistance = property("ST_FrechetDistance");
   public static final Property st_furthestCoordinate = property("ST_FurthestCoordinate");
   public static final Property st_generatePoints = property("ST_GeneratePoints");
   public static final Property st_geohash = property("ST_GeoHash");
   public static final Property st_geomCollFromText = property("ST_CollFromText");
   public static final Property st_geomFromGeoHash = property("ST_GeomFromGeoHash");
   public static final Property st_geomFromGeoJSON = property("ST_GeomFromGeoJSON");
   public static final Property st_geomFromKML = property("ST_GeomFromKML");
   public static final Property st_geomFromGML = property("ST_GeomFromGML");
   public static final Property st_geomFromWKB = property("ST_GeomFromWKB");
   public static final Property st_geomFromText = property("ST_GeomFromText");
   public static final Property st_geometricMedian = property("ST_GeometricMedian");
   public static final Property st_geometryN = property("ST_GeometryN");
   public static final Property st_geometryType = property("ST_GeometryType");
   public static final Property st_gmlToSQL = property("ST_GMLToSQL");
   public static final Property st_grayscale = property("ST_Grayscale");
   public static final Property st_hasHorizontalCRS = property("ST_HasHorizontalCRS");
   public static final Property st_hasNoBand = property("ST_HasNoBand");
   public static final Property st_hasRepeatedPoints = property("ST_HasRepeatedPoints");
   public static final Property st_hausdorffDistance = property("ST_HausdorffDistance");
   public static final Property st_height = property("ST_Height");
   public static final Property st_interiorRingN = property("ST_InteriorRingN");
   public static final Property st_interpolatePoint = property("ST_InterpolatePoint");
   public static final Property st_intersectionPercentage = property("ST_IntersectionPercentage");
   public static final Property st_is3D = property("ST_Is3D");
   public static final Property st_isClosed = property("ST_IsClosed");
   public static final Property st_isCollection = property("ST_IsCollection");
   public static final Property st_isConvex = property("ST_IsConvex");
   public static final Property st_isEmpty = property("ST_IsEmpty");
   public static final Property st_isGrayscale = property("ST_IsGrayscale");
   public static final Property st_isIndexed = property("ST_IsIndexed");
   public static final Property st_isIsocelesTriangle= property("ST_IsIsocelesTriangle");
   public static final Property st_isMeasured = property("ST_IsMeasured");
   public static final Property st_isMorePrecise = property("ST_IsMorePrecise");
   public static final Property st_isNodingValid = property("ST_IsNodingValid");
   public static final Property st_isPointOnLine = property("ST_IsPointOnLine");
   public static final Property st_isPointInRing = property("ST_IsPointInRing");
   public static final Property st_isPolygonCW = property("ST_IsPolygonCW");
   public static final Property st_isPolygonCCW = property("ST_IsPolygonCCW");
   public static final Property st_isRing = property("ST_IsRing");
   public static final Property st_isSimple = property("ST_IsSimple");
   public static final Property st_isTiled = property("ST_IsTiled");
   public static final Property st_isTranslucent = property("ST_IsTranslucent");
   public static final Property st_isTriangle= property("ST_IsTriangle");
   public static final Property st_isValid = property("ST_IsValid");
   public static final Property st_isValidDetail = property("ST_IsValidDetail");
   public static final Property st_isValidReason = property("ST_IsValidReason");
   public static final Property st_isValidTrajectory = property("ST_IsValidTrajectory");
   public static final Property st_Length = property("ST_Length");
   public static final Property st_Length2D = property("ST_Length2D");
   public static final Property st_Length3D = property("ST_Length3D");
   public static final Property st_LengthToPoint = property("ST_LengthToPoint");
   public static final Property st_locateAlong = property("ST_LocateAlong");
   public static final Property st_locateBetween = property("ST_LocateBetween");
   public static final Property st_locateBetweenElevations = property("ST_LocateBetweenElevations");
   public static final Property st_longestLine = property("ST_LongestLine");
   public static final Property st_longestLine3D = property("ST_LongestLine3D");
   public static final Property st_lineCrossingDirection=property("ST_LineCrossingDirection");
   public static final Property st_lineFromEncodedPolyline=property("ST_LineFromEncodedPolyline");
   public static final Property st_lineFromMultiPoint=property("ST_LineFromMultiPoint");
   public static final Property st_lineFromWKB = property("ST_LineFromWKB");
   public static final Property st_lineFromWKT = property("ST_LineFromWKT");
   public static final Property st_lineFromText = property("ST_LineFromText");
   public static final Property st_lineInterpolatePoint = property("ST_LineInterpolatePoint");
   public static final Property st_lineInterpolatePoints = property("ST_LineInterpolatePoints");
   public static final Property st_lineLength3D = property("ST_LineLength3D");
   public static final Property st_lineLocatePoint = property("ST_LineLocatePoint");
   public static final Property st_lineMerge = property("ST_LineMerge");
   public static final Property st_lineSelfIntersectionPoint = property("ST_LineSelfIntersectionPoint");
   public static final Property st_lineSubstring = property("ST_LineSubstring");
   public static final Property st_lineToCurve = property("ST_LineToCurve");
   public static final Property st_m = property("ST_M");
   public static final Property st_makeCircle = property("ST_MakeCircle");
   public static final Property st_makeEllipse = property("ST_MakeEllipse");
   public static final Property st_makeEmptyCoverage = property("ST_MakeEmptyCoverage");
   public static final Property st_makeEmptyRaster = property("ST_MakeEmptyRaster");
   public static final Property st_makeEnvelope = property("ST_MakeEnvelope");
   public static final Property st_makeLine = property("ST_MakeLine");
   public static final Property st_makePoint = property("ST_MakePoint");
   public static final Property st_makePointM = property("ST_MakePointM");
   public static final Property st_makePolygon = property("ST_MakePolygon");
   public static final Property st_makeValid = property("ST_MakeValid");
   public static final Property st_maxDistance = property("ST_MaxDistance");
   public static final Property st_maxDistance3D = property("ST_MaxDistance3D");
   public static final Property st_memsize = property("ST_MemSize");
   public static final Property st_minimumBoundingCircle = property("ST_MinimumBoundingCircle");
   public static final Property st_minimumBoundingCircleCenter = property("ST_MinimumBoundingCircleCenter");
   public static final Property st_minimumBoundingRadius = property("ST_MinimumBoundingRadius");
   public static final Property st_minimumClearance = property("ST_MinimumClearance");
   public static final Property st_minimumClearanceLine = property("ST_MinimumClearanceLine");
   public static final Property st_minimumDiameter = property("ST_MinimumDiameter");
   public static final Property st_minimumDiameterLine = property("ST_MinimumDiameterLine");
   public static final Property st_minimumRectangle = property("ST_MinimumRectangle");
   public static final Property st_minConvexHull = property("ST_MinConvexHull");
   public static final Property st_mintilex = property("ST_MinTileX");
   public static final Property st_mintiley = property("ST_MinTileY");
   public static final Property st_mMin = property("ST_MMin");
   public static final Property st_mMax = property("ST_MMax");
   public static final Property st_multi = property("ST_Multi");
   public static final Property st_multiplyz = property("ST_MultiplyZ");
   public static final Property st_mPointFromText = property("ST_MPointFromText");
   public static final Property st_mLineFromText = property("ST_MLineFromText");
   public static final Property st_mPolyFromText = property("ST_MPolyFromText");
   public static final Property st_nearestValue = property("ST_NearestValue");
   public static final Property st_node = property("ST_Node");
   public static final Property st_normalize = property("ST_Normalize");
   public static final Property st_notSameAlignmentReason = property("ST_NotSameAlignmentReason");
   public static final Property st_nDims = property("ST_NDims");
   public static final Property st_nRings = property("ST_NRings");
   public static final Property st_numBands = property("ST_NumBands");
   public static final Property st_numDistinctPoints=property("ST_NumDistinctPoints");
   public static final Property st_numGeometries = property("ST_NumGeometries");
   public static final Property st_numDistinctGeometries=property("ST_NumDistinctGeometries");
   public static final Property st_numInteriorRings = property("ST_NumInteriorRings");
   public static final Property st_numPatches = property("ST_NumPatches");
   public static final Property st_numPoints = property("ST_NumPoints");
   public static final Property st_numXTiles = property("ST_NumXTiles");
   public static final Property st_numYTiles = property("ST_NumYTiles");
   public static final Property st_nPoints = property("ST_NPoints");
   public static final Property st_octogonalEnvelope = property("ST_OctogonalEnvelope");
   public static final Property st_orientation = property("ST_Orientation");
   public static final Property st_offsetCurve = property("ST_OffsetCurve");
   public static final Property st_orderingEquals = property("ST_OrderingEquals");
   public static final Property st_osmlink = property("ST_OSMLink");
   public static final Property st_partOfGeometryAfter = property("ST_PartOfGeometryAfter");
   public static final Property st_partOfGeometryBefore = property("ST_PartOfGeometryBefore");
   public static final Property st_patchN = property("ST_PatchN");
   public static final Property st_pixelAsCentroid = property("ST_PixelAsCentroid");
   public static final Property st_pixelAsCentroids = property("ST_PixelAsCentroids");
   public static final Property st_pixelAsPoint = property("ST_PixelAsPoint");
   public static final Property st_pixelAsPoints = property("ST_PixelAsPoints");
   public static final Property st_pixelAsPolygon = property("ST_PixelAsPolygon");
   public static final Property st_pixelAsPolygons = property("ST_PixelAsPolygons");
   public static final Property st_pixelHeight = property("ST_PixelHeight");
   public static final Property st_pixelOfValue = property("ST_PixelOfValue");
   public static final Property st_pixelWidth = property("ST_PixelWidth");
   public static final Property st_perimeter = property("ST_Perimeter");
   public static final Property st_perimeter2D = property("ST_Perimeter2D");
   public static final Property st_perimeter3D = property("ST_Perimeter3D");
   public static final Property st_pointFromGeoHash = property("ST_PointFromGeoHash");
   public static final Property st_pointFromWKB = property("ST_PointFromWKB");
   public static final Property st_pointFromText = property("ST_PointFromText");
   public static final Property st_pointInsideCircle = property("ST_PointInsideCircle");
   public static final Property st_pointN = property("ST_PointN");
   public static final Property st_points = property("ST_Points");
   public static final Property st_pointOnSurface = property("ST_PointOnSurface");
   public static final Property st_polygon = property("ST_Polygon");
   public static final Property st_polygonize = property("ST_Polygonize");
   public static final Property st_polygonFromText = property("ST_PolygonFromText");
   public static final Property st_polygonFromWKB = property("ST_PolygonFromWKB");
   public static final Property st_precisionReducer = property("ST_PrecisionReducer");
   public static final Property st_rastFromHexWKB = property("ST_RastFromHexWKB");
   public static final Property st_rastFromWKB = property("ST_RastFromWKB");
   public static final Property st_rast_algebra_add = property("ST_Add");
   public static final Property st_rast_algebra_addconst = property("ST_AddConst");
   public static final Property st_rast_algebra_and = property("ST_And");
   public static final Property st_rast_algebra_andconst = property("ST_AndConst");
   public static final Property st_rast_algebra_div = property("ST_Div");
   public static final Property st_rast_algebra_divconst = property("ST_DivConst");
   public static final Property st_rast_algebra_log = property("ST_Log");
   public static final Property st_rast_algebra_mult = property("ST_Mult");
   public static final Property st_rast_algebra_multconst = property("ST_MultConst");
   public static final Property st_rast_algebra_not = property("ST_Not");
   public static final Property st_rast_algebra_or = property("ST_Or");
   public static final Property st_rast_algebra_orconst = property("ST_OrConst");
   public static final Property st_rast_algebra_subtract = property("ST_Subtract");
   public static final Property st_rast_algebra_subtractconst = property("ST_SubtractConst");
   public static final Property st_rast_algebra_xor = property("ST_Xor");
   public static final Property st_rast_algebra_xorconst = property("ST_XorConst");
   public static final Property st_rast_isEmpty = property("ST_isEmpty");
   public static final Property st_rast_Contains = property("ST_Contains");
   public static final Property st_rast_Covers = property("ST_Covers");
   public static final Property st_rast_CoveredBy = property("ST_CoveredBy");
   public static final Property st_rast_Crosses = property("ST_Crosses");
   public static final Property st_rast_Disjoint = property("ST_Disjoint");
   public static final Property st_rast_Intersects = property("ST_Intersects");
   public static final Property st_rast_Intersection=property("ST_Intersection");
   public static final Property st_rast_Overlaps = property("ST_Overlaps");
   public static final Property st_rast_Touches = property("ST_Touches");
   public static final Property st_rast_Within = property("ST_Within");
   public static final Property st_rast_srid = property("ST_SRID");
   public static final Property st_rasterToWorldCoord = property("ST_RasterToWorldCoord");
   public static final Property st_rasterToWorldCoordX = property("ST_RasterToWorldCoordX");
   public static final Property st_rasterToWorldCoordY = property("ST_RasterToWorldCoordY");
   public static final Property st_removePoint = property("ST_RemovePoint");
   public static final Property st_removePoints = property("ST_RemovePoints");
   public static final Property st_removeRepeatedPoints = property("ST_RemoveRepeatedPoints");
   public static final Property st_reflect = property("ST_Reflect");
   public static final Property st_relate = property("ST_Relate");
   public static final Property st_relateMatch = property("ST_RelateMatch");
   public static final Property st_resize = property("ST_Resize");
   public static final Property st_reskew = property("ST_Reskew");
   public static final Property st_retile = property("ST_Retile");
   public static final Property st_reverse = property("ST_Reverse");
   public static final Property st_rotate = property("ST_Rotate");
   public static final Property st_rotateX = property("ST_RotateX");
   public static final Property st_rotateY = property("ST_RotateY");
   public static final Property st_rotateZ = property("ST_RotateZ");
   public static final Property st_rotation = property("ST_Rotation");
   public static final Property st_roughness = property("ST_Roughness");
   public static final Property st_sameAlignment = property("ST_SameAlignment");
   public static final Property st_scale = property("ST_Scale");
   public static final Property st_scaleX = property("ST_ScaleX");
   public static final Property st_scaleY = property("ST_ScaleY");
   public static final Property st_segmentize = property("ST_Segmentize");
   public static final Property st_setPoint = property("ST_SetPoint");
   public static final Property st_setSRID = property("ST_SetSRID");
   public static final Property st_sharedPaths = property("ST_SharedPaths");
   public static final Property st_shearTransformation = property("ST_ShearTransformation");
   public static final Property st_shiftLongitude = property("ST_ShiftLongitude");
   public static final Property st_shortestLine = property("ST_ShortestLine");
   public static final Property st_shortestLine3D = property("ST_3DShortestLine");
   public static final Property st_simplify = property("ST_Simplify");
   public static final Property st_simplifyPreserveTopology = property("ST_SimplifyPreserveTopology");
   public static final Property st_simplifyVW = property("ST_SimplifyVW");
   public static final Property st_skewX = property("ST_SkewX");
   public static final Property st_skewY = property("ST_SkewY");
   public static final Property st_slope = property("ST_Slope");
   public static final Property st_snap = property("ST_Snap");
   public static final Property st_snapToGrid = property("ST_SnapToGrid");
   public static final Property st_split = property("ST_Split");
   public static final Property st_srid = property("ST_SRID");
   public static final Property st_sridToEPSG = property("ST_SRIDToEPSG");
   public static final Property st_startPoint = property("ST_StartPoint");
   public static final Property st_straightSkeleton = property("ST_StraightSkeleton");
   public static final Property st_summary = property("ST_Summary");
   public static final Property st_summaryStats = property("ST_SummaryStats");
   public static final Property st_swapOrdinates = property("ST_SwapOrdinates");
   public static final Property st_symDifference = property("ST_SymDifference");
   public static final Property st_tesselate = property("ST_Tesselate");
   public static final Property st_t = property("ST_T");
   public static final Property st_tilegridxoffset = property("ST_TileGridXOffset");
   public static final Property st_tilegridyoffset = property("ST_TileGridYOffset");
   public static final Property st_tileheight = property("ST_TileHeight");
   public static final Property st_tilewidth = property("ST_TileWidth");
   public static final Property st_tMax = property("ST_TMax");
   public static final Property st_tMin = property("ST_TMin");
   public static final Property st_toDegrees=property("ST_ToDegrees");
   public static final Property st_toRadians=property("ST_ToRadians");
   public static final Property st_tpi = property("ST_TPI");
   public static final Property st_tri = property("ST_TRI");
   public static final Property st_transform = property("ST_Transform");
   public static final Property st_translate = property("ST_Translate");
   public static final Property st_transscale = property("ST_TransScale");
   public static final Property st_upperLeftX = property("ST_UpperLeftX");
   public static final Property st_upperLeftY = property("ST_UpperLeftY");
   public static final Property st_union = property("ST_Union");
   public static final Property st_unaryUnion = property("ST_UnaryUnion");
   public static final Property st_value = property("ST_Value");
   public static final Property st_vectorize = property("ST_Vectorize");
   public static final Property st_voronoiLines = property("ST_VoronoiLines");
   public static final Property st_voronoiPolygons = property("ST_VoronoiPolygons");
   public static final Property st_width = property("ST_Width");
   public static final Property st_wkbToSQL = property("ST_WKBToSQL");
   public static final Property st_wktToSQL = property("ST_WKTToSQL");
   public static final Property st_worldToRasterCoord = property("ST_WorldToRasterCoord");
   public static final Property st_worldToRasterCoordX = property("ST_WorldToRasterCoordX");
   public static final Property st_worldToRasterCoordY = property("ST_WorldToRasterCoordY");
   public static final Property st_x = property("ST_X");
   public static final Property st_xMin = property("ST_XMin");
   public static final Property st_xMax = property("ST_XMax");
   public static final Property st_y = property("ST_Y");
   public static final Property st_yMin = property("ST_YMin");
   public static final Property st_yMax = property("ST_YMax");
   public static final Property st_z = property("ST_Z");
   public static final Property st_zMin = property("ST_ZMin");
   public static final Property st_zMax = property("ST_ZMax");
   public static final Property st_zmFlag = property("ST_ZmFlag");
   public static final String WKB = "WKB";
public static final String GeoJSON = "GeoJSON";
public static final String GeoJSONLD = "GeoJSONLD";
public static final String GeoHash = "GeoHash";
public static final String GeoBuf = "GeoBuf";
public static final String GeoURI="GeoURI";
public static final String GeoRSS="GeoRSS";
public static final String GMLCOV="GMLCOV";
public static final String KML = "KML";
public static final String WKBRaster = "WKBRaster";
public static final String GEOTIFF = "GeoTIFF";
public static final String EncodedPolyline = "EncodedPolyline";
public static final String Polyshape= "Polyshape";
public static final String TWKB = "TWKB";
public static final String HEXWKB = "HEXWKB";
public static final String DXF="DXF";
public static final String MVT = "MVT";
public static final String X3D = "X3D";
public static final String OSM= "OSM";
public static final String NetCDF="NetCDF";
public static final String HexWKBRaster = "HexWKBRaster";
public static final String TopoJSON = "TopoJSON";
public static final String TemporalRange="TemporalRange";
public static final String CoverageJSON = "CoverageJSON";

































































































































   public static class Nodes {
      public static final Node SpatialObject = PostGISGeo.SpatialObject.asNode();
      public static final Node Feature = PostGISGeo.Feature.asNode();
      // simple features topological relations
      public static final Node st_area= PostGISGeo.st_area.asNode();
      public static final Node st_azimuth= PostGISGeo.st_azimuth.asNode();
      public static final Node st_band= PostGISGeo.st_band.asNode();
      public static final Node st_bandmetadata= PostGISGeo.st_bandmetadata.asNode();
      public static final Node st_bandnodatavalue= PostGISGeo.st_bandnodatavalue.asNode();
      public static final Node st_bandpixeltype= PostGISGeo.st_bandpixeltype.asNode();
      public static final Node st_centroid = PostGISGeo.st_centroid.asNode();
      public static final Node st_clip = PostGISGeo.st_clip.asNode();
      public static final Node st_closestPoint = PostGISGeo.st_closestPoint.asNode();
      public static final Node st_concaveHull = PostGISGeo.st_concaveHull.asNode();
      public static final Node st_delaunayTriangles = PostGISGeo.st_delaunayTriangles.asNode();
      public static final Node st_dimension = PostGISGeo.st_dimension.asNode();
      public static final Node st_endPoint = PostGISGeo.st_endPoint.asNode();
      public static final Node st_envelope = PostGISGeo.st_envelope.asNode();
      public static final Node st_flipCoordinates = PostGISGeo.st_flipCoordinates.asNode();
      public static final Node st_geometryType = PostGISGeo.st_geometryType.asNode();
      public static final Node st_geometryN = PostGISGeo.st_geometryN.asNode();
      public static final Node st_hausdorffDistance = PostGISGeo.st_hausdorffDistance.asNode();
      public static final Node st_isClosed = PostGISGeo.st_isClosed.asNode();
      public static final Node st_isCollection = PostGISGeo.st_isCollection.asNode();
      public static final Node st_isEmpty = PostGISGeo.st_isEmpty.asNode();
      public static final Node st_isRing = PostGISGeo.st_isRing.asNode();
      public static final Node st_isSimple = PostGISGeo.st_isSimple.asNode();
      public static final Node st_isValid = PostGISGeo.st_isValid.asNode();
      public static final Node st_isValidReason = PostGISGeo.st_isValidReason.asNode();
      public static final Node st_Length = PostGISGeo.st_Length.asNode();
      public static final Node st_Length2D = PostGISGeo.st_Length2D.asNode();
      public static final Node st_m = PostGISGeo.st_m.asNode();
      public static final Node st_makeLine = PostGISGeo.st_makeLine.asNode();
      public static final Node st_makePoint = PostGISGeo.st_makePoint.asNode();
      public static final Node st_makePointM = PostGISGeo.st_makePointM.asNode();
      public static final Node st_makePolygon = PostGISGeo.st_makePolygon.asNode();
      public static final Node st_mLineFromText = PostGISGeo.st_mLineFromText.asNode();
      public static final Node st_mPointFromText = PostGISGeo.st_mPointFromText.asNode();
      public static final Node st_mPolyFromText = PostGISGeo.st_mPolyFromText.asNode();
      public static final Node st_minimumBoundingCircle = PostGISGeo.st_minimumBoundingCircle.asNode();
      public static final Node st_nearestValue = PostGISGeo.st_nearestValue.asNode();
      public static final Node st_numBands = PostGISGeo.st_numBands.asNode();
      public static final Node st_numGeometries = PostGISGeo.st_numGeometries.asNode();
      public static final Node st_numPoints = PostGISGeo.st_numPoints.asNode();
      public static final Node st_nPoints = PostGISGeo.st_nPoints.asNode();  
      public static final Node st_offsetCurve = PostGISGeo.st_offsetCurve.asNode();
      public static final Node st_perimeter = PostGISGeo.st_perimeter.asNode();
      public static final Node st_perimeter2D = PostGISGeo.st_perimeter2D.asNode();
      public static final Node st_pixelAsPoint = PostGISGeo.st_pixelAsPoint.asNode();
      public static final Node st_pixelHeight = PostGISGeo.st_pixelHeight.asNode();
      public static final Node st_pointN = PostGISGeo.st_pointN.asNode();
      public static final Node st_rast_Contains = PostGISGeo.st_rast_Contains.asNode();
      public static final Node st_rast_Covers = PostGISGeo.st_rast_Covers.asNode();
      public static final Node st_rast_CoveredBy = PostGISGeo.st_rast_CoveredBy.asNode();
      public static final Node st_rast_Disjoint = PostGISGeo.st_rast_Disjoint.asNode();
      public static final Node st_rast_isEmpty = PostGISGeo.st_rast_isEmpty.asNode();
      public static final Node st_rast_Overlaps = PostGISGeo.st_rast_Overlaps.asNode();
      public static final Node st_rast_srid = PostGISGeo.st_rast_srid.asNode();
      public static final Node st_rast_Touches = PostGISGeo.st_rast_Touches.asNode();
      public static final Node st_rast_Within = PostGISGeo.st_rast_Within.asNode();
      public static final Node st_rasterToWorldCoord = PostGISGeo.st_rasterToWorldCoord.asNode();
      public static final Node st_rasterToWorldCoordX = PostGISGeo.st_rasterToWorldCoordX.asNode();
      public static final Node st_rasterToWorldCoordY = PostGISGeo.st_rasterToWorldCoordY.asNode();
      public static final Node st_reverse = PostGISGeo.st_reverse.asNode();
      public static final Node st_rotate = PostGISGeo.st_rotate.asNode();
      public static final Node st_scale = PostGISGeo.st_scale.asNode();
      public static final Node st_segmentize = PostGISGeo.st_segmentize.asNode();
      public static final Node st_setPoint = PostGISGeo.st_setPoint.asNode();
      public static final Node st_simplify = PostGISGeo.st_simplify.asNode();
      public static final Node st_simplifyPreserveTopology = PostGISGeo.st_simplifyPreserveTopology.asNode();
      public static final Node st_snap = PostGISGeo.st_snap.asNode();
      public static final Node st_split = PostGISGeo.st_split.asNode();
      public static final Node st_srid = PostGISGeo.st_srid.asNode();
      public static final Node st_startPoint = PostGISGeo.st_startPoint.asNode();
      public static final Node st_summary = PostGISGeo.st_summary.asNode();
      public static final Node st_summaryStats = PostGISGeo.st_summaryStats.asNode();
      public static final Node st_swapOrdinates = PostGISGeo.st_swapOrdinates.asNode();
      public static final Node st_transform = PostGISGeo.st_transform.asNode();
      public static final Node st_translate = PostGISGeo.st_translate.asNode();
      public static final Node st_transscale = PostGISGeo.st_transscale.asNode();
      public static final Node st_unaryUnion = PostGISGeo.st_unaryUnion.asNode();
      public static final Node st_upperLeftX = PostGISGeo.st_upperLeftX.asNode();
      public static final Node st_upperLeftY = PostGISGeo.st_upperLeftY.asNode();
      public static final Node st_value = PostGISGeo.st_value.asNode();
      public static final Node st_width = PostGISGeo.st_width.asNode();
      public static final Node st_worldToRasterCoord = PostGISGeo.st_worldToRasterCoord.asNode();
      public static final Node st_worldToRasterCoordX = PostGISGeo.st_worldToRasterCoordX.asNode();
      public static final Node st_worldToRasterCoordY = PostGISGeo.st_worldToRasterCoordY.asNode();
      public static final Node st_x = PostGISGeo.st_x.asNode();
      public static final Node st_xMin = PostGISGeo.st_xMin.asNode();
      public static final Node st_xMax = PostGISGeo.st_xMax.asNode();
      public static final Node st_y = PostGISGeo.st_y.asNode();
      public static final Node st_yMin = PostGISGeo.st_yMin.asNode();
      public static final Node st_yMax = PostGISGeo.st_yMax.asNode();
      public static final Node st_z = PostGISGeo.st_z.asNode();

   }
}

