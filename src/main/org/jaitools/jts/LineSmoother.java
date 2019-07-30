/* 
 *  Copyright (c) 2010, Michael Bedward. All rights reserved. 
 *   
 *  Redistribution and use in source and binary forms, with or without modification, 
 *  are permitted provided that the following conditions are met: 
 *   
 *  - Redistributions of source code must retain the above copyright notice, this  
 *    list of conditions and the following disclaimer. 
 *   
 *  - Redistributions in binary form must reproduce the above copyright notice, this 
 *    list of conditions and the following disclaimer in the documentation and/or 
 *    other materials provided with the distribution.   
 *   
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR 
 *  ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON 
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */   

package org.jaitools.jts;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import org.jaitools.jts.AbstractSmoother.InterpPoint;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;


/**
 * Line smoothing by interpolation with cubic Bazier curves.
 * This code adapts an algorithm which was devised by Maxim Shemanarev
 * for polygons and described by him at:
 * <a href="http://www.antigrain.com/research/bezier_interpolation/index.html">
 * http://www.antigrain.com/research/bezier_interpolation/index.html</a>.
 * <p>
 * To use the same algorithm for {@code LineStrings} we add a dummy vertex to
 * either end, calculate control points for just the real vertices.
 * 
 * @author Michael Bedward
 * @since 1.1
 * @version $Id$
 */
public class LineSmoother {

	GeometryFactory fac=new GeometryFactory();

    
    /**
     * Calculates a pair of Bezier control points for each vertex in an
     * array of {@code Coordinates}.
     * 
     * @param coords input vertices
     * @param N number of coordinates in {@coords} to use
     * @param alpha tightness of fit
     * 
     * @return 2D array of {@code Coordinates} for positions of each pair of
     *         control points per input vertex
     */
    private Coordinate[][] getControlPoints(Coordinate[] coords, double alpha) {
        if (alpha < 0.0 || alpha > 1.0) {
            throw new IllegalArgumentException("alpha must be a value between 0 and 1 inclusive");
        }

        final int N = coords.length;
        Coordinate[][] ctrl = new Coordinate[N][2];

        Coordinate[] v = new Coordinate[3];

        Coordinate[] mid = new Coordinate[2];
        mid[0] = new Coordinate();
        mid[1] = new Coordinate();

        Coordinate anchor = new Coordinate();
        double[] vdist = new double[2];
        double mdist;

        // Start with dummy coordinate preceding first real coordinate
        v[1] = new Coordinate(
                2 * coords[0].x - coords[1].x, 2 * coords[0].y - coords[1].y);
        v[2] = coords[0];
        
        // Dummy coordinate for end of line
        Coordinate vN = new Coordinate(
                2 * coords[N-1].x - coords[N-2].x, 
                2 * coords[N-1].y - coords[N-2].y);
        
        mid[1].x = (v[1].x + v[2].x) / 2.0;
        mid[1].y = (v[1].y + v[2].y) / 2.0;
        vdist[1] = v[1].distance(v[2]);

        for (int i = 0; i < N; i++) {
            v[0] = v[1];
            v[1] = v[2];
            v[2] = (i < N - 1 ? coords[i + 1] : vN);

            mid[0].x = mid[1].x;
            mid[0].y = mid[1].y;
            mid[1].x = (v[1].x + v[2].x) / 2.0;
            mid[1].y = (v[1].y + v[2].y) / 2.0;

            vdist[0] = vdist[1];
            vdist[1] = v[1].distance(v[2]);

            double p = vdist[0] / (vdist[0] + vdist[1]);
            anchor.x = mid[0].x + p * (mid[1].x - mid[0].x);
            anchor.y = mid[0].y + p * (mid[1].y - mid[0].y);

            double xdelta = anchor.x - v[1].x;
            double ydelta = anchor.y - v[1].y;

            ctrl[i][0] = new Coordinate(
                    alpha*(v[1].x - mid[0].x + xdelta) + mid[0].x - xdelta,
                    alpha*(v[1].y - mid[0].y + ydelta) + mid[0].y - ydelta);

            ctrl[i][1] = new Coordinate(
                    alpha*(v[1].x - mid[1].x + xdelta) + mid[1].x - xdelta,
                    alpha*(v[1].y - mid[1].y + ydelta) + mid[1].y - ydelta);
        }

        return ctrl;
    }
    
    public static final SmootherControl control = new SmootherControl() {
        public double getMinLength() {
            return 0.0;
        }
        
        public int getNumVertices(double length) {
            return 10;
        }
    };
    
    /**
     * Creates a new {@code LineString} which is a smoothed version of 
     * the input {@code LineString}.
     * 
     * @param ls the input {@code LineString}
     * 
     * @param alpha a value between 0 and 1 (inclusive) specifying the tightness
     *        of fit of the smoothed boundary (0 is loose)
     * 
     * @return the smoothed {@code LineString}
     */
    public LineString smooth(LineString ls, double alpha) {
        Coordinate[] coords = ls.getCoordinates();
        
        Coordinate[][] controlPoints = getControlPoints(coords, alpha);
        
        final int N = coords.length;
        List<Coordinate> smoothCoords = new ArrayList<Coordinate>();
        double dist;
        for (int i = 0; i < N - 1; i++) {
            dist = coords[i].distance(coords[i+1]);
            if (dist < control.getMinLength()) {
                // segment too short - just copy input coordinate
                smoothCoords.add(new Coordinate(coords[i]));
                
            } else {
                int smoothN = control.getNumVertices(dist);
                Coordinate[] segment = cubicBezier(
                        coords[i], coords[i+1],
                        controlPoints[i][1], controlPoints[i+1][0],
                        smoothN);
            
                int copyN = i < N - 1 ? segment.length - 1 : segment.length;
                for (int k = 0; k < copyN; k++) {
                    smoothCoords.add(segment[k]);
                }
            }
        }
        smoothCoords.add(coords[N - 1]);

        return fac.createLineString(smoothCoords.toArray(new Coordinate[0]));
    }
    
    protected Coordinate[] cubicBezier(final Coordinate start, final Coordinate end,
            final Coordinate ctrl1, final Coordinate ctrl2, final int nv) {
                
        final Coordinate[] curve = new Coordinate[nv];
        
        final Coordinate[] buf = new Coordinate[3];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = new Coordinate();
        }
        
        curve[0] = new Coordinate(start);
        curve[nv - 1] = new Coordinate(end);
        InterpPoint[] ip = getInterpPoints(nv);

        for (int i = 1; i < nv-1; i++) {
            Coordinate c = new Coordinate();

            c.x = ip[i].t[0]*start.x + ip[i].t[1]*ctrl1.x + ip[i].t[2]*ctrl2.x + ip[i].t[3]*end.x;
            c.x /= ip[i].tsum;
            c.y = ip[i].t[0]*start.y + ip[i].t[1]*ctrl1.y + ip[i].t[2]*ctrl2.y + ip[i].t[3]*end.y;
            c.y /= ip[i].tsum;

            curve[i] = c;
        }

        return curve;
    }
    
    protected InterpPoint[] getInterpPoints(int npoints) {
        WeakReference<InterpPoint[]> ref = lookup.get(npoints);
        InterpPoint[] ip = null;
        if (ref != null) ip = ref.get();
        
        if (ip == null) {
            ip = new InterpPoint[npoints];
        
            for (int i = 0; i < npoints; i++) {
                double t = (double) i / (npoints - 1);
                double tc = 1.0 - t;

                ip[i] = new InterpPoint();
                ip[i].t[0] = tc*tc*tc;
                ip[i].t[1] = 3.0*tc*tc*t;
                ip[i].t[2] = 3.0*tc*t*t;
                ip[i].t[3] = t*t*t;
                ip[i].tsum = ip[i].t[0] + ip[i].t[1] + ip[i].t[2] + ip[i].t[3];
            }
            
            lookup.put(npoints, new WeakReference<InterpPoint[]>(ip));
        }
        
        return ip;
    }
    
}