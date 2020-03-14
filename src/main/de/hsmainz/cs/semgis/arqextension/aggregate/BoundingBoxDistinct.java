package de.hsmainz.cs.semgis.arqextension.aggregate;

import org.apache.jena.sparql.expr.Expr;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.aggregate.Aggregator;

public class BoundingBoxDistinct extends BoundingBox {

	protected BoundingBoxDistinct(Expr expr) {
		super(expr);
		// TODO Auto-generated constructor stub
	}
		
	@Override
	public Aggregator copy(ExprList exprs) {
		return new BoundingBoxDistinct(exprs.get(0)) ;
	}

	@Override
	public boolean equals(Aggregator other, boolean bySyntax) {
		if ( other == null ) return false ;
        if ( this == other ) return true ; 
        if ( ! ( other instanceof BoundingBoxDistinct ) )
            return false ;
        BoundingBoxDistinct agg = (BoundingBoxDistinct)other ;
        return exprList.equals(agg.exprList, bySyntax) ;
	}

}
