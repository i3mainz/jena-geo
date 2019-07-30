package de.hsmainz.cs.semgis.arqextension.geometry.transform;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.jaitools.jts.LineSmoother;
import org.locationtech.jts.geom.Coordinate;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Returns a "smoothed" version of the given geometry using the Chaikin algorithm.
 *
 */
public class ChaikinSmoothing extends FunctionBase3{

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v1);
		BigInteger nIterations=v2.getInteger();
		Boolean preserveEndpoints=v3.getBoolean();
		LineSmoother smoother=new LineSmoother();
        throw new UnsupportedOperationException("Not supported yet.");
	}
	
	public Coordinate[] refinePath(Coordinate[] path)
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
	}

}
