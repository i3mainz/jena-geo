package de.hsmainz.cs.semgis.arqextension.aggregate;

import org.apache.jena.sparql.expr.Expr;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.aggregate.Aggregator;

public class AvgZDistinct extends AvgZ {

	public AvgZDistinct(Expr expr) {
		super(true,expr);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Aggregator copy(ExprList exprs) {
		return new AvgZDistinct(exprs.get(0)) ;
	}
	
	@Override
	public boolean equals(Aggregator other, boolean bySyntax) {
		if ( other == null ) return false ;
        if ( this == other ) return true ; 
        if ( ! ( other instanceof AvgZDistinct ) )
            return false ;
        AvgZDistinct agg = (AvgZDistinct)other ;
        return exprList.equals(agg.exprList, bySyntax) ;
	}
	
}
