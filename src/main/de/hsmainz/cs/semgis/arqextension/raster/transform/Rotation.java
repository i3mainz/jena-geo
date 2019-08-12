package de.hsmainz.cs.semgis.arqextension.raster.transform;

import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import javax.media.jai.JAI;
import javax.media.jai.ParameterBlockJAI;
import javax.media.jai.PlanarImage;

import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.jena.sparql.function.FunctionEnv;
import org.apache.sis.coverage.SampleDimension;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.geometry.DirectPosition2D;
import org.apache.sis.internal.feature.jts.JTS;
import org.apache.sis.internal.referencing.j2d.AffineTransform2D;
import org.apache.sis.util.resources.Vocabulary;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Returns the rotation of the raster in radian.
 *
 */
public class Rotation extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue v) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	public GridCoverage execute(GridCoverage inputCoverage, Coordinate anchorPoint, double angle) {
        this.initilizeVariables(inputCoverage);

        final PlanarImage inputImage = (PlanarImage) inputCoverage.getRenderedImage();
        CoordinateReferenceSystem crs = inputCoverage.getCoordinateReferenceSystem();
        DirectPosition realPos = new DirectPosition2D(crs, anchorPoint.x, anchorPoint.y);

        // The default is the lower left corner of the input raster dataset.
        DirectPosition gridPos = new DirectPosition2D(-0.5, inputImage.getHeight() - 0.5);
        try {
            gridPos = RasterHelper.worldToGridPos(inputCoverage, realPos);
        } catch (TransformException e) {
            LOGGER.log(Level.FINER, e.getMessage(), e);
        }

        // Rotate_management (in_raster, out_raster, angle, {pivot_point}, {resampling_type})
        // http://resources.arcgis.com/en/help/main/10.1/#/Rotate/00170000007s000000/
        // http://java.sun.com/products/java-media/jai/forDevelopers/jai1_0_1guide-unc/Geom-image-manip.doc.html#51140

        // rotate = [xOrigin, yOrigin, angle, interpolation, backgroundValues]
        ParameterBlockJAI parameterBlock = new ParameterBlockJAI("rotate", "rendered");
        parameterBlock.addSource(inputImage);

        parameterBlock.setParameter("xOrigin", (float) gridPos.getOrdinate(0) + 0.5f);
        parameterBlock.setParameter("yOrigin", (float) gridPos.getOrdinate(1) + 0.5f);

        float rotZRadians = (float) SSUtils.convert2Radians(angle);
        parameterBlock.setParameter("angle", rotZRadians);

        parameterBlock.setParameter("interpolation", interpolation);

        final double[] backgroundValues = new double[inputImage.getSampleModel().getNumBands()];
        for (int index = 0; index < backgroundValues.length; index++) {
            backgroundValues[index] = NoData;
        }
        parameterBlock.setParameter("backgroundValues", backgroundValues);

        PlanarImage outputImage = JAI.create("rotate", parameterBlock);

        // rotate envelope
        Envelope newExt = (Envelope) inputCoverage.getEnvelope();
        try {
            // Important!, the raster dataset will rotate in a clockwise direction.
            rotZRadians = (float) SSUtils.convert2Radians(360 - angle);

            AffineTransform affineTransform = AffineTransform.getRotateInstance(rotZRadians,
                    anchorPoint.x, anchorPoint.y);
            MathTransform mathTransform = new AffineTransform2D(affineTransform);

            newExt = JTS.transform(Extent, mathTransform);
        } catch (MismatchedDimensionException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        } catch (TransformException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }

        // adjust extent
        final int column = outputImage.getWidth();
        final int row = outputImage.getHeight();
        final double maxX = newExt.getMinX() + (column * CellSizeX);
        final double maxY = newExt.getMinY() + (row * CellSizeY);

        Envelope Extent = new ReferencedEnvelope(newExt.getMinX(), maxX, newExt.getMinY(), maxY, crs);

        final int numBands = inputCoverage.getSampleDimensions().size();

        if (numBands == 1) {
            return createGridCoverage(inputCoverage.getName(), outputImage);
        } else {
            List<SampleDimension> bands = inputCoverage.getSampleDimensions();

            Set<Number> nodataValues = bands.get(0).getNoDataValues();
            Object noData = nodataValues == null ? Integer.MAX_VALUE : nodataValues[0];

            Map properties = inputCoverage.getProperties();
            properties.put(Vocabulary.formatInternational(VocabularyKeys.NODATA), noData);
            properties.put("GC_NODATA", noData);

            GridCoverageFactory factory = CoverageFactoryFinder.getGridCoverageFactory(null);
            return factory.create(inputCoverage.getName(), outputImage, Extent, bands, null,
                    properties);
        }

}
