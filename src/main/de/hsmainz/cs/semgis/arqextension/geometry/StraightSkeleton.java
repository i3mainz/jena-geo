package de.hsmainz.cs.semgis.arqextension.geometry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.vecmath.Vector3d;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineSegment;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.Triangle;
import org.twak.camp.Edge;
import org.twak.camp.Machine;
import org.twak.utils.collections.Loop;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Compute a straight skeleton from a geometry
 *
 */
public class StraightSkeleton extends FunctionBase1 {

	public StraightSkeleton() {
		
	}
	
	@Override
	public NodeValue exec(NodeValue v) {
		GeometryWrapper geometry = GeometryWrapper.extract(v);
        Geometry geom = geometry.getParsingGeometry();
        throw new UnsupportedOperationException("Not supported yet.");
	}
	
	public Set<LineSegment> skeletonizeStraightSkeleton(Polygon polygon) {
		    Set<LineSegment> skeletonSegments = new HashSet<LineSegment>();
		    Machine directionMachine = new Machine();

		    // when the geometry is too big, it needs to be simplified first
		    Polygon geom = polygon;
		    if (polygon.numPoints() > 500) {
		      geom = (Polygon) Filtering.DouglasPeucker(polygon, 15.0);
		    }
		    if (polygon.numPoints() > 1000) {
		      geom = (Polygon) Filtering.DouglasPeucker(polygon, 30.0);
		    }

		    Polygon p = (Polygon) geom.reverse();
		    LoopL<Edge> input = new LoopL<Edge>();

		    Ring rExt = p.getExterior();

		    Loop<Edge> loop = new Loop<Edge>();
		    List<Edge> lEExt = fromDPLToEdges(rExt.coord());
		    for (Edge e : lEExt)
		      loop.append(e);
		    for (Edge e : lEExt)
		      e.machine = directionMachine;

		    input.add(loop);

		    for (Ring rInt : p.getInterior()) {

		      Loop<Edge> loopIn = new Loop<Edge>();
		      input.add(loopIn);
		      List<Edge> lInt = fromDPLToEdges(rInt.coord());
		      for (Edge e : lInt)
		        loop.append(e);

		      for (Edge e : lInt)
		        e.machine = directionMachine;
		    }

		    Skeleton ske = new Skeleton(input, true);
		    ske.skeleton();

		    for (SharedEdge edge : ske.output.edges.map.keySet()) {
		      LineSegment segment = new GM_LineSegment(
		          new DirectPosition(edge.getStart(edge.left).x,
		              edge.getStart(edge.left).y),
		          new DirectPosition(edge.getEnd(edge.left).x,
		              edge.getEnd(edge.left).y));
		      if (segment.intersects(polygon.getExterior().getPrimitive()))
		        continue;
		      for (Ring hole : polygon.getInterior()) {
		        if (segment.intersects(hole.getPrimitive()))
		          continue;
		      }
		      if (polygon.disjoint(segment))
		        continue;
		      skeletonSegments.add(segment);
		    }

		    return skeletonSegments;
		  }
}

