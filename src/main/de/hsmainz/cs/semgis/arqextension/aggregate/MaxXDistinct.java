package de.hsmainz.cs.semgis.arqextension.aggregate;

import org.apache.jena.sparql.expr.Expr;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.aggregate.Aggregator;

public class MaxXDistinct extends MaxX {

	protected MaxXDistinct(Expr expr) {
		super("MAXX", true, expr);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Aggregator copy(ExprList exprs) {
		return new MaxXDistinct(exprs.get(0)) ;
	}
	
	@Override
	public boolean equals(Aggregator other, boolean bySyntax) {
		if ( other == null ) return false ;
        if ( this == other ) return true ; 
        if ( ! ( other instanceof MaxXDistinct ) )
            return false ;
        MaxXDistinct agg = (MaxXDistinct)other ;
        return exprList.equals(agg.exprList, bySyntax) ;
	}

}
