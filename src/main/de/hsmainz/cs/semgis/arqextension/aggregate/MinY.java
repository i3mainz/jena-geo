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

public class MinY extends AggregatorBase {
	protected MinY(String name, boolean isDistinct, Expr expr) {
		super("MINY", false, expr);
		// TODO Auto-generated constructor stub
	}
	
	protected MinY(Expr expr) {
		super("MINY", false, expr);
	}

	private static class AccMinY extends AccumulatorExpr
    {
        // Non-empty case but still can be nothing because the expression may be undefined.
        private NodeValue minSoFar = null ;

        public AccMinY(Expr expr) { super(expr, false) ; }

        static final boolean DEBUG = false ;

        @Override
        public void accumulate(NodeValue nv, Binding binding, FunctionEnv functionEnv)
        { 
        	
        	GeometryWrapper geometry = GeometryWrapper.extract(nv);
            Geometry geo=geometry.getXYGeometry();
            Double localminY=0.;
            Double minY=Double.MAX_VALUE;
            for(Coordinate coord:geo.getCoordinates()) {
            	if(minY>coord.getY()) {
            		minY=coord.getY();
            	}
            }
        	NodeValue localMin=NodeValue.makeDouble(localminY);
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
		return new MinY(exprs.get(0)) ;
	}

	@Override
	public boolean equals(Aggregator other, boolean bySyntax) {
		if ( other == null ) return false ;
        if ( this == other ) return true ; 
        if ( ! ( other instanceof MinY ) )
            return false ;
        MinY agg = (MinY)other ;
        return exprList.equals(agg.exprList, bySyntax) ;
	}

	@Override
	public Accumulator createAccumulator() {
		return new AccMinY(getExpr());
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
