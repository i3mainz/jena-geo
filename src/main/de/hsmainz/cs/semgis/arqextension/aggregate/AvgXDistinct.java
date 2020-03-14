package de.hsmainz.cs.semgis.arqextension.aggregate;

import org.apache.jena.sparql.expr.Expr;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.aggregate.Aggregator;

public class AvgXDistinct extends AvgX {

	public AvgXDistinct(Expr expr) {
		super(true,expr);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Aggregator copy(ExprList exprs) {
		return new AvgXDistinct(exprs.get(0)) ;
	}
	
	@Override
	public boolean equals(Aggregator other, boolean bySyntax) {
		if ( other == null ) return false ;
        if ( this == other ) return true ; 
        if ( ! ( other instanceof AvgXDistinct ) )
            return false ;
        AvgXDistinct agg = (AvgXDistinct)other ;
        return exprList.equals(agg.exprList, bySyntax) ;
	}

}
