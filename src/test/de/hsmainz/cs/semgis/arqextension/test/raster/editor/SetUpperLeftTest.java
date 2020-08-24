package de.hsmainz.cs.semgis.arqextension.test.raster.editor;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.editor.AddBand;
import de.hsmainz.cs.semgis.arqextension.raster.editor.SetUpperLeft;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CovJSONDatatype;

public class SetUpperLeftTest extends SampleRasters {
	
	@Test
	public void testSetUpperLeft() {
		NodeValue covLiteral = NodeValue.makeNode(rasterLiteral1, CovJSONDatatype.INSTANCE);
        SetUpperLeft instance=new SetUpperLeft();
		throw new UnsupportedOperationException("Not yet implemented");
        /*NodeValue expResult = NodeValue.makeInteger(10);
        NodeValue result = instance.exec(covLiteral);
        assertEquals(expResult, result);*/
	}

}
