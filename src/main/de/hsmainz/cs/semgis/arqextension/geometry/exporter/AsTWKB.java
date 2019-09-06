package de.hsmainz.cs.semgis.arqextension.geometry.exporter;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.geowave.core.geotime.util.TWKBWriter;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Returns the geometry as TWKB, aka "Tiny Well-Known Binary"
 *
 */
public class AsTWKB extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v,NodeValue v1) {
		try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            String endianness = v1.getString();
            TWKBWriter writer=new TWKBWriter();
            byte[] result=writer.write(geometry.getParsingGeometry());
            if(v1!=null) {
                ByteBuffer bb = ByteBuffer.wrap(result);
            	if("XDR".equals(endianness)) {
                    bb.order( ByteOrder.BIG_ENDIAN);
            	}else if("NDR".equals(endianness)) {
            		bb.order( ByteOrder.LITTLE_ENDIAN);
            	}
                return NodeValue.makeString(new String(bb.array(),"UTF-8"));
            }
            return NodeValue.makeString(new String(result,"UTF-8"));
        } catch (DatatypeFormatException | UnsupportedEncodingException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
		
	}

}
