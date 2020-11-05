package de.hsmainz.cs.semgis.arqextension.test.raster.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.attribute.Envelope;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class EnvelopeTest extends SampleRasters {
	
	@Test
	public void testEnvelope() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        Envelope instance=new Envelope();
        NodeValue expResult = NodeValue.FALSE;
        NodeValue result = instance.exec(covLiteral);
        assertEquals(expResult, result);
	}

}
