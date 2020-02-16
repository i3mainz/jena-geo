package de.hsmainz.cs.semgis.arqextension.aggregate;

import org.apache.jena.graph.Node;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.Expr;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.expr.aggregate.Accumulator;
import org.apache.jena.sparql.expr.aggregate.AccumulatorExpr;
import org.apache.jena.sparql.expr.aggregate.Aggregator;
import org.apache.jena.sparql.expr.aggregate.AggregatorBase;
import org.apache.jena.sparql.function.FunctionEnv;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class BoundingBox extends AggregatorBase {

	protected BoundingBox(Expr expr) {
		super("BBOX", true, expr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Aggregator copy(ExprList exprs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Aggregator other, boolean bySyntax) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Accumulator createAccumulator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getValueEmpty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private static class AccBBOX extends AccumulatorExpr
    {
        // Non-empty case but still can be nothing because the expression may be undefined.
        private NodeValue maxXSoFar = null ;     
        private NodeValue maxYSoFar = null ;
        private NodeValue maxZSoFar = null ;
        private NodeValue minXSoFar = null ;
        private NodeValue minYSoFar = null ;
        private NodeValue minZSoFar = null ;

        public AccBBOX(Expr expr) { super(expr, false) ; }

        static final boolean DEBUG = false ;

        @Override
        public void accumulate(NodeValue nv, Binding binding, FunctionEnv functionEnv)
        { 
        	GeometryWrapper geometry = GeometryWrapper.extract(nv);
            Geometry geo=geometry.getXYGeometry();
            geo.getEnvelope().getCoordinates()
            Double localmaxZ=0.,Double localmax;
        	for(Coordinate coord:geo.getCoordinates()) {
            	if(localmaxZ<coord.getZ()) {
            		localmaxZ=coord.getZ();
            	}
            }
        	NodeValue localMax=NodeValue.makeDouble(localmaxZ);
        	
            if ( maxSoFar == null )
            {
                maxSoFar = localMax ;
                if ( DEBUG ) System.out.println("max: init : "+localMax) ;
                return ;
            }

            int x = NodeValue.compareAlways(maxSoFar, localMax) ;
            if ( x < 0 )
                maxSoFar = localMax ;

            if ( DEBUG ) System.out.println("max: "+localMax+" ==> "+maxSoFar) ;
        }

        @Override
        protected void accumulateError(Binding binding, FunctionEnv functionEnv)
        {}

        @Override
        public NodeValue getAccValue()
        { return maxSoFar ; }
    }

}
