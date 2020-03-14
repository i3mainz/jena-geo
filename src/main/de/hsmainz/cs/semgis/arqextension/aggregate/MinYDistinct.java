package de.hsmainz.cs.semgis.arqextension.aggregate;

import org.apache.jena.sparql.expr.Expr;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.aggregate.Aggregator;

public class MinYDistinct extends MinY{

	protected MinYDistinct(Expr expr) {
		super("MINY", true, expr);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Aggregator copy(ExprList exprs) {
		return new MinYDistinct(exprs.get(0)) ;
	}
	
	@Override
	public boolean equals(Aggregator other, boolean bySyntax) {
		if ( other == null ) return false ;
        if ( this == other ) return true ; 
        if ( ! ( other instanceof MinYDistinct ) )
            return false ;
        MinYDistinct agg = (MinYDistinct)other ;
        return exprList.equals(agg.exprList, bySyntax) ;
	}
	
}
