package de.hsmainz.cs.semgis.arqextension.util.parsers;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.sis.coverage.Category;
import org.apache.sis.coverage.SampleDimension;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.coverage.grid.GridExtent;
import org.apache.sis.coverage.grid.GridGeometry;
import org.apache.sis.coverage.grid.ImageRenderer;
import org.apache.sis.geometry.Envelope2D;
import org.apache.sis.internal.coverage.BufferedGridCoverage;
import org.apache.sis.referencing.NamedIdentifier;
import org.apache.sis.referencing.crs.DefaultTemporalCRS;
import org.apache.sis.referencing.datum.DefaultTemporalDatum;
import org.apache.sis.referencing.factory.GeodeticAuthorityFactory;
import org.apache.sis.util.iso.Names;
import org.geotoolkit.referencing.CRS;
import org.json.JSONArray;
import org.json.JSONObject;
import org.opengis.metadata.spatial.DimensionNameType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.datum.TemporalDatum;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.util.FactoryException;
import org.opengis.util.GenericName;

import de.hsmainz.cs.semgis.arqextension.util.CovJSONCoverage;

public class CoverageJSONReader {

	public static GridCoverage covJSONStringToCoverage(String covjson) {
		return covJSONToCoverage(new JSONObject(covjson));
	}
	
	public static CovJSONCoverage covJSONToIntCoverage(JSONObject covjson) {
		CovJSONCoverage result=new CovJSONCoverage();
		JSONObject axes=covjson.getJSONObject("domain").getJSONObject("axes");
		Integer minX=Integer.MAX_VALUE;
		Integer maxX=0;
		Integer minY=Integer.MAX_VALUE;
		Integer maxY=0;
		Integer minZ=Integer.MAX_VALUE;
		Integer maxZ=0;
		Integer minT=Integer.MAX_VALUE;
		Integer maxT=0;
		List<DimensionNameType> axestypes=new LinkedList<DimensionNameType>();
		List<Long> mins=new LinkedList<Long>();
		List<Long> maxs=new LinkedList<Long>();
		if(axes.getJSONObject("x").has("values")) {
			JSONArray xvalues=axes.getJSONObject("x").getJSONArray("values");
			for(int i=0;i<xvalues.length();i++) {

				if(xvalues.getInt(i)<minX) {
					minX=xvalues.getInt(i);
				}
				if(xvalues.getInt(i)>maxX) {
					maxX=xvalues.getInt(i);
				}
			}
			System.out.println(minX+" - "+Long.valueOf(minX));
			mins.add(Long.valueOf(minX));
			maxs.add(Long.valueOf(maxX));
			axestypes.add(DimensionNameType.COLUMN);
		}else if(axes.getJSONObject("x").has("start") && axes.getJSONObject("x").has("stop")
				&& axes.getJSONObject("x").has("num")) {
			mins.add(axes.getJSONObject("x").getLong("start"));
			maxs.add(axes.getJSONObject("x").getLong("stop"));
			axestypes.add(DimensionNameType.COLUMN);
		}
		System.out.println(mins);
		System.out.println(maxs);
		if(axes.getJSONObject("y").has("values")) {
			JSONArray yvalues=axes.getJSONObject("y").getJSONArray("values");
			for(int i=0;i<yvalues.length();i++) {
				if(yvalues.getInt(i)<minY) {
					minY=yvalues.getInt(i);
				}
				if(yvalues.getInt(i)>maxY) {
					maxY=yvalues.getInt(i);
				}
			}
			mins.add(Long.valueOf(minY).longValue());
			maxs.add(Long.valueOf(maxY).longValue());
			axestypes.add(DimensionNameType.ROW);
		}else if(axes.getJSONObject("y").has("start") && axes.getJSONObject("y").has("stop")
				&& axes.getJSONObject("y").has("num")) {
			mins.add(axes.getJSONObject("y").getLong("start"));
			maxs.add(axes.getJSONObject("y").getLong("stop"));
			axestypes.add(DimensionNameType.ROW);
		}
		System.out.println(mins);
		System.out.println(maxs);
		if(axes.has("z")) {
			if(axes.getJSONObject("z").has("values")) {
				JSONArray zvalues=axes.getJSONObject("z").getJSONArray("values");
				for(int i=0;i<zvalues.length();i++) {
					if(zvalues.getInt(i)<minZ) {
						minZ=zvalues.getInt(i);
					}
					if(zvalues.getInt(i)>maxZ) {
						maxZ=zvalues.getInt(i);
					}
				}
				mins.add(Long.valueOf(minZ).longValue());
				maxs.add(Long.valueOf(maxZ).longValue());
				axestypes.add(DimensionNameType.VERTICAL);
			}else if(axes.getJSONObject("z").has("start") && axes.getJSONObject("z").has("stop")
					&& axes.getJSONObject("z").has("num")) {
				mins.add(axes.getJSONObject("z").getLong("start"));
				maxs.add(axes.getJSONObject("z").getLong("stop"));
				axestypes.add(DimensionNameType.VERTICAL);
			}
		}
		System.out.println(mins);
		System.out.println(maxs);
		/*if(axes.has("t")) {
			if(axes.getJSONObject("t").has("values")) {
				JSONArray tvalues=axes.getJSONObject("t").getJSONArray("values");
				System.out.println(tvalues);
				try {
				for(int i=0;i<tvalues.length();i++) {
					if(tvalues.getInt(i)<minT) {
						minT=tvalues.getInt(i);
					}
					if(tvalues.getInt(i)>maxT) {
						maxT=tvalues.getInt(i);
					}
				}
				}catch(Exception e) {
					e.printStackTrace();
				}
				mins.add(Long.valueOf(minT).longValue());
				maxs.add(Long.valueOf(maxT).longValue());
				axestypes.add(DimensionNameType.TIME);
			}else if(axes.getJSONObject("t").has("start") && axes.getJSONObject("t").has("stop")
					&& axes.getJSONObject("t").has("num")) {
				mins.add(axes.getJSONObject("t").getLong("start"));
				maxs.add(axes.getJSONObject("t").getLong("stop"));
				axestypes.add(DimensionNameType.TIME);
			}
		}*/
		String epsg="";
		CoordinateReferenceSystem sys=null;
		MathTransform transform;
		if(covjson.getJSONObject("domain").has("referencing")) {
			JSONArray refs=covjson.getJSONObject("domain").getJSONArray("referencing");
			for(int i=0;i<refs.length();i++) {
				JSONObject ref=refs.getJSONObject(i);
				if(ref.getJSONObject("system").getString("type").equals("GeographicCRS")) {
					epsg=ref.getJSONObject("system").getString("id").substring(ref.getJSONObject("system").getString("id").lastIndexOf('/')+1);
					try {
						//sys=CRS.decode("EPSG:"+epsg);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				/*if(ref.getJSONObject("system").getString("type").equals("TemporalRS")) {
					TemporalDatum datum=new TemporalDatum
					datum.
				    DefaultTemporalCRS tcrs=DefaultTemporalCRS(new TreeMap<>(), datum, cs)
				}*/
			}
			
		}

		System.out.println(mins);
		System.out.println(maxs);
		System.out.println("DimensionNameType: "+axestypes);
		System.out.println("Mins: "+mins);
		System.out.println("Maxs: "+maxs);
		GridExtent extent=new GridExtent(axestypes.toArray(new DimensionNameType[0]),
				ArrayUtils.toPrimitive(mins.toArray(new Long[0])), 
				ArrayUtils.toPrimitive(maxs.toArray(new Long[0])),true);	
		Envelope2D gridenv=new Envelope2D();
		GridGeometry gridgeom=null;
		if(sys!=null) {
			gridgeom=new GridGeometry(extent,null,null,sys);//domain
		}else {
			gridgeom=new GridGeometry(extent, gridenv);//domain
		}

		Map<String,List<Category>> categories=new TreeMap<>();
		for(String key:covjson.getJSONObject("ranges").keySet()) {
			if(!categories.containsKey(key)) {
				categories.put(key,new LinkedList<Category>());
			}
			if("NdArray".equals(covjson.getJSONObject("ranges").getJSONObject(key).getString("type"))) {
				JSONArray values=covjson.getJSONObject("ranges").getJSONObject(key).getJSONArray("values");
				Integer[] intrange=new Integer[values.length()];
				for (int i = 0; i < values.length(); i++) {
				    intrange[i] = values.optInt(i);
				}
				org.apache.sis.measure.NumberRange<?> range;
				//NumberRange<Integer> range=new NumberRange<Integer>(null);
				/*Category.
				Category cat=new Category(key,range,null,null,null);
				categories.get(key).add(cat);*/

			}


					
		}
		return result;
	}
	
	public static GridCoverage covJSONToCoverage(JSONObject covjson) {
		JSONObject axes=covjson.getJSONObject("domain").getJSONObject("axes");
		Integer minX=Integer.MAX_VALUE;
		Integer maxX=0;
		Integer minY=Integer.MAX_VALUE;
		Integer maxY=0;
		Integer minZ=Integer.MAX_VALUE;
		Integer maxZ=0;
		Integer minT=Integer.MAX_VALUE;
		Integer maxT=0;
		List<DimensionNameType> axestypes=new LinkedList<DimensionNameType>();
		List<Long> mins=new LinkedList<Long>();
		List<Long> maxs=new LinkedList<Long>();
		if(axes.getJSONObject("x").has("values")) {
			JSONArray xvalues=axes.getJSONObject("x").getJSONArray("values");
			for(int i=0;i<xvalues.length();i++) {

				if(xvalues.getInt(i)<minX) {
					minX=xvalues.getInt(i);
				}
				if(xvalues.getInt(i)>maxX) {
					maxX=xvalues.getInt(i);
				}
			}
			System.out.println(minX+" - "+Long.valueOf(minX));
			mins.add(Long.valueOf(minX));
			maxs.add(Long.valueOf(maxX));
			axestypes.add(DimensionNameType.COLUMN);
		}else if(axes.getJSONObject("x").has("start") && axes.getJSONObject("x").has("stop")
				&& axes.getJSONObject("x").has("num")) {
			mins.add(axes.getJSONObject("x").getLong("start"));
			maxs.add(axes.getJSONObject("x").getLong("stop"));
			axestypes.add(DimensionNameType.COLUMN);
		}
		System.out.println(mins);
		System.out.println(maxs);
		if(axes.getJSONObject("y").has("values")) {
			JSONArray yvalues=axes.getJSONObject("y").getJSONArray("values");
			for(int i=0;i<yvalues.length();i++) {
				if(yvalues.getInt(i)<minY) {
					minY=yvalues.getInt(i);
				}
				if(yvalues.getInt(i)>maxY) {
					maxY=yvalues.getInt(i);
				}
			}
			mins.add(Long.valueOf(minY).longValue());
			maxs.add(Long.valueOf(maxY).longValue());
			axestypes.add(DimensionNameType.ROW);
		}else if(axes.getJSONObject("y").has("start") && axes.getJSONObject("y").has("stop")
				&& axes.getJSONObject("y").has("num")) {
			mins.add(axes.getJSONObject("y").getLong("start"));
			maxs.add(axes.getJSONObject("y").getLong("stop"));
			axestypes.add(DimensionNameType.ROW);
		}
		System.out.println(mins);
		System.out.println(maxs);
		if(axes.has("z")) {
			if(axes.getJSONObject("z").has("values")) {
				JSONArray zvalues=axes.getJSONObject("z").getJSONArray("values");
				for(int i=0;i<zvalues.length();i++) {
					if(zvalues.getInt(i)<minZ) {
						minZ=zvalues.getInt(i);
					}
					if(zvalues.getInt(i)>maxZ) {
						maxZ=zvalues.getInt(i);
					}
				}
				mins.add(Long.valueOf(minZ).longValue());
				maxs.add(Long.valueOf(maxZ).longValue());
				axestypes.add(DimensionNameType.VERTICAL);
			}else if(axes.getJSONObject("z").has("start") && axes.getJSONObject("z").has("stop")
					&& axes.getJSONObject("z").has("num")) {
				mins.add(axes.getJSONObject("z").getLong("start"));
				maxs.add(axes.getJSONObject("z").getLong("stop"));
				axestypes.add(DimensionNameType.VERTICAL);
			}
		}
		System.out.println(mins);
		System.out.println(maxs);
		/*if(axes.has("t")) {
			if(axes.getJSONObject("t").has("values")) {
				JSONArray tvalues=axes.getJSONObject("t").getJSONArray("values");
				System.out.println(tvalues);
				try {
				for(int i=0;i<tvalues.length();i++) {
					if(tvalues.getInt(i)<minT) {
						minT=tvalues.getInt(i);
					}
					if(tvalues.getInt(i)>maxT) {
						maxT=tvalues.getInt(i);
					}
				}
				}catch(Exception e) {
					e.printStackTrace();
				}
				mins.add(Long.valueOf(minT).longValue());
				maxs.add(Long.valueOf(maxT).longValue());
				axestypes.add(DimensionNameType.TIME);
			}else if(axes.getJSONObject("t").has("start") && axes.getJSONObject("t").has("stop")
					&& axes.getJSONObject("t").has("num")) {
				mins.add(axes.getJSONObject("t").getLong("start"));
				maxs.add(axes.getJSONObject("t").getLong("stop"));
				axestypes.add(DimensionNameType.TIME);
			}
		}*/
		String epsg="";
		CoordinateReferenceSystem sys=null;
		MathTransform transform;
		if(covjson.getJSONObject("domain").has("referencing")) {
			JSONArray refs=covjson.getJSONObject("domain").getJSONArray("referencing");
			for(int i=0;i<refs.length();i++) {
				JSONObject ref=refs.getJSONObject(i);
				if(ref.getJSONObject("system").getString("type").equals("GeographicCRS")) {
					epsg=ref.getJSONObject("system").getString("id").substring(ref.getJSONObject("system").getString("id").lastIndexOf('/')+1);
					try {
						//sys=CRS.decode("EPSG:"+epsg);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				/*if(ref.getJSONObject("system").getString("type").equals("TemporalRS")) {
					TemporalDatum datum=new TemporalDatum
					datum.
				    DefaultTemporalCRS tcrs=DefaultTemporalCRS(new TreeMap<>(), datum, cs)
				}*/
			}
			
		}

		System.out.println(mins);
		System.out.println(maxs);
		System.out.println("DimensionNameType: "+axestypes);
		System.out.println("Mins: "+mins);
		System.out.println("Maxs: "+maxs);
		GridExtent extent=new GridExtent(axestypes.toArray(new DimensionNameType[0]),
				ArrayUtils.toPrimitive(mins.toArray(new Long[0])), 
				ArrayUtils.toPrimitive(maxs.toArray(new Long[0])),true);	
		Envelope2D gridenv=new Envelope2D();
		GridGeometry gridgeom=null;
		if(sys!=null) {
			gridgeom=new GridGeometry(extent,null,null,sys);//domain
		}else {
			gridgeom=new GridGeometry(extent, gridenv);//domain
		}

		Map<String,List<Category>> categories=new TreeMap<>();
		for(String key:covjson.getJSONObject("ranges").keySet()) {
			if(!categories.containsKey(key)) {
				categories.put(key,new LinkedList<Category>());
			}
			if("NdArray".equals(covjson.getJSONObject("ranges").getJSONObject(key).getString("type"))) {
				JSONArray values=covjson.getJSONObject("ranges").getJSONObject(key).getJSONArray("values");
				Integer[] intrange=new Integer[values.length()];
				for (int i = 0; i < values.length(); i++) {
				    intrange[i] = values.optInt(i);
				}
				org.apache.sis.measure.NumberRange<?> range;
				//NumberRange<Integer> range=new NumberRange<Integer>(null);
				/*Category.
				Category cat=new Category(key,range,null,null,null);
				categories.get(key).add(cat);*/

			}


					
		}
/*		
		ParameterBlock pb = new ParameterBlock();
        pb.add(
                spi.createInputStreamInstance(
                        inFile, ImageIO.getUseCache(), ImageIO.getCacheDirectory()));
        pb.add(index);
        pb.add(Boolean.FALSE);
        pb.add(Boolean.FALSE);
        pb.add(Boolean.FALSE);
        pb.add(null);
        pb.add(null);
        pb.add(readP);
        pb.add(READER_SPI.createReaderInstance());
	*/
		List<SampleDimension> dimensions=new LinkedList<SampleDimension>();
		for(String key:covjson.getJSONObject("parameters").keySet()) {
			GenericName name=Names.createGenericName("http://www.semgis.de/geodata#", "#", new String[] {key});
			if(categories.containsKey(key)) {
				dimensions.add(new SampleDimension(name, Integer.valueOf("0"), categories.get(key)));
			}else {
				dimensions.add(new SampleDimension(name, 0, new LinkedList<Category>()));
			}
		}
		System.out.println(dimensions);
		/*
		 * Create the grid coverage, gets its image and set values directly as short
		 * integers.
		 */
		BufferedGridCoverage coverage = new BufferedGridCoverage(gridgeom,
				dimensions, DataBuffer.TYPE_INT);
		System.out.println(coverage);
		ImageRenderer renderer=new ImageRenderer(coverage,extent);
		//renderer.setData(data);
		//WritableRaster raster=renderer.raster();
		//WritableRaster rasterr = ((BufferedImage) coverage.render(null)).getRaster();
		
		//rasterr.setDataElements(x, y, rasterr);
		//rasterr.setRect(subtractedImage.getSourceImage(0).getData());
		return coverage;
	}
	
	
	public static void main(String[] args) {
		String covjson="{\r\n" + 
				"  \"type\" : \"Coverage\",\r\n" + 
				"  \"domain\" : {\r\n" + 
				"    \"type\" : \"Domain\",\r\n" + 
				"    \"domainType\" : \"Grid\",\r\n" + 
				"    \"axes\": {\r\n" + 
				"      \"x\" : { \"values\": [-10,-5,0] },\r\n" + 
				"      \"y\" : { \"values\": [40,50] },\r\n" + 
				"      \"z\" : { \"values\": [ 5] },\r\n" + 
				"      \"t\" : { \"values\": [\"2010-01-01T00:12:20Z\"] }\r\n" + 
				"    },\r\n" + 
				"    \"referencing\": [{\r\n" + 
				"      \"coordinates\": [\"y\",\"x\",\"z\"],\r\n" + 
				"      \"system\": {\r\n" + 
				"        \"type\": \"GeographicCRS\",\r\n" + 
				"        \"id\": \"http://www.opengis.net/def/crs/EPSG/0/4979\"\r\n" + 
				"      }\r\n" + 
				"    }, {\r\n" + 
				"      \"coordinates\": [\"t\"],\r\n" + 
				"      \"system\": {\r\n" + 
				"        \"type\": \"TemporalRS\",\r\n" + 
				"        \"calendar\": \"Gregorian\"\r\n" + 
				"      }\r\n" + 
				"    }]\r\n" + 
				"  },\r\n" + 
				"  \"parameters\" : {\r\n" + 
				"    \"ICEC\": {\r\n" + 
				"      \"type\" : \"Parameter\",\r\n" + 
				"      \"description\": {\r\n" + 
				"      	\"en\": \"Sea Ice concentration (ice=1;no ice=0)\"\r\n" + 
				"      },\r\n" + 
				"      \"unit\" : {\r\n" + 
				"        \"label\": {\r\n" + 
				"          \"en\": \"Ratio\"\r\n" + 
				"        },\r\n" + 
				"        \"symbol\": {\r\n" + 
				"          \"value\": \"1\",\r\n" + 
				"          \"type\": \"http://www.opengis.net/def/uom/UCUM/\"\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"observedProperty\" : {\r\n" + 
				"        \"id\" : \"http://vocab.nerc.ac.uk/standard_name/sea_ice_area_fraction/\",\r\n" + 
				"        \"label\" : {\r\n" + 
				"          \"en\": \"Sea Ice Concentration\"\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  },\r\n" + 
				"  \"ranges\" : {\r\n" + 
				"    \"ICEC\" : {\r\n" + 
				"      \"type\" : \"NdArray\",\r\n" + 
				"      \"dataType\": \"float\",\r\n" + 
				"      \"axisNames\": [\"t\",\"z\",\"y\",\"x\"],\r\n" + 
				"      \"shape\": [1, 1, 2, 3],\r\n" + 
				"      \"values\" : [ 0.5, 0.6, 0.4, 0.6, 0.2, null ]\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";
			GridCoverage cov=covJSONStringToCoverage(covjson);
			System.out.println(CoverageJsonWriter.coverageToCovJSON(cov).toString(2));
			
	}
}
