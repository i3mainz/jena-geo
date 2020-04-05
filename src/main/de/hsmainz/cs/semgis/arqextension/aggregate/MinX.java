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

public class MinX extends AggregatorBase {

	protected MinX(String name, boolean isDistinct, Expr expr) {
		super("MINX", false, expr);
		// TODO Auto-generated constructor stub
	}
	
	protected MinX(Expr expr) {
		super("MINX", true, expr);
		// TODO Auto-generated constructor stub
	}

	private static class AccMinX extends AccumulatorExpr
    {
        // Non-empty case but still can be nothing because the expression may be undefined.
        private NodeValue minSoFar = null ;

        public AccMinX(Expr expr) { super(expr, false) ; }

        static final boolean DEBUG = false ;

        @Override
        public void accumulate(NodeValue nv, Binding binding, FunctionEnv functionEnv)
        { 
        	
        	GeometryWrapper geometry = GeometryWrapper.extract(nv);
            Geometry geo=geometry.getParsingGeometry();
            Double localminX=0.;
            Double minX=Double.MAX_VALUE;
            for(Coordinate coord:geo.getCoordinates()) {
            	if(minX>coord.getX()) {
            		minX=coord.getX();
            	}
            }
        	NodeValue localMin=NodeValue.makeDouble(localminX);
            if ( minSoFar == null )
            {
                minSoFar = localMin ;
                if ( DEBUG ) System.out.println("min: init : "+localMin) ;
                return ;
            }

            int x = NodeValue.compareAlways(minSoFar, localMin) ;
            if ( x > 0 )
                minSoFar = localMin ;

            if ( DEBUG ) System.out.println("min: "+localMin+" ==> "+minSoFar) ;
        }
        
        @Override
        protected void accumulateError(Binding binding, FunctionEnv functionEnv)
        {}

        @Override
        public NodeValue getAccValue()
        { return minSoFar ; }
    }

	@Override
	public Aggregator copy(ExprList exprs) {
		return new MinX(exprs.get(0)) ;
	}

	@Override
	public boolean equals(Aggregator other, boolean bySyntax) {
		if ( other == null ) return false ;
        if ( this == other ) return true ; 
        if ( ! ( other instanceof MinX ) )
            return false ;
        MinX agg = (MinX)other ;
        return exprList.equals(agg.exprList, bySyntax) ;
	}

	@Override
	public Accumulator createAccumulator() {
		return new AccMinX(getExpr());
	}

	@Override
	public Node getValueEmpty() {
		return null;
	}

	@Override
	public int hashCode() {
		return getExpr().hashCode() ;
	}
	
}
