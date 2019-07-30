package de.hsmainz.cs.semgis.arqextension.polygon;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.triangulate.Segment;

public class ApproximateMedialAxis extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		// TODO Auto-generated method stub
		return null;
	}

	private MultiLineString getMedialAxis(final Polygon poly) {

	      final ArrayList<LineString> list = new ArrayList<LineString>();
	      final GeometryFactory gf = new GeometryFactory();
	      final Coordinate[] coords = poly.getExteriorRing().getCoordinates();
	      final Point xpoints[] = new Point[coords.length];
	      for (int i = 0; i < coords.length; i++) {
	         xpoints[i] = new Point(coords[i].x, coords[i].y);
	      }
	      final Polygon xpoly = new Polygon(xpoints);
	      final Skeleton skeleton = new Skeleton(xpoly);
	      skeleton.execute();
	      final List lines = skeleton.getLines(false);
	      final Object[] array = lines.convertValuesToArray();

	      for (int i = 0; i < array.length; i++) {
	         final Segment segment = (Segment) array[i];
	         System.out.println(segment.toString());
	         final Coordinate[] segmentCoords = new Coordinate[] {
	                  new Coordinate(segment.getStartPoint().x, segment.getStartPoint().y),
	                  new Coordinate(segment.getEndPoint().x, segment.getEndPoint().y) };
	         list.add(gf.createLineString(segmentCoords));
	      }

	      return gf.createMultiLineString(list.toArray(new LineString[0]));


	   }
	
}
