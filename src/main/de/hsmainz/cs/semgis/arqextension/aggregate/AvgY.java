package de.hsmainz.cs.semgis.arqextension.aggregate;

import org.apache.jena.graph.Node;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.Expr;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.expr.aggregate.Accumulator;
import org.apache.jena.sparql.expr.aggregate.AccumulatorExpr;
import org.apache.jena.sparql.expr.aggregate.Aggregator;
import org.apache.jena.sparql.expr.aggregate.AggregatorBase;
import org.apache.jena.sparql.expr.nodevalue.XSDFuncOp;
import org.apache.jena.sparql.function.FunctionEnv;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class AvgY extends AggregatorBase {

	protected AvgY(boolean isDistinct, Expr expr) {
		super("AVGY", false, expr);
		// TODO Auto-generated constructor stub
	}
	
	 public AvgY(Expr expr) { super("AVGY", false, expr) ; } 
	    
	    private static Logger log = LoggerFactory.getLogger("AVGY") ;

	    @Override
	    public Aggregator copy(ExprList expr) { return new AvgY(expr.get(0)) ; }

	    // XQuery/XPath Functions&Operators suggests zero
	    // SQL suggests null.
	    private static final NodeValue noValuesToAvg = NodeValue.nvZERO ; // null 

	    @Override
	    public Accumulator createAccumulator()
	    { 
	        return new AccAvgY(getExpr()) ;
	    }

	    @Override
	    public Node getValueEmpty()     { return NodeValue.toNode(noValuesToAvg) ; } 
	    
	    @Override
	    public int hashCode()   { return HC_AggAvg ^ getExprList().hashCode() ; }

	    @Override
	    public boolean equals(Aggregator other, boolean bySyntax) {
	        if ( other == null ) return false ;
	        if ( this == other ) return true ;
	        if ( ! ( other instanceof AvgY ) ) return false ;
	        AvgY a = (AvgY)other ;
	        return exprList.equals(a.exprList, bySyntax) ;
	    }
	
	
	private static class AccAvgY extends AccumulatorExpr
 {
     // Non-empty case but still can be nothing because the expression may be undefined.
     private NodeValue total = noValuesToAvg ;
     private int count = 0 ;

     public AccAvgY(Expr expr) { super(expr, false) ; }

     @Override
     protected void accumulate(NodeValue nv, Binding binding, FunctionEnv functionEnv)
     { 
			log.debug("avg {}", nv);
			GeometryWrapper geometry = GeometryWrapper.extract(nv);
         Geometry geo=geometry.getXYGeometry();
         if ( nv.isNumber() ) {
         for(Coordinate coord:geo.getCoordinates()) {
         	NodeValue nvy=NodeValue.makeDouble(coord.getY());            	

             if ( total == noValuesToAvg )
                 total = nvy ;
             else
                 total = XSDFuncOp.numAdd(nvy, total) ;
             count++ ;
         }
         }
         else
         {
             //ARQ.getExecLogger().warn("Evaluation error: avg() on "+nv) ;
             throw new ExprEvalException("avg: not a number: "+nv) ;
         }

         log.debug("avg count {}", count);

     }
     
     @Override
     protected void accumulateError(Binding binding, FunctionEnv functionEnv)
     {}

     @Override
     public NodeValue getAccValue()
     {
         if ( count == 0 ) return noValuesToAvg ;
         if ( super.errorCount != 0 )
             //throw new ExprEvalException("avg: error in group") ; 
             return null ;
         NodeValue nvCount = NodeValue.makeInteger(count) ;
         return XSDFuncOp.numDivide(total, nvCount) ;
     }
 }
	
}
