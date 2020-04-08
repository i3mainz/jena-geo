package de.hsmainz.cs.semgis.arqextension.raster.attribute;

import java.awt.Rectangle;
import java.awt.image.Raster;

import javax.media.jai.PlanarImage;
import javax.media.jai.iterator.WritableRectIter;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.geotoolkit.coverage.grid.GridCoordinates2D;
import org.opengis.coverage.grid.GridCoordinates;

public class Curvature extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		// TODO Auto-generated method stub
		return null;
	}
	
	    protected double srcNoData = -Float.MAX_VALUE;

	    protected double _8DX = CellSizeX * 8;

	    protected double _8DY = CellSizeY * 8;

	    protected PlanarImage image;

	    protected java.awt.Rectangle bounds;

	    private int maxCol;

	    private int maxRow;
	
	private void visitCurvature(WritableRectIter writer, GridCoordinates pos, double zFactor) {
        // http://resources.arcgis.com/en/help/main/10.1/#/How_Curvature_works/009z000000vs000000/
        // Zeverbergen, L. W., and C. R. Thorne. 1987. Quantitative Analysis of Land Surface
        // Topography.
        // Earth Surface Processes and Landforms 12: 47–56.

        double[][] mx = getSubMatrix(pos, 3, 3, zFactor);
        if (Double.isNaN(mx[1][1]) || SSUtils.compareDouble(srcNoData, mx[1][1])) {
            writer.setSample(0, NoData);
            return;
        }

        // Z = Ax²y² + Bx²y + Cxy² + Dx² + Ey² + Fxy + Gx + Hy + I

        // A = [(Z1 + Z3 + Z7 + Z9) / 4 - (Z2 + Z4 + Z6 + Z8) / 2 + Z5] / L4
        // double A = ((Z1 + Z3 + Z7 + Z9) / 4 - (Z2 + Z4 + Z6 + Z8) / 2 + Z5) / L4 ;

        // B = [(Z1 + Z3 - Z7 - Z9) /4 - (Z2 - Z8) /2] / L3
        // double B = ((Z1 + Z3 - Z7 - Z9) /4 - (Z2 - Z8) /2) / L3;

        // C = [(-Z1 + Z3 - Z7 + Z9) /4 + (Z4 - Z6)] /2] / L3
        // double C = ((-Z1 + Z3 - Z7 + Z9) /4 + (Z4 - Z6)] /2) / L3;

        // +-------+ +----------+
        // | 0 1 2 | | Z1 Z2 Z3 |
        // | 3 4 5 |>| Z4 Z5 Z6 |
        // | 6 7 8 | | Z7 Z8 Z9 |
        // +-------+ +----------+
        // D = [(Z4 + Z6) /2 - Z5] / L2
        double D = ((mx[0][1] + mx[2][1]) / 2.0 - mx[1][1]) / xL2;

        // E = [(Z2 + Z8) /2 - Z5] / L2
        double E = ((mx[1][0] + mx[1][2]) / 2.0 - mx[1][1]) / yL2;

        // F = (-Z1 + Z3 + Z7 - Z9) / 4L2
        // double F = (mx[2][0] - mx[0][0] + mx[0][2] - mx[2][2]) / x4L2;

        // G = (-Z4 + Z6) / 2L
        double G = (mx[2][1] - mx[0][1]) / x2L;

        // H = (Z2 - Z8) / 2L
        double H = (mx[1][0] - mx[1][2]) / y2L;

        // I = Z5
        // double I = Z5;

        // The output of the Curvature tool is the second derivative of the surface—for example,
        // the slope of the slope—such that: Curvature = -2(D + E) * 100

        final double k2 = G * G + H * H;

        double curvature = 0;
        if (k2 != 0) {
            curvature = -2.0 * (E + D);

            // optional Horizontal curvature & Vertical curvature
            // double k1 = F * G * H;
            // double vCurv = -2.0 * (D * G * G + E * H * H + k1) / k2;
            // double hCurv = -2.0 * (D * H * H + E * G * G - k1) / k2;
        }

        // Units of the curvature output raster, as well as the units for the optional output
        // profile curve raster and output plan curve raster, are one hundredth (1/100) of a z-unit.

        curvature = curvature * (100.0 * zFactor);

        writer.setSample(0, curvature);
        updateStatistics(curvature);
    }
	
	protected double[][] getSubMatrix(GridCoordinates2D pos, int width, int height, double zFactor) {
        int posX = width / 2;
        int posY = height / 2;

        // upper-left corner
        GridCoordinates2D ulPos = new GridCoordinates2D(pos.x - posX, pos.y - posY);
        Rectangle rect = new Rectangle(ulPos.x, ulPos.y, width, height);

        Raster subsetRs = image.getData(rect);

        boolean hasNAN = false;
        double[][] mx = new double[width][height];
        for (int dy = ulPos.y, row = 0; row < height; dy++, row++) {
            for (int dx = ulPos.x, col = 0; col < width; dx++, col++) {
                if (dx < bounds.x || dy < bounds.y || dx >= maxCol || dy >= maxRow) {
                    mx[col][row] = Double.NaN;
                    hasNAN = true;
                } else {
                    double ret = subsetRs.getSampleDouble(dx, dy, 0);
                    if (compareDouble(ret, this.srcNoData,0.00001)) {
                        mx[col][row] = Double.NaN;
                        hasNAN = true;
                    } else {
                        mx[col][row] = ret * zFactor;
                    }
                }
            }
        }

        if (Double.isNaN(mx[1][1])) {
            return mx;
        }

        // http://help.arcgis.com/en/arcgisdesktop/10.0/help/index.html#/How_Slope_works/009z000000vz000000/
        // If any neighborhood cells are NoData, they are assigned the value of the center cell;
        // then the slope is computed.
        if (hasNAN) {
            for (int drow = 0; drow < height; drow++) {
                for (int dcol = 0; dcol < width; dcol++) {
                    if (Double.isNaN(mx[dcol][drow])) {
                        mx[dcol][drow] = mx[1][1];
                    }
                }
            }
        }

        return mx;
    }
	
	public static Boolean compareDouble(Double a, Double b, Double rTol) {
		final double aTol = 0.00000001;

        if (Math.abs(a - b) < aTol + (rTol * Math.abs(b))) {
            return true;
        }

        return false;
	}

}
