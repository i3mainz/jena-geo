package de.hsmainz.cs.semgis.arqextension.geometry.transform;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.jaitools.jts.LineSmoother;
import org.locationtech.jts.geom.LineString;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

/**
 * Returns a "smoothed" version of the given geometry using the Chaikin algorithm.
 *
 */
public class ChaikinSmoothing extends FunctionBase3{

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v1);
		double nIterations=v2.getDouble();
		Boolean preserveEndpoints=v3.getBoolean();
		LineSmoother smoother=new LineSmoother();
		LineString res = smoother.smooth((LineString)geom1.getParsingGeometry(), nIterations);
    	GeometryWrapper lineWrapper = GeometryWrapperFactory.createLineString(res, "<http://www.opengis.net/def/crs/EPSG/0/"+geom1.getSRID()+">", WKTDatatype.URI);	
    	return lineWrapper.asNodeValue();
	}
	
	/*public Coordinate[] refinePath(Coordinate[] path)
	{
	  List<Coordinate> ret = new LinkedList<Coordinate>();
	  Queue<Coordinate> procPath = new Queue<Coordinate>(path);
	  ret.add(path[0]);

	  Coordinate curPoint, prevPoint, nextPoint, 
	          currentHeading, nextHeading, 
	          pointQ, pointR;
	  float angle;
	  prevPoint = procPath.poll();
	  while(procPath.size() > 1)
	  {
	    curPoint = procPath.poll();
	    nextPoint = procPath.First();
	    currentHeading = (curPoint - prevPoint).normalized;
	    nextHeading = (nextPoint - curPoint).normalized;
	    angle = Vector3.Angle(currentHeading, nextHeading);
	    if (angle >= 30)
	    {
	      if (angle >= 90)
	      {
	        procPath.poll();
	        prevPoint = curPoint;
	        continue;
	      }
	      pointQ = (0.75f * curPoint) + (0.25f * nextPoint);
	      pointR= (0.25f * curPoint) + (0.75f * nextPoint);
	      ret.add(pointQ);
	      ret.add(pointR);
	      prevPoint = pointR;
	    }
	    else
	    {
	      // Paranoid check.
	      if(ret.contains(curPoint) == false)
	          ret.add(curPoint);
	      prevPoint = curPoint;
	    }
	    
	  }

	  // Make sure we get home.
	  if(ret.contains(path[path.length - 1]) == false) 
	    ret.add(path[path.length - 1]);
	  return ret;
	}*/

}
